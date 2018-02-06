/*
 * Copyright (c) 2006-2007 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the i2b2 Software License v1.0 
 * which accompanies this distribution. 
 * 
 * Contributors:
 * 		Lori Phillips
 */
package edu.harvard.i2b2.pm.util;

import java.io.StringWriter;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.Operations;
import org.jboss.dmr.ModelNode;

/**
 * StringUtil class to perform string parsing tasks
 * This is singleton class.
 * @author lcp5
 */
public class StringUtil {

    //to make this class singleton
    private static StringUtil thisInstance;
    
    static {
            thisInstance = new StringUtil();
    }
    
    public static StringUtil getInstance() {
        return thisInstance;
    }
    
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
    
    public static String getTableCd(String fullPath) {
    	int end = fullPath.indexOf("\\", 3);
    	return fullPath.substring(2, end).trim();
    }
    
    public static String getPath(String fullPath) {
    	int end = fullPath.indexOf("\\", 3);
    	return fullPath.substring(end).trim();
    }
    
    // convert //key/index   to index
    public static String getParentIndex(String fullIndex) {
    	int end = fullIndex.indexOf("\\", 3) + 1;
    	return fullIndex.substring(end).trim();
    }
    
    // convert //key/index   to index
    public static String getIndex(String fullIndex) {
    	int end = fullIndex.indexOf("\\", 3) + 1;
    	return fullIndex.substring(end).trim();
    }
    
    public static String replaceEnd(String path, String oldEnding, String newEnding) {
    	if(path == null)
    		return null;
    	else {
    		int end;
			try {
				end = path.length() - oldEnding.length();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				return null;
			}
			String newPath = path.substring(0,end).trim() + newEnding;
    		return newPath.trim();
    	}
    }
    
	public static String generateMessageId() {
		StringWriter strWriter = new StringWriter();
		for(int i=0; i<20; i++) {
			int num = getValidAcsiiValue();
			strWriter.append((char)num);
		}
		return strWriter.toString();
	}

	private static int getValidAcsiiValue() {
		int number = 48;
		while(true) {
			number = 48+(int) Math.round(Math.random() * 74);
			if((number > 47 && number < 58) || (number > 64 && number < 91) 
				|| (number > 96 && number < 123)) {
					break;
				}
		}
		return number;
	}
	

    public static String appServerRunningVersion() throws Exception {  
		StringWriter strWriter = new StringWriter();
        try (ModelControllerClient client = ModelControllerClient.Factory.create("localhost", 9990)) {  
            final ModelNode op = Operations.createReadResourceOperation(new ModelNode().setEmptyList());  
            final ModelNode result = client.execute(op);  
            if (Operations.isSuccessfulOutcome(result)) {  
                final ModelNode model = Operations.readResult(result);  
                final String productName;  
                if (model.hasDefined("product-name")) {  
                    productName = model.get("product-name").asString();  
                } else {  
                    productName = "WildFly";  
                }  
      
      
                String productVersion = null;  
                if (model.hasDefined("product-version")) {  
                    productVersion = model.get("product-version").asString();  
                }  
      
      
                String releaseCodename = null;  
                if (model.hasDefined("release-codename")) {  
                    releaseCodename = model.get("release-codename").asString();  
                }  
      
      
                String releaseVersion = null;  
                if (model.hasDefined("release-version")) {  
                    releaseVersion = model.get("release-version").asString();  
                }  
      
      
                strWriter.append((productName != null ? productName : "WildFly"));  
                strWriter.append(" " + productVersion);  
                //strWriter.append("\nCodename: " + releaseCodename);  
                //strWriter.append("\nRelease Version: " +  releaseVersion);  
            } else {  
            	strWriter.append(result.toString());  
            }  
        }  
        return strWriter.toString();
    }  
}