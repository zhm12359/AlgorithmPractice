import java.io.*;
import java.util.*;
public class PickUpSticks {

    static BufferedReader br;
    static HashMap<Integer, TreeSet<Integer>> graph;
    static int[] incoming;
    static boolean[] visited;
    static ArrayList<Integer> sorted;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){

            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0];
            int m = tks[1];
            if(n==0 && m==0) break;

            graph = new HashMap<>();
            incoming = new int[n+1];
            visited = new boolean[n+1];
            sorted = new ArrayList<>();

            for(int i=1; i<=n; i++) graph.put(i, new TreeSet<>());

            for(int i=0; i<m; i++){
                tks = lineToIntArray(br.readLine());
                int a = tks[0];
                int b = tks[1];

                TreeSet<Integer> to = graph.get(a);
                if(!to.contains(b)) incoming[b]++;
                to.add(b);

            }

//            System.out.println(graph);
//            System.out.println(Arrays.toString(incoming));

//            boolean start = false;
//            for(int i=1; i<=n; i++){
//                if(incoming[i]==0){
//                    start=true;
//                    break;
//                }
//            }


            if(topSort()){

                for(int i: sorted){
                    pw.println(i);
                }

            }else{
                pw.println("IMPOSSIBLE");
            }
            //System.out.println(sorted);


        }

        pw.flush();


    }
    //returns false if there's a cycle
    public static boolean topSort(){

        Queue<Integer> ready = new LinkedList<Integer>();
        for(int i=1; i<incoming.length; i++){
            if(incoming[i]==0) {
                ready.offer(i);
            }

        }


        if(ready.isEmpty()) return false;

        while(!ready.isEmpty()){

            //System.out.println(Arrays.toString(visited));

            int cur = ready.poll();
            sorted.add(cur);


            for(int i: graph.get(cur)){
                if(visited[i]) {
                    //System.out.printf("happen  %d  \n", cur);
                    return false; //found cycle
                }
                incoming[i]--;
                if(incoming[i]==0) {
                    visited[i]=true;
                    ready.offer(i);
                }
            }

            visited[cur] = true;

        }



        return true;

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