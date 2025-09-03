package MBI.DST.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MBI.DST.Listeners.ExtentNode;

public class LogHelper {
	
	private static int stepCounter = 1;
	private static ExtentTest currentStepNode;

    public static void resetCounter() {
        stepCounter = 1;
    }

    public static void step(String message) {
        String stepMessage = "STEP " + stepCounter++ + ": " + message;
        //currentStepNode = ExtentNode.getTest().createNode(stepMessage);
        currentStepNode = ExtentNode.createNode(stepMessage);
        //currentStepNode.log(Status.INFO,stepMessage);
    }
    

    
    public static void detail(String message) {
        if (currentStepNode != null) {
            currentStepNode.log(Status.PASS, message);
        } else {
            ExtentNode.getTest().log(Status.PASS, message);
        }
    }

    public static void pass(String message) {
    	if (currentStepNode != null) {
            currentStepNode.log(Status.PASS, message);
        } else {
            ExtentNode.getTest().log(Status.PASS, message);
        }
    }

    public static void fail(String message) {
    	if (currentStepNode != null) {
            currentStepNode.log(Status.FAIL, message);
        } else {
            ExtentNode.getTest().log(Status.FAIL, message);
        }
    }

    public static void warning(String message) {
        ExtentNode.getNode().info(message);
    }

}
