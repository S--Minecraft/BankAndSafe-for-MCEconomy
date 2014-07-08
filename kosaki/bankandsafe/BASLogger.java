package kosaki.bankandsafe;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class BASLogger {

	public static final Logger logger = Logger.getLogger("BankAndSafe for MCEconomy");

	public static void checkingMod(String modName) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINER, "Mod Plugin Load : Now checking for " + modName);
	}

	public static void loadedMod(String modid) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINER, "Mod Plugin Load : Succeeded loaded " + modName + " plugin");
	}

	public static void failedMod(String modid) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINER, "Mod Plugin Load : Failed to check for " + modName);
	}

	public static void otherLog(String aString) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINEST, "Other Log : " + aString);
	}

	public static void BASLoading(String aString) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINEST, "Loading : " + aString);
	}

	public static void log(String aString) {
		logger.setParent(FMLLog.getLogger());
		logger.log(Level.FINEST, aString);
	}

}