package newAlgorithm;

import java.io.*;
import java.util.*;

public class P5_BJ2839 {
    static int n, answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        answer = Integer.MAX_VALUE;

        /*
        //5를 뺐을때랑 3을 뺐을때랑 경우의 수를 나눠서 재귀 -> 시간초과
        delivery(n, 0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        */

        //일단 가장 작은 경우는 5의 배수인 경우.
        if (n % 5 == 0) {
            answer = n / 5;
        } else {
            int tmp = 0;
            while (n > 0) {
                //n에서 3만큼 빼고 계산
                n -= 3;
                tmp += 1;
                if (n % 5 == 0) {
                    answer = Math.min(answer, tmp + (n / 5));
                    break;
                }
            }
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }//main

/*    static void delivery(int weight, int num) {
        if (num > answer) return;
        if (weight == 0) {
            answer = Math.min(num, answer);
        }
        if (weight < 0) {
            return;
        }

        //5kg을 뺀 경우
        delivery(weight - 5, num + 1);
        //3kg을 뺀 경우
        delivery(weight - 3, num + 1);
    }*/

}//class
