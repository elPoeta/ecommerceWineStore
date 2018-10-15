
package elpoeta.felurian.dao;

import elpoeta.felurian.conexion.Conexion;
import elpoeta.felurian.modelo.Carrito;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 *
 * @author elpoeta
 */
public class FinalizarCompraDao {
    private static FinalizarCompraDao INSTANCE = null;
    //private final String SQL_INSERTAR_CARRITO = "INSERT INTO carrito (fecha_creacion, total, cantidad_items, usuario_id)values(?,?,?,?);";
    private final String SQL_INSERTAR_CARRITO = "INSERT INTO carrito (fecha_creacion, usuario_id)values(?,?);";
    private final String SQL_INSERTAR_CARRITO_ITEM = "INSERT INTO carrito_item (cantidad, sub_total, carrito_id, producto_id )values(?,?,?,?);";
    
      private FinalizarCompraDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static FinalizarCompraDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new FinalizarCompraDao();
		}
		return INSTANCE;
	}
    
        public void insertar(Carrito carrito) throws ClassNotFoundException, IOException, SQLException {
           // final String SQL_LAST_INSERT_ID = "SELECT SCOPE_IDENTITY();";
            //final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";
            final String SQL_LAST_INSERT_ID = "CALL IDENTITY();";
            Connection conexion = null;
      
          /* 
LocalDate now = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String formattedDate = now.format(formatter);
LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
    
    getObject(int, LocalDate.class)
*/
		   PreparedStatement ptsmt = null;
		   try {
		       conexion = Conexion.getInstance().getConnection();
		       conexion.setAutoCommit(false);
                       ptsmt = conexion.prepareStatement(SQL_INSERTAR_CARRITO);
		       
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = carrito.getFechaCreacion().format(formatter);
                        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
                       
                        ptsmt.setObject(1, parsedDate);
                        ptsmt.setInt(2, carrito.getUsuario().getId());
                        ptsmt.execute();
                      
                       //ptsmt = conexion.prepareStatement(SQL_LAST_INSERT_ID);
                       //ResultSet rs = ptsmt.executeQuery();
                       
                      ptsmt = conexion.prepareStatement(SQL_LAST_INSERT_ID);

                     ResultSet rs = ptsmt.executeQuery();

                       rs.next();
                       Integer idCarrito = rs.getInt(1);
                       rs.close();
                       
                       ptsmt = conexion.prepareStatement(SQL_INSERTAR_CARRITO_ITEM);
                       Iterator it =  carrito.getItems().keySet().iterator();
                     while(it.hasNext()){
                         Integer key = (Integer) it.next();
                       
                       ptsmt.setInt(1, carrito.getItems().get(key).getCantidad());
                       ptsmt.setBigDecimal(2, carrito.getItems().get(key).getSubtotal());
                       ptsmt.setInt(3, idCarrito);
                       ptsmt.setInt(4,carrito.getItems().get(key).getProducto().getId());
                       ptsmt.execute();
                     }   
                       conexion.commit();
                       
              } catch (Exception e) {
                      conexion.rollback();
                       System.out.println("Rollback " +e.getMessage());
              } finally {
		       try {
		           ptsmt.close();
		       } finally {
		           conexion.close();
		       }
		   }
    }
}
