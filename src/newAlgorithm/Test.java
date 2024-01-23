package newAlgorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test {
    static int N, M, answer;
    static ArrayList<Route> routes;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[101];
        routes = new ArrayList<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            routes.add(new Route(from, to));
        }

        dfs(1, 0);
        System.out.println(answer);
    }

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

            for (Route r : routes) {
                if (r.from == nStart) {
                    dfs(r.to, num + 1);
                    return; // 뱀이나 사다리를 만나면 해당 위치로 이동 후 종료
                }
            }

            dfs(nStart, num + 1);
            visit[nStart] = false; // backtracking
        }
    }

    static class Route {
        int from;
        int to;

        public Route(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
