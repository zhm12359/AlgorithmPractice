import java.io.*;
import java.util.*;
public class VirtualFriends {

    static BufferedReader br;
    public static int[] Rep;
    public static int[] rank;
    public static int[] count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int N = Integer.parseInt(br.readLine());

            HashMap<String, Integer> ids = new HashMap<>();

            init(100000);
            int newId = 0;
            for(int i=0; i<N; ++i){

                String[] ppl = br.readLine().split("\\s+");
                String p1 = ppl[0];
                String p2 = ppl[1];

                if(!ids.containsKey(p1)){
                    ids.put(p1, newId);
                    newId++;
                }
                if(!ids.containsKey(p2)){
                    ids.put(p2, newId);
                    newId++;
                }

                int id1 = ids.get(p1);
                int id2 = ids.get(p2);

                setFriend(id1, id2);
                int rep1 = find(id1);

                System.out.println(count[rep1]);

            }

        }


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