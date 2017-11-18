package edu.csupomona.cs480.helpers;
import edu.csupomona.cs480.constants.*;

public class ExceptionHandler {
	public ErrorPackage Handler(Exception e) {
		String m = e.getMessage();
		int c;
		if(m.contains("sql")) {
			if(m.contains("not found")) {
				c = 404;
				return new ErrorPackage(c,m);
			}
		}
		c = 500;
		return new ErrorPackage(c,m);
	}
}
