package com.test.api.controller;


import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import com.test.api.bean.RequestDO;
import com.test.api.bean.ResponseBean;
import com.test.api.constants.FileConstants;
import com.test.api.repositories.ApiRepository;
import com.test.api.service.ApiService;
import com.test.api.validation.CommonValidation;
import com.test.api.validation.RequestParamValid;




@RestController
public class apiController  {
	
	Logger logger = Logger.getLogger(getClass()); 
	
	@Autowired
	RequestParamValid requestParamValid;
	
	/*
	 * @Autowired private ApplicationConstants applicationConstants;
	 */
	@Autowired
	public ApiService apiService;

	@Autowired
	ApiRepository apiRepository;
	
	
	
	@RequestMapping(value = FileConstants.COMMON_URL_STR, method=RequestMethod.POST)
	
	public ResponseEntity<Object> tuSwapOccurs(HttpServletRequest request, @RequestBody RequestDO requestDO) throws ParseException, ApplicationException  {
		
		
		String firstName, lastName, mailId, username, password;
		logger.debug("In APIController");
		
		String methodName = "feedTU";
		logger.info(" ENTRY " + methodName);

		ResponseBean responseBean = null;
		
		firstName = requestDO.getFirstName();
		requestDO.setFirstName(firstName);
		
		lastName = requestDO.getLastName();
		requestDO.setLastName(lastName);
		
		mailId = requestDO.getMailId();
		requestDO.setMailId(mailId);
		
		
		
		logger.info("firstName :"+firstName+", lastName :"+lastName+", mailId :"+mailId);
		
		String err=requestParamValid.validateRequestParam(requestDO);
		
		String msg = null;
		
		try {

		if (CommonValidation.trim(err).equals("")) {
			
			if (!firstName.isEmpty() && !lastName.isEmpty() && !mailId.isEmpty()) {
				String tuSerial = requestDO.getTuSerial();
				requestDO.setTuSerial(tuSerial);	

			int tuSerialNumber = apiService.checkTuSerial(tuSerial);// need to pass userName/Id here instead firstname.

			logger.info(" exits :" + tuSerialNumber);

			if (tuSerialNumber == 1) {

						/*
						 * msg = apiService.updatePanaTUTable(tuserial, iccid, imei, msisdn, imsi,
						 * firmware, tuType);
						 * 
						 * logger.info(" exits :" + msg);
						 */

			} else if (tuSerialNumber == 0) {

						/*
						 * msg = tuSwapService.insertToPanaTUTable(tuserial, iccid, imei, msisdn, imsi,
						 * firmware, tuType);
						 * 
						 * logger.info(" exits :" + msg);
						 */
			}

			if (msg.equalsIgnoreCase("Success")) {

				responseBean = new ResponseBean(msg);
				return new ResponseEntity<Object>(responseBean, HttpStatus.OK);

			} else {
				responseBean = new ResponseBean(msg);
				return new ResponseEntity<Object>(responseBean, HttpStatus.BAD_REQUEST);
			}

		} else {
			
			msg = "Bad Request (InvalidInputCriteria)";
			responseBean = new ResponseBean(err);
			return new ResponseEntity<Object>(responseBean, HttpStatus.BAD_REQUEST);

		} 
		
	} else {
			responseBean = new ResponseBean(err);
			return new ResponseEntity<Object>(responseBean, HttpStatus.BAD_REQUEST);

		}
		
	} catch(Exception e){
		
		 String techMsg = null;
         try{
             logger.error("Exception while processing the authorize request. "+e);
             techMsg = "Internal Server Error. Please try again after some time. "+e;
         }catch(Exception e1) {
             logger.error("Exception connecting to DDI Database. "+e1);
             techMsg = "Internal Server Error. Please try again after some time. "+e1;
         }
         String[] statusmsg= {techMsg};
         String error="TECHNICAL_ERROR";
         responseBean = new ResponseBean(techMsg);
         logger.info("The response for the request is "+responseBean.toString());
         return new ResponseEntity<>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	}
}
	
