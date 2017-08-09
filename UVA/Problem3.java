/**
 * Created by hanmingzeng on 3/27/17.
 */
public class Problem3 {

    public static void main(String[] args){

        //TODO: main

    }

    public static boolean isMarkovMatrix(double[][] m){
        int rnum = m.length;
        int cnum=0;
        if(rnum>0) cnum = m[0].length;

        for(int i=0; i<cnum; i++){

            double sum=0;

            for(int j=0; j<rnum; j++){
                if(m[j][i] <0) return false;
                sum+=m[j][i];
            }

            if(sum!=1) return false;
        }


        return true;

    }
}
