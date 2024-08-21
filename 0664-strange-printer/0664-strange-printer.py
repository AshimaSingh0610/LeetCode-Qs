class Solution(object):
    def strangePrinter(self, s):
        memo = {}

        def dp(i, j):
            if i > j:
                return 0
            if (i, j) in memo:
                return memo[(i, j)]

            result = dp(i + 1, j) + 1
            for k in range(i + 1, j + 1):
                if s[k] == s[i]:
                    result = min(result, dp(i, k - 1) + dp(k + 1, j))

            memo[(i, j)] = result
            return result

        return dp(0, len(s) - 1)