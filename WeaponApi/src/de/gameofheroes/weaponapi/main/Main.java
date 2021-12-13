package de.gameofheroes.weaponapi.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.gameofheroes.weaponapi.weapons.Weapon;
import de.gameofheroes.weaponapi.weapons.WeaponLoader;
import de.gameofheroes.weaponapi.weapons.types.LevelData;
import de.gameofheroes.weaponapi.weapons.types.ShootingWeapon;
import de.gameofheroes.weaponapi.weapons.types.WeaponType;

public class Main extends JavaPlugin {
	
	private static Main main;
	private WeaponLoader weaponLoader;
	private PluginManager pluginManager;
	
	@Override
	public void onEnable() {
		main = this;
		pluginManager = Bukkit.getPluginManager();
		loadWeapons();
		
		
		
	}
	
	

	@Override
	public void onDisable() {
		
		
	}
	
	private void loadWeapons() {
		Weapon[] weapons = new Weapon[3];
		LevelData[] levelDatas = new LevelData[7];
		for (int i = 0; i < levelDatas.length; i++) {
			levelDatas[i] = new LevelData(i+1, 30, 2, 0.2, 30);
		}
		weapons[0] = new ShootingWeapon("P90", 0, WeaponType.AR, levelDatas, Material.SPYGLASS);
		weapons[1] = new ShootingWeapon("P9", 1, WeaponType.AR, levelDatas, Material.SPYGLASS);
		weapons[2] = new ShootingWeapon("P9a", 0, WeaponType.AR, levelDatas, Material.SPYGLASS);
		weaponLoader = new WeaponLoader(weapons);
		pluginManager.registerEvents(weaponLoader, this);
	}
	
	public static Main getMain() {
		return main;
	}



	public WeaponLoader getWeaponLoader() {
		return weaponLoader;
	}

}
