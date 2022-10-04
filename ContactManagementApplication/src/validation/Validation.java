package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public boolean mobileNumberValidation(String mobileNumber) {
		String mobileNumberValidation = "^[6-9][0-9]{9}$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(mobileNumberValidation);
		matcher = pattern.matcher(mobileNumber);
		return matcher.matches();
	}
	
	public boolean emailIdValidation(String mailId) {
		 //String mailIdValidation = "^(.+)@(.+)$"; 
		 //String mailIdValidation = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		 //String mailIdValidation  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
		 String mailIdValidation = "^[A-Za-z0-9+_.-]+@(.+)$";  
		 Pattern pattern;
		 Matcher matcher;
		 pattern = Pattern.compile(mailIdValidation);
		 matcher = pattern.matcher(mailId);
		 return matcher.matches();
		 
	}
}
