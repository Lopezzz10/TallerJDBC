package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateVehiculo {

	private static final Logger log = LoggerFactory.getLogger(UpdateVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = """
					UPDATE vehiculos SET marca = ?, modelo = ?, anio = ?, precio = ?, color = ?, disponible = ?
					WHERE placa = ?
					""";
			ps = con.prepareStatement(sql);
			ps.setString(1, "Toyota");
			ps.setString(2, "Yaris");
			ps.setInt(3, 2022);
			ps.setDouble(4, 16000.0);
			ps.setString(5, "Azul");
			ps.setBoolean(6, false);
			ps.setString(7, "PBA-1234");

			int filas = ps.executeUpdate();
			if (filas > 0) {
				log.info("Vehículo actualizado. Filas afectadas: {}", filas);
				System.out.println("Vehículo actualizado. Filas afectadas: " + filas);
			} else {
				log.info("No se encontró el vehículo. Filas afectadas: {}", filas);
				System.out.println("No se encontró el vehículo. Filas afectadas: " + filas);
			}

		} catch (Exception e) {
			log.error("Error al actualizar vehículo: {}", e.getMessage());
			System.out.println("Error al actualizar vehículo: " + e.getMessage());
		} finally {
			Conexion.cerrar(con);
		}
	}
}