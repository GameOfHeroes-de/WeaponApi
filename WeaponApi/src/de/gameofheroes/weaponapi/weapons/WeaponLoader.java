package de.gameofheroes.weaponapi.weapons;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.gameofheroes.weaponapi.main.Main;
import de.gameofheroes.weaponapi.weapons.types.ShootingWeapon;
import de.gameofheroes.weaponapi.weapons.types.WeaponType;
import net.md_5.bungee.api.chat.hover.content.Item;

public class WeaponLoader implements Listener {

	private static Main main = Main.getMain();
	private static Logger log = Main.getMain().getLogger();
	
	private HashMap<Player, Integer> repeatingTasks = new HashMap<>();
	private ArrayList<Weapon> weapons;
	
	
	public WeaponLoader(Weapon[] weapons) {
		this.weapons = new ArrayList<>();
		for(Weapon c : weapons) this.weapons.add(c);
	}
	
	public WeaponLoader(Weapon[] weapons, WeaponLoader weaponLoader) {
		this.weapons = new ArrayList<>();
		for(Weapon c : weapons) this.weapons.add(c);
		this.weapons.addAll(weaponLoader.getWeapons());
	}

	@EventHandler
	public void doShoot(PlayerInteractEvent event) {
		if(event.getAction() != Action.LEFT_CLICK_AIR) return;
		//log.info("1");
		if(!event.hasItem()) return;
		ItemStack item = event.getItem();
		//log.info("2");
		if(!item.getItemMeta().hasCustomModelData()) return;
		//log.info("3");
		Weapon weapon = getWeapon(item.getItemMeta().getCustomModelData());
		//log.info(weapon.getWeaponMaterial().toString());
		if(item.getType() != weapon.getWeaponMaterial()) return; 
		//log.info("4");
		
		
		
		if(weapon instanceof ShootingWeapon) {
			Player player = event.getPlayer();
			if(!repeatingTasks.containsKey(player)) repeatingTasks.put(player, null);
			
			if(repeatingTasks.get(player) != null) {
				Bukkit.getScheduler().cancelTask(repeatingTasks.get(player));
				repeatingTasks.replace(player, null);
			}
			else { 
				log.info("Enable Weapon");
				int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

					@Override
					public void run() {
						Shoot.newWeaponShoot(player, weapon, 0);

					}
				}, 0, 0);
				repeatingTasks.replace(player, i);
			}
			
			
		}
	}
	
	@EventHandler
	public void killProjectile(ProjectileHitEvent event) {
		for (Shoot c : Shoot.getShoots5sec()) {
			
		}
	}
	
	
	public Weapon getWeapon(int customModelData) {
		for(Weapon weapon : weapons) {
			log.info("GetWeapon for: " + customModelData + " with " + weapon.getID());
			if(weapon.getID() == customModelData/1000) //CustomModel-IDRange 
				return weapon;
			}
		return null;
		
	}
	
	
	public ArrayList<Weapon> getWeapons(WeaponType weaponType) {
		ArrayList<Weapon> weapons = new ArrayList<>();
		for(Weapon c : this.weapons) {
			if(c.getWeaponType() == weaponType) weapons.add(c);
		}
		return weapons;
	}
	
	
	
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	

	
	
	
	
	
}