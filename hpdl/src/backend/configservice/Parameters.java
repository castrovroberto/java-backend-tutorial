package backend.configservice; 

import backend.utils.CustomConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parameters implements ParametersMBean{
	private static Parameters parameters;

	// **********************************
	// Constructor - Singleton
	// **********************************

	public static Parameters getParameters(){
		if (parameters == null){
			parameters = new Parameters();
		}
		return parameters;
	}

	private int LISTENER_READ_TIMER;

	public int getListenerReadTimer(){
		return this.LISTENER_READ_TIMER;
	}

	public void readPropertiesFile(){
		String configFileName = System.getenv(CustomConstants.APP_CONFIG_FILENAME);
		try(BufferedReader br = new BufferedReader(new FileReader(configFileName))){
			String sCurrentLine;
			String sCurrentLineTag;
			String sCurrentLineValue;

			while((sCurrentLine = br.readLine()) != null){
				if(!sCurrentLine.isEmpty() && sCurrentLine.contains("backend.hpdl.app")) {
					sCurrentLineTag = sCurrentLine.split("=")[0];
					sCurrentLineValue = sCurrentLine.split("=")[1];

					switch(sCurrentLineTag){
						case "backend.hpdl.app.read.timer":
							LISTENER_READ_TIMER = Integer.parseInt(sCurrentLineValue);
							break;
						default:
							break;
					}
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
			System.out.println("Error loading configuration file... Exiting");
			System.exit(1);
		}catch(Exception exception){
			exception.printStackTrace();
			System.out.println("Unknown error while loading configuration file... Exiting");
			System.exit(1);
		}
	}
}
