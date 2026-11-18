package ui_solutions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class responsible of printing the receipt when the user interacts with the interface
public class PrintReceiptListener implements ActionListener {

  private Mediator mediator;

  public PrintReceiptListener(Mediator mediator) {

    this.mediator = mediator;
  }


  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    // when an action is performed in the user interface, calls print receipt from the mediator,
    // that will be the function resposible for calling the right functions from the receivers and update them in order to
    // print the receipt
    mediator.printReceipt();
  }
}