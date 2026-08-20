class Solution {
    //pranshi
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // arr1 starts at index 0
        int idx1 = 0;
        result[idx1++] = nums[0];
        
        // arr2 starts after arr1
        int idx2 = n - 1;
        result[idx2--] = nums[1];
        
        int last1 = nums[0];
        int last2 = nums[1];
        
        for (int i = 2; i < n; i++) {
            if (last1 > last2) {
                result[idx1++] = nums[i];
                last1 = nums[i];
            } else {
                result[idx2--] = nums[i];
                last2 = nums[i];
            }
        }
        
        // Now result has arr1 in front and arr2 reversed at the back
        // Reverse the arr2 portion to restore order
        int left = idx1, right = n - 1;
        while (left < right) {
            int temp = result[left];
            result[left] = result[right];
            result[right] = temp;
            left++;
            right--;
        }
        
        return result;
    }
}
