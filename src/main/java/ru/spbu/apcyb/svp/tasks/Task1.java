package ru.spbu.apcyb.svp.tasks;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task1 {
    static final int startLen = 8;
    long sum = 0;
    long[] bills = new long[startLen];

    public int getInput() throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), " ");
        if (stringTokenizer.hasMoreTokens()) {
            long num = Long.parseLong(stringTokenizer.nextToken());
            if (num > 0) this.sum = num;
            else throw new IOException("Result sum must be positive");
        } else throw new IOException("At least 2 numbers are required: result sum and bill value");
        int k = 0;
        while (stringTokenizer.hasMoreTokens()) {
            long num = Long.parseLong(stringTokenizer.nextToken());
            if (num > 0) {
                int i = 0;
                for (; i < k; ++i) {
                    if (this.bills[i] == num) break;
                }
                if (i == k) this.bills[k++] = num;
                if (k == this.bills.length) {
                    this.bills = Arrays.copyOf(this.bills, 2 * this.bills.length);
                }
            } else throw new IOException("Bill value must be positive");
        }
        if (k == 0) throw new IOException("At least 1 bill is required");
        this.bills = Arrays.copyOf(this.bills, k);
        return 0;
    }

    private int findInd(long bill) {
        for (int i = 0; i < bills.length; ++i) {
            if (bills[i] == bill) return i;
        }
        return -1;
    }

    private long searchFromBill(int num) {
        Stack<Long> curChange = new Stack<>();
        long res = 0;
        long bill = bills[num];
        long curSum = bill;
        curChange.add(bill);
        int j = num;
        if (curSum < sum) {
            while (!curChange.isEmpty()) {
                while (j < bills.length) {
                    bill = bills[j];
                    curSum += bill;
                    if (curSum < this.sum) curChange.add(bill);
                    else if (curSum > this.sum) {
                        ++j;
                        curSum -= bill;
                    } else {
                        curChange.add(bill);
                        System.out.println(Arrays.toString(curChange.toArray()).
                                replace("[", "").
                                replace("]", "").
                                replace(",", ""));
                        curSum -= curChange.pop();
                        ++j;
                        ++res;
                    }
                }
                bill = curChange.pop();
                curSum -= bill;
                j = this.findInd(bill) + 1;
            }
        } else if (curSum == sum) {
            System.out.println(bill);
            return 1;
        }
        return res;
    }

    public long findPossibleChanges() {
        long k = 0;
        int len = this.bills.length;
        for (int i = 0; i < len; ++i) {
            k += searchFromBill(i);
        }
        return k;
    }
}
