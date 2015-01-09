package com.ruscorporation.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ruscorporation.model.Contact;


public class ContactValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Contact.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "required");
		
		Contact contact = (Contact) target;
		if(contact.getAge() == null || contact.getAge()<18){
			errors.rejectValue("age", "gretter.contact.age");
		}
		
	}

}
