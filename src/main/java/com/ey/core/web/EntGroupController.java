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

import com.ey.core.entity.EntGroup;
import com.ey.core.service.EntGroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@Controller
public class EntGroupController {

	@Autowired
	private EntGroupService entGroupService;

	@PostMapping("/egroups")
	public ResponseEntity<Void> createEntGroup(@RequestBody EntGroup egroup, UriComponentsBuilder uriBuilder) {
		log.debug(" Create EnterpriseGroup Controller ");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (egroup != null) {
			entGroupService.addEntGroup(egroup);

			headers.setLocation(
					uriBuilder.path("/wave-prov/wave/egroups/{name}").buildAndExpand(egroup.getName()).toUri());

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/egroups")
	public ResponseEntity<List<EntGroup>> getAllEntgroups() {

		log.debug(" GETALL EnterpriseGroup Controller ");

		List<EntGroup> entGroupList = entGroupService.getAllEntGroups();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(entGroupList, headers, HttpStatus.OK);

	}

	@GetMapping("/egroups/{name}")
	public ResponseEntity<EntGroup> findByEntGroup(@PathVariable("name") String name) {

		log.debug(" GET EntGroup Controller ");
		EntGroup entGroup = entGroupService.getEntGroupByName(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(entGroup, headers, HttpStatus.OK);

	}

	@DeleteMapping("/egroups/{name}")
	public ResponseEntity<EntGroup> deleteByEntGroup(@PathVariable("name") String name) {

		log.debug(" Delete EntGroup Controller ");

		entGroupService.deleteEntGroupByName(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@PutMapping("/egroups/{name}")
	public ResponseEntity<Void> updateEntGroup(@RequestBody EntGroup entGroup, @PathVariable("name") String name) {

		log.debug(" Update EntGroup Controller ");

		entGroupService.updateEntGroup(name, entGroup);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

}
