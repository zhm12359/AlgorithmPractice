
import java.io.*;
import java.util.*;
public class YinAndYang {

    static BufferedReader br;



    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        int n = line.length();
        HashMap<String, Integer> results = new HashMap<>();
        HashMap<String, Integer> temp = new HashMap<>();
        results.put("B", 0);
        results.put("W", 0);
        temp.put("B", 0);
        temp.put("W", 0);

        String s = line;
        boolean possible = true;
        int len = n;
        int preLen = n;

        do{
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i)=='W') temp.put("W", temp.get("W")+1);
                if(s.charAt(i)=='B') temp.put("B", temp.get("B")+1);

                if(temp.get("W") == temp.get("B") +1 && temp.get("B")!=0){
                    s = "W" + s.substring(i+1);
                    break;
                }
                if(temp.get("B") == temp.get("W") + 1 && temp.get("W")!=0){
                    s= "B" + s.substring(i+1);
                    break;
                }

            }

            if(preLen == s.length()){
                possible = false;
                break;
            }

            temp.put("B",0);
            temp.put("W",0);

            preLen = s.length();

        }while(s.length()>2);

        if(s.equals("WB") || s.equals("BW")){
            System.out.println(1);
        }else{
            System.out.println(0);
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