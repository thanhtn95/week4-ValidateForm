package userAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import userAccount.model.UserAccount;
import userAccount.service.UserAccountService;

@Controller
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;
    @GetMapping("")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("/account/index");
        modelAndView.addObject("userAccount",new UserAccount());
        return modelAndView;
    }

    @PostMapping("/doAddAccount")
    public ModelAndView doAddNewAccount(@Validated @ModelAttribute("userAccount") UserAccount userAccount, BindingResult bindingResult){
        new UserAccount().validate(userAccount,bindingResult);
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/account/index");
            modelAndView.addObject("userAccount",userAccount);
            return modelAndView;
        }
        userAccountService.save(userAccount);
        ModelAndView modelAndView = new ModelAndView("/account/index");
        modelAndView.addObject("userAccount",new UserAccount());
        modelAndView.addObject("msg","Added A New Account");
        return modelAndView;
    }
}
