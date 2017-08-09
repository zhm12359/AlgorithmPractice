import java.io.*;
import java.util.*;
public class Nature {

    static BufferedReader br;
    public static int[] Rep;
    public static int[] rank;
    public static int[] count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){

            int[] tokens = lineToIntArray(br.readLine());
            //System.out.println(Arrays.toString(tokens));
            int C = tokens[0];
            int R = tokens[1];
            if(C==0&&R==0) break;

            init(C);

            HashMap<String, Integer> map = new HashMap<>();
            for(int i=0; i<C; ++i){
                map.put(br.readLine(),i);
            }

            //System.out.println("hhiiiii"+map);

            for(int i=0; i<R; ++i){
                String[] tks = br.readLine().split("\\s+");
                //System.out.printf("%s %s\n", tks[0], tks[1]);
                setFriend(map.get(tks[0]), map.get(tks[1]));
            }

            int max=-1;
            for(int i=0; i<C; ++i){
                max=Math.max(max, count[i]);
            }

            pw.println(max);

            br.readLine();

        }
        pw.flush();

    }

    public static void init(int total){

        Rep = new int[total+1];
        rank = new int[total+1];
        count = new int[total+1];
        for(int i=0; i<Rep.length; ++i){
            Rep[i] = i; //init: everyone represents themselves
            rank[i] = 1; //every set only has one friend(the people itself)
            count[i] = 1;
        }
    }


    public static int find(int x){
        if(Rep[x]!=x){
            Rep[x] = find(Rep[x]);
        }
        return Rep[x];
    }




    public static void setFriend(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        if(xRep!=yRep){

            if(rank[xRep] >= rank[yRep]){
                Rep[yRep] = xRep;
                rank[xRep]++;
            }else{
                Rep[xRep] = yRep;
                rank[yRep]++;
            }
            count[xRep] += count[yRep];
            count[yRep] = count[xRep];

        }
    }

    public static boolean areFriends(int x, int y){
        int xRep = find(x);
        int yRep = find(y);
        boolean res =  (xRep==yRep) ? true:false;

        return res;
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