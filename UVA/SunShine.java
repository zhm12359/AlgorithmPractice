import java.io.*;
import java.util.*;
public class SunShine {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine());

        while( T-->0){
            int n = Integer.parseInt(br.readLine());

            ArrayList<Point> pts = new ArrayList<>();

            for(int i =0; i<n; i++){
                double[] tks = lineToIntArray(br.readLine());
                pts.add(new Point(tks[0], tks[1]));
            }

            Collections.sort(pts);

            double d = 0;
            if(n<=1){
                System.out.println("0.00");
                break;
            }else{

                int low = n-1;
                int high = n-2;
                double highY = 0;

                while(low>0 && high>=0){
                    Point a = pts.get(low);
                    Point b = pts.get(high);

                    if(highY==0) {
                        d+=dist(a,b);
                        highY = b.y;
                    }
                    else if(b.y > highY){
                        //System.out.println("happen");

                        double m=  dist(a,b) * (b.y-highY)/ (b.y-a.y);
                        d+=m;

                        highY = b.y;
                    }


                    low = low-2;
                    high = high -2;

                }

            }

            System.out.printf( "%.2f\n",d );


        }
    }


    static class Point implements Comparable<Point>
    {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public String toString() { return String.format("(%.03f,%.03f)", x,y); }

        public int compareTo(Point other){
            int ret = (int) Double.compare(this.x, other.x);
            return ret;
        }
    }

    static double dist(Point a, Point b) { return Math.hypot(a.x-b.x,a.y-b.y); }

    public static double[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }

}