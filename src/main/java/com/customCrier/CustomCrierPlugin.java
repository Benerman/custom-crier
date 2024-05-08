package com.customCrier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.NPC;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameStateChanged;

import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
;

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

	@Inject
	private OverlayManager overlayManager;

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
//			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
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

			GoogleSheetAPI sheet = new GoogleSheetAPI();
			sheet.setApiKey(googleAPIKey);
			sheet.setSpreadsheetId(googleSheetID);
			try {
				sheet.getSheet("A1:B12");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// TODO look into how to get data from sheet
			//	Upon login, get data from specified sheet
			//		- If no sheet is specified, do nothing
			//		- If sheet is specified, get data from sheet
			// TODO look into how data is stored(API Key, Sheet ID)
			// TODO Look into how to discover if NPC ID in view
			// Benny NPC ID: 5216
//			public static final int TOWN_CRIER = 276;
//			public static final int TOWN_CRIER_277 = 277;
//			public static final int TOWN_CRIER_278 = 278;
//			public static final int TOWN_CRIER_279 = 279;
//			public static final int TOWN_CRIER_280 = 280;
		}
	}

	//	if NPC is in view, check if NPC is Benny
	@Subscribe
	public void onAnimationChanged(AnimationChanged animationChanged)
	{
		final NPC npc = (NPC) animationChanged.getActor();

		if (npc.getId() == 5216 && npc.getName() != null)
		{
			log.info("Benny the Newspaper guy is in view!");
		}
		if (npc.getId() == 276 && npc.getName() != null)
		{
			log.info("Varrock Town Crier is in view!");
			npc.setOverheadText("BODEGA BOIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}


	@Provides
	CustomCrierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CustomCrierConfig.class);
	}
}
