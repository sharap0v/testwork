package test;


public class Consumer extends Thread {
        private Buffer buffer;

        Consumer(Buffer buffer){
            this.buffer = buffer;
        }
        private static synchronized void readBuffer(Buffer buffer){
            String string = buffer.read();
            if(string!=null)
            System.out.println(string+" "+ Thread.currentThread().getName());
        }


    @Override
        public void run(){
        while (!isInterrupted()){
            try {
                readBuffer(this.buffer);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                interrupt();
                //break;
            }
        }
    }


}
