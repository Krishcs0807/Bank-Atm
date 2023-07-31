package com.miniatm.okbankatm.Services.Servicesinterface;

import java.util.Optional;

import com.miniatm.okbankatm.Model.AcHolderModel;

public interface DepositInterface {
     public Optional<String> pinValidation(String pinNumber);

     public Optional<AcHolderModel> moneyDeposit(int depositAmount);
}
