package com.ladykoala.controller;
import com.ladykoala.model.DtoBankAccount;
import com.ladykoala.model.RequestCashIn;
import com.ladykoala.model.RequestLinkBank;
import com.ladykoala.model.ResponseCashIn;
import com.ladykoala.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin()
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/account/banks", method = RequestMethod.GET)
    public List<DtoBankAccount> getAccount(HttpServletResponse response) {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));
        return bankAccountService.findByUserId(userid);
    }

    @RequestMapping(value = "/account/linkbanks", method = RequestMethod.POST)
    public DtoBankAccount linkAccount(@RequestBody RequestLinkBank dto, HttpServletResponse response) throws Exception {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));
        DtoBankAccount dtores = bankAccountService.linkBank(dto, userid);
        if(dtores!=null){
            return dtores;
        }else{
            throw new Exception("Account cannot be found");
        }
    }

    @RequestMapping(value = "/account/cashin", method = RequestMethod.POST)
    public ResponseCashIn cashIn(@RequestBody RequestCashIn dto, HttpServletResponse response) throws Exception {
        long userid = Long.parseLong(response.getHeader("userid"));
        try{
            return bankAccountService.cashIn(dto, userid);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("[BankAccountController][CashIn] Internal Server Error");
        }
    }
}