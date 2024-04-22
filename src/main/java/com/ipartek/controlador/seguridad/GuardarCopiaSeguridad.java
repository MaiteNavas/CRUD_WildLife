package com.ipartek.controlador.seguridad;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/GuardarCopiaSeguridad")
public class GuardarCopiaSeguridad extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;

    public GuardarCopiaSeguridad() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper db= new DB_Helper();
		Connection con = db.conectar();
		
		List<Animal> lista_Animales= db.obtenerTodosAnimales(con);
		List<Especie> lista_Especies= db.obtenerTodasEspecies(con);
		
		db.desconectar(con);
		
		try {
			FileWriter archivo = new FileWriter("/Users/maitenavas/Desktop/wild_life/copia_seguridad_animales.csv");

			PrintWriter pw = new PrintWriter(archivo);
			
			for(Animal animal : lista_Animales){
				pw.println(animal);
			}
			archivo.close();
			
		} catch (IOException e) {	
			e.printStackTrace();	
		}
		
		try {
			FileWriter archivo = new FileWriter("/Users/maitenavas/Desktop/wild_life/copia_seguridad_especies.csv");

			PrintWriter pw = new PrintWriter(archivo);
			
			for(Especie especie : lista_Especies){
				pw.println(especie);
			}
			archivo.close();


		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		request.getRequestDispatcher(SEGURIDAD_JSP).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
