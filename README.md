# Skulls
A Spigot Plugin that can give Player and Entity Skulls when they die or with a command.

## Config
You can configure the Plugin in the config.yml. <br/>
Here is a brief explanation of what the configurations do.

```yml
# If this is set to true the Plugin initialization is not run.
# This means that the Listeners and Commands aren't getting registered.
# You can still see the command in the ChatBox but can't use it.
# (It's being shown because it's still in the plugin.yml)
enabled: true 

drop:
  # When a player kills an entity should the head be dropped?
  entity: true
  # When a player kills a player should the head be dropped?
  player: true
  # Should the /Skull command be enabled?
  command: true
```
