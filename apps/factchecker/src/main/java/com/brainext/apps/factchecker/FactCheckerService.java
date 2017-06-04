package com.brainext.apps.factchecker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FactCheckerService {
	RestTemplate restTemplate;
	
	public boolean checkStatements() {
		return false;
	}
}
