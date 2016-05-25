package org.springframework.social.microsoft.api;

import java.io.Serializable;

/**
 * Model class containing emails information.
 */
public class Emails extends MicrosoftObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String preferred;

	private final String account;

	private final String personal;

	private final String business;

	public Emails(String preferred, String account, String personal, String business) {
		super();
		this.preferred = preferred;
		this.account = account;
		this.personal = personal;
		this.business = business;
	}

	public String getPreferred() {
		return preferred;
	}

	public String getAccount() {
		return account;
	}

	public String getPersonal() {
		return personal;
	}

	public String getBusiness() {
		return business;
	}

	@Override
	public String toString() {
		return "Emails [preferred=" + preferred + ", account=" + account + ", personal=" + personal + ", business=" + business + "]";
	}

}
