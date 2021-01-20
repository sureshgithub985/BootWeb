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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.dto.CustomerDTO;
import com.ey.core.entity.Customer;
import com.ey.core.entity.UProfile;
import com.ey.core.service.CustomerService;
import com.ey.core.service.UprofileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@RestController
public class UProfileController {

	@Autowired
	private UprofileService uprofileService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HttpServletRequest request;

	@PostMapping("/uprofiles")
	public ResponseEntity<Void> createUserProfile(@RequestBody @Valid UProfile uprofile,
			UriComponentsBuilder uriBuilder) {

		String contentValue = request.getContentType();

		log.info("Add Customer Controller " + contentValue);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", contentValue);

		uprofileService.addUserProfile(uprofile);

		headers.setLocation(uriBuilder.path("/wave-prov/wave/uprofiles/{id}").buildAndExpand(uprofile.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/uprofiles/{id}")
	public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid UProfile uprofile,
			@PathVariable("id") Integer id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Customer cust = modelMapper.map(custDTO, Customer.class);

		uprofileService.updateUserProfile(uprofile, id);

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@GetMapping("/uprofiles")
	public ResponseEntity<List<UProfile>> getAllUserProfiles() {

		List<UProfile> upofileList = uprofileService.getAllCustomers();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(upofileList, headers, HttpStatus.OK);

	}

	@DeleteMapping("/uprofiles/{id}")
	public ResponseEntity<Void> deleteUserProfileById(@PathVariable("id") Integer id) {

		uprofileService.deleteUserProfileById(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@GetMapping("/uprofiles/{id}")
	public ResponseEntity<UProfile> getUserProfile(@PathVariable("id") Integer id) {

		UProfile uprofile = uprofileService.getUserProfile(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		System.out.println("uprofile value is " + uprofile);

		return new ResponseEntity<>(uprofile, headers, HttpStatus.OK);

	}

}
