package test;

public class Message extends Thread{
    private long timeWork;
    private Buffer buffer;
    public Message(long timeWork, Buffer buffer) {
        this.timeWork = timeWork;
        this.buffer = buffer;
    }

    @Override
        public void run() {
            long timestart = System.currentTimeMillis();
            while (System.currentTimeMillis()-timestart<timeWork*1000){
                try {
                    Thread.sleep(10000);
                    System.out.printf("\uF0B7Количество сгенерированных данных %d.\n" +
                                    "\uF0B7 Количество потребленных данных %d.\n" +
                                    "\uF0B7 Время работы % d секунд. \n" +
                                    "\uF0B7 Оставшееся время работы. %d секунд\n",
                            buffer.getgenerayteCounter(),
                            buffer.getReadCounter()-1,
                            (System.currentTimeMillis()-timestart)/1000,
                            (timeWork*1000+timestart-System.currentTimeMillis())/1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

