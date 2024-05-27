package com.webwork.currencyexchangeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webwork.currencyexchangeservice.beans.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long>{

	List<ExchangeValue> findByFromAndTo(String from, String to);

	
}
