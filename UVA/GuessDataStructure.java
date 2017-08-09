import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Queue;
//
//guess data struct
public class GuessDataStructure {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num,c,x;
        boolean isStack = true;
        boolean isQueue = true;
        boolean isPriorityQueue = true;

        Queue<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());


        while(sc.hasNext()){

            num = sc.nextInt();
            stack.clear();
            queue.clear();
            maxHeap.clear();
            isStack = true;
            isQueue = true;
            isPriorityQueue = true;

            for(int i=0; i<num; i++){
                c = sc.nextInt();
                x = sc.nextInt();
                if(c==1) {
                    queue.add(x);
                    stack.push(x);
                    maxHeap.offer(x);
                } else if(c==2){

                    if(isStack){
                        if(stack.isEmpty()){
                            isStack = false;
                        }else{
                            if(stack.pop()!=x){
                                isStack = false;
                            }
                        }
                    }

                    if(isQueue){
                        if(queue.isEmpty()){
                            isQueue = false;
                        }else{
                            if(queue.poll()!=x){
                                isQueue = false;
                            }
                        }
                    }

                    if(isPriorityQueue){
                        if(maxHeap.isEmpty()){
                            isPriorityQueue = false;
                        }else{
                            if(maxHeap.poll()!=x){
                                isPriorityQueue = false;
                            }
                        }
                    }
                }
            }
            if(!isStack && !isPriorityQueue && !isQueue){
                System.out.println("impossible");
            }else if(isStack && !isPriorityQueue && !isQueue){
                System.out.println("stack");
            }else if(isQueue && !isStack && !isPriorityQueue){
                System.out.println("queue");
            }else if(isPriorityQueue && !isQueue && !isStack){
                System.out.println("priority queue");
            }else{
                System.out.println("not sure");
            }


        }

    }

}


//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.Scanner;
//import java.util.Stack;
//
//public class GuessDataStructure {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        boolean q, st, pq;
//        Queue<Integer> queue = new LinkedList<Integer>();
//        PriorityQueue<Integer> priority = new PriorityQueue<Integer>();
//        Stack<Integer> stack = new Stack<Integer>();
//        StringBuilder out = new StringBuilder("");
//
//        while (sc.hasNext()) {
//            int n = sc.nextInt();
//            q = st = pq = true;
//            stack.clear();
//            queue.clear();
//            priority.clear();
//
//            for (int i = 0; i < n; i++) {
//                int com = sc.nextInt();
//                int val = sc.nextInt();
//
//                if (com == 1) {
//                    stack.push(val);
//                    queue.add(val);
//                    priority.add(-val);
//                } else {
//                    if (st) {
//                        if (stack.isEmpty()) {
//                            st = false;
//                        } else {
//                            if (stack.pop() != val) {
//                                st = false;
//                            }
//                        }
//                    }
//                    if (q) {
//                        if (queue.isEmpty()) {
//                            q = false;
//                        } else {
//                            if (queue.poll() != val) {
//                                q = false;
//                            }
//                        }
//                    }
//                    if (pq) {
//                        if (priority.isEmpty()) {
//                            pq = false;
//                        } else {
//                            if (-1 * priority.poll() != val) {
//                                pq = false;
//                            }
//                        }
//                    }
//                }
//            }
//            if (!q && !st && !pq)
//                out.append("impossible\n");
//            else if (q && !(st || pq))
//                out.append("queue\n");
//            else if (st && !(q || pq))
//                out.append("stack\n");
//            else if (pq && !(st || q))
//                out.append("priority queue\n");
//            else
//                out.append("not sure\n");
//        }
//        System.out.print(out);
//    }
//}