package ru.spbu.apcyb.svp.tasks;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor {
  private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
  private volatile boolean isWorking = true;

  private final class TaskWork implements Runnable {

    @Override
    public void run() {

      while (isWorking) {
        Runnable nextTask = workQueue.poll();
        if (nextTask != null) {
          nextTask.run();
        }
      }
    }
  }

  public ThreadPool(int countThreads) {

    for (int i = 0; i < countThreads; i++) {
      new Thread(new TaskWork()).start();
    }
  }

  @Override
  public void execute(Runnable command) {

    if (isWorking) {
      workQueue.offer(command);
    }
  }

  public void shutdown() {
    isWorking = false;
  }

}
