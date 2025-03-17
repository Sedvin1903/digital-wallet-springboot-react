package com.stackroute.walletservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.walletservice.model.WalletModel;
import com.stackroute.walletservice.service.WalletService;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/digimon/v1")
public class TransactionController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/balance")
    public ResponseEntity<?> getWalletBalance( @RequestParam("walletId") String walletId) {
        try {
            double balance = walletService.showSenderBalance(Long.parseLong(walletId));
            System.out.println(balance);
            return new ResponseEntity<>(balance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error retrieving wallet balance", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/deposit")
    public ResponseEntity<?> depositAmount( @RequestParam("amount") String amount, @RequestParam("walletPin") String walletPin ,@RequestParam("walletId") String sourceWalletId) {
        boolean updatedWallet = walletService.depositAmount(amount, walletPin,sourceWalletId);
        return new ResponseEntity<String>("Money deposited successfully!!", HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount( @RequestParam("targetPhoneNumber") long targetPhoneNumber, @RequestParam("amount") double amount, @RequestParam("walletPin") long walletPin, @RequestParam("sourceWalletId") long sourceWalletId) {
        boolean updatedSourceWallet = walletService.transferAmount(targetPhoneNumber, amount, walletPin,sourceWalletId);
        return new ResponseEntity<String>("Payment Successfull !!", HttpStatus.OK);
    }
    @GetMapping("/showDenominations")
    public Map<Integer, Integer> showDenominations( @RequestParam("senderBalance") double senderBalance) {
        return walletService.showDenominations(senderBalance);
    }

}
