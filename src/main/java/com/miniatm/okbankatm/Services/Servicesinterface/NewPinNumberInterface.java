package com.miniatm.okbankatm.Services.Servicesinterface;

import java.util.Optional;

import com.miniatm.okbankatm.Model.PinNumberModel;

public interface NewPinNumberInterface {
    
    public Optional<PinNumberModel> createPinNumber(PinNumberModel createPinNumber);
}
