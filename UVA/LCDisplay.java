import java.util.ArrayList;
import java.util.Scanner;

public class LCDisplay {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int s;
        String n;
        String[] nl;
        String[] rep;
        ArrayList<String[]> board = new ArrayList<String[]>();
        String result="";

        while(sc.hasNext()){
            result = "";

            board.clear();

            s = sc.nextInt();
            n = sc.next();
            nl = n.split("");
            rep = new String[nl.length];

            if(s==0 && n.equals("0")) break;
            for(int i=0; i<nl.length; ++i){
                switch (nl[i]){
                    case "0":
                        rep = zero(s);
                        break;
                    case "1":
                        rep = one(s);
                        break;
                    case "2":
                        rep = two(s);
                        break;
                    case "3":
                        rep = three(s);
                        break;
                    case "4":
                        rep = four(s);
                        break;
                    case "5":
                        rep = five(s);
                        break;
                    case "6":
                        rep = six(s);
                        break;
                    case "7":
                        rep = seven(s);
                        break;
                    case "8":
                        rep = eight(s);
                        break;
                    case "9":
                        rep = nine(s);
                        break;
                    default:
                        break;
                }
                board.add(rep);
            }

            for(int col=0; col<2*s+3; ++col){
                for(int row=0; row<nl.length; ++row){
                    result += String.join("",board.get(row)[col].replace("\n", ""));
                    if(row!=nl.length-1) result+=" ";
                    else result+="\n";
                }
            }
            System.out.println(result);


        }
    }

    public static String horizontalLine(int padding, int size){
        String result="";
        for(int i=0; i<padding; ++i){
            result += " ";
        }

        for(int j=0; j<size; ++j){
            result += "-";
        }
        result += " \n";
        return result;
    }

    public static String verticalLine(int left, int right){
        String result="";

            for(int j=0; j<left; ++j){
                result += " ";
            }
            result += "|";
        for(int j=0; j<right; ++j){
            result += " ";
        }
        result +="\n";

        return result;
    }

    public static String spaces(int n){
        String res="";
        for(int i=0;i<n;i++){
            res+=" ";
        }
        return res+"\n";
    }

    public static String twoLines(int padding){
        String result ="";

            result+="|";
            for(int j=0; j<padding; ++j){
                result += " ";
            }
            result += "|\n";



        return result;
    }

    public static String[] zero(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String twoLine = twoLines(s);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = twoLine;
        }
        result[height/2] = spaces(s+2);
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = twoLine;
        }
        result[2*s+2] =line;



        return result;

    }

    public static String[] one(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = verticalLine(s+1,0);

        result[0] = spaces(s+2);
        for(int i=1; i< height/2; ++i){
            result[i] = line;
        }
        result[height/2] = spaces(s+2);
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = line;
        }
        result[2*s+2] =spaces(s+2);



        return result;

    }

    public static String[] two(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String upVertical = verticalLine(s+1,0);
        String lowerVertical = verticalLine(0,s+1);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = upVertical;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = lowerVertical;
        }
        result[2*s+2] =line;


        return result;

    }

    public static String[] three(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String vertical = verticalLine(s+1,0);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = vertical;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = vertical;
        }
        result[2*s+2] =line;


        return result;

    }

    public static String[] four(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String twoLine = twoLines(s);
        String lowerVertical = verticalLine(s+1,0);

        result[0] = spaces(s+2);
        for(int i=1; i< height/2; ++i){
            result[i] = twoLine;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = lowerVertical;
        }
        result[2*s+2] =spaces(s+2);

        return result;

    }

    public static String[] five(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String lowerVertical = verticalLine(s+1,0);
        String upVertical = verticalLine(0,s+1);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = upVertical;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = lowerVertical;
        }
        result[2*s+2] =line;

        return result;

    }

    public static String[] six(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String twoLine = twoLines(s);
        String upVertical = verticalLine(0,s+1);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = upVertical;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = twoLine;
        }
        result[2*s+2] =line;



        return result;

    }

    public static String[] seven(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String hline= horizontalLine(1,s);

        String line = verticalLine(s+1,0);

        result[0] = hline;
        for(int i=1; i< height/2; ++i){
            result[i] = line;
        }
        result[height/2] = spaces(s+2);
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = line;
        }
        result[2*s+2] =spaces(s+2);



        return result;

    }

    public static String[] eight(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String twoLine = twoLines(s);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = twoLine;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = twoLine;
        }
        result[2*s+2] =line;



        return result;

    }


    public static String[] nine(int s){
        int height = 2*s + 3;
        String[] result = new String[height];
        int size = s+2;

        String line = horizontalLine(1,s);
        String twoLine = twoLines(s);
        String low = verticalLine(s+1,0);
        result[0] = line;
        for(int i=1; i< height/2; ++i){
            result[i] = twoLine;
        }
        result[height/2] = line;
        for(int i=height/2+1; i< 2*s+2; ++i){
            result[i] = low;
        }
        result[2*s+2] =line;


        return result;

    }

}

