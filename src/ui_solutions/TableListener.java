package ui_solutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class that represents a table, and receives the orders of the listeners in order to change the state of the sales,
// or the number of products. It is also a listener, so it can order through the Mediator, for example, it controlls
// the statusPanel and also itself, to track which is the current table
class TableListener implements ActionListener {

  private static JButton selectedButton = null;

  private JButton table;

  private int saleId;

  private boolean hasASale = false;

  private int numProducts;

  private Mediator mediator;

  public TableListener(
      JButton table,
      StatusPanel statusPanel,
      Mediator mediator) {

    this.table = table;
    this.mediator = mediator;
    this.numProducts = 0;
  }

  // when an action is performed in the user interface, calls tableSelected from the mediator,
  // that will be the function resposible for calling the right functions from the receivers and update them in
  // order to select a table
  @Override
  public void actionPerformed(
      ActionEvent actionEvent) {

    if (selectedButton != null) {
      selectedButton.setBackground(null);
    }

    table.setBackground(Color.GREEN);

    selectedButton = table;

    mediator.tableSelected(this, getTableId(), hasASale, saleId);
  }

  public int getSaleId() {
    return saleId;
  }

  public void setSaleId(int saleId) {
    this.saleId = saleId;
    hasASale = true;
    numProducts = 0;
  }

  public boolean hasASale() {
    return hasASale;
  }

  public void clearSale() {
    hasASale = false;
    numProducts = 0;
  }

  public String getTableId() {
    return table.getText();
  }

  public void increaseNumProducts(){
    numProducts++;
  }

  public int getNumProducts(){
    return numProducts;
  }
}