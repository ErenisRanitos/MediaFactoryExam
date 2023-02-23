import org.junit.Assert;
import org.junit.Test;
import org.mediafactoryexam.eshop.Bank;
import org.mediafactoryexam.eshop.CsobBank;

public class CsobBankTest {

    @Test
    public void createBank() {
        Bank csob = new CsobBank();
        Assert.assertNotNull(csob);
    }

    @Test
    public void createWalletInBank() {
        Bank bank = new CsobBank();

        bank.createWallet();
        Assert.assertEquals(1, bank.getWalletCount());

        bank.createWallet();
        Assert.assertEquals(2, bank.getWalletCount());

        bank.createWallet();
        Assert.assertEquals(3, bank.getWalletCount());

        Assert.assertEquals(3, bank.getWalletCount());
        Assert.assertNotNull(bank.getWallets());

        for (int i = 0; i < bank.getWalletCount(); i++) {
            Assert.assertNotNull(bank.getWallet(i));
        }
    }

    @Test
    public void deleteWalletInBank() {
        Bank bank = new CsobBank();

        bank.createWallet();
        bank.createWallet();
        bank.createWallet();

        for (int i = 0; i < bank.getWalletCount(); i++) {
            bank.deleteWallet(i);
            Assert.assertNull(bank.getWallet(i));
        }
    }
}
