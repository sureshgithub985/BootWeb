package com.ey.core.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.core.entity.EGOwner;

@RestController
@RequestMapping("/wave-prov/wave/")
public class EGOwnerController {

	@PostMapping("/egroups/${name}/egowners")
	public ResponseEntity<Object> createEnterpriseGroupOwners(@RequestBody EGOwner egowner) {

		System.out.println("egowner value is " + egowner);

		return new ResponseEntity<Object>(HttpStatus.OK);

	}
}
