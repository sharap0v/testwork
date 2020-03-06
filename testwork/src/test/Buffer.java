package test;


import java.io.*;
import java.sql.Timestamp;

public class Buffer {
    private int info = 0;
    private int size = 0;
    private int generayteCounter = 0;
    private int readCounter = 0;
    private File filename;
    private int length=0;
    private BufferedReader bufferedReader;
    private String trigger = "Write";

    public Buffer(File filename,int size) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(filename));
        this.filename = filename;
        this.size = size;
    }

    public int getgenerayteCounter() {
        return generayteCounter;
    }

    public int getReadCounter() {
        return readCounter;
    }

    public boolean getBufferedReader() throws IOException {
        return bufferedReader.ready();
    }

    public synchronized String read(){
        String string = null;
        while (info==-1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
            try {
               // if (bufferedReader.ready()) {
                    string = bufferedReader.readLine();
              //  }
            }
            catch (IOException e) {
            }
            info--;
            readCounter++;
            notify();
            return string;
    }

    public synchronized void write(Timestamp timestamp){
        while (trigger.equals("Delete")) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))){
            bufferedWriter.write(timestamp+"\n");
            bufferedWriter.flush();
        }
        catch (IOException e){
        }
        generayteCounter++;
        info++;
        length++;
        //System.out.println("Информации в буфере: " + info);

        if(length==size){
            trigger = "Delete";
            clean();
        }
        else {notify();}

    }
    private synchronized void clean(){
        while (info!=0 ||trigger.equals("Write")){
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
       // System.out.println("Очистка буфера");
        try{
            bufferedReader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("");
            writer.close();
        }
        catch (IOException e){
        }
        length = 0;
        trigger = "Write";
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException e){

        }
        notify();
    }
}


