/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package subscriber;

import controller.StartStopController;
import controller.ValueAdapter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.DataModel;
import subscriber.view.Fenster;

/**
 *
 * @author le
 */
public class Start 
{
  public Start()
  {
    Fenster view = new Fenster();
    DataModel model = new DataModel();
    StartStopController controller = new StartStopController(view, model);
    controller.registerEvents();
    ValueAdapter vAdapter = new ValueAdapter(view, model);
    vAdapter.doSubscribe();
    view.setVisible(true);
  }

  public static void main(String[] args) 
  {
    try    
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }    
    catch (Exception ex)    
    {
      JOptionPane.showMessageDialog(null, ex.toString());
    }
    new Start();
  }

}
