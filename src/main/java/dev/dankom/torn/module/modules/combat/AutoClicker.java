package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker extends Module {

    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    private double min;
    private double max;

    public AutoClicker() {
        super("AutoClicker", Category.COMBAT, -1, new Color(179, 32, 0), true, true);
        ArrayList<String> args = new ArrayList<>();
        args.add("Left");
        args.add("Right");
        addSetting(new Setting("Mouse Button", this, "Left", args));
        addSetting(new Setting("Min CPS", this, 1, 1 , 10, true));
        addSetting(new Setting("Max CPS", this, 1, 1 , 50, true));
        addSetting(new Setting("Fake Jitter", this, false));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        updateVals();
    }

    @Override
    public void onUpdate(UpdateEvent e) {
        tick();
    }

    public void tick() {
        int key;
        if (getSetting("Mouse Button").getValString().equalsIgnoreCase("Left")) {
            key = mc.gameSettings.keyBindAttack.getKeyCode();
        } else {
            key = mc.gameSettings.keyBindPickBlock.getKeyCode();
        }
        if (Mouse.isButtonDown(0)) {
            if (System.currentTimeMillis() - lastClick > speed * 1000) {
                lastClick = System.currentTimeMillis();
                if (hold < lastClick) {
                    hold = lastClick;
                }
                KeyBinding.setKeyBindState(key, true);
                KeyBinding.onTick(key);
                updateVals();
            } else if (System.currentTimeMillis() - hold > holdLength * 1000) {
                KeyBinding.setKeyBindState(key, false);
                updateVals();
            }
        }
    }

    private void updateVals() {
        min = getSetting("Min CPS").getValDouble();
        max = getSetting("Max CPS").getValDouble();

        if (min >= max) {
            max = min + 1;
        }
        speed = 1.0 / ThreadLocalRandom.current().nextDouble(min - 0.2, max);
        holdLength = speed / ThreadLocalRandom.current().nextDouble(min, max);
    }
}
