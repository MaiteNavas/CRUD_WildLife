package com.ipartek.controlador.seguridad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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


@WebServlet("/RestaurarCopiaSeguridad")
public class RestaurarCopiaSeguridad extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
    public RestaurarCopiaSeguridad() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper db=new DB_Helper();
		Connection con = db.conectar();
		
		db.eliminarTablas(con);
		
		try {
			File archivo = new File("/Users/maitenavas/Desktop/wild_life/copia_seguridad_especies.csv");
			FileReader fr =  new FileReader(archivo);
			try (BufferedReader br = new BufferedReader(fr)) {
				List<Especie> lista_Especies = new ArrayList<Especie>();
				String linea = "";
				
				while ((linea = br.readLine()) != null) {
					String[] cachos = linea.split(";");
					
					Especie especie = new Especie();
					especie.setId(Integer.parseInt(cachos[0]));
					especie.setEspecie(cachos[1]);
					
					lista_Especies.add(especie);
				}
					
				for(Especie especie:lista_Especies) {
					db.insertarEspecieConId(con, especie);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File archivo = new File("/Users/maitenavas/Desktop/wild_life/copia_seguridad_animales.csv");
			FileReader fr =  new FileReader(archivo);
			try (BufferedReader br = new BufferedReader(fr)) {
				List<Animal> lista_Animales = new ArrayList<Animal>();
				String linea = "";
				
				while ((linea = br.readLine()) != null) {
					String[] cachos = linea.split(";");
					
					Animal animal = new Animal();
					animal.setId(Integer.parseInt(cachos[0]));
					animal.setNombre(cachos[1]);
					animal.setPeso(Double.parseDouble(cachos[2]));
					animal.setEdad(Integer.parseInt(cachos[3]));
					animal.setFk_id_especie(Integer.parseInt(cachos[4]));
					
					lista_Animales.add(animal);
				}
					
				for(Animal animal:lista_Animales) {
					db.insertarAnimalConId(con, animal);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		db.desconectar(con);

		request.getRequestDispatcher(SEGURIDAD_JSP).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
