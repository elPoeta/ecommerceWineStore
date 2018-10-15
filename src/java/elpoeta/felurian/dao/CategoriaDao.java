
package elpoeta.felurian.dao;

import elpoeta.felurian.conexion.Conexion;
import elpoeta.felurian.modelo.Categoria;
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
public class CategoriaDao {
    
   private static CategoriaDao INSTANCE = null;
    private final static String SQL_CATEGORIAS_SELECT = "SELECT * FROM categoria;";
    private final static String SQL_CATEGORIA_SELECT = "SELECT * FROM categoria WHERE id = ?;";
    private final static String SQL_SUBCATEGORIA_FINDBY_IDCATEGORIA = "SELECT * FROM sub_categoria WHERE categoria_id = ?;";

    private CategoriaDao() throws ClassNotFoundException,
    IOException, SQLException {
}
    public static CategoriaDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new CategoriaDao();
		}
		return INSTANCE;
	}

    public Categoria buscarPorId(Integer id) throws ClassNotFoundException,
		IOException, SQLException {
        Categoria cat = null;
    
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_CATEGORIA_SELECT);
		ptsmt.setInt(1, id);
		rs = ptsmt.executeQuery();
		List<SubCategoria> subCatLista = null;
		if(rs.next()) {
		    try {
		        cat = new Categoria();
		        cat.setId(rs.getInt("id"));
		        cat.setNombre(rs.getString("nombre"));
		        cat.setActiva(rs.getBoolean("is_activa"));
                        Integer idSub = rs.getInt("id");
                        
                        try{
                            SubCategoria subCat = null;
                            ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_FINDBY_IDCATEGORIA);
		            ptsmtSub.setInt(1, idSub);
		            rsSub = ptsmtSub.executeQuery();
                              subCatLista = new ArrayList();
		while (rsSub.next()) {
		    try {
		        subCat = new SubCategoria();
		        subCat.setId(rsSub.getInt("id"));
		        subCat.setNombre(rsSub.getString("nombre"));
		        subCat.setActiva(rsSub.getBoolean("is_activa"));
                        subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                    
                    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
                    
		    subCatLista.add(subCat);
                   
		}
                        }catch(Exception ex){
                         ex.printStackTrace();
                        }
                         
                        cat.setSubCategorias(subCatLista);
                        
		        
		 
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
		return cat;

    }
   
    public List<Categoria> buscarTodos() throws ClassNotFoundException, IOException, SQLException {
        ArrayList<Categoria> lista = new ArrayList();
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
                 PreparedStatement ptsmtSub = null;
		ResultSet rsSub = null;
		try {
		conexion = Conexion.getInstance().getConnection();
		ptsmt = conexion.prepareStatement(SQL_CATEGORIAS_SELECT);
		rs = ptsmt.executeQuery();
		Categoria cat = null;
                List<SubCategoria> subCatLista = null;
		while (rs.next()) {
		    try {
		        cat = new Categoria();
		        cat.setId(rs.getInt("id"));
		        cat.setNombre(rs.getString("nombre"));
		        cat.setActiva(rs.getBoolean("is_activa"));
                       
                         Integer idSub = rs.getInt("id");
                        
                        try{
                            SubCategoria subCat = null;
                            ptsmtSub = conexion.prepareStatement(SQL_SUBCATEGORIA_FINDBY_IDCATEGORIA);
		            ptsmtSub.setInt(1, idSub);
		            rsSub = ptsmtSub.executeQuery();
                            subCatLista = new ArrayList();
		while (rsSub.next()) {
		    try {
		        subCat = new SubCategoria();
		        subCat.setId(rsSub.getInt("id"));
		        subCat.setNombre(rsSub.getString("nombre"));
		        subCat.setActiva(rsSub.getBoolean("is_activa"));
                        subCat.setIdCategoria(rsSub.getInt("categoria_id"));
                    
                    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    subCatLista.add(subCat);
		}
                        }catch(Exception ex){
                         ex.printStackTrace();
                        }
                        
                        cat.setSubCategorias(subCatLista);
                        lista.add(cat);
		 
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
		return lista;
    }

    
}
