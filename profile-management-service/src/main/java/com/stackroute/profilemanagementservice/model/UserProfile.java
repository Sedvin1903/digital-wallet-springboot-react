package com.stackroute.profilemanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
public class UserProfile {

    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private String aadharNumber;
    private String panNo;
    private String alternateMobileNumber;
    private String alternateEmailId;
    private int bankId;
    private String password;
    private String bankName;
    private String ifscCode;
    private String accountNumber;
    private String walletId;
}
