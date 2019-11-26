package backend.configservice;

public interface ParametersMBean{
	int getListenerReadTimer();
	String getMasterLogLevel();
	String getMasterLogFile();
	String getInputDirectory();
	String getOutputDirectory();
	void readPropertiesFile();
}

