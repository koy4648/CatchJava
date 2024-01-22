package newAlgorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P8_BJ16928 {
    static int N, M, answer;
    static ArrayList<Route> routes;
    static boolean[] visit;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[101];
        routes = new ArrayList();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            routes.add(new Route(from, to));
        }

        bfs();

        System.out.println(answer);
    }//main


    static void bfs() {
        q = new ArrayDeque();
        q.add(1);
        visit[1]=true;

        int num = 0;
        while (!q.isEmpty() ) {
            int size = q.size();
            num++;
            for (int i=0;i<size;i++) {
                int start =q.poll();


                if (start > 100) continue;
                for (int j = 1; j < 7; j++) {
                    int nStart = start+j;

                    if (nStart == 100) {
                        answer = Math.min(num,answer);
                        return;
                    }

                    if(nStart>100)continue;
                    if(visit[nStart])continue;
                    visit[nStart]=true;

                    for (Route r : routes) {
                        if (r.from == nStart) {//사다리 만나면 무조건 타는데
                            q.add(r.to);
                        } else {
                            q.add(nStart);
                        }
                    }
                }//for_주사위
            }
        }
    }

    static class Route {
        int from;
        int to;

        public Route(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }//Route
}//CLASS


/*
1 5
2 92
94 3
95 4
96 5
97 6
98 7
 */