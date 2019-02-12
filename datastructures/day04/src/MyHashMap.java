import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        int hash_code = key.hashCode();

        return buckets[hash_code%buckets.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        ListIterator<Entry> iterator = chooseBucket(key).listIterator();
        while (iterator.hasNext()){
            if (iterator.next().key.equals(key)){
                return true;
            }
        }

        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < buckets.length; i++){
            LinkedList bucket = buckets[i];
            ListIterator<Entry> bucket_iterator = bucket.listIterator(0);
            while (bucket_iterator.hasNext()){
                Entry next = bucket_iterator.next();
                if (next.value == value){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        //TODO
        LinkedList bucket = chooseBucket( key);
        ListIterator<Entry> iterator = chooseBucket(key).listIterator();
        while (iterator.hasNext()){
            Entry next = iterator.next();
            if (next.key.equals(key)){
                return next.value;
            }
        }
        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        if (containsKey(key)){
            V temp_value = remove(key);

            LinkedList bucket = chooseBucket(key);
            Entry entry = new Entry(key, value);
            bucket.add(entry);
            size++;

            return temp_value;
        }

        LinkedList bucket = chooseBucket(key);
        Entry entry = new Entry(key, value);
        bucket.add(entry);

        //RESIZE
        size++;
        if ((double)size/buckets.length >= ALPHA){
            rehash(GROWTH_FACTOR);
        }

        return null;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        if (! containsKey(key)){
            return null;
        }

        LinkedList bucket = chooseBucket(key);
        ListIterator<Entry> iterator = bucket.listIterator();
        while (iterator.hasNext()){
            Entry next = iterator.next();
            if (next.key.equals(key)){
                V temp_value = next.value;
                iterator.remove();
                size--;
                if ((double)size/buckets.length <= BETA){
                    rehash(SHRINK_FACTOR);
                }
                return temp_value;
            }

        }


        //RESIZE
        size--;
        if ((double)size/buckets.length <= BETA){
            rehash(SHRINK_FACTOR);
        }

        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        if ( growthFactor*buckets.length >= MIN_BUCKETS){
            LinkedList<Entry>[] buckets_copy = buckets;
            clear();
            initBuckets((int)(buckets.length*growthFactor));

            for (int i = 0; i < buckets_copy.length; i++){
                LinkedList bucket = buckets_copy[i];
                ListIterator<Entry> bucket_iterator = bucket.listIterator(0);
                while (bucket_iterator.hasNext()){
                    Entry next = bucket_iterator.next();
                    put(next.key, next.value);
                }
            }

        }

        System.out.println(buckets.length);
        // hint: once you have removed all values from the buckets, use put(k, v) to add them back in the correct place
    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
