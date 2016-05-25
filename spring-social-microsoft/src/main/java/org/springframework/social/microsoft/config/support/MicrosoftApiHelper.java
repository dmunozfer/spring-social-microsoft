package org.springframework.social.microsoft.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.microsoft.api.Microsoft;

/**
 * Support class for JavaConfig and XML configuration support. Creates an API binding instance for the current user's connection.
 */
public class MicrosoftApiHelper implements ApiHelper<Microsoft> {

	private final UsersConnectionRepository usersConnectionRepository;

	private final UserIdSource userIdSource;

	private MicrosoftApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.userIdSource = userIdSource;
	}

	public Microsoft getApi() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting API binding instance for Microsoft");
		}

		Connection<Microsoft> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId())
				.findPrimaryConnection(Microsoft.class);
		if (logger.isDebugEnabled() && connection == null) {
			logger.debug("No current connection; Returning default MicrosoftTemplate instance.");
		}
		return connection != null ? connection.getApi() : null;
	}

	private final static Log logger = LogFactory.getLog(MicrosoftApiHelper.class);

}
