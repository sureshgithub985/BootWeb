package com.ey.core.web;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.core.dto.FileDetails;
import com.ey.core.entity.Employee;
import com.ey.core.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/wave-prov/wave/fileupload")
@RestController
public class FileControlller {

	@Autowired
	private EmployeeService employeeService;

	@SuppressWarnings("unchecked")
	@PostMapping
	public ResponseEntity<Void> uploadDataUsingExternalFile(@RequestBody FileDetails fileDetails) {

		log.debug("going inside the controller..........");
		System.out.println("file details .... " + fileDetails);

		JSONParser parser = new JSONParser();

		try (FileReader reader = new FileReader(fileDetails.getPath() + "\\" + fileDetails.getFileName())) {
			Object parse = parser.parse(reader);
			JSONArray jsonObj = (JSONArray) parse;

			jsonObj.forEach(jsonObj1 -> {
				final JSONObject employeeObj = (JSONObject) jsonObj1;
				final Employee emp = new Employee();
				emp.setName((String) employeeObj.get("name"));
				emp.setAddress((String) employeeObj.get("address"));
				for (int i = 0; i < 1000; i++) {
					employeeService.createEmployee(emp);
				}

				System.out.println("Employee details are... " + emp);
			});

		} catch (IOException e) {
			log.debug(e.getMessage());
		} catch (ParseException e) {
			log.debug(e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
