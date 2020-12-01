package groupproject.webinterface;

import groupproject.webinterface.model.Database;
import groupproject.webinterface.model.QueryNexus;
import org.springframework.boot.SpringApplication;
import org.springframework.data.neo4j.core.*;
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

		QueryNexus.initQueryNexus();


		Database db = null;

		try {
			db = Database.instance();
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
