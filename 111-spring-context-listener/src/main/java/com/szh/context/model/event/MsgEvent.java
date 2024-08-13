package com.szh.context.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MsgEvent<T> extends ApplicationEvent {

    private T msg;

    public MsgEvent(Object source, T msg) {
        super(source);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MsgEvent{" + "msg='" + msg + '\'' + '}';
    }
}
