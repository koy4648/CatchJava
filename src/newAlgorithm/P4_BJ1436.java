package newAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P4_BJ1436 {
    public static void main(String[] args) throws IOException {
        //1부터 세어가면서 666이 이어져 있으면 n으로 처리한다... 매번 체크
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer=0;
        int number=0;
        for(int i=666;i<Integer.MAX_VALUE;i++){
            if(Integer.toString(i).contains("666")) {
                number++;
                if(number==n){
                    answer = i;
                    break;
                }
            }
        }//for
        System.out.println(answer);
    }//main
}//class
