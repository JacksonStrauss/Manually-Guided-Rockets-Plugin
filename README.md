## [Manually Guided Rockets Plugin](https://www.spigotmc.org/resources/manually-guided-rockets.107215/)
This is a Java Minecraft Plugin that I created which adds rockets to the game that the user can manually control and guide to a desired target.

This github repository stores the sourcecode for the plugin. The plugin download is hosted on the SpigotMC Forums website linked here: [Plugin Webpage](https://www.spigotmc.org/resources/manually-guided-rockets.107215/)

I created a custom Minecraft "texture pack" for the rocket and rocket launcher models using an application called [Blockbench](https://www.blockbench.net/). I'll also add the description of the plugin here that can be found on the SpigotMC forum page:

### » Description
This plugin allows users to launch rockets and manually guide them by moving the mouse/cursor. (Propels the rocket to where the player is looking on the screen.) The user just needs to right-click with the rocket launcher in their hand and rockets in their inventory as ammo and whoosh... off you go!

The plugin makes use of a custom resource pack that adds the rocket and rocket launcher textures. The resource pack is automatically built-in so that every user will be prompted to use the resource pack when they join the server.

The user's inventory is saved so that once they strike their target while in rocket mode, their inventory is restored and they are teleported back to where they launched the rocket from.

### » IMPORTANT NOTES
- You're going to want to set allow-flight to true in the server.properties file or else players might get kicked for flying in survival mode

- The rocket and rocket launcher models use minecraft paper as the item.
- The custom model data for the rocket is 1000
- The custom model data for the rocket launcher is 1001
- If you want to change the custom model data values, you are able to do so in the "config.yml" file but you would also have to change the mappings in the resource pack json files. You can contact me if you need help doing this.

- This plugin is still in the early stages of development and there might be some bugs, sorry about that! Please report the bugs using my contact form below.

### » COMMANDS
/giverocket
(Gives one rocket item to the player who runs the command)

/giverocket [amount]
(Gives the specified amount of rocket items to the player who runs the command)

/giverocket [amount] [playername]
(Gives the specified amount of rocket items to the player who is listed as the 2nd argument of the command)

/giverocketlauncher
(Gives one rocket launcher item to the player who runs the command)

/giverocketlauncher [playername]
(Gives one rocket launcher item to the player who is listed as the 1st argument of the command)

### » PERMISSIONS
- 'manuallyguidedrockets.giverocket'
(Determines whether a user can use the /giverocket command)

- 'manuallyguidedrockets.giverocketlauncher'
(Determines whether a user can use the /giverocketlauncher command)

- 'manuallyguidedrockets.fire'
(Determines whether a user can fire a rocket from a rocket launcher)

IMPORTANT NOTE: requiring these permissions can be disabled in the "config.yml" file. The default is that permissions are OFF. If you are going to implement this into a server where users need permissions to perform these commands and actions then please toggle the config.yml setting to TRUE.


### » CUSTOMIZATION
- You can edit all of the plugin messages in the provided "lang.yml" file.
- You can edit the item lore of the rocket and rocket launcher items in the "lang.yml" file.

### » FUTURE PLANS
- Add documentation to code
- Reformat the /giverocket command so that the [playername] goes before the [amount]
- Add an NPC character that acts as the player so that the player can take damage and die even while in rocket mode.
- A configurable cooldown timer to use the rocket launcher
- A maximum range that the rocket can travel from the launch site
- A maximum flight time that the rocket has before fuel runs out and it explodes
- WorldGuard compatibility
