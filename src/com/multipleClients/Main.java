package com.multipleClients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
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
}
