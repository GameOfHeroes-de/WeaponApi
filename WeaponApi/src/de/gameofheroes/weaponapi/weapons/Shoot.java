package de.gameofheroes.weaponapi.weapons;

import java.util.logging.Logger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import de.gameofheroes.weaponapi.main.Main;
import de.gameofheroes.weaponapi.main.api.RegisterLister;
import de.gameofheroes.weaponapi.main.api.ShootListener;
import de.gameofheroes.weaponapi.weapons.types.ShootingWeapon;
import net.minecraft.world.level.Explosion.Effect;


public class Shoot {
	
	private static Main main = Main.getMain();
	private static Logger log = Main.getMain().getLogger();
	
	private static int lastShootID = 0;
	private static ArrayList<Shoot> shoots5min = new ArrayList<>();
	private static ArrayList<Shoot> shoots5sec = new ArrayList<>() ;
	private static HashMap<Player, Long> lastPlayerShoot = new HashMap<>();
	
	public static void newWeaponShoot(Player shooter, Weapon weapon, int weaponLevel) {
		long l = System.currentTimeMillis();
		if(!lastPlayerShoot.containsKey(shooter)) {
			lastPlayerShoot.put(shooter, l);
			l = l - 1000000; 
		}
		
		//log.info("Shoot from: "+ shooter.getDisplayName() + " with: " + weapon.getName() + " shoottime-> " + l);
		Arrow projectile = shooter.launchProjectile(Arrow.class);
		projectile.setSilent(true);
		
		
		Shoot shoot = new Shoot(shooter, projectile, lastShootID++, weapon, weaponLevel);
		
		for(ShootListener shootListener : RegisterLister.getListeners()) {
			try {
				shootListener.shootEvent(shoot);
			} catch (Exception e) {
				log.warning(e.getMessage());
			}
		}
		
		
		if(!shoot.isCanceld) {
			ShootingWeapon shootingWeapon = (ShootingWeapon) weapon;
			if(shootingWeapon.getLevelData(weaponLevel).getReloadTime() <= (l-lastPlayerShoot.get(shooter)+10)/10) {
				shootingWeapon.prepareProjectile(projectile);
				lastPlayerShoot.replace(shooter, l);
				//Needs nms projectile.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1, true, false), false);
				projectile.setVelocity(projectile.getVelocity().multiply(10));
				projectile.setGravity(false);
				projectile.setGlowing(true);
				projectile.setTicksLived(1180);
			}	else {
				projectile.remove();
			}
		} else {
			projectile.remove();
		}
		
		
	}
	
	
	
	
	private Player shooter;
	private Projectile projectile; 
	private int shootID, weaponLevel; 
	private Weapon weapon; 
	private boolean isCanceld = false;
	
	
	private Shoot(Player shooter, Projectile projectile, int shootID, Weapon weapon, int weaponLevel) {
		this.shooter = shooter;
		this.projectile = projectile;
		this.shootID = shootID;
		this.weapon = weapon;
		this.weaponLevel = weaponLevel;
	}
	
	private void arrowTrace() {
		// WIP
	}
	

	public Player getShooter() {
		return shooter;
	}


	public Projectile getProjectile() {
		return projectile;
	}


	public int getShootID() {
		return shootID;
	}


	public int getWeaponLevel() {
		return weaponLevel;
	}


	public Weapon getWeapon() {
		return weapon;
	}


	public void setShooter(Player shooter) {
		this.shooter = shooter;
	}


	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}


	public void setShootID(int shootID) {
		this.shootID = shootID;
	}


	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}


	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean isCanceld() {
		return isCanceld;
	}

	public static ArrayList<Shoot> getShoots5sec() {
		return shoots5sec;
	}

	
	
}
