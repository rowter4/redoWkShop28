package sg.edu.nus.iss.workshop27Redo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop27Redo.models.PurchaseOrder;
import static sg.edu.nus.iss.workshop27Redo.repository.Queries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class PurchaseOrderRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Integer insertPurchaseOrder(PurchaseOrder po) {

        KeyHolder keyholder = new GeneratedKeyHolder();

        template.update(conn -> {
            
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PURCHASE_ORDER, 
                                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, po.getName());
            ps.setString(2, po.getEmail());
            return ps;

        },keyholder);
        
        BigInteger bigInt = (BigInteger) keyholder.getKey();
        return bigInt.intValue();
    }
}
