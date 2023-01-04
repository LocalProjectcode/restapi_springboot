package com.test.api.repositories;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.test.api.config.ConfigService;
import com.test.api.config.Configuration;





@Repository 
public class ApiRepositoryImpl implements ApiRepository{
	
	Logger logger = Logger.getLogger(getClass());
	
	Configuration config = ConfigService.getConfiguration("SqlQueries");
	
	/*
	 * @Autowired ApplicationConstants applicationConstants;
	 */
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	NamedParameterJdbcTemplate parameterJdbcTemplate;
	
	@Value("${queryToCheckTUSerial}")
	private String queryToCheckTUSerial;
	
	@Value("${queryToUpdateRadioDetail}")
	private String queryToUpdateRadioDetail;
	
	@Value("${queryToInsertRadioDetail}")
	private String queryToInsertRadioDetail;
	
	public static final String TuSerial = "tuSerial";

	
	@Override
	public int checkTuSerial(String tuSerial) {
		logger.debug("TuSerial:" + tuSerial);

		logger.debug("Checking User Exits in DB");
		int count = 0;
		String checkUserExits = (String) config.getProperty("queryToCheckTUSerial");
		try (Connection con = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement statement = con.prepareStatement(checkUserExits)) {
			statement.setString(1, tuSerial);
			try (ResultSet results = statement.executeQuery()) {
				if (results.next()) {
					count = results.getInt(1);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured while checking for user Exits in DB. " + e);
		}
		logger.debug("User Exits count:" + count);
		return count;

	}
	 
	 @Override
	    public String updatePanaTUTable(String firstName, String lastName, String mailId) {
	        
	        String updateRadioDetails = (String)config.getProperty("queryToUpdateRadioDetail");
	        logger.debug("Inside updatePanaTUTable method");
	        String msg;
	        try {
	            jdbcTemplate.execute(updateRadioDetails,new PreparedStatementCallback<Boolean>(){  
	                public Boolean doInPreparedStatement(PreparedStatement ps)  
	                        throws SQLException, DataAccessException { 
	                	ps.setString(1,firstName); 
	                    ps.setString(2,lastName);
	                    ps.setString(3,mailId);
	                    
	                    return ps.execute();  
	                          
	                }  
	                
	                });  
	            logger.debug("entry added to db");
	            msg="Success";
	            return msg;
	        }
	        
	        catch(Exception e) {
	            logger.error("failed to add entry in db. "+ e);
	            msg="InvalidInputCriteria";
	            return msg;
	        }
	    }
	    
	 
	 @Override
	    public String insertToPanaTUTable(String firstName, String lastName, String mailId) {
	        
	        String insertRadioDetails = (String)config.getProperty("queryToInsertRadioDetail");
	        logger.debug("Inside insertToPanaTUTable method");
	        String msg;
	        try {
	            jdbcTemplate.execute(insertRadioDetails,new PreparedStatementCallback<Boolean>(){  
	                public Boolean doInPreparedStatement(PreparedStatement ps)  
	                        throws SQLException, DataAccessException { 
	                	ps.setString(1,firstName); 
	                    ps.setString(2,lastName);
	                    ps.setString(3,mailId);
	                    
	                    
	                    return ps.execute();  
	                          
	                }  
	                
	                });  
	            logger.debug("entry added to db");
	            msg="Success";
	            return msg;
	        }
	        
	        catch(Exception e) {
	            logger.error("failed to add entry in db. "+ e);
	            msg="InvalidInputCriteria";
	            return msg;
	        }
	    }
	    
	    
}