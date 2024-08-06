class Solution {
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {
        dp = new int[matrix.length+1][matrix[0].length+1];
        int res = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                res = Math.max(dfs(matrix, i, j, Integer.MIN_VALUE), res);
            }
        }
        
        return res;
    }
    
    public int dfs(int[][] matrix, int i, int j, int prevVal){
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prevVal){
            return 0;
        }
        
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int temp = 0;
        int curr = matrix[i][j];
        temp = Math.max(temp, dfs(matrix, i+1, j, curr));
        temp = Math.max(temp, dfs(matrix, i-1, j, curr));
        temp = Math.max(temp, dfs(matrix, i, j+1, curr));
        temp = Math.max(temp, dfs(matrix, i, j-1, curr));
        
        return dp[i][j] = ++temp;
    }
}