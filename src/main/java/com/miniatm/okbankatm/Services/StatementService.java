package com.miniatm.okbankatm.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniatm.okbankatm.Model.StatementModel;
import com.miniatm.okbankatm.Repository.StatementRepository;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    public void entityMethod(StatementModel statementModel) {

        statementRepository.save(statementModel);

    }

    public List<StatementModel> accountStatement(String accountNumber, String pin) {

        if (statementRepository.existsBypinNumber(pin) && statementRepository.existsByaccountNumber(accountNumber)) {

            return statementRepository.findBypinNumber(pin);


        } else {

            return null;

        }

    }

}
