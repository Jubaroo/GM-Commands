
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.sounds.SoundPlayer;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.GoTo;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

public class CmdGoTo extends WurmCmd {

    public CmdGoTo() {
        super("#goto", Initiator.CmdGoToPower);
    }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
        Communicator comm = actor.getCommunicator();

        if ( argv.length != 2 ) {
            comm.sendNormalServerMessage("usage: #goto <deed|player>");
            return true;
        }

        if ( argv[1].matches("^[0-9]+,[0-9]+$") ) {
            String[] coords = argv[1].split(",");
            GoTo.sendToXy(actor, Integer.valueOf(coords[0]), Integer.valueOf(coords[1]), 0, 0);
            SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
            return true;
        }

        if ( GoTo.sendToPlayer(actor, argv[1]) ) {
            SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
            return true;
        }

        if ( GoTo.sendToVillage(actor, argv[1]) ) {
            SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
            return true;
        }

        return true;
    }

}
