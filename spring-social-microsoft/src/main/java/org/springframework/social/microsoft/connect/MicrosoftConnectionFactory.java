package org.springframework.social.microsoft.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.microsoft.api.Microsoft;

/**
 * Microsoft ConnectionFactory implementation.
 */
public class MicrosoftConnectionFactory extends OAuth2ConnectionFactory<Microsoft> {

	public MicrosoftConnectionFactory(String clientId, String clientSecret) {
		super("microsoft", new MicrosoftServiceProvider(clientId, clientSecret), new MicrosoftAdapter());
	}
}
