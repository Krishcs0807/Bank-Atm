package com.miniatm.okbankatm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.miniatm.okbankatm.Services.BalanceService;

@Controller
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("balance")
    public ModelAndView balanceView(Model model) {
        ModelAndView mav = new ModelAndView("PinValid");
        model.addAttribute("pinurl", "BalancePinvalid");
        return mav;
    }

    @RequestMapping(value = "/BalancePinvalid", method = RequestMethod.POST)
    public ModelAndView balance(@RequestParam(value = "pinNumber", required = false) String pinNumber, Model model) {
        String pinvalid = balanceService.pinValidation(pinNumber);
        ModelAndView mav;
        if (pinvalid != null) {
            mav = new ModelAndView("Message");
            model.addAttribute("Amount", "Balance : " + pinvalid);
            return mav;
        } else {
            mav = new ModelAndView("pinValid");
            model.addAttribute("Error", "Wrong Pin Number");
            return mav;
        }
    }

}
