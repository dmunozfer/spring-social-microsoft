package org.springframework.social.microsoft.api.impl.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Annotated mixin to add Jackson annotations to Microsoft.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class MicrosoftObjectMixin {

	@JsonAnySetter
	abstract void add(String key, Object value);
}