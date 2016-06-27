import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

class DeviceControl {
    String name;
    String modelName;
    String modelNumber;
    String serialNumber;
    String macAddress;
    String ipAddress;
    String port;
    String endpoint;
    String firmware;
    DeviceControl(String endpoint) throws IOException {
 
    	
//    	URL url = new URL("http://${\"192.168.1.90\"}:${1900}/upnp/control/basicevent1");
  //      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    //    connection.setRequestMethod("GET");
      //  connection.setRequestProperty("Content-Type", 
//                "text/xml; charset=\"utf-8\"");
//        connection.setRequestProperty("Host", "\"urn:Belkin:service:basicevent:1#GetBinaryState\"");
//
//        InputStream is = connection.getInputStream();
//    
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//        StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
//        String line;
//        while((line = rd.readLine()) != null) {
//          response.append(line);
//          response.append('\r');
//        }
//        System.out.println(response);
//        rd.close();
    }
        

    
    public void wemoTurnOn() throws IOException {
    	URL url = new URL("http://192.168.1.92:49153/upnp/control/basicevent1");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");
        connection.setRequestProperty("Accept", "");
        connection.setRequestProperty("SOAPAction", "\"urn:Belkin:service:basicevent:1#SetBinaryState\"");
        
        
	    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	           "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
	    +      "<s:Body>"
	    +      "<u:SetBinaryState xmlns:u=\"urn:Belkin:service:basicevent:1\">"
	    +      "<BinaryState>1</BinaryState>" 
	    +      "</u:SetBinaryState>"
	    +      "</s:Body>"
	    +      "</s:Envelope>";
    			    		
	    connection.setDoOutput(true);
	    connection.setDoInput(true);

	    OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		os.flush();
		os.close();
	    int res = connection.getResponseCode();
	   // System.out.println(res);
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
    	URL url = new URL("http://192.168.1.92:49153/upnp/control/basicevent1");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");
        connection.setRequestProperty("Accept", "");
        connection.setRequestProperty("SOAPAction", "\"urn:Belkin:service:basicevent:1#SetBinaryState\"");
        
        
	    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	           "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
	    +      "<s:Body>"
	    +      "<u:SetBinaryState xmlns:u=\"urn:Belkin:service:basicevent:1\">"
	    +      "<BinaryState>0</BinaryState>" 
	    +      "</u:SetBinaryState>"
	    +      "</s:Body>"
	    +      "</s:Envelope>";
    			    		
	    connection.setDoOutput(true);
	    connection.setDoInput(true);

	    OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		os.flush();
		os.close();
	    int res = connection.getResponseCode();
	   // System.out.println(res);
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