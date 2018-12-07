package pluralsight.demo;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSampler {
	
	@Bean
	public Sampler smartSampler(){
		return new Sampler() {
			
			@Override
			public boolean isSampled(Span span) {
				// TODO Auto-generated method stub
				System.out.println("custom sampler used!");
				return true;
			}
		};
	}
}
