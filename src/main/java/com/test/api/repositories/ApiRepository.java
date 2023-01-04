package com.test.api.repositories;


import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Repository;


@Repository
public interface ApiRepository{
	

	public String updatePanaTUTable(String firstName, String lastName, String mailId);
	
	//public String checkTuSerialExists(String tuSerial);
	
	public int checkTuSerial(String tuSerial);
	
	public String insertToPanaTUTable(String firstName, String lastName, String mailId);
	
	
}