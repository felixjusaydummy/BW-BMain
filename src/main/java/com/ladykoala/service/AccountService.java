package com.ladykoala.service;

import com.ladykoala.dao.AccountDao;
import com.ladykoala.model.DtoAccount;
import com.ladykoala.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public DtoAccount findByUserId(long userid){
        AccountDao dao = accountRepository.findByUserId(userid);
        return convertToDto(dao, userid);
    }

    private DtoAccount convertToDto(AccountDao account, long userid){
        DtoAccount ac = new DtoAccount();
        ac.setUserId(userid);
        ac.setLastname(account.getLastname());
        ac.setFirstname(account.getFirstname());
        ac.setBirthday(account.getBirthday());
        ac.setAddress(account.getAddress());
        ac.setEmail(account.getEmail());
        ac.setContactNo(account.getContactNo());
        ac.setVerified(account.isVerified());
        return ac;
    }

}
