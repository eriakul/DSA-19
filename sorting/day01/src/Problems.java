import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> lowQ = maxPQ();
        PriorityQueue<Integer> highQ = minPQ();
        for (int i = 0; i<inputStream.length; i++){
            if (lowQ.isEmpty() || inputStream[i] < (double) lowQ.peek()){
                lowQ.offer(inputStream[i]);
                if (lowQ.size() - highQ.size() > 1){
                    Integer transfer = lowQ.poll();
                    highQ.offer(transfer);
                }
            }
            else{
                highQ.offer(inputStream[i]);
                if (highQ.size() - lowQ.size() > 1){
                    Integer transfer = highQ.poll();
                    lowQ.offer(transfer);
                }
            }
            runningMedian[i] = getMedian2(lowQ, highQ);
        }
        return runningMedian;
    }
    private static double getMedian2(PriorityQueue<Integer> lowQ, PriorityQueue<Integer> highQ) {
        double median;
        if (lowQ.size()> highQ.size()){
            median = (double) lowQ.peek();
        }
        else if (lowQ.size()< highQ.size()){
            median = (double) highQ.peek();
        }
        else{
            median = ((double) lowQ.peek()+(double) highQ.peek())/2;
        }

        return median;
    }


}
