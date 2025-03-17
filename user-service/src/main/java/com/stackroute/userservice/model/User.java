package com.stackroute.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//<<<<<<< HEAD
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
//=======
import lombok.ToString;
//>>>>>>> e05cd25 (implemented receiver end in rabbitmq)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(name = "First_Name",nullable = false)
	private String firstName;
	@Column(name = "Last_Name",nullable = false)
	private String lastName;
	@Column(name = "Password",nullable = false)
	private String password;
	@Column(name = "Email_Id",unique = true,nullable = false)
	private String emailId;
	@Column(name = "Mobile_Number",nullable = false, unique = true)
	private String mobileNumber;
	private long walletId;
}
