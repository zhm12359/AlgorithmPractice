import java.io.*;
import java.util.*;
public class BlazingNewTrails {

    static BufferedReader br;
    static TreeSet<Integer> ks = new TreeSet<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int[] tks = lineToIntArray(br.readLine());
        int n= tks[0], m=tks[1], k=tks[2], w=tks[3];
        for(int i=0; i<k; i++){
            ks.add(Integer.parseInt(br.readLine())-1);
        }

        Graph graph = new Graph(n,m);

        for(int i=0; i<m; i++){
            tks = lineToIntArray(br.readLine());
            int a = tks[0] -1;
            int b = tks[1] -1;
            int weight = tks[2];

            graph.edge[i].src = a;
            graph.edge[i].dest = b;
            graph.edge[i].weight = weight;

        }

        graph.KruskalMST(w);




    }




    static class Graph {
        // A class to represent a graph edge
        class Edge implements Comparable<Edge> {
            int src, dest, weight;

            // Comparator function used for sorting edges based on
            // their weight
            public int compareTo(Edge compareEdge) {
                return this.weight - compareEdge.weight;
            }

            public String toString(){
                return String.format("(%d, %d) %d", this.src, this.dest, this.weight);
            }
        }

        ;

        // A class to represent a subset for union-find
        class subset {
            int parent, rank;
        }

        ;

        int V, E;    // V-> no. of vertices & E->no.of edges
        Edge edge[]; // collection of all edges

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[E];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        // A utility function to find set of an element i
        // (uses path compression technique)
        int find(subset subsets[], int i) {
            // find root and make root as parent of i (path compression)
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        // A function that does union of two sets of x and y
        // (uses union by rank)
        void Union(subset subsets[], int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            // Attach smaller rank tree under root of high rank tree
            // (Union by Rank)
            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;

                // If ranks are same, then make one as root and increment
                // its rank by one
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        // The main function to construct MST using Kruskal's algorithm
        void KruskalMST(int nw) {
            int e = 0;  // An index variable, used for result[]
            int i = 0;  // An index variable, used for sorted edges

            ArrayList<Edge> result = new ArrayList<>();


            Arrays.sort(edge);

            subset subsets[] = new subset[V];
            for (i = 0; i < V; ++i)
                subsets[i] = new subset();


            for (int v = 0; v < V; ++v) {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            i = 0;  // Index used to pick next edge

            boolean[] visited = new boolean[E];

            int mustPick=0;
            for(int j=0; j<E; j++){
                Edge cur = edge[j];
                if( (ks.contains(cur.src)&&!ks.contains(cur.dest)) || (!ks.contains(cur.src)&&ks.contains(cur.dest)) ){
                    int x = find(subsets, cur.src);
                    int y = find(subsets, cur.dest);

                    if (x != y) {
                        result.add(cur);
                        e++;
                        Union(subsets, x, y);
                        mustPick++;
                        visited[j] = true;
                    }

                }
                if(mustPick==nw){
                   break;
                }
            }

            boolean possible = true;
            //System.out.println(nw + " " + result.size());

            if(result.size()!=nw){

                possible = false;
            }

            e = nw;
            int prevE = e;


            // Number of edges to be taken is equal to V-1
            while ( i<E && possible) {
                Edge next_edge = new Edge();

                next_edge = edge[i++];
                if(visited[i-1]==true) continue;

                if( (ks.contains(next_edge.src)&&!ks.contains(next_edge.dest)) || (!ks.contains(next_edge.src)&&ks.contains(next_edge.dest)) ) continue;

                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);

                if (x != y) {
                    result.add(next_edge);
                    e++;
                    Union(subsets, x, y);

                }

            }

            //System.out.println(result);

            if(result.size()<V-1 || !possible){
                System.out.println(-1);
            }else{
                int sum=0;
                for(int j=0; j<result.size(); j++){
                    sum+=result.get(j).weight;
                }
                System.out.println(sum);
            }

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