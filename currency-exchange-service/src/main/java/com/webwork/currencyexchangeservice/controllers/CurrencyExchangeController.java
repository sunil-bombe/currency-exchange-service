package com.webwork.currencyexchangeservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webwork.currencyexchangeservice.beans.ExchangeValue;
import com.webwork.currencyexchangeservice.beans.ServerPortListener;
import com.webwork.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Value("${username.first}")
	private String firstName;

	@Value("${username.last}")
	private String lastName;
	
	@Autowired
	private ServerPortListener portListener;

  @Autowired
   private Environment environment;
  
  @Autowired
  private ExchangeValueRepository exchangeValueRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<ExchangeValue> retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		System.out.println(from);
		System.out.println(to);

		List<ExchangeValue> exchangeValue = exchangeValueRepo.findByFromAndTo(from, to);
		ExchangeValue exchangeValueResponse = exchangeValue.size() == 1 ? exchangeValue.get(0) : null;
		exchangeValueResponse.setPort(portListener.getPort());
			 return new ResponseEntity(exchangeValueResponse, HttpStatus.OK);
	}
	
	@GetMapping("/currency-exchange/all")
	public ResponseEntity<List<ExchangeValue>> retriveAllExchangeValue(){
		 System.out.println("exchange API gets callled..!");
		 List<ExchangeValue> exchangeValue = exchangeValueRepo.findAll();
		 return new ResponseEntity(exchangeValue, HttpStatus.OK);
	}
	
	@GetMapping("/currency-exchange/first/last")
	public ResponseEntity<String> retriveValuesFromPropertiesFile(){
		 
		 return new ResponseEntity(firstName+" "+lastName, HttpStatus.OK);
	}
	
	
}
