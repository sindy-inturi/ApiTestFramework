package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class BasicUtils {
	
	public static boolean ValidateEmail(String emailId){
		boolean flag=false;
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		
		Pattern pat = Pattern.compile(emailRegex); 
        
        if( pat.matcher(emailId).matches())
        	{
        	flag=true;
        	}
        
		return flag;
	}
	
	
	public static String GetDateAndTime()
	{
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		return df.format(new Date());
	}

}
