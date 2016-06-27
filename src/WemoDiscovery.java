import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

class WemoDiscovery {
	Listener listener;
    Long msToListen;
    Set<String> endpoints = new HashSet<>();
    String iface;
    public WemoDiscovery() { 
    	
    }
    private class Reminder {
        Timer timer;

        private Reminder(Listener listener, int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(listener), seconds*1000);
    	}

        class RemindTask extends TimerTask {
        	Listener listener;
        	RemindTask(Listener listener) {
        		this.listener = listener;
        	}
            public void run() {
                System.out.println("Time's up!");
                listener.terminate();
                timer.cancel(); //Terminate the timer thread
            }
        }
    }
    public Set<String> getEndpoints() throws IOException, InterruptedException {
    	listener = new Listener();
    	
    	Thread listenerThread = new Thread(listener);
    	listenerThread.start();
    	
        discover();
        
        new Reminder(listener, 2);
        
        listenerThread.join();
        
        System.out.print("Finished");
        
        return endpoints;
    }

    
    

    private void discover() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("239.255.255.250"), 1900);
        MulticastSocket socket = new MulticastSocket(null);
        try {
            socket.bind(new InetSocketAddress("192.168.1.90", 1901));
            StringBuilder packet = new StringBuilder();
            packet.append( "M-SEARCH * HTTP/1.1\r\n" );
            packet.append( "HOST: 239.255.255.250:1900\r\n" );
            packet.append( "MAN: \"ssdp:discover\"\r\n" );
            packet.append( "MX: 2").append( "\r\n" );
            packet.append( "ST: " ).append( "ssdp:all" ).append( "\r\n" ).append( "\r\n" );
            packet.append( "ST: " ).append( "urn:Belkin:device:controller:1" ).append( "\r\n" ).append( "\r\n" );
            byte[] data = packet.toString().getBytes();
            System.out.println("sending discovery packet");
            socket.send(new DatagramPacket(data, data.length, socketAddress));
        } catch (IOException e) {
        	listener.terminate();
            throw e;
        } finally {
            socket.disconnect();
            socket.close();
        }
    }
}