package com.envyful.reforged.gts.forge.listener.discord;

import com.envyful.api.concurrency.UtilConcurrency;
import com.envyful.api.discord.DiscordWebHook;
import com.envyful.api.forge.listener.LazyListener;
import com.envyful.reforged.gts.api.discord.DiscordEvent;
import com.envyful.reforged.gts.api.discord.DiscordEventManager;
import com.envyful.reforged.gts.forge.event.TradePurchaseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

public class DiscordTradePurchaseListener extends LazyListener {

    public DiscordTradePurchaseListener() {
        super();
    }

    @SubscribeEvent
    public void onTradeCreate(TradePurchaseEvent event) {
        DiscordEvent purchaseHandler = DiscordEventManager.getPurchaseHandler();

        if (!purchaseHandler.isEnabled()) {
            return;
        }

        UtilConcurrency.runAsync(() -> {
            try {
                DiscordWebHook webHook = event.getTrade().getWebHook(purchaseHandler);

                if (webHook != null) {
                    webHook.execute();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
