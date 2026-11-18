package ui_solutions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class responsible of starting the order to create a new sale when the user interacts with the interface
class NewSaleListener implements ActionListener {

  private Mediator mediator;

  public NewSaleListener(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  // when an action is performed in the user interface, calls newSale from the mediator,
  // that will be the function resposible for calling the right functions from the receivers and update them in order to create a new sale
  public void actionPerformed(ActionEvent actionEvent) {
    mediator.newSale();
  }
}