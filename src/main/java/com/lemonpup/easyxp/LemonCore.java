package com.lemonpup.easyxp;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LemonCore {

    private static boolean isLookingAtWater(Player p) {
        return false;
    }
    public static boolean hasEnoughXP(Player p) {
        if (p.getExpToLevel() >= 1)
            return true;
        return false;
    }
    public static boolean isHoldingEmptyBottle(Player p) {
        if (p.getItemInHand().getType() == Material.GLASS_BOTTLE && !isLookingAtWater(p))
            return true;
        return false;
    }

}
