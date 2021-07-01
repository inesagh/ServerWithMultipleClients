package com.multipleClients;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Main {
    static HashSet<Socket> socketHashSet = new HashSet<>();
    static String message = "";
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while(true) {
                Socket client = serverSocket.accept();
                System.out.println("Connected");

                Thread thread = new Thread(new ForMultipleClients(client));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromClients(String message){
        int i = 0;
        try {
            for (Socket socket : socketHashSet) {
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(message);
                System.out.println("Client " + i + ":" + message);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
