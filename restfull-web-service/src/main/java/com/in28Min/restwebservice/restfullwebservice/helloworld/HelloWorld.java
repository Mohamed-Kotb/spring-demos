package com.in28Min.restwebservice.restfullwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "hello-world")
	public String helloWorld() {

		return "Hello World";
	}
	
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "hello-world-path/{name}")
	public HelloWorldBean helloWorldPath(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World %s", name) );
	}

	@GetMapping(path = "hello-world-inter")
	public String helloWorldInter() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "default message", locale);
	}
}
