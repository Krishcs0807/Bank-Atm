package com.miniatm.okbankatm.Services.Servicesinterface;

import java.util.Optional;

import com.miniatm.okbankatm.Model.AcHolderModel;

public interface MoneyTransforInterface {
    
    public Optional<String> pinValidation(String pinNumber);

    public Optional<AcHolderModel> moneyTransfor(int TransforAmount, String TransforAccount);

    public void AmountSenderStatement(int TransforAmmount);
}
