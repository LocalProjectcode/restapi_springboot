package com.test.api.service;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.api.repositories.ApiRepository;


@Service
public class ApiServiceImpl implements  ApiService{
	
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	ApiRepository apiRepository;

	
	public String updatePanaTUTable(String firstName, String lastName, String mailId){
		
		return  apiRepository.updatePanaTUTable(firstName,lastName,mailId);
	}


	/*
	 * public String checkTuSerialExists(String tuSerial) {
	 * 
	 * return apiRepository.checkTuSerialExists(tuSerial); }
	 */


	public int checkTuSerial(String tuSerial)  {
		
		return apiRepository.checkTuSerial(tuSerial);
	}
	
	public String insertToPanaTUTable(String firstName, String lastName, String mailId) {
		
		return  apiRepository.insertToPanaTUTable(firstName,lastName,mailId);
	}
	
	
	
	
}