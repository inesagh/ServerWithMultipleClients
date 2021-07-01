package com.multipleClients.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    static String clientMessage = "";
    static String socketMessage = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 8080);

            Thread write = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                            clientMessage = scanner.nextLine();
                            objectOutputStream.writeObject(clientMessage);

                            if (clientMessage.equals("exit")) {
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread read = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                            socketMessage = (String) objectInputStream.readObject();
                            System.out.println(socketMessage);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            write.start();
            read.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
