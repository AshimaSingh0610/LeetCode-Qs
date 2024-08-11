class Solution:
    def __init__(self):
        self.X = [-1, 0, 1, 0]
        self.Y = [0, 1, 0, -1]

    def isBoundary(self, i, j, n, m):
        return 0 <= i < n and 0 <= j < m

    def dfs(self, i, j, grid, vis):
        vis[i][j] = True
        for k in range(4):
            ii = i + self.X[k]
            jj = j + self.Y[k]
            if self.isBoundary(ii, jj, len(grid), len(grid[0])) and grid[ii][jj] == 1 and not vis[ii][jj]:
                self.dfs(ii, jj, grid, vis)

    def getIslands(self, grid):
        n, m = len(grid), len(grid[0])
        cnt = 0
        vis = [[False] * m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                if grid[i][j] == 1 and not vis[i][j]:
                    cnt += 1
                    self.dfs(i, j, grid, vis)

        return cnt

    def minDays(self, grid):
        if self.getIslands(grid) != 1:
            return 0

        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 1:
                    grid[i][j] = 0
                    if self.getIslands(grid) != 1:
                        return 1
                    grid[i][j] = 1

        return 2