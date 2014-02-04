package net.coasterman10.Annihilation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class VaultHooks {
	public static boolean vault = false;

	private static VaultHooks inst;

	public static VaultHooks instance() {
		if (vault) {
			if (inst == null)
				inst = new VaultHooks();
			return inst;
		} else
			return null;
	}

	private VaultHooks() {

	}

	public static Permission permission;
	public static Chat chat;
	 
	public static Permission getPermissionManager() {
	    return permission;
	}
	
	public static Chat getChatManager() {
        return chat;
    }
	
	public boolean setupPermissions() {
		if (!vault)
			return false;
		
		RegisteredServiceProvider<Permission> permissionProvider = Bukkit
				.getServicesManager().getRegistration(
						net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			permission = permissionProvider.getProvider();
		return (permission != null);
	}
	
	public boolean setupChat()
    {
	    if (!vault)
            return false;
	    
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    public static String getGroup(String name) {
        if (!vault) return "";
        
        String group = VaultHooks.getPermissionManager().getPrimaryGroup(Bukkit.getPlayer(name));
        return ChatColor.translateAlternateColorCodes('&', 
                VaultHooks.getChatManager().getGroupPrefix(Bukkit.getPlayer(name).getWorld(), group) + ChatColor.RESET);
    }
}
