package com.ey.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.model.GTProfile;
import com.ey.core.service.GTProfileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@RestController
public class GTProfileController {

	@Autowired
	private GTProfileService gtprofileService;

	@PostMapping("/gprofiles")
	public ResponseEntity<Void> createGroupProfile(@RequestBody GTProfile gprofile, UriComponentsBuilder uriBuilder) {
		log.debug(" Create GroupProfile Controller ");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (gprofile != null) {
			gtprofileService.addGprofile(gprofile);

			headers.setLocation(
					uriBuilder.path("/wave-prov/wave/gprofiles/{id}").buildAndExpand(gprofile.getId()).toUri());

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
