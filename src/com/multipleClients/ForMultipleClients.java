package com.multipleClients;

import java.io.*;
import java.net.Socket;

public class ForMultipleClients implements Runnable {
    private Socket client;

    public ForMultipleClients(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                InputStream inputStream = client.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                String clientMessage = (String) objectInputStream.readObject();
                System.out.println(clientMessage);
                Main.socketHashSet.add(client);
                Main.message = clientMessage;
                Main.readFromClients(Main.message);
                if (clientMessage.equals("exit")) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
