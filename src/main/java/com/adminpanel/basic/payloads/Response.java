package com.adminpanel.basic.payloads;

import java.util.List;

public class Response 
{
	private String success;
	
	private String message;
	
	private List<Object> data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Response(String success, String message, List<Object> data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", message=" + message + ", data=" + data + "]";
	}

	public Response() {
		super();
	}

	

	
}
