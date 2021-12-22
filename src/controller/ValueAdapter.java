/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import model.DataModel;
import subscriber.view.Fenster;

/**
 *
 * @author le
 */
public class ValueAdapter implements Subscriber<Integer>
{
  private Fenster view;
  private DataModel model;
  private Subscription subscription;
  
  public ValueAdapter(Fenster view, DataModel model)
  {
    this.view = view;
    this.model = model;
  }
  
  public void doSubscribe()
  {
    model.addValueSubscriptior(this);
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription)
  {
    this.subscription = subscription;
    subscription.request(1);
    subscription.request(6);
  }

  @Override
  public void onNext(Integer item)
  {
    String strValue = String.valueOf(item);
//    String oldValue = view.getLblWert().getText();
//    view.getLblWert().setText(oldValue + " - " + strValue);
    view.getLblWert().setText(strValue);
    subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable)
  {
    System.err.println(throwable);
  }

  @Override
  public void onComplete()
  {
    System.err.println("FERTIG");
  }
}
