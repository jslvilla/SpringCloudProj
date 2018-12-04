package pluralsight.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter{
		
		@Bean
		public PasswordEncoder passwordEncoder() {
		    String idForEncode = "bcrypt";
		    Map<String, PasswordEncoder> encoderMap = new HashMap<>();
		    encoderMap.put(idForEncode, new BCryptPasswordEncoder());
		    return new DelegatingPasswordEncoder(idForEncode, encoderMap);
		}
	
		@Autowired
		private PasswordEncoder passcoder;
	
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			//passcoder.encode("pluralsight");
			
			auth.inMemoryAuthentication()
			.withUser("agoldberg").password(passcoder.encode("pass1")).roles("USER").and()
			.withUser("bgoldberg").password(passcoder.encode("pass2")).roles("USER","OPERATOR");
		}
}


