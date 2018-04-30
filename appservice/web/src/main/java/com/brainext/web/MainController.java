package com.brainext.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller for brain extension
 * 
 * @author cpetroaca
 *
 */
@RestController
public class MainController {
	@RequestMapping("/")
	public String index() {
		return "Greetings from Brain Extension!";
	}
}