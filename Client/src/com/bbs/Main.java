package com.bbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
        Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server.");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            String message;
            String response;

            while(true){
                System.out.println("Enter a message.");
                message = scanner.nextLine();

                printWriter.println(message);

                if (message.equals("exit"))
                    break;

                response = bufferedReader.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }





    }
}
