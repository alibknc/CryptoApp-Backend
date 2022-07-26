package com.project.cryptoapp.repositories;

import com.project.cryptoapp.entities.AggTradeObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggTradeObjectRepository extends JpaRepository<AggTradeObject, Long> {

}
