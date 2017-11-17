package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Splitter;
/**
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;


import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;
/** Unusable temporarily. To fix, import jsoup and math commons library in pom.xml
	@RequestMapping(value = "/cs480/abcdefg", method = RequestMethod.GET)
	String abcdefg() throws IOException {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		Document doc;
		doc = Jsoup.connect("http://google.com").get();
		Elements links = doc.getElementsByTag("a");
		Element a = links.first();
		return links.html();
		
	}
	
	
	@RequestMapping(value = "/cs480/stats", method = RequestMethod.GET)
	String stats() {
		RandomGenerator rg = new JDKRandomGenerator();
		GaussianRandomGenerator rawGenerator=new GaussianRandomGenerator(rg);
		
		UncorrelatedRandomVectorGenerator rand = new UncorrelatedRandomVectorGenerator(5, rawGenerator );
		double []temp = rand.nextVector();
		String out = "";
		for(int i = 0; i < temp.length; i++)
		{
			out = out + " " + temp[i];
		}
		return out;
	}
		
*/
/*	@RequestMapping(value = "/cs480/dcba", method = RequestMethod.GET)
	String dcba() {
		String str = "a,,b,     c,,,d";
 
		Iterable<String> result = Splitter.on(',')
			       .split(str);
 
		for(String s: result){
			return s;
		}
	}	
*/	
	
	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}


	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}


	@RequestMapping(value = "/cs480/smellCheck", method = RequestMethod.GET)
	String smellCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "Needs a Bath";
	}
	//david's implementation
	@RequestMapping(value = "/cs480/error404", method = RequestMethod.GET)
	String error404() {
			return "Error 404: Page not found";
	}

	@RequestMapping(value = "/cs480/hello", method = RequestMethod.GET)
	String hello() {
		return "Hello World";
	}	
	
	@RequestMapping("/")
	public class UserRestController {

	    @RequestMapping(
	            value = "user",
	            method = RequestMethod.GET)
	    public Principal user(Principal user) {
	        return user;
	    }
	}
	
	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	
	
}
