package org.springframework.boot.autoconfigure.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;
import org.springframework.social.microsoft.api.Microsoft;
import org.springframework.social.microsoft.connect.MicrosoftConnectionFactory;


/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Social
 * connectivity with Microsoft.
 *
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, MicrosoftConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.microsoft", name = "app-id")
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class MicrosoftAutoConfiguration {

    @Configuration
    @EnableSocial
    @EnableConfigurationProperties(MicrosoftProperties.class)
    @ConditionalOnWebApplication
    protected static class MicrosoftConfigurerAdapter extends SocialAutoConfigurerAdapter {

	@Autowired
	private MicrosoftProperties properties;

	@Bean
	@ConditionalOnMissingBean(Microsoft.class)
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Microsoft microsoft(ConnectionRepository repository) {
	    Connection<Microsoft> connection = repository.findPrimaryConnection(Microsoft.class);
	    return connection != null ? connection.getApi() : null;
	}

	@Bean(name = { "connect/microsoftConnect", "connect/microsoftConnected" })
	@ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
	public GenericConnectionStatusView microsoftConnectView() {
	    return new GenericConnectionStatusView("microsoft", "Microsoft");
	}

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
	    return new MicrosoftConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
	}
    }
}
