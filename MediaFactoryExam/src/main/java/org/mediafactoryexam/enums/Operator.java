package org.mediafactoryexam.enums;

public enum Operator {
    ADDITION ("+"),
    SUBTRACTION ("-"),
    MULTIPLICATION ("*"),
    DIVISION ("/"),
    GREATER (">"),
    LOWER ("<"),
    EQUALS ("="),
    EXCHANGE ("->");

    private String operatorChar;

    Operator (String operatorChar) {
        this.operatorChar = operatorChar;
    }

    public String getOperatorChar () {
        return operatorChar;
    }
}
