import java.io.*;
import java.util.*;
public class Ordering {

    static BufferedReader br;
    static TreeMap<String, TreeSet<String>> graph;
    static HashMap<String, Boolean> visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());


        while(T-->0){
            br.readLine();
            graph = new TreeMap<>();
            visited = new HashMap<>();
            String[] nodes = br.readLine().split("\\s+");
            String[] cons = br.readLine().split("\\s+");

            for(String s: nodes) {
                graph.put(s, new TreeSet<>());
                visited.put(s, false);
            }
            for(String s: cons){
                String[] tks = s.split("<");
                String a = tks[0];
                String b = tks[1];
                graph.get(a).add(b);

            }

            //System.out.println(graph);
            if(!dfs("")) System.out.println("NO");
            if(T>0) System.out.println();

        }

    }

    public static boolean valid(String s ){
        TreeSet<String> children = graph.get(s);
        for(String c: children){
            //make sure the constrained element does not appear before s
            if(visited.get(c)) return false;
        }
        return true;
    }

    public static boolean dfs(String s){
        if(s.length() == graph.size()){
            print(s);
            return true;
        }

        boolean res = false;

        for(String element : graph.keySet()){
            if(!visited.get(element) && valid(element)){
                visited.put(element, true);
                res = dfs(s+element) || res;
                visited.put(element, false);
            }
        }

        return res;
    }

    public static void print(String s){

        int len = s.length();

        for(int i=0; i<len; i++){
            if(i<len-1) System.out.print(s.charAt(i)+" ");
            else System.out.print(s.charAt(i)+"\n");
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