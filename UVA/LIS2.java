import java.util.*;

// longest increasing subsequence google

public class LIS2 {

    public static int[] lis(int[] a) {
        int n = a.length;
        int[] tail = new int[n];
        int[] prev = new int[n];

        int len = 0;
        for (int i = 0; i < n; i++) {
            int pos = lower_bound(a, tail, len, a[i]);
            if (pos == len) {
                ++len;
            }
            prev[i] = pos > 0 ? tail[pos - 1] : -1;
            tail[pos] = i;
        }

        int[] res = new int[len];
        for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
            res[--len] = a[i];
        }
        return res;
    }

    static int lower_bound(int[] a, int[] tail, int len, int key) {
        int lo = -1;
        int hi = len;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (a[tail[mid]] < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    // random test
    public static void main(String[] args) {
//        Random rnd = new Random(1);
//        for (int step = 0; step < 10_000; step++) {
//            int n = rnd.nextInt(10) + 1;
//            int[] a = new int[n];
//            for (int i = 0; i < n; i++)
//                a[i] = rnd.nextInt(10);
//            int[] lis = lis(a);
//            checkLis(a, lis);
//        }

        int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        System.out.println(Arrays.toString(lis(A)));
    }

    static void checkLis(int[] a, int[] lis) {
        int n = a.length;
        boolean found = false;
        m1:
        for (int mask = 0; mask < 1 << n; mask++) {
            int len = Integer.bitCount(mask);
            if (len < lis.length)
                continue;
            for (int i = 0, prev = Integer.MIN_VALUE; i < n; i++)
                if ((mask & (1 << i)) != 0) {
                    if (prev >= a[i])
                        continue m1;
                    prev = a[i];
                }
            if (len > lis.length)
                throw new RuntimeException();
            boolean ok = true;
            for (int i = 0, j = 0; i < n; i++)
                if ((mask & (1 << i)) != 0)
                    ok &= a[i] == lis[j++];
            found |= ok;
        }
        if (!found)
            throw new RuntimeException();
    }
}