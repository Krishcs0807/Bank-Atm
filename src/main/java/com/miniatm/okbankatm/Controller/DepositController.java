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
import com.miniatm.okbankatm.Services.DepositService;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("Depositview")
    public ModelAndView depositView(Model model) {
        ModelAndView mav = new ModelAndView("PinValid");
        model.addAttribute("pinurl", "DepositPinvalid");
        return mav;
    }

    @RequestMapping(value = "/DepositPinvalid", method = RequestMethod.POST)
    public ModelAndView pinValidation(@RequestParam(value = "pinNumber", required = false) String pinNumber,
            Model model) {
        Optional<String> pinvalid = depositService.pinValidation(pinNumber);
        ModelAndView mav;
        if (pinvalid.isPresent()) {
            mav = new ModelAndView("Amount");
            model.addAttribute("Amounturl", "depositAmount");
            return mav;
        } else {
            mav = new ModelAndView("DepositpinValid");
            model.addAttribute("Error", "Wrong Pin Number");
            return mav;
        }
    }

    @RequestMapping(value = "/depositAmount", method = RequestMethod.POST)
    public ModelAndView moneyDeposit(@RequestParam(value = "Money", required = false) int Money, Model model) {
        Optional<AcHolderModel> deposit = depositService.moneyDeposit(Money);
        ModelAndView mav;
        if (deposit.isPresent()) {
            model.addAttribute("msg", "Deposit Successfull");
            mav = new ModelAndView("Message");
            return mav;

        } else {
            model.addAttribute("msg", "Something wrong");
            mav = new ModelAndView("Message");
            return mav;

        }

    }

}
