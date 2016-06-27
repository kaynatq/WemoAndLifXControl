import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class to control all light bulbs
 * @author kaynat
 *
 */
class DeviceControl {
    DeviceControl() throws IOException {
    	
    }
        

    
    public void wemoTurnOn() throws IOException {
    	URL url = new URL("http://192.168.1.92:49153/upnp/control/bridge1");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("SOAPACTION", "\"urn:Belkin:service:bridge:1#SetDeviceStatus\"");
         String xml =
	    		"<?xml version=\"1.0\" encoding=\"utf-8\"?>"
	    +       "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
	    +      "<s:Body>"
	    +      "<u:SetDeviceStatus xmlns:u=\"urn:Belkin:service:bridge:1\">"
	    +      "<DeviceStatusList>"
	    + "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;DeviceStatus&gt;&lt;IsGroupAction&gt;NO&lt;/IsGroupAction&gt;&lt;DeviceID available=&quot;YES&quot;&gt;94103EA2B277FE87&lt;/DeviceID&gt;&lt;CapabilityID&gt;10006,10008&lt;/CapabilityID&gt;&lt;CapabilityValue&gt;1,250&lt;/CapabilityValue&gt;&lt;/DeviceStatus&gt;'"	     
		+      "</DeviceStatusList>"
	    +      "</u:SetDeviceStatus>"
	    +      "</s:Body>"
	    +      "</s:Envelope>";
        
	    connection.setDoOutput(true);
	    connection.setDoInput(true);

	    OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		os.flush();
		os.close();
	    int res = connection.getResponseCode();
        System.out.println(res);
	    InputStream is = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while((line = br.readLine() ) != null) {
	        System.out.println(line);
	    }
	    connection.disconnect();
	    
	}
    
    /**
     * Method to turn off a wemo light
     * @throws IOException
     */
    public void wemoTurnOff() throws IOException {
    	URL url = new URL("http://192.168.1.92:49153/upnp/control/bridge1");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("SOAPACTION", "\"urn:Belkin:service:bridge:1#SetDeviceStatus\"");
        String xml =
	    		"<?xml version=\"1.0\" encoding=\"utf-8\"?>"
	    +       "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
	    +      "<s:Body>"
	    +      "<u:SetDeviceStatus xmlns:u=\"urn:Belkin:service:bridge:1\">"
	    +      "<DeviceStatusList>"
	    + "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;DeviceStatus&gt;&lt;IsGroupAction&gt;NO&lt;/IsGroupAction&gt;&lt;DeviceID available=&quot;YES&quot;&gt;94103EA2B277FE87&lt;/DeviceID&gt;&lt;CapabilityID&gt;10006,10008&lt;/CapabilityID&gt;&lt;CapabilityValue&gt;0,0&lt;/CapabilityValue&gt;&lt;/DeviceStatus&gt;'"	     
		+      "</DeviceStatusList>"
	    +      "</u:SetDeviceStatus>"
	    +      "</s:Body>"
	    +      "</s:Envelope>";
        		    		
	    connection.setDoOutput(true);
	    connection.setDoInput(true);

	    OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		os.flush();
		os.close();
	    int res = connection.getResponseCode();
	    System.out.println(res);
	    InputStream is = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while((line = br.readLine() ) != null) {
	        System.out.println(line);
	    }
	    connection.disconnect();
	    
	}
    
    
    
    /**
     * Method to toggle the state of a lifX bulb
     * @throws IOException
     */
    public void lifXToggle() throws IOException{
    	URL url = new URL("https://api.lifx.com/v1/lights/all/toggle");
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setConnectTimeout(5000);//5 secs
	    connection.setReadTimeout(5000);//5 secs

	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Authorization", 
	    		"Bearer c8edb8fd63084bcb0303254f8a5472a3d06d408c2fedd0532bc6759bae5f2f14");
	    connection.setDoOutput(true);
	    connection.setRequestProperty("Content-Type", "application/json");
	    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());  
	  
	    outputStreamWriter.flush();
	    outputStreamWriter.close();

	    int res = connection.getResponseCode();

	    System.out.println(res);


	    InputStream is = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while((line = br.readLine() ) != null) {
	        System.out.println(line);
	    }
	    connection.disconnect();
    }
    
    /**
     * Method to turn on a LifX bulb
     * @throws IOException
     */
    public void lifXTurnOn() throws IOException{
    	URL url = new URL("https://api.lifx.com/v1/lights/all/state");
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setConnectTimeout(5000);//5 secs
	    connection.setReadTimeout(5000);//5 secs

	    connection.setRequestMethod("PUT");
	    connection.setRequestProperty("Authorization", 
	    		"Bearer c8edb8fd63084bcb0303254f8a5472a3d06d408c2fedd0532bc6759bae5f2f14");
	    connection.setDoOutput(true);
	    connection.setRequestProperty("Content-Type", "application/json");
	    DataOutputStream outputStreamWriter = new DataOutputStream(connection.getOutputStream()); 
	    String urlParameters = "{  \"power\": \"on\", \"color\": \"blue saturation:0.5\",  "
	    		+ "\"brightness\": 0.5,  \"duration\": 5}";
	    outputStreamWriter.write(urlParameters.getBytes());
	    outputStreamWriter.flush();
	    outputStreamWriter.close();

	    int res = connection.getResponseCode();
	    System.out.println(res);


	    InputStream is = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while((line = br.readLine() ) != null) {
	        System.out.println(line);
	    }
	    connection.disconnect();
    }
    
    public void lifXTurnOff() throws IOException{
    	URL url = new URL("https://api.lifx.com/v1/lights/all/state");
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setConnectTimeout(5000);//5 secs
	    connection.setReadTimeout(5000);//5 secs

	    connection.setRequestMethod("PUT");
	    connection.setRequestProperty("Authorization", 
	    		"Bearer c8edb8fd63084bcb0303254f8a5472a3d06d408c2fedd0532bc6759bae5f2f14");
	    connection.setDoOutput(true);
	    connection.setRequestProperty("Content-Type", "application/json");
	    DataOutputStream outputStreamWriter = new DataOutputStream(connection.getOutputStream()); 
	    String urlParameters = "{  \"power\": \"off\"}";
	    outputStreamWriter.write(urlParameters.getBytes());
	    outputStreamWriter.flush();
	    outputStreamWriter.close();

	    int res = connection.getResponseCode();
	    System.out.println(res);


	    InputStream is = connection.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    while((line = br.readLine() ) != null) {
	        System.out.println(line);
	    }
	    connection.disconnect();
    }

}