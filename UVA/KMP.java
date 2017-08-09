import java.util.Arrays;

public class KMP
{
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
    public static void main(String[] args)
    {
        String pattern = args[0];
        String text = args[1];
        int[] table = buildKMPTable(pattern);
        System.out.println("table "+ Arrays.toString(table) );
        for (int i : table) System.out.print(i);
        System.out.println();
        System.out.println(simulate(table,text,pattern));
    }
}