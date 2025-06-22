package com.szh.algorithm.hash;

/**
 * 383. 赎金信
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ransomNoteCount = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            ransomNoteCount[ransomNote.charAt(i) - 'a']++;
        }

        for (int i = 0; i < magazine.length(); i++) {
            ransomNoteCount[magazine.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (ransomNoteCount[i] > 0) {
                return false;
            }
        }

        return true;

    }
}
