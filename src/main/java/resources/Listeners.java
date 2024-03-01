package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage;
import base.ExtendManager;

public class Listeners extends BasePage implements ITestListener {

	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public synchronized void onStart(ITestContext context) {
		ExtendManager.getReport();
		ExtendManager.createTest(context.getName(), context.getName());
	}
	
	
	 public synchronized void onTestFailure(ITestResult result) {
		 ExtendManager.getTest().fail(result.getThrowable());
		 
		 try {
			  System.out.println("Test failed: " + result.getName());
		    	takeSnapShot(result.getMethod().getMethodName());
		    	ExtendManager.attachImage();
		    }
		    catch (Exception e) {
				e.printStackTrace();
			}
		  }
 
	 public synchronized void onFinish(ITestContext context) {
		 ExtendManager.flushReport();
		 
	 }
}
