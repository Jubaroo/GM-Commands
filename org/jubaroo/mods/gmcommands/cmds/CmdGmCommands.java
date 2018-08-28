
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

public class CmdGmCommands extends WurmCmd {

    public CmdGmCommands() {
        super("#gmcommands", Initiator.CmdGmCommandsPower);
    }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
        Communicator comm = actor.getCommunicator();
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Add an affinity to a player. Note - Skill name must be capitalized: GM Power " + Initiator.CmdAddAffPower);
        comm.sendSystemMessage("#addaff <player> <skill>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Fill sleep bonus of chosen player: GM Power  " + Initiator.CmdAddSleepBonusPower);
        comm.sendSystemMessage("#addsleep <player>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Show current coffer value: GM Power  " + Initiator.CmdCoffersPower);
        comm.sendSystemMessage("#coffers");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Fill a players food, water, health, CCFP, and nutrition bars: GM Power  " + Initiator.CmdFillUpPower);
        comm.sendSystemMessage("#fillup <player>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Teleport yourself to any player or village (choose either a deed or a player): GM Power  " + Initiator.CmdGoToPower);
        comm.sendSystemMessage("#goto <deed|player>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Fill food, water, health, CCFP, and nutrition bars of all current players: GM Power  " + Initiator.CmdMassRefreshPower);
        comm.sendSystemMessage("#massrefresh");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Summon player to you: GM Power  " + Initiator.CmdMovePlayerPower);
        comm.sendSystemMessage("#moveplayer <player>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Send a player to their home deed: GM Power  " + Initiator.CmdSendToHomePower);
        comm.sendSystemMessage("#sendhome <player>");
        comm.sendSystemMessage("=========================================================================================");
        comm.sendSystemMessage("Reset items (not coin) on all traders: GM Power  " + Initiator.CmdTraderResetPower);
        comm.sendSystemMessage("#resettraders");
        comm.sendSystemMessage("=========================================================================================");
        return true;
    }

}
