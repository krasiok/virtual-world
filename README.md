🌍 Virtual World Simulator (RPG Edition)

A turn-based biological simulation game written in Java using the Swing framework. The project demonstrates core Object-Oriented Programming principles such as inheritance, polymorphism, and encapsulation.

The simulation takes place on a 2D grid (default 20x20) where various organisms (animals and plants) interact, fight, reproduce, and evolve. The player controls a Human character, earning XP and unlocking special abilities to survive in this hostile environment.
🎮 Gameplay & Mechanics
Turn System

The game is turn-based. In each turn, all organisms perform their actions based on their Initiative:

-Organisms with higher initiative move first.
-If initiatives are equal, the older organism moves first.
-Plants have an initiative of 0.

The Grid

Movement: Most animals move 1 tile in a random direction.
Collision: If an organism moves to an occupied tile, a collision occurs.
Standard Rule: The stronger organism wins and kills the weaker one.
Same Species: If two animals of the same species meet, they breed instead of fighting.

🧬 Organisms
🐾 Animals
Icon	Name	Str	Init	Special Ability
🐺	Wolf	9	5	Standard movement and combat. High strength.
🐑	Sheep	4	4	Standard movement and combat.
🦊	Fox	3	7	Good Smell: Never moves to a tile occupied by an organism stronger than itself.
🐢	Turtle	2	1	Shell: 75% chance to stay in place. Deflects attacks from animals with Strength < 5.
🦌	Antelope	4	4	Agility: Movement range is 2 tiles. 50% chance to flee from a fight to a free neighbor tile.
🦾	CyberSheep	11	4	Anti-Hogweed Unit: Targets the nearest Sosnowsky's Hogweed. Eats it without dying. Transforms into a normal Sheep when no Hogweeds remain.
🌿 Plants

All plants have 0 Initiative. Their standard action is a chance to spread (sow) to an adjacent tile.
Icon	Name	Strength	Effect
🌱	Grass	0	Standard plant. Basic food source.
🌼	Dandelion	0	Spread: Makes 3 attempts to spread in one turn.
🍒	Guarana	0	Buff: Adds +3 Strength to the animal that eats it.
☠️	Deadly Nightshade	99	Poison: Instantly kills any animal that eats it.
☣️	Sosnowsky's Hogweed	10	Toxic Area: Kills all animals in its vicinity (neighbors) every turn. Kills any eater (except CyberSheep).

👤 The Human (Player)

The player controls a Human character. Unlike animals, the Human does not move randomly.
Stats: Strength: 5 | Initiative: 4
Controls: Use W, A, S, D (or arrows) to determine the next move direction before the turn starts.
Progression: The Human earns XP (Experience Points) by killing animals or eating plants. Accumulated XP allows the player to activate special abilities.

🌟 Special Abilities

The Human can activate powerful skills using keys 1 to 5. Abilities last for a specific duration and have a cooldown period.

[1]	Burnt Offering - Destroys (incinerates) all plants and animals on the tiles immediately surrounding the Human.
[2]	Magic Elixir - Increases Strength to 10. Strength decreases by 1 each subsequent turn until it returns to normal.
[3]	Antelope Speed - Movement range increases to 2 tiles. (First 3 turns: 100% chance; Last 2 turns: 50% chance).
[4]	Alzur's Shield - Scares away other animals. Any animal trying to step on the Human's tile is pushed to a random adjacent tile.
[5]	Immortality - The Human cannot be killed. If attacked by a stronger foe, the Human is moved to a safe adjacent tile.

🛠️ Installation & Execution
Prerequisites

Java Development Kit (JDK) 8 or higher.

Running the Game

1.Clone the repository (or download the source code).
2.Navigate to the out/artifacts/ folder (or where the .jar is located).
3.Ensure the resources folder (containing images) is in the correct path relative to the JAR (or embedded).
4.Run the following command in your terminal: java -jar game.jar

Alternatively, you can open the project in IntelliJ IDEA and run the Main class.
