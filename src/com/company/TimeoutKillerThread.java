package com.company;

import java.util.List;

public class TimeoutKillerThread extends Thread {
  private final int timeOutInMs;
  private final List<? extends Thread> threads;

  public TimeoutKillerThread(int timeOutInMs, List<? extends Thread> threads) {
    this.timeOutInMs = timeOutInMs;
    this.threads = threads;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(this.timeOutInMs);
    } catch (InterruptedException e) {
    }
    System.out.println("Time out, interrupting...");
    this.threads
            .forEach(Thread::interrupt);
  }
}
