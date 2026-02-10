# Kill Item Entity
kill @e[type=minecraft:item,distance=..1,nbt={Item:{id:"minecraft:deepslate"}},limit=1]
kill @e[type=minecraft:item,distance=..1,nbt={Item:{id:"minecraft:lapis_lazuli"}},limit=1]

# New Entity
summon minecraft:item ~ ~0.1 ~ {Item:{id:"fromtheabyss:abyss_alloy",Count:1b}}
particle minecraft:sculk_soul ~ ~ ~ 0.2 0 0.2 0.04 10 force