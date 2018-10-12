/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elpoeta.felurian.web.server;

import elpoeta.felurian.modelo.Carrito;
import elpoeta.felurian.modelo.Usuario;
import elpoeta.felurian.util.GsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "FinalizarCompraServer", urlPatterns = {"/api/privado/FinalizarCompraServer"})
public class FinalizarCompraServer extends HttpServlet {
     private Carrito carrito;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //carrito = (Carrito) request.getSession().getAttribute("carro");
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
         if( usuario != null ){
            
            response.getWriter().print(GsonUtil.CONVERTIR.toJson(usuario));
        }else{
            response.getWriter().print(GsonUtil.CONVERTIR.toJson("error"));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             request.getSession().setAttribute("carro",null);
    }
}
