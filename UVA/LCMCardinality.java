
import java.util.*;
import java.io.*;

public class LCMCardinality {

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            int number = Integer.parseInt(br.readLine());

            if(number == 0) {
                break;
            }

            //itself
            int counter = 0;

            ArrayList<Integer> divisors = new ArrayList<>();

            for(int i = 1; i <= (int) Math.sqrt(number); i++) {
                if(number % i == 0) {
                    divisors.add(i);
                    if(number/i != (int) Math.sqrt(number)) {
                        divisors.add(number/i);
                    }
                }
            }

            for(int i = 0; i < divisors.size(); i++) {
                for(int j = i; j < divisors.size(); j++) {
                    if(gcd(divisors.get(i), divisors.get(j)) == 1) {
                        counter++;
                    }
                }
            }

            System.out.println(number + " " + counter);
        }
    }
}