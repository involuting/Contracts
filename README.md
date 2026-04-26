# Contracts Plugin

A fully dynamic Minecraft contract system that generates PvP, PvE, and mining contracts automatically.  
Designed for survival, RPG, and economy servers.

## Features

- Dynamic contract generation (no config required)
-  Player kill contracts
-  Mob kill contracts
-  Block mining contracts
-  Contract expiration system
-  Progress tracking system
-  Reward system (Vault supported)
-  GUI contract menu with pagination
-  Real-time contract updates
-  Persistent storage system


## How It Works

Contracts are automatically generated at intervals.  
Players can accept contracts from the GUI and complete them by performing tasks such as:

- Killing a specific player
- Killing specific mobs
- Mining specific blocks

Once completed, players receive rewards automatically.

## Contract Types

### ⚔ Player Contracts
Kill a specific player a set number of times.

### Mob Contracts
Kill specific mobs like:
- Zombie
- Skeleton
- Creeper
- Spider
- Enderman

### Block Contracts
Mine specific blocks such as:
- Stone
- Iron Ore
- Diamond Ore
- Coal Ore

## Rewards

Rewards are automatically calculated and can include:
- Money (Vault integration)
- Scaled rewards based on difficulty

## Installation

1. Download the plugin `.jar`
2. Place it into your `/plugins` folder
3. Restart your server
4. Configure permissions (if needed)

## Dependencies

- Spigot / Paper 1.21+
- Vault (optional, for economy rewards)

## Future Features

- Contract rarity system (Common → Legendary)
- NPC contract givers
- Player contract statistics
- Leaderboards
- GUI upgrades with animations
- Custom contract types API


## Developer Notes

This plugin is built with a modular architecture:

- **Generator** → handles contract spawning logic
- **Factory** → creates contracts
- **Manager** → stores and manages active contracts
- **Listeners** → track player actions
- **Storage** → handles persistence

## License

Free to use for learning and personal servers.  
Do not redistribute as paid content.


## Support

If you like this project, consider starring the repository.

