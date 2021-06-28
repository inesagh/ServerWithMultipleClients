package com.multipleClients;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ForMultipleClients implements Runnable {
    private Socket client;

    public ForMultipleClients(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            InputStream inputStream = client.getInputStream();

            while(true) {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                String clientMessage = (String) objectInputStream.readObject();
                System.out.println(clientMessage);

                if (clientMessage.equals("exit")) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
