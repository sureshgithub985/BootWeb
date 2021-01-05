package com.ey.core.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.ey.core.model.GTProfile;


@Component
public class XMLConvertor {

	JAXBContext context = null;

	Class<?>[] META_CLASSES = { GTProfile.class };

	public XMLConvertor() {
		try {
			context = JAXBContext.newInstance(META_CLASSES);
		} catch (JAXBException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}

		return outXml;

	}

	public Object fromXml(String inXml) {
		Object obj = null;

		try {
			Unmarshaller um = context.createUnmarshaller();
			obj = um.unmarshal(new StringReader(inXml));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
