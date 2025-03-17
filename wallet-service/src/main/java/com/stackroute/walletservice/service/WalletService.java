package com.stackroute.walletservice.service;

import com.stackroute.walletservice.model.WalletModel;

import java.util.Map;

public interface WalletService {

    public double showSenderBalance(long walletId);

    public boolean depositAmount(String amount, String  walletPin, String walletId);

    public boolean transferAmount(long targetMobileNumber, double amount, long walletPin , long sourceWalletId);

    public boolean validatePin(long walletId ,long walletPin);

    //List<Integer> showDenominations(double balance);

    public Map<Integer, Integer> showDenominations( double balance);


}