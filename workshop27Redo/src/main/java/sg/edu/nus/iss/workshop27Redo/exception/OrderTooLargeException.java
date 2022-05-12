package sg.edu.nus.iss.workshop27Redo.exception;
import sg.edu.nus.iss.workshop27Redo.models.PurchaseOrder;

public class OrderTooLargeException extends Exception {

    private PurchaseOrder po;

    public PurchaseOrder getPo() { return po; }
    public void setPo(PurchaseOrder po) { this.po = po; }

    public OrderTooLargeException() {
        super();
    }

    public OrderTooLargeException(String message) {
        super(message);
    }


    
}
