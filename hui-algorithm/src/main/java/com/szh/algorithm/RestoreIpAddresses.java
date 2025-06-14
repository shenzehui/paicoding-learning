package com.szh.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原 IP 地址
 *
 * @author szh
 */
public class RestoreIpAddresses {

    String path = "";

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backTracking(s, 0, 0);
        return res;
    }

    public void backTracking(String s, int startIndex, int count) {
        if (count == 4) {
            if (startIndex == s.length()) {
                res.add(path.substring(0, path.length() - 1));
            }
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            if (str.length() > 1 && str.charAt(0) == '0') {
                break;
            }
            int num = Integer.parseInt(str);
            if (num > 255) {
                break;
            }
            path += str + ".";
            backTracking(s, i + 1, count + 1);
            path = path.substring(0, path.length() - str.length() - 1);
//            count--;
        }
    }

    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        List<String> res = restoreIpAddresses.restoreIpAddresses("25525511135");
        System.out.println(res);
    }
}
