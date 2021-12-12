package de.gameofheroes.weaponapi.weapons.types;

import org.bukkit.Material;
import org.bukkit.entity.Projectile;

import de.gameofheroes.weaponapi.weapons.Weapon;

public class ShootingWeapon extends Weapon {

	private LevelData[] levelDatas;
	
	
	
	public ShootingWeapon(String name, int ID, WeaponType weaponType, LevelData[] levelDatas, Material material) {
		super(name, ID, weaponType, material);
		this.levelDatas = levelDatas;

	}


	public LevelData[] getLevelDatas() {
		return levelDatas;
	}


	public void setLevelDatas(LevelData[] levelDatas) {
		this.levelDatas = levelDatas;
	}


	public void prepareProjectile(Projectile projectile) {
		
	}

	
	
}
