import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class ShellSort {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Cases = Integer.parseInt(br.readLine());

        for(int c=0; c<Cases; ++c){

            int N = Integer.parseInt(br.readLine());
            String[] unsorted = new String[N];

            for(int i=0; i<N; ++i){

                unsorted[i] = br.readLine();

            }

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            HashMap<Integer, String> indexMap = new HashMap< Integer, String>();

            for(int i=0; i<N; ++i){
                String t = br.readLine();
                map.put(t, i);
                indexMap.put(i,t);
            }

            int[] unsortedIndex = new int[N];

            ArrayList<Integer> problem = new ArrayList<Integer>(); //problematic index that is not in order


            int remaining = N-1;
            for(int i=N-1; i>=0; --i){
                unsortedIndex[i] = map.get(unsorted[i]);

                if(unsortedIndex[i] != remaining) {
                    problem.add(unsortedIndex[i]);
                }else{
                    --remaining;
                }
            }

            Collections.sort(problem);

            if(problem.size()>0){
                int min = problem.get(0);

                for(int i=0; i<N; ++i){

                    if(unsortedIndex[i]<min){
                        problem.add(i);
                    }

                }

                Collections.sort(problem);

                int len = problem.size();

                for(int i=len-1; i>=0; --i){

                    System.out.println( indexMap.get(problem.get(i)) );

                }

                System.out.println();

            }else{
                System.out.println();
            }


        }


    }


}