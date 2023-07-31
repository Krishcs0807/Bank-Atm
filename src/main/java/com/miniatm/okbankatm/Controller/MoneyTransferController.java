package com.miniatm.okbankatm.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Services.MoneyTransforeService;

@RestController
public class MoneyTransferController {

    @Autowired
    private MoneyTransforeService moneyTransfereService;

    @GetMapping("moneytransferView")
    public ModelAndView transferview(Model model) {
        ModelAndView mav = new ModelAndView("PinValid");
        model.addAttribute("pinurl", "transferValid");
        return mav;
    }

    @RequestMapping(value = "transferValid", method = RequestMethod.POST)
    public ModelAndView pinValidation(@RequestParam(value = "pinNumber", required = false) String pinNumber,
            Model model) {

        Optional<String> pinvalid = moneyTransfereService.pinValidation(pinNumber);
        ModelAndView mav;
        if (pinvalid.isPresent()) {
            mav = new ModelAndView("Moneytransfer");
            return mav;

        } else {
            mav = new ModelAndView("pinValid");
            model.addAttribute("Error", "Wrong Pin Number");
            return mav;

        }
    }

    @RequestMapping(value = "transferDetails", method = RequestMethod.POST)
    public ModelAndView TransforMoney(@RequestParam(value = "AcNumber", required = false) String AcNumber,
            @RequestParam(value = "Money", required = false) int Money, Model model) {

        Optional<AcHolderModel> Transfor = moneyTransfereService.moneyTransfor(Money, AcNumber);
        ModelAndView mav;
        if (Transfor.isPresent()) {
            model.addAttribute("msg", "Amount Transfer successfill");
            mav = new ModelAndView("Message");
            return mav;
        } else {
            model.addAttribute("msg", "AccountNumber is wrong Or Transferor's Account Insufficient Balance");
            mav = new ModelAndView("Moneytransfer");
            return mav;
        }

    }

}
