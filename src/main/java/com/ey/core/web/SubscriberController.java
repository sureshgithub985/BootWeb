package com.ey.core.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.dto.SubscriberDTO;
import com.ey.core.entity.Subscriber;
import com.ey.core.service.SubscriberService;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;
import com.ey.core.util.XMLConvertor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave/subscribers")
@Controller
public class SubscriberController {

	@Autowired
	private SubscriberService subServie;

	@Autowired
	private XMLConvertor xc;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Void> createSubscriber(@RequestBody Subscriber sub, UriComponentsBuilder uriBuilder) {
		log.debug("Create Subscriber Controller ");

		System.out.println("Subscriber value is " + sub);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (sub != null) {

			// if (sub.getSuids().size() > 5)
			// throw new ValidationErrorException(MessageUtil.LIMIT_EXCEDED);

			subServie.addSubscriber(sub);

			headers.setLocation(
					uriBuilder.path("/wave-prov/wave/subscribers/{mdn}").buildAndExpand(sub.getMdn()).toUri());

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Subscriber>> getAllSubscribers(
			@RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "100", required = false) int pageSize,
			@RequestParam(value = "enterprise_eq", defaultValue = "", required = false) String enterprise) {

		log.debug(" GETALL Subscribers Controller ");

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		List<Subscriber> subList = subServie.getAllSubscribers(pageable, enterprise);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-Total-Count", String.valueOf(subList.size()));

		return new ResponseEntity<>(subList, headers, HttpStatus.OK);

	}

	@GetMapping("/{mdn}")
	public ResponseEntity<Object> findBySubscriber(@PathVariable("mdn") Long mdn) {

		log.debug(" GET Subscriber Controller ");
		Subscriber sub = subServie.getSubscriber(mdn);


		SubscriberDTO sub1 = modelMapper.map(sub, SubscriberDTO.class);

		System.out.println("sub1 value is " + sub1);
		
		String inXml = xc.toXml(sub1);

		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		return new ResponseEntity<>(inXml, headers, HttpStatus.OK);

	}

	@DeleteMapping("/{mdn}")
	public ResponseEntity<Subscriber> deleteSubscriber(@PathVariable("mdn") Long mdn) {

		log.debug(" Delete Subscriber Controller ");

		subServie.deleteSubscriber(mdn);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@PutMapping("/{mdn}")
	public ResponseEntity<Void> updateSubscriber(@RequestBody Subscriber sub, @PathVariable("mdn") Long mdn) {

		log.debug(" Update Subscriber Controller ");

		subServie.updateSusbciber(mdn, sub);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

}
