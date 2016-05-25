package org.springframework.social.microsoft.api.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Microsoft request error handler
 */
public class MicrosoftErrorHandler extends DefaultResponseErrorHandler {

	private final static Log logger = LogFactory.getLog(MicrosoftErrorHandler.class);

	private static final String MICROSOFT_PROVIDER_ID = "microsoft";

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		Map<String, Object> errorDetails = extractErrorDetailsFromResponse(response);

		if (logger.isDebugEnabled()) {
			logger.debug("Error from Microsoft: " + errorDetails);
		}

		String message = extractMessageFromErrorDetails(errorDetails);
		HttpStatus statusCode = response.getStatusCode();
		if (statusCode.equals(HttpStatus.UNAUTHORIZED)) {
			throw new NotAuthorizedException(MICROSOFT_PROVIDER_ID, message);
		} else if (statusCode.equals(HttpStatus.FORBIDDEN)) {
			throw new InsufficientPermissionException(MICROSOFT_PROVIDER_ID);
		} else if (statusCode.equals(HttpStatus.NOT_FOUND)) {
			throw new ResourceNotFoundException(MICROSOFT_PROVIDER_ID, message);
		}

		handleUncategorizedError(response);
	}

	private void handleUncategorizedError(ClientHttpResponse response) {
		try {
			super.handleError(response);
		} catch (Exception e) {
			throw new UncategorizedApiException(MICROSOFT_PROVIDER_ID, "", e);
		}
	}

	private Map<String, Object> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		try {
			return mapper.<Map<String, Object>> readValue(response.getBody(), new TypeReference<Map<String, Object>>() {
			});
		} catch (JsonParseException e) {
			return Collections.emptyMap();
		}
	}

	private String extractMessageFromErrorDetails(Map<String, Object> errorDetails) {
		@SuppressWarnings("unchecked")
		Map<String, Object> error = (Map<String, Object>) errorDetails.get("error");
		String message = (String) error.get("message");
		return (message == null) ? "" : message;
	}
}
