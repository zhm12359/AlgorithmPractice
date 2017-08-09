
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Argus {

    static BufferedReader br;

    static class Node implements Comparable<Node>{
        int id;
        int p;
        int op; //original period

        Node(int a, int b, int op){
            this.id = a;
            this.p = b;
            this.op = op;
        }

        public int compareTo(Node other){

            if(other.p > p) return -1;
            else if(other.p<p) return 1;
            else{
                //System.out.println("happen"+ "other "+ other.id + "this" + id + " " + other.p);
                if(other.id < id) return 1;
                else return -1;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int arraySize, queries;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        while(true){
            line = br.readLine();
            if(line.equals("#")){
                break;
            }
            String[] token = line.split("\\s+");
            pq.offer(new Node(Integer.parseInt(token[1]), Integer.parseInt(token[2]), Integer.parseInt(token[2])));
        }

        int top = Integer.parseInt(br.readLine());


        while(top-->0){
            Node cur = pq.poll();
            System.out.println(cur.id);

            pq.offer(new Node(cur.id, cur.p + cur.op, cur.op));
        }





    }



    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    static int readInt() throws Exception {
        int c = br.read();
        while (isWhitespace(c)) {
            c = br.read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = br.read();
        }
        int res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = br.read();
        } while (!isWhitespace(c));
        return res * sgn;
    }


}