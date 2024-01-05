package newAlgorithm;

import java.io.*;
import java.util.*;

public class P3_BJ1018 {
    static int answer, M, N, map[][];

    public static void main(String[] args) throws IOException {
        //M*N 그림 -> 8*8 검흰이 번갈아서 색칠. 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        //입력값 받아서 그리기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            //b=0 w=1;
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'B') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }//if
            }//for_2
        }//for_1

        //startPoint 정하기
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                switchColor(i, j);
            }
        }
        System.out.println(answer);
    }//MAIN

    static void switchColor(int r, int c) {
        //일단 0,0 ~ 8,8 까지 떼어내고, 왼쪽이 검정인걸로 1번, 흰색인걸로 1번확인
        //0,1 ~8,9 ~~~~ 0,N-8 8,N까지
        //이중for문으로

        int[][] captureMap = new int[8][8];
        int[][] origMap = new int[8][8];
        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c + 8; j++) {
                origMap[i - r][j - c] = map[i][j];
                captureMap[i - r][j - c] = map[i][j];
            }//for_1
        }//for_2

        //그린 맵에서 BW 변경테스트
        ///왼쪽 위에서부터 확인하고 내려오니까 오른쪽이랑 아래만 확인하면 된다.
        int orig = checkMin(captureMap);
        answer = Math.min(orig, answer);


        //원상복구 후 첫번째 값 바꿔서 돌리기
        for (int i = 0; i < 8; i++) {
            captureMap[i] = origMap[i].clone();
        }
        captureMap[0][0] = (captureMap[0][0] + 1) % 2;
        int change = checkMin(captureMap);
        //위에서 값 바꿨으니까+1;
        if (change != Integer.MAX_VALUE) {
            change += 1;
        }
        answer = Math.min(change, answer);
    }//checkMin

    static int checkMin(int[][] captureMap) {
        int min = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //일단 오른쪽만 확인하고, j가 0이면 아래도 확인
                int nextR = i + 1;
                int nextC = j + 1;
                if (j == 0 && nextR < 8 && captureMap[nextR][j] == captureMap[i][j]) {
                    captureMap[nextR][j] = (captureMap[nextR][j] + 1) % 2;
                    min++;

                }
                if (nextC < 8 && captureMap[i][nextC] == captureMap[i][j]) {
                    captureMap[i][nextC] = (captureMap[i][nextC] + 1) % 2;
                    min++;
                }
                if (min >= answer) {
                    return Integer.MAX_VALUE;
                }
            }//for2
        }//for1
        return min;
    }

}//CLASS
