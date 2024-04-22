package com.ipartek.modelo;

public interface DAO_Constantes {
	
	String BD_DRIVER = "com.mysql.cj.jdbc.Driver";
	String BD = "db_wild_life";
	String CADENA_CONEXION = "jdbc:mysql://localhost:3306/" + BD;
	String USUARIO = "root";
	String PASS = "12345678";
	
	String TABLA_ANIMALES = "animales";
	String ANIMALES_ID = "id";
	String ANIMALES_NOMBRE = "nombre";
	String ANIMALES_PESO = "peso";
	String ANIMALES_EDAD = "edad";
	String ANIMALES_FK_ID_ESPECIE = "fk_id_especie";
	
	String TABLA_V_ANIMALES = "v_animales";
	String V_ANIMALES_ID = "id";
	String V_ANIMALES_NOMBRE = "nombre";
	String V_ANIMALES_PESO = "peso";
	String V_ANIMALES_EDAD = "edad";
	String V_ANIMALES_FK_ID_ESPECIE = "fk_id_especie";
	String V_ANIMALES_ESPECIE = "especie";
	
	String TABLA_ESPECIES = "especies";
	String ESPECIES_ID = "id";
	String ESPECIES_ESPECIE = "especie";
	
	String SP_OBTENER_TODOS_ANIMALES_VISTA = "call sp_obtener_todos_animales_vista()";
	String SP_OBTENER_TODOS_ANIMALES = "call sp_obtener_todos_animales()";
	String SP_OBTENER_TODAS_ESPECIES = "call sp_obtener_todas_especies()";
	String SP_INSERTAR_ANIMAL = "call sp_insertar_animal(?,?,?,?)";
	String SP_OBTENER_ANIMAL = "call sp_obtener_animal(?)";
	String SP_ACTUALIZAR_ANIMAL = "call sp_actualizar_animal(?,?,?,?,?)";
	String SP_ELIMINAR_ANIMAL = "call sp_eliminar_animal(?)";
	String SP_FILTRAR_ANIMALES = "call sp_filtrar_animales(?,?)";
	
	String BACK_BORRAR_TABLAS = "call BACK_borrar_tablas()";
	String BACK_INSERTAR_ESPECIE_CON_ID = "call BACK_insertar_especie_con_id(?,?)";
	String BACK_INSERTAR_ANIMAL_CON_ID = "call BACK_insertar_animal_con_id(?,?,?,?,?)";
	
	String INICIO_JSP = "inicio.jsp";
	String EDITAR_JSP = "editar.jsp";
	String SEGURIDAD_JSP = "seguridad.jsp";

	String ATR_V_ANIMALES = "atr_v_animales";
	String ATR_ESPECIES = "atr_especies";
	String ATR_ANIMAL = "atr_animal";
	String ATR_FILTRAR_ANIMALES = "atr_filtrar_animales";

}
