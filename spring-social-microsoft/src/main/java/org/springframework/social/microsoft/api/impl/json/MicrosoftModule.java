package org.springframework.social.microsoft.api.impl.json;

import org.springframework.social.microsoft.api.Emails;
import org.springframework.social.microsoft.api.LiveProfile;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Jackson module for registering mixin annotations against Microsoft model classes.
 */
public class MicrosoftModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public MicrosoftModule() {
		super("MicrosoftModule");
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(LiveProfile.class, LiveProfileMixin.class);
		context.setMixInAnnotations(Emails.class, EmailsMixin.class);
	}

}
