package sg.edu.nus.iss.workshop27Redo.repository;

public interface Queries {
    
    public static final String SQL_INSERT_PURCHASE_ORDER = 
        "insert into po(name,email) values (?,?)";

    public static final String SQL_INSERT_LINE_ITEM = 
        "insert into line_item(ord_id,description,quantity, unit_price) values (?,?,?,?)";

    public static final String SQL_TOTAL_PURCHASE_ORDER =
        "select count(*) from po";
}
