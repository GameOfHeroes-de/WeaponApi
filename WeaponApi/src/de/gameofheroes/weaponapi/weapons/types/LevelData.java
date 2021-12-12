package de.gameofheroes.weaponapi.weapons.types;

public class LevelData {

	
	//these are static Universal Values for defined Weapons
	private int ammo, level;
	private double damage, spread;
	private long reloadTime;
	
	
	public LevelData(int level, int ammo, double damage, double spread, long reloadTime) {
		this.ammo = ammo;
		this.level = level;
		this.damage = damage;
		this.spread = spread;
		this.reloadTime = reloadTime;
	}


	public int getAmmo() {
		return ammo;
	}


	public int getLevel() {
		return level;
	}


	public double getDamage() {
		return damage;
	}


	public double getSpread() {
		return spread;
	}


	public long getReloadTime() {
		return reloadTime;
	}


	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public void setDamage(double damage) {
		this.damage = damage;
	}


	public void setSpread(double spread) {
		this.spread = spread;
	}


	public void setReloadTime(long reloadTime) {
		this.reloadTime = reloadTime;
	}
	
	

}
