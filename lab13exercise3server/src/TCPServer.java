import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TCPServer {

	public static void main(String[] args) throws IOException {
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 2339;
		ServerSocket serverSocket = new ServerSocket(portNo);

		// Launch the server frame
		FrameServer serverFrame = new FrameServer();
		serverFrame.setVisible(true);

		// Counter to keep track the number of requested connection
		int totalRequest = 0;

		// Server needs to be alive forever
		//while (true) {

			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);


			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();


			// Read stream data from the client
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String input = bufferReader.readLine();
			System.out.println("input = " + input);
			
			// get the value for the total words using method wordsCount()
			int totalWords = wordsCount(input);

			// Create stream to write data on the network
			PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

			printWriter.println(Integer.toString(totalWords));
			printWriter.flush();
			System.out.println("word count sent to client");
			System.out.println("total words = " + totalWords);

			// Close the socket
			clientSocket.close();
			
			printWriter.close();
			bufferReader.close();
			
			// Update the request status
			serverFrame.updateRequestStatus(
					"Words Count: " + String.valueOf(totalWords));
			serverFrame.updateRequestStatus(input);
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
		
	}
	
	private static int wordsCount(String text) {
        // Custom word count implementation based on your requirements
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

}