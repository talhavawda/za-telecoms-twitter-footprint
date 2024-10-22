package GroupProject.WebInterface;

import GroupProject.WebInterface.model.Database;
import GroupProject.WebInterface.model.QueryNexus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
		QueryNexus queryNexus = null;
		Database db = null;

		//testing the singletons
		try {
			db = Database.instance();
			queryNexus = QueryNexus.instance();

		} catch (Exception e) {
			e.printStackTrace();
		}


/*      //Dont do this as it results in the db closing before queries can be made

		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

 */

	}

}
