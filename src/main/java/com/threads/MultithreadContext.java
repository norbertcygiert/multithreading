package com.threads;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class MultithreadContext {

    private final static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    
    MultithreadContext(){
        list.addAll(Arrays.asList(0));
    };
    
    public static void CreateContext(){
        Thread t1 = new Thread(new WritingThread(list, 1));
        Thread t2 = new Thread(new WritingThread(list, 2));
        Thread t3 = new Thread(new WritingThread(list, 3));
        Thread t4 = new Thread(new WritingThread(list, 4));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}