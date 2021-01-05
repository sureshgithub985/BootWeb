package com.ey.core.web;

import java.util.List;

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

import com.ey.core.model.GTProfile;
import com.ey.core.service.GTProfileService;
import com.ey.core.util.XMLConvertor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/wave-prov/wave")
@RestController
public class GTProfileController {

	@Autowired
	private GTProfileService gtprofileService;

	@Autowired
	private XMLConvertor xc;

	@PostMapping(value = "/gprofiles", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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

	@GetMapping(value = "/gprofiles", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getAllGTProfiles() {

		StringBuilder sb = new StringBuilder();
		List<GTProfile> gprofileList = gtprofileService.getAllGprofiles();
		sb.append("<collection>");
		for (GTProfile gprofile : gprofileList) {
			sb.append("\n");
			String inXml = xc.toXml(gprofile);
			sb.append(inXml);
		}
		sb.append("\n").append("</collection>");
		Object result = sb.toString();

		log.info("gprofileList value is " + result);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(result, headers, HttpStatus.OK);

	}

	@GetMapping("/gprofiles/{id}")
	public ResponseEntity<GTProfile> getCustomer(@PathVariable("id") int id) {

		GTProfile gprofile = gtprofileService.getGprofileById(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(gprofile, headers, HttpStatus.OK);

	}

	@DeleteMapping("/gprofiles/{id}")
	public ResponseEntity<GTProfile> deleteGroupProfile(@PathVariable("id") int id) {

		log.debug(" Delete GTProfile Controller ");

		gtprofileService.deleteGroupProfileById(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@PutMapping("/gprofiles/{id}")
	public ResponseEntity<Void> updateEnterprise(@RequestBody GTProfile gprofile, @PathVariable("id") int id) {

		log.debug(" Update Enterprise Controller ");

		gtprofileService.updateGprofile(id, gprofile);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

}
