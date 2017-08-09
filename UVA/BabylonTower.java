import java.io.*;
import java.util.*;
public class BabylonTower {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int test = 0;

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;
            ArrayList<Blk> blks = new ArrayList<>();

            for(int i=0; i<n; i++){
                int[] tks = lineToIntArray(br.readLine());
                int x = tks[0], y =tks[1], z = tks[2];

                blks.add(new Blk(x,y,z));
                blks.add(new Blk(x,z,y));
                blks.add(new Blk(y,x,z));
                blks.add(new Blk(y,z,x));
                blks.add(new Blk(z,x,y));
                blks.add(new Blk(z,y,x));

            }

            Collections.sort(blks);

            int res = LIS( blks.size(), blks );

            System.out.printf("Case %d: maximum height = %d\n",++test,res);


        }

    }

    static int LIS( int n, ArrayList<Blk> lst ) {

        int max_lis = -1;
        int[] lis = new int[n];

        for (int i=0 ; i<n ; i++) {
            lis[i] = lst.get(i).z;
            for (int j=0 ; j<i ; j++) {
                if (( (lst.get(i).x > lst.get(j).x && lst.get(i).y > lst.get(j).y) || (lst.get(i).x > lst.get(j).y && lst.get(i).y > lst.get(j).x) ) ) {
                    lis[i] = Math.max ( lis[i] , lis[j]+lst.get(i).z );
                }
            }
            max_lis = Math.max ( lis[i] , max_lis );
        }
        return max_lis;
    }

    public static class Blk implements Comparable<Blk>{

        int x, y, z;

        public Blk(int a, int b, int c){
            x = a;
            y = b;
            z = c;
        }

        public int compareTo(Blk other){

            if(x!=other.x) return x - other.x;
            else if(y!=other.y) return y - other.y;
            else return z - other.z;

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