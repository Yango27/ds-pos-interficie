package ui_students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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