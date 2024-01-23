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
//        dfs(1, 0);
        System.out.println(answer);
    }//main

    /*
        static void dfs(int start, int num) {
            if (start == 100) {
                answer = Math.min(num, answer);
                return;
            }
            if (start > 100) return;

            for (int i = 1; i <= 6; i++) {
                int nStart = start + i;

                if (nStart > 100) continue;
                if (visit[nStart]) continue;
                visit[nStart] = true;

                for (Test.Route r : routes) {
                    if (r.from == nStart) {
                        dfs(r.to, num + 1);
                        return; // 뱀이나 사다리를 만나면 해당 위치로 이동 후 종료
                    }
                }

                dfs(nStart, num + 1);
                visit[nStart] = false; // backtracking
            }
        }
    */
    static void bfs() {
        q = new ArrayDeque();
        q.add(1);
        visit[1] = true;

        int num = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            num++;
            for (int i = 0; i < size; i++) {
                int start = q.poll();

                if (start > 100) continue;
                for (int j = 1; j < 7; j++) {
                    int nStart = start + j;
                    //이동한 값이 100이면 최소값을 구해서 answer로 내놓기
                    if (nStart == 100) {
                        answer = Math.min(num, answer);
                        return;
                    }

                    if (nStart > 100) continue;
                    if (visit[nStart]) continue;
                    visit[nStart] = true;

                    boolean moved = false;
                    for (Route r : routes) {
                        if (r.from == nStart) {//사다리나 뱀이 있으면 이동한 이후 값을 q에 넣는다.
                            q.add(r.to);
                            moved = true;
                            break;
                        }
                    }
                    if (!moved) {//사다리나 뱀을 탈 게 없으면 주사위가 이동한 값을 q에 넣는다.
                        q.add(nStart);
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
>>>>>>> 3293f161f8c8989a6c3034e94e1ebe62b8c3f7b4
