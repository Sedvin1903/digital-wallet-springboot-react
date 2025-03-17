package com.stackroute.walletservice.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.walletservice.model.WalletModel;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletModel,Long> {

//
//    Optional<WalletModel> findByWalletPin(int walletPin);

    Optional<WalletModel> findByMobileNumber(long mobileNumber);
}
