package com.ey.core.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.ey.core.dto.EnterpriseDTO;
import com.ey.core.dto.GTProfileDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class XMLConvertor {

	JAXBContext context = null;

	Class<?>[] metaCLASSES = { GTProfileDTO.class, EnterpriseDTO.class };

	public XMLConvertor() {
		try {
			context = JAXBContext.newInstance(metaCLASSES);
		} catch (JAXBException e) {
			log.debug(e.getMessage());
		}
	}

	public String toXml(Object obj) {
		String outXml = null;
		StringWriter sw = new StringWriter();

		try {
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			m.marshal(obj, sw);
			outXml = sw.toString();
		} catch (JAXBException e) {
			log.debug(e.getMessage());
		}

		return outXml;

	}

	public Object fromXml(String inXml) {
		Object obj = null;

		try {
			Unmarshaller um = context.createUnmarshaller();
			obj = um.unmarshal(new StringReader(inXml));
		} catch (JAXBException e) {
			log.debug(e.getMessage());
		}
		return obj;
	}
}
