package com.szh.qrcodelogin.rest;

import com.github.hui.quick.plugin.base.DomUtil;
import com.github.hui.quick.plugin.base.constants.MediaType;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;
import com.szh.qrcodelogin.util.IpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by szh on 2023-05-02
 *
 * @author szh
 */

@Controller
@CrossOrigin
public class QrLoginRest {

    @Value("${server.port}")
    private int port;

    /**
     * 缓存 SseEmitter
     */
    private Map<String, SseEmitter> cache = new ConcurrentHashMap<>();

    /**
     * 二维码生成接口
     */
    @GetMapping(path = "/login")
    public ModelAndView qr(Map<String, Object> data, ModelAndView modelAndView) throws IOException, WriterException {
        String id = UUID.randomUUID().toString();
        // 获取本机 IP，使用 127.0.0.1 会出现问题
        String ip = IpUtils.getLocalIP();

        String pref = "http://" + ip + ":" + port + "/";
        // 跳转 url，app 授权之后，跳转的页面
        modelAndView.addObject("redirect", pref + "home");
        // 订阅 url，用户访问这个 url，开启长连接，接收服务端推送的扫码，登录事件
        modelAndView.addObject("subscribe", pref + "subscribe?id=" + id);

        // 二维码 url：当用户扫描二维码后，会自动访问这个接口地址，手机端返回 scan.html 页面，请求登录
        String qrUrl = pref + "scan?id=" + id;

        // base64 图片
        String qrcode = QrCodeGenWrapper.of(qrUrl).setW(200).setDrawBgColor(Color.WHITE).setDrawStyle(QrCodeOptions.DrawStyle.CIRCLE).asString();
        modelAndView.addObject("qrcode", DomUtil.toDomSrc(qrcode, MediaType.ImageJpg));

        modelAndView.setViewName("login");

        return modelAndView;
    }

    /**
     * sse 订阅接口
     */
    @GetMapping(path = "/subscribe", produces = {org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter subscribe(String id) {
        // 设置 5 分钟的超过时间
        SseEmitter sseEmitter = new SseEmitter(5 * 60 * 1000L);
        cache.put(id, sseEmitter);
        sseEmitter.onTimeout(() -> cache.remove(id));
        sseEmitter.onError((e) -> cache.remove(id));
        return sseEmitter;
    }

    /**
     * 扫码接口
     */
    @GetMapping(path = "/scan")
    public String scan(Model model, HttpServletRequest request) throws IOException {
        String id = request.getParameter("id");
        SseEmitter sseEmitter = cache.get(id);
        if (sseEmitter != null) {
            // 告诉 pc 端，已经扫码了
            sseEmitter.send("scan");
        }
        // 授权同意 url
        String url = "http://" + IpUtils.getLocalIP() + ":" + port + "/accept?id=" + id;
        model.addAttribute("url", url);
        return "scan";
    }

    /**
     * 授权接口
     */
    @ResponseBody
    @GetMapping("/accept")
    public String accept(String id, String token) throws IOException {
        SseEmitter sseEmitter = cache.get(id);
        if (sseEmitter != null) {
            // 发送登录成功事件，并传递 cookie 值
            sseEmitter.send("login#qrlogin=" + token);
            sseEmitter.complete();
            cache.remove(id);
        }
        return "登录成功" + token;
    }

    /**
     * 首页
     */
    @GetMapping(path = {"home", ""})
    @ResponseBody
    public String home(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return "未登录!";
        }

        Optional<Cookie> cookie = Stream.of(cookies).filter(s -> s.getName().equalsIgnoreCase("qrlogin")).findFirst();
        return cookie.map(cookie1 -> "欢迎进入首页: " + cookie1.getValue()).orElse("未登录!");
    }
}
