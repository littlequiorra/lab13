

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.swing.JFrame;

public class TCPServer extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TCPServer() {
        // Set up the frame properties
        setTitle("TCP Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {

        // Binding to a port
        int portNo = 2345;
        ServerSocket serverSocket = new ServerSocket(portNo);

        // Launch the server frame
        TCPServer serverFrame = new TCPServer();
        serverFrame.setVisible(true);

        // Counter to keep track of the number of requested connections
        int totalRequest = 0;

        while (true) {
            // Message to indicate the server is alive
            serverFrame.setVisible(false);

            // Accept client request for connection
            Socket clientSocket = serverSocket.accept();

            // Read stream data from the client
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String input = bufferReader.readLine();
            System.out.println("input = " + input);

            // Get the value for the total words using the wordsCount() method
            int totalWords = wordsCount(input);

            // Create a stream to write data on the network
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

            printWriter.println(Integer.toString(totalWords));
            printWriter.flush();
            System.out.println("Word count sent to the client");
            System.out.println("Total words = " + totalWords);

            // Close the socket
            clientSocket.close();

            printWriter.close();
            bufferReader.close();

            // Update the request status
            serverFrame.updateRequestStatus("Word Count: " + String.valueOf(totalWords));
            serverFrame.updateRequestStatus(input);
            serverFrame.updateRequestStatus("Accepted connection from the client. Total request = " + ++totalRequest);
        }
    }

    private static int wordsCount(String text) {
        // Custom word count implementation 
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public void updateRequestStatus(String message) {
        // Method to update the request status
        System.out.println(message);
    }
}
