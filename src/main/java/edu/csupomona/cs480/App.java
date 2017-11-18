package edu.csupomona.cs480;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import com.google.api.services.appsactivity.Appsactivity;
import com.google.api.services.appsactivity.model.Activity;
import com.google.api.services.appsactivity.model.Event;
import com.google.api.services.appsactivity.model.ListActivitiesResponse;
import com.google.api.services.appsactivity.model.Target;
import com.google.api.services.appsactivity.model.User;

import edu.csupomona.cs480.DAL.DataAccess;
import edu.csupomona.cs480.data.provider.FSUserManager;
import edu.csupomona.cs480.data.provider.UserManager;

@SpringBootApplication
@EnableOAuth2Sso //annotation needed for Spring OAuth2
public class App {

    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
	
    @Bean
    public UserManager userManager() {
        UserManager userManager = new FSUserManager();
        return userManager;
    }
    
/** <<<<<<< HEAD ======= **/
    @Bean
    public DataAccess dataServices() {
    	DataAccess d = new DataAccess();
    	return d;
    }
/** >>>>>>> UserController **/

    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
        
            }            
}	
    

    
