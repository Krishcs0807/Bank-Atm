package com.miniatm.okbankatm.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="pinnumber")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PinNumberModel {

    @Id
	@Column(name="AccountNumber")
    private String Accountnumber;

   
    @Column(name="ATMPin")
    private String pinNumber;
    
}
