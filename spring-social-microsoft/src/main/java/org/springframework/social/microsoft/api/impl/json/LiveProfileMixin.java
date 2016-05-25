package org.springframework.social.microsoft.api.impl.json;

import org.springframework.social.microsoft.api.Emails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class LiveProfileMixin extends MicrosoftObjectMixin {

	@JsonCreator
	LiveProfileMixin(
			@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("first_name") String firstName,
			@JsonProperty("last_name") String lastName,
			@JsonProperty("gender") String gender) {}
	
	@JsonProperty("link")
	String link;

	@JsonProperty("locale")
	String locale;

	@JsonProperty("emails")
	Emails emails;
}
