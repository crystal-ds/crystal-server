/*
 * 
 */
package org.mitre.crystal.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tmlewis
 *
 */

@Controller
@RequestMapping (value = "/")
public class RootController {
	final Logger log = LoggerFactory.getLogger(RootController.class);
	
	@RequestMapping(method = RequestMethod.GET)
    public String index() {
        log.info("Request for root");
		return "index";
    }

}
