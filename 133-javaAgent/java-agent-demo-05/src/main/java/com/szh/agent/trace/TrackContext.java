package com.szh.agent.trace;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class TrackContext {

    public static final ThreadLocal<String> trackLocal = new ThreadLocal<>();

    public static void clear() {
        trackLocal.remove();
    }

    public static String getLinkId() {
        return trackLocal.get();
    }

    public static void setLinkId(String linkId) {
        trackLocal.set(linkId);
    }
}
