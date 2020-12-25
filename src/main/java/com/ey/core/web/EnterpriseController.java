package com.ey.core.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ey.core.model.Enterprise;
import com.ey.core.service.EnterpriseService;

@RequestMapping("/wave-prov/wave")
@Controller
public class EnterpriseController {

	@Autowired
	private EnterpriseService entServie;

	@PostMapping("/enterprises")
	public ResponseEntity createEnterprise(@RequestBody Enterprise ent) throws URISyntaxException {
		System.out.println("we are in the CreateEnterprise Controller...." + ent);
		entServie.addEnterprise(ent);
		URI uri = new URI("/enterprises");
		return ResponseEntity.created(uri).build();

	}
	
	@PutMapping("/enterprises/{name}")
	public ResponseEntity updateEnterprise(@RequestBody Enterprise ent, @PathVariable("name") String name) {
		
		entServie.updateEnterprise(name,ent);
		
		return ResponseEntity.ok(null);

	}


	@GetMapping("/enterprises")
	public ResponseEntity<List<Enterprise>> getAllEnterprises() {

		List<Enterprise> entList = entServie.getAllEnterprises();
		return ResponseEntity.ok(entList);

	}
	
	
	@GetMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> findByEnterpriseName(@PathVariable("name") String name) {

		System.out.println("Name value is "+name);
		Enterprise ent = entServie.getEnterpriseByName(name);
		return ResponseEntity.ok(ent);

	}
	
	@DeleteMapping("/enterprises/{name}")
	public ResponseEntity<Enterprise> deleteByEnterpriseName(@PathVariable("name") String name) {

		System.out.println("Name value is "+name);
		Enterprise ent = entServie.deleteEnterpriseByName(name);
		return ResponseEntity.ok(ent);

	}
	
	
	
	

}
