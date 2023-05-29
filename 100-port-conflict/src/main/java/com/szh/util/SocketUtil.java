package com.szh.util;

import javax.net.ServerSocketFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Random;

/**
 * Created by szh on 2023-05-02
 */

public class SocketUtil {

    /**
     * 判断端口是否可用
     */
    public static boolean isPortAvailable(int port) {
        try {
            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port, 1, InetAddress.getByName("localhost"));
            serverSocket.close();
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    private static Random random = new Random();


    /**
     * 找到一个可用的端口号
     */
    public static int findAvailableTcpPort(int minPort, int maxPort, int defaultPort) {
        if (isPortAvailable(defaultPort)) {
            return defaultPort;
        }
        if (maxPort <= minPort) {
            throw new IllegalArgumentException("maxPort should bigger than minPort");
        }
        int portRange = maxPort - minPort;
        int searchCounter = 0;
        while (searchCounter <= portRange) {
            int candidatePort = findRandomPort(minPort, maxPort);
            ++searchCounter;
            if (isPortAvailable(candidatePort)) {
                return candidatePort;
            }
        }
        throw new IllegalArgumentException(String.format("Could not find an available %s port in the range [%d,%d] after %d attempts", SocketUtil.class.getName(), minPort, maxPort, searchCounter));

    }

    private static int findRandomPort(int minPort, int maxPort) {
        int portRange = maxPort - minPort;
        return minPort + random.nextInt(portRange + 1);
    }

}
