class Solution {
public:
    int n;

    void merge(vector<int>& nums, int low, int mid, int high) {

        int cnt = 0;
        int left = low;
        int right = mid+1;

        vector<int> tmp;

        while(left <= mid && right <= high) {
            if(nums[left] <= nums[right]) {
                tmp.push_back(nums[left]);
                left++;
            }
            else {
                tmp.push_back(nums[right]);
                right++;
            }
        } 

        while(left <= mid) {
            tmp.push_back(nums[left]);
            left++;
        }

        while(right <= high) {
            tmp.push_back(nums[right]);
            right++;
        }
        
        for(int i=low; i<=high; i++) {
            nums[i] = tmp[i-low];
        }

    }

    int countpairs(vector<int>& nums, int low, int mid, int high) {
        
        int right = mid+1;
        int cnt = 0;

        for(int i=low; i<=mid; i++) {
            while(right <= high && (long long)nums[i] > (long long)2*nums[right]) right++;
            cnt += (right-(mid+1));
        }
        
        return cnt;
    }

    int mergeSort(vector<int>& nums, int low, int high) {

        int cnt = 0;

        if(low >= high) return cnt;
        int mid = (low+high)/2;      
        cnt += mergeSort(nums, low, mid);
        cnt += mergeSort(nums, mid+1, high);
        cnt += countpairs(nums, low, mid, high);
        merge(nums, low, mid, high);

        return cnt;
    } 

    int reversePairs(vector<int>& nums) {
        n = nums.size();
        return mergeSort(nums, 0, n-1);
    }
};