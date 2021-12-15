package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//Quitar carpeta web_inf si no carga el navegador 

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        
        //Recuperamos la sesion
        HttpSession sesion = request.getSession();
        
        //Recuperamos la lista de los articulos que previamente haya creado el cliente
        
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        
        if (articulos == null){
            //No hemos agregado ningun articulo
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        
        String articuloNuevo = request.getParameter("articulo");
        
        //Vamos a comprobar el valor de nuestro articulo y lo agregamos
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
            
        }
        response.setContentType("text/html;charset=UTF-8");
        //Mostramos nuestro carrito de compra en cada solicitud
        PrintWriter out = response.getWriter();
        out.print("<h1>Lista de la compra:</h1>");
        for (String articulo : articulos) {
            out.print("<li>"+articulo+"</li>");
        }
        out.print("<br>");
        out.print("<a href='/CarritoCompra'>Inicio</a>");
        out.close();
    }
}
