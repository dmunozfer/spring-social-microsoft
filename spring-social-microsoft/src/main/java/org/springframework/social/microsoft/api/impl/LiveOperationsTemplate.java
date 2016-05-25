
package org.springframework.social.microsoft.api.impl;

import org.springframework.social.microsoft.api.LiveOperations;
import org.springframework.social.microsoft.api.LiveProfile;
import org.springframework.web.client.RestTemplate;

public class LiveOperationsTemplate extends AbstractMicrosoftOperations implements LiveOperations {
	private final RestTemplate restTemplate;

	public LiveOperationsTemplate(MicrosoftTemplate microsoftTemplate, RestTemplate restTemplate, boolean authorized) {
		super(authorized);
		this.restTemplate = restTemplate;
	}

	@Override
	public LiveProfile getUserProfile() {
		return restTemplate.getForObject(buildUri("me"), LiveProfile.class);
	}
}
