package com.miniatm.okbankatm.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.miniatm.okbankatm.Model.PinNumberModel;
import com.miniatm.okbankatm.Services.NewPinNumberService;

@RestController
public class NewPinNumberController {

    @Autowired
    private NewPinNumberService NewPinService;

    @PostMapping("pingenerate")
    public ModelAndView createNewPin(@ModelAttribute PinNumberModel newPin, Model model) {
        Optional<PinNumberModel> Pinnumber = NewPinService.createPinNumber(newPin);
        ModelAndView mav;
        if (Pinnumber.isPresent()) {
            mav = new ModelAndView("Home");
            return mav;
        } else {
            mav = new ModelAndView("NewPinNumber");
            model.addAttribute("Error", "plese enter correct Accountnumber and uniqu pin number");
            return mav;
        }
    }

}
