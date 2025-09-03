package MBI.DST.Listeners;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;



public class ExtentNode {
	

	private static Map<Long, ExtentTest> parentTestMap = new HashMap<>();
    private static Map<Long, ExtentTest> nodeTestMap = new HashMap<>();

    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest test = ExtentReportsManager.getExtentReports().createTest(testName);
        parentTestMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return parentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized ExtentTest createNode(String stepName) {
        ExtentTest node = getTest().createNode(stepName);
        nodeTestMap.put(Thread.currentThread().getId(), node);
        return node;
    }

    public static synchronized ExtentTest getNode() {
    	 ExtentTest node = nodeTestMap.get(Thread.currentThread().getId());
    	 if (node == null) {
    	        ExtentTest parent = getTest();
    	        if (parent == null) {
    	            // create parent test otomatis jika hilang
    	            parent = ExtentReportsManager.getExtentReports().createTest("Unnamed Test");
    	            parentTestMap.put(Thread.currentThread().getId(), parent);
    	        }
    	        return parent;
    	    }
    	    return node;
    }
    
    public static synchronized void addInfo(String message) {
        getNode().info(message);
    }

    public static synchronized void addScreenshot(String path) {
        try {
            getNode().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            getNode().warning("Failed to attach screenshot: " + e.getMessage());
        }
    }

}
