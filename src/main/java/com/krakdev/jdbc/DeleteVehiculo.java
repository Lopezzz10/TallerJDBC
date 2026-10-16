package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteVehiculo {

	private static final Logger log = LoggerFactory.getLogger(DeleteVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = Conexion.getConnection();
			String sql = """
					DELETE FROM vehiculos WHERE placa = ?
					""";
			ps = con.prepareStatement(sql);
			ps.setString(1, "PBA-1234");

			int filas = ps.executeUpdate();
			if (filas > 0) {
				log.info("Vehículo eliminado. Filas eliminadas: {}", filas);
				System.out.println("Vehículo eliminado. Filas eliminadas: " + filas);
			} else {
				log.info("No se encontró el vehículo. Filas eliminadas: {}", filas);
				System.out.println("No se encontró el vehículo. Filas eliminadas: " + filas);
			}

		} catch (SQLException e) {
			log.error("Error SQL al eliminar vehículo: {}", e.getMessage());
			System.out.println("Error SQL al eliminar vehículo: " + e.getMessage());
		} catch (Exception e) {
			log.error("Error al eliminar vehículo: {}", e.getMessage());
			System.out.println("Error al eliminar vehículo: " + e.getMessage());
		} finally {
			Conexion.cerrar(null, ps, con);
		}
	}
}