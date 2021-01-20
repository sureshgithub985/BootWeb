package com.ey.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.core.dao.VerssionDAO;
import com.ey.core.entity.Version;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@RestController
public class VersionController {

	@Autowired
	private VerssionDAO versionDao;

	@GetMapping("/version")
	public ResponseEntity<Object> getVersion() {
		log.debug(" GET Verison Controller ");
		Version version = versionDao.getversion();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(version, headers, HttpStatus.OK);
	}
}
