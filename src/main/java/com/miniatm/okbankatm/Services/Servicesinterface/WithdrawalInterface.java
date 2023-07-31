package com.miniatm.okbankatm.Services.Servicesinterface;

import java.util.Optional;

import com.miniatm.okbankatm.Model.AcHolderModel;

public interface WithdrawalInterface {
    
    public Optional<String> pinValidation(String pinNumber);

     public Optional<AcHolderModel> moneyWithdraw(int withdraAmount);
}
