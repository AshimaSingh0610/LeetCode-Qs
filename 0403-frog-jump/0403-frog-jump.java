class Solution {
    public boolean canCross(int[] stones) {
        
        Map<Integer, Boolean>[] dp=new Map[stones.length];
        for(int i=0;i<dp.length;i++){
            Map<Integer, Boolean> map = new HashMap<>();
            dp[i]=map;
        }
        boolean ans= jumplast(stones,0,0,dp);
        return ans;
    }

    public static boolean jumplast(int[] stones,int idx,int jumps,Map<Integer, Boolean>[] dp){
        if(idx==stones.length-1){
            return true;
        }else if(idx>stones.length-1){
            return false;
        }

        if(dp[idx].containsKey(jumps)){
            return dp[idx].get(jumps);
        }

        dp[idx].put(jumps,false);

        for(int i=idx+1;i<stones.length;i++){
            if(stones[i]-stones[idx] >=jumps-1 && stones[i]-stones[idx]<=jumps+1){
                boolean jump1=jumplast(stones,i,stones[i]-stones[idx],dp);
                if(jump1){
                    dp[idx].put(jumps,true);
                    return true;
                }
            }else if(stones[i]-stones[idx]>jumps+1){
                break;
            }
        }

        return false;
    }
}