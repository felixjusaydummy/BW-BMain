package com.ladykoala.repository;
import com.ladykoala.dao.BankDao;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<BankDao, Integer> {
    BankDao findByAccount(String account);
    BankDao findByName(String account);
}