import java.io.*;
import java.util.*;
public class SendingEmail2 {

    static BufferedReader br;
    static ArrayList<Integer>[] adjList;
    static HashMap<String, Integer> weights;
    static int[] dist;
    static int n;
    static int S;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int Tc = Integer.parseInt(br.readLine());

        for(int t=1; t<=Tc; t++){

            int[] tks = lineToIntArray(br.readLine());
            n = tks[0];
            int m = tks[1];
            S = tks[2];
            int T = tks[3];

            List<Edge>[] edges = new List[n];
            for(int i=0; i<n; i++) edges[i] = new ArrayList<>();

            for(int i=0; i<m; i++){

                tks = lineToIntArray(br.readLine());
                int v1 = tks[0], v2 = tks[1], weight = tks[2];

                edges[v1].add(new Edge(v2, weight));

                edges[v2].add(new Edge(v1, weight));

            }


            int[] dist = new int[n];
            int[] pred = new int[n];
            shortestPaths(edges, S, dist, pred);

            if(dist[T]!=Integer.MAX_VALUE){
                System.out.printf("Case #%d: %d\n", t, dist[T]);
            }else{
                System.out.printf("Case #%d: unreachable\n", t);
            }

        }
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

}
