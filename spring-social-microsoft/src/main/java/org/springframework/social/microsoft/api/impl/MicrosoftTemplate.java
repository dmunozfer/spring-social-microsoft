package org.springframework.social.microsoft.api.impl;

import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.microsoft.api.LiveOperations;
import org.springframework.social.microsoft.api.Microsoft;
import org.springframework.social.microsoft.api.impl.json.MicrosoftModule;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This is the central class for interacting with Microsoft.
 */
public class MicrosoftTemplate extends AbstractOAuth2ApiBinding implements Microsoft {

	private ObjectMapper objectMapper;

	private LiveOperations liveOperations;

	public MicrosoftTemplate(String accessToken) {
		super(accessToken);
		initialize();
	}

	@Override
	public LiveOperations liveOperations() {
		return liveOperations;
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new MicrosoftErrorHandler());
	}

	private void initialize() {
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
		registerMicrosoftJsonModule();
		initSubApis();
	}

	private void initSubApis() {
		liveOperations = new LiveOperationsTemplate(this, getRestTemplate(), isAuthorized());
	}

	private void registerMicrosoftJsonModule() {
		List<HttpMessageConverter<?>> converters = getRestTemplate().getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				objectMapper = new ObjectMapper();
				objectMapper.registerModule(new MicrosoftModule());
				objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				jsonConverter.setObjectMapper(objectMapper);
			}
		}
	}
}
