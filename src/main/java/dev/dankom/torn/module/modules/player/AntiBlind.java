package dev.dankom.torn.module.modules.player;

import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class AntiBlind extends Module {
    public AntiBlind() {
        super("AntiBlind", Category.PLAYER, -1, new Color(0, 0, 0), true, true);
    }
}
