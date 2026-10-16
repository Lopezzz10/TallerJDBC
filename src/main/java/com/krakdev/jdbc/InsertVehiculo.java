package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertVehiculo {

	private static final Logger log = LoggerFactory.getLogger(InsertVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = """
					INSERT INTO vehiculos(placa, marca, modelo, anio, precio, color, disponible)
					VALUES(?,?,?,?,?,?,?)
					""";
			ps = con.prepareStatement(sql);
			ps.setString(1, "PBA-1234");
			ps.setString(2, "Toyota");
			ps.setString(3, "Corolla");
			ps.setInt(4, 2020);
			ps.setDouble(5, 18500.0);
			ps.setString(6, "Rojo");
			ps.setBoolean(7, true);

			int filas = ps.executeUpdate();
			log.info("Vehículo insertado. Filas insertadas: {}", filas);
			System.out.println("Vehículo insertado. Filas insertadas: " + filas);

		} catch (SQLException e) {
			log.error("Error SQL al insertar vehículo: {}", e.getMessage());
			System.out.println("Error SQL al insertar vehículo: " + e.getMessage());
		} catch (Exception e) {
			log.error("Error al insertar vehículo: {}", e.getMessage());
			System.out.println("Error al insertar vehículo: " + e.getMessage());
		} finally {
			Conexion.cerrar(null, ps, con);
		}
	}
}