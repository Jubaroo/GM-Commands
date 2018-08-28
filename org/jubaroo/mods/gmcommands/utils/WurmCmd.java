
package org.jubaroo.mods.gmcommands.utils;

import com.wurmonline.server.creatures.Creature;

public class WurmCmd {

    int minPower;
    String cmdName;

    public WurmCmd (String cmdName, int minPower) {
        this.cmdName = cmdName;
        this.minPower = minPower;
    }

    public boolean runWurmCmd(Creature player, String[] argv) { return true; }
}
