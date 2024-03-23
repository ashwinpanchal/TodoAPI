package org.example.todoapi;

public class ApiResponse<T>{
	private T data;
	private boolean success;
	private String message;

	public ApiResponse(T data, boolean success, String message){
		this.data = data;
		this.success = success;
		this.message = message;
	}

	public void setData(T data){
		this.data = data;
	}

	public T getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
