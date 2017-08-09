import java.io.*;
import java.util.*;
public class OpenSource {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();

        while (true) {

            if (line.equals("0")) break;

            HashMap<String, String> students = new HashMap<>();
            TreeMap<String, Integer> projects = new TreeMap<>();
            PriorityQueue<Node> pq = new PriorityQueue<>(100, Collections.reverseOrder());

            String input = line;

            String currentProject = input;
            while (true) {
                if (input.equals("1")) break;
                if (input.toUpperCase().equals(input)) {
                    //System.out.println("meow " + input);
                    currentProject = input;
                    projects.put(currentProject, 0);
                } else {

                    if (!students.containsKey(input)) {
                        students.put(input, currentProject);
                    } else {
                        if (!students.get(input).equals(currentProject))
                            students.put(input, "invalid");
                    }
                }

                input = br.readLine();

            }

            for (String s : students.keySet()) {
                String p = students.get(s);
                if (!p.equals("invalid")) {
                    projects.put(p, projects.get(p) + 1);
                }
            }

            for(String p : projects.keySet()){

                int n = projects.get(p);
                pq.offer(new Node(p,n));

            }

            while(!pq.isEmpty()){
                Node cur = pq.poll();
                pw.printf("%s %d\n", cur.name, cur.num);
            }

            pw.flush();

            line = br.readLine();


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


    public static class Node implements Comparable<Node> {

        String name;
        int num;

        public Node(String name, int n) {
            this.name = name;
            this.num = n;
        }

        public int compareTo(Node other) {
            int res = Integer.compare(this.num, other.num);
            if (res == 0) {
                res = 0 - name.compareTo(other.name);
            }
            return res;

        }
    }
}