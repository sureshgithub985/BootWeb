package com.ey.core.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/enterprises")
	public ResponseEntity<List<Enterprise>> getAllEnterprises() {

		List<Enterprise> entList = entServie.getAllEnterprises();
		return ResponseEntity.ok(entList);

	}

}
