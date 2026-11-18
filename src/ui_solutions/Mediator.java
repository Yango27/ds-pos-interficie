package ui_solutions;

// interface Mediator, father class of MediatorPos, it defines the functions that our mediator is going to have.
// We use a Mediator Pattern in order to avoid having the same attributes (the receivers of the orders, StatusPanel,
// TableListener, AmountListener and PointOfSale) at the different classes, this way we have a
// low coupling, and less dependencies, Mediator will be the responsible to have this attributes that are shared from
// this different classes and call them in order to do some actions

// Basically, we have two types of classes, the receivers, already mentioned, and the Listeners, the last ones are the
// responsible to react to an action on the user interface to do a certain action, through the Mediator class, calling
// the right function. Each Listener has a reference to the mediator, in order to call the corresponded method to do the
// intend action

public interface Mediator {
    void tableSelected(TableListener tableName, String tableId, boolean hasASale, int saleId);
    void newSale();
    void productSelected(String productName);
    void printReceipt();
    void pay();
}
