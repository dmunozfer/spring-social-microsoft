package org.springframework.social.microsoft.config.xml;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.config.xml.AbstractProviderConfigNamespaceHandler;

/**
 * {@link NamespaceHandler} for Spring Social Microsoft
 */
public class MicrosoftNamespaceHandler extends AbstractProviderConfigNamespaceHandler {

	@Override
	protected AbstractProviderConfigBeanDefinitionParser getProviderConfigBeanDefinitionParser() {
		return new MicrosoftConfigBeanDefinitionParser();
	}

}