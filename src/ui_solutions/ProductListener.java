package ui_solutions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class resposible of select a product and add it, if it's possible, to the table
public class ProductListener implements ActionListener {

  private String productName;
  private Mediator mediator;

  public ProductListener(String productName, Mediator mediator) {

    this.productName = productName;
    this.mediator = mediator;
  }

  // when an action is performed in the user interface, calls productSelected from the mediator,
  // that will be the function resposible for calling the right functions from the receivers and update them
  // in order to select a product
  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    mediator.productSelected(productName);
  }
}