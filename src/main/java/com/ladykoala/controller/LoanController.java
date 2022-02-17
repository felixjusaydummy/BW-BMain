package com.ladykoala.controller;

import com.ladykoala.model.DtoAccount;
import com.ladykoala.model.RequestApplyLoan;
import com.ladykoala.model.RequestCashIn;
import com.ladykoala.model.ResponseCashIn;
import com.ladykoala.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin()
public class LoanController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/loan/getloans", method = RequestMethod.GET)
    public void getLoans(HttpServletResponse response) {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));

        DtoAccount account = accountService.findByUserId(userid);

    }

    @RequestMapping(value = "/loan/request", method = RequestMethod.POST)
    public String applyLoan(@RequestBody RequestApplyLoan dto, HttpServletResponse response) throws Exception {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));
        try{
//            ResponseCashIn res = bankAccountService.cashIn(dto, userid);
            return "Done";
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("[LoanController][CashIn] Internal Server Error");
        }
    }
}
