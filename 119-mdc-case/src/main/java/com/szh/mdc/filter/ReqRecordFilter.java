package com.szh.mdc.filter;

import com.szh.mdc.util.CrossUtil;
import com.szh.mdc.util.MdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "reqRecordFilter", asyncSupported = true)
public class ReqRecordFilter implements Filter {
    /**
     * 返回给前端的traceId，用于日志追踪
     */
    private static final String GLOBAL_TRACE_ID_HEADER = "g-trace-id";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = null;
        StopWatch stopWatch = new StopWatch("请求耗时");
        try {
            stopWatch.start("请求参数构建");
            request = this.initReqInfo((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
            stopWatch.stop();
            stopWatch.start("cors");
            CrossUtil.buildCors(request, (HttpServletResponse) servletResponse);
            stopWatch.stop();
            stopWatch.start("业务执行");
            filterChain.doFilter(request, servletResponse);
        } finally {
            if (stopWatch.isRunning()) {
                // 避免doFitler执行异常，导致上面的 stopWatch无法结束，这里先首当结束一下上次的计数
                stopWatch.stop();
            }
            stopWatch.start("输出请求日志");
            // 一个链路请求完毕，清空MDC相关的变量(如GlobalTraceId，用户信息)
            MdcUtil.clear();
            stopWatch.stop();

            log.info("{} - cost:\n{}", request.getRequestURI(), stopWatch.prettyPrint());
        }
    }

    @Override
    public void destroy() {
    }

    private HttpServletRequest initReqInfo(HttpServletRequest request, HttpServletResponse response) {
        if (isStaticURI(request)) {
            // 静态资源直接放行
            return request;
        }

        StopWatch stopWatch = new StopWatch("请求参数构建");
        try {
            stopWatch.start("traceId");
            // 添加全链路的traceId
            MdcUtil.addTraceId();
            stopWatch.stop();

            stopWatch.start("请求基本信息");
            // 手动写入一个session，借助 OnlineUserCountListener 实现在线人数实时统计
            request.getSession().setAttribute("latestVisit", System.currentTimeMillis());

            stopWatch.start("登录用户信息");

            stopWatch.start("回写traceId");
            // 返回头中记录traceId
            response.setHeader(GLOBAL_TRACE_ID_HEADER, Optional.ofNullable(MdcUtil.getTraceId()).orElse(""));
            stopWatch.stop();
        } catch (Exception e) {
            log.error("init reqInfo error!", e);
        } finally {
            log.info("{} -> 请求构建耗时: \n{}", request.getRequestURI(), stopWatch.prettyPrint());
        }
        return request;
    }

    private boolean isStaticURI(HttpServletRequest request) {
        return request == null || request.getRequestURI().endsWith("css") || request.getRequestURI().endsWith("js") || request.getRequestURI().endsWith("png") || request.getRequestURI().endsWith("ico") || request.getRequestURI().endsWith("svg") || request.getRequestURI().endsWith("min.js.map") || request.getRequestURI().endsWith("min.css.map");
    }

}
