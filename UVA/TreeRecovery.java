import java.io.*;
import java.util.*;
public class TreeRecovery {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);



        String line;
        while( (line=br.readLine())!=null){
            String[] tks = line.split("\\s+");
            String preorder = tks[0];
            String inorder = tks[1];

            Node tree = buildTree(preorder, inorder);
            postOrder(tree);

            System.out.println();

        }

    }

    static Node buildTree(String p, String i){

        if(p.length()==0) return null;

        Node root = new Node(p.charAt(0));

        int rootIndexInOrder = i.indexOf(p.charAt(0));
        String leftInorder = i.substring(0, rootIndexInOrder);
        String rightInforder = i.substring(rootIndexInOrder+1);

        int leftLen = leftInorder.length();
        int rightLen = rightInforder.length();

        String leftPre = p.substring(1, leftLen+1);
        String rightPre = p.substring(leftLen+1);

        root.left = buildTree(leftPre, leftInorder);
        root.right = buildTree(rightPre, rightInforder);

        return root;

    }

    static void postOrder(Node n){

        if(n==null) return;

        postOrder(n.left);
        postOrder(n.right);

        System.out.print(n.name);

    }

    static class Node{
        Node left;
        Node right;
        char name;

        Node(char m){
            this.name = m;
        }

    }

    public static int[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }

}
