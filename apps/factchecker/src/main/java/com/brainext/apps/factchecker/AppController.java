package com.brainext.apps.factchecker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class AppController {
    @RequestMapping("/apps/factchecker")
    public String index() {
        return "Greetings from the FactChecker app!";
    }
    
}