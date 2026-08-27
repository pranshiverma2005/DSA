import java.util.*;

class MedianFinder {

    // Smaller half
    PriorityQueue<Integer> maxHeap;

    // Larger half
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        // Step 1: Add to max heap
        maxHeap.offer(num);

        // Step 2: Make sure every element
        // in maxHeap <= every element in minHeap
        if (!maxHeap.isEmpty() &&
            !minHeap.isEmpty() &&
            maxHeap.peek() > minHeap.peek()) {

            minHeap.offer(maxHeap.poll());
        }

        // Step 3: Balance sizes
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }

        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {

        // Same size
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        // maxHeap has more
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        // minHeap has more
        return minHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */