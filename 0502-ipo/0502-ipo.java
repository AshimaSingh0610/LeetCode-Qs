class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<int[]> vp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vp.add(new int[]{capital[i], profits[i]});
        }
        vp.sort((a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int totalProfit = w, j = 0;
        for (int i = 0; i < k; i++) {
            while (j < n && vp.get(j)[0] <= totalProfit) {
                pq.offer(vp.get(j)[1]);
                j++;
            }

            if (pq.isEmpty()) {
                break;
            }
            totalProfit += pq.poll();
        }

        return totalProfit;
    }
}