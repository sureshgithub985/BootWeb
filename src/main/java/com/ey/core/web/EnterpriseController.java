package com.ey.core.web;

import java.util.List;

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

import com.ey.core.model.Enterprise;
import com.ey.core.service.EnterpriseService;

@RequestMapping("/wave-prov/wave")
@Controller
public class EnterpriseController {

	@Autowired
	private EnterpriseService entServie;

	@PostMapping("/enterprises")
	public ResponseEntity<Void> createEnterprise(@RequestBody Enterprise ent, UriComponentsBuilder uriBuilder)
			 {
		System.out.println("we are in the CreateEnterprise Controller...." + ent);
		
		if(ent != null)
			entServie.addEnterprise(ent);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setLocation(uriBuilder.path("/wave-prov/wave/enterprises/{name}").buildAndExpand(ent.getName()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/enterprises/{name}")
	public ResponseEntity<Void> updateEnterprise(@RequestBody Enterprise ent, @PathVariable("name") String name) {

		entServie.updateEnterprise(name, ent);
		return ResponseEntity.ok(null);
	}

	
	@GetMapping("/enterprises")
	public ResponseEntity<List<Enterprise>> getAllEnterprises() {

		List<Enterprise> entList = entServie.getAllEnterprises();
		return ResponseEntity.ok(entList);

	}

	@GetMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> findByEnterpriseName(@PathVariable("name") String name) {

		System.out.println("Name value is " + name);
		Enterprise ent = entServie.getEnterpriseByName(name);
		return ResponseEntity.ok(ent);

	}

	@DeleteMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> deleteByEnterpriseName(@PathVariable("name") String name) {

		System.out.println("Name value is " + name);
		Enterprise ent = entServie.deleteEnterpriseByName(name);
		return ResponseEntity.ok(ent);

	}

}
