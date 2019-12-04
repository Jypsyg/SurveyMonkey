package sm.project.testscripts;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.gargoylesoftware.htmlunit.javascript.host.Set;

import java.lang.*; 
public class practice {
 
	public static void main(String[] args) { 
//	String[] Strarr= { "Sun", 
//            "Apple", 
//            "JBoss",
//            "Whatsup",
//            "Apple", // duplicate
//            "BEA Weblogic",
//            "JBoss"};
//	removeDup(Strarr);
//	String[] inputList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
//			"aug", "Sep", "Oct", "nov", "Dec" };
//	showList(inputList);
//	Arrays.sort(inputList);
//	showList(inputList);
primeNum(29);
}

	private static void palindrome(int inputNum) {
		int sum=0,i;
		int a = inputNum;
		while (a>0) {
		int	lastdigit = (a%10);
				sum=(sum*10)+lastdigit;
				a=a/10;
		
		}
		if (inputNum==sum) {
			System.out.println("palindrome");
		}
			else {
				System.out.println("not");
			}
		}
	
	private static void removeDup(String[] strArray) {
		for(String str :strArray ) {
			System.out.println("not unique "+str);
		}
		
		HashSet<String> set = new HashSet<String>();
			for (int i = 0; i < strArray.length; i++) {
				set.add(strArray[i]);	
				
			}

			strArray= set.toArray(new String[0]);
			for(String uniqet :strArray )
				System.out.println("unique"+uniqet);
			
}
	
	private static void showList(String[] arr) {
		for(String str:arr) {
			System.out.println(str);
		}
	}
		
		private static void primeNum(int num ) {
			
			boolean flag = true;
			for (int i = 2; i < num/2; i++) {
				if (num%i==0) {
					flag=true;
					break;
				}
			if (flag=false) {
				System.out.println("number is prime");
				
			}
			
				
			}
		

}
}



		
