package com.threads;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WritingThread implements Runnable {

    private List<Integer> listToModify = Collections.synchronizedList(new ArrayList<>());
    private int threadID;
    private Random random = new Random();
    WritingThread(List<Integer> list, int ID) {
        this.listToModify = list;
        this.threadID = ID;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(500, 1500));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // synchronized keyword used by Collections.synchronizedList to avoid ConcurrentModificationException
            synchronized (listToModify) {
                if (listToModify.size() >= 100) {
                    //Ending the threads work when list size exceeds 100
                    Thread.currentThread().interrupt();
                    System.out.println("Array size exceeded: interrupting (Thread ID: " + this.threadID + ")");
                    return;
                }
                listToModify.add(threadID);
                //Showing which thread has last modified the list and how does it currently look like
                System.out.println("\033c" + "Writing Thread ID: " + this.threadID);
                System.out.println("\n" + listToModify);
            }
        }
    }
    
}