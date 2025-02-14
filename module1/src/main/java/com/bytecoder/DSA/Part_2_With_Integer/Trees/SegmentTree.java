package com.bytecoder.DSA.Part_2_With_Integer.Trees;

import java.util.Arrays;

public class SegmentTree {
    int[] tree;
    int[] arr;
    int n;

    public SegmentTree(int[] input) {
        this.n = input.length;
        this.arr = input;
        this.tree = new int[4 * n];
        build(0, 0, n - 1);
    }

    private int build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        int leftSum = build(2 * node + 1, start, mid);
        int rightSum = build(2 * node + 2, mid + 1, end);
        return tree[node] = leftSum + rightSum;
    }

    public int query(int L, int R) {
        return queryUtil(0, 0, n - 1, L, R);
    }

    private int queryUtil(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return 0;
        }
        if (L <= start && end <= R) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftSum = queryUtil(2 * node + 1, start, mid, L, R);
        int rightSum = queryUtil(2 * node + 2, mid + 1, end, L, R);
        return leftSum + rightSum;
    }

    public int update(int index, int value) {
        return updateUtil(0, 0, n - 1, index, value);
    }

    private int updateUtil(int node, int start, int end, int index, int value) {
        if (start == end) {
            arr[index] = value;
            return tree[node] = value;
        }
        int mid = (start + end) / 2;
        if (index <= mid) {
            tree[2 * node + 1] = updateUtil(2 * node + 1, start, mid, index, value);
        } else {
            tree[2 * node + 2] = updateUtil(2 * node + 2, mid + 1, end, index, value);
        }
        return tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public void printTree() {
        System.out.println(Arrays.toString(tree));
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segTree = new SegmentTree(arr);

        System.out.println("Sum of range [1,4]: " + segTree.query(1, 4));
        segTree.update(2, 10);
        System.out.println("Updated sum of range [1,4]: " + segTree.query(1, 4));
    }
}

