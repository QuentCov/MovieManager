package utilities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class is used to define utility functions used in various web security features.
public class SecurityUtilities {
	
	//Filters a string, first by seeing if the string has special characters, 
	//then by replacing those characters with their escaped versions.
	public static String filterString(String stringToFilter) {
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(stringToFilter);
		if(m.matches()) {
			return stringToFilter;
		}
		
		StringBuffer filter = new StringBuffer(stringToFilter.length());
		char c;
		for(int i = 0; i < stringToFilter.length(); i++) {
			c = stringToFilter.charAt(i);
			switch(c) {
				case '<' : filter.append("&lt;"); break;
				case '>' : filter.append("&gt;"); break;
				case '"' : filter.append("&quot;"); break;
				case '&' : filter.append("&amp;"); break;
				default: filter.append(c);
			}
		}
		return filter.toString();
	}
	
	
	//Convenience function that calls filterString on each string in an array.
	public static ArrayList<String> filterStrings(ArrayList<String> stringsToFilter) {
		ArrayList<String> filteredStrings = new ArrayList<String>();
		for(int i = 0; i < stringsToFilter.size(); i++) {
			filteredStrings.add(filterString(stringsToFilter.get(i)));
		}
		return filteredStrings;
	}
}
