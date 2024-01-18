package newAlgorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7_BJ1012 {
    static int T, M, N, K, answer, map[][];
    static int[][] move = {{0,1},{1,0},{0,-1},{-1,0}};
    static boolean check[][];
    static Queue<Point> q;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());
        for (int t = T; t >0 ; t--) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            answer=0;
            map = new int[M][N];
            check = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c]=1;
            }
//            bfs();
            dfs();
            sb.append(answer).append("\n");
        }//tc
        System.out.println(sb);
    }//main

    static void bfs(){
        q = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]==1 && !check[i][j]){
                    q.add(new Point(i,j));
                    answer++;
                    while(!q.isEmpty()){
                        Point tmp = q.poll();
                        if(check[tmp.r][tmp.c])continue;//이미 이전에 접근했으면 더 볼 필요 없음.
                        check[tmp.r][tmp.c]=true;
                        for (int k = 0; k < 4; k++) {
                            int nr = tmp.r+move[k][0];
                            int nc = tmp.c+move[k][1];
                            if(nr>=0 && nc>=0 && nr<M && nc<N && !check[nr][nc] && map[nr][nc]==1){   //맵 안에 있고, 아직 접근하지 않았고, map이 1이면
                                q.add(new Point(nr,nc));
                            }//if
                        }//for
                    }//while
                }
            }
        }
    }//bfs
    static void dfs(){

    }

    static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        public String toString(int r, int c){
            return "r & c =" + r+"&"+c;
        }
    }
}//class
