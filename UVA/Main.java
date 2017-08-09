// A
//import java.util.Scanner;
//
//public class Main {
//
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//        int a,b;
//        while(sc.hasNext()){
//            a = sc.nextInt();
//            b = sc.nextInt();
//            System.out.println(a+b);
//        }
//    }
//
//}


// B
//import java.util.Scanner;
//
//public class Main {
//
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//        sc.nextInt();
//        int a,b;
//        while(sc.hasNext()){
//            a = sc.nextInt();
//            b = sc.nextInt();
//            System.out.println(a+b);
//        }
//    }
//
//}

// C
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//
//        int a,b;
//        while(sc.hasNextLine()){
//            a = sc.nextInt();
//            b = sc.nextInt();
//            if(a == 0 && b==0 ){
//                break;
//            }
//            System.out.println(a+b);
//        }
//    }
//
//}

//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//
//        while(sc.hasNext()){
//
//            int num= sc.nextInt();
//            int sum=0;
//
//
//            for(int i=0; i<num; ++i){
//                sum+=sc.nextInt();
//            }
//
//            System.out.println(sum);
//        }
//    }
//
//}

//import java.util.Scanner;
//
//public class Main {
//
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//        int a,b;
//        while(sc.hasNext()){
//            a = sc.nextInt();
//            b = sc.nextInt();
//            System.out.println(a+b);
//            System.out.println();
//        }
//    }
//}

//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//        int lines = sc.nextInt();
//        sc.nextLine();
//
//        for(int j=0; j<lines; ++j){
//
//            int num= sc.nextInt();
//            int sum=0;
//
//
//            for(int i=0; i<num; ++i){
//                sum+=sc.nextInt();
//            }
//
//            System.out.println(sum);
//            if(j<lines-1) System.out.println();
//        }
//    }
//
//}

