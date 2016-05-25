package org.springframework.social.microsoft.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class EmailsMixin extends MicrosoftObjectMixin {

	@JsonCreator
	EmailsMixin(
			@JsonProperty("preferred") String idpreferred,
			@JsonProperty("account") String account,
			@JsonProperty("personal") String personal,
			@JsonProperty("business") String business) {}
}
