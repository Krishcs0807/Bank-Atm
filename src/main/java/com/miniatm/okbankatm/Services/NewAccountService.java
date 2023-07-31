package com.miniatm.okbankatm.Services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Repository.AcHolderRepository;
import com.miniatm.okbankatm.Services.Servicesinterface.NewAccountInterface;

@Service
public class NewAccountService implements NewAccountInterface {

    @Autowired
    private AcHolderRepository ACrep;

    @Override
    public Optional<AcHolderModel> createNewAccount(AcHolderModel newAccountHolder) {

        if (ACrep.existsById(newAccountHolder.getAccountNumber())||ACrep.existsByphoneNumber(newAccountHolder.getPhoneNumber())) {
        
            return Optional.empty();
        
        } else {
        
            newAccountHolder.setBalance(20000);
        
            return Optional.of(ACrep.save(newAccountHolder));
        
        }
    }

    public String findbyAc(String Ac){
        return ACrep.findById(Ac).get().getName();
    }


}
