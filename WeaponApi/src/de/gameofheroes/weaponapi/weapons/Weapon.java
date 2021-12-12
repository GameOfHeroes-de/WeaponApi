package de.gameofheroes.weaponapi.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.gameofheroes.weaponapi.main.Main;
import de.gameofheroes.weaponapi.weapons.types.WeaponType;

public abstract class Weapon {
	
	private static Main main = Main.getMain();
	private static Logger log = Main.getMain().getLogger();
	
	static private HashMap<String, Integer> universalWeaponMap = new HashMap<>();
	
	private String name;
	private int ID;
	private WeaponType weaponType;
	private Material weaponMaterial;
	
	public Weapon(String name, int ID, WeaponType weaponType, Material weaponMaterial) {
		//Stupid stuff xD	
		setNameIDData(name, ID);
		setWeaponMaterial(weaponMaterial);
		setWeaponType(weaponType);
		
	}
	
	//abstract Shoot shoot(Player player);
	

	protected boolean setNameIDData(String name, int ID) {
		if(universalWeaponMap.containsValue(this.ID) && universalWeaponMap.containsKey(this.name)) {
			universalWeaponMap.remove(this.name, this.ID);
		}
		
		boolean b = false;
		boolean fine = true;
		int i = 404;
		while (!b) { 
			if(!(universalWeaponMap.containsValue(ID)||universalWeaponMap.containsKey(name))) {
				b = true;
			} else {
				log.warning("Illegal WeaponConfiguration -> " + name + " " + ID);
				name = i + name;
				ID = Integer.valueOf(i+ "" + ID);
				fine = false;
			}
		}
		universalWeaponMap.put(name, ID);
		setID(ID);
		setName(name);
		return fine;
	}
	

	public int getID() {
		return ID;
	}
	
	private void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public WeaponType getWeaponType() {
		return weaponType;
	}
	
	protected void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public Material getWeaponMaterial() {
		return weaponMaterial;
	}

	protected void setWeaponMaterial(Material weaponMaterial) {
		this.weaponMaterial = weaponMaterial;
	}

}
