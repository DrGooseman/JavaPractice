package com.bbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection extends Thread{
    Socket socket;

    public ServerConnection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client connected.");
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String echoString = input.readLine();
                if (echoString == "exit"){
                    break;
                }
                System.out.println("Client message: " + echoString);
                output.println("Server response: " + echoString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
