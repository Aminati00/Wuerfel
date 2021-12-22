/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DataModel;
import subscriber.view.Fenster;

/**
 *
 * @author le
 */
public class StartStopController implements ActionListener
{
  private Fenster view;
  private DataModel model;
  
  public StartStopController(Fenster view, DataModel model)
  {
    this.view = view;
    this.model = model;
  }
  
  public void registerEvents()
  {
    view.getBtnStart().addActionListener(this);
    view.getBtnStop().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    if (evt.getSource() == view.getBtnStart())
    {
      model.start();
    }
    else
    {
      model.stop();
    }
  }
}
