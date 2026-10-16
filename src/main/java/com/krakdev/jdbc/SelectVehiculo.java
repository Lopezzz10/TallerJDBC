package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectVehiculo {

	private static final Logger log = LoggerFactory.getLogger(SelectVehiculo.class);

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();
			String sql = """
					SELECT * FROM vehiculos
					""";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			int contador = 0;
			while (rs.next()) {
				String placa = rs.getString("placa");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				int anio = rs.getInt("anio");
				double precio = rs.getDouble("precio");
				String color = rs.getString("color");
				boolean disponible = rs.getBoolean("disponible");
				int kilometraje = rs.getInt("kilometraje");

				String info = "Vehículo: Placa: " + placa + " Marca: " + marca + " Modelo: " + modelo + " Año: " + anio
						+ " Precio: " + precio + " Color: " + color + " Disponible: " + disponible + " Kilometraje: "
						+ kilometraje;
				log.info(info);
				System.out.println(info);
				contador++;
			}
			log.info("Vehículos listados: {}", contador);
			System.out.println("Vehículos listados: " + contador);

		} catch (SQLException e) {
			log.error("Error SQL al listar vehículos: {}", e.getMessage());
			System.out.println("Error SQL al listar vehículos: " + e.getMessage());
		} catch (Exception e) {
			log.error("Error al listar vehículos: {}", e.getMessage());
			System.out.println("Error al listar vehículos: " + e.getMessage());
		} finally {
			Conexion.cerrar(rs, ps, con);
		}
	}
}