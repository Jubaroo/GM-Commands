
package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.economy.Economy;
import com.wurmonline.server.economy.Shop;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

public class CmdCoffers extends WurmCmd {

    public CmdCoffers() { super("#coffers", Initiator.CmdCoffersPower); }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
        Communicator comm = actor.getCommunicator();

        Shop kingsShop = Economy.getEconomy().getKingsShop();

        if ( argv.length == 2 ) {
            Long cash = Long.valueOf( argv[1] );
            kingsShop.setMoney( cash );
        }

        Long coff = kingsShop.getMoney();

        comm.sendNormalServerMessage( String.format("kings coffers: %d", coff) );

        return true;
    }

}
