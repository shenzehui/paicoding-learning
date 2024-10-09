package com.szh.flux.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */


@RestController
@RequestMapping("/flux")
public class TestController {

    @GetMapping("/hello")
    public Mono<String> sayHello(String name) {
        return Mono.just("hello" + name);
    }

    @GetMapping("/loop")
    public Flux<ServerSentEvent<String>> everySayHello(String name) {
        return Flux.interval(Duration.ofSeconds(1)).map(seed -> seed + seed)
                .map(s -> ServerSentEvent.<String>builder().event("rand").data(name + "|" + s).build());
    }
}
