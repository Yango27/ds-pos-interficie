package ui_solutions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class responsible of paying when the user interacts with the user interface
public class PayListener implements ActionListener {

  private Mediator mediator;

  public PayListener(Mediator mediator) {

    this.mediator = mediator;
  }

  // when an action is performed in the user interface, calls pay from the mediator,
  // that will be the function resposible for calling the right functions from the
  // receivers and update them in order to pay the table
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    mediator.pay();

  }
}