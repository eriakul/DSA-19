package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */

public class MyPriorityQueue {
    LinkedList ll;
    public MyPriorityQueue(){
        ll = new LinkedList();

    }
    public void enqueue(int item) {
        if (ll.isEmpty()) {
            ll.push(item);
        } else {
            int i = 0;
            while (i < ll.size()) {
                if (item > (int) ll.get(i)){
                    i++;
                }
                else{
                    break;
                }
            }
            ll.add(i, item);
            System.out.println(ll);
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return (int) ll.removeLast();
    }

}