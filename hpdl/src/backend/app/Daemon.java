package backend.app;

import backend.configservice.Parameters;
import backend.logger.Logger;
import backend.utils.FileManager;

public class Daemon implements Runnable {

    private boolean stopFlag = false;

    public Daemon(){
        // Empty constructor
    }

    @Override
    public void run() {
        while(true && !stopFlag){
            try{
                Logger.writeLogEntry("DEBUG", "LISTENER_THREAD", "Checking input file directory");
                this.checkInputFiles();
                Thread.sleep(Parameters.getParameters().getListenerReadTimer());
            }catch(InterruptedException interruptedException){
                interruptedException.printStackTrace();
                Logger.writeLogEntry("ERROR", "LISTENER_THREAD", interruptedException.getMessage());
            }catch(Exception exception){
                exception.printStackTrace();
                Logger.writeLogEntry("ERROR", "LISTENER_THREAD", exception.getMessage());
            }
        }
        Logger.writeLogEntry("DEBUG", "LISTENER_THREAD", "Stoping working thread!");
    }

    private void checkInputFiles(){
        // Check for stop flag
        Logger.writeLogEntry("DEBUG", "LISTENER_THREAD", "Checking stop flag");
        stopFlag = FileManager.checkStopSemaphore(Parameters.getParameters().getInputDirectory());
    }
}