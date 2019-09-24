package com.isoft.youeryuan;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i =  0;
        while (scanner.hasNextLine()){
            i++;
            for (int i1 = 0; i1 < i; i1++) {
                int a=scanner.nextInt();
                int b = scanner.nextInt();
                System.out.println(a+b);
            }
        }
        System.out.println(i);
    }

}
