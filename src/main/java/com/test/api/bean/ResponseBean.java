package com.test.api.bean;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 private String status;
	 
	    public ResponseBean(String status) {
	        this.status = status;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "TuSwapResponseBean [status=" + status + "]";
	    }

	

}
