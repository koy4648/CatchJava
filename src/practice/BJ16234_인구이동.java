package practice;

import java.util.*;
import java.io.*;

public class BJ16234_인구이동 {
    static boolean visited[][];
    static int map[][], update[][];
    static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
    static int N, R, L, updated;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        // 정사각형형태
        // 국경선이 열릴수 있는지 체크 //visited로 일차 처리 boolean으로 나누기?
        // 연합이 여러개가 될 수도 있나? 각자 나눠서 큐에 넣기
        // 열려있는 나라 수 확인
        // 열려있는 나라의 국민수 다 합치고 나누기 -> 소수점 버린다. int로 처리
        // 며칠동안 발생하는지 체크 ->cnt++
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N][N];
        map = new int[N][N];
        update = new int[N][N];
        flag = true;
        updated = 0;
        int answer = 0;

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            } // for_2
        } // for_1

        // update가 다 0이면 끝내기
        while (flag) {
            //System.out.println("start"+Arrays.deepToString(map));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            // 한번 돌린 결과 -> true인 동안 bfs를 계속 돌린다. update가 다 0이면 끝낸다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (update[i][j] != 0) {
                        map[i][j] = update[i][j];
                    }
                }
            }
            if (updated != 0) {
                answer++;
                updated = 0;

                for (int i = 0; i < visited.length; i++) {
                    Arrays.fill(visited[i], false);
                    Arrays.fill(update[i], 0);
                }

            } else {
                flag = false;
            }
        }
        System.out.println(answer);
    }// main

    static void bfs(int i, int j) {
        Queue<Point> q = new ArrayDeque<>();
        Set<Point> s = new HashSet();

        q.add(new Point(i, j));
        s.add(new Point(i, j));
        int plus =0;
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            int r = tmp.r;
            int c = tmp.c;

            // move 배열로 사방탐색
            for (int k = 0; k < 4; k++) {
                int nr = tmp.r + move[k][0];
                int nc = tmp.c + move[k][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || Math.abs(map[nr][nc] - map[r][c]) > R
                        || Math.abs(map[nr][nc] - map[r][c]) < L)
                    continue;
                // 같은 구획으로 인정 queue에 넣기
                visited[nr][nc] = true;
                q.add(new Point(nr, nc));
                // set에 point넣기
                s.add(new Point(nr, nc));
            } // FOR
        } // while
        // 지금 i,j에서 그룹확인. 얘네로 연합 인구 처리 -> set에 들어간 애들은 이미 visited가 true이기 때문에 더 안본다?
        if (s.size() > 1) {
            Iterator iter = s.iterator();
            while (iter.hasNext()) {
                Point p = (Point) iter.next();
                //System.out.println(p);
                plus += map[p.r][p.c];
            }

            int div = plus / s.size();

            iter = s.iterator();
            while (iter.hasNext()) {
                Point p = (Point) iter.next();
                update[p.r][p.c] = div;
            }
            updated=plus;
            //boolean을 새로 넣어?
        }
    }// bfs

    static class Point {
        int r, c;


        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }

        @Override
        public int hashCode() {
            return this.r+this.c;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point) {
                Point tmp = (Point)obj;
                if(this.r==tmp.r && this.c==tmp.c) {
                    return true;
                }
            }
            return false;
        }

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }// Point_Class
}// class
