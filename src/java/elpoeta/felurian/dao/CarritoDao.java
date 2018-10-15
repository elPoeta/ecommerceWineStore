
package elpoeta.felurian.dao;

import elpoeta.felurian.conexion.Conexion;
import elpoeta.felurian.modelo.Carrito;
import elpoeta.felurian.modelo.CarritoItem;
import elpoeta.felurian.modelo.Producto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class CarritoDao {
    private static CarritoDao INSTANCE = null;
    private static final String SQL_CARRITO_SELECT_POR_USUARIO_ID = "SELECT * FROM carrito WHERE carrito.usuario_id = ? ORDER BY fecha_creacion DESC;"; 
     private static final String SQL_ITEM_SELECT_POR_CARRITO_ID = "SELECT * FROM carrito_item WHERE carrito_item.carrito_id = ?;";
     private final static String SQL_PRODUCTO_SELECT = "SELECT * FROM producto WHERE producto.id = ?;";
     
    private CarritoDao() throws ClassNotFoundException,
    IOException, SQLException{
    }
    
    public static CarritoDao getInstance() throws ClassNotFoundException,
    IOException, SQLException{
        if (INSTANCE == null) {
            INSTANCE = new CarritoDao();
        }
        return INSTANCE;
    } 
    
    public List<Carrito> buscarCarritoPorUsuario(Integer id) throws ClassNotFoundException,
    IOException, SQLException{
        ArrayList<Carrito> listaCarrito = new ArrayList();
        Connection conexion = null;
        PreparedStatement ptsmt = null;
        PreparedStatement ptsmtCartItem = null;
        PreparedStatement ptsmtProd = null;
        ResultSet rs = null;
        ResultSet rsCartItem =  null;
         ResultSet rsProd =  null;
        try{
        conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_CARRITO_SELECT_POR_USUARIO_ID);
                ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
                Carrito carrito = null;
               while(rs.next()){
                        try {
		        carrito = new Carrito();
		        carrito.setId(rs.getInt("id"));
		        carrito.setFechaCreacion(rs.getObject("fecha_creacion", LocalDate.class));
		        //carrito.setCantidadItems(rs.getInt("cantidad_items"));
                        
                       
                        Integer idCarrito = carrito.getId();
                       
                        ptsmtCartItem= conexion.prepareStatement(SQL_ITEM_SELECT_POR_CARRITO_ID);
		        ptsmtCartItem.setInt(1, idCarrito);
		        rsCartItem = ptsmtCartItem.executeQuery();    
                        CarritoItem cartItem = null;
                        while(rsCartItem.next()) {
		          try {
                               cartItem = new CarritoItem();
                               cartItem.setId(rsCartItem.getInt("id"));
                               cartItem.setCantidad(rsCartItem.getInt("cantidad"));
                               cartItem.setSubtotal(rsCartItem.getBigDecimal("sub_total"));
                               
                               Integer idProd = rsCartItem.getInt("producto_id");
                               ptsmtProd = conexion.prepareStatement(SQL_PRODUCTO_SELECT);
                               ptsmtProd.setInt(1, idProd);
                               rsProd= ptsmtProd.executeQuery();
                               Producto prod = null;
                              
                               if(rsProd.next()){
                                prod = new Producto();
                                prod.setId(rsProd.getInt("id"));
                                prod.setNombre(rsProd.getString("nombre"));
                                prod.setImagen(rsProd.getString("imagen"));
                               }
                               
                               cartItem.setProducto(prod);
                              
                                carrito.agregar(cartItem);
                               carrito.getCantidadItems();
                 
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                       
                       listaCarrito.add(carrito); 
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    
		}
                
        }finally {
             try {
                 ptsmt.close();
                 ptsmtCartItem.close();
                 ptsmtProd.close();
                 rs.close();
                 rsCartItem.close();
                 rsProd.close();
             } finally {
                 conexion.close();
             }
         }
        return listaCarrito;
}

}