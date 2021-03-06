package com.brainext.apps.factchecker;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for the fact checker app
 * 
 * @author cpetroaca
 *
 */
@RestController
public class AppController {
	@Autowired
	private FactCheckerService factCheckerService;

	@RequestMapping("/apps/factChecker")
	public String index() {
		return "Greetings from the FactChecker app!";
	}

	@RequestMapping("/apps/factChecker/validateStatements")
	public List<CheckedRelation> validateStatements(@RequestParam(value = "text") String text) throws Exception {
		return factCheckerService.validate(text);
	}
}