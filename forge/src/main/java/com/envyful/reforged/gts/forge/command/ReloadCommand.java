package com.envyful.reforged.gts.forge.command;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.reforged.gts.forge.ReforgedGTSForge;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

@Command(
        value = "reload",
        description = "Reloads the configs"
)
@Child
@Permissible("reforged.gts.command.reload")
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSender sender, String[] args) {
        ReforgedGTSForge.getInstance().loadConfig();
        sender.sendMessage(new TextComponentString("Reloaded config"));
    }
}
