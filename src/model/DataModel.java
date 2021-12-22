/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author le
 */
public class DataModel implements Runnable // Callable
{
  private int value;
  /** Zustandsvariable -> bei Nebenläufigkeit immer nötig 
      boolean für 2 Werte - bei mehreren Werten: enum
   */
  private boolean running; 
  private SubmissionPublisher<Integer> iPublisher;
  //private Thread thd;
  private ExecutorService eService;
  
  private Object obj;
  
  public DataModel()
  {
    value = 1;
    running = false;
    iPublisher = new SubmissionPublisher<>();
    eService = Executors.newSingleThreadExecutor();
    
    obj = new Object();
    
  }
  
  public void addValueSubscriptior(Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
  public void start()
  {
    running = true;
    eService.execute(this); // submit(this) liefert Future Object
  }
  
  public void stop()
  {
    running = false;
  }
  

  @Override
  public void run()
  {
    int i = 1;
    while (running)
    {
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException ex)
      {
        System.err.println(ex);
      }
      //value++;
      value = i;
      // Subscriber benachrichtigen und Wert mitsenden (via Event)
      
      if (running)
      {
        iPublisher.submit(value);
        if(i == 6) 
        {
          i = 1;
        }
        else
        {
          i += 1;          
        }
      }
      else
      {
        try
        {
          synchronized (obj){
          obj.wait();
          }
        }
        catch (InterruptedException exe)
        {
          System.err.println(exe);  
        }
                
      }
      
      
     
    }
  }
}
