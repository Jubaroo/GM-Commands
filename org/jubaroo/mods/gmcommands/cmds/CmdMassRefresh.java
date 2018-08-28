
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.Players;
import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.sounds.SoundPlayer;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdMassRefresh extends WurmCmd {
    private static Logger logger;
    static {
        CmdMassRefresh.logger = Logger.getLogger(CmdMassRefresh.class.getName());
    }

public CmdMassRefresh() {
    super("#massrefresh", Initiator.CmdMassRefreshPower);
}

@Override
public boolean runWurmCmd(Creature actor, String[] argv) {
    Communicator comm = actor.getCommunicator();
    try {
        int nums = 0;
        Player[] allPlayers = Players.getInstance().getPlayers();
        int numPlayers = allPlayers.length;

        for (int var8 = 0; var8 < numPlayers; ++var8) {
            Player player = allPlayers[var8];
                ++nums;
                SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
                player.getStatus().refresh(0.99f, true);
                player.getStatus().removeWounds();
                player.getStatus().setMaxCCFP();
                player.getCommunicator().sendSafeServerMessage("A strange and calming energy flows through the lands and into its people as the gods grant favor upon them. You suddenly feel refreshed, well fed, and fully healed!", (byte) 2);
        }
        if (nums > 0) {
            String message = String.format("All players refreshed and healed");
            comm.sendNormalServerMessage(message);
            CmdMassRefresh.logger.log(Level.INFO, "CmdMassRefresh()");
        }
    } catch (Throwable e) {
        comm.sendNormalServerMessage("error: " + e.toString());
        return true;
    }
    return true;
    }

}
