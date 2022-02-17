package com.ladykoala.service;

import com.ladykoala.dao.AccountDao;
import com.ladykoala.dao.DigitalAssetDao;
import com.ladykoala.model.DtoAccount;
import com.ladykoala.model.RequestDigitalAsset;
import com.ladykoala.repository.AccountRepository;
import com.ladykoala.repository.DigitalAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DigitalAssetService {

    @Autowired
    private DigitalAssetsRepository digitalAssetsRepository;

    public void addAssets(List<RequestDigitalAsset> assets, long userid)throws Exception{
        List<DigitalAssetDao> res = new ArrayList<>();
        for(RequestDigitalAsset a: assets){
            DigitalAssetDao dao = new DigitalAssetDao();
            dao.setUserId(userid);
            dao.setCurrency(a.getCurrency());
            dao.setHost(a.getHost());
            dao.setValue(a.getValue());
            dao.setValidated(a.isValidated());
            res.add(dao);
        }
        digitalAssetsRepository.saveAll(res);
    }

}
