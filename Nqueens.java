// Time complexity - O(n!)
// space complexity - O(n^2)
// Did you solve on leetcode - yes
// Any problems faced - No
// we are using backtracking to place one queen per row, trying all columns while checking if each position is safe (not in the same column or diagonal as previous queens). It uses a boolean[][] board to mark queen positions and recursively proceeds row by row, backtracking if no valid placement is found. When all n queens are placed (r == n), the board configuration is converted into strings and added to the result list.
class Solution {
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        helper(board, 0, n);
        return result;
    }

    public void helper(boolean[][] board, int r, int n) {
        //base
        if(r == n) {
            List<String> l = new ArrayList<>();
            for(int i=0;i<n;i++) {
                StringBuilder s = new StringBuilder();
                for(int j=0;j<n;j++) {
                    if(board[i][j]) {
                        s.append("Q");
                    } else {
                        s.append(".");
                    }
                }
                l.add(s.toString());
            }
            result.add(l);
        }

        // logic
        for(int i=0;i<n;i++) {
            if(isSafe(board,r, i,n)) {
                board[r][i] = true;
                helper(board, r+1, n);
                board[r][i] = false;
            }
        }
    }

    public boolean isSafe(boolean[][] board, int r, int c, int n) {
        // check column
        for(int i= r-1;i>=0;i--) {
            if(board[i][c]) return false;
        }

        //left diagonal
        int i = r-1, j= c-1;
        while(i>=0 && j>=0) {
            if(board[i][j]) return false;
            i--;j--;
        }

        //right diagonal
        i = r - 1;
        j = c + 1;
        while (i >= 0 && j < n) {
            if (board[i][j]) return false;
            i--;
            j++;
        }
        return true;

    }
}
