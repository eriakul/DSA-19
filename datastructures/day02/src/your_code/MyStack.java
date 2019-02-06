package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> me;

    public MyStack() {
        ll = new LinkedList<>();
        me = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (me.size() == 0 || e >= me.getFirst()){
            me.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        if (ll.getFirst() == me.getFirst()){
            me.removeFirst();
        }
        Integer pop = ll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        if (ll.size() > 0){
            return me.getFirst();
        }
        return 0;
    }
}
