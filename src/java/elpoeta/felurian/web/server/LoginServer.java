/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elpoeta.felurian.web.server;

import elpoeta.felurian.dao.UsuarioDao;
import elpoeta.felurian.modelo.Usuario;
import elpoeta.felurian.util.GsonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "LoginServer", urlPatterns = {"/api/LoginServer"})
public class LoginServer extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	
            String texto = request.getReader().readLine();
            
            Usuario userParametro = GsonUtil.CONVERTIR.fromJson(texto, Usuario.class);
            System.out.println("PaR > "+userParametro);
            Usuario userBD = UsuarioDao.getInstance().buscarPorEmail(userParametro.getEmail()); 
            System.out.println("BD > "+userBD);
        
            if(userBD.getEmail().equals(userParametro.getEmail()) && BCrypt.checkpw(userParametro.getPassword(), userBD.getPassword()) && userBD.isActivo())
            {   
                request.getSession().setAttribute("user", userBD);
               System.out.println("...Server logIN ..." + request.getSession().getAttribute("user") );
               out.print(GsonUtil.CONVERTIR.toJson("ok"));
            
               
            }else{
                request.getSession().removeAttribute("user");
               System.out.println("...Server logOUT ..." + request.getSession().getAttribute("user") );
                out.print(GsonUtil.CONVERTIR.toJson( "error"));
                
            
            }

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
        
      
    }

}
