class Solution {
public:
    int findIntegers(int n) {
        int fib[31];
        fib[0] = 1;
        fib[1] = 2;

        for(int i=2; i<30; i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        int count = 30, ans = 0, prevOne = 0;
        while(count >= 0){
            if(n&(1<<count)){
                ans += fib[count];
                if(prevOne) return ans;
                prevOne = 1;
            }
            else{
                prevOne = 0;
            }
            count--;
        }
        return ans+1;
    }
};