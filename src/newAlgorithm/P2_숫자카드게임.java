package newAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2_숫자카드게임 {
    public static void main(String[] args) throws IOException {
        //행별로 가장 작은 숫자 찾고, 최소 숫자끼리 비교
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;

        int [][]map = new int[n][m];
        for(int i=0;i<n;i++){
            int min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] =  Integer.parseInt(st.nextToken());
                //min값 구하기
                min = Math.min(min, map[i][j]);
            }
            answer = Math.max(answer,min);
        }

        System.out.println(answer);

    }//main
}//class
