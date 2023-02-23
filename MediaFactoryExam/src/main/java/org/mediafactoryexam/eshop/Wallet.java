package org.mediafactoryexam.eshop;

import org.mediafactoryexam.enums.Currency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private int walletId;
    private final Map<Currency, Double> walletAmount;

    public Wallet(Map<Currency, Double> walletAmount, int walletId) {
        this.walletAmount = new HashMap<>();
        walletAmount.forEach(this.walletAmount::putIfAbsent);
        this.walletId = walletId;
    }

    public Wallet(int walletId) {
        walletAmount = new HashMap<>();
        Arrays.stream(Currency.values()).forEach(currency -> walletAmount.put(currency, 0.0));
        this.walletId = walletId;
    }

    public double getAmount (Currency currency) {
        return walletAmount.get(currency);
    }

    public int getWalletId() {
        return walletId;
    }

    public double totalAmount (Currency outputCurrency) {
        double totalAmount = 0.0;
        for (Map.Entry<Currency, Double> oneAmount : walletAmount.entrySet()) {
            Currency currency = oneAmount.getKey();
            double amount = oneAmount.getValue();

            if (currency == outputCurrency) {
                totalAmount += amount;
            } else {
                totalAmount += currency.convert(amount, outputCurrency);
            }
        }
        return totalAmount;
    }

    public double totalAmount (String outputCurrency) {
        return totalAmount(Currency.valueOf(outputCurrency));
    }

    public void addAmount(double amount, Currency currency) {
        walletAmount.put(currency, walletAmount.get(currency) + amount);
    }

    public void addAmount(double amount, String currency) {
        addAmount(amount, Currency.valueOf(currency));
    }
}
