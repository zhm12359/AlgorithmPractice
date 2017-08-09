import java.io.*;
import java.util.*;
public class HappyEnding {

    static BufferedReader br;
    static HashMap<Integer, HashSet<Integer>> map;
    static HashSet<Integer> happy;
    static HashSet<Integer> sad;
    static HashMap<Integer, Integer> dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int S = Integer.parseInt(br.readLine());

            map = new HashMap<>();
            happy = new HashSet<>();
            sad = new HashSet<>();
            dp = new HashMap<>();


            for (int s = 0; s < S; s++){
                String line = br.readLine();
                String[] lineArray = line.split("\\s+");
                if(lineArray.length==2){
                    int p = Integer.parseInt(lineArray[0]);
                    if(lineArray[1].equals("favourably")){
                        happy.add(p);
                    }else{
                        sad.add(p);
                    }
                } else{
                    int p1 = Integer.parseInt(lineArray[0]);
                    HashSet<Integer> connect = new HashSet<>();
                    for(int c=1; c<4; ++c){
                        connect.add(Integer.parseInt(lineArray[c]));
                    }
                    map.put(p1, connect);
                }

            }
            System.out.println(dfs(1));
        }
    }

    public static int dfs(int p){

        if (dp.containsKey(p)) return dp.get(p);
        if (happy.contains(p)) {
            dp.put(p, 1);
            return 1;
        }
//        if (sad.contains(p)) {
//            dp.put(p, 0);
//            return 0;
//        }

        int count=0;
        HashSet<Integer> children = map.get(p);

        if (children != null){
            for(int i : children){
                count += dfs(i);
            }
        }
        dp.put(p, count);
        return count;
    }

}