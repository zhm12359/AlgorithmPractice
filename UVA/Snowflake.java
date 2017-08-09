
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Snowflake {

    static BufferedReader br;

        public static void main(String[] args) throws Exception {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int Cases = Integer.parseInt(br.readLine());

            int max, start, cur;

            for(int c=0; c< Cases; ++c) {

                int arraySize = Integer.parseInt(br.readLine());
                HashMap<Integer, Integer> snow = new HashMap<Integer, Integer>();
                max = 0;
                start = 0;

                for (int i = 0; i < arraySize; i++) {
                    cur = Integer.parseInt(br.readLine());
                    if (snow.containsKey(cur)) {

                        int pos = snow.get(cur);
                        if (pos >= start) {
                            start = pos + 1;
                        }
                        max = max >i + 1 - start ? max : i + 1 - start ;
                        snow.put(cur, i);
                    } else {
                        snow.put(cur, i);
                        max = max >i + 1 - start ? max : i + 1 - start ;
                    }
                }
                System.out.println(max);
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