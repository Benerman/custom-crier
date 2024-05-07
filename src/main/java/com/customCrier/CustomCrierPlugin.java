package com.customCrier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;


@Slf4j
@PluginDescriptor(
	name = "Custom Crier"
)
public class CustomCrierPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private CustomCrierConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Discord Crier started!");

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Discord Crier stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
			log.info("Attempting to retrieve Google Sheet data...");
			String googleSheetID = config.googleSheetID();
			String googleAPIKey = config.googleAPIKey();
			if (googleSheetID.isEmpty() || googleAPIKey.isEmpty())
			{
				log.info("Google Sheet ID or Google API Key is empty. Please fill in the config.");
			}
			else
			{
				log.info("Google Sheet ID: " + googleSheetID);
				log.info("Google API Key: " + googleAPIKey);
			}
			// TODO look into how to get data from sheet
			//	Upon login, get data from specified sheet
			//		- If no sheet is specified, do nothing
			//		- If sheet is specified, get data from sheet
			// TODO look into how data is stored(API Key, Sheet ID)
			// TODO Look into how to discover if NPC ID in view
			// Benny NPC ID: 5216
			// Varrock Town Crier NPC ID: 276
			// Fally Town Crier NPC ID: 278
			// Ardougne Town Crier NPC ID: 279
			// Seers Town Crier NPC ID: 280
		}
	}

	@Provides
	CustomCrierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CustomCrierConfig.class);
	}
}
