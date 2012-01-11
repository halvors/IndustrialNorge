package no.industrialnorge.industrialnorge.listener;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.util.Vector;

public class VehicleListener extends org.bukkit.event.vehicle.VehicleListener {
//	private final halvors plugin;

	public VehicleListener(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	public void onVehicleUpdate(VehicleUpdateEvent event) {
		Vehicle vehicle = event.getVehicle();
		
		// Konstant hastighet for minecarts.
		if (vehicle instanceof Minecart || vehicle.getPassenger() != null) {
			Vector vel = vehicle.getVelocity();
		
			if (vel.getX() > 0) {
				vel.setX(0.6);
			}
		
			if (vel.getX() < 0) {
				vel.setX(-0.6);
			}
		
			if (vel.getZ() > 0) {
				vel.setZ(0.6);
			}
			
			if (vel.getZ() < 0) {
				vel.setZ(-0.6);
			}
		
			vehicle.setVelocity(vel);
		}
	}
}
