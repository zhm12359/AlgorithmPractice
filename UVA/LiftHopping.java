import java.io.*;
import java.util.*;
public class LiftHopping {

    static BufferedReader br;
    static ArrayList<Pair>[] graph;
    static int[][] dist;
    static ArrayList<Pair> pairs;
    static int[] time;
    static TreeMap<Integer, ArrayList<Integer>> eMap;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        String line;
        while( (line=br.readLine())!=null ){

            int[] tks = lineToIntArray(line);
            int n = tks[0];
            int k = tks[1];
            time = lineToIntArray(br.readLine());
            eMap = new TreeMap<>();
            graph= new ArrayList[100];
            pairs = new ArrayList<>();

            for(int i=0; i<100; i++) graph[i]=new ArrayList<>();

            for(int i=0; i<n; i++){
                int[] floors = lineToIntArray(br.readLine());
                eMap.put(i, new ArrayList<>());
                for(int j=0; j<floors.length; j++){
                    eMap.get(i).add(floors[j]);
                    Pair cur = new Pair(i, floors[j]);
                    cur.index = j;
                    pairs.add(cur);
                    graph[floors[j]].add(new Pair(i, floors[j]));
                    if(j>0) graph[floors[j]].add(new Pair(i, floors[j-1]));
                    if(j<floors.length-1) graph[floors[j]].add(new Pair(i, floors[j+1]));

                }

            }

            dijkstra();

            //for(int i=0; i <eMap.size(); i++) System.out.println(Arrays.toString(dist[i]));
            //System.out.println(eMap);

            int min = Integer.MAX_VALUE;

            for(int i=0; i<n; i++){
                min = Math.min(min, dist[i][k]);
            }
            if(min != Integer.MAX_VALUE) System.out.println(min);
            else System.out.println("IMPOSSIBLE");

        }
    }

    static void dijkstra(){

        dist = new int[eMap.size()][100];
        for(int i=0; i<eMap.size(); i++) {
            for(int j=0; j<100; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Pair sourcePair = graph[0].get(0); // 0th floor first element is defly 0...
        dist[sourcePair.elevator][0] = 0; // elevator, starting floor
        Node s = new Node(0, sourcePair);

        PriorityQueue<Node> minQ = new PriorityQueue<>();
        minQ.offer(s);

        while(!minQ.isEmpty()){
            Node node = minQ.poll();
            int cost = node.cost;
            int floor = node.p.floor;
            int elevator = node.p.elevator;

            for (int i = 0; i < graph[floor].size(); i++) {
                int transfer = 0;
                Pair v = graph[floor].get(i);
                if(v.elevator == elevator || v.floor == floor){ // can only move if it's same elevator or same floor
                    if(v.elevator != elevator) transfer = 60;
                    int temp = dist[elevator][floor] + time[elevator] *Math.abs(floor - v.floor) + transfer;
                    if(temp < dist[v.elevator][v.floor] ){
                        dist[v.elevator][v.floor] = temp;
                        minQ.add(new Node(temp, v));
                    }
                }

            }

        }


//        dist = new int[eMap.size()][100];
//        for(int i=0; i<eMap.size(); i++) {
//            for(int j=0; j<100; j++){
//                dist[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        for(int e: eMap.keySet()){
//            if(eMap.get(e).contains(source)){
//                dist[e][source]=0;
//            }
//        }
//
//        PriorityQueue<Pair> minQ = new PriorityQueue<>();
//        for(Pair p: pairs){
//            minQ.offer(p);
//        }
//
//        while(!minQ.isEmpty()){
//            Pair v = minQ.poll();
//            int curFloor = v.floor;
//            int curEle = v.elevator;
//            int index= v.index;
//
//            ArrayList<Integer> transfer = new ArrayList<>();
//
//            if(index>0){
//                int leftFloor = eMap.get(curEle).get(index-1);
//                if(dist[curEle][leftFloor]> dist[curEle][curFloor] + time[curEle] ){
//                    dist[curEle][leftFloor] = dist[curEle][curFloor] + time[curEle];
//                }
//            }
//            if(index < eMap.get(curEle).size()-1 ){
//                int rightFloor = eMap.get(curEle).get(index+1);
//                if(dist[curEle][rightFloor]> dist[curEle][curFloor] + time[curEle] ){
//                    dist[curEle][rightFloor] = dist[curEle][curFloor] + time[curEle];
//                }
//            }
//
//            for(int w: eMap.keySet()){
//                if(w!=curEle && eMap.get(w).contains(curFloor)){
//                    if(dist[w][curFloor]> dist[v.elevator][curFloor] + 60){
//                        dist[w][curFloor] = dist[v.elevator][curFloor] + 60;
//                    }
//                }
//            }
//        }

    }

    static class Pair implements Comparable<Pair>{
        int elevator;
        int floor;
        int index;

        Pair(int e, int f){
            this.elevator = e;
            this.floor = f;
        }

        public int compareTo(Pair other){
            return Integer.compare(this.floor, other.floor);
        }
    }

    static class Node implements Comparable<Node>{
        int cost;
        Pair p;
        Node(int c, Pair p1){
            this.p = p1;
            this.cost = c;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
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
}