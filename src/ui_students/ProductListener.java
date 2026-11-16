package ui_students;

import pos_creditcard.PointOfSale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductListener implements ActionListener {

  private String productName;
  private Mediator mediator;

  public ProductListener(String productName, Mediator mediator) {

    this.productName = productName;
    this.mediator = mediator;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {

    mediator.productSelected(productName);
  }
}