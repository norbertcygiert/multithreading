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
            synchronized (listToModify) {
                if (listToModify.size() >= 100) {
                    Thread.currentThread().interrupt();
                    System.out.println("Array size exceeded: interrupting (Thread ID: " + this.threadID + ")");
                    return;
                }
                listToModify.add(threadID);
                System.out.println("\033c");
                System.out.println(listToModify + "   Writing Thread ID: " + this.threadID);
            }
        }
    }
    
}