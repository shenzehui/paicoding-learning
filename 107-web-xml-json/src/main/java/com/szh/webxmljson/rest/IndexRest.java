package com.szh.webxmljson.rest;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by szh on 2023-05-09
 *
 * @author szh
 */

@RestController
public class IndexRest {

    @Data
    public static class ResVo<T> {
        private int code;
        private String msg;
        private T data;

        public ResVo(int code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }
    }

    /**
     * produces:设置响应头中的 content-type
     */
    @GetMapping(path = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResVo<String> xml() {
        return new ResVo<>(0, "ok", "返回xml");
    }

    @GetMapping(path = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResVo<String> json() {
        return new ResVo<>(0, "ok", "返回json");
    }

    @GetMapping(path = "/")
    public ResVo<String> index() {
        return new ResVo<>(0, "ok", "简单的测试");
    }

    /**
     * 当指定了 accept 时，并且传参中指定了 mediaType，则以 mediaType 传参为准
     * 若不传 mediaType，则默认为 json 格式返回值，因为它在前
     *
     * @param mediaType
     * @return
     */
    @GetMapping(path = "/param")
    public ResVo<String> params(@RequestParam(name = "mediaType", required = false) String mediaType) {
        return new ResVo<>(0, "ok", String.format("基于传参来决定返回类型：%s", mediaType));
    }
}
