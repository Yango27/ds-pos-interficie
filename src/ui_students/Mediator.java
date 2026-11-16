package ui_students;

public interface Mediator {
    void tableSelected(TableListener tableName, String tableId, boolean hasASale, int saleId);
    void newSale();
    void productSelected(String productName);
    void printReceipt();
    void pay();
}
