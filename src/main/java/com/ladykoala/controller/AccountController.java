package com.ladykoala.controller;
import com.ladykoala.model.DtoAccount;
import com.ladykoala.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin()
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public DtoAccount getAccount(HttpServletResponse response) {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));
        return accountService.findByUserId(userid);
    }

}