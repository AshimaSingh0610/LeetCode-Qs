class Solution(object):
    def minPatches(self, nums, n):
        """
        :type nums: List[int]
        :type n: int
        :rtype: int
        """
        
        l = len(nums)
        d = defaultdict(lambda : float("inf"))
        for i in range(l):
            d[i] = nums[i]

        ans = 0 
        baseSum = 0

        i = 0
        while True:
            if baseSum >= n:
                return ans 

            elif d[i] > baseSum + 1:
                ans += 1
                baseSum += baseSum + 1

            else:
                baseSum += d[i]
                i+=1