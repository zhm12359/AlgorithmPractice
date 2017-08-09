import java.io.*;
import java.util.*;
public class StableUnicorn {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0];
            Node R = new Node("R",tks[1]), O = new Node("O",tks[2]),
                    Y = new Node("Y",tks[3]), G = new Node("G",tks[4]),
                    B = new Node("B",tks[5]), V = new Node("V",tks[6]);

            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(R);
            nodes.add(O);
            nodes.add(Y);
            nodes.add(G);
            nodes.add(B);
            nodes.add(V);
            //{R, O, Y, G, B, V};

            String ans = "";

            int count = 0;
            while(ans.length() != n){

                Collections.sort(nodes);

                //System.out.println(nodes);

                Node pick = nodes.get(5);
                String pickColor = nodes.get(5).color;

                String prev="";
                if(ans.length()>0){
                    prev = ans.charAt(ans.length()-1) +"";
                }

                pickColor = pick.color;
                pick.num--;

                if(prev.equals(pickColor)){

                    //pick = nodes.get(4);
                    ans = pickColor+ans;


                }else{
                    ans += pickColor;
                }






            }

            System.out.println(ans);

//            if(!check(ans)){
//                System.out.printf("Case #%d: IMPOSSIBLE\n",t);
//            }else{
//                System.out.printf("Case #%d: %s\n",t,ans);
//            }

        }

    }


    static boolean check(String s){

        int len =s.length();
        if(len<=1) return true;

        if( len>1 && s.charAt(0) == s.charAt(len-1)){
            return false;
        }
        boolean res = true;
        for(int i=1; i<len; i++){
            if(s.charAt(i)==s.charAt(i-1)){
                res = false;
                break;
            }
        }

        return res;


    }

    static class Node implements Comparable<Node> {
        String color;
        int num;

        Node(String c, int n){
            this.color = c;
            this.num = n;
        }

        public int compareTo(Node other){
            return Integer.compare(this.num, other.num);
        }

        public String toString(){
            return String.format("%s, %d", color, num);
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