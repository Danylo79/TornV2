package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;

import java.awt.*;

public class Notifications extends Module {
    public Notifications() {
        super("Notification", Category.RENDER, -1, new Color(0, 0, 0), true, false);
        addSetting(new Setting("Use ClickGUI Color", this, false));
    }
}
