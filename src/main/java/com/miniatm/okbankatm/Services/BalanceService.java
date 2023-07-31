package com.miniatm.okbankatm.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Model.PinNumberModel;
import com.miniatm.okbankatm.Repository.AcHolderRepository;
import com.miniatm.okbankatm.Repository.PinNumberRepository;

@Service
public class BalanceService {

     @Autowired
    private PinNumberRepository pinRep;

    @Autowired
    private AcHolderRepository acHolderRep;

    

    public String pinValidation(String pinNumber) {

        if (pinRep.existsBypinNumber(pinNumber)) {
           
            Optional<PinNumberModel> pinVerify = pinRep.findBypinNumber(pinNumber);
            AcHolderModel AcVerify = acHolderRep.findById(pinVerify.get().getAccountnumber()).get();

        if (acHolderRep.existsById(pinVerify.get().getAccountnumber())) {

            return String.valueOf(AcVerify.getBalance());
        }else{
            return null;
        }

        } else {

            return null;

        }
    }

    
    
}
