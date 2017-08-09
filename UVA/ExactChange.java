import java.util.Scanner;

public class ExactChange {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int MAX = 30000;

        while(T-->0){

            int bill = sc.nextInt();
            int n = sc.nextInt();

            int[] dp = new int[MAX];

            for(int j = 0; j<n; j++)
            {
                int note = sc.nextInt();

                for(int i = MAX-note-1; i>= 0; i--)
                {  //first time I did the loop from 0 to MAX-note and Got Wrong Answer. Think Why 🙂
                    if(dp[i]!=0)
                    {
                        if(dp[i+note] == 0 || dp[i+note] > dp[i] + 1)
                        {
                            dp[i+note] = dp[i] + 1;
                        }
                    }
                }
                dp[note] = 1;
            }

            for(int i = bill; i<MAX; i++)
            {
                if(dp[i]>0)
                {
                    System.out.printf("%d %d\n", i, dp[i]);
                    break;
                }
            }



        }

    }
}
