package edu.csupomona.cs480.util;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

/**
 * this class will create a User object and place it in the Principal object
 * available in controllers.
 * 
 */
public class GoogleUserInfoPrincipalExtractor implements PrincipalExtractor {

	private static final Logger LOG = LoggerFactory.getLogger(GoogleUserInfoPrincipalExtractor.class);

	@Override
	public Object extractPrincipal(Map<String, Object> map) {

		String name = (String) map.get("name");
		String id = (String) map.get("id");
		String givenName = (String) map.get("given_name");
		String familyName = (String) map.get("family_name");
		String gender = (String) map.get("gender");
		String picture = (String) map.get("picture");
		String link = (String) map.get("link");
		UserInfo u = new UserInfo(id, name, givenName, familyName, gender, picture, link);
		// for (String k: map.keySet())
		// {
		//
		// LOG.info("key "+k+" "+map.get(k));
		//
		// }

		return u;

	}

}
