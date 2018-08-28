
package org.jubaroo.mods.gmcommands;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.players.Player;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.PlayerMessageListener;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.jubaroo.mods.gmcommands.cmds.*;
import org.jubaroo.mods.gmcommands.utils.ArgumentTokenizer;
import org.jubaroo.mods.gmcommands.utils.CmdTool;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Initiator implements WurmServerMod, ServerStartedListener, Configurable, PlayerMessageListener {
    private static Logger logger = Logger.getLogger(Initiator.class.getName());
    private CmdTool cmdtool = null;
    private static boolean CmdAddAff;
    private static boolean CmdAddSleepBonus;
    private static boolean CmdCoffers;
    private static boolean CmdFillUp;
    private static boolean CmdGmCommands;
    private static boolean CmdGoTo;
    private static boolean CmdMassRefresh;
    private static boolean CmdMovePlayer;
    private static boolean CmdSendToHome;
    private static boolean CmdTraderReset;
    public static int CmdAddAffPower;
    public static int CmdAddSleepBonusPower;
    public static int CmdCoffersPower;
    public static int CmdFillUpPower;
    public static int CmdGmCommandsPower;
    public static int CmdGoToPower;
    public static int CmdMassRefreshPower;
    public static int CmdMovePlayerPower;
    public static int CmdSendToHomePower;
    public static int CmdTraderResetPower;
    static {
        Initiator.CmdAddAff = false;
        Initiator.CmdAddSleepBonus = false;
        Initiator.CmdCoffers = false;
        Initiator.CmdFillUp = false;
        Initiator.CmdGmCommands = false;
        Initiator.CmdGoTo = false;
        Initiator.CmdMassRefresh = false;
        Initiator.CmdMovePlayer = false;
        Initiator.CmdSendToHome = false;
        Initiator.CmdTraderReset = false;
        Initiator.CmdAddAffPower = 5;
        Initiator.CmdAddSleepBonusPower = 5;
        Initiator.CmdCoffersPower = 5;
        Initiator.CmdFillUpPower = 5;
        Initiator.CmdGmCommandsPower = 5;
        Initiator.CmdGoToPower = 5;
        Initiator.CmdMassRefreshPower = 5;
        Initiator.CmdMovePlayerPower = 5;
        Initiator.CmdSendToHomePower = 5;
        Initiator.CmdTraderResetPower = 5;
    }

    public void configure(Properties properties) {
        Initiator.logger.log(Level.INFO, "configure called");
        Initiator.logger.log(Level.INFO, "========================= Command Toggles ==============================" );
        Initiator.CmdAddAff = Boolean.valueOf(properties.getProperty("CmdAddAff"));
        Initiator.CmdAddSleepBonus = Boolean.valueOf(properties.getProperty("CmdAddSleepBonus"));
        Initiator.CmdCoffers = Boolean.valueOf(properties.getProperty("CmdCoffers"));
        Initiator.CmdFillUp = Boolean.valueOf(properties.getProperty("CmdFillUp"));
        Initiator.CmdGmCommands = Boolean.valueOf(properties.getProperty("CmdGmCommands"));
        Initiator.CmdGoTo = Boolean.valueOf(properties.getProperty("CmdGoTo"));
        Initiator.CmdMassRefresh = Boolean.valueOf(properties.getProperty("CmdMassRefresh"));
        Initiator.CmdMovePlayer = Boolean.valueOf(properties.getProperty("CmdMovePlayer"));
        Initiator.CmdSendToHome = Boolean.valueOf(properties.getProperty("CmdSendToHome"));
        Initiator.CmdTraderReset = Boolean.valueOf(properties.getProperty("CmdTraderReset"));
        Initiator.logger.log(Level.INFO, "========================= Command Power Levels ==============================" );
        Initiator.CmdAddAffPower = Integer.valueOf( properties.getProperty("CmdAddAffPower") );
        Initiator.CmdAddSleepBonusPower = Integer.valueOf( properties.getProperty("CmdAddSleepBonusPower") );
        Initiator.CmdCoffersPower = Integer.valueOf( properties.getProperty("CmdCoffersPower") );
        Initiator.CmdFillUpPower = Integer.valueOf( properties.getProperty("CmdFillUpPower") );
        Initiator.CmdGmCommandsPower = Integer.valueOf( properties.getProperty("CmdGmCommandsPower") );
        Initiator.CmdGoToPower = Integer.valueOf( properties.getProperty("CmdGoToPower") );
        Initiator.CmdMassRefreshPower = Integer.valueOf( properties.getProperty("CmdMassRefreshPower") );
        Initiator.CmdMovePlayerPower = Integer.valueOf( properties.getProperty("CmdMovePlayerPower") );
        Initiator.CmdSendToHomePower = Integer.valueOf( properties.getProperty("CmdSendToHomePower") );
        Initiator.CmdTraderResetPower = Integer.valueOf( properties.getProperty("CmdTraderResetPower") );
        Initiator.logger.log(Level.INFO, "========================= Logging ==============================" );
        Initiator.logger.log(Level.INFO, "CmdAddAffPower = " + Initiator.CmdAddAffPower );
        Initiator.logger.log(Level.INFO, "CmdAddSleepBonusPower = " + Initiator.CmdAddSleepBonusPower );
        Initiator.logger.log(Level.INFO, "CmdCoffersPower = " + Initiator.CmdCoffersPower );
        Initiator.logger.log(Level.INFO, "CmdFillUpPower = " + Initiator.CmdFillUpPower );
        Initiator.logger.log(Level.INFO, "CmdGmCommandsPower = " + Initiator.CmdGmCommandsPower );
        Initiator.logger.log(Level.INFO, "CmdGoToPower = " + Initiator.CmdGoToPower );
        Initiator.logger.log(Level.INFO, "CmdMassRefreshPower = " + Initiator.CmdMassRefreshPower );
        Initiator.logger.log(Level.INFO, "CmdMovePlayerPower = " + Initiator.CmdMovePlayerPower );
        Initiator.logger.log(Level.INFO, "CmdSendToHomePower = " + Initiator.CmdSendToHomePower );
        Initiator.logger.log(Level.INFO, "CmdTraderResetPower = " + Initiator.CmdTraderResetPower );
        if (Initiator.CmdAddAff) { logger.log(Level.INFO, "CmdAddAff: Enabled"); }
        if (!Initiator.CmdAddAff) { logger.log(Level.INFO, "CmdAddAff: Disabled"); }
        if (Initiator.CmdAddSleepBonus) { logger.log(Level.INFO, "CmdAddSleepBonus: Enabled"); }
        if (!Initiator.CmdAddSleepBonus) { logger.log(Level.INFO, "CmdAddSleepBonus: Disabled"); }
        if (Initiator.CmdCoffers) { logger.log(Level.INFO, "CmdCoffers: Enabled"); }
        if (!Initiator.CmdCoffers) { logger.log(Level.INFO, "CmdCoffers: Disabled"); }
        if (Initiator.CmdFillUp) { logger.log(Level.INFO, "CmdFillUp: Enabled"); }
        if (!Initiator.CmdFillUp) { logger.log(Level.INFO, "CmdFillUp: Disabled"); }
        if (Initiator.CmdGmCommands) { logger.log(Level.INFO, "CmdGmCommands: Enabled"); }
        if (!Initiator.CmdGmCommands) { logger.log(Level.INFO, "CmdGmCommands: Disabled"); }
        if (Initiator.CmdGoTo) { logger.log(Level.INFO, "CmdGoTo: Enabled"); }
        if (!Initiator.CmdGoTo) { logger.log(Level.INFO, "CmdGoTo: Disabled"); }
        if (Initiator.CmdMassRefresh) { logger.log(Level.INFO, "CmdMassRefresh: Enabled"); }
        if (!Initiator.CmdMassRefresh) { logger.log(Level.INFO, "CmdMassRefresh: Disabled"); }
        if (Initiator.CmdMovePlayer) { logger.log(Level.INFO, "CmdMovePlayer: Enabled"); }
        if (!Initiator.CmdMovePlayer) { logger.log(Level.INFO, "CmdMovePlayer: Disabled"); }
        if (Initiator.CmdSendToHome) { logger.log(Level.INFO, "CmdSendToHome: Enabled"); }
        if (!Initiator.CmdSendToHome) { logger.log(Level.INFO, "CmdSendToHome: Disabled"); }
        if (Initiator.CmdTraderReset) { logger.log(Level.INFO, "CmdTraderReset: Enabled"); }
        if (!Initiator.CmdTraderReset) { logger.log(Level.INFO, "CmdTraderReset: Disabled"); }
        Initiator.logger.log(Level.INFO, "all configure completed");
    }

    @Override
    public void onServerStarted() {
        Initiator.logger.log(Level.INFO, "onServerStarted called");
        try {
            Initiator.logger.info("Registering Server commands...");
            cmdtool = new CmdTool();
            if (Initiator.CmdAddAff) { cmdtool.addWurmCmd(new CmdGoTo()); }
            if (Initiator.CmdAddSleepBonus) { cmdtool.addWurmCmd(new CmdGmCommands()); }
            if (Initiator.CmdCoffers) { cmdtool.addWurmCmd(new CmdAddAff()); }
            if (Initiator.CmdFillUp) { cmdtool.addWurmCmd(new CmdSendToHome()); }
            if (Initiator.CmdGmCommands) { cmdtool.addWurmCmd(new CmdFillUp()); }
            if (Initiator.CmdGoTo) { cmdtool.addWurmCmd(new CmdMovePlayer()); }
            if (Initiator.CmdMassRefresh) { cmdtool.addWurmCmd(new CmdTraderReset()); }
            if (Initiator.CmdMovePlayer) { cmdtool.addWurmCmd(new CmdCoffers() ); }
            if (Initiator.CmdSendToHome) { cmdtool.addWurmCmd(new CmdAddSleepBonus() ); }
            if (Initiator.CmdTraderReset) { cmdtool.addWurmCmd(new CmdMassRefresh() ); }
        }
        catch (IllegalArgumentException | ClassCastException e) {
            Initiator.logger.log(Level.SEVERE, "Error in modifyItemsOnServerStarted()", e);
        }
        Initiator.logger.log(Level.INFO, "all onServerStarted completed");
    }

    @Override
    public boolean onPlayerMessage(Communicator communicator, String message) {
        final Player player = communicator.getPlayer();
        try {
            String[] argv = ArgumentTokenizer.tokenize( message ).toArray(new String[0]);
            if (cmdtool.runWurmCmd( player, argv ) ) {
                return true;
            }
        } catch (Throwable e) {
            communicator.sendNormalServerMessage( String.format("Cmd Err (%s) %s", message, e.toString()) );
            return false;
        }
        return false;
    }

    public String getVersion() {
        return "v1.0";
    }

}