package com.miniatm.okbankatm.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.miniatm.okbankatm.Model.StatementModel;

public interface StatementRepository extends JpaRepository<StatementModel,Integer> {

    List<StatementModel> findBypinNumber(String pinNumber);

    boolean existsByaccountNumber(String accountNumber);

    boolean existsBypinNumber(String pinNumber);

}
