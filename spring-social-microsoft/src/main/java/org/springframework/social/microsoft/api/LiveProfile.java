package org.springframework.social.microsoft.api;

import java.io.Serializable;

/**
 * Model class containing a user's Live profile information.
 */
public class LiveProfile extends MicrosoftObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;

	private final String name;

	private final String firstName;

	private final String lastName;

	private final String gender;

	private String link;

	private String locale;

	private Emails emails;

	public LiveProfile(String id, String name, String firstName, String lastName, String gender) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Emails getEmail() {
		return emails;
	}

	public void setEmail(Emails emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "LiveProfile [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", link="
				+ link + ", locale=" + locale + ", emails=" + emails + ", extraData=" + getExtraData() + "]";
	}

}
