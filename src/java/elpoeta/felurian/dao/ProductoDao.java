
package elpoeta.felurian.dao;

import elpoeta.felurian.conexion.Conexion;
import elpoeta.felurian.modelo.Producto;
import elpoeta.felurian.modelo.SubCategoria;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class ProductoDao {
       
   private static ProductoDao INSTANCE = null;
    private final static String SQL_PRODUCTOS_SELECT = "SELECT * FROM producto;";
    private final static String SQL_PRODUCTO_SELECT = "SELECT * FROM producto WHERE id = ?;";
    private final static String SQL_PRODUCTOS_SELECT_BY_CATEGORIA = "SELECT producto.id, producto.nombre, producto.precio, producto.sub_categoria_id,"
                                                                  + "producto.imagen, producto.is_disponible, producto.stock "
                                                                  + "FROM producto INNER JOIN sub_categoria on sub_categoria.id = producto.sub_categoria_id "
                                                                  + "INNER JOIN categoria ON sub_categoria.categoria_id = categoria.id " 
                                                                  +"WHERE categoria.id = ?;";    
    private final static String SQL_PRODUCTOS_SELECT_BY_SUBCATEGORIA = "SELECT * from producto WHERE producto.sub_categoria_id = ?;";   
    private final static String SQL_SUBCATEGORIA_SELECT = "SELECT * FROM sub_categoria WHERE id = ?;";
    private final static String SQL_BUSCAR_PRODUCTOS_POR_NOMBRE_LIKE = "SELECT producto.id, producto.nombre, producto.precio, producto.sub_categoria_id,"
                                                                  + "producto.imagen, producto.is_disponible, producto.stock "
                                                                  +"FROM producto WHERE LOWER(nombre) LIKE LOWER(?)";
    
    private ProductoDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static ProductoDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new ProductoDao();
		}
		return INSTANCE;
	}
    
    public Producto buscarPorId(Integer id) throws ClassNotFoundException, IOException, SQLException {
          Producto prod = null;
          SubCategoria subCat = null;
		Connection conexion = null;
                PreparedStatement ptsmt = null;
		ResultSet rs = null;
		PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_PRODUCTO_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
	
		if(rs.next()) {
		    try {
		        prod = new Producto();
		        prod.setId(rs.getInt("id"));
		        prod.setNombre(rs.getString("nombre"));
		        prod.setPrecio(rs.getBigDecimal("precio"));
                        prod.setDescripcion(rs.getString("descripcion"));
                        prod.setStock(rs.getInt("stock"));
                        prod.setImagen(rs.getString("imagen"));
		        prod.setDisponible(rs.getBoolean("is_disponible"));
                        Integer idSub = rs.getInt("sub_categoria_id");
	
                        ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_SELECT);
		        ptsmtSub.setInt(1, idSub);
		        rsSub = ptsmtSub.executeQuery();    
                        if(rsSub.next()) {
		          try {
                                 subCat = new SubCategoria();
		                 subCat.setId(rsSub.getInt("id"));
		                 subCat.setNombre(rsSub.getString("nombre"));
		                 subCat.setActiva(rsSub.getBoolean("is_activa"));
                                 subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                               
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                          prod.setSubCategoria(subCat);
                    
                        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return prod;

    }

    public List<Producto> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
         ArrayList<Producto> lista = new ArrayList();
         SubCategoria subCat = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_PRODUCTOS_SELECT);
		rs = ptsmt.executeQuery();
		Producto prod = null;
               
		while (rs.next()) {
		    try {
		        prod = new Producto();
		        prod.setId(rs.getInt("id"));
		        prod.setNombre(rs.getString("nombre"));
		        prod.setPrecio(rs.getBigDecimal("precio"));
                        prod.setDescripcion(rs.getString("descripcion"));
                        prod.setStock(rs.getInt("stock"));
                        prod.setImagen(rs.getString("imagen"));
		        prod.setDisponible(rs.getBoolean("is_disponible"));
                        Integer idSub = rs.getInt("sub_categoria_id");
                        
                        ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_SELECT);
		        ptsmtSub.setInt(1, idSub);
		        rsSub = ptsmtSub.executeQuery();    
                        if(rsSub.next()) {
		          try {
                                 subCat = new SubCategoria();
		                 subCat.setId(rsSub.getInt("id"));
		                 subCat.setNombre(rsSub.getString("nombre"));
		                 subCat.setActiva(rsSub.getBoolean("is_activa"));
                                 subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                               
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                          prod.setSubCategoria(subCat);
		
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    lista.add(prod);
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return lista;
    }
    
    public List<Producto> buscarPorCategoria(Integer id) throws ClassNotFoundException, IOException, SQLException {
         ArrayList<Producto> lista = new ArrayList();
          SubCategoria subCat = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
                try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_PRODUCTOS_SELECT_BY_CATEGORIA);
                    
                ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		Producto prod = null;
               
		while (rs.next()) {
		    try {
		        prod = new Producto();
		        prod.setId(rs.getInt("id"));
		        prod.setNombre(rs.getString("nombre"));
		        prod.setPrecio(rs.getBigDecimal("precio"));
                        //prod.setDescripcion(rs.getString("descripcion"));
                        prod.setStock(rs.getInt("stock"));
                        prod.setImagen(rs.getString("imagen"));
		        prod.setDisponible(rs.getBoolean("is_disponible"));
		        
                        Integer idSub = rs.getInt("sub_categoria_id");
                       
                        ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_SELECT);
		        ptsmtSub.setInt(1, idSub);
		        rsSub = ptsmtSub.executeQuery();    
                        if(rsSub.next()) {
		          try {
                                 subCat = new SubCategoria();
		                 subCat.setId(rsSub.getInt("id"));
		                 subCat.setNombre(rsSub.getString("nombre"));
		                 subCat.setActiva(rsSub.getBoolean("is_activa"));
                                 subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                               
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                          prod.setSubCategoria(subCat);


                        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    lista.add(prod);
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return lista;
    }
    
    public List<Producto> buscarPorSubCategoria(Integer id) throws ClassNotFoundException, IOException, SQLException {
         ArrayList<Producto> lista = new ArrayList();
          SubCategoria subCat = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
                try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_PRODUCTOS_SELECT_BY_SUBCATEGORIA);
                    
                ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		Producto prod = null;
               
		while (rs.next()) {
		    try {
		        prod = new Producto();
		        prod.setId(rs.getInt("id"));
		        prod.setNombre(rs.getString("nombre"));
		        prod.setPrecio(rs.getBigDecimal("precio"));
                        //prod.setDescripcion(rs.getString("descripcion"));
                        prod.setStock(rs.getInt("stock"));
                        prod.setImagen(rs.getString("imagen"));
		        prod.setDisponible(rs.getBoolean("is_disponible"));
		         Integer idSub = rs.getInt("sub_categoria_id");
         
                        ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_SELECT);
		        ptsmtSub.setInt(1, idSub);
		        rsSub = ptsmtSub.executeQuery();    
                        if(rsSub.next()) {
		          try {
                                 subCat = new SubCategoria();
		                 subCat.setId(rsSub.getInt("id"));
		                 subCat.setNombre(rsSub.getString("nombre"));
		                 subCat.setActiva(rsSub.getBoolean("is_activa"));
                                 subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                               
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                          prod.setSubCategoria(subCat);
                        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    lista.add(prod);
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return lista;
    }
    
    public List<Producto> buscarPorNombre(String nombre) throws ClassNotFoundException, IOException, SQLException{
      ;
         ArrayList<Producto> lista = new ArrayList();
          SubCategoria subCat = null;
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
                try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_BUSCAR_PRODUCTOS_POR_NOMBRE_LIKE);
        
                ptsmt.setString(1, "%" + nombre + "%");
		rs = ptsmt.executeQuery();
		Producto prod = null;
                
		while (rs.next()) {
                  
		    try {
		        prod = new Producto();
		        prod.setId(rs.getInt("id"));
		        prod.setNombre(rs.getString("nombre"));
		        prod.setPrecio(rs.getBigDecimal("precio"));
                        //prod.setDescripcion(rs.getString("descripcion"));
                        prod.setStock(rs.getInt("stock"));
                        prod.setImagen(rs.getString("imagen"));
		        prod.setDisponible(rs.getBoolean("is_disponible"));
		        
                        Integer idSub = rs.getInt("sub_categoria_id");
                       
                        ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_SELECT);
		        ptsmtSub.setInt(1, idSub);
		        rsSub = ptsmtSub.executeQuery();    
                        if(rsSub.next()) {
		      
                            try {
                                 subCat = new SubCategoria();
		                 subCat.setId(rsSub.getInt("id"));
		                 subCat.setNombre(rsSub.getString("nombre"));
		                 subCat.setActiva(rsSub.getBoolean("is_activa"));
                                 subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                               
                          } catch (Exception ex) {
		        ex.printStackTrace();}
                        }
                          prod.setSubCategoria(subCat);


                        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    lista.add(prod);
                  
		}
		} finally {
		try {
		    rs.close();
		} finally {
		    try {
		        ptsmt.close();
		    } finally {
		        conexion.close();
		    }
		}
		}
		return lista;
    }
}
