  public class Node {
      int val;
      int row;
       int col;
      Node() {}
      Node(int val) { this.val = val; }
      Node(int val, int row,int col) { this.val = val; this.row = row; this.col = col; }
  }
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int a=0;
        int b=0;
        int max=Integer.MIN_VALUE;
        int min=0;
        int k=nums.size();
        int len=Integer.MAX_VALUE;
  PriorityQueue<Node> q= new PriorityQueue<Node>(k,new Comparator<Node>(){
            @Override
            public int compare(Node o1,Node o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else 
                    return 1;
            }
        });
         for(int i=0;i<k;i++){
            int e=nums.get(i).get(0);
            max=Math.max(max,e);
            q.add(new Node(e,i,0));
        }
        while(q.size()>0){
        Node curr=q.peek();
        if(len==max-curr.val && curr.val<a){
            a=curr.val;
            b=max;
        }
        else if(len>max-curr.val){
            a=curr.val;
            b=max;
            len=max-curr.val;
        }
        q.poll();
        if(curr.col<nums.get(curr.row).size()-1){
        Node neww=new Node(nums.get(curr.row).get(curr.col+1),curr.row,curr.col+1);
        q.add(neww);
        max=Math.max(max,neww.val);
        }
        else break;
        }
        return new int[]{a,b};
    }
}