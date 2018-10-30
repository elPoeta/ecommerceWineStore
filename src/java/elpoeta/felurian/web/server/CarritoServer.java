/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elpoeta.felurian.web.server;

import elpoeta.felurian.dao.ProductoDao;
import elpoeta.felurian.modelo.Carrito;
import elpoeta.felurian.modelo.CarritoItem;
import elpoeta.felurian.modelo.Producto;
import elpoeta.felurian.modelo.Usuario;
import elpoeta.felurian.util.GsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "CarritoServer", urlPatterns = {"/api/CarritoServer"})
public class CarritoServer extends HttpServlet {
     private Carrito carrito;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        carrito = (Carrito) request.getSession().getAttribute("carro");
        if( carrito == null ){
            carrito = new Carrito();
            request.getSession().setAttribute("carro", carrito);
        }
          response.getWriter().print(GsonUtil.CONVERTIR.toJson(carrito));
    }
     
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String texto = request.getReader().readLine();
        
        CarritoItem productoComprado = GsonUtil.CONVERTIR.fromJson(texto, CarritoItem.class);
        try {
            if(productoComprado.getCantidad()<=0){
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
            }
            Producto productoDB = ProductoDao.getInstance().buscarPorId(productoComprado.getProducto().getId());
             carrito = (Carrito) request.getSession().getAttribute("carro");
            if(productoComprado.getCantidad() <= productoDB.getStock())
        { 
         productoComprado.setProducto(productoDB); 
         carrito.agregar( productoComprado );
         carrito.getTotal();
         carrito.getCantidadItems();
        }
         Usuario usuario = (Usuario) request.getSession().getAttribute("user");
         carrito.setUsuario(usuario);
         response.getWriter().print(GsonUtil.CONVERTIR.toJson(carrito));
        
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarritoServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarritoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        TreeMap parametro = GsonUtil.CONVERTIR.fromJson(req.getParameter("q"), TreeMap.class);
        
        Integer id = Integer.parseInt( String.valueOf( parametro.get("id")));
      
        carrito = (Carrito) req.getSession().getAttribute("carro");
     
        carrito.quitar( id );
        carrito.getCantidadItems();
        carrito.getTotal();
      
        resp.getWriter().print(GsonUtil.CONVERTIR.toJson(carrito));   

    }
    
}
