/*
 * Copyright (C) 2014 maciekmm <maciekmm@maciekmm.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.maciekmm.tabber;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class Tabber extends Plugin implements Listener {

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTab(TabCompleteEvent event) {
        if (!event.getSuggestions().isEmpty()) {
            return; //If suggestions for this command are handled by other plugin don't add any
        }
        String[] args = event.getCursor().split(" ");

        final String checked = (args.length > 0 ? args[args.length - 1] : event.getCursor()).toLowerCase();
        for (ProxiedPlayer player : this.getProxy().getPlayers()) {
            if (player.getName().toLowerCase().startsWith(checked)) {
                event.getSuggestions().add(player.getName());
            }
        }
    }
}
