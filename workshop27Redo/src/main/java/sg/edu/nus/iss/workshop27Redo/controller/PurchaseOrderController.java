package sg.edu.nus.iss.workshop27Redo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop27Redo.exception.OrderTooLargeException;
import sg.edu.nus.iss.workshop27Redo.models.PurchaseOrder;
import sg.edu.nus.iss.workshop27Redo.service.PurchaseOrderService;

@RestController
@RequestMapping(path="/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService poSvc;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder (@RequestBody String json) {
        PurchaseOrder order = new PurchaseOrder();
        JsonObject resp = null;

        try {
           order = PurchaseOrder.create(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp = Json.createObjectBuilder()
                        .add("error",ex.getMessage())
                        .build();

            return ResponseEntity.badRequest().body(resp.toString());
        }

        Integer orderId;
        try {
            orderId = poSvc.createPurchaseOrder(order);
            resp = Json.createObjectBuilder()
                        .add("orderId", orderId)
                        .build();
        } catch (OrderTooLargeException e) {
            e.printStackTrace();
            resp = Json.createObjectBuilder()
                        .add("error", e.getMessage())
                        .build();
        }

        return ResponseEntity.ok(resp.toString());
    }
    
}
