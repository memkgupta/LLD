import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
       s.findAllPeople(
               12,
               new int[][]{
                       {10,8,6},{9,5,11},{0,5,18},{4,5,13},{1,6,17},{0,11,10},{0,11,7},{5,8,3},{7,6,16},{3,6,10},{3,11,1},{8,3,2},{5,0,7},{3,8,20},{1,0,20},{8,3,4},{1,9,4},{0,7,11},{8,10,18}
               },
               9
       );

    }
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Set<Integer> res = new HashSet<>();
        res.add(0);
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }
        for(int[] meeting : meetings)
        {
            int x = meeting[0];
            int y = meeting[1];
            int time = meeting[2];
            adj.get(x).add(new int[]{y,time});
            adj.get(y).add(new int[]{x,time});
        }

        Set<Integer> vis = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b)->{
                    return a[1]-b[0];
                }
        );
        vis.add(0);
        pq.offer(new int[]{firstPerson,1});
        pq.offer(new int[]{0,0});
        vis.add(firstPerson);
        while(!pq.isEmpty())
        {
            int[] node = pq.poll();
            int person = node[0];
            res.add(person);
            int time = node[1];
            for(int[] child : adj.get(person))
            {
                if(!vis.contains(child[0]) && child[1]>=time)
                {

                    pq.offer(new int[]{child[0],child[1]});
                }
            }
        }
        return new ArrayList<>(res);
    }
    public int numberOfWays(String corridor) {
        return rec(corridor , 0 , 0);
    }
    public int rec(String corridor , int i , int seats)
    {
        if(i == corridor.length())
        {
            return seats == 2 ? 1: 0;

        }
        int divide = 0;
        int skip = 0;
        if(seats == 2)
        {
            divide = rec(corridor , i+1 , 0);
            if(corridor.charAt(i) == 'P')
            {
                skip+=rec(corridor,i+1, seats);
            }

        }
        else if(seats < 2)
        {
            skip += rec(corridor,i+1,seats + (corridor.charAt(i) == 'S' ? 1 : 0));
        }

        return divide + skip;
    }
}