/*
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    private static final char SPACE = ' ', VERTICAL = '|', HORIZONTAL = '-';
    private static final byte[] BITMAP = {0077, 0024, 0155, 0165, 0126, 0163, 0173, 0025, 0177, 0167};

    public static void main(String[] args) {
        run(System.in, System.out);
    }

    private static void run(InputStream input, PrintStream out) {
        Scanner inputScanner = new Scanner(input);
        while (inputScanner.hasNext()) {
            int size = inputScanner.nextInt();
            String number = inputScanner.next();

            if ((size == 0) && (number.equals("0"))) {
                break;
            }

            out.printf("%s\n", getLCDString(size, number));
        }
    }

    private static String getLCDString(int size, String number) {
        final int numberOfChar = number.length();
        String[][] buffer = new String[numberOfChar][];
        for (int i = 0; i < numberOfChar; i++) {
            buffer[i] = getLCDDigit(size, number.charAt(i));
        }

        final int totalHeight = 2 * size + 3;
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < totalHeight; row++) {
            for (int charIndex = 0; charIndex < numberOfChar; charIndex++) {
                sb.append(buffer[charIndex][row]);
                if (charIndex < numberOfChar - 1) sb.append(SPACE);
            }
            sb.append("\n"); //new line for next row
        }

        return sb.toString();
    }

    private static String[] getLCDDigit(int size, char c) {
        final int totalHeight = 2 * size + 3;
        String[] rows = new String[totalHeight];

        char[] charBuffer = new char[size];
        byte bitmap = BITMAP[c - '0'];

        //first row
        Arrays.fill(charBuffer,  (bitmap & 0001) > 0 ? HORIZONTAL : SPACE);
        StringBuilder sb = new StringBuilder();
        rows[0] = sb.append(SPACE).append(String.valueOf(charBuffer)).append(SPACE).toString();

        //middle row
        sb = new StringBuilder();
        Arrays.fill(charBuffer, (bitmap & 0100) > 0 ? HORIZONTAL : SPACE);
        rows[size + 1] = sb.append(SPACE).append(String.valueOf(charBuffer)).append(SPACE).toString();

        //bottom row
        sb = new StringBuilder();
        Arrays.fill(charBuffer, (bitmap & 0040) > 0 ? HORIZONTAL : SPACE);
        rows[2 * size + 2] = sb.append(SPACE).append(String.valueOf(charBuffer)).append(SPACE).toString();

        //top half
        sb = new StringBuilder();
        Arrays.fill(charBuffer, SPACE);
        Arrays.fill(rows, 1, size + 1, sb.append((bitmap & 0002) > 0 ? VERTICAL : SPACE).append(String.valueOf(charBuffer)).append((bitmap & 0004) > 0 ? VERTICAL : SPACE).toString());

        //bottom half
        sb = new StringBuilder();
        Arrays.fill(charBuffer, SPACE);
        Arrays.fill(rows, size + 2, 2 * size + 2, sb.append((bitmap & 0010) > 0 ? VERTICAL : SPACE).append(String.valueOf(charBuffer)).append((bitmap & 0020) > 0 ? VERTICAL : SPACE).toString());

        return rows;
    }
}
 */
