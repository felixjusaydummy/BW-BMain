package com.ladykoala.repository;
import com.ladykoala.dao.BankAccountDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepository extends CrudRepository<BankAccountDao, Integer> {
    List<BankAccountDao> findByUserId(long userId);
}