package com.miniatm.okbankatm.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Model.PinNumberModel;
import com.miniatm.okbankatm.Repository.AcHolderRepository;
import com.miniatm.okbankatm.Repository.PinNumberRepository;
import com.miniatm.okbankatm.Services.Servicesinterface.NewPinNumberInterface;

@Service
public class NewPinNumberService implements NewPinNumberInterface {

    @Autowired
    private PinNumberRepository PinRepository;

    @Autowired
    private AcHolderRepository AcRepository;

    @Override
    public Optional<PinNumberModel> createPinNumber(PinNumberModel createPinNumber) {

        if (PinRepository.existsById(createPinNumber.getAccountnumber())) {

            return Optional.empty();
            
        } else {
            
            Optional<AcHolderModel> acholder = AcRepository.findById(createPinNumber.getAccountnumber());

            if (acholder.isPresent()) {

                if (PinRepository.existsBypinNumber(createPinNumber.getPinNumber())) {

                    return Optional.empty();

                } else {

                    return Optional.of(PinRepository.save(createPinNumber));
                }

            } else {

                return Optional.empty();
            }
        }

    }

    
}
