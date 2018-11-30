package pluralsight.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PluralsightSpringcloudM4SecureserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluralsightSpringcloudM4SecureserviceApplication.class, args);
	}
	
	@RequestMapping("/tolldata")
	public ArrayList<TollUsage> getTolldata() {
		
		TollUsage instance1 = new TollUsage("100","station150","B65GT1W","2016-09-30T06:31:22");
		TollUsage instance2 = new TollUsage("101","station119","AHY673B","2016-09-30T06:32:50");
		TollUsage instance3 = new TollUsage("102","station150","ZN2GP0","2016-09-30T06:37:00");
		
		ArrayList<TollUsage> tolls = new ArrayList<TollUsage>();
		tolls.add(instance1);
		tolls.add(instance2);
		tolls.add(instance3);
		
		return tolls;
	}
	
	public class TollUsage {
		
		
		
		public TollUsage(String id, String stationId, String licenseplate, String timestamp) {
			this.Id = id;
			this.stationId = stationId;
			this.licenseplate = licenseplate;
			this.timestamp = timestamp;
		}
		public String Id;
		public String stationId;
		public String licenseplate;
		public String timestamp;
		
	}
}
