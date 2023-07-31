package com.miniatm.okbankatm.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="statement")
@Data
public class StatementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="rowNo")
    private int RowNo;

    @Column(name="Date")
    private String date;

    @Column(name="Time")
    private String time;

    @Column(name="AccountNumber")
    private String accountNumber;

    @Column(name="credit")
    private int credit;

    @Column(name="debit")
    private int debit;

    @Column(name="Balance")
    private int balance;

    @Column(name="Pin")
    private String pinNumber;

   
    
}

