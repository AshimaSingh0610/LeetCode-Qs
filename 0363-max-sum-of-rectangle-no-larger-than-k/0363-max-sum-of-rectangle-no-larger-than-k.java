//Kadane's Algorithm Approach
//600ms+  explained in video

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        
        for(int left =0 ;left<matrix[0].length; left++){
            
            int[] rSum = new int[matrix.length];
            
            for(int right = left;right<matrix[0].length;right++){
                for(int row=0; row < matrix.length; row++)
                    rSum[row] += matrix[row][right];
					
					//explanation of these 3 loops starts from 02:00 in Video
                
                TreeSet<Integer> set = new TreeSet<>();
                
                set.add(0);
                int cs = 0;
                
                for(int a: rSum){
                    cs += a;
					
                    //if you don't understand what exactly is going on here
					// then watch the video from 12:27
					
                    Integer target = set.ceiling(cs-k);
                    
                    if(target !=null)
                        result = Math.max(result,cs-target);
                    set.add(cs);
                }
            }
        }
        return result;
    }
}