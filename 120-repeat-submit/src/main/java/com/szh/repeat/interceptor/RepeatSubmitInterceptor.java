package com.szh.repeat.interceptor;

import com.alibaba.fastjson2.schema.ValidateResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szh.repeat.annocation.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 防止重复提交拦截器
 *
 * @author szh
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只过滤获取控制层中的方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 拦截器实现 AOP 功能
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {
                    try {
                        response.setStatus(200);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        ObjectMapper objectMapper = new ObjectMapper();
                        // 简化返回结果，开发中要封装成一个统一返回类
                        Map<String, Object> result = new HashMap<>();
                        result.put("code", 500);
                        result.put("msg", annotation.message());
                        result.put("data", null);

                        response.getWriter().print(objectMapper.writeValueAsString(result));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws ExecutionException;
}