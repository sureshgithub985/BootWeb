package com.ey.core.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ey.core.dto.UProfileDTO;
import com.ey.core.entity.UProfile;
import com.ey.core.service.UprofileService;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;
import com.ey.core.util.XMLConvertor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/wave-prov/wave/uprofiles", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
@RestController
public class UProfileController {

	@Autowired
	private UprofileService uprofileService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private XMLConvertor xc;

	@Autowired
	private Environment env;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> createUserProfile(@RequestBody UProfileDTO uprofileDTO,
			UriComponentsBuilder uriBuilder) {

		log.info(" Add UserProfile Controller ");

		String conentType = addDefaultContentTyoe(request);

		if (conentType != null) {
			HttpHeaders headers = addHeaders(conentType);

			UProfile uprofile = dtoToEntityConversion(uprofileDTO);
			uprofileService.addUserProfile(uprofile);

			headers.setLocation(
					uriBuilder.path("/wave-prov/wave/uprofiles/{id}").buildAndExpand(uprofile.getId()).toUri());

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private String addDefaultContentTyoe(HttpServletRequest request) {
		String contentType = request.getContentType();
		if (contentType == null)
			contentType = env.getProperty("default.content.type");

		return contentType;
	}

	private UProfile dtoToEntityConversion(UProfileDTO uprofileDTO) {

		UProfile uprofile = mapper.map(uprofileDTO, UProfile.class);
		if (uprofile == null)
			throw new ValidationErrorException(MessageUtil.MODEL_MAPPER_CONVERSION_FAILED);

		return uprofile;
	}

	private UProfileDTO entityToDTOConversion(UProfile uprofile) {

		UProfileDTO uprofileDto = mapper.map(uprofile, UProfileDTO.class);
		if (uprofile == null)
			throw new ValidationErrorException(MessageUtil.MODEL_MAPPER_CONVERSION_FAILED);

		return uprofileDto;
	}

	private HttpHeaders addHeaders(String contentValue) {

		HttpHeaders headers = new HttpHeaders();

		if (contentValue.equals(MediaType.APPLICATION_XML_VALUE))
			headers.setContentType(MediaType.APPLICATION_XML);
		else {
			headers.setContentType(MediaType.APPLICATION_JSON);
		}

		return headers;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUserProfile(@RequestBody UProfileDTO uprofileDTO,
			@PathVariable("id") Integer id) {

		String contentType = request.getContentType();
		if (contentType == null)
			contentType = env.getProperty("default.content.type");

		HttpHeaders headers = addHeaders(contentType);

		UProfile uprofile = dtoToEntityConversion(uprofileDTO);

		uprofileService.updateUserProfile(uprofile, id);

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<Object> getAllUserProfiles(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "100") int pageSize) {

		log.debug("PageNum value is " + pageNum + "\n " + "PageSize value is " + pageSize);
		String contentType = request.getContentType();
		if (contentType == null)
			contentType = env.getProperty("default.content.type");

		HttpHeaders headers = addHeaders(contentType);

		Pageable pageable = PageRequest.of(pageNum, pageSize);

		List<UProfile> upofileList = uprofileService.getAllCustomers(pageable);

		headers.add("X-Total-Count", String.valueOf(upofileList.size()));

		if (contentType.equals(MediaType.APPLICATION_XML_VALUE)) {
			StringBuilder sb = new StringBuilder();
			sb.append("<uprofiles>").append("\n").append("<collection>");
			for (UProfile uprofile : upofileList) {
				UProfileDTO uprofiledDTO = entityToDTOConversion(uprofile);
				String outXml = xc.toXml(uprofiledDTO);
				sb.append("\n").append(outXml);
			}
			sb.append("</collection>").append("\n").append("</uprofiles>");

			return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
		} else
			return new ResponseEntity<>(upofileList, headers, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserProfileById(@PathVariable("id") Integer id) {

		uprofileService.deleteUserProfileById(id);

		String contentType = request.getContentType();
		if (contentType == null)
			contentType = env.getProperty("default.content.type");

		HttpHeaders headers = addHeaders(contentType);

		return new ResponseEntity<>(headers, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserProfile(@PathVariable("id") Integer id) {

		log.debug(" GET UserProfile Controller ");

		String contentType = request.getContentType();
		if (contentType == null)
			contentType = env.getProperty("default.content.type");

		UProfile uprofile = uprofileService.getUserProfile(id);

		HttpHeaders headers = addHeaders(contentType);

		if (contentType.equals(MediaType.APPLICATION_XML_VALUE)) {
			UProfileDTO uprofiledDTO = entityToDTOConversion(uprofile);

			String outXml = xc.toXml(uprofiledDTO);
			return new ResponseEntity<>(outXml, headers, HttpStatus.OK);
		} else
			return new ResponseEntity<>(uprofile, headers, HttpStatus.OK);

	}

}
