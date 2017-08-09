import java.io.*;
import java.util.*;
public class ExactSum {

    static BufferedReader br;

    static class Node implements Comparable<Node>{
        int a;
        int b;
        int diff;

        Node(int a, int b){
            this.a = a;
            this.b = b;
            this.diff = Math.abs(a-b);
        }

        public int compareTo(Node other){

            return Integer.compare(diff,other.diff);

        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(true){
            String line = br.readLine();
            if(line==null) break;
            int n = Integer.parseInt(line);
            int[] books = lineToIntArray(br.readLine());
            int sum = Integer.parseInt(br.readLine());
            TreeSet<Node> ans = new TreeSet<>();

            Arrays.sort(books);

            for(int i=0; i<n; ++i){

                int cur = books[i];
                int left = sum-cur;

                int res = Arrays.binarySearch(Arrays.copyOfRange(books, i+1, books.length), left);
                if(res>=0){
                    ans.add(new Node(cur, left));
                }

            }

            pw.printf("Peter should buy books whose prices are %d and %d.\n\n", ans.first().a, ans.first().b);


            br.readLine();
        }
        pw.flush();
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