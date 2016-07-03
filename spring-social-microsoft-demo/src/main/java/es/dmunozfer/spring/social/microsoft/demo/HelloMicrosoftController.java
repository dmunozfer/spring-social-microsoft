package es.dmunozfer.spring.social.microsoft.demo;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.microsoft.api.LiveProfile;
import org.springframework.social.microsoft.api.Microsoft;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloMicrosoftController {
    private final static Log logger = LogFactory.getLog(HelloMicrosoftController.class);

    private Microsoft microsoft;
    private ConnectionRepository connectionRepository;

    @Inject
    public HelloMicrosoftController(Microsoft microsoft, ConnectionRepository connectionRepository) {
	this.microsoft = microsoft;
	this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hello(Model model) {
	return "redirect:/microsoft";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/microsoft")
    public String helloMicrosoft(Model model) {
	if (connectionRepository.findPrimaryConnection(Microsoft.class) == null) {
	    return "redirect:/connect/microsoft";
	}

	LiveProfile profile = microsoft.liveOperations().getUserProfile();
	if (logger.isDebugEnabled()) {
	    logger.debug("profile: " + profile);
	}

	model.addAttribute("profile", profile);
	return "helloMicrosoft";
    }
}