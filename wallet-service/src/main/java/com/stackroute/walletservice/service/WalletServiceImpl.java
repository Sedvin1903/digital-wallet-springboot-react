package com.stackroute.walletservice.service;

import com.stackroute.walletservice.model.WalletModel;
import com.stackroute.walletservice.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    @Override

    public double showSenderBalance(long walletId ) {
        Optional<WalletModel> optionalWallet = walletRepository.findById(walletId);
        if (optionalWallet.isPresent()) {
            return optionalWallet.get().getBalance();
        } else {
            throw new IllegalArgumentException("wallet id does not  exist");
        }
    }

    @Override
    public boolean depositAmount(String amount1,  String walletPin1, String sourceWalletId) {
        double amount = Double.parseDouble(amount1);
        long walletId = Long.parseLong(sourceWalletId);
        long walletPin = Long.parseLong(walletPin1);
        if (validatePin(walletId, walletPin)) {
            Optional<WalletModel> optionalWallet = walletRepository.findById(walletId);
            System.out.println("i m here at validate pin ");
            if (optionalWallet.isPresent() && optionalWallet.get().getMobileNumber() == walletPin) {
                System.out.println(" i m here at optional wallet");
                WalletModel wallet = optionalWallet.get();
                double currentBalance = wallet.getBalance();
                System.out.println(currentBalance);
                System.out.println(amount);
                System.out.println(currentBalance+amount);
                double total = currentBalance+amount;
                System.out.println(total);
                wallet.setBalance(total);
                System.out.println(wallet.getBalance());
                walletRepository.save(wallet);
                return true;
            } else {
                throw new IllegalArgumentException("Incorrect PIN: " + walletPin);
            }
        }
        else {
            throw new IllegalArgumentException("Invalid PIN: " + walletPin);
        }
    }

    @Override
    public boolean transferAmount(long targetPhoneNumber, double amount, long walletPin ,long sourceWalletId) {
        if (validatePin(sourceWalletId, walletPin)) {
            Optional<WalletModel> optionalSourceWallet = walletRepository.findById(sourceWalletId);
            if (optionalSourceWallet.isPresent()) {
                WalletModel sourceWallet = optionalSourceWallet.get();
                double sourceBalance = sourceWallet.getBalance();
                if (sourceBalance >= amount) {
                    sourceWallet.setBalance(sourceBalance - amount);
                   sourceWallet =  walletRepository.save(sourceWallet);
                    // Find target wallet by phone number
                    Optional<WalletModel> optionalTargetWallet = walletRepository.findByMobileNumber(targetPhoneNumber);
                    if (optionalTargetWallet.isPresent()) {
                        WalletModel targetWallet = optionalTargetWallet.get();
                        targetWallet.setBalance(targetWallet.getBalance() + amount);
                        targetWallet = walletRepository.save(targetWallet);
                        return true;
                    } else {
                        throw new IllegalArgumentException("Target wallet not found for phone number: " + targetPhoneNumber);
                    }
                } else {
                    throw new IllegalArgumentException("Insufficient balance in source wallet to transfer amount");
                }
            } else {
                throw new IllegalArgumentException("Source wallet not found for PIN: " + walletPin);
            }
        } else {
            throw new IllegalArgumentException("Invalid PIN: " + walletPin);
        }
    }

    @Override
    public boolean validatePin(long walletId ,long walletPin) {
        return walletRepository.existsById(walletId) && (walletRepository.findById(walletId).get().getMobileNumber() == walletPin);
    }

    @Override
    public Map<Integer,Integer> showDenominations( double senderBalance) {
        int[] denominations = new int[]{2000, 500, 200, 100, 50, 20,10,5,2,1};
        Map<Integer,Integer> results = new HashMap<>();

        double b = senderBalance;

        for (int denomination : denominations) {
            int count = (int) (b / denomination);

            if( denomination  == 2000 && count >0)
            {
                results.put(2000,count);
                b  =  ( b - count*2000);
            }if( denomination  == 500 && count >0)
            {
                results.put(500,count);
                b  =  ( b - count*500);
            }if( denomination  == 200 && count >0)
            {
                results.put(200,count);
                b  =  ( b - count*200);
            }if( denomination  == 100 && count >0)
            {
                results.put(100,count);
                b  =  ( b - count*100);
            }if( denomination  == 50 && count >0)
            {
                results.put(50,count);
                b  =  ( b - count*50);
            }if( denomination  == 20 && count >0)
            {
                results.put(20,count);
                b  =  ( b - count*20);
            }if( denomination  == 10 && count >0)
            {
                results.put(10,count);
                b  =  ( b - count*10);
            }if( denomination  == 5 && count >0)
            {
                results.put(5,count);
                b  =  ( b - count*5);
            }if( denomination  == 2 && count >0)
            {
                results.put(2,count);
                b  =  ( b - count*2);
            }if( denomination  == 1 && count >0)
            {
                results.put(1,count);
                b  =  ( b - count*1);
            }

        }
        return results;    // Returning the list of denominations
    }

}
