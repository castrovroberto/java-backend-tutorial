package backend.logger;

import backend.configservice.Parameters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
	
	public static void writeLogEntry(String logLevel, String appModule, String logEntry){
		BufferedWriter bw = null;
		FileWriter fw = null;
		String configLogLevel = Parameters.getParameters().getMasterLogLevel();
		try {
			fw = new FileWriter(Parameters.getParameters().getMasterLogFile(), true);
			bw = new BufferedWriter(fw);

			StringBuilder strBuilder = new StringBuilder();
			LocalDateTime date = LocalDateTime.now();
			String timestamp = date.toString();

			if(configLogLevel.equals("DEBUG") || configLogLevel.equals(logLevel)) {
				switch(logLevel){
					case "DEBUG":
						strBuilder.append("D");
						strBuilder.append(" - ");
						strBuilder.append(timestamp);
						strBuilder.append(" - DEBUG: ");
						strBuilder.append(appModule);
						strBuilder.append(" ");
						strBuilder.append(logEntry);
						strBuilder.append("\n");
						break;
					case "ERROR":
						strBuilder.append("E");
						strBuilder.append(" - ");
						strBuilder.append(timestamp);
						strBuilder.append(" - ERROR: ");
						strBuilder.append(appModule);
						strBuilder.append(" ");
						strBuilder.append(logEntry);
						strBuilder.append("\n");
						break;
					case "INFO":
						strBuilder.append("I");
						strBuilder.append(" - ");
						strBuilder.append(timestamp);
						strBuilder.append(" - INFO: ");
						strBuilder.append(appModule);
						strBuilder.append(" ");
						strBuilder.append(logEntry);
						strBuilder.append("\n");
						break;
					default:
						break;
				}
				bw.write(strBuilder.toString());
			}
		}catch(IOException ioException){
			System.out.println("IO exception while trying to write log entry");
			ioException.printStackTrace();
		}catch(Exception exception){
			System.out.println("Unknown exception while trying to write log entry");
			exception.printStackTrace();
		}finally {
			try {
				if(bw != null){
					bw.close();
				}
				if(fw != null){
					fw.close();
				}
			}catch(IOException iofException){
				System.out.println("IO exception while trying to close buffered/file writers");
				iofException.printStackTrace();
			}
		}
	}
}
