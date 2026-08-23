import java.util.*;
class Solution {
    public int[][] merge(int[][] intervals) {
            List<int[]> result = new ArrayList<>(); 
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
         int start1 = intervals[0][0];
                int end1 = intervals[0][1];
        for(int i =1; i< intervals.length; i++){
               int start2 = intervals[i][0];
            int end2 = intervals[i][1];
            if(start2<=end1){
                int newstart = Math.min(start1, start2);
                int newend = Math.max(end1, end2);
              
                start1 = newstart;
                end1 = newend;
            }
            else{
                result.add(new int[]{start1, end1});
                start1 = start2;
end1 = end2;
            }
        }
result.add(new int[]{start1, end1});
        return result.toArray(new int[result.size()][]);
    }
}