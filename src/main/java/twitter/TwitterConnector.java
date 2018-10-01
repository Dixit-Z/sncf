package twitter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import configuration.APIConfiguration;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConnector {
	
	@Autowired
	private APIConfiguration oauthConfiguration;
	
	private Twitter twitter;
	
	public static String TWITTER_POST = "[${heure}] Train immobilisé sur la ligne ${line} en gare de ${station} #Balancetontrain #SNCF #RATP";
	
	private Twitter getConnector(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(oauthConfiguration.getKey())
		  .setOAuthConsumerSecret(oauthConfiguration.getSecret())
		  .setOAuthAccessToken(oauthConfiguration.getToken())
		  .setOAuthAccessTokenSecret(oauthConfiguration.getTokensecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
	
	synchronized Twitter getInstance(){
		if(twitter != null){
			return twitter;
		}
		else{
			return getConnector();
		}
	}
	
	public int postUpdate(String station, String line, String heure) {
		Map<String, String> renderingMap = new HashMap<>();
		renderingMap.put("station", station);
		renderingMap.put("line", line);
		renderingMap.put("heure", heure);
		try {
			getInstance().updateStatus(new StrSubstitutor(renderingMap).replace(TWITTER_POST));
		} catch (TwitterException | RuntimeException e) {
			return 1;
		}
		return 0;
	}

	public APIConfiguration getOauthConfiguration() {
		return oauthConfiguration;
	}

	public void setOauthConfiguration(APIConfiguration oauthConfiguration) {
		this.oauthConfiguration = oauthConfiguration;
	}
}
