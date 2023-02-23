package org.mediafactoryexam.eshop;

import org.mediafactoryexam.enums.Currency;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private int walletId;
    private final Map<Currency, Double> walletAmount;

    public Wallet() {
        walletAmount = new HashMap<>();
    }

    public Wallet(Map<Currency, Double> walletAmount) {
        // we do this to create a new Object
        // this.walletAmount = walletAmount would pass the pointer to the parameter
        // that could lead to updates on the parameter from outside the constructor
        // and thus changing the values in the property
        this.walletAmount = new HashMap<>();
        walletAmount.forEach(this.walletAmount::putIfAbsent);
    }

    public Wallet(int walletId) {
        walletAmount = new HashMap<>();
        this.walletId = walletId;
    }

    public Double getAmount (Currency currency) {
        return walletAmount.get(currency);
    }

    public int getWalletId() {
        return walletId;
    }

    public Double totalAmount (Currency outputCurrency) {
        Double totalAmount = 0.0;
        for (Map.Entry<Currency, Double> oneAmount : walletAmount.entrySet()) {
            Currency currency = oneAmount.getKey();
            Double amount = oneAmount.getValue();

            if (currency == outputCurrency) {
                totalAmount += amount;
            } else {
                totalAmount += currency.convert(amount, outputCurrency);
            }
        }
        return totalAmount;
    }

    public Double totalAmount (String outputCurrency) {
        return totalAmount(Currency.valueOf(outputCurrency));
    }

    public void addAmount(double amount, Currency currency) {
        walletAmount.put(currency, walletAmount.get(currency) + amount);
    }

    public void addAmount(double amount, String currency) {
        addAmount(amount, Currency.valueOf(currency));
    }
}
