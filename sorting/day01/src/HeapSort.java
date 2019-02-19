import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if (leftChild(i) < this.size && this.heap[leftChild(i)] > this.heap[i]){
            if (rightChild(i)>= this.size || this.heap[leftChild(i)] > this.heap[rightChild(i)]){
               swap(i, leftChild(i));
               sink(leftChild(i));
            }
            else{
                swap(i, rightChild(i));
                sink(rightChild(i));
            }
        }
        if (rightChild(i) < this.size && this.heap[rightChild(i)] > this.heap[i]){
            if (leftChild(i)>= this.size || this.heap[leftChild(i)] < this.heap[rightChild(i)]){
                swap(i, rightChild(i));
                sink(rightChild(i));
            }
            else{
                swap(i, leftChild(i));
                sink(leftChild(i));
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    public void heapify2() {
        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        this.heap = array;
        this.size = this.heap.length;

        for (int i=this.size-1; i>0; i--) {
            this.size = i+1;
            heapify2();
            swap(0, i);

        }
        return this.heap;
    }

    public void swap(int i, int j) {
        int temp = this.heap[j];
        this.heap[j] = this.heap[i];
        this.heap[i] = temp;

    }

}
