package com.jrdevel.aboutus.core.cloud;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.jrdevel.aboutus.core.common.configuration.AboutUsConfiguration;

/**
 * @author Raphael Domingues
 *
 */
@Component
public class GoogleDriveService {

	private static GoogleCredential credential; 
	
    private static final Logger logger = Logger.getLogger(GoogleDriveService.class);
    
    @Autowired
    private AboutUsConfiguration conf;

	private void manageCredential(){
		logger.info("entering manageCredential method");
		long start = System.currentTimeMillis();
		if (credential == null){
			logger.info("credential is null");
			initializeCredential();
		}else if (credential.getExpiresInSeconds() <= 20){
			try {
				credential.refreshToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		logger.info("Took " + ((end - start) / 1000) + " seconds to initialize credential");
	}

	public String insert(String title, String type, InputStream inputStream){
		logger.info("entering insert method");
		manageCredential();
		
		logger.info("initiliaze send the file");
		long start = System.currentTimeMillis();
		Drive service = new Drive.Builder(new ApacheHttpTransport(), new JacksonFactory(), credential).
	    		setApplicationName("AboutChurch").build();
		
		File body = new File();
	    body.setTitle(title);
	    body.setMimeType(type);
		
		InputStreamContent content = new InputStreamContent(type, inputStream);
		
		try {
			File file = service.files().insert(body, content).execute();
			long end = System.currentTimeMillis();
			logger.info("Took " + ((end - start) / 1000) + " seconds to send the file to google drive");
			return file.getId();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void delete(String fileId){
		manageCredential();
		
		Drive service = new Drive.Builder(new ApacheHttpTransport(), new JacksonFactory(), credential).
				setApplicationName("AboutChurch").build();
		try {
			service.files().delete(fileId).execute();
			
		} catch (IOException e) {
			// An error occurred.
		}
	}
	
	public InputStream get(String fileId){
		manageCredential();
		
		Drive service = new Drive.Builder(new ApacheHttpTransport(), new JacksonFactory(), credential).
				setApplicationName("AboutChurch").build();
		try {
			File file = service.files().get(fileId).execute();
			
			HttpResponse resp =
					service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
					.execute();
			return resp.getContent();
		} catch (IOException e) {
			// An error occurred.
			e.printStackTrace();
			return null;
		}
	}

	private void initializeCredential(){
		credential = new GoogleCredential.Builder()
		.setTransport(new NetHttpTransport())
		.setJsonFactory(new JacksonFactory())
		.setClientSecrets(conf.getClientId(), conf.getClientSecret())
		.addRefreshListener(new CredentialRefreshListener() {
			public void onTokenResponse(Credential credential, TokenResponse tokenResponse) {
				logger.info("Credential was refreshed successfully.");
			}

			public void onTokenErrorResponse(Credential credential,
					TokenErrorResponse tokenErrorResponse) {
				logger.error("Credential was not refreshed successfully. "
						+ "Redirect to error page or login screen.");
			}
		}).build();

		credential.setRefreshToken(conf.getRefreshToken());

		try {
			credential.refreshToken();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
