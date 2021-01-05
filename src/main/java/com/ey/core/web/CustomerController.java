package com.ey.core.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.model.Address;
import com.ey.core.model.Customer;
import com.ey.core.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@Controller
public class CustomerController {

	@Autowired
	private CustomerService custService;

	@PostMapping("/customers")
	public ResponseEntity<Void> createCustomer(@RequestBody Customer cust, UriComponentsBuilder uriBuilder) {

		Date date = new Date();
		cust.setUpdatedAt(date);

		log.info("we are in the CreateEnterprise Controller...." + cust);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		custService.addCustomer(cust);

		headers.setLocation(uriBuilder.path("/wave-prov/wave/customers/{name}")
				.buildAndExpand(cust.getFirstName() + cust.getLastName()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> custList = custService.getAllCustomers();

		System.out.println("custList value is " + custList);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(custList, headers, HttpStatus.OK);

	}

	@GetMapping("/customers/{firstName}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("firstName") String firstName) {

		Customer cust = custService.getCustomerByName(firstName);

		System.out.println("custList value is " + cust);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(cust, headers, HttpStatus.OK);

	}

}
