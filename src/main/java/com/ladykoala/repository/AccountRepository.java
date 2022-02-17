package com.ladykoala.repository;
import com.ladykoala.dao.AccountDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<AccountDao, Integer> {
    AccountDao findByUserId(long userId);

    @Modifying
    @Query("update AccountDao a set a.verified = :verified where a.id = :id")
    void setVerified (@Param("verified") boolean verified, @Param("id") long id);
}