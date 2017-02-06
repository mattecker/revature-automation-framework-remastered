package SDET1611.testingframework;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;


public class TestThread extends Thread {
	private Thread t;
	private String threadName;

	public TestThread(String name){
		threadName = name;
		System.out.println("Creating " +  threadName );
	}

	@Override
	public void run(){
		try {
			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			testng.setTestClasses(new Class[] { HybridTest.class });
			//testng.settes
			testng.addListener(tla);
			testng.run();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Thread " +  threadName + " interrupted.");
		}
	
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start(){
		System.out.println("Starting " +  threadName );
		if(t == null){
			t = new Thread (this, threadName);
			t.start();
		}
	}
}