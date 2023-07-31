package com.miniatm.okbankatm.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="accountholders")
@Data 
public class AcHolderModel {

    @Id
    @Column(name="AccountNumber")
    private String accountNumber;

    @Column(name="Name")
    private String name;

    @Column(name="PhoneNumber")
    private String phoneNumber;

    @Column(name="BalanceAmount")
    private int balance;
    
}
