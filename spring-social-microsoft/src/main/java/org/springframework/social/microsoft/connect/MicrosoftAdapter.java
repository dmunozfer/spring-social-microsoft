package org.springframework.social.microsoft.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.microsoft.api.LiveProfile;
import org.springframework.social.microsoft.api.Microsoft;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Microsoft ApiAdapter implementation.
 */
public class MicrosoftAdapter implements ApiAdapter<Microsoft> {

	@Override
	public boolean test(Microsoft microsoft) {
		try {
			microsoft.liveOperations().getUserProfile();
			return true;
		} catch (HttpClientErrorException | ApiException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Microsoft microsoft, ConnectionValues values) {
		LiveProfile profile = microsoft.liveOperations().getUserProfile();
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getName());
		values.setImageUrl("https://apis.live.net/v5.0/" + profile.getId() + "/picture");

	}

	@Override
	public UserProfile fetchUserProfile(Microsoft microsoft) {
		LiveProfile profile = microsoft.liveOperations().getUserProfile();
		return new UserProfileBuilder().setName(profile.getName()).setFirstName(profile.getFirstName()).setLastName(profile.getLastName())
				.setUsername(profile.getId()).build();
	}

	@Override
	public void updateStatus(Microsoft microsoft, String message) {
		// None
	}

}
