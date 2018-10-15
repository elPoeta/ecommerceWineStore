
package elpoeta.felurian.web.server;

import elpoeta.felurian.dao.CarritoDao;
import elpoeta.felurian.modelo.Carrito;
import elpoeta.felurian.modelo.Usuario;
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
@WebServlet(name = "MisCompras", urlPatterns = {"/api/privado/MisCompras"})
public class MisCompras extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         try {
                   
                     Usuario usuario = (Usuario) request.getSession().getAttribute("user");
                     List<Carrito> listado =  CarritoDao.getInstance().buscarCarritoPorUsuario(usuario.getId());
	             String resultado = GsonUtil.CONVERTIR.toJson(listado);
	             out.println("" + resultado);
          
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
