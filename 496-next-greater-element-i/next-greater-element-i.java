class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = nums2.length-1;i>=0;i--){
            int curr = nums2[i];
            while(!s.isEmpty()&&s.peek()<=curr){
                s.pop();
            }
            if(s.isEmpty()){
                map.put(curr, -1);
            }
            else{
                map.put(curr, s.peek());
            }
            s.push(curr);
        }
        int[] ans = new int[nums1.length];
        for(int i =0;i <nums1.length; i++){
            ans[i] =  map.get(nums1[i]);
        } 
        return ans;
    }
}