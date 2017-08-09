import java.io.*;
import java.util.*;
public class TidyNumber {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<T+1; t++){

            String n = br.readLine().trim();
            int len = n.length();
            int[] digits = new int[len];

            for(int i=0; i<len; i++){
                digits[i] = Integer.parseInt(""+n.charAt(i));
            }
            //System.out.println(Arrays.toString(digits));

            if(len==1) System.out.printf("Case #%d: %s\n", t, n);
            else{

                int fast = 1;
                int slow = 0;

                while(fast<len && slow<len){

                    if(digits[fast]>=digits[slow]){
                        fast++;
                        slow++;
                    }else{
                        int before = digits[slow];
                        digits[slow]--;
                        int after = digits[slow];
                        //System.out.println("before "+ before + " after "+ after);

                        //look back
                        int back = slow-1;
                        //System.out.println(back+ " back");
                        if(after!=0){
                            while(back>=0 && digits[back]==before){
                                digits[back] = after;
                                back--;
                            }

                            int forward = back+2;
                            while(forward<len && digits[forward]==after){
                                digits[forward]=9;
                                forward++;
                            }

                        }else{
                            digits[0]=0;
                            for(int i=1; i<len; i++){
                                digits[i]=9;
                            }

                        }

                        //trailing 0
                        for(int j=fast; j<len; j++){
                            digits[j] = 9;
                        }
                        break;
                    }

                }

                String ans="";
                for(int i=0; i<len; i++){
                    ans += (digits[i]==0? "" : digits[i]);
                }

                System.out.printf("Case #%d: %s\n", t, ans);


            }


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