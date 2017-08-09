/**
 * Created by hanmingzeng on 5/9/17.
 */
public class DPExample {
}

/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.*;

public class HardDPExample {

    private static int[] dp;
    private static int[] trips;

    private static int travelTime;
    private static int[] arrivalTimes;
    private static int maxCars;

    private static int best(int end) {
        if (dp[end] != 0) return dp[end];
        if (end + 1 <= maxCars) {
            dp[end] = arrivalTimes[end] + travelTime;
            trips[end] = 1;
            return arrivalTimes[end] + travelTime;
        }
        int min = Integer.MAX_VALUE;
        int numtrips = 0;
        for (int cut = end - 1; cut >= end - maxCars && cut >= 0; cut--) {
            int endTime = best(cut);
            if (endTime + travelTime <= arrivalTimes[end])
                endTime = arrivalTimes[end] + travelTime;
            else
                endTime += 2 * travelTime;
            min = Math.min(endTime, min);
            numtrips = 1 + trips[cut];
        }
        trips[end] = numtrips;
        dp[end] = min;
        return min;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            String[] s = in.readLine().split("\\s+");
            maxCars = Integer.parseInt(s[0]);
            travelTime = Integer.parseInt(s[1]);
            int numCars = Integer.parseInt(s[2]);

            arrivalTimes = new int[numCars];
            for (int car = 0; car < numCars; car++) {
                arrivalTimes[car] = Integer.parseInt(in.readLine());
            }

            dp = new int[numCars];
            trips = new int[numCars];

            int endTime = best(numCars - 1);
            int numTrips = trips[numCars - 1];

            System.out.println(endTime + " " + numTrips);
        }
    }

}

import java.util.*;

public class HarderDPExample {

    private static ArrayList<Integer> cars;
    private static String[][] decisions;
    // lookup[carNum][used space on starboard side]
    private static int[][] lookup;
    private static int ferryLength;

    private static int max(int currCar, int usedPort, int usedStar) {
        if (currCar >= cars.size()) return 0;
        if (lookup[currCar][usedStar] != 0) return lookup[currCar][usedStar];
        if (cars.get(currCar) + usedPort > ferryLength && cars.get(currCar) + usedStar > ferryLength) {
            return 0;
        }
        if (cars.get(currCar) + usedPort > ferryLength) {
            decisions[currCar][usedStar] = "starboard";
            lookup[currCar][usedStar] = 1 + max(currCar + 1, usedPort, usedStar + cars.get(currCar));
            return lookup[currCar][usedStar];
        }
        if (cars.get(currCar) + usedStar > ferryLength) {
            decisions[currCar][usedStar] = "port";
            lookup[currCar][usedStar] = 1 + max(currCar + 1, usedPort + cars.get(currCar), usedStar);
            return lookup[currCar][usedStar];
        }

        int bestPort = 1 + max(currCar + 1, usedPort + cars.get(currCar), usedStar);
        int bestStar = 1 + max(currCar + 1, usedPort, usedStar + cars.get(currCar));

        if (bestStar > bestPort) {
            lookup[currCar][usedStar] = bestStar;
            decisions[currCar][usedStar] = "starboard";
            return bestStar;
        } else {
            lookup[currCar][usedStar] = bestPort;
            decisions[currCar][usedStar] = "port";
            return bestPort;
        }
    }

    private static void doDP() {
        lookup = new int[cars.size()][ferryLength + 1];
        decisions = new String[cars.size()][ferryLength + 1];

        System.out.println(max(0, 0, 0));

        int curr = 0;
        for (int i = 0; i < cars.size(); i++) {
            String s = decisions[i][curr];
            if (s == null) break;
            System.out.println(s);
            if (s.equals("starboard")) {
                curr += cars.get(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        for (int c = 0; c < cases; c++) {
            ferryLength = in.nextInt() * 100;
            cars = new ArrayList<>();
            while (true) {
                int car_length = in.nextInt();
                if (car_length == 0) {
                    doDP();
                    if (c < cases - 1) System.out.println();
                    break;
                }
                cars.add(car_length);
            }
        }
    }

}
 */