package ApiAutomation.testRun;

import org.testng.annotations.Test;
import Automation.Functions.myPediaPageClass;
import Automation.TestBase.TestBase;

public class myPedia extends TestBase {
	
	myPediaPageClass myPPC = new myPediaPageClass();
	
/*	Confirm that language dropdown has at least 3 languages 
*	Auto-Select different languages and validate that the label of the [CONTINUE] button changes to selected language.
**/
	
	@Test(priority = 0)
	public void dropDownCheck() throws InterruptedException{
		myPPC.verifyDD();
		myPPC.langChnage();
		
	}
	
	/*Click on "setup parent support" -> Create a new account. Fill all the details to create an account. Make this data driven so it could be executed multiple times. 
	*Assert that "create account" button is disabled till all fields are filled.
	**/
	@Test(priority = 1)
	public void createAccount() throws Exception{
		myPPC.setupParent();
		myPPC.createAccount();
		myPPC.fillRegistration();
	}
}
