package ru.spbu.apcyb.svp.tasks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * ThreadPool.
 */
public class ThreadPool implements Executor {
  
  private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
  private volatile boolean isRunning = true;
  
  /**
   * ThreadPool.
   */
  public ThreadPool(int numbOfThreads) {
    
    for (int i = 0; i < numbOfThreads; i++) {
      new Thread(new TaskWorker()).start();
    }
  }
  
  @Override
  public void execute(Runnable command) {
    
    if (isRunning) {
      workQueue.offer(command);
    }
  }
  
  public void shutdown() {
    isRunning = false;
  }
  
  private final class TaskWorker implements Runnable {
    
    @Override
    public void run() {
      
      while (isRunning) {
        Runnable nextTask = workQueue.poll();
        if (nextTask != null) {
          nextTask.run();
        }
      }
    }
  }
  
}