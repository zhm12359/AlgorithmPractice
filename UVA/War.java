import java.io.BufferedReader;
import java.io.InputStreamReader;

//War
public class War {

    public static int[] War;
    public static int total;

    public static void main(String[] args) throws Exception{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(in.readLine());
        init(total);

        int c,x,y;
        String[] line;

        while(true){
            line = in.readLine().split(" ");
            c = Integer.parseInt(line[0]);
            x = Integer.parseInt(line[1]);
            y = Integer.parseInt(line[2]);
            if(c==0 && x==0 && y==0) break;

            if(c==1) setFriend(x,y);
            else if(c==2) setEnemies(x,y);
            else if(c==3) areFriends(x,y);
            else if(c==4) areEnemies(x,y);
        }

    }

    public static void init(int total){

        War = new int[total*2+1];

        for(int i=0; i<War.length; ++i){
            War[i] = i; //init: everyone represents themselves
        }
    }

    public static int find(int x){
        if(War[x]!=x){
            War[x] = find(War[x]);
        }
        return War[x];
    }

    public static void setFriend(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        int xE = find(x+total);
        int yE = find(y+total);

        if(xRep == yE || yRep ==xE){ // if the xRep is the rep of y's enemies (and vice versa)
            System.out.println(-1);
        }else{
            War[yRep] = xRep;
            War[yE] = xE;

        }

    }

    public static void areFriends(int x, int y){
        int xRep = find(x);
        int yRep = find(y);
        System.out.println(xRep==yRep? 1:0);
    }

    public static void areEnemies(int x, int y){
        int xRep = find(x);
        int yRep = find(y);
        int xE = find(x+total);
        int yE = find(y+total);

        System.out.println(xRep==yE||yRep==xE ? 1 : 0);
    }

    public static void setEnemies(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        int xE = find(x+total);
        int yE = find(y+total);

        if(xRep == yRep ){ // if the xRep is the rep of y's enemies (and vice versa)
            System.out.println(-1);
        }else{
            War[xE] = yRep;
            War[yE] = xRep;
        }

    }

}



/*

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class War {

    public static int[] War, rank;
    public static Set<Integer>[] enemy;

    public static void main(String[] args) throws Exception{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(in.readLine());
        init(total);

        int c,x,y;
        String[] line;

        while(true){
            line = in.readLine().split(" ");
            c = Integer.parseInt(line[0]);
            x = Integer.parseInt(line[1]);
            y = Integer.parseInt(line[2]);
            if(c==0 && x==0 && y==0) break;

            if(c==1){
                if(areEnemies(x,y)) System.out.println(-1);
                else union(x,y);
            }else if(c==2){
                if(!setEnemies(x,y)) System.out.println(-1);
            }else if(c==3){
                System.out.println(areFriends(x,y)? 1: 0);
            }else if(c==4){
                System.out.println(areEnemies(x,y)? 1: 0);
            }
        }

    }

    public static void init(int total){

        War = new int[total];
        rank = new int[total];
        enemy = new HashSet[total];

        for(int i=0; i<War.length; ++i){
            War[i] = i; //init: everyone represents themselves
            rank[i] =1;
            enemy[i] = new HashSet<Integer>();
        }
    }

    public static int find(int x){
        if(War[x]!=x){
            War[x] = find(War[x]);
        }
        return War[x];
    }

    public static void union(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        if(xRep!=yRep){
            if(rank[xRep]>=rank[yRep]){
                War[yRep] = xRep;
            }
            // my friend's enemy is my enemy
            for (int i : enemy[yRep]) {
                enemy[xRep].add(i);
            }
        }else{
            War[xRep] = yRep;
            for (int i : enemy[xRep]) {
                enemy[yRep].add(i);
            }
        }
        if(rank[xRep]==rank[yRep]){
            rank[xRep]++;
        }

    }

    public static boolean areFriends(int x, int y){
        int xRep = find(x);
        int yRep = find(y);
        return xRep==yRep;
    }

    public static boolean areEnemies(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        if(enemy[xRep].contains(yRep)){
            return true;
        }

        return false;
    }

    public static boolean setEnemies(int x, int y){
        int xRep = find(x);
        int yRep = find(y);

        if(xRep==yRep){
            return false;
        }
        enemy[xRep].add(yRep);
        enemy[yRep].add(xRep);

        // enemy's enemy is a friend
        for(int i: enemy[xRep]){
            union(y,i);
        }

        for(int i: enemy[yRep]){
            union(x,i);
        }
        return true;

    }

}






public class War {

    public static void main(String[] args) throws Exception{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());


        HashMap<Integer, Integer> war = new HashMap<Integer, Integer>();

        int c,x,y;

        while(sc.hasNextLine()){
            c = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();

            if(c ==0 && x==0 && y==0) break;

            switch (c){
                case 1:
                    if(war.containsKey(x) && war.containsKey(y)){

                        if(war.get(x)!= war.get(y)) System.out.println(-1);

                    }else if(war.containsKey(x)){
                        war.put(y,war.get(x));
                    }else if(war.containsKey(y)){
                        war.put(x, war.get(y));
                    }else{
                        war.put(x,1);
                        war.put(y,1);
                    }
                    break;
                case 2:
                    if(war.containsKey(x) && war.containsKey(y)){

                        if(war.get(x)== war.get(y)) System.out.println(-1);

                    }else if(war.containsKey(x)){

                        int xpo = war.get(x);
                        if(xpo==1) war.put(y,2);
                        else war.put(y, 1);

                    }else if(war.containsKey(y)){

                        int ypo = war.get(y);
                        if(ypo==1) war.put(x,2);
                        else war.put(x,1);

                    }else{
                        war.put(x,1);
                        war.put(y,2);
                    }
                    break;

                case 3:
                    if( war.get(x) == war.get(y) && war.get(x)!=null && war.get(y)!=null ) System.out.println(1);
                    else System.out.println(0);

                    break;

                case 4:
                    if( war.get(x) == war.get(y) && war.get(x)!=null && war.get(y)!=null ) System.out.println(0);
                    else if(war.get(x) != war.get(y) && war.get(x)!=null && war.get(y)!=null ) System.out.println();
                    break;
                default:
                    break;
            }

        }



    }

}
 */