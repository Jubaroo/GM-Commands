
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.sounds.SoundPlayer;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.GoTo;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

public class CmdMovePlayer extends WurmCmd {

    public CmdMovePlayer() {
        super("#moveplayer", Initiator.CmdMovePlayerPower);
    }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
        Communicator comm = actor.getCommunicator();

        if ( argv.length != 2 ) {
            comm.sendNormalServerMessage("usage: #moveplayer <player>");
            return true;
        }

        if ( GoTo.sendPlayerHere(actor, argv[1]) ) {
            SoundPlayer.playSound("sound.fx.humm", actor, 1.0f);
            return true;
        }
        return true;
    }

}
