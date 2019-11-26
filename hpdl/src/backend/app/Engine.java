package backend.app;

import javax.management.*;
import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

import backend.configservice.Parameters;
import backend.logger.Logger;

public class Engine{
	public static void main(String[] args){
		Logger.writeLogEntry("INFO", "ENGINE", "Engine Starting!");
		Engine.registerConfigServiceMBean();
		Engine.startDaemonEngine();
	}

	private static void registerConfigServiceMBean(){
		Logger.writeLogEntry("INFO", "ENGINE", "Registering ConfigService MBean");
		try{
			String objectName = "backend:type=basic,name=configservice";
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			ObjectName mbeanName = new ObjectName(objectName);
			Parameters mbean = Parameters.getParameters();
			server.registerMBean(mbean, mbeanName);
		} catch(MalformedObjectNameException e){
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        Logger.writeLogEntry("INFO", "ENGINE", "ConfigService MBean successfully registered");
	}

	private static void startDaemonEngine(){
		Logger.writeLogEntry("INFO", "ENGINE", "Starting daemon thread");
		Thread daemonThread = new Thread(new Daemon());
		daemonThread.start();
	}
}
