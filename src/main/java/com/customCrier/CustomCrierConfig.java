package com.customCrier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("customCrier")
public interface CustomCrierConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
		keyName = "googleSheetID",
		name = "Google Sheet ID",
		description = "Google Sheet ID of the public sheet to read from"
	)
	default String googleSheetID() {	return "";	}

	@ConfigItem(
		keyName = "googleAPIKey",
		name = "Google API Key",
		description = "Google API Key to access the Google Sheet"
	)
	default String googleAPIKey()	{	return "";	}

	@ConfigItem(
		keyName = "varrockBenny",
		name = "Include Benny in Varrock(Newsie)",
		description = "Have Benny in Varrock broadcast messages as well"
	)
	default Boolean varrockBenny()	{	return false;	}
}
