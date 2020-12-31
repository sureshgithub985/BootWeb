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

import com.ey.core.model.Subscriber;
import com.ey.core.service.SubscriberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave")
@Controller
public class SubsriberController {

	@Autowired
	private SubscriberService subServie;

	@PostMapping("/subscribers")
	public ResponseEntity<Void> createSubscriber(@RequestBody Subscriber sub, UriComponentsBuilder uriBuilder) {
		log.debug("Create Subscriber Controller ");

		if (sub != null)
			subServie.addSubscriber(sub);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setLocation(uriBuilder.path("/wave-prov/wave/subscribers/{mdn}").buildAndExpand(sub.getMdn()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/subscribers")
	public ResponseEntity<List<Subscriber>> getAllSubscribers() {

		log.debug(" GETALL Subscribers Controller ");

		List<Subscriber> subList = subServie.getAllSubscribers();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(subList, headers, HttpStatus.OK);

	}

	@GetMapping("/subscribers/{mdn}")
	public ResponseEntity<Subscriber> findBySubscriber(@PathVariable("mdn") Long mdn) {

		log.debug(" GET Subscriber Controller ");
		Subscriber sub = subServie.getSubscriber(mdn);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(sub, headers, HttpStatus.OK);

	}

	@DeleteMapping("/subscribers/{mdn}")
	public ResponseEntity<Subscriber> deleteSubscriber(@PathVariable("mdn") Long mdn) {

		log.debug(" Delete Subscriber Controller ");

		subServie.deleteSubscriber(mdn);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@PutMapping("/subscribers/{name}")
	public ResponseEntity<Void> updateEntGroup(@RequestBody Subscriber sub, @PathVariable("mdn") Long mdn) {

		log.debug(" Update Subscriber Controller ");

		subServie.updateSusbciber(mdn, sub);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

}
