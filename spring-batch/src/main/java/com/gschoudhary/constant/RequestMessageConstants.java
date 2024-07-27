package com.gschoudhary.constant;

public enum RequestMessageConstants {
	
	UNAUTHORIZED("Unauthorized user, please login"),
	UPDATED("Successfully updated"),
	REMOVED("Successfully removed"),
	UPLOADED("Successfully uploaded"),
	SENT("Sent successfully"),
	VALID_TOKEN("Token is valid"),
	INVALID_TOKEN("Token is not valid."),
	ADDED("Successfully added"),
	DETAIL("Latest details"),
	NOT_FOUND("Details not found"),
	HEALTH_CHECK_MESSAGE("OK");

	private String message;
	
	public String getMessage(){
		return this.message;
	}
	
	RequestMessageConstants(String message){
		this.message = message;
	}
}
