package com.miniatm.okbankatm.Services.Servicesinterface;

import java.util.List;
import java.util.Optional;

import com.miniatm.okbankatm.Model.StatementModel;

public interface StatementInterface {
    
    public void entityMethod(StatementModel statementModel);

    public Optional<List<StatementModel>> accountStatement(String accountNumber, short pin);
}
