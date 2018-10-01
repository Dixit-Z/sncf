package configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class APIConfiguration {
	
	private String token;
	
	private String tokensecret;
	
	private String secret;
	
	private String key;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokensecret() {
		return tokensecret;
	}

	public void setTokensecret(String tokensecret) {
		this.tokensecret = tokensecret;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
