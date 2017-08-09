import java.util.ArrayList;
import java.util.Scanner;

public class Vertex2 {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;

    private static int count = 0;

    private static void dfs(int start) {
        ArrayList<Integer> child = graph[start];
        for (int i = 0; i < child.size(); i++) {
            if (!visited[child.get(i)]) {
                visited[child.get(i)] = true;
                count++;
                dfs(child.get(i));
            }
        }
    }

    private static void get() {
        Scanner scan = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (true) {
            int n = scan.nextInt();
            if (n == 0)
                break;
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<Integer>();
            while (true) {
                int ver = scan.nextInt();
                if (ver == 0)
                    break;
                while (true) {
                    int next = scan.nextInt();
                    if (next == 0)
                        break;
                    graph[ver - 1].add(next - 1);
                }
            }
            visited = new boolean[n];
            int q = scan.nextInt();
            for (int i = 0; i < q; i++) {
                int start = scan.nextInt() - 1;
                for (int j = 0; j < visited.length; j++)
                    visited[j] = false;
                count = 0;
                dfs(start);
                builder.append(n - count);
                for (int j = 0; j < visited.length; j++) {
                    if (!visited[j])
                        builder.append(" " + (j + 1));
                }
                builder.append("\n");
            }
        }
        System.out.print(builder.toString());
    }

    public static void main(String[] args) {
        get();
    }

}