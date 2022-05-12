package sg.edu.nus.iss.workshop27Redo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.workshop27Redo.exception.OrderTooLargeException;
import sg.edu.nus.iss.workshop27Redo.models.LineItem;
import sg.edu.nus.iss.workshop27Redo.models.PurchaseOrder;
import sg.edu.nus.iss.workshop27Redo.repository.LineItemRepository;
import sg.edu.nus.iss.workshop27Redo.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {
    
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository lineItemRepo;

    @Transactional(rollbackFor = OrderTooLargeException.class)
    public Integer createPurchaseOrder(PurchaseOrder po) throws OrderTooLargeException {

        Integer orderId = poRepo.insertPurchaseOrder(po);
        double totalUnitPrice = 0d;

        for (LineItem li : po.getLineItems()) {
            totalUnitPrice = li.getQuantity() * li.getUnitPrice();
            if (totalUnitPrice > 10000) {
                OrderTooLargeException ex = 
                    new OrderTooLargeException("Order exceeded SGD 10000 : %d".formatted(totalUnitPrice));
                ex.setPo(po);
                throw ex;
            }
        }

        lineItemRepo.addLineItem(orderId, po.getLineItems());
        return 0;
    }
        
    
}
