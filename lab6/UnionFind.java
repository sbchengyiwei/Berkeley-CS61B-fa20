import java.util.ArrayList;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid v1. */
    private void validate(int v1) {
        // TODO
        if (v1 < 0 | v1 > parent.length - 1) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return -1 * parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if parents v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       v1 with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (isConnected(v1, v2)) {
            return;
        }
        if (v1 != v2) {
            v1 = find(v1);
            v2 = find(v2);
            if (sizeOf(v1) > sizeOf(v2)) {
                parent[v1] -= sizeOf(v2);
                parent[v2] = v1;
            } else {
                parent[v2] -= sizeOf(v1);
                parent[v1] = v2;
            }
        }
    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        // TODO
        validate(v1);
        int temp = v1;
        int temp2 = 0;
        while (parent(temp) > -1) {
            temp = parent(temp);
        }
        while (v1 != temp) {
            temp2 = parent[v1];
            parent[v1] = temp;
            v1 = temp2;
        }
        return temp;
    }

}