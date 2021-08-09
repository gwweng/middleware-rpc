package com.middleware.rpc.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author gengweiweng
 * @time 2021/8/6
 * @desc
 */
public class NetUtils {
    public static boolean isPortUsing(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            socket.close();
            return true;
        }catch (IOException e){

        }
        return false;
    }

    public static String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) {
        isPortUsing(8080);
    }

}
