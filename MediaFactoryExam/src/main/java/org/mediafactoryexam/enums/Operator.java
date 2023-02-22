package org.mediafactoryexam.enums;

public enum Operator {
    ADDITION ("+"),
    SUBTRACTION ("-"),
    MULTIPLICATION ("*"),
    DIVISION ("/");

    private String operatorChar;

    Operator (String operatorChar) {
        this.operatorChar = operatorChar;
    }

    public String getOperatorChar () {
        return operatorChar;
    }
}
