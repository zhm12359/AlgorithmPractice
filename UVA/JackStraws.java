import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class JackStraws {
    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            br.readLine();

            int n = Integer.parseInt(br.readLine());
            ArrayList<Line> lines = new ArrayList<>();

            for(int i=0; i<n; i++){
                double[] tks = lineToDoubleArray(br.readLine());
                Line l = new Line(new Point(tks[0], tks[1]), new Point(tks[2], tks[3]) );
                lines.add(l);
            }

            boolean[][] p = new boolean[n][n];
            for(int i=0; i<n; i++){
                p[i][i] = true;
                for(int j=i+1; j<n; j++){
                    if( //intersect(lines.get(i), lines.get(j))
                            doIntersect(lines.get(i).a,lines.get(i).b, lines.get(j).a, lines.get(j).b)
                            ){
                        p[i][j] = true;
                        p[j][i] = true;
                    }
                }
            }

            //Floyd-Warshall
            for (int k = 0; k < n; k++){
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        if (i != j&&p[i][k] && p[k][j] && !p[i][j]){
                            p[i][j] = true;
                        }
                    }
                }
            }


            while( true ){
                int[] tks = lineToIntArray(br.readLine());
                if(tks[0]==0 && tks[1]==0) break;
                if(p[tks[0]-1][tks[1]-1]) System.out.println("CONNECTED");
                else System.out.println("NOT CONNECTED");
            }
            if(T!=0) System.out.println();


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

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }

    static class Point
    {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public String toString() { return String.format("(%.03f,%.03f)", x,y); }
    }

    static class Line{
        public Point a;
        public Point b;

        public Line(Point aa, Point bb){
            a = aa;
            b = bb;
        }

    }

    static double cross(Point a, Point b, Point c)
    {
        double y1 = b.y-a.y, y2 = c.y-b.y;
        double x1 = b.x-a.x, x2 = c.x-b.x;
        return x1*y2-x2*y1;
    }
    static boolean intersect1D(double a1, double a2, double b1, double b2)
    {
        double mina = a1;
        double maxa = a2;
        if (a1 > a2) {
            mina = a2;
            maxa = a1;
        }
        double minb = b1;
        double maxb= b2;
        if (b1 > b2) {
            minb = b2;
            maxb = b1;
        }
        return Math.max(mina,minb) <= Math.min(maxa, maxb);
    }

    static boolean intersect(Line L1, Line L2)
    {
        return intersect1D(L1.a.x, L1.b.x, L2.a.x, L2.b.x) //x 軸投影相交
                && intersect1D(L1.a.y, L1.b.y, L2.a.y, L2.b.y) //y 軸投影相交
                && cross(L1.a, L1.b, L2.a)*cross(L1.a, L1.b, L2.b) <= 0
                && cross(L2.a, L2.b, L1.a)*cross(L2.a, L2.b, L1.b) <= 0;
    }

    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    static boolean onSegment(Point p, Point q, Point r)
    {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
    static int orientation(Point p, Point q, Point r)
    {
        // See http://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;  // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

}
