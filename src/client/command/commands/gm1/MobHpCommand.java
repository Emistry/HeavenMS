/*
    This file is part of the HeavenMS MapleStory Server, commands OdinMS-based
    Copyleft (L) 2016 - 2019 RonanLana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import constants.game.GameConstants;
import server.life.MapleMonster;

public class MobHpCommand extends Command {
    {
        setDescription("Show list of monster's HP.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        int totalMonsterCount = 0;
        for(MapleMonster monster : player.getMap().getAllMonsters()) {
            if (monster != null && monster.getHp() > 0) {
                if (params.length == 0 // list all monsters
                    || (params.length > 0 && monster.getName().toLowerCase().contains(String.join(" ", params))) // filter by monster name
                ) {
                    int currentHp = monster.getHp();
                    int currentMaxHp = monster.getMaxHp();
                    long hpPercent = (currentHp * 100L / currentMaxHp);
                    player.yellowMessage(monster.getName() + " (" + monster.getId() + ") has " + GameConstants.numberWithCommas(currentHp) + " / " + GameConstants.numberWithCommas(currentMaxHp) + " HP (" + hpPercent + "%)");
                    totalMonsterCount++;
                } 
            }
        }
        player.yellowMessage("Total Monster found: "+ totalMonsterCount);
    }
}
