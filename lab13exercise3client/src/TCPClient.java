
import java.net.Socket;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TCPClient {

	public static void main(String[] args) {
		
		FrameClient clientFrame = new FrameClient();
		String text = "BITP 3123 DISTRIBUTED APPLICATION DEVELOPMENT";
		try {
			
		// Launch client-side frame
		
		clientFrame.setVisible(true);
			
		// Connect to the server @ localhost, port 2339
		Socket socket = new Socket(InetAddress.getLocalHost(), 2339);

		// Update the status of the connection
		clientFrame.updateConnectionStatus(socket.isConnected());
		System.out.println(socket.isConnected());

		// Write text for server
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		
		// send text
		printWriter.println(text);
		System.out.println("text send to server");
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader
				(new InputStreamReader(socket.getInputStream()));

		System.out.println("test");
		// display words count
		String wordsCount = bufferedReader.readLine();
		System.out.println("word = " + wordsCount);
		clientFrame.updateWordsCount(wordsCount);
	

		// Close everything
		bufferedReader.close();
		socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}