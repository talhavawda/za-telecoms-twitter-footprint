package GroupProject.WebInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import GroupProject.WebInterface.model.Database;
import GroupProject.WebInterface.model.QueryNexus;


/*
	The @SpringBootAnnotation is a convenience annotation that adds the following annotations:
		@Configuration - tags the class as a source of bean definitions for the application context
		@EnableAutoConfiguration - tells SpringBoot to add beans based on settings
		@ComponentScan - tells Spring to look for controllers (they have the @Controller annotation)

 */
@SpringBootApplication
public class WebInterfaceApplication {

	public static void main(String[] args) {

		//run
		SpringApplication.run(WebInterfaceApplication.class, args); //launch the server


		//set up singletons
		QueryNexus.initQueryNexus();
		Database db = null;

		try {
			db = Database.instance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