////import java.util.Scanner;
////import java.util.ArrayList;
////
// //War
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        sc.nextInt();
//        sc.nextLine();
//        int c,x,y;
//        ArrayList<Integer> country1 = new ArrayList<Integer>();
//        ArrayList<Integer> country2 = new ArrayList<Integer>();
//
//
//
//        while(sc.hasNextLine()){
//
//            c = sc.nextInt();
//            x = sc.nextInt();
//            y = sc.nextInt();
//
//            if(c ==0 && x==0 && y==0) break;
//
//            switch (c){
//                case 1:
//                    //set freinds
//                    if(country1.contains(x)){
//                        if(country2.contains(y)) System.out.println(-1);
//                        else if(!country1.contains(y)) country1.add(y);
//                    }else if(country2.contains(x)){
//                        if(country1.contains(y)) System.out.println(-1);
//                        else if(!country2.contains(y)) country1.add(y);
//                    }else{
//                        if(country1.contains(y)) country1.add(x);
//                        else if(country2.contains(x)) country2.add(x);
//                        else{
//                            country1.add(x);
//                            country1.add(y);
//                        }
//                    }
//                    break;
//                case 2:
//                    //set enemies
//                    if(country1.contains(x)){
//                        if(country1.contains(y)) System.out.println(-1);
//                        else if(!country2.contains(y)) country2.add(y);
//                    }else if(country2.contains(x)){
//                        if(country2.contains(y)) System.out.println(-1);
//                        else if(!country1.contains(y)) country1.add(y);
//                    }else{
//                        if(country1.contains(y)) country2.add(x);
//                        else if(country2.contains(x)) country1.add(x);
//                        else{
//                            country1.add(x);
//                            country2.add(y);
//                        }
//                    }
//                    break;
//                case 3:
//                    if((country1.contains(x)&&country1.contains(y)) || (country2.contains(x) && country2.contains(y)) ){
//                        System.out.println(1);
//                    }else{
//                        System.out.println(0);
//                    }
//                    break;
//                case 4:
//                    if((country1.contains(x)&&country2.contains(y)) || (country2.contains(x) && country1.contains(y)) ){
//                        System.out.println(1);
//                    }else{
//                        System.out.println(0);
//                    }
//                    break;
//                default:
//                    break;
//            }
//
//
//        }
//
//    }
//
//}
//
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Stack;
//import java.util.PriorityQueue;
//import java.util.Collections;
////
////guess data struct
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        int num = sc.nextInt();
//        sc.nextLine();
//        int c,x;
//        ArrayList<Integer> queue = new ArrayList<Integer>();
//        ArrayList<Integer> out = new ArrayList<Integer>();
//        Stack<Integer> stack = new Stack<Integer>();
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());
//
//
//
//        while(sc.hasNextLine()){
//
//            for(int i=0; i<num; i++){
//                c = sc.nextInt();
//                x = sc.nextInt();
//                if(c==1) {
//                    queue.add(x);
//                    stack.push(x);
//                    maxHeap.offer(x);
//                }
//                if(c==2) out.add(x);
//            }
//            if(isStack(stack,out) ){
//                if(!isPriorityQueue(maxHeap,out)) System.out.println("stack");
//                else System.out.println("not sure");
//            }else if(isQueue(queue, out) ) {
//                if(!isPriorityQueue(maxHeap,out)) System.out.println("queue");
//                else System.out.println("not sure");
//            }else if(isPriorityQueue(maxHeap, out)){
//                System.out.println("priority queue");
//            }else{
//                System.out.println("impossible");
//            }
//            if(sc.hasNextLine()){
//                sc.nextLine();
//                num = sc.nextInt();
//                queue.clear();
//                stack.clear();
//                maxHeap.clear();
//                out.clear();
//            }
//
//        }
//
//    }
//
//    public static boolean isStack(Stack<Integer> stack, ArrayList<Integer> out){
//        for(int i=0; i<out.size(); i++){
//            if(stack.pop()!=out.get(i)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isQueue(ArrayList<Integer> queue, ArrayList<Integer> out){
//        int len = out.size();
//        int inbound, outbound;
//        for(int i=0; i<len; i++){
//            outbound = out.get(i);
//            inbound = queue.get(i);
//
//            if(outbound!=inbound){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isPriorityQueue(PriorityQueue<Integer> maxHeap, ArrayList<Integer> out){
//
//        for(int i=0; i<out.size(); ++i){
//            if(maxHeap.poll() != out.get(i)){
//                return false;
//            }
//        }
//        return true;
//
//    }
//
//
//
//}

//
//import java.util.Scanner;
//import java.util.Stack;
//public class Main {
//
//
//    public static void main(String[] args){
//
//        Scanner sc = new Scanner(System.in);
//        int num;
//        String[] a,b;
//        Stack<Integer> ones = new Stack<Integer>();
//
//
//        while(sc.hasNext()){
//            num = sc.nextInt();
//            if(num==0) break;
//            String[] bArray = Integer.toBinaryString(num).split("");
//            a = bArray.clone();
//            for(int j=0; j<a.length; j++){
//                a[j] = "0";
//            }
//            b=a.clone();
//
//
//            for(int i=0; i<bArray.length; i++){
//                if(bArray[i].equals("1")){
//                    ones.push(i);
//                }
//            }
//
//            int k,m;
//            while(!ones.empty()){
//                k = ones.pop();
//                a[k] = "1";
//                if(!ones.empty()){
//                    m=ones.pop();
//                    b[m] = "1";
//                }
//            }
//
//            int ad = Integer.parseInt(String.join("",a),2);
//            int bd = Integer.parseInt(String.join("",b),2);
//
//            System.out.println(ad+" " +bd);
//
//        }
//    }
//
//}


//one two six ten
//four five nine
//three seven eight
//
//import java.util.Scanner;
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String n;
//
//        while(sc.hasNext()){
//            n= sc.next();
//
//            if(n.equals("END")) break;
//            System.out.println(reduce(n));
//
//        }
//
//    }
//
//    public static int reduce(String a){
//        if(a.equals("1")) return 1;
//        if(a.length()==1) return 2;
//
//        String next= String.valueOf(a.length());
//
//        return reduce(next) + 1;
//
//    }
//
//    }