package com.brainext.apps.factchecker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class AppController {
	@Autowired
	private FactCheckerService factCheckerService;

	@RequestMapping("/apps/factchecker")
	public String index() {
		return "Greetings from the FactChecker app!";
	}

	@RequestMapping("/apps/factchecker/checkStatements")
	public String checkStatements() {
		return Boolean.toString(factCheckerService.checkStatements());
	}
}