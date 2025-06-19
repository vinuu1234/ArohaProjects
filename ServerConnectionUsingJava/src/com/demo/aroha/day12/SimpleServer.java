package com.demo.aroha.day12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Server is listening on port 6000...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            byte[] buffer = new byte[256];
            int bytesRead = input.read(buffer);
            if (bytesRead > 0) {
                String received = new String(buffer, 0, bytesRead);
                System.out.println("Received from client: " + received);
            }

            // Send reply to client
            output.write("Hello from server!".getBytes());

            clientSocket.close();
            System.out.println("Server closed connection.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}