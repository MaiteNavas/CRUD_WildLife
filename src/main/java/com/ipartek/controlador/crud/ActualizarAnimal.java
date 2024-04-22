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
import com.ipartek.modelo.dto.Animal;
import com.ipartek.modelo.dto.Especie;
import com.ipartek.modelo.dto.V_Animal;


@WebServlet("/ActualizarAnimal")
public class ActualizarAnimal extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;

    public ActualizarAnimal() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id= 0;
		if(request.getParameter("id")!=null) {
			 id= Integer.parseInt(request.getParameter("id"));
		}
		
		String nombre="";
		if(request.getParameter("nombre")!=null) {
			 nombre= request.getParameter("nombre");
		}
		
		double peso= 0.0;
		if(request.getParameter("peso")!=null) {
			 peso= Double.parseDouble(request.getParameter("peso"));
		}
		
		int edad= 0;
		if(request.getParameter("edad")!=null) {
			 edad= Integer.parseInt(request.getParameter("edad"));
		}
		
		int fk_id_especie= 0;
		if(request.getParameter("fk_id_especie")!=null) {
			fk_id_especie= Integer.parseInt(request.getParameter("fk_id_especie"));
		}

		Animal animal= new Animal(id, nombre, peso, edad, fk_id_especie);

		DB_Helper db=new DB_Helper();
		Connection con = db.conectar();
	
		db.actualizarAnimal(con, animal);
		
		List<V_Animal> listaV_Animales= db.obtenerTodosAnimalesVista(con);
		List<Especie> lista_Especies= db.obtenerTodasEspecies(con);

		db.desconectar(con);

		request.setAttribute(ATR_V_ANIMALES, listaV_Animales);
		request.setAttribute(ATR_ESPECIES, lista_Especies);

		request.getRequestDispatcher(INICIO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
