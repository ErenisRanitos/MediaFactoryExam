package org.mediafactoryexam.utils;

/**
 * Auxiliary class for managing the conversion values
 * In SpringBoot, this would go in the configuration.properties
 * and used with @Value annotation
 */
public class ConversionConfig {
    public static final double EUR2CZK = 26.0;
    public static final double CZK2EUR = 1 / EUR2CZK;
    public static final double USD2CZK = 22.23;
    public static final double CZK2USD = 1 / USD2CZK;
    public static final double EUR2USD = 1.06;
    public static final double USD2EUR = 1 / EUR2USD;
}
