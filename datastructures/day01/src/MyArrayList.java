import java.lang.Math;
public class MyArrayList {
    private Cow[] elems;
    private int size = 0;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        System.out.println(String.format("Adding %s Size: %s Length %d ",c.name,this.size, elems.length));
        if (this.size >= elems.length){
            doubleSize();
        }
        elems[size] = c;
        this.size++;

    }

    // TODO: Runtime: O(1)
    public int size() {

        return this.size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index > this.size - 1){
            throw new IndexOutOfBoundsException();
        }
        return this.elems[index];
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        Cow tempCow = get(index);
        for (int i = index; i < this.size-1; i++){
            elems[i] = elems[i+1];
        }
        elems[size-1] = null;
        this.size = this.size -1;

        if (this.size < Math.floor(elems.length/4)){
            halfSize();
        }

        return tempCow;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (index > this.size){
            throw new IndexOutOfBoundsException();
        }
//        System.out.print(String.format("Adding %s at %S",c, index));


        if (this.size >= elems.length){

//            System.out.print("Doubling...");

            doubleSize();
        }

        for (int i = this.size-1; i >= index; --i){
            elems[i+1] = elems[i];
        }
        elems[index] = c;
        this.size = this.size + 1;

    }

    private void doubleSize(){
        Cow[] temp_array = new Cow[elems.length*2];
        System.arraycopy(elems, 0, temp_array, 0, elems.length);
        elems = temp_array;

    }
    private void halfSize(){
        Cow[] temp_array = new Cow[elems.length/2];
        System.arraycopy(elems, 0, temp_array, 0, this.size);
        elems = temp_array;
    }
}