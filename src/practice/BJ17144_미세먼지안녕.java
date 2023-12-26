package practice;

import java.util.*;
import java.io.*;

public class BJ17144_미세먼지안녕 {
    static int R, C, T, air;
    static int[][] map;
    static int[][] addition;
    static int[][] move = {{1,0},{-1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer=0;
        air=0;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
//		//System.out.println(R+ ""+C+""+T);

        map = new int[R][C];
        addition = new int[R][C];
        for (int i = 0; i <R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
                if(air==0 && map[i][j]== -1) {
                    air = i;
                }
            }//FOR_2
        }//FOR_1

        //먼지 있으면 다 옆으로 퍼뜨리기
        for (int i = 0; i < T; i++) {
            //먼지 퍼트리기
            plus();
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    map[j][k]+=addition[j][k];
                }
                //addition초기화하기
                Arrays.fill(addition[j], 0);
            }

            //공기청정기로 밀기
            push();
        }

        //T초 뒤에 남아있는 먼지의 합 구하기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]!=-1) {
                    answer+=map[i][j];
                }
            }
        }
        System.out.println(answer);

    }// main

    static void plus() {
        for(int r=0;r<R;r++) {
            for(int c=0;c<C;c++) {
                //빈 맵에 확산된 값 넣기
                if(map[r][c]!=0 && map[r][c]!=-1) {
                    int add = map[r][c]/5;
                    //move로 돌려서 실행
                    for (int k = 0; k < 4; k++) {
                        int nr = r+move[k][0];
                        int nc = c+move[k][1];
                        if(nr<0 || nr>=R ||nc<0 || nc>=C || (nr==air && nc==0) ||map[nr][nc]==-1  || add==0)continue;
                        addition[nr][nc]+=add;
                        addition[r][c] -= add;
                    }//for_3
                }//if
            }//for_2
        }//for_1
    }//spread


    static void push() {
        //System.out.println("afterplus"+Arrays.deepToString(map));
        //오른쪽으로 보내기
        Point start = new Point(air,1);
        Point stop = new Point(air,C-1);
        int stopPoint1=map[stop.r][stop.c];
        int stopPoint2 = map[stop.r+1][stop.c];

        right(start,stop);
        map[start.r][start.c] = 0;
        map[start.r+1][start.c] = 0;

        //위로 올리기
        start = new Point(air,C-1);
        stop = new Point(0,C-1);
        int stopPoint3 = map[stop.r][stop.c];
        up(start,stop);
        map[air-1][C-1]=stopPoint1;

        //밑으로 내리는 것도 체크
        start = new Point(air+1,C-1);
        stop = new Point(R-1,C-1);
        int stopPoint4 = map[stop.r][stop.c];
        down(start,stop);
        map[start.r+1][C-1]=stopPoint2;

        //왼쪽으로 보내기
        start = new Point(0,C-2);
        stop = new Point(0,0);
        stopPoint1=map[stop.r][stop.c];
        stopPoint2 = map[stop.r+R-1][stop.c];
        left(start,stop);
        map[start.r][start.c]=stopPoint3;
        map[R-1][C-2]=stopPoint4;


        //밑으로 내리기
        start = new Point(0,0);
        stop = new Point(air-1,0);
        down(start,stop);
        map[start.r+1][start.c]=stopPoint1;

        //마지막 위로 올리기
        start = new Point(R-1,0);
        stop = new Point(air+2,0);
        up(start,stop);
        map[R-2][start.c]=stopPoint2;

    }//push

    static void right(Point start, Point stop) {
        //공기청정기 2칸 한번에 돌리기
        for (int i = stop.c; i > start.c; i--) {
            map[stop.r][i]=map[stop.r][i-1];
            map[stop.r+1][i]=map[stop.r+1][i-1];
        }
    }//right

    static void up(Point start, Point stop) {
        for (int i = stop.r+1; i <start.r; i++) {
            //System.out.println("UP!!"+i+" "+stop.c);
            map[i-1][stop.c]=map[i][stop.c];
            //System.out.println(map[i-1][stop.c]);
        }
    }//up

    static void down(Point start, Point stop) {
        for(int i=stop.r;i>start.r;i--) {
            map[i][stop.c]=map[i-1][stop.c];
        }
    }//down

    static void left(Point start, Point stop) {
        //공기청정기 2칸 한번에 돌리기
        for (int i = stop.c; i < start.c; i++) {
            map[0][i]=map[0][i+1];
            map[R-1][i]=map[R-1][i+1];
        }
    }//LEFT

    static class Point{
        int r;
        int c;

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}// class