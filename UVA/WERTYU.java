import java.util.Scanner;
import java.util.Arrays;

public class WERTYU {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String[] line;
        String old, newChar;
        int index;

        String[] keyboard = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'",
                "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"};

        while(sc.hasNextLine()){

            line = sc.nextLine().split("");
            for(int i=0; i<line.length; ++i){
                old = line[i];
                if(old.equals(" ")) continue;

                index = Arrays.asList(keyboard).indexOf(old);
                line[i] = keyboard[index - 1];
            }

            System.out.println(String.join("",line));

        }

    }

}
