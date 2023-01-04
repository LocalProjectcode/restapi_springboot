package com.test.api.service;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;


import com.test.api.bean.RequestDO;

@Service
public interface ApiService{
	
	
	public String updatePanaTUTable(String firstName, String lastName, String mailId);
	
	//public String checkTuSerialExists(String tuSerial);
	
	public int checkTuSerial(String tuSerial);
	
	public String insertToPanaTUTable(String firstName, String lastName, String mailId);
}

