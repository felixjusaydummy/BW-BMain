package com.ladykoala.repository;
import com.ladykoala.dao.DigitalAssetDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DigitalAssetsRepository extends CrudRepository<DigitalAssetDao, Integer> {
    List<DigitalAssetDao> findByUserId(long userId);
}