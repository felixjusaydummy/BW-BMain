package com.ladykoala.service;

import com.ladykoala.dao.AccountDao;
import com.ladykoala.dao.BankAccountDao;
import com.ladykoala.dao.BankDao;
import com.ladykoala.model.*;
import com.ladykoala.repository.AccountRepository;
import com.ladykoala.repository.BankAccountRepository;
import com.ladykoala.repository.BankRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BankAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankRepository  bankRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public List<DtoBankAccount> findByUserId(long userId){
        List<BankAccountDao> dao = bankAccountRepository.findByUserId(userId);
        return dao.stream().map(m->convertToDto(m)).collect(Collectors.toList());
    }

    private DtoBankAccount convertToDto(BankAccountDao account){
        DtoBankAccount ac = new DtoBankAccount();
        ac.setUserId(account.getUserId());
        ac.setBankAccount(account.getBankAccount());
        ac.setBankName(account.getBankName());
        ac.setAccountNumber(account.getAccountNumber());
        return ac;
    }

    public DtoBankAccount linkBank(RequestLinkBank request, long userid)throws Exception{

        AccountDao acc =  accountRepository.findByUserId(userid);
        if(!acc.isVerified()){
            throw new Exception("Account is not yet verified");
        }

        BankDao bankDao = bankRepository.findByName(request.getBankName());
        if(bankDao == null){
            throw new Exception("Bank is not yet registered");
        }

        List<BankAccountDao> userBanks = bankAccountRepository.findByUserId(userid);

        BankAccountDao identicalDao = userBanks.stream().filter(b-> b.getBankAccount().equalsIgnoreCase(bankDao.getAccount())).findAny().orElse(null);
        if(identicalDao!=null){
            throw new Exception("Bank Account is already registered");
        }

        HttpEntity<ResponseLinkBank> requestHttp = new HttpEntity(request);
        ResponseEntity<ResponseLinkBank> linkResult = restTemplate().postForEntity(bankDao.getRestHost()+"/linkaccount", requestHttp, ResponseLinkBank.class);
        ResponseLinkBank bankconnect = linkResult.getBody();
        if (bankconnect!=null && bankconnect.getAccount()!=null){
            BankAccountDao dao = new BankAccountDao();
            dao.setBankAccount(bankconnect.getBankAccount());
            dao.setBankName(bankconnect.getBankName());
            dao.setAccountNumber(bankconnect.getAccount());
            dao.setUserId(userid);
            dao.setCordakycid(bankconnect.getCordaFlowId());
            BankAccountDao daores = bankAccountRepository.save(dao);

            acc.setCordakycid(bankconnect.getCordaFlowId());
            accountRepository.save(acc);

            return convertToDto(daores);
        }else{
            return null;
        }

    }

    public ResponseCashIn cashIn(RequestCashIn dto, long userid)throws Exception{

        BankDao bankDao = bankRepository.findByName(dto.getBackName());
        if(bankDao == null){
            throw new Exception("Bank is not yet registered");
        }

        try {
            RequestBankTransferFund request = new RequestBankTransferFund();
            request.setAccount( dto.getAccountNumber());
            request.setAmount(dto.getAmount());

            String url = bankDao.getRestHost()+"/transferCash";
            System.out.println("url: "+ url);
            System.out.println("url: "+ request.toString());

            HttpEntity<String> requestHttp = new HttpEntity(request);

            ResponseEntity<ResponseCashIn> res = restTemplate().exchange(url, HttpMethod.POST, requestHttp, ResponseCashIn.class);
            System.out.print("res: "+ res);

            if (res.getStatusCode() == HttpStatus.ACCEPTED || res.getStatusCode() == HttpStatus.OK ){
                System.out.println("cashin body: "+ res.getBody().toString());

                AccountDao acc =  accountRepository.findByUserId(userid);
                acc.setWalletBalance(acc.getWalletBalance() + res.getBody().getAmount());
                accountRepository.save(acc);

                return res.getBody();
            }else{
                System.out.println("cashin: null");
                return null;
            }
        }catch (Exception e) {
            System.out.println("exception: "+ e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}

//            HttpEntity<?> requestHttp = new HttpEntity(request.toString());
//            ResponseEntity<ResponseCashIn> res = restTemplate().exchange(url,  HttpMethod.POST,requestHttp, ResponseCashIn.class);
//            ResponseEntity<String> res = restTemplate().postForEntity(url, requestHttp, String.class);


//            HttpEntity<ResponseCashIn> requestHttp = new HttpEntity(request.toString());
//            ResponseEntity<ResponseCashIn> res = restTemplate().postForEntity(url, requestHttp, ResponseCashIn.class);
//            System.out.print("res: "+ res.toString());
