//import java.util.Collections;
//import java.util.Scanner;
//import java.util.LinkedList;
//
//
//public class Google {
//
//
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        int num = sc.nextInt();
//        sc.nextLine();
//        String[] line;
//        LinkedList<Website> pq = new LinkedList<>();
//        Website website;
//        int maxP;
//
//        for(int j=1; j<num+1; j++){
//            pq.clear();
//            for(int i=0; i<10; ++i){
//                line = sc.nextLine().split(" ");
//                website = new Website(line[0], Integer.parseInt(line[1]));
//                pq.offer(website);
//            }
//
//            System.out.println("Case #"+j+":");
//            Collections.sort(pq);
//            Collections.reverse(pq);
//            maxP = pq.peek().priority;
//            while(pq.peek().priority == maxP){
//                website = pq.poll();
//                System.out.println(website.url);
//            }
//
//
//        }
//
//    }
//    static class Website implements Comparable<Website>{
//
//        public String url;
//        public int priority;
//
//
//        public Website(String u, int p){
//            this.url = u;
//            this.priority = p;
//        }
//
//        public int compareTo(Website w){
//            return this.priority - w.priority;
//        }
//    }
//}


import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Google {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] line;
        Queue<String> queue = new LinkedList<>();
        int currentMax=-1;
        int priority;

        for(int j=1; j<num+1; j++){
            queue.clear();
            currentMax = -1;
            for(int i=0; i<10; ++i){
                line = sc.nextLine().split(" ");
                priority = Integer.parseInt(line[1]);
                if(currentMax<priority){
                    queue.clear();
                    currentMax = priority;
                    queue.offer(line[0]);
                }else if(currentMax==priority){
                    queue.offer(line[0]);
                }

            }

            System.out.println("Case #"+j+":");

            while(!queue.isEmpty()){
                System.out.println(queue.poll());
            }


        }

    }

}