import org.junit.Assert;
import org.junit.Test;
import org.mediafactoryexam.enums.Currency;
import org.mediafactoryexam.eshop.Operation;

public class OperationTest {

    @Test
    public void computationTest() {
        String example = "100 CZK + 1 EUR";
        String example1 = "100 CZK + 1 EUR + 1 USD";
        String example2 = "100 CZK * 5";
        String example3 = "100 CZK * 5 + 100 USD / 10";
        String example4 = "100 EUR - 10 USD / 2";

        double expectedAmount;
        String expectedResult;

        expectedAmount = 100 + Currency.EUR.convert(1, Currency.CZK);
        expectedResult = expectedAmount + " CZK";
        Assert.assertEquals(expectedResult, Operation.computeAmount(example));

        expectedAmount = 100 + Currency.EUR.convert(1, Currency.CZK) + Currency.USD.convert(1, Currency.CZK);
        expectedResult = expectedAmount + " CZK";
        Assert.assertEquals(expectedResult, Operation.computeAmount(example1));

        expectedAmount = 500;
        expectedResult = expectedAmount + " CZK";
        Assert.assertEquals(expectedResult, Operation.computeAmount(example2));

        expectedAmount = 500 + Currency.USD.convert(100, Currency.CZK) / 10;
        expectedResult = expectedAmount + " CZK";
        Assert.assertEquals(expectedResult, Operation.computeAmount(example3));

        expectedAmount = 100 - Currency.USD.convert(10, Currency.EUR) / 2;
        expectedResult = expectedAmount + " EUR";
        Assert.assertEquals(expectedResult, Operation.computeAmount(example4));
    }

    @Test
    public void comparisonTest() {
        String example = "1 USD = 22.23 CZK";
        String example1 = "10 USD > 10 CZK";
        String example2 = "10 EUR < 10 USD";

        Assert.assertTrue(Operation.compareAmount(example));
        Assert.assertTrue(Operation.compareAmount(example1));
        Assert.assertFalse(Operation.compareAmount(example2));
    }

    @Test
    public void roundTest() {
        String example = "1.23 USD";
        String example1 = "99.99 CZK";
        String example2 = "120.46 EUR";

        Assert.assertEquals("1.0 USD", Operation.round(example));
        Assert.assertEquals("100.0 CZK", Operation.round(example1));
        Assert.assertEquals("120.0 EUR", Operation.round(example2));
    }

    @Test
    public void exchangeTest() {
        String z = "100 CZK -> USD";
        String z1 = "100 CZK -> EUR";
        String z2 = "100 EUR -> USD";
        String z3 = "100 EUR -> CZK";
        String z4 = "100 USD -> CZK";
        String z5 = "100 USD -> EUR";
        String z6 = "100 CZK -> CZK";


        Assert.assertEquals(Currency.CZK.convert(100, Currency.USD) + " USD", Operation.exchange(z));
        Assert.assertEquals(Currency.CZK.convert(100, Currency.EUR) + " EUR", Operation.exchange(z1));
        Assert.assertEquals(Currency.EUR.convert(100, Currency.USD) + " USD", Operation.exchange(z2));
        Assert.assertEquals(Currency.EUR.convert(100, Currency.CZK) + " CZK", Operation.exchange(z3));
        Assert.assertEquals(Currency.USD.convert(100, Currency.CZK) + " CZK", Operation.exchange(z4));
        Assert.assertEquals(Currency.USD.convert(100, Currency.EUR) + " EUR", Operation.exchange(z5));
        Assert.assertEquals("100.0 CZK", Operation.exchange(z6));
    }
}
