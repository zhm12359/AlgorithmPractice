
import java.io.*;
import java.util.*;
public class SoftwareAllocation2 {

    static BufferedReader br;

    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(true){

            String line;
            //graph = new int[38][38]; // 0 is source, [1,26] are applications, [27-36] are computers, 37 is sink.
            //parent = new int[38];
            List<Edge>[] graph = createGraph( 38);
            for(int i=0; i<38; i++) graph[i] = new ArrayList<>();
            for(int i=27; i<=36; ++i){
                //graph[i][37] =1 ; //computer to sink is 1 (each computer can only process one at a time)
                addEdge(graph, i, 37, 1);
            }

            int targetFlow = 0;
            while((line=br.readLine())!=null && !line.equals("")){

                String[] tks = line.split("\\s+");
                char program = tks[0].charAt(0);
                int weight = Integer.parseInt( tks[0].substring(1) );
                targetFlow += weight;

                //graph[0][program - 'A' + 1] = weight; //source to program has weight amount of target flows
                addEdge(graph, 0, program - 'A' + 1, weight );

                for(char i : tks[1].replace(";","").toCharArray()){
                    //System.out.println(i - '0' + 27);
                    //graph[program - 'A' + 1][ i - '0' + 27  ] = 1; // program to computer is 1
                    addEdge(graph, program - 'A' + 1, i - '0' + 27, 1);
                }

            } //end of input -> graph


            char[] result = new char[10];
            Arrays.fill(result, '_');
            if(maxFlow(graph,0, 37)==targetFlow){

                for(int i=27; i<=36; i++){
//                    if(parent[i] ==-1) System.out.print("_");
//                    else System.out.print( (char)(parent[i]+'A'-1)+" ");

                    // look for starting from computers to Programs, that has a backedge with 1

                    //boolean found=false;
                    for(int j=1; j<=26; j++){

                        //if(rGraph[i][j]==1) {
                        if( graph[i].size() > j && graph[i].get(j).t==1){
                            result[i-27] = (char) (j+'A'-1);
                            //System.out.print( (char) (j+'A'-1) );
                        }
                        //found=true;

                    }

                    //if(!found) System.out.print("_");

                }

                System.out.println(String.valueOf(result));

            }else{
                System.out.println("!");
            }

            if(line==null) break;


        }
    }


    static class Edge {
        int s, t, rev, cap, f;

        public Edge(int s, int t, int rev, int cap) {
            this.s = s;
            this.t = t;
            this.rev = rev;
            this.cap = cap;
        }
    }

    public static List<Edge>[] createGraph(int nodes) {
        List<Edge>[] graph = new List[nodes];
        for (int i = 0; i < nodes; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap) {
        graph[s].add(new Edge(s, t, graph[t].size(), cap));
        graph[t].add(new Edge(t, s, graph[s].size() - 1, 0));
    }

    public static int maxFlow(List<Edge>[] graph, int s, int t) {
        int flow = 0;
        int[] q = new int[graph.length];
        while (true) {
            int qt = 0;
            q[qt++] = s;
            Edge[] pred = new Edge[graph.length];
            for (int qh = 0; qh < qt && pred[t] == null; qh++) {
                int cur = q[qh];
                for (Edge e : graph[cur]) {
                    if (pred[e.t] == null && e.cap > e.f) {
                        pred[e.t] = e;
                        q[qt++] = e.t;
                    }
                }
            }
            if (pred[t] == null)
                break;
            int df = Integer.MAX_VALUE;
            for (int u = t; u != s; u = pred[u].s)
                df = Math.min(df, pred[u].cap - pred[u].f);
            for (int u = t; u != s; u = pred[u].s) {
                pred[u].f += df;
                graph[pred[u].t].get(pred[u].rev).f -= df;
            }
            flow += df;
        }
        return flow;
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