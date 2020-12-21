package dev.dankom.torn.command.commands;

import dev.dankom.torn.Torn;
import dev.dankom.torn.command.Command;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.util.ChatUtils;
import net.minecraft.command.CommandException;

import java.util.List;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "t", "activate", "deactivate");
    }

    @Override
    public void run(String alias, String[] args) {
        if (args.length < 1) {
            try {
                throw new CommandException("Usage: ." + alias + " <module> [<on/off>]");
            } catch (CommandException e) {
                e.printStackTrace();
            }
        }
        Module mod = Torn.getModuleManager().getModule(args[0]);

        if (mod == null) try {
            throw new CommandException("The module '" + args[0] + "' does not exist");
        } catch (CommandException e) {
            e.printStackTrace();
        }

        boolean state = !mod.isToggled();

        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("on")) state = true;
            else if (args[1].equalsIgnoreCase("off")) state = false;
            else try {
                    throw new CommandException("Usage: ." + alias + " <module> <on/off>");
                } catch (CommandException e) {
                    e.printStackTrace();
                }
        }

        mod.setToggled(state);

        ChatUtils.success(mod.getName() + " was " + ChatUtils.PRIMARY_COLOR + (state ? "enabled" : "disabled"));
    }

    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return null;
    }
}
