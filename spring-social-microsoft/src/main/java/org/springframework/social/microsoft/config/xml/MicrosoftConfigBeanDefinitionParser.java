package org.springframework.social.microsoft.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.microsoft.config.support.MicrosoftApiHelper;
import org.springframework.social.microsoft.connect.MicrosoftConnectionFactory;
import org.springframework.social.microsoft.security.MicrosoftAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * Implementation of {@link AbstractConnectionFactoryBeanDefinitionParser} that creates a {@link MicrosoftConnectionFactory}.
 */
public class MicrosoftConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

	public MicrosoftConfigBeanDefinitionParser() {
		super(MicrosoftConnectionFactory.class, MicrosoftApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return MicrosoftAuthenticationService.class;
	}

}
