package org.mediafactoryexam.eshop;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.mediafactoryexam.enums.Currency;
import org.mediafactoryexam.enums.Operator;

import java.util.*;

public class Operation {

    // this needs validation prior to calling compute amount
    // checks like spaces and currency as a second word for resultCurrency setup
    public static String computeAmount(String input) {
        List<String> parsedInput = Arrays.asList(input.split(StringUtils.SPACE));
        Currency resultCurrency = Currency.valueOf(parsedInput.get(1));

        List<String> normalizedInput = new ArrayList<>();
        for (int i = 0; i < parsedInput.size(); i++) {
            if (EnumUtils.isValidEnum(Currency.class, parsedInput.get(i))) {
                normalizedInput.remove(normalizedInput.size() - 1);
                normalizedInput.add(normalizeCurrency(
                        Double.parseDouble(parsedInput.get(i - 1)),
                        Currency.valueOf(parsedInput.get(i)),
                        resultCurrency));
            } else {
                normalizedInput.add(parsedInput.get(i));
            }
        }

        if (normalizedInput.contains(Operator.MULTIPLICATION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.MULTIPLICATION, resultCurrency);
        }
        if (normalizedInput.contains(Operator.DIVISION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.DIVISION, resultCurrency);
        }
        if (normalizedInput.contains(Operator.ADDITION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.ADDITION, resultCurrency);
        }
        if (normalizedInput.contains(Operator.SUBTRACTION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.SUBTRACTION, resultCurrency);
        }

        // I could also use stream api and collectors to join these strings
        // in case I need some more control over them
        return normalizedInput.get(0) + StringUtils.SPACE + resultCurrency;
    }

    private static List<String> solveOperation(List<String> parsedInput, Operator OPERATOR, org.mediafactoryexam.enums.Currency resultCurrency) {
        int last = parsedInput.lastIndexOf(OPERATOR.getOperatorChar());

        while (parsedInput.indexOf(OPERATOR.getOperatorChar()) <= last) {
            List<String> newInput = new ArrayList<>();
            int next = parsedInput.indexOf(OPERATOR.getOperatorChar());

            double amount1 = Double.parseDouble(parsedInput.get(next - 1));
            double amount2 = Double.parseDouble(parsedInput.get(next + 1));

            double operationResult = calculateResult(amount1, amount2, OPERATOR);


            if (next - 1 >= 0 ) {
                newInput.addAll(parsedInput.subList(0, next - 1));
            }
            newInput.add(String.valueOf(operationResult));
            newInput.addAll(parsedInput.subList(next + 2, parsedInput.size()));
            parsedInput = newInput;

            last = parsedInput.lastIndexOf(OPERATOR.getOperatorChar());
            if (last < 0) {
                return parsedInput;
            }
        }

        return parsedInput;
    }

    private static double calculateResult(double amount1, double amount2, Operator OPERATOR) {
        return switch (OPERATOR) {
            case ADDITION -> amount1 + amount2;
            case SUBTRACTION -> amount1 - amount2;
            case MULTIPLICATION -> amount1 * amount2;
            case DIVISION -> amount1 / amount2;
        };
    }

    private static String normalizeCurrency(double amount, Currency currency, Currency resultCurrency) {
        if (currency != resultCurrency) {
            return String.valueOf(currency.convert(amount, resultCurrency));
        }
        return String.valueOf(amount);
    }
}
