package edu.csupomona.cs480;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;

import edu.csupomona.cs480.util.ClientResources;
import edu.csupomona.cs480.util.GoogleUserInfoPrincipalExtractor;
import edu.csupomona.cs480.rest.utils.AppResponseErrorHandler;
import edu.csupomona.cs480.rest.utils.LoggingRequestInterceptor;
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

	@Autowired
    OAuth2ClientContext oauth2ClientContext;
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
    

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }
    
    @Bean
    //@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES) ????
    // not needed as client context is session scoped
    //see https://jhasaket.blogspot.com/2014/09/securing-spring-mvc-application-using.html
    public OAuth2RestTemplate getRestClient() {

        OAuth2RestTemplate restTemplate = 

        new OAuth2RestTemplate(google().getClient(), oauth2ClientContext);
        ///comment out this section to turn off wire logging////////////////////
       
        BufferingClientHttpRequestFactory requestFactory = 
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        restTemplate.setRequestFactory(requestFactory);
       
        ///////////////////////////////////////////////////////////////////////
        
        
        restTemplate.setErrorHandler(new AppResponseErrorHandler());
        return restTemplate;
    }

    private Filter ssoFilter() {
        /*
        used if there were more than one login provider
         CompositeFilter filter = new CompositeFilter();
         List<Filter> filters = new ArrayList<>();
         filters.add(ssoFilter(google(), "/login/google"));
        
         filter.setFilters(filters);
         return filter;
         */
        return ssoFilter(google(), "/login/google");
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(
                path);
        OAuth2RestTemplate oAuth2RestTemplate = getRestClient();
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
                client.getClient().getClientId());
        //see GoogleUserInfoPrincipalExtractor for an explanation
        tokenServices.setPrincipalExtractor(new GoogleUserInfoPrincipalExtractor());
        
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        return oAuth2ClientAuthenticationFilter;
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
    

    
