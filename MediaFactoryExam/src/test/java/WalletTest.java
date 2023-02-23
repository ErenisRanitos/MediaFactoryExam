import org.junit.Assert;
import org.junit.Test;
import org.mediafactoryexam.enums.Currency;
import org.mediafactoryexam.eshop.Wallet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WalletTest {

    private final double amount = 100.0;

    @Test
    public void createEmptyWalletTest() {
        Wallet w = new Wallet(0);
        Assert.assertNotNull(w);
        Assert.assertEquals(0, w.getWalletId());
        assertWalletAmounts(0, w);
    }

    @Test
    public void createFullWalletTest() {
        Wallet w = createWallet(amount);

        Assert.assertNotNull(w);
        Assert.assertEquals(0, w.getWalletId());
        assertWalletAmounts(amount, w);
    }

    @Test
    public void totalAmountTest() {
        Wallet w = createWallet(amount);

        Assert.assertNotNull(w);

        double expectedCZK = amount + Currency.USD.convert(amount, Currency.CZK) + Currency.EUR.convert(amount, Currency.CZK);
        double expectedUSD = Currency.CZK.convert(amount, Currency.USD) + amount + Currency.EUR.convert(amount, Currency.USD);
        double expectedEUR = Currency.CZK.convert(amount, Currency.EUR) + Currency.USD.convert(amount, Currency.EUR) + amount;

        Assert.assertEquals(expectedCZK, w.totalAmount(Currency.CZK), 0.001);
        Assert.assertEquals(expectedCZK, w.totalAmount("CZK"), 0.001);
        Assert.assertEquals(expectedUSD, w.totalAmount(Currency.USD), 0.001);
        Assert.assertEquals(expectedUSD, w.totalAmount("USD"), 0.001);
        Assert.assertEquals(expectedEUR, w.totalAmount(Currency.EUR), 0.001);
        Assert.assertEquals(expectedEUR, w.totalAmount("EUR"), 0.001);
    }

    @Test
    public void addAmountTest() {
        Wallet w = createWallet(amount);

        w.addAmount(amount, Currency.CZK);
        w.addAmount(amount, Currency.USD);
        w.addAmount(amount, Currency.EUR);
        assertWalletAmounts(2 * amount, w);

        w.addAmount(amount, "CZK");
        w.addAmount(amount, "USD");
        w.addAmount(amount, "EUR");
        assertWalletAmounts(3 * amount, w);
    }

    private Wallet createWallet(double amount) {
        Map<Currency, Double> testData = new HashMap<>();
        testData.put(Currency.CZK, amount);
        testData.put(Currency.USD, amount);
        testData.put(Currency.EUR, amount);

        return new Wallet(testData, 0);
    }

    private void assertWalletAmounts(double amount, Wallet w) {
        Arrays.stream(Currency.values()).forEach(currency ->
                Assert.assertEquals(amount, w.getAmount(currency), 0));
    }
}
