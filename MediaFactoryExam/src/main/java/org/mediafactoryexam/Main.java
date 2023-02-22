package org.mediafactoryexam;

import org.mediafactoryexam.eshop.Operation;

public class Main {
    public static void main(String[] args) {
        String example = "100 CZK + 1 EUR";
        String example1 = "100 CZK + 1 EUR + 1 USD";
        String example2 = "100 CZK * 5";
        String example3 = "100 CZK * 5 + 100 USD / 10";
        String lol = Operation.computeAmount(example);
        lol = Operation.computeAmount(example1);
        lol = Operation.computeAmount(example2);
        lol = Operation.computeAmount(example3);
        int x = 0;
    }
}