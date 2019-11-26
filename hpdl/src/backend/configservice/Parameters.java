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

	private Parameters(){
		this.readPropertiesFile();
	}

	private int listenerReadTimer;
	private String masterLogLevel;
	private String masterLogFile;
	private String inputDirectory;
	private String outputDirectory;

	public int getListenerReadTimer(){return this.listenerReadTimer;}
	public String getMasterLogLevel(){return this.masterLogLevel;}
	public String getMasterLogFile(){return this.masterLogFile;}
	public String getInputDirectory(){return this.inputDirectory;}
	public String getOutputDirectory(){return this.outputDirectory;}

	public void readPropertiesFile(){
		String baseDirectory = System.getenv(CustomConstants.HPDL_BASE_DIR);
		String configFileName = System.getenv(CustomConstants.APP_CONFIG_FILENAME);
		try(BufferedReader br = new BufferedReader(new FileReader(configFileName))){
			String sCurrentLine;
			String sCurrentLineTag;
			String sCurrentLineValue;

			while((sCurrentLine = br.readLine()) != null){
				if(!sCurrentLine.isEmpty() && sCurrentLine.contains(CustomConstants.CONFIG_PROP_PREFIX)) {
					sCurrentLineTag = sCurrentLine.split("=")[0];
					sCurrentLineValue = sCurrentLine.split("=")[1];

					switch(sCurrentLineTag){
						case CustomConstants.INPUT_READ_TIMER:
							listenerReadTimer = Integer.parseInt(sCurrentLineValue);
							break;
						case CustomConstants.MASTER_LOG_LEVEL:
							masterLogLevel = sCurrentLineValue;
							break;
						case CustomConstants.MASTER_LOG_FILENAME:
							masterLogFile = baseDirectory + sCurrentLineValue;
							break;
						case CustomConstants.APP_INPUT_DIRECTORY:
							inputDirectory = baseDirectory + sCurrentLineValue;
							break;
						case CustomConstants.APP_OUTPUT_DIRECTORY:
							outputDirectory = baseDirectory + sCurrentLineValue;
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
