package de.gameofheroes.weaponapi.main.api;

import java.util.ArrayList;

public class RegisterLister {
	
	private static ArrayList<ShootListener> primaryShootListeners = new ArrayList<>();
	private static ArrayList<ShootListener> secondaryShootListeners = new ArrayList<>();
	
	public static ArrayList<ShootListener> getListeners() {
		 ArrayList<ShootListener> array = new ArrayList<>();
		 array.addAll(primaryShootListeners);
		 array.addAll(secondaryShootListeners);
		 return array;
	}

	
	
	
	
}
