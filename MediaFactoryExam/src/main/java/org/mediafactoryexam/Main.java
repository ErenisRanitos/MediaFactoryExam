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

        String more = "1 USD = 22.23 CZK";
        String more1 = "10 USD > 10 CZK";
        String more2 = "10 EUR < 10 USD";
        boolean haha = Operation.compareAmount(more);
        haha = Operation.compareAmount(more1);
        haha = Operation.compareAmount(more2);

        String f = "1.23 USD";
        String f1 = "99.99 CZK";
        String f2 = "120.46 EUR";
        String x = Operation.round(f);
        x = Operation.round(f1);
        x = Operation.round(f2);

        String z = "100 CZK -> USD";
        String z1 = "100 CZK -> EUR";
        String z2 = "100 EUR -> USD";
        String z3 = "100 EUR -> CZK";
        String z4 = "100 USD -> CZK";
        String z5 = "100 USD -> EUR";
        String s = Operation.exchange(z);
        s = Operation.exchange(z1);
        s = Operation.exchange(z2);
        s = Operation.exchange(z3);
        s = Operation.exchange(z4);
        s = Operation.exchange(z5);

        int y = 0;
    }
}