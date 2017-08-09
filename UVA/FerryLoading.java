
import java.io.*;
import java.util.*;
public class FerryLoading {

    static BufferedReader br;
    static String[] decision;
    static ArrayList<Integer> cars;
    static HashMap<String, Integer> dp;
    //static int[][] dp;
    //static int[] totalRemaining;
    static int size;
    static int ans;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().replace(" ",""));

        while(T-->0){
            br.readLine();
            int ferrrLen = Integer.parseInt(br.readLine().replace(" ", ""));
            ferrrLen *= 100;
            cars = new ArrayList<>();

            String line = br.readLine().replace(" ","");
            if(line!=""){
                int car = Integer.parseInt(line);
                while(car!=0){
                    cars.add(car);
                    car = Integer.parseInt(br.readLine().replace(" ", ""));
                }
            }

            size = cars.size();
//            totalRemaining = new int[size];
//            totalRemaining[0] = ferrrLen*2;
//            for(int i=1; i<size; ++i){
//                totalRemaining[i] = totalRemaining[i-1] - cars.get(i-1);
//            }


            decision = new String[size];
            dp = new HashMap<>();
            ans = best(ferrrLen, ferrrLen, 0);

            System.out.println(ans);


            printPath(0,ferrrLen);
            if(T>0) System.out.println();



        }
    }

    public static int best(int p, int s, int i){

        if(i>=cars.size()) return 0;
        String key = i + "-" + p;
        if(dp.containsKey(key)) {
            return dp.get(key);
        }


        int ci = cars.get(i);
        int res;
        if(ci > p && ci >s ) {
            res = 0;
        }else if(ci<=p && ci>s) {
            res= 1+best(p-ci, s, i+1);
        }else if(ci<=s && ci>p) {
            res= 1+best(p, s-ci, i+1);
        }else{
            int chooseP = best(p-ci, s, i+1);
            int chooseS = best(p, s-ci, i+1);

            if(chooseP>chooseS){
                res= 1+chooseP;
            } else{
                res= 1+chooseS;
            }
        }
        dp.put(key,res);
        return res;
    }

    public static void printPath(int i, int p){

        if(i==ans) return;
        String key = i + "-" + p;

        String chooseP = (i+1) + "-" + (p-cars.get(i));
        String chooseS = (i+1) + "-" + (p);

        if(dp.containsKey(chooseP) && dp.containsKey(chooseS)){
            int cp = dp.get(chooseP);
            int cs = dp.get(chooseS);

            if(cp>=cs){
                System.out.println("port");
                printPath(i+1, p-cars.get(i));
            }else{
                System.out.println("starboard");
                printPath(i+1, p);
            }

        }else if( dp.containsKey(chooseP)){
            System.out.println("port");
            printPath(i+1, p-cars.get(i));
        }else {
            System.out.println("starboard");
            printPath(i+1, p);
        }


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