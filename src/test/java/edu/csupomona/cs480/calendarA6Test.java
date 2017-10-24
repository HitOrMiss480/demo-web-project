package edu.csupomona.cs480;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import edu.csupomona.cs480.util.calendarA6;
import junit.framework.Assert;

import org.junit.Test;

public class calendarA6Test {

	@Test
	public void test1()
	{
		String result = "";
		calendarA6 cal = new calendarA6();
		
		result = cal.EventDate("Career Fair", "10/27/2017");
		assertEquals("Career Fair is scheduled to be on 10/27/2017", result);
	}

}


