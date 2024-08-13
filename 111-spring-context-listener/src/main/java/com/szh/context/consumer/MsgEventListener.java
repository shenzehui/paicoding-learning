package com.szh.context.consumer;

import com.szh.context.model.event.MsgEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
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
public class MsgEventListener {

    /**
     * 监听
     *
     * @param event 消息
     */
    @EventListener(classes = MsgEvent.class)
    public void notifyMsgListener(MsgEvent event) {
        log.info("方式二接收到消息：{}", event);
    }
}
