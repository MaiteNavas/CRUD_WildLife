package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.modelo.dto.Animal;
import com.ipartek.modelo.dto.Especie;
import com.ipartek.modelo.dto.V_Animal;


public class DB_Helper implements DAO_Constantes{

	public Connection conectar() {
		
		Connection con = null;
		
		try {
			Class.forName(BD_DRIVER);
			con=DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASS);
			System.out.println("BASE DE DATOS CONECTADA");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: DRIVER NO ENCONTRADO");
			e.printStackTrace();
			
		} catch (SQLException e) {
			 System.out.println("ERROR: ERROR EN EL SQL: posiblemente usuario, password, nombre BD");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void desconectar(Connection con) {
		
		try {
			con.close();
			System.out.println("BASE DE DATOS DESCONECTADA");
			
		} catch (SQLException e) {
			System.out.println("ERROR:NO SE PUDO DESCONECTAR");
			e.printStackTrace();
		}
	}
	
	public List<V_Animal> obtenerTodosAnimalesVista(Connection con) {
			
		List<V_Animal> listaV_Animales = new ArrayList<V_Animal>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODOS_ANIMALES_VISTA);
			
			boolean tieneSelect = cStmt.execute();
			
			if(tieneSelect) {
				
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()){
					
					V_Animal vAnimal = new V_Animal();
					
					vAnimal.setId(rs.getInt(V_ANIMALES_ID));
					vAnimal.setNombre(rs.getString(V_ANIMALES_NOMBRE));
					vAnimal.setPeso(rs.getDouble(V_ANIMALES_PESO));
					vAnimal.setEdad(rs.getInt(V_ANIMALES_EDAD));
					vAnimal.setFk_id_especie(rs.getInt(V_ANIMALES_FK_ID_ESPECIE));
					vAnimal.setEspecie(rs.getString(V_ANIMALES_ESPECIE));
					
					listaV_Animales.add(vAnimal);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaV_Animales;
	}
	
	public List<Animal> obtenerTodosAnimales(Connection con) {
		
		List<Animal> lista_Animales = new ArrayList<Animal>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODOS_ANIMALES);
			
			boolean tieneSelect = cStmt.execute();
			
			if(tieneSelect) {
				
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()){
					
					Animal Animal = new Animal();
					
					Animal.setId(rs.getInt(ANIMALES_ID));
					Animal.setNombre(rs.getString(ANIMALES_NOMBRE));
					Animal.setPeso(rs.getDouble(ANIMALES_PESO));
					Animal.setEdad(rs.getInt(ANIMALES_EDAD));
					Animal.setFk_id_especie(rs.getInt(ANIMALES_FK_ID_ESPECIE));

					
					lista_Animales.add(Animal);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista_Animales;
	}
	
	public List<Especie> obtenerTodasEspecies(Connection con) {
		
		List<Especie> lista_Especies = new ArrayList<Especie>();
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODAS_ESPECIES);
			
			boolean tieneSelect = cStmt.execute();
			
			if(tieneSelect) {
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()){
					
					Especie especie = new Especie();
					
					especie.setId(rs.getInt(ESPECIES_ID));
					especie.setEspecie(rs.getString(ESPECIES_ESPECIE));
					
					lista_Especies.add(especie);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista_Especies;
		
	}
	
	public void insertarAnimal(Connection con, Animal animal) {
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_ANIMAL);
			
			cStmt.setString(1, animal.getNombre());
			cStmt.setDouble(2, animal.getPeso());
			cStmt.setInt(3, animal.getEdad());
			cStmt.setInt(4, animal.getFk_id_especie());
			
			cStmt.execute();
			System.out.println("Se ha insertado el animal");

		} catch (SQLException e) {
			System.out.println("No se pudo insertar el animal");
			e.printStackTrace();
		}
	}
	
	public Animal obtenerAnimal(Connection con, int id) {
		
		try {
			Animal animal = new Animal();
			
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_ANIMAL);
			cStmt.setInt(1,id);
			boolean tieneSelect = cStmt.execute();
			
			if(tieneSelect) {
				
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()){
					animal.setId(rs.getInt(ANIMALES_ID));
					animal.setNombre(rs.getString(ANIMALES_NOMBRE));
					animal.setPeso(rs.getDouble(ANIMALES_PESO));
					animal.setEdad(rs.getInt(ANIMALES_EDAD));
					animal.setFk_id_especie(rs.getInt(ANIMALES_FK_ID_ESPECIE));
				}
			}
			return animal;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			return new Animal();
		}
	}
	
	public void actualizarAnimal(Connection con, Animal animal) {
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_ACTUALIZAR_ANIMAL);
			
			cStmt.setInt(1, animal.getId());
			cStmt.setString(2, animal.getNombre());
			cStmt.setDouble(3, animal.getPeso());
			cStmt.setInt(4, animal.getEdad());
			cStmt.setInt(5, animal.getFk_id_especie());
			
			cStmt.execute();
			System.out.println("Se ha actualizado el animal");

		} catch (SQLException e) {
			System.out.println("No se pudo actualizar el animal");
			e.printStackTrace();
		}
	}
	
	public void eliminarAnimal(Connection con, int id) {
		
		try {
		
			CallableStatement cStmt = con.prepareCall(SP_ELIMINAR_ANIMAL);
			cStmt.setInt(1,id);
			cStmt.execute();
			
			System.out.println("Se ha eliminado el animal");

		} catch (SQLException e) {
			System.out.println("No se ha podido eliminar el animal");
			e.printStackTrace();
		
		}
	}
	
	public List<V_Animal> filtrarAnimales(Connection con, String texto, int fk_id_especie) {
		
		List<V_Animal> listaV_Animales = new ArrayList<V_Animal>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_FILTRAR_ANIMALES);
			cStmt.setString(1,texto);
			cStmt.setInt(2, fk_id_especie);
			boolean tieneSelect = cStmt.execute();
			
			if(tieneSelect) {

				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()){
					
					V_Animal vAnimal = new V_Animal();
					
					vAnimal.setId(rs.getInt(V_ANIMALES_ID));
					vAnimal.setNombre(rs.getString(V_ANIMALES_NOMBRE));
					vAnimal.setPeso(rs.getDouble(V_ANIMALES_PESO));
					vAnimal.setEdad(rs.getInt(V_ANIMALES_EDAD));
					vAnimal.setFk_id_especie(rs.getInt(V_ANIMALES_FK_ID_ESPECIE));
					vAnimal.setEspecie(rs.getString(V_ANIMALES_ESPECIE));
					
					listaV_Animales.add(vAnimal);
				}
			}
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaV_Animales;
	}
	
	public void eliminarTablas(Connection con) {
		
		try {
		
			CallableStatement cStmt = con.prepareCall(BACK_BORRAR_TABLAS);

			cStmt.execute();
			
			System.out.println("Se han eliminado las tablas");

		} catch (SQLException e) {
			System.out.println("No se han podido eliminar las tablas");
			e.printStackTrace();
		
		}
	}
	
	public void insertarEspecieConId(Connection con, Especie especie){
		
		try {
			CallableStatement cStmt = con.prepareCall(BACK_INSERTAR_ESPECIE_CON_ID);
			
			cStmt.setInt(1, especie.getId());
			cStmt.setString(2, especie.getEspecie());
			
			cStmt.execute();
			System.out.println("Se ha restaurado la especie");

		} catch (SQLException e) {
			System.out.println("No se pudo restaurar la especie");
			e.printStackTrace();
		}
	}
	
	public void insertarAnimalConId(Connection con, Animal animal){
		
		try {
			CallableStatement cStmt = con.prepareCall(BACK_INSERTAR_ANIMAL_CON_ID);
			
			cStmt.setInt(1, animal.getId());
			cStmt.setString(2, animal.getNombre());
			cStmt.setDouble(3, animal.getPeso());
			cStmt.setInt(4, animal.getEdad());
			cStmt.setInt(5, animal.getFk_id_especie());
			
			cStmt.execute();
			System.out.println("Se ha restaurado el animal");

		} catch (SQLException e) {
			System.out.println("No se pudo restaurar el animal");
			e.printStackTrace();
		}
	}
	
}
