package org.mediafactoryexam.eshop;

import java.util.List;

public interface Bank {

    Wallet createWallet();

    void deleteWallet(int walletId);

    int getWalletCount();

    List<Wallet> getWallets();

    Wallet getWallet(int walletId);
}
