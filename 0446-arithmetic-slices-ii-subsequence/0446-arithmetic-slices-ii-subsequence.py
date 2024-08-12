class Solution(object):
    def numberOfArithmeticSlices(self, nums):
        n = len(nums)
        totalCount = 0
         # initialize a list of dictionaries to store counts of subsequences
        dp = [{} for _ in range(n)]

        for i in range(n):
            for j in range(i):
                # calculate the difference between current and previous elements
                diff = nums[i] - nums[j]

                # get the count of subsequences with the same difference ending at nums[j]
                prevCount = dp[j].get(diff, 0)
                # update count of subsequences ending at nums[i]
                dp[i][diff] = dp[i].get(diff, 0) + prevCount + 1
                # accumulate the previous count to the total count
                totalCount += prevCount

        return totalCount