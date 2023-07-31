package com.miniatm.okbankatm.Services.Servicesinterface;


import java.util.Optional;

import com.miniatm.okbankatm.Model.AcHolderModel;

public interface NewAccountInterface {

     public Optional<AcHolderModel> createNewAccount(AcHolderModel newAccountHolder);

     
    
}
