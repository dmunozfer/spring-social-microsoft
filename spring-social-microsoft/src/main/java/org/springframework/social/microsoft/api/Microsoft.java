package org.springframework.social.microsoft.api;

import org.springframework.social.ApiBinding;

/**
 * Interface specifying a basic set of operations for interacting with Microsoft. Implemented by {@link MicrosoftTemplate}. <br />
 * Many of the methods contained in this interface require OAuth authentication with Microsoft. <br />
 * When a method's description speaks of the "current user", it is referring to the user for whom the access token has been issued.
 */
public interface Microsoft extends ApiBinding {

	/**
	 * API for retrieving and performing operations on live
	 * 
	 * @return a {@link LiveOperations} for working with live
	 */
	LiveOperations liveOperations();
}
