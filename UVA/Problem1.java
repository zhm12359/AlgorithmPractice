
public class Problem1 {

    public static void main(String[] args) throws Exception {

        //TODO: main
    }

    public static int[] locateLargest(double[][] a){

        int rnum = a.length;
        int cnum=0;
        if(a.length>0){
            cnum = a[0].length;
        }

        int r=-1;
        int c=-1;
        double max = Double.MIN_VALUE;

        for(int i=0; i<rnum; i++){
            for(int j=0; j<cnum; j++){
                if(a[i][j]>max){
                    max = a[i][j];
                    r = i;
                    c = j;

                }
            }
        }

        int[] res = {r,c};


        return res;
    }
}
