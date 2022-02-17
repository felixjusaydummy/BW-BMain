package com.ladykoala.repository;
import com.ladykoala.dao.AccountDao;
import com.ladykoala.dao.AccountVerifiedIDDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountVerifierRepository extends CrudRepository<AccountVerifiedIDDao, Integer> {

    List<AccountVerifiedIDDao> findByUserId(long userId);
}