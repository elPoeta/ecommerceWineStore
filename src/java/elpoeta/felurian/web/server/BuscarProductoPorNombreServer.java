/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "BuscarProductoPorNombreServer", urlPatterns = {"/api/BuscarProductoPorNombreServer"})
public class BuscarProductoPorNombreServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         try {
                     TreeMap parametro = GsonUtil.CONVERTIR.fromJson(request.getParameter("q"), TreeMap.class);
          
                          String texto = (String) parametro.get("letras");
                       
                        List<Producto> listado =  ProductoDao.getInstance().buscarPorNombre(texto);
	                String resultado = GsonUtil.CONVERTIR.toJson(listado); 
	                out.println("" + resultado);
                   
                    
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
