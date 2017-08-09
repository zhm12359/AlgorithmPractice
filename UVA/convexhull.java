import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Point;
import java.lang.*;
import java.lang.Math;
import java.util.*;

/**
 * Created by jennyliang on 4/28/17.
 */
public class convexhull {

/*
1
5
1 1 Y
1 -1 Y
0 0 N
-1 -1 Y
-1 1 Y
*/


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            int S = Integer.parseInt(br.readLine());

            ArrayList<Point> points = new ArrayList<Point>();

            for (int j = 0; j < S;  j++) {

                String[] line = br.readLine().split("\\s+");

                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);
                String YN = line[2];

                if (YN.equals("Y")){
                    Point e = new Point(x, y);
                    points.add(e);
                }
            }

            // find average of points

            int xTotal = 0;
            int yTotal = 0;
            for (int i = 0; i < points.size(); i++){
                xTotal +=points.get(i).x;
            }
            for (int i = 0; i < points.size(); i++){
                yTotal+=points.get(i).y;
            }

            double xAverage = xTotal/points.size();
            double yAverage = yTotal/points.size();

            double[] arctan = new double[points.size()];

            ArrayList<PointsTan> arr = new ArrayList<>();
            for (int i = 0; i < points.size(); i++){

                double changeX = xAverage - points.get(i).x;
                double changeY = yAverage - points.get(i).y;

                arctan[i] = Math.atan2(changeY, changeX);
                PointsTan pt = new PointsTan(points.get(i), arctan[i]);
                arr.add(pt);

            }


            Collections.sort(arr, new CustomComparatorTan());


            int x = 10000000;
            int y = 10000000;
            int index = 0;
            for (int i =0; i < arr.size(); i++){
                if (arr.get(i).p.x < x){
                    x = arr.get(i).p.x;
                    index = i;
                }
                else if (arr.get(i).p.x == x){
                    if (arr.get(i).p.y < y){
                        y = arr.get(i).p.y;
                        index = i;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            System.out.println(arr.size());
            for (int i = index; i <arr.size(); i++) {
                sb.append(arr.get(i).p.x + " " + arr.get(i).p.y+"\n");
            }
            for (int i = 0; i < index; i++) {
                sb.append(arr.get(i).p.x + " " + arr.get(i).p.y+"\n");
            }

            System.out.println(sb.toString());


        }
    }

    public static class CustomComparatorTan implements Comparator<PointsTan> {
        @Override
        public int compare(PointsTan a1, PointsTan a2) {
            if (a1.arctan - a2.arctan < 0){
                return -1;
            }else if (a1.arctan - a2.arctan  == 0){
                return 0;
            }
            else{
                return 1;
            }
        }
    }


    public static class PointsTan{
        Point p;
        double arctan;

        public PointsTan(Point p, double arctan){
            this.p = p;
            this.arctan = arctan;
        }
    }


}