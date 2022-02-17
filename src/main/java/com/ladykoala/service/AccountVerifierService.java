package com.ladykoala.service;

import com.ladykoala.dao.AccountDao;
import com.ladykoala.dao.AccountVerifiedIDDao;
import com.ladykoala.model.DtoAccountVerifiedID;
import com.ladykoala.repository.AccountRepository;
import com.ladykoala.repository.AccountVerifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountVerifierService {
    static enum ValidIDNames {
        SSS,
        LTO,
        GSIS,
        BIR
    }



    @Autowired
    private AccountVerifierRepository accountVerifiedIDRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void addAccountVerifiedIDs(List<DtoAccountVerifiedID> verifiedIds, long userId) throws Exception {
        verifyIDNames(verifiedIds);
        List<AccountVerifiedIDDao> list = verifiedIds.stream().map(idv -> {
            AccountVerifiedIDDao iddao = new AccountVerifiedIDDao();
            iddao.setUserId(userId);
            iddao.setIdname(idv.getIdname());
            iddao.setIdnumber(idv.getIdnumber());
            return iddao;
        }).collect(Collectors.toList());
        accountVerifiedIDRepository.saveAll(list);

        AccountDao acc =  accountRepository.findByUserId(userId);
        accountRepository.setVerified(true,acc.getId());
    }

    public List<DtoAccountVerifiedID> findByUserId(long userId){
        List<AccountVerifiedIDDao> list = accountVerifiedIDRepository.findByUserId(userId);
        return convertToDto(list);
    }

    private List<DtoAccountVerifiedID> convertToDto(List<AccountVerifiedIDDao> list){
        return list.stream().map(idv->{
            DtoAccountVerifiedID dto = new DtoAccountVerifiedID();
            dto.setUserId(idv.getUserId());
            dto.setIdname(idv.getIdname());
            dto.setIdnumber(idv.getIdnumber());
            return dto;
        }).collect(Collectors.toList());
    }

    private void verifyIDNames(List<DtoAccountVerifiedID> list)throws Exception{
        for(DtoAccountVerifiedID dto: list){
            boolean flag = false;
            for(ValidIDNames idn: ValidIDNames.values()){
                if(idn.name().equalsIgnoreCase(dto.getIdname())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                throw new Exception("Undefine ID Name: "+dto.getIdname());
            }
        }
    }
}
