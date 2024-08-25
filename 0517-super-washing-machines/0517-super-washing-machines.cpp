class Solution {
public:
    int findMinMoves(vector<int>& ms) {
        int n = ms.size();
        int sum = 0;
        for (int &i : ms) sum += i;
        if (sum % n) return -1;
        int t = sum / n;
        int ans = 0, toRight = 0;
        for (int &i : ms) {
            toRight = toRight + i - t;
            ans = max(ans, max(abs(toRight), i - t));
        }
        return ans;
    }
};