package com.customCrier;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("customCrier")
public interface CustomCrierConfig extends Config
{
	int SHEET_ROW_MIN = 1;
	int SHEET_ROW_MAX = 15;

	@ConfigSection(
			name = "Available NPCs",
			description = "All available NPCs to broadcast messages",
			position = 0
	)
	String npcSection = "Available NPCs";

	@ConfigSection(
			name = "Custom Crier",
			description = "Configuration for the Custom Crier plugin",
			position = 1
	)
	String apiSection = "Google Sheets Settings";

	@ConfigSection(
			name = "Style",
			description = "Customize the font's style",
			position = 2
	)
	String styleSection = "Stylize the Crier's messages";

	@ConfigSection(
			name = "Preview of Messages",
			description = "View the messages that will be broadcasted by the NPCs",
			position = 3
	)
	String messageSection = "Preview Messages";



	@ConfigItem(
		position = 1,
		keyName = "googleSheetID",
		name = "Google Sheet ID",
		description = "Google Sheet ID of the public sheet to read from",
		section = apiSection
	)
	default String googleSheetID() {	return "";	}

	@ConfigItem(
		position = 2,
		keyName = "googleAPIKey",
		name = "Google API Key",
		description = "Google API Key to access the Google Sheet",
		section = apiSection
	)
	default String googleAPIKey()	{	return "";	}

	@Range(
			min = SHEET_ROW_MIN,
			max = SHEET_ROW_MAX
	)
	@ConfigItem(
			position = 3,
			keyName = "numberOfCells",
			name = "Number of Cells in Column A to retrieve from Google Sheet",
			description = "(1-15) How many rows of the spreadsheet you will retrieve.",
			section = apiSection
	)
	default int numberOfCells() { return 5; }


	@ConfigItem(
			position = 1,
			keyName = "varrockTC",
			name = "Town Crier in Varrock(East Bank)",
			description = "Have Benny in Varrock broadcast messages as well",
			section = npcSection
	)
	default boolean varrockTC()	{	return false;	}

	@ConfigItem(
			position = 2,
			keyName = "lumbridgeGuide",
			name = "Lumbridge Guide in Lumbridge",
			description = "Have Guide in Lumbridge broadcast messages as well",
			section = npcSection
	)
	default boolean lumbridgeGuide()	{	return false;	}

	@ConfigItem(
			position = 3,
			keyName = "ardougneTC",
			name = "Town Crier in Ardougne Market",
			description = "Have Town Crier in Ardougne Market broadcast messages",
			section = npcSection
	)
	default boolean ardougneTC()	{	return false;	}

	@ConfigItem(
			position = 4,
			keyName = "seersTC",
			name = "Town Crier in Seers Village",
			description = "Have Town Crier in Seers Village broadcast messages",
			section = npcSection
	)
	default boolean seersTC()	{	return false;	}

	@ConfigItem(
			position = 5,
			keyName = "varrockBenny",
			name = "Benny(Newsie) in Varrock Square",
			description = "Have Benny in Varrock broadcast messages",
			section = npcSection
	)
	default boolean varrockBenny()	{	return false;	}
}
