package newAlgorithm;

import java.io.*;
import java.util.*;

public class P6_BJ2667 {
    //1은 집이 있는 곳 0은 집이 없는 곳. 상하좌우에 있으면 연결되어 있는 단지.
    // 단지에 속하는 집의 수를 오름차순으로 출력하기.
    //즉 상하좌우로 연결되어 있는 것이 몇개인지 세야 함. 그 갯수를 arrayList에 넣고 sort하기!
    static int n,num, map[][];
    static boolean checkMap[][];
    static Queue<Point> queue;
    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        checkMap = new boolean[n][n];
        answer = new ArrayList();
        for (int i = 0; i < n; i++) {
            String list = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = list.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checkMap[i][j] || map[i][j] == 0) {//이미 확인했거나, 0인 경우 큐에 넣을 필요 없음.
                    continue;
                }

                checkMap[i][j]=true;
                num = 1;

                //1. BFS로 풀기
//                bfs(i,j);
                //2. DFS로 풀기
                dfs(i,j);
                answer.add(num);

            }//for2
        }//for1
        //출력
        Collections.sort(answer);
        sb.append(answer.size());
        for (int i : answer) {
            sb.append("\n").append(i);
        }
        System.out.println(sb);
    }//main

    static void bfs(int i, int j) {
                queue = new ArrayDeque();
                queue.add(new Point(i, j));
                while (!queue.isEmpty()) {
                    Point tmp = queue.poll();
                    //for문에서 돌면서 이미 확인했다는 표시
                    for (int k = 0; k < 4; k++) {
                        int nr = tmp.r + move[k][0];
                        int nc = tmp.c + move[k][1];
                        if (nr >= 0 && nc >= 0 && nr < n && nc < n && !checkMap[nr][nc] && map[nr][nc] == 1) {
                            checkMap[nr][nc] = true;
                            queue.add(new Point(nr, nc));
                            num++;
                        }
                    }//for_move
                }//while
    }//bfs


    static void dfs(int i, int j){
        //1이면 타고타고 가기
        for (int k = 0; k < 4; k++) {
            int nr = i + move[k][0];
            int nc = j + move[k][1];
            if (nr >= 0 && nc >= 0 && nr < n && nc < n && !checkMap[nr][nc] && map[nr][nc] == 1) {
                checkMap[nr][nc] = true;
                num++;
                dfs(nr,nc);
            }
        }//for_move
        return;
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public String toString(int r, int c) {
            return "r & c = " + r + " " + c;
        }
    }
}//class
