package ru.spbu.apcyb.svp.tasks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;

public class ThreadPool  implements Executor{
  private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
  private boolean isRunning=true;

  public ThreadPool(int numOfThreads){
    for (int i=0; i<numOfThreads; i++){
      new Thread(new Run()).start();
    }

  }
  @Override
  public void execute(@Nonnull Runnable command){
    if (isRunning){
      try {
        Thread.sleep(0, 10);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      queue.offer(command);
    }
  }
  public void stop(){
    isRunning=false;
  }
  private final class Run implements Runnable{

    @Override
    public void run(){
      while (isRunning){
        Runnable nextTask = queue.poll();

        if (nextTask!=null){
          nextTask.run();
        }
      }
    }
  }

}
