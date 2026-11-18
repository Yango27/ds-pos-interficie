package ui_solutions;

import pos_creditcard.PointOfSale;

// inherits from Mediator, Mediator pattern explained in Mediator.java

public class MediatorPos implements Mediator{
    // these classes are the receivers, they receive the orders that the listeners start
    private TableListener listenerCurrentTable;
    private PointOfSale pointOfSale;
    private StatusPanel statusPanel;
    private AmountListener amountListener;

    public MediatorPos(
        TableListener listenerCurrentTable,
        PointOfSale pointOfSale,
        StatusPanel statusPanel,
        AmountListener amountListener) {

        this.listenerCurrentTable = listenerCurrentTable;
        this.pointOfSale = pointOfSale;
        this.statusPanel = statusPanel;
        this.amountListener = amountListener;


    }
    @Override
    public void newSale() {
        if (listenerCurrentTable == null) {

            statusPanel.refresh("Select a table first");
            return;
        }

        if (listenerCurrentTable.hasASale()) {

            int currentSaleId = listenerCurrentTable.getSaleId();

            if (pointOfSale.isSalePaid(currentSaleId)) {

                int saleId = pointOfSale.makeNewSale();

                listenerCurrentTable.setSaleId(saleId);

                statusPanel.setSale(String.valueOf(saleId));
                statusPanel.setState("UNPAID");
                // set the num products to 0, in case the last sale was not reseted
                statusPanel.setNumProducts(0);
                statusPanel.refresh("New sale created");

            } else {

                statusPanel.setSale(String.valueOf(currentSaleId));
                statusPanel.setState("UNPAID");

                statusPanel.refresh(
                        "Current sale must be paid first");
            }

        } else {

            int saleId = pointOfSale.makeNewSale();

            listenerCurrentTable.setSaleId(saleId);

            statusPanel.setSale(String.valueOf(saleId));
            statusPanel.setState("UNPAID");

            statusPanel.refresh("New sale created");
        }
    }

    @Override
    public void printReceipt() {
        if (listenerCurrentTable != null) {

            if (listenerCurrentTable.hasASale()) {
                int id = listenerCurrentTable.getSaleId();

                if (!pointOfSale.saleHasProducts(id)){
                    statusPanel.refresh("No ordered products yet");
                }
                else{
                    pointOfSale.printReceiptOfSale(id);
                    statusPanel.refresh("Receipt printed");
                }

            } else {

                statusPanel.refresh(
                        "Current table has no sale");
            }
        } else {
            statusPanel.refresh("Select a table first");
        }
    }

    @Override
    public void productSelected(String productName) {
        if (listenerCurrentTable != null) {

            if (listenerCurrentTable.hasASale()) {

                int idSale = listenerCurrentTable.getSaleId();

                pointOfSale.addLineItemToSale(
                        idSale,
                        productName,
                        1);

                listenerCurrentTable.increaseNumProducts();
                statusPanel.setNumProducts(listenerCurrentTable.getNumProducts());

                statusPanel.refresh(
                        "Added product: " + productName);

            } else {

                statusPanel.refresh(
                        "Open a sale before adding products");
            }
        } else {
            statusPanel.refresh("Select a table first");
        }
    }

    @Override
    public void pay() {
        if (listenerCurrentTable != null) {

            if (listenerCurrentTable.hasASale()) {

                int idSale =
                        listenerCurrentTable.getSaleId();

                double paidAmount =
                        amountListener.getPaidAmount();

                if (paidAmount <= 0) {

                    statusPanel.refresh(
                            "Invalid payment amount");

                    return;
                }

                if (!pointOfSale.isAmountHandedEnough(idSale, paidAmount)) {

                    statusPanel.refresh("Amount is not enough");
                    return;
                }

                if (!pointOfSale.saleHasProducts(idSale)) {

                    statusPanel.refresh("No ordered products yet");
                    return;
                }

                if (!pointOfSale.isSalePaid(idSale)) {

                    pointOfSale.payOneSaleCash(
                            idSale,
                            paidAmount);

                    pointOfSale.printPayment(idSale);

                    amountListener.setText("0.0 €");

                    statusPanel.setState("PAID");
                    statusPanel.setNumProducts(0);
                    statusPanel.refresh(
                            "Sale paid");

                    listenerCurrentTable.clearSale();

                } else {

                    statusPanel.refresh(
                            "Sale already paid");
                }
            } else {
                statusPanel.refresh(
                        "Current table has no sale");
            }
        } else {
            statusPanel.refresh("Select a table first");
        }
    }

    @Override
    public void tableSelected(TableListener table, String tableId, boolean hasASale, int saleId) {
        statusPanel.setTable(tableId);
        if (hasASale) {

            statusPanel.setSale(
                    String.valueOf(saleId));

            statusPanel.setState("UNPAID");

        } else {

            statusPanel.setSale("-");
            statusPanel.setState("NO SALE");
        }
        this.listenerCurrentTable = table;
        statusPanel.setNumProducts(listenerCurrentTable.getNumProducts());
        statusPanel.refresh("Table selected");
    }


}
