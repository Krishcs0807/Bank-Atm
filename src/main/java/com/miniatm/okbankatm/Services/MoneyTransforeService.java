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
import com.miniatm.okbankatm.Services.Servicesinterface.MoneyTransforInterface;

@Service
public class MoneyTransforeService implements MoneyTransforInterface{

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

            Pin = pinNumber;

            return Optional.of(AcNumber);

        } else {

            return Optional.empty();

        }
    }
    

    public Optional<AcHolderModel> moneyTransfor(int TransforAmount, String TransforAccount) {
         AcHolderModel AcVerify1 = acHolderRep.findById(AcNumber).get();

        if (AcVerify1.getBalance()>=TransforAmount && acHolderRep.existsById(TransforAccount)) {

            String AmountReciverPin = pinRep.findById(TransforAccount).get().getPinNumber();

            AcHolderModel AcVerify = acHolderRep.findById(TransforAccount).get();

            AcVerify.setBalance(AcVerify.getBalance() + TransforAmount);

            acHolderRep.save(AcVerify);

            LocalDateTime localDateTime = LocalDateTime.now();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

            String formatterDate = dateFormatter.format(localDateTime);

            DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

            String formatterTime = TimeFormatter.format(localDateTime);

            StatementModel statementModel = new StatementModel();
            statementModel.setAccountNumber(TransforAccount);
            statementModel.setDate(formatterDate);
            statementModel.setTime(formatterTime);
            statementModel.setDebit(0);
            statementModel.setCredit(TransforAmount);
            statementModel.setBalance(AcVerify.getBalance());
            statementModel.setPinNumber(AmountReciverPin);

            statementService.entityMethod(statementModel);
           
            AmountSenderStatement(TransforAmount);

            return Optional.of(AcVerify);

        }else {

            return Optional.empty();

        }

    }


    public void AmountSenderStatement(int TransforAmmount) {

        AcHolderModel AcVerify = acHolderRep.findById(AcNumber).get();

        AcVerify.setBalance(AcVerify.getBalance() - TransforAmmount);

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
        statementModel.setDebit(TransforAmmount);
        statementModel.setCredit(0);
        statementModel.setBalance(AcVerify.getBalance());
        statementModel.setPinNumber(Pin);

        statementService.entityMethod(statementModel);

    }

}
