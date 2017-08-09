import java.io.*;
import java.util.*;


// this code is given with the assumption(fact) that there are only 22 such primes
public class AnagrammaticPrimes {

    static BufferedReader br;
    static boolean[] primes = new boolean[1000];
    static TreeSet<Integer> primeNumbers = new TreeSet<>();
    static ArrayList<Integer> AnaPrimes = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        //int T = Integer.parseInt(br.readLine());

        sieve();
        for(int i=0; i<1000; i++){
            if(primes[i]){
                primeNumbers.add(i);
            }
        }

        for(int i: primeNumbers){
            if(isAnaPrime(i)){
                AnaPrimes.add(i);
            }
        }

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;

            if(n>1000){
                System.out.println(0);
            }else{

                int prev =0;
                int ans = 0;

                for(int i=0; i<AnaPrimes.size(); i++){
                    int cur = AnaPrimes.get(i);

                    if(cur>n && cur< Math.pow(10, (n+"").length()) ){
                        ans = cur;
                        break;
                    }
                    if(cur>n) break;
                }
                System.out.println(ans);

            }


        }



    }

    static void sieve(){

        for(int i=2; i<1000; i++) primes[i] = true;
        primes[0] = false;
        primes[1] = false;

        for(int i=2; i<1000; i++){

            if(primes[i]){
                for(int j=i+i; j<1000; j+=i){
                    primes[j] = false;

                }
            }
        }
    }

    static boolean isAnaPrime(int n){

        boolean is = true;

        String s = n+"";

        String[] sa = s.split("");
        Arrays.sort(sa);

        do {
            String cur = String.join("",sa);

            int num = Integer.parseInt(cur);
            if(!primeNumbers.contains(num)){
                is = false;
                break;
            }

        } while (nextPermutation(sa));

        return is;
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

    public static int[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }

}
