/**
 * Created by hanmingzeng on 3/27/17.
 */
public class Problem4 {
    static int[][] matrix;

    public static void main(String[] args){

        int[][] block = {
                {1,0,1,0,1},
                {1,1,1,0,1},
                {1,0,1,1,1},
                {1,0,1,1,1},
                {1,0,1,1,1}
        };

        matrix = block;

        findLargestBlock(block);


    }

    public static void findLargestBlock(int[][] matrix){

        int [] record = new int [3];
        int size = matrix.length;
        int max = 0;
        int row=-1;
        int col=-1;
        for (int a = 0; a < size; a++){
            for (int b = 0; b < size; b++){
                if (matrix [a][b] == 1){
                    for(int c=a; c<size; c++){
                        for(int d=b; d<size;d++ ){

                            max = Math.max(max, blocSize(a,b,c,d));


                        }
                    }

                }

            }

        }

        System.out.println(max);

    }

    public static int blocSize(int a, int b, int c, int d){


        int size=0;
        for(int i=a; i<=c; i++){
            for(int j=b; j<=d; j++){
                if(matrix[i][j]!=1){
                    return 0;
                }
                size++;
            }
        }

        return size;

    }



}
