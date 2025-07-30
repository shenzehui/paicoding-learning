package com.szh.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N皇后
 */
public class NQueens {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTracking(chessboard, n, 0);
        return result;
    }

    public void backTracking(char[][] chessboard, int n, int row) {
        if (row == n) {
            result.add(generateChessboard(chessboard));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!isValid(row, i, n, chessboard)) {
                continue;
            }
            chessboard[row][i] = 'Q';
            backTracking(chessboard, n, row + 1);
            chessboard[row][i] = '.';
        }
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> result = nQueens.solveNQueens(4);
        System.out.println(result);
    }


    public List<String> generateChessboard(char[][] chessboard) {
        List<String> chessboardList = new ArrayList<>();
        for (char[] row : chessboard) {
            chessboardList.add(new String(row));
        }
        return chessboardList;
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard) {

        // 检查列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

}
