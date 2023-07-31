package com.miniatm.okbankatm.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.miniatm.okbankatm.Model.AcHolderModel;

public interface AcHolderRepository extends JpaRepository<AcHolderModel,String> {

    boolean existsByphoneNumber(String PNumber);
    
}
