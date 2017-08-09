import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Sticks {

    public static ArrayList<Integer>[] edgeList;
    public static int [] incomingEdgeCount;
    public static boolean [] visited;
    public static ArrayList<Integer> ans;

    public static void topologySort(int id) {
        if (!visited[id]) {
            visited[id]=true;
            if (edgeList[id]!=null) {
                for (int i=0;i<edgeList[id].size();i++) {
                    topologySort(edgeList[id].get(i));
                }
            }
            ans.add(id);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            if (n==0 && m==0) {
                break;
            }
            edgeList=new ArrayList [n+1]; //1m x 1m array = insufficient memory!
            incomingEdgeCount=new int [n+1];
            HashSet<String> edgeSet=new HashSet<>();

            for (int i=0;i<m;i++) {
                st=new StringTokenizer(br.readLine());
                int src=Integer.parseInt(st.nextToken());
                int dest=Integer.parseInt(st.nextToken());
                String key=src+"_"+dest;
                if (src!=dest && !edgeSet.contains(key)) {
                    //System.out.println(key);
                    //Who knows if the test data gives the same edge for multiple times? :/
                    if (edgeList[src]==null) {
                        edgeList[src]=new ArrayList<>();
                    }
                    edgeList[src].add(dest);
                    incomingEdgeCount[dest]++;
                    edgeSet.add(key);
                }
            }

            boolean hasStartingPoint=false;
            for (int i=1;i<=n && !hasStartingPoint;i++) {
                hasStartingPoint=(incomingEdgeCount[i]==0);
            }

            if (hasStartingPoint) {
                ans=new ArrayList<>();
                visited=new boolean [n+1];
                for (int i=1;i<=n;i++) {
                    if (incomingEdgeCount[i]==0) {
                        topologySort(i);
                    }
                }

                for (int i=ans.size()-1;i>=0;i--) {
                    System.out.println(ans.get(i));
                }
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}