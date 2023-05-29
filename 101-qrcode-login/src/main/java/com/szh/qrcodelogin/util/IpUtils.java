package com.szh.qrcodelogin.util;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by szh on 2023-05-02
 *
 * @author szh
 */

public class IpUtils {
    public static final String DEFAULT_IP = "127.0.0.1";

    /**
     * 直接根据第一个网卡地址作为其内网ipv4地址，避免返回 127.0.0.1
     *
     * @return
     */
    public static String getLocalIpByNetcard() {
        try {
            // 获取所有网络接口的枚举并遍历
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface item = e.nextElement();
                // 获取与该接口相关的接口地址并遍历
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    // 如果接口是回环接口或者未启用，继续
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    // 如果接口是 IPV4 地址，则转换为 IPV4 地址并返回该地址的主机地址
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
                        return inet4Address.getHostAddress();
                    }
                }
            }
            // 获取本机地址作为默认地址
            return InetAddress.getLocalHost().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            return DEFAULT_IP;
        }
    }

    private static volatile String ip;

    public static String getLocalIP() {
        if (ip == null) {
            synchronized (IpUtils.class) {
                if (ip == null) {
                    ip = getLocalIpByNetcard();
                }
            }
        }
        return ip;
    }
}
