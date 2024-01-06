import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server is running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                DataInputStream dis = new DataInputStream(socket.getInputStream());

                int fileNameLength = dis.readInt();
                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];
                    dis.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);

                    int fileContentLength = dis.readInt();
                    if (fileContentLength > 0) {
                        byte[] fileContentBytes = new byte[fileContentLength];
                        dis.readFully(fileContentBytes, 0, fileContentBytes.length);

                        OutputStream os = new FileOutputStream(fileName);
                        os.write(fileContentBytes);
                        System.out.println("File " + fileName + " received from client.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}