import javax.swing.JFrame;
import java.net.Socket;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TCPClient extends JFrame {

    public TCPClient() {
        // Set up the frame properties
        setTitle("TCP Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        TCPClient clientFrame = new TCPClient();
        String text = "BITP 3213";
        try {

            // Launch client-side frame
            clientFrame.setVisible(true);

            // Connect to the server @ localhost, port 2345
            Socket socket = new Socket(InetAddress.getLocalHost(), 2345);

            // Update the status of the connection
            clientFrame.updateConnectionStatus(socket.isConnected());
            System.out.println(socket.isConnected());

            // Write text for server
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            // Send text
            printWriter.println(text);
            System.out.println("Text sent to server");

            // Read from network
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Display word count
            String wordsCount = bufferedReader.readLine();
            System.out.println("Word Count: " + wordsCount);
            clientFrame.updateWordsCount(wordsCount);

            // Close everything
            bufferedReader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateConnectionStatus(boolean isConnected) {
        // Method to update the connection status
        System.out.println("Connection Status: " + isConnected);
    }

    public void updateWordsCount(String count) {
        // Method to update the words count
        System.out.println("Word Count: " + count);
    }
}
