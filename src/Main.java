import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	WemoDiscovery wemoDiscovery = new WemoDiscovery();
    	wemoDiscovery.getEndpoints();
    	
    	WemoDeviceControl wemoDeviceControl = new WemoDeviceControl("");
    	wemoDeviceControl.lifXTurnOn();
    	wemoDeviceControl.turnOff();
    	
    }

}