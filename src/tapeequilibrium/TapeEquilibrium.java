/*
A non-empty zero-indexed array A consisting of N integers is given. Array A 
represents numbers on a tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: 
A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The difference between the two parts is the value of: 
|(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part 
and the sum of the second part.

For example, consider array A such that:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
We can split this tape in four places:

P = 1, difference = |3 − 10| = 7 
P = 2, difference = |4 − 9| = 5 
P = 3, difference = |6 − 7| = 1 
P = 4, difference = |10 − 3| = 7 
Write a function:

class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A of N integers, returns the minimal 
difference that can be achieved.

For example, given:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
the function should return 1, as explained above.

Assume that:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting
the storage required for input arguments).
Elements of input arrays can be modified.
Copyright 2009–2016 by Codility Limited. 
All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
package tapeequilibrium;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TapeEquilibrium {

    public static void main(String[] args) {
        int[] A = new int[]{3, 1, 2, 4, 3};

        int sumPre = A[0];
        int sumPost = 0;
        for (int i = 1; i < A.length; i++) {
            sumPost += A[i];
        }
        int difMin = Math.abs(sumPost - sumPre);
        int tempSub = 0;
        for (int i = 1; i < A.length - 1; i++) {
            sumPre += A[i];
            sumPost -= A[i];
            tempSub = Math.abs(sumPost - sumPre);
            if (tempSub < difMin) {
                difMin = tempSub;
            }
        }
        System.out.println(difMin);
    }

    public int solution1(int[] A) {
        final int N = A.length;
        long minimalSum = (int) A[0];

        //original -- This is the array from which a range is to to be copied.
        //from -- This is the initial index of the range to be copied, inclusive.
        //to -- This is the final index of the range to be copied, exclusive.
        int[] rightSide = Arrays.copyOfRange(A, 1, N);
        long maximalSum = IntStream.of(rightSide).sum();

        int minimalDifference = (int) Math.abs(maximalSum - minimalSum);
        for (int i = 1; i < N; i++) {
            int difference = (int) Math.abs(maximalSum - minimalSum);
            minimalDifference = difference < minimalDifference ? difference : minimalDifference;
            minimalSum += A[i];
            maximalSum -= A[i];
        }
        return minimalDifference;
    }

    public int solution2(int[] A) {
        int sumMin = A[0];
        int sumMax = 0;

        for (int i = 1; i < A.length; i++) {
            sumMax += A[i];
        }

        int minDif = Math.abs(sumMin - sumMax);
        for (int i = 1; i < A.length - 1; i++) {
            sumMin += A[i];
            sumMax -= A[i];
            minDif = Math.min(minDif, Math.abs(sumMin - sumMax));
        }
        return minDif;
    }

    public int solution3(int[] A) {

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int len = A.length;
        int min = 1;

        for (int i = 0; i < len; i++) {
            sum2 += A[i];
        }

        for (int i = 0; i < len - 1; i++) {

            sum1 += A[i];
            sum3 = sum2 - sum1;

            if (min > Math.abs(sum1 - sum3)) {
                min = Math.abs(sum1 - sum3);
            }
        }
        return min;
    }
}
