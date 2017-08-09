import java.io.*;
import java.util.*;
public class MoneyMatters {

    static BufferedReader br;
    public static int[] Rep;
    public static int[] rank;
    public static int[] money;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            int[] tokens = lineToIntArray(br.readLine());
            int n= tokens[0];
            int m= tokens[1];

            init(n);
            for(int i=0; i<n; i++){
                money[i] = Integer.parseInt(br.readLine());
            }
            for(int i=0; i<m; ++i){
                int[] tks = lineToIntArray(br.readLine());
                setFriend(tks[0],tks[1]);
            }

            //System.out.println(Arrays.toString(money));
            boolean flag=true;
            for(int i=0; i<n; ++i){
                int rep = find(i);
                if(money[rep]!=0){
                    flag=false;
                    break;
                }
            }

            System.out.println(flag? "POSSIBLE":"IMPOSSIBLE");

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

    public static void init(int total){

        Rep = new int[total+1];
        rank = new int[total+1];
        money = new int[total];
        for(int i=0; i<Rep.length; ++i){
            Rep[i] = i; //init: everyone represents themselves
            rank[i] = 1; //every set only has one friend(the people itself)
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

            money[xRep] += money[yRep];
            money[yRep] = money[xRep];
        }


    }

    public static boolean areFriends(int x, int y){
        int xRep = find(x);
        int yRep = find(y);
        boolean res =  (xRep==yRep) ? true:false;

        return res;
    }
}