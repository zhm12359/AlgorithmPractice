/**
 * Created by hanmingzeng on 5/7/17.
 */
import java.util.*;
public class ArrayAdapter {

    public static void main(String[] args){

        int[] array = {3213, 34234, 23, 534};

        Collections.sort(intArrayAsList(array));
        System.out.println(Arrays.toString(array));

    }
    static List<Integer> intArrayAsList(final int[] a) {
        return new AbstractList<Integer>() {
            public Integer get(int i) {
                return a[i];
            }

            @Override
            public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;
                return oldVal;
            }

            public int size() {
                return a.length;
            }
        };
    }
}
