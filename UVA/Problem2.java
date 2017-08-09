import java.util.*;

public class Problem2 {

    public static void main(String[] args){

// declaration

        int num;

        Scanner kb = new Scanner(System.in);


        //get user input to populate cities

        System.out.print("How many cities do you want to input: ");

        num = kb.nextInt();

        City [] cities = new City [num];



        for (int i = 0; i < num; i++){


            System.out.print("Enter the x-coordinate of city " + (i+1) + ": ");



            double x = kb.nextDouble();



            System.out.print("Enter the y-coordinate of city " + (i+1) + ": ");

            double y = kb.nextDouble();

            cities[i] = new City(x,y);

        }

        kb.close();



        City centralCity = getCentralCity(cities);

        System.out.println("The central city is: (" + centralCity.x + ", " + centralCity.y + ")");

        System.out.println("The minimun total distance is: " + centralCity.distSum);



    }



    public static City getCentralCity(City[] cities){



        double[]  dist = new double[cities.length];



        for (int i = 0; i < cities.length; i++){

            for (int j = 0; j < cities.length; j++){

                City cityi = cities[i];

                City cityj = cities[j];

                dist[i] += dist(cityi, cityj);

            }

        }



        int minIndex = -1;

        double min = Double.MAX_VALUE;



        for (int i = 0; i < cities.length; i++){

            if (min > dist[i]){

                min = dist[i];

                minIndex = i;

            }

            cities[i].distSum = dist[i];

        }

        return cities[minIndex];

    }



    public static class City{

        double x;

        double y;

        double distSum;

        public City(double x, double y){
            this.x=x;
            this.y=y;
        }

    }



    public static double dist(City a, City b){



        double x1 = a.x;

        double y1 = a.y;

        double x2 = b.x;

        double y2 = b.y;




        return Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );

    }


}