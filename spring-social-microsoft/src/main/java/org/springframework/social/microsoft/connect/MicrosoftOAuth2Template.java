package org.springframework.social.microsoft.connect;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.web.client.RestTemplate;

/**
 * Microsoft OAuth2Template implementation
 */
public class MicrosoftOAuth2Template extends OAuth2Template {

	private static String URL_MICROSOFT_AUTHORIZE = "https://oauth.live.com/authorize";
	private static String URL_MICROSOFT_ACCESS_TOKEN = "https://oauth.live.com/token";

	public MicrosoftOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, URL_MICROSOFT_AUTHORIZE, URL_MICROSOFT_ACCESS_TOKEN);
		setUseParametersForClientAuthentication(true);
	}

	/**
	 * Microsoft return JSON as "text/html" when should be application/json <br />
	 * Adds support to handle text/html to MappingJackson2HttpMessageConverter
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate template = super.createRestTemplate();
		for (HttpMessageConverter<?> converter : template.getMessageConverters()) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				List<MediaType> supportedMediaTypes = new ArrayList<>();
				// Add all default
				supportedMediaTypes.addAll(converter.getSupportedMediaTypes());
				// And also handle text/html json returned on POST to /token
				supportedMediaTypes.add(new MediaType("text", "html", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));
				((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(supportedMediaTypes);
			}
		}
		return template;
	}
}