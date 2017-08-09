import java.io.*;
import java.util.*;
public class SendingEmail {

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

            adjList = new ArrayList[n];
            weights = new HashMap<>();

            for(int i=0; i<n; i++) adjList[i] = new ArrayList<>();

            for(int i=0; i<m; i++){

                tks = lineToIntArray(br.readLine());
                int v1 = tks[0], v2 = tks[1], weight = tks[2];

                adjList[v1].add(v2);
                adjList[v2].add(v1);
                weights.put(v1+"-"+v2, weight);
                weights.put(v2+"-"+v1, weight);

            }

            dijkstra();

            //System.out.println(Arrays.toString(dist));

            if(dist[T]!=Integer.MAX_VALUE){
                System.out.printf("Case #%d: %d\n", t, dist[T]);
            }else{
                System.out.printf("Case #%d: unreachable\n", t);
            }

        }
    }

    static void dijkstra() {

        dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[S] = 0;
        //Node sourceNode = new Node(0, S, 0);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        minQ.offer(S);

        while(!minQ.isEmpty()){

            int v1 = minQ.poll();

            for(int i=0; i<adjList[v1].size(); i++){
                int neighbor = adjList[v1].get(i);

                int temp = dist[v1] + weights.get(neighbor+"-"+v1);
                if(dist[neighbor]>temp){
                    dist[neighbor] = temp;
                    minQ.add(neighbor);
                }
            }

        }

    }

//    static class Node implements Comparable<Node>{
//        int a, b, weight;
//
//        Node( int x, int y, int w){
//            this.a = x;
//            this.b = y;
//            this.weight = w;
//        }
//
//        public int compareTo(Node other){
//            return Integer.compare(this.weight, other.weight);
//        }
//
//    }

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
