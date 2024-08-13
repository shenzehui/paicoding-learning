package com.szh.context.consumer;

import com.szh.context.model.event.MsgEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Component
@Slf4j
public class MsgMessageListener implements ApplicationListener<MsgEvent> {
    @Override
    public void onApplicationEvent(MsgEvent event) {
        log.info("方式一接收到消息：{}", event);
    }
}
