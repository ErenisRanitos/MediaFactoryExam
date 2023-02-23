package org.mediafactoryexam.eshop;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.mediafactoryexam.enums.Currency;
import org.mediafactoryexam.enums.Operator;

import java.util.*;

public class Operation {

    public static String exchange(String input) {
        List<String> parsedInput = Arrays.asList(input.split(StringUtils.SPACE));
        Double amount = Double.parseDouble(parsedInput.get(0));
        Currency inputCurrency = Currency.valueOf(parsedInput.get(1));
        Currency outputCurrency = Currency.valueOf(parsedInput.get(parsedInput.size() - 1));

        if (inputCurrency == outputCurrency) {
            return amount + StringUtils.SPACE + outputCurrency;
        } else {
            return inputCurrency.convert(amount, outputCurrency) + StringUtils.SPACE + outputCurrency;
        }
    }

    public static String round(String input) {
        List<String> parsedInput = Arrays.asList(input.split(StringUtils.SPACE));
        Double amount = Double.parseDouble(parsedInput.get(0));
        amount = (double) Math.round(amount);

        return amount + StringUtils.SPACE + parsedInput.get(1);
    }

    public static boolean compareAmount(String input) {
        List<String> parsedInput = Arrays.asList(input.split(StringUtils.SPACE));
        Currency resultCurrency = Currency.valueOf(parsedInput.get(1));

        List<String> normalizedInput = normalizeInput(parsedInput, resultCurrency);

        if (normalizedInput.contains(Operator.GREATER.getOperatorChar())) {
            return solveComparison(normalizedInput, Operator.GREATER);
        }
        if (normalizedInput.contains(Operator.LOWER.getOperatorChar())) {
            return solveComparison(normalizedInput, Operator.LOWER);
        }
        if (normalizedInput.contains(Operator.EQUALS.getOperatorChar())) {
            return solveComparison(normalizedInput, Operator.EQUALS);
        }

        return false;
    }

    // this needs validation prior to calling compute amount
    // checks like spaces and currency as a second word for resultCurrency setup
    public static String computeAmount(String input) {
        List<String> parsedInput = Arrays.asList(input.split(StringUtils.SPACE));
        Currency resultCurrency = Currency.valueOf(parsedInput.get(1));

        List<String> normalizedInput = normalizeInput(parsedInput, resultCurrency);

        if (normalizedInput.contains(Operator.MULTIPLICATION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.MULTIPLICATION);
        }
        if (normalizedInput.contains(Operator.DIVISION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.DIVISION);
        }
        if (normalizedInput.contains(Operator.ADDITION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.ADDITION);
        }
        if (normalizedInput.contains(Operator.SUBTRACTION.getOperatorChar())) {
            normalizedInput = solveOperation(normalizedInput, Operator.SUBTRACTION);
        }

        // I could also use stream api and collectors to join these strings
        // in case I need some more control over them
        return normalizedInput.get(0) + StringUtils.SPACE + resultCurrency;
    }

    private static List<String> normalizeInput(List<String> parsedInput, Currency resultCurrency) {
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

        return normalizedInput;
    }

    private static boolean solveComparison(List<String> parsedInput, Operator operator) {
        int next = parsedInput.indexOf(operator.getOperatorChar());

        double amount1 = Double.parseDouble(parsedInput.get(next - 1));
        double amount2 = Double.parseDouble(parsedInput.get(next + 1));

        return compareResult(amount1, amount2, operator);
    }

    private static List<String> solveOperation(List<String> parsedInput, Operator operator) {
        int last = parsedInput.lastIndexOf(operator.getOperatorChar());

        while (parsedInput.indexOf(operator.getOperatorChar()) <= last) {
            List<String> newInput = new ArrayList<>();
            int next = parsedInput.indexOf(operator.getOperatorChar());

            double amount1 = Double.parseDouble(parsedInput.get(next - 1));
            double amount2 = Double.parseDouble(parsedInput.get(next + 1));

            double operationResult = calculateResult(amount1, amount2, operator);


            newInput.addAll(parsedInput.subList(0, next - 1));
            newInput.add(String.valueOf(operationResult));
            newInput.addAll(parsedInput.subList(next + 2, parsedInput.size()));
            parsedInput = newInput;

            last = parsedInput.lastIndexOf(operator.getOperatorChar());
            if (last < 0) {
                return parsedInput;
            }
        }

        return parsedInput;
    }

    private static double calculateResult(double amount1, double amount2, Operator operator) {
        return switch (operator) {
            case ADDITION -> amount1 + amount2;
            case SUBTRACTION -> amount1 - amount2;
            case MULTIPLICATION -> amount1 * amount2;
            case DIVISION -> amount1 / amount2;
            default -> 0.0;
        };
    }

    private static boolean compareResult(double amount1, double amount2, Operator operator) {
        return switch (operator) {
            case LOWER -> amount1 < amount2;
            case GREATER -> amount1 > amount2;
            case EQUALS -> amount1 == amount2;
            default -> false;
        };
    }

    private static String normalizeCurrency(double amount, Currency currency, Currency resultCurrency) {
        if (currency != resultCurrency) {
            return String.valueOf(currency.convert(amount, resultCurrency));
        }
        return String.valueOf(amount);
    }
}
