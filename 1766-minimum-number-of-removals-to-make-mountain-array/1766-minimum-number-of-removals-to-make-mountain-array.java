class Solution {
    public int minimumMountainRemovals(int[] arr) {
        int n = arr.length;
        int inc[] = new int[n];
        inc[0] = 1;
        for(int i=1; i<n; i++){
            int ans = 0;
            for(int j=0; j<i; j++){
                if(arr[i]>arr[j]){
                    ans = Math.max(ans,inc[j]);
                }
            }
            inc[i] = ans+1;
        }

        int dec[] = new int[n];
        dec[n-1] = 1;

        for(int i=n-2; i>=0; i--){
            int ans = 0;
            for(int j=i+1; j<n; j++){
                if(arr[i]>arr[j]){
                    ans = Math.max(ans,dec[j]);
                }
            }
            dec[i] = ans+1;
        }

        for(var a : inc){
            System.out.print(a+" ");
        }

        System.out.println();
        for(var a : dec){
            System.out.print(a+" ");
        }

        int len = 0;
        for(int i=0; i<n; i++){
            if(inc[i]>1 && dec[i]>1) len = Math.max(len,inc[i]+dec[i]-1);
        }
        return n-len;
    }
}