
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
public class Easy {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int arraySize, queries;
        line = br.readLine();
        while(true){

            String[] token = line.split(" ");

            arraySize = Integer.parseInt(token[0]);
            queries = Integer.parseInt(token[1]);

            HashMap<Integer, ArrayList<Integer> > map = new HashMap<Integer, ArrayList<Integer> >();

            //creating the data struc
            for(int i=0; i<arraySize; ++i){
                int cur = readInt();
                if(!map.containsKey(cur)){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    map.put(cur,temp);
                }else{
                    ArrayList<Integer> temp = map.get(cur);
                    temp.add(i);
                }
            }

            //System.out.println(map);

            for(int q=0; q<queries; ++q){
                int occurence = readInt() -1;
                int element = readInt();

                //System.out.println("occurence"+ occurence + "element" + element);

                try{

                    System.out.println( map.get(element).get(occurence)+1  );
                }catch (IndexOutOfBoundsException e) {

                    System.out.println(0);

                }
            }

            line = br.readLine();
            if(line==null){
                break;
            }






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