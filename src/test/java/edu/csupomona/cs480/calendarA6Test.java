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
	public void testEventDate()
	{
		String result = "";
		calendarA6 cal = new calendarA6();
		
		result = cal.EventDate("Career Fair", "10/27/2017");
		assertEquals("Career Fair is scheduled to be on 10/27/2017", result);
	}
	
	@Test
	public void testEndDate() {
		calendarA6 cal = new calendarA6();
		assertEquals(false, cal.endDate(10232017, 10242017));
	}
	
	@Test
	public void testProduct() {
		calendarA6 cal = new calendarA6();
		int prod = cal.product(3, 5);
		assertEquals(15, prod);
	}
	
	@Test
	public void testNotFound() {
		calendarA6 cal = new calendarA6();
		assertEquals(cal.Empty(),true);		
	}

}


