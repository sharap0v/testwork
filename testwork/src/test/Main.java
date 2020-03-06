package test;


import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int consumerCount;
    public static int producerCount;
    public static long frequency;
    public static long timeWork;
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("file.txt");
        file.createNewFile();
        Buffer buffer=new Buffer(file,10);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
        try {
            System.out.println("количество consumer");
            consumerCount = Integer.parseInt(reader.readLine());
            System.out.println("количество producers");
            producerCount = Integer.parseInt(reader.readLine());
            System.out.println("частота генерации producers в секундах");
            frequency = Long.parseLong(reader.readLine());
            System.out.println("время работы producers в секундах");
            timeWork = Long.parseLong(reader.readLine());
            break;
        }
        catch (NumberFormatException e){
            System.out.println("не является числом или неверное количество");
            continue;
        }}
        reader.close();
        Consumer[] consumers = new Consumer[consumerCount];
        for (int i = 0; i<producerCount;i++){
            (new Producer(buffer, timeWork, frequency)).start();
        }
        for (int i = 0; i<consumerCount;i++){
            consumers[i]= new Consumer(buffer);
        }
        for (int i = 0; i<consumerCount;i++){
            consumers[i].start();
        }
        Message message = new Message(timeWork, buffer);
        message.start();
        message.join();
        if (!buffer.getBufferedReader()){
            for (int i = 0; i<consumerCount;i++){
                consumers[i].stop();
            }
        }
        file.delete();
        System.out.println("программа завершена");
    }
}
