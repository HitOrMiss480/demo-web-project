package edu.csupomona.cs480;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.csupomona.cs480.DAL.DataAccess;
import edu.csupomona.cs480.data.provider.FSUserManager;
import edu.csupomona.cs480.data.provider.GpsProductManager;
import edu.csupomona.cs480.data.provider.UserManager;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@EnableOAuth2Sso
public class App {

    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
    @Bean
    public DataAccess userManager() {
    	DataAccess userManager = new DataAccess();
        return userManager;
    }
    
    @Bean
    public DataAccess eventManager() {
    	DataAccess eventManage = new DataAccess();
        return eventManage;
    }
    
    @Bean
    public DataAccess orgManager() {
    	DataAccess orgManager = new DataAccess();
        return orgManager;
    }
    
    @Bean
    public DataAccess webManager() {
    	DataAccess webManager = new DataAccess();
        return webManager;
    }

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
