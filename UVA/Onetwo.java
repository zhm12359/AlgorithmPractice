import java.util.Scanner;

public class Onetwo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line="";
        int num = Integer.parseInt(sc.nextLine());
        while((num--) > 0) {
            line = sc.nextLine();
            if(line.length() == 5) {
                System.out.println("3");
                continue;
            }
            if((line.charAt(0) == 'o' && line.charAt(1) == 'n') ||
                    (line.charAt(1) == 'n' && line.charAt(2) == 'e') ||
                    (line.charAt(2) == 'e' && line.charAt(0) == 'o'))
                System.out.println("1");
            else
                System.out.println("2");
        }
    }
}