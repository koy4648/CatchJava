package newAlgorithm;

import java.util.*;
import java.io.*;

public class P1_큰수의법칙{
    public static void main(String[] args) throws Exception{
        // sort해서 3번하고 1번하고 3번하고 1번하고
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] list = new int[N];

        int answer=0;
        int max=Integer.MIN_VALUE;
        int secondMax=Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            list[i] = Integer.parseInt(st.nextToken());
            if(max<=list[i]){
                secondMax = max;
                max = list[i];
            }
        }//for

//        sort사용
//        Arrays.sort(list);
//        for(int i=0;i<M;i++) {
//            if(i%(K+1)==K) {
//                answer+=list[N-2];
//            }else {
//                answer+=list[N-1];
//            }
//        }

        for(int i=0;i<M;i++) {
            if(i%(K+1)==K) {
                answer+=secondMax;
            }else {
                answer+=max;
            }
        }
        System.out.println(answer);
    }//main
}//class
