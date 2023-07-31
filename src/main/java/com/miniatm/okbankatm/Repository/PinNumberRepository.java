package com.miniatm.okbankatm.Repository;



import java.util.Optional;


import org.springframework.data.repository.CrudRepository;


import com.miniatm.okbankatm.Model.PinNumberModel;


public interface PinNumberRepository extends CrudRepository<PinNumberModel,String> {

     Optional<PinNumberModel> findBypinNumber(String pinNumber);
     
     
     boolean existsBypinNumber(String pinNumber);

    
}
