import java.util.*;
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
       int newstart = newInterval[0];
       int newend = newInterval[1];
       for(int i =0; i<intervals.length;i++){
         int currentstart = intervals[i][0];
         int currentend = intervals[i][1];
         if(currentend<newstart){
            result.add(new int[]{currentstart, currentend});
         
         }
         else if(currentstart>newend){
            
            result.add(new int[]{newstart, newend});
         
    for (int j = i; j < intervals.length; j++) {
        result.add(intervals[j]);
    }

    return result.toArray(new int[result.size()][]);
         }
         else {
            newstart = Math.min(newstart, currentstart);
            newend = Math.max(newend, currentend);
         }
       }
       result.add(new int[]{newstart, newend});
return result.toArray(new int[result.size()][]);
    }
}