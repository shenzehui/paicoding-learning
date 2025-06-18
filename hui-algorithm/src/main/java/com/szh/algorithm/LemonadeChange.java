package com.szh.algorithm;

/**
 * 柠檬水找零
 * <p>
 * 简单题！！
 *
 * @author szh
 **/
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            switch (bill) {
                case 5:
                    five++;
                    break;
                case 10:
                    if (five == 0) {
                        return false; // 找零10元时没有5元
                    }
                    five--;
                    ten++;
                    break;
                case 20:
                    if (five > 0 && ten > 0) { // 贪心，优先找10
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false; // 找零20元时没有5元
                    }
            }
        }
        return true;
    }
}