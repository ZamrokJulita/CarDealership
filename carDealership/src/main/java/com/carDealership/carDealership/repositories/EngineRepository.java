package com.carDealership.carDealership.repositories;

import com.carDealership.carDealership.models.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Integer> {
}
