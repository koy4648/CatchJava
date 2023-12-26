package practice;

import java.util.*;
import java.io.*;

public class BJ10026_적록색약 {
    static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int N;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        int answer = 0;
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();
        }
//		System.out.println(Arrays.deepToString(map));

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 방문기록이 없으면 이 좌표부터 bfs돌리기
                if (!visited[i][j]) {
                    bfs(i, j);
                    // 다돌고 왔으면 구역 나눠진거니까 +1
                    answer++;
                }
            } // for
        }
        sb.append(answer).append(" ");

        // 초기화
        answer = 0;
        // 적록색약의 경우 R과 G를 구분 못하기 때문에 하나로 통일해버리기
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(visited[i], false);
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            } // if
        } // for

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 방문기록이 없으면 이 좌표부터 bfs돌리기
                if (!visited[i][j]) {
                    bfs(i, j);
                    // 다돌고 왔으면 구역 나눠진거니까 +1
                    answer++;
                }
            } // for
        }

        sb.append(answer);
        System.out.println(sb);
    }// main

    static void bfs(int i, int j) {
        Queue<Point> q = new ArrayDeque();
        q.add(new Point(i, j));
        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + move[k][0];
                int ny = y + move[k][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] != map[x][y]) {
                    continue;
                }

                // 다음 좌표에 방문 표시하고 큐에 넣기
                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            } // for

        } // while
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}// class
