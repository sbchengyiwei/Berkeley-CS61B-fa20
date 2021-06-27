import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int size;
    private int threshold;
    private BucketEntity<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private class BucketEntity<K, V> {
        private K key;
        private V value;
        private BucketEntity<K, V> next;
        private int hashCode;

        public BucketEntity(int hashCode, K key, V value, BucketEntity<K, V> next) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public BucketEntity<K, V> getNext() {
            return next;
        }

        public void setNext(BucketEntity<K, V> next) {
            this.next = next;
        }
    }

    public MyHashMap() {
        buckets = new BucketEntity[INITIAL_CAPACITY];
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;
    }

    public MyHashMap(int initialSize) {
        buckets = new BucketEntity[initialSize];
        threshold = (int) (initialSize * LOAD_FACTOR);
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        buckets = new BucketEntity[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }

    @Override
    public void clear() {
        buckets = new BucketEntity[buckets.length];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    private int hash(K key, int length) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % length;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = hash(key, buckets.length);
        BucketEntity<K, V> entity = get(hashCode, key);
        return entity == null ? null : entity.getValue();
    }

    private BucketEntity<K, V> get(int hashCode, K key) {
        BucketEntity<K, V> entity = buckets[hashCode];
        while (entity != null) {
            if (entity.getHashCode() == hashCode && entity.getKey().equals(key)) {
                return entity;
            }
            entity = entity.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hashCode = hash(key, buckets.length);
        BucketEntity<K, V> entity = buckets[hashCode];
        while (entity != null) {
            if (entity.getHashCode() == hashCode && entity.getKey().equals(key)) {
                entity.setValue(value);
                return;
            }
            entity = entity.getNext();
        }
        put(hashCode, key, value);
    }
    private void put(int hashCode, K key, V value) {
        BucketEntity<K, V> entity = new BucketEntity<>(hashCode, key, value, buckets[hashCode]);
        buckets[hashCode] = entity;
        size += 1;
        if (size > threshold) {
            resize(buckets.length * 2);
        }
    }
    private void resize(int capacity) {
        BucketEntity<K, V>[] newBuckets = new BucketEntity[capacity];
        for (int i = 0; i < buckets.length; i += 1) {
            BucketEntity<K, V> entity = buckets[i];
            while (entity != null) {
                BucketEntity<K, V> oldNext = entity.getNext();
                int newHashCode = hash(entity.getKey(), newBuckets.length);
                entity.setNext(newBuckets[newHashCode]);
                entity.setHashCode(newHashCode);
                newBuckets[newHashCode] = entity;
                entity = oldNext;
            }
        }
        buckets = newBuckets;
        threshold = (int) (buckets.length * LOAD_FACTOR);
    }

    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for (int i = 0; i < buckets.length; i += 1) {
            BucketEntity<K, V> entity = buckets[i];
            while (entity != null) {
                allKeys.add(entity.getKey());
                entity = entity.getNext();
            }
        }
        return allKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

