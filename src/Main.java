import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    	WemoDiscovery wemoDiscovery = new WemoDiscovery();
    	wemoDiscovery.getEndpoints();
    	
    	DeviceControl wemoDeviceControl = new DeviceControl("");
    	wemoDeviceControl.lifXTurnOn();
    	wemoDeviceControl.wemoTurnOff();
    	
    }

}