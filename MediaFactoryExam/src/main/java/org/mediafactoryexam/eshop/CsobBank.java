package org.mediafactoryexam.eshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsobBank implements Bank {

    private final List<Wallet> wallets;

    public CsobBank() {
        wallets = new ArrayList<>();
    }

    @Override
    public Wallet createWallet() {
        int newId = wallets.size();
        Wallet newWallet = new Wallet(newId);
        wallets.add(newWallet);
        return newWallet;
    }

    @Override
    public void deleteWallet(int walletId) {
        List<Wallet> toDelete = wallets.stream().filter(wallet -> wallet.getWalletId() == walletId).toList();
        toDelete.forEach(wallets::remove);
    }

    @Override
    public int getWalletCount() {
        return wallets.size();
    }

    @Override
    public List<Wallet> getWallets() {
        return wallets;
    }

    @Override
    public Wallet getWallet(int walletId) {
        Optional<Wallet> result = wallets.stream().filter(wallet -> wallet.getWalletId() == walletId).findFirst();
        return result.orElse(null);
    }
}
