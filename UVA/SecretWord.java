import java.io.*;
import java.util.*;
public class SecretWord {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            int n = line.length();
            for(int i=n-1; i>=0; i--){
                sb.append(line.charAt(i));
            }
            String reverse = sb.toString();
            //System.out.println(reverse);


            int[] table = buildKMPTable(line);
            int fs = simulate(table, reverse, line);

            String ansRev = line.substring(0, fs);
            sb = new StringBuilder();
            int alen = ansRev.length();
            for(int i=alen-1; i>=0; i--){
                sb.append(line.charAt(i));
            }

            String ans = sb.toString();

            System.out.println(ans);



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
        int longest = 0;
        for (int i = 0; i < text.length(); ++i)
        {
            while (true)
            {
                if (text.charAt(i) == pattern.charAt(state)) {
                    state++;
                    longest = Math.max(state, longest);
                    break; }
                else if (state == 0) break;
                state = table[state];
                longest = Math.max(state, longest);
            }
            if (state == table.length -1) break;
        }
        return longest;
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
