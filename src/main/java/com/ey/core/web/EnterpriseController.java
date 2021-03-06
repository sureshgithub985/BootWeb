package com.ey.core.web;

import java.util.List;

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

import com.ey.core.dto.EnterpriseDTO;
import com.ey.core.entity.Enterprise;
import com.ey.core.service.EnterpriseService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/wave-prov/wave")
@Controller
@Slf4j
@Api(produces = "application/json", value = "Operations pertaining to manager blood donors in the application")
public class EnterpriseController {

	@Autowired
	private EnterpriseService entServie;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/enterprises")
	public ResponseEntity<Void> createEnterprise(@RequestBody EnterpriseDTO entDTO, UriComponentsBuilder uriBuilder) {
		log.debug(" Create Enterprise Controller ");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (entDTO != null) {
			Enterprise ent = modelMapper.map(entDTO, Enterprise.class);
			entServie.addEnterprise(ent);

			headers.setLocation(
					uriBuilder.path("/wave-prov/wave/enterprises/{name}").buildAndExpand(ent.getName()).toUri());

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/enterprises/{name}")
	public ResponseEntity<Void> updateEnterprise(@RequestBody Enterprise ent, @PathVariable("name") String name) {

		log.debug(" Update Enterprise Controller ");

		entServie.updateEnterprise(name, ent);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}


	@GetMapping("/enterprises")
	public ResponseEntity<List<Enterprise>> getAllEnterprises() {

		log.debug(" GETALL Enterprise Controller ");
		List<Enterprise> entList = entServie.getAllEnterprises();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(entList, headers, HttpStatus.OK);

	}

	@GetMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> findByEnterpriseName(@PathVariable("name") String name) {

		log.debug(" GET Enterprise Controller ");
		Enterprise ent = entServie.getEnterpriseByName(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(ent, headers, HttpStatus.OK);

	}

	@DeleteMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> deleteByEnterpriseName(@PathVariable("name") String name) {

		log.debug(" Delete Enterprise Controller ");

		entServie.deleteEnterpriseByName(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

}
