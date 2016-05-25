package org.springframework.social.microsoft.connect;

import org.springframework.social.microsoft.api.Microsoft;
import org.springframework.social.microsoft.api.impl.MicrosoftTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Microsoft ServiceProvider implementation.
 */
public class MicrosoftServiceProvider extends AbstractOAuth2ServiceProvider<Microsoft> {

	public MicrosoftServiceProvider(String clientId, String clientSecret) {
		super(new MicrosoftOAuth2Template(clientId, clientSecret));
	}

	public Microsoft getApi(String accessToken) {
		return new MicrosoftTemplate(accessToken);
	}

}
