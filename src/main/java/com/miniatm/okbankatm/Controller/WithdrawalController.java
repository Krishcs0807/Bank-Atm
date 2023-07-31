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
import com.miniatm.okbankatm.Services.WithdrawalService;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawService;

    @GetMapping("Withdrawalview")
    public ModelAndView depositView(Model model) {
        ModelAndView mav = new ModelAndView("PinValid");
        model.addAttribute("pinurl", "WithdrawPinvalid");
        return mav;
    }

    @RequestMapping(value = "/WithdrawPinvalid", method = RequestMethod.POST)
    public ModelAndView pinValidation(@RequestParam(value = "pinNumber", required = false) String pinNumber,
            Model model) {
        Optional<String> pinvalid = withdrawService.pinValidation(pinNumber);
        ModelAndView mav;
        if (pinvalid.isPresent()) {
            mav = new ModelAndView("Amount");
            model.addAttribute("Amounturl", "withdraAmount");
            return mav;
        } else {
            mav = new ModelAndView("pinValid");
            model.addAttribute("Error", "Wrong Pin Number");
            return mav;
        }
    }

    @RequestMapping(value = "withdraAmount", method = RequestMethod.POST)
    public ModelAndView moneyWithdrawal(@RequestParam(value = "Money", required = false) int Money, Model model) {
        Optional<AcHolderModel> Withdr = withdrawService.moneyWithdraw(Money);
        ModelAndView mav;
        if (Withdr.isPresent()) {
            model.addAttribute("msg", "Collect the Case");
            mav = new ModelAndView("Message");
            return mav;

        } else {
            model.addAttribute("msg", "inefcian balance");
            mav = new ModelAndView("Message");
            return mav;
        }

    }

}
