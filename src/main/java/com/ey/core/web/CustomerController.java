package com.ey.core.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.dto.CustomerDTO;
import com.ey.core.entity.Customer;
import com.ey.core.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@Controller
public class CustomerController {

	@Autowired
	private CustomerService custService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HttpServletRequest request;

	@PostMapping("/customers")
	public ResponseEntity<Void> createCustomer(@RequestBody @Valid CustomerDTO custDTO,
			UriComponentsBuilder uriBuilder) {

		Date date = new Date();
		custDTO.setUpdatedAt(date);

		String contentValue = request.getContentType();

		log.info("Add Customer Controller " + contentValue);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", contentValue);

		Customer cust = modelMapper.map(custDTO, Customer.class);
		custService.addCustomer(cust);

		headers.setLocation(uriBuilder.path("/wave-prov/wave/customers/{name}")
				.buildAndExpand(cust.getFirstName() + cust.getLastName()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/customers/{email}")
	public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerDTO custDTO,
			@PathVariable("email") String email) {

		Date date = new Date();
		custDTO.setUpdatedAt(date);

		log.info("we are in the CreateEnterprise Controller...." + custDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Customer cust = modelMapper.map(custDTO, Customer.class);

		custService.updateCustomer(cust, email);

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> custList = custService.getAllCustomers();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(custList, headers, HttpStatus.OK);

	}

	@DeleteMapping("/customers/{email}")
	public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable("email") String email) {

		custService.deleteCustomerByEmail(email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("email") String email) {

		Customer cust = custService.getCustomerByEmail(email);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(cust, headers, HttpStatus.OK);

	}

}
