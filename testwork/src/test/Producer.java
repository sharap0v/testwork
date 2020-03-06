package test;

import java.sql.Timestamp;

public class Producer extends Thread {
    private Buffer buffer;
    private long timeWork;
    private long frequency;

    Producer(Buffer buffer,long timeWork, long frequency){
        this.buffer=buffer;
        this.timeWork = timeWork*1000;
        this.frequency = frequency*1000;
    }
    private synchronized static void printToBuffer(Buffer buffer){
        buffer.write(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void run() {
        long timestart = System.currentTimeMillis();
        while (this.timeWork>System.currentTimeMillis()- timestart){
            printToBuffer(this.buffer);
        try {
            Thread.sleep(frequency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        };
    }
}
