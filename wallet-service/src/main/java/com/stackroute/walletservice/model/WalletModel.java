package com.stackroute.walletservice.model;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WalletModel {
    @Id
    private long walletId;
//    private double amount;
    private double balance;
//    private double senderBalance; // default
//    private String description;
//    private String status;
    private String walletPin;
    private long mobileNumber;
}
