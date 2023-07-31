package com.miniatm.okbankatm.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Model.PinNumberModel;
import com.miniatm.okbankatm.Model.StatementModel;
import com.miniatm.okbankatm.Repository.AcHolderRepository;
import com.miniatm.okbankatm.Repository.PinNumberRepository;
import com.miniatm.okbankatm.Services.Servicesinterface.DepositInterface;

@Service
public class DepositService implements DepositInterface {

    @Autowired
    private PinNumberRepository pinRep;

    @Autowired
    private AcHolderRepository acHolderRep;

    @Autowired
    private StatementService statementService;

    private String AcNumber;
    private String Pin;

    public Optional<String> pinValidation(String pinNumber) {

        if (pinRep.existsBypinNumber(pinNumber)) {

            Optional<PinNumberModel> pinVerify = pinRep.findBypinNumber(pinNumber);

            AcNumber = pinVerify.get().getAccountnumber();

            this.Pin = pinNumber;
            return Optional.of(AcNumber);

        } else {

            return Optional.empty();

        }
    }

    public Optional<AcHolderModel> moneyDeposit(int depositAmount) {

        AcHolderModel AcVerify = acHolderRep.findById(AcNumber).get();

        if (acHolderRep.existsById(AcNumber)) {
            AcVerify.setBalance(AcVerify.getBalance() + depositAmount);
            acHolderRep.save(AcVerify);

            LocalDateTime localDateTime = LocalDateTime.now();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            String formatterDate = dateFormatter.format(localDateTime);

            DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formatterTime = TimeFormatter.format(localDateTime);

            StatementModel statementModel = new StatementModel();
            statementModel.setAccountNumber(AcNumber);
            statementModel.setDate(formatterDate);
            statementModel.setTime(formatterTime);
            statementModel.setDebit(0);
            statementModel.setCredit(depositAmount);
            statementModel.setBalance(AcVerify.getBalance());
            statementModel.setPinNumber(Pin);

            statementService.entityMethod(statementModel);

            return Optional.of(AcVerify);

        } else {

            return Optional.empty();

        }

    }

}
