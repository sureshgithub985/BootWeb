package com.ey.core.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.ey.core.entity.UProfile;
import com.ey.core.service.UprofileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave/uprofiles")
@RestController
public class UProfileController {

	@Autowired
	private UprofileService uprofileService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping
	public ResponseEntity<Void> createUserProfile(@RequestBody @Valid UProfile uprofile,
			UriComponentsBuilder uriBuilder) {

		log.info(" Add UserProfile Controller ");

		HttpHeaders headers = addHeaders(request);

		uprofileService.addUserProfile(uprofile);

		headers.setLocation(uriBuilder.path("/wave-prov/wave/uprofiles/{id}").buildAndExpand(uprofile.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	private HttpHeaders addHeaders(HttpServletRequest request) {

		String contentValue = request.getContentType();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", contentValue);
		if (contentValue.equals("application/xml"))
			headers.setContentType(MediaType.APPLICATION_XML);
		else
			headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid UProfile uprofile,
			@PathVariable("id") Integer id) {

		HttpHeaders headers = addHeaders(request);

		uprofileService.updateUserProfile(uprofile, id);

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<UProfile>> getAllUserProfiles() {

		HttpHeaders headers = addHeaders(request);

		List<UProfile> upofileList = uprofileService.getAllCustomers();

		return new ResponseEntity<>(upofileList, headers, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserProfileById(@PathVariable("id") Integer id) {

		uprofileService.deleteUserProfileById(id);

		HttpHeaders headers = addHeaders(request);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UProfile> getUserProfile(@PathVariable("id") Integer id) {

		log.debug(" GET UserProfile Controller ");

		UProfile uprofile = uprofileService.getUserProfile(id);

		HttpHeaders headers = addHeaders(request);

		return new ResponseEntity<>(uprofile, headers, HttpStatus.OK);

	}

}
