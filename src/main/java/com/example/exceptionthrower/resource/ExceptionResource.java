package com.example.exceptionthrower.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exceptions")
public class ExceptionResource {

	@GetMapping("null")
	public String throwNullPointer() {
		return getNullObj().toString();
	}

	@GetMapping("illegal")
	public String throwIllegalArgument() {
		if (getNullObj() == null) {
			throw new IllegalArgumentException("Null value");
		}
		return "";
	}
	
	@GetMapping("chained")
	public String throwChainedException() {
		try {
			return tryGettingValue();
		} catch (Exception e) {
			throw new RuntimeException("It was possible to retrieve the requested value", e);
		}
	}

	private String tryGettingValue() {
		try {
			return throwNullPointer();
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid value", e);
		}
	}
	
	
	private Object getNullObj() {
		return null;
	}
	
}
