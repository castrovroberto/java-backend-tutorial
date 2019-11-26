package backend.utils;

import backend.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager{

    public static boolean checkStopSemaphore(String mInputDir) {
        boolean stopSemaphore = false;
        File folder = new File(mInputDir);
        Logger.writeLogEntry("DEBUG", "FILE_MANAGER", "Checking input directory: " + mInputDir);
        File[] listOfFiles = folder.listFiles();

        try {
            for (File f : listOfFiles) {
                if (f.isFile() && f.getName().contains(CustomConstants.APP_STOP_FLAG)) {
                    Logger.writeLogEntry("DEBUG", "FILE_MANAGER", "Detecting stop flag semaphore... ");
                    stopSemaphore = true;
                    Files.delete(Paths.get(mInputDir + f.getName()));
                    break;
                }
            }
        }catch (IOException ioException) {
            Logger.writeLogEntry("ERROR", "FILE_MANAGER", "Error deleting file: " + CustomConstants.APP_STOP_FLAG);
            Logger.writeLogEntry("ERROR", "FILE_MANAGER", ioException.getMessage());
        }
        return stopSemaphore;
    }

}