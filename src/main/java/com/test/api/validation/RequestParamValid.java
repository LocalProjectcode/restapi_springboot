package com.test.api.validation;

import org.springframework.stereotype.Component;

import com.test.api.bean.RequestDO;

@Component
public class RequestParamValid {
	

    
    public String validateRequestParam(RequestDO requestDO){
    	String tuSerial = requestDO.getTuSerial();
		String firstName = requestDO.getFirstName();
		String lastName = requestDO.getLastName();
		String mailId = requestDO.getMailId();
        String errMsg = "";
        
        if("".equalsIgnoreCase(CommonValidation.trim(tuSerial))) {
            errMsg = "invalid_input";  
        }
        else if("".equalsIgnoreCase(CommonValidation.trim(firstName))){    
            errMsg = "invalid_input";
        }
        else if("".equalsIgnoreCase(CommonValidation.trim(lastName))){
            errMsg = "invalid_input";
        }
        else if("".equalsIgnoreCase(CommonValidation.trim(mailId))) { 
            errMsg = "invalid_input";
        }
        
        
        return errMsg;

    }



}
