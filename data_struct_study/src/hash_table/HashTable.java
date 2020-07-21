package hash_table;

import java.util.TreeMap;

public class HashTable<K, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    public static final int UPPER_TOL = 10;
    public static final int LOWER_TOL = 2;
    public int capacity_index;

    private TreeMap<K, V>[] hashTable;
    private int size;
    private int M;

    public HashTable() {
        this.M = capacity[capacity_index];
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    private int hash(K k) {
        return (k.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(K k) {
        return hashTable[hash(k)].containsKey(k);
    }

    public void add(K k, V v) {
        TreeMap<K, V> treeMap = hashTable[hash(k)];
        if (treeMap.containsKey(k)) {
            treeMap.put(k, v);
        } else {
            treeMap.put(k, v);
            size++;

            if (size > UPPER_TOL * M && capacity_index < capacity.length - 1) {
                capacity_index++;
                resize(capacity_index);
            }
        }
    }

    public V remove(K k) {
        TreeMap<K, V> treeMap = hashTable[hash(k)];
        V ret = null;
        if (treeMap.containsKey(k)) {
            ret = treeMap.remove(k);
            size--;

            if (size < LOWER_TOL * M && capacity_index - 1 >= 0) {
                capacity_index--;
                resize(capacity_index);
            }
        }
        return ret;
    }

    public V set(K k, V v) {
        TreeMap<K, V> treeMap = hashTable[hash(k)];

        if (!treeMap.containsKey(k)) {
            throw new IllegalArgumentException(k + "doesn't exist~");
        }

        return treeMap.put(k, v);
    }

    public V get(K k) {
        return hashTable[hash(k)].get(k);
    }

    private void resize(int newCapacity) {
        TreeMap[] newHashTable = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newHashTable[i] = new TreeMap();
        }

        int oldM = M;
        M = newCapacity;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> treeMap = hashTable[i];
            for (K k : treeMap.keySet()) {
                newHashTable[hash(k)].put(k, treeMap.get(k));
            }
        }

        hashTable = newHashTable;
    }

}
