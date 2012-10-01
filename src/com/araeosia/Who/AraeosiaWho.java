package com.araeosia.Who;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import query.MCQuery;
import query.QueryResponse;

public class AraeosiaWho extends JavaPlugin {

	static final Logger log = Logger.getLogger("Minecraft");
	public boolean debug=false;
	
	
	
	@Override
	public void onEnable(){
		loadConfiguration();
		this.debug("log", "[AraeosiaWho] Debug mode enabled!");
	
	}

	@Override
	public void onDisable(){
		log.info("[AraeosiaWho] Good night.");
	}
	@Override
	public boolean onCommand(CommandSender sender,  Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("WHO")){
			MCQuery mcquery = new MCQuery("192.168.5.106", 25565);
			QueryResponse response = mcquery.fullStat(); 
			ArrayList<String> AraeosiaRPG = response.getPlayerList();
		}
		return false;	
	}
	public void loadConfiguration(){
		boolean configIsCurrentVersion = getConfig().getDouble("AraeosiaWho.technical.version")==0.1;
		if(!configIsCurrentVersion){
			getConfig().set("AraeosiaWho.technical.debug", false);
			getConfig().set("AraeosiaWho.technical.version", 0.1);
			saveConfig();
		}
		debug = getConfig().getBoolean("AraeosiaWho.technical.debug");
	}
	
	private void debug(String channel, String msg) {
		if(debug){
			if(channel.equals("log")){
				log.info( "[AraeosiaWho]:" + msg);
			}
		}	
	}
}
