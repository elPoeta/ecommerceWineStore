/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elpoeta.felurian.web.server;

import elpoeta.felurian.dao.UsuarioDao;
import elpoeta.felurian.modelo.Usuario;
import elpoeta.felurian.util.GsonUtil;
import elpoeta.felurian.util.Validar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "RegistroServer", urlPatterns = {"/api/RegistroServer"})
public class RegistroServer extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
        try {
        	
            String texto = request.getReader().readLine();

            Usuario userParametro = GsonUtil.CONVERTIR.fromJson(texto, Usuario.class); 
            System.out.println(userParametro);
            if(validarUsuario(userParametro)){
            
                Usuario userBD = UsuarioDao.getInstance().buscarPorEmail(userParametro.getEmail());  
            System.out.println(userBD);
            if( userBD == null && userParametro.getPassword().equals(userParametro.getConfirmPassword()))
            {
                UsuarioDao.getInstance().insertar(userParametro);
                out.println(GsonUtil.CONVERTIR.toJson("OK"));
            }else{
                  System.out.println("Error");
                out.println(GsonUtil.CONVERTIR.toJson("error"));
            }
            }else{
                  System.out.println("Error");
                out.println(GsonUtil.CONVERTIR.toJson("error"));
            
            }
        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > "+ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("SQL > "+ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("EXcep > "+ex.getMessage());
        } finally {
            out.close();
        }
    }
    
  private boolean validarUsuario(Usuario u){
    if(u.getNombre() != "" && u.getNombre() !=null 
       && u.getApellido() != "" && u.getApellido() !=null
       && Validar.isValidEmail(u.getEmail()) 
       && u.getPassword() != "" && u.getPassword() != null 
       && u.getConfirmPassword() != "" && u.getConfirmPassword() !=null){
        return true;
    }
      
      return false;
}
}
