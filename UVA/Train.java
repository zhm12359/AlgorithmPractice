//import java.util.Arrays;
//import java.util.Scanner;
//
///**
// * Created by hanmingzeng on 1/31/17.
// */
//public class Train {
//
//
//
//        public static void main(String[] args) {
//
//            Scanner sc = new Scanner(System.in);
//            int num = sc.nextInt();
//            sc.nextLine();
//            String[] line;
//            String cur="", prev="";
//            boolean loop = true;
//
//            for(int i=0; i<num; ++i){
//
//                line = sc.nextLine().split(" ");
////                if(line.length==1) {
////                    System.out.println("LOOP");
////                    continue;
////                }
//
//                prev = line[0];
//                loop = true;
//
//
//                if(prev.substring(0,1).equals(line[line.length-1].substring(1))){
//                    System.out.println("NO LOOP");
//                    continue;
//                }
//                for(int j=1; j<line.length; ++j){
//                    cur = line[j];
//                    if(cur.substring(0,1).equals(prev.substring(1))){
//                        loop = false;
//                        break;
//                    }
//                    prev = cur;
//                }
//
//                System.out.println(loop? "LOOP": "NO LOOP");
//
//            }
//
//        }
//
//}


import java.util.Scanner;


public class Train {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] line;
        int m,f;

        for(int i=0; i<num; ++i){
            m = 0;
            f = 0;

            line = sc.nextLine().replace(" ", "").split("");

                if(line.length==2) {
                    System.out.println("NO LOOP");
                    continue;
                }


            for(int j=0; j<line.length; ++j){

                if(line[j].equals("F")) ++f;
                else if(line[j].equals("M")) ++m;

            }

            System.out.println(f==m? "LOOP": "NO LOOP");

        }

    }

}

