
package elpoeta.felurian.web.server;

import elpoeta.felurian.dao.ProductoDao;
import elpoeta.felurian.modelo.Producto;
import elpoeta.felurian.util.GsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "ProductoServer", urlPatterns = {"/api/ProductoServer"})
public class ProductoServer extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         try {
                     TreeMap parametro = GsonUtil.CONVERTIR.fromJson(request.getParameter("q"), TreeMap.class);
          
                      if(parametro.containsKey("categoria")){
                           Integer id = Integer.parseInt( String.valueOf( parametro.get("categoria")));
                            if(id == 0){
                        List<Producto> listado =  ProductoDao.getInstance().buscarTodos();
	                String resultado = GsonUtil.CONVERTIR.toJson(listado); 
	                out.println("" + resultado);
                         }else{
                              List<Producto> listado =  ProductoDao.getInstance().buscarPorCategoria(id);
	                     String resultado = GsonUtil.CONVERTIR.toJson(listado); 
	                      out.println("" + resultado);
                         }
                      }else 
                          if(parametro.containsKey("subCategoria")){
                               Integer id = Integer.parseInt( String.valueOf( parametro.get("subCategoria")));
                    
                                List<Producto> listado =  ProductoDao.getInstance().buscarPorSubCategoria(id);
                                String resultado = GsonUtil.CONVERTIR.toJson(listado); 
                                out.println("" + resultado);
                     
                          }     
                 }catch (NumberFormatException ex){
                     out.println("Verificar:" + ex.getMessage());
	         } catch (ClassNotFoundException ex) {
	             out.println("Verificar:" + ex.getMessage());
	         } catch (SQLException ex) {
	             out.println("Verificar:" + ex.getMessage());
	         } catch (Exception ex) {
	             out.println("Verificar:" + ex.getMessage());
	         } finally {
	             out.close();
	         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
