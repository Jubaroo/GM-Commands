package org.jubaroo.mods.gmcommands.cmds;

import com.wurmonline.server.Items;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.Creatures;
import com.wurmonline.server.economy.Economy;
import com.wurmonline.server.economy.Shop;
import com.wurmonline.server.items.Item;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.jubaroo.mods.gmcommands.Initiator;
import org.jubaroo.mods.gmcommands.utils.WurmCmd;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdTraderReset extends WurmCmd {
	private static final Logger logger = Logger.getLogger(CmdTraderReset.class.getName());
	
    public CmdTraderReset () {
        super("#resettraders", Initiator.CmdTraderResetPower);
    }

    @Override
    public boolean runWurmCmd(Creature actor, String[] argv) {
    	refreshTraders();
    	return true;
    }

    public static int refreshTraders() {
        int affected = 0;
        for (Shop shop : Economy.getTraders()) {
            try {
                if (shop.isPersonal()) continue;
                Creature creature = Creatures.getInstance().getCreatureOrNull(shop.getWurmId());
                if (creature == null) continue;
                new LinkedList<>(creature.getInventory().getItems())
                        .stream().map(Item::getWurmId).forEach(Items::destroyItem);
                ReflectionUtil.callPrivateMethod(null, ReflectionUtil.getMethod(Shop.class, "createShop", new Class[]{Creature.class}), creature);
                affected++;
            } catch (Exception e) {
                logger.log(Level.SEVERE,"Error in shop refresh" + e.toString());
            }
        }
        return affected;
    }
}
