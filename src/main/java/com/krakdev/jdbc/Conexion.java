package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

	private static final Logger log = LoggerFactory.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/tallerjdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "2006"; 

	private Conexion() {
	}

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Conexión realizada con PostgreSQL");
			return con;
		} catch (SQLException e) {
			log.error("Error de conexión: {}", e.getMessage(), e);
			throw new RuntimeException("No se pudo conectar a PostgreSQL", e);
		}
	}

	public static void cerrar(Connection con) {
		if (con != null) {
			try {
				con.close();
				log.info("Conexión cerrada");
			} catch (SQLException e) {
				log.error("Error al cerrar la conexión: {}", e.getMessage(), e);
			}
		}
	}

	public static void cerrar(ResultSet rs, PreparedStatement ps, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el ResultSet: {}", e.getMessage(), e);
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el PreparedStatement: {}", e.getMessage(), e);
			}
		}
		cerrar(con);
	}
}