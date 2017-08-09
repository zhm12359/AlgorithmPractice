import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Friends {

    public static int[] Rep;
    public static int[] rank;
    public static int[] count;
    public static int max;
    static BufferedReader br;


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());

        for(int c=0; c<Cases; ++c){

            String[] NM = br.readLine().split(" ");

            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);

            init(N);
            //System.out.println(max);

            for(int m=0; m<M; ++m) {

                String[] token = br.readLine().split(" ");

                int p1 = Integer.parseInt(token[0]);
                int p2 = Integer.parseInt(token[1]);

                setFriend(p1, p2);
            }

            //System.out.println(Arrays.toString(Rep));
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i=1; i<N+1; ++i){
                int cur = find(Rep[i]);
                if(map.containsKey(cur)){
                    map.put(cur, map.get(cur)+1);
                }else{
                    map.put(cur,1);
                }
                max = max > map.get(cur) ? max : map.get(cur);

            }

            System.out.println(max);

        }


    }

    public static void init(int total){

        Rep = new int[total+1];
        rank = new int[total+1];
        count = new int[total+1];

        max=0;

        for(int i=0; i<Rep.length; ++i){
            Rep[i] = i; //init: everyone represents themselves
            rank[i] = 1; //every set only has one friend(the people itself)
            count[1] = 1;
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
}




