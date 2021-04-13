package com.bet.me.model;

import java.io.Serializable;
import java.util.List;

public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	public boolean success;
	public List<T> data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
