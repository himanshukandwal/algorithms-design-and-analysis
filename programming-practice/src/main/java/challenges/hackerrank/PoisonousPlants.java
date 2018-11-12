package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Poisonous Plants
 *
 * link: https://www.hackerrank.com/challenges/poisonous-plants/problem
 */
public class PoisonousPlants extends AbstractCustomTestRunner {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve()
    {
        int n = ni();
        int[] a = na(n);
        int[] wall = enumPrevWall(a);
        SegmentTreeRMQ st = new SegmentTreeRMQ(n+1); // building as we go ahead, just allocating the space
        for(int i = 0;i < n;i++){
            if (wall[i] == -1){
                st.update(i, 99999999);
            }else{
                int time = -st.min(wall[i] + 1, i);
                st.update(i, -(time + 1));              // we are storing in negative to get the max (reverse the feature from min to max)
                                                        // it will take (n + 1) step for the plant to get killed.
            }
        }

        int ret = -st.st[1];
        if(ret < 0)ret = 0;
        out.println(ret);
    }

    public static int[] enumPrevWall(int[] a)
    {
        int n = a.length;
        int[] L = new int[n];
        for(int i = 0;i < n;i++){
            L[i] = i-1;
            while(L[i] >= 0 && a[L[i]] >= a[i])L[i] = L[L[i]];
        }
        return L;
    }

    public static class SegmentTreeRMQ {
        public int M, H, N;
        public int[] st;

        public SegmentTreeRMQ(int n)
        {
            N = n;
            M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
            H = M>>>1;
            st = new int[M];
            Arrays.fill(st, 0, M, Integer.MAX_VALUE);
        }

        public SegmentTreeRMQ(int[] a)
        {
            N = a.length;
            M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
            H = M>>>1;
            st = new int[M];
            for(int i = 0;i < N;i++){
                st[H+i] = a[i];
            }
            Arrays.fill(st, H+N, M, Integer.MAX_VALUE);
            for(int i = H-1;i >= 1;i--)propagate(i);
        }

        public void update(int pos, int x)
        {
            st[H+pos] = x;
            for(int i = (H+pos)>>>1;i >= 1;i >>>= 1)propagate(i);
        }

        private void propagate(int i)
        {
            st[i] = Math.min(st[2*i], st[2*i+1]);
        }

        public int min(int l, int r){ return l >= r ? 0 : min(l, r, 0, H, 1);}

        private int min(int l, int r, int cl, int cr, int cur)
        {
            if(l <= cl && cr <= r){
                return st[cur];
            }else{
                int mid = cl+cr>>>1;
                int ret = Integer.MAX_VALUE;
                if(cl < r && l < mid){
                    ret = Math.min(ret, min(l, r, cl, mid, 2*cur));
                }
                if(mid < r && l < cr){
                    ret = Math.min(ret, min(l, r, mid, cr, 2*cur+1));
                }
                return ret;
            }
        }

        public int firstle(int l, int v) {
            int cur = H+l;
            while(true){
                if(st[cur] <= v){
                    if(cur < H){
                        cur = 2*cur;
                    }else{
                        return cur-H;
                    }
                }else{
                    cur++;
                    if((cur&cur-1) == 0)return -1;
                    if((cur&1)==0)cur>>>=1;
                }
            }
        }

        public int lastle(int l, int v) {
            int cur = H+l;
            while(true){
                if(st[cur] <= v){
                    if(cur < H){
                        cur = 2*cur+1;
                    }else{
                        return cur-H;
                    }
                }else{
                    if((cur&cur-1) == 0)return -1;
                    cur--;
                    if((cur&1)==1)cur>>>=1;
                }
            }
        }
    }


    void run() throws Exception
    {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        if(!INPUT.isEmpty())tr(System.currentTimeMillis()-s+"ms");
    }

    public static void main(String[] args) throws Exception { new D().run(); }

    private byte[] inbuf = new byte[1024];
    private int lenbuf = 0, ptrbuf = 0;

    private int readByte()
    {
        if(lenbuf == -1)throw new InputMismatchException();
        if(ptrbuf >= lenbuf){
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
            if(lenbuf <= 0)return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
    private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

    private double nd() { return Double.parseDouble(ns()); }
    private char nc() { return (char)skip(); }

    private String ns()
    {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n)
    {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while(p < n && !(isSpaceChar(b))){
            buf[p++] = (char)b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m)
    {
        char[][] map = new char[n][];
        for(int i = 0;i < n;i++)map[i] = ns(m);
        return map;
    }

    private int[] na(int n)
    {
        int[] a = new int[n];
        for(int i = 0;i < n;i++)a[i] = ni();
        return a;
    }

    private int ni()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) { System.out.println(Arrays.deepToString(o)); }
}
