import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Password {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int k = Integer.parseInt(br.readLine());

            HashMap<String, Integer> seen1 = new HashMap<String, Integer>();
            HashMap<String, Integer> seen2 = new HashMap<String, Integer>();
            HashMap<String, Integer> seen3 = new HashMap<String, Integer>();
            HashMap<String, Integer> seen4 = new HashMap<String, Integer>();
            HashMap<String, Integer> seen5 = new HashMap<String, Integer>();

            ArrayList<String> p1s = new ArrayList<String>();
            ArrayList<String> p2s = new ArrayList<String>();
            ArrayList<String> p3s = new ArrayList<String>();
            ArrayList<String> p4s = new ArrayList<String>();
            ArrayList<String> p5s = new ArrayList<String>();

            for(int r=0; r<6; ++r){
                String[] line= br.readLine().split("");
                String p1 = line[0];
                String p2 = line[1];
                String p3 = line[2];
                String p4 = line[3];
                String p5 = line[4];

                seen1.put(p1,1);

                seen2.put(p2,1);

                seen3.put(p3,1);

                seen4.put(p4,1);

                seen5.put(p5,1);

            }

            for(int r=6; r<12; ++r){
                String[] line= br.readLine().split("");
                String p1 = line[0];
                String p2 = line[1];
                String p3 = line[2];
                String p4 = line[3];
                String p5 = line[4];

                if(seen1.containsKey(p1)) {
                    if(!p1s.contains(p1)) p1s.add(p1);
                }

                if(seen2.containsKey(p2)) {
                    if(!p2s.contains(p2)) p2s.add(p2);
                }

                if(seen3.containsKey(p3)) {
                    if(!p3s.contains(p3)) p3s.add(p3);
                }

                if(seen4.containsKey(p4)) {
                    if(!p4s.contains(p4)) p4s.add(p4);
                }

                if(seen5.containsKey(p5)) {
                    if(!p5s.contains(p5)) p5s.add(p5);
                }

            }

            ArrayList<String> candidates = new ArrayList<String>();

            for(int a=0; a<p1s.size(); ++a){

                String p1 = p1s.get(a);


                for(int b=0; b<p2s.size(); ++b){

                    String p2 = p2s.get(b);

                    for(int c=0; c<p3s.size(); ++c){

                        String p3 = p3s.get(c);

                        for(int d=0; d<p4s.size(); ++d){

                            String p4 = p4s.get(d);

                            for(int e=0; e<p5s.size(); ++e){

                                String p5 = p5s.get(e);
                                String can=p1+p2+p3+p4+p5;

                                candidates.add(can);
                            }

                        }
                    }

                }
            }

            //System.out.println(p2s);

            Collections.sort(candidates);
            //System.out.println(candidates);
//
//            System.out.println(candidates);
//
//            System.out.println("\n\n");


            if(k-1<candidates.size()) System.out.println(candidates.get(k-1));
            else System.out.println("NO");


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