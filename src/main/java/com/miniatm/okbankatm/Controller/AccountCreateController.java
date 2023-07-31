package com.miniatm.okbankatm.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miniatm.okbankatm.Model.AcHolderModel;
import com.miniatm.okbankatm.Services.NewAccountService;

@Controller
public class AccountCreateController {
    
    @Autowired
    private NewAccountService NewAcService;

   @GetMapping("CreateAccount")
   public String AccountCreatepage(){

    return"NewAccount";
   }

    @PostMapping("newAccount")
    public ModelAndView newAcCreate(@ModelAttribute AcHolderModel newAcHolder,Model model){
        ModelAndView mav;
        Optional<AcHolderModel> NewCustomer=NewAcService.createNewAccount(newAcHolder);
        if(NewCustomer.isPresent()){
             mav=new ModelAndView("NewPinNumber");
            return mav;
        }else{
             mav=new ModelAndView("NewAccount");
           model.addAttribute("Error","This AccountNumber Already exites");
           return mav;
        }
    }
        
 
      
        
    
    
}
