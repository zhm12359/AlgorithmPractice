import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Brandwidth {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        while(!line.equals("#")){

            String[] pairs = line.split(";");
            HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

            String unique = "";

            for(int i=0; i<pairs.length; ++i){

                String[] kv = pairs[i].split(":");
                String key = kv[0];
                if(!unique.contains(key)) unique+=key;
                ArrayList<String> value = new ArrayList<String>();
                int len = kv[1].length();
                for(int c=0; c<len; ++c){
                    String cur = ""+kv[1].charAt(c);
                    value.add(""+cur);
                    if(!unique.contains(cur)) unique+=cur;
                }
                map.put(key, value);
            }

            String[] ordering = unique.split("");
            Arrays.sort(ordering);
//            System.out.println(Arrays.toString(ordering));
              //System.out.println(map);

            LinkedHashMap<String,Integer> processed = new LinkedHashMap<String, Integer>();


            do {
                String cur = String.join("",ordering);
                if(processed.containsKey(cur)) continue;


                int localMax = 0;
                for(String key: map.keySet()){

                    ArrayList<String> connectedTo = map.get(key);
                    int keyAt = cur.indexOf(key);

                    for(String v: connectedTo){

                        int valueAt = cur.indexOf(v);
                        int temp = Math.abs(keyAt-valueAt);

                        localMax = localMax > temp? localMax:temp;

                    }

                }

                processed.put(cur, localMax);

            } while (nextPermutation(ordering));


            String ans="";
            int min=99;

            for(String s: processed.keySet()){

                int v = processed.get(s);

                if(min>v){
                    ans = s;
                    min =v;

                }
            }

            ans = String.join(" ", ans.split(""));

            System.out.println(ans+" "+"-> "+min);



            line = br.readLine();

        }



        //example usage of next perm
//        String[] array = { "b", "a", "c", ""};
//        Arrays.sort(array);
//        do {  // Must start at lowest permutation
//
//            String s = String.join("", array);
//            System.out.println(s);
//        } while (nextPermutation(array));

    }



    public static boolean nextPermutation(String[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1].compareTo(array[i])>=0 )
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j].compareTo(array[i - 1])<=0 )
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        String temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }
}


/*
boolean nextPermutation(int[] array) {
    // Find longest non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
        i--;
    // Now i is the head index of the suffix

    // Are we at the last permutation already?
    if (i <= 0)
        return false;

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
        j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i

    // Swap the pivot with j
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse the suffix
    j = array.length - 1;
    while (i < j) {
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
        j--;
    }

    // Successfully computed the next permutation
    return true;
}
 */
