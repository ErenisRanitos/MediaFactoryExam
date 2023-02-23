package org.mediafactoryexam.enums;

import org.mediafactoryexam.utils.ConversionConfig;

public enum Currency {
    CZK ("CZK"),
    EUR ("EUR"),
    USD ("USD");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public double convert (double amount, Currency outputCurrency) {
        switch (this) {
            case CZK -> {
                switch (outputCurrency) {
                    case EUR -> {
                        return amount * ConversionConfig.CZK2EUR;
                    }
                    case USD -> {
                        return amount * ConversionConfig.CZK2USD;
                    }
                }
            }
            case EUR -> {
                switch (outputCurrency) {
                    case CZK -> {
                        return amount * ConversionConfig.EUR2CZK;
                    }
                    case USD -> {
                        return amount * ConversionConfig.EUR2USD;
                    }
                }
            }
            case USD -> {
                switch (outputCurrency) {
                    case EUR -> {
                        return amount * ConversionConfig.USD2EUR;
                    }
                    case CZK -> {
                        return amount * ConversionConfig.USD2CZK;
                    }
                }
            }
        }
        return amount;
    }
}
