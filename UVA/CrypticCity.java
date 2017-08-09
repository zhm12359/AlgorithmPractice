import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class CrypticCity {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0], A = tks[1], B = tks[2];

            n+=1;

            List<Edge>[] edges = new List[n];
            for(int i=0; i<n; i++) edges[i] = new ArrayList<Edge>();

            for(int x = 2; x<n; x++){
                for(int y =x; y<n; y++){
                    edges[x].add(new Edge(y, gcd(x,y)));
                    edges[y].add(new Edge(x, gcd(x,y)));
                }
            }

            int[] dist = new int[n];
            int[] pred = new int[n];

            shortestPaths(edges, A, dist, pred);

//            System.out.println(Arrays.toString(dist));
//            System.out.println(Arrays.toString(pred));
            System.out.printf("%d ", A);
            printPath(pred, A,B);
            System.out.println();




        }
    }

    static void printPath(int[] p, int s, int d){

        if(s==d) return;
        printPath(p, s, p[d] );

        System.out.printf("%d ", d );

    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void shortestPaths(List<Edge>[] edges, int s, int[] prio, int[] pred) {
        Arrays.fill(pred, -1);
        Arrays.fill(prio, Integer.MAX_VALUE);
        prio[s] = 0;
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add((long) s);
        while (!q.isEmpty()) {
            long cur = q.remove();
            int curu = (int) cur;
            if (cur >>> 32 != prio[curu])
                continue;
            for (Edge e : edges[curu]) {
                int v = e.t;
                int nprio = prio[curu] + e.cost;
                if (prio[v] > nprio) {
                    prio[v] = nprio;
                    pred[v] = curu;
                    q.add(((long) nprio << 32) + v);
                }
            }
        }
    }

    static class Edge {
        int t, cost;

        public Edge(int t, int cost) {
            this.t = t;
            this.cost = cost;
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

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }
}
