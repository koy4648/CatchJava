package practice;

import java.util.*;
import java.io.*;

public class BJ14502_연구소{
	static int N, M, max, map[][];
	static int move[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static ArrayList<Point> points;
//	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;

		map = new int[N][M];
		points = new ArrayList();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					points.add(new Point(i, j));
			}
		} // map ׸

		dfs(0);
		System.out.println(max);

	}// main

	static void dfs(int cnt) {
		if (cnt == 3) {
			bfs();

			return;
		}

		//
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(cnt + 1);
					map[i][j] = 0;
				}
			}
		} // for_1
	}// dfs

	static void bfs() {
		int num = 0;

		//updatedmap                ϱ
		int[][]updatedMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			updatedMap[i] = map[i].clone();
		}

		Queue<Point> q = new ArrayDeque();
		for (Point p : points) {
			q.add(p);
		}

		while (!q.isEmpty()) {
			Point tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = tmp.r + move[i][0];
				int nc = tmp.c + move[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || updatedMap[nr][nc] != 0)
					continue;
				updatedMap[nr][nc] = 2;
				q.add(new Point(nr, nc));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (updatedMap[i][j] == 0)
					num++;
			}
		}
		// max
		max = Math.max(max, num);
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}// class
