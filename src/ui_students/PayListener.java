package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayListener implements ActionListener {

  private Mediator mediator;

  public PayListener(Mediator mediator) {

    this.mediator = mediator;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    mediator.pay();

  }
}