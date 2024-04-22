package com.ipartek.controlador.crud;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Especie;
import com.ipartek.modelo.dto.V_Animal;


@WebServlet("/BuscarAnimal")
public class BuscarAnimal extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
    public BuscarAnimal() {
        super();
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String texto= "";
		if(request.getParameter("texto")!=null || request.getParameter("texto") == "" ) {
			texto= request.getParameter("texto");
		}
		int fk_id_especie= 0;
		if(request.getParameter("fk_id_especie")!=null) {
			fk_id_especie= Integer.parseInt(request.getParameter("fk_id_especie"));
		}

		DB_Helper db=new DB_Helper();
		Connection con = db.conectar();

		List<V_Animal> listaV_Animales = db.filtrarAnimales(con, texto, fk_id_especie );

		List<Especie> lista_especies= db.obtenerTodasEspecies(con);

		
		db.desconectar(con);
		
		request.setAttribute(ATR_V_ANIMALES, listaV_Animales);
		request.setAttribute(ATR_ESPECIES, lista_especies);
		
		request.getRequestDispatcher(INICIO_JSP).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
