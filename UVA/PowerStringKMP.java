import java.io.*;
import java.util.*;
public class PowerStringKMP {

    static BufferedReader br;
    static String line;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(!(line=br.readLine()).equals(".")){

            int n = line.length();




        }

    }


    public static int[] buildKMPTable(String pattern)
    {
        int[] table = new int[pattern.length()+1];
        for (int i = 2; i < table.length; ++i)
        {
            int j = table[i-1];
            while (true)
            {
                if (pattern.charAt(j) == pattern.charAt(i-1)) { table[i] = j+1; break;}
                else if (j == 0) break;
                else j = table[j];
            }
        }
        return table;
    }
    /** Returns the final state when simulating the DFA built using pattern on the string text */
    public static int simulate(int[] table, String text, String pattern)
    {
        int state = 0;
        for (int i = 0; i < text.length(); ++i)
        {
            while (true)
            {
                if (text.charAt(i) == pattern.charAt(state)) { state++; break; }
                else if (state == 0) break;
                state = table[state];
            }
            if (state == table.length -1) break;
        }
        return state;
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