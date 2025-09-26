package com.in28Min.restwebservice.restfullwebservice.versioning;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningRestController {

	// twitter style
	@GetMapping("/v1/person")
	public Person getFirstVersionPersone() {
		return new Person("Mohamed Korb");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPersone() {
		return new PersonV2("Mohamed", "Korb");
	}

	// amazon style
	@GetMapping(path = "/person")
	public Person getFirstVersionPersoneParam() {
		return new Person("Mohamed Korb amazon 1");
	}

	@GetMapping(path = "/person", params = "vesion=2")
	public PersonV2 getSecondVersionPersoneParam() {
		return new PersonV2("Mohamed", "Korb amazon 2");
	}

	// microsoft style
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public Person getFirstVersionPersoneHeader() {
		return new Person("Mohamed Korb microsoft 1");
	}

	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionPersoneHeader() {
		return new PersonV2("Mohamed", "Korb microsoft2");
	}
	
	// GitHup style
	@GetMapping(path = "/person", produces = "application/vnd.kotb.app-v1+json")
	public Person getFirstVersionPersoneHeaderAccept() {
		return new Person("Mohamed Korb accpt header 1");
	}
	
	//HATEOAS
	@GetMapping(path = "/person",  produces = "application/vnd.kotb.app-v2+json")
	public EntityModel<PersonV2> getSecondVersionPersoneHeaderAccept() {
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getSecondVersionPersoneHeaderAccept());
		return EntityModel.of(new PersonV2("Mohamed", "Korb accpt header 2 "));
	}
}
