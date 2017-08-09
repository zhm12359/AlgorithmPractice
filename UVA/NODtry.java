import java.io.*;
import java.util.*;

public class NODtry
{
    static ArrayList<Integer> sec;
    public static void main(String... args)throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int cases=0;

        sec=new ArrayList<Integer>();
        sec.add(1);
        int x=0;

        for(;x<=1000000;)
        {
            int t = sec.get(sec.size()-1);
            x=NOD(t) +t;
            sec.add(x);
        }

        int n = new Integer(in.readLine());

        while(n-->0)
        {
            StringTokenizer tk = new StringTokenizer(in.readLine());

            int a = new Integer(tk.nextToken());
            int b = new Integer(tk.nextToken());
            out.println("Case "+(++cases)+": "+((bs(b, true)-bs(a, false))+1));
            out.flush();
        }
    }
    static int bs(int n, boolean b)
    {
        int ini = 0;
        int fin = sec.size()-1;
        int m=0;

        while(ini <= fin)
        {
            m = (ini + fin) / 2;

            if(sec.get(m)==n)
                return m;
            else if(sec.get(m)<n)
                ini = m+1;
            else
                fin = m-1;
        }
        if(b)
            return sec.get(m)>n?m-1:m;
        else
            return sec.get(m)<n?m+1:m;
    }
    static int NOD(int n)
    {
        if(n==1) return 1;
        if(n==2) return 2;

        int root =(int) Math.sqrt(n);
        int count=0;

        for(int i=2;i<=root;++i)
            if(n%i==0)
            {
                if(n/i == i)
                    ++count;
                else
                    count+=2;
            }

        return (count)+2;
    }
}