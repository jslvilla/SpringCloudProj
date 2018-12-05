package pluralsight.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class PluralsightSpringcloudM4SecurecliApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PluralsightSpringcloudM4SecurecliApplication.class, args);
	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
	    String idForEncode = "bcrypt";
	    Map<String, PasswordEncoder> encoderMap = new HashMap<>();
	    encoderMap.put(idForEncode, new BCryptPasswordEncoder());
	    return new DelegatingPasswordEncoder(idForEncode, encoderMap);
	}

	@Autowired
	private PasswordEncoder passcoder;*/
	
	
	@Override
	public void run(String... arg0) throws Exception {
		
		System.out.println("cron job started");
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri("http://localhost:9000/services/oauth/token");
		
		//must be a valid scope or get an error; if empty, get all scopes (default); better to ask for one
		resourceDetails.setScope(Arrays.asList("toll_read"));
		
		//must be valid client id or get an error
		resourceDetails.setClientId("pluralsight");
		resourceDetails.setClientSecret("pluralsightsecret");
		
		//diff user results in diff authorities/roles coming out; preauth on roles fails for adam, works for barry
		resourceDetails.setUsername("agoldberg");
		resourceDetails.setPassword("pass1");
		//resourceDetails.setPassword(passcoder.encode("pass1"));
		//resourceDetails.setId("ID");
		
		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails);
		//could also get scopes: template.getAccessToken().getScope()
		//template.getAccessToken().getValue();
		String token =  template.getAccessToken().toString();//.getValue();		 
		
		System.out.println("Token: " + token);
		
		String s = template.getForObject("http://localhost:9001/services/tolldata", String.class);
		
		System.out.println("Result: " + s);
	}
	
	
}
