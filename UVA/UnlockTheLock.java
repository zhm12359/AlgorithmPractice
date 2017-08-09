
import java.io.*;
import java.util.*;
public class UnlockTheLock {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t=1;
        while(true){
            int[] tks = lineToIntArray(br.readLine());
            int L = tks[0];
            int U = tks[1];
            int n = tks[2];
            if(L==0 && U==0 && n==0) break;

            int[] dp = new int[10000];
            boolean[] visited = new boolean[10000];
            int[] buttons = lineToIntArray(br.readLine());

            dp[L] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(L);

            boolean found=false;

            while(!queue.isEmpty()){

                int cur = queue.poll();

                if(cur==U){
                    found=true;
                    System.out.printf("Case %d: %d\n", t, (dp[cur]) );
                    break;
                }


                for(int i=0; i<n; i++){
                    int value = buttons[i];
                    int next = (cur+value)%10000;

                    if(!visited[next]){
                        dp[next] = dp[cur]+1;
                        queue.offer(next);
                        visited[next] = true;
                    }

                }

            }

            if(!found) System.out.printf("Case %d: Permanently Locked\n", t);
            t++;

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