package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.Torn;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", Category.RENDER, Keyboard.KEY_RSHIFT, new Color(249, 0, 255), false, false);
        addSetting(new Setting("R", this, 100.0, 0.0, 100.0, true));
        addSetting(new Setting("G", this, 50.0, 0.0, 100.0, true));
        addSetting(new Setting("B", this, 50.0, 0.0, 100.0, true));
        addSetting(new Setting("A", this, 100.0, 0.0, 100.0, true));
        addSetting(new Setting("Chroma", this, false));
        addSetting(new Setting("Chroma Speed", this, 10.0, 10.0, 1000.0, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Torn.getClickGui());
        this.setToggled(false);
    }
}
