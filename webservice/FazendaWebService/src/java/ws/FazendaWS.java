/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import modelo.Usuario;
import org.jboss.logging.Param;

/**
 * REST Web Service
 *
 * @author alinecamargos
 */
@Path("fazenda")
public class FazendaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FazendaWS
     */
    
    public FazendaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.FazendaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/text")
    public String getJson() {
        return "meu primeiro WS RESTFULL";
    }
    
    @GET
    @Produces("application/json")
    @Path("Usuario/get/{login}")
    public String getUsuario(@PathParam("login") String login)
    {
        Usuario u = new Usuario();
        u.setLogin(login);
        
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
       
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(u);
    }
    @GET
    @Produces("application/json")
    @Path("Usuario/list")
    public String listUsuarios()
    {
        List<Usuario> lista;
        
        UsuarioDAO dao = new UsuarioDAO();
        lista = dao.listar();
        
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    @POST
    @Consumes({"application/json"})
    @Path("Usuario/inserir")
public boolean inserir(String content){
     Gson g = new Gson();
    Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();  
        return dao.inserir(u);
}
    
    /**
     * PUT method for updating or creating an instance of FazendaWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Path("Usuario/alterar")
    public void alterar(String content) {
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();  
        dao.atualizar(u);
    }
    
   @DELETE
   @Path("Usuario/excluir/{login}")
    public boolean excluir(@PathParam("login") String login)
    {    
        Usuario u = new Usuario();
        u.setLogin(login);
        
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
        return dao.excluir(u);
    }
}
