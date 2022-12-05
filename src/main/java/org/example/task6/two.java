package org.example.task6;

import java.util.ArrayList;

public class two {

    public static void main(String[] args) {
        System.out.println("The different states of a thread are: ");

        ArrayList<Thread.State> states = new ArrayList<>();
        states.add(Thread.State.valueOf("NEW"));
        states.add(Thread.State.valueOf("RUNNABLE"));
        states.add(Thread.State.valueOf("BLOCKED"));
        states.add(Thread.State.valueOf("WAITING"));
        states.add(Thread.State.valueOf("TIMED_WAITING"));
        states.add(Thread.State.valueOf("TERMINATED"));

        for (Thread.State state : states) {
            System.out.print(state + " ");
        }
        System.out.print("\nLet's get practical:\n");

        MyThreadRunnable t = new MyThreadRunnable("Thread States");
        t.start();
        //wait 500 milliseconds for the thread to die
        t.join(500);
        t.printState();
        t.join(3000);
        t.printState();
        //Notify thread to wake up
        t.threadNotify();
        t.printState();
        //wait forever for the thread to die
        t.join();
        t.printState();

    }
}


class MyThreadRunnable implements Runnable{
        private Thread t;
        private final String name;
        private final Object obj;
        public MyThreadRunnable(String name)
        {
            this.name=name;
            obj=new Object();
        }
        @Override
        public void run() {
            printState();
            try {
                //Make thread sleep for 2000 milliseconds
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (obj) {
                try {
                    //make the thread wait until it is notified
                    obj.wait();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            printState();
        }
        public void start()
        {
            t=new Thread(this,name);
            printState();
            t.start();
        }

        public void printState()
        {
            //print the current state of the thread
            Thread.State state = t.getState();
            System.out.println(state.toString());
        }

        public  void join() {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public  void join(int millis) {
            try {
                t.join(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void threadNotify()
        {
            synchronized (obj) {
                obj.notify();
            }
        }
    }
