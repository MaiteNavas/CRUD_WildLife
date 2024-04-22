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


@WebServlet("/EditarAnimal")
public class EditarAnimal extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
       
    public EditarAnimal() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id= 0;
		if(request.getParameter("id")!=null) {
			id= Integer.parseInt(request.getParameter("id"));
		}

		DB_Helper db=new DB_Helper();
		Connection con = db.conectar();

		Animal animal = db.obtenerAnimal(con, id);
		List<Especie> lista_especies= db.obtenerTodasEspecies(con);

		
		db.desconectar(con);
		
		request.setAttribute(ATR_ANIMAL, animal);
		request.setAttribute(ATR_ESPECIES, lista_especies);
		
		request.getRequestDispatcher(EDITAR_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
