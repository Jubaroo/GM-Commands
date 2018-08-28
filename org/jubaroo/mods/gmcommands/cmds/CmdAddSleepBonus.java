
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.NoSuchPlayerException;
import com.wurmonline.server.Players;
import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.sounds.SoundPlayer;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

public class CmdAddSleepBonus extends WurmCmd {

    public CmdAddSleepBonus () {
        super("#addsleep", Initiator.CmdAddSleepBonusPower);
    }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
        Communicator comm = actor.getCommunicator();

        if ( argv.length != 2 ) {
            comm.sendNormalServerMessage("usage: #addsleep <player> ");
            return true;
        }

        try {
            SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
            Player player = Players.getInstance().getPlayer(argv[1]);
            player.getSaveFile().addToSleep(18000);
            player.getCommunicator().sendSafeServerMessage("A sudden burst of energy flows through your body and you feel like you just slept a whole month. Your sleep bonus is full now!", (byte)2);
            String mesg = String.format("Player %s sleep bonus is filled", argv[1]);
            comm.sendNormalServerMessage(mesg);
        } catch (NoSuchPlayerException e) {
            comm.sendNormalServerMessage(String.format("Player %s not found", argv[1]));
            return false;

        } catch (Throwable e) {
            comm.sendNormalServerMessage("error: " + e.toString());
            return true;
        }

        return true;
    }

}
