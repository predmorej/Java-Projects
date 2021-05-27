import java.util.*;


//i dunna we gotta plan shit better

class characterCreater
{
    //create an character autobuilder                       hold ability scores / prof in own class or method?
    //      oh god probably need a different class
    //      select Class
    //          roll ability scores
    //              record
    //          record hit die, primary ability, saving throws, all proficiencies, skills, and equipment
    //          if magic user, record spell save DC and spell attack modifier
    //          select path (if is granted on lv 1) (add ability to choose level?)
    //      select race
    //          record race traits
    //          select subrace
    //              record race & subrace traits (abilitie score +s, extra proficicies, abilities, ect)
    //          select sex and name      
    //      select Personality (Traits??)
    //          select age and alignment
    //          select height and weight (use table given - pg 121)
    //          ?? select additional physical characteristics (hair / eye color, skin tone, scars, ect) ??
    //          select additional languages (possibly assign sooner) (make it less likely to assign exotic languages??)
    //      select Background
    //          record extra skill proficiencies, languages, and equipment
    //          record features
    //          record extra feature (Favorite schemes, criminal specialty, guild business, ect)  
    //          record possible variants
    //      assign weapons, spells (oh god)


    String charClass;
    String race;
    String subRace;
    String personality;
    String background;
    String hitDie;
    String pA;            // Primary ability
    String savingThrow1;
    String savingThrow2;
    ArrayList<Integer> abilityScores;
    ArrayList<String> weaponProf;
    ArrayList<String> armorProf;
    ArrayList<String> toolProf;
    ArrayList<String> skillProf;
    
    Random numGen = new Random();


    public void characterCreater()
    {
        this.charClass = "";
        this.race = "";
        this.personality = "";
        this.background = "";
        this.hitDie = "";
        this.pA = "";
        this.savingThrow1 = "";
        this.savingThrow2 = "";
        this.abilityScores = new ArrayList<Integer>();
    }

    public void abilityScores()
    {
        int strength = 0;
        int dexterity = 0;
        int constitution = 0;
        int intelligence = 0;
        int wisdom = 0;
        int charisma = 0;
    }

    public void proficiencies(String inProf)
    {
        this.weaponProf = new ArrayList<String>();
        this.armorProf = new ArrayList<String>();
        this.toolProf = new ArrayList<String>();
        this.skillProf = new ArrayList<String>();
        //i dunna store them in here somehow       
    }

    public void setAbilityScores(int inScore)
    {
        abilityScores.add(inScore);
        //once it hits length of 6, sort then set highest as the main skill for casting/attacking/whatever, randomly assign others
    }

    public void printAbilityScores()
    {
        System.out.println("\n" + "Ability Scores: ");     //for readability
        for (int i = 0; i < abilityScores.size(); i++)
        {
            System.out.print(abilityScores.get(i) + " ");
        }
    }

    public void setCharClass(String inClass)
    {
        this.charClass = inClass;
    }

    public String getCharClass()
    {
        return this.charClass;
    }

    public void selectCharClass()
    {
        //choose random class out of x classes ()

        String[] classArray = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
        List<String> classes = new ArrayList<String>();

        for (String i : classArray)      //Creates an arrayList of the differenct race types - 9 types
        {
            classes.add(i);
        }

        int classNum = numGen.ints(0, 12).findFirst().getAsInt();
        this.charClass = classes.get(classNum);     //Records race selected

        switch(this.charClass)
        {
            case "Barbarian":
                /*
                    Hit Points:
                        Hit Dice: 1d 12 per barbarian level
                        Hit Points at 1st Level: 12 + your Constitution modifier
                        Hit Points at Higher Levels: 1d 12 (or 7) + your Constitution modifier per barbarian level after 1st

                    Proficiencies: 
                        Armor: Light armor, medium armor, shields
                        Weapons: Simple weapons, martial weapons
                        Tools: None
                        PA: Strength
                        Saving Throws: Strength, Constitution
                        Skills: Choose two from: Animal Handling, Athletics, Intimidation, Nature, Perception, and Survival

                    Equipment:
                    • (a) a greataxe or (b) any martial melee weapon
                    • (a) two handaxes or (b) any simple weapon
                    • An explorer’s pack and four javelins

                    Features: 
                        Rage:
                            Rages: 2
                            Rage Damage: +2
                            You can enter a rage as a bonus action. While raging, you gain the following benefits if you aren’t wearing heavy armor:
                                • You have advantage on Strength checks and Strength saving throws.
                                • When you make a melee weapon attack using Strength, you gain a bonus to the damage roll that increases as you gain levels as a barbarian, 
                                    as shown in the Rage Damage column of the Barbarian table.
                                • You have resistance to bludgeoning, piercing, and slashing damage.
                        Unarmored Defense:
                            While you are not wearing any armor, your Armor Class equals 10 + your Dexterity modifier + your Constitution modifier. 
                            You can use a shield and still gain this benefit.
                */
                break;
            case "Bard":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per bard level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per bard level after 1st


                    Proficiencies:
                        Armor: Light armor
                        Weapons: Simple weapons, hand crossbows,
                                longswords, rapiers, shortswords
                        Tools: Three musical instruments o f your choice
                        PA: Charisma 
                        Saving Throws: Dexterity, Charisma
                        Skills: Choose any three

                    Equipment:
                    • (a) a rapier, (b) a longsword, or (c) any simple weapon
                    • (a) a diplomat’s pack or (b) an entertainer's pack
                    • (a) a lute or (b) any other musical instrument
                    • Leather armor and a dagger

                    Spellcasting:
                        Cantrips: 2 from Bard spell list
                        Spells Known: 4 from Bard spell list
                        Spell Slots: 2
                        SA = Charisma                                   SA = Spellcasting Ability
                        Spell Save DC = 8 + prof bonus + charisma mod
                        Spell Attack mod = prof bonus + charisma mod
                        Ritual Casting
                        SpellCasting Focus

                    Features:
                        Bardic Inspiration:
                            you use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you. 
                            That creature gains one Bardic Inspiration die, a d6. Once within the next 10 minutes, the creature can roll the die and add 
                            the number rolled to one ability check, attack roll, or saving throw it makes.

                */
                break;
            case "Cleric":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per cleric level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per cleric level after 1st

                    Proficiencies:
                        Armor: Light armor, medium armor, shields
                        Weapons: All simple weapons
                        Tools: None
                        PA: Wisdom
                        Saving Throws: Wisdom, Charisma
                        Skills: Choose two from History, Insight, Medicine, Persuasion, and Religion

                    Equipment:
                        • (a) a mace or (b) a warhammer (if proficient)
                        • (a) scale mail, (b) leather armor, or (c) chain mail (if proficient)
                        • (a) a light crossbow and 20 bolts or (b) any simple weapon
                        • (a) a priest’s pack or (b) an explorer’s pack
                        • A shield and a holy symbol

                    SpellCasting:
                        Cantrips: 3 from Cleric spell list
                        Spell Slots: 2
                        SA = Wisdom
                        Spell Save DC = 8 + prof bonus + wisdom mod
                        Spell attack mod = prof bonus + wisdom mod
                        Ritual Casting
                        Spellcasting Focus

                    Features:
                        Divine Domain: Knowledge, Life, Light, Nature, Tempest, Trickery, War
                            Knowledge:
                                Spells: 
                                    Command, identify
                                Blessings of Knowledge: 
                                    learn 2 extra languages
                                    proficient in 2 of the following skills: Arcana, History, Nature, or Religion. These skills get a prof bonus that is doubled 
                            Life:
                                Spells:
                                    bless, cure wounds
                                + prof with heavy armor
                                Disciple of Life:
                                    your healing spells are more effective. Whenever you use a spell of 1st level or higher to restore hit points 
                                        to a creature, the creature regains additional hit points equal to 2 + the spell’s level.
                            Light: 
                                Spells: 
                                    Burning hands; faerie fire
                                +1 Cantrip
                                Warding Flare: 
                                    you can interpose divine light between yourself and an attacking enemy.
                            Nature:
                                Spells:
                                    Animal Friendship, speak with animaals
                                + prof with heavy armor
                                + 1 druid cantrip
                                + prof in one of the following skills: animal handling, nature, survival
                            Tempest: 
                                Spells:
                                    fog cloud, thunderwave
                                + prof with martial weapons and heavy armor
                                Wrath of the storm:
                                    you can thunderously rebuke attackers.
                            Trickery:
                                Spells:
                                    charm person, disguise self
                                Blessing of the trickster:
                                    you can use your action to touch a willing creature other than yourself to give it advantage on Dexterity (Stealth) checks.
                            War:
                                Spells:
                                    Divine favor, shield of the faith
                                + prof with martial weapons and heavy armor
                                War Priest:
                                    your god delivers bolts of inspiration to you while you are engaged in battle. When you use the Attack action, 
                                    you can make one weapon attack as a bonus action.
                */
                break;
            case "Druid":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per druid level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per druid level after 1st 

                    Proficiencies:
                        Armor: Light armor, medium armor, shields (druids will not wear armor or use shields made of metal)
                        Weapons: Clubs, daggers, darts, javelins, maces, quarterstaffs, scimitars, sickles, slings, spears
                        Tools: Herbalism kit
                        PA: Wisdom
                        Saving Throws: Intelligence, Wisdom
                        Skills: Choose two from Arcana, Animal Handling, Insight, Medicine, Nature, Perception, Religion, and Survival

                    Equipment:
                        • (a) a w ooden shield or (b) any simple weapon
                        • (a) a scimitar or (b) any simple melee weapon
                        • Leather armor, an explorer’s pack, and a druidic focus

                    Spellcasting:
                        Cantrips: 2 from druid spell list
                        Spell slots: 2
                        SA = Wisdom
                        Spell Save DC = 8 + prof bonus + wisdom mod
                        Spell attack mod = prof bonus + wisdom mod
                        Ritual Casting
                        Spellcasting Focus
                */
                break;
            case "Fighter":
                /*
                    Hit Points:
                        Hit Dice: 1d 10 per fighter level
                        Hit Points at 1st Level: 10 + your Constitution modifier
                        Hit Points at Higher Levels: 1d 10 (or 6) + your Constitution modifier per fighter level after 1st

                    Proficiencies:
                        Armor : All armor, shields
                        Weapons: Simple weapons, martial weapons
                        Tools: None
                        PA: Strength and Dex
                        Saving Throws: Strength, Constitution
                        Skills: Choose two skills from Acrobatics, Animal Handling, Athletics, History, Insight, Intimidation, Perception, and Survival

                    Equipment:
                        • (a) chain mail or (b) leather, longbow, and 20 arrows
                        • (a) a martial weapon and a shield or (b) two martial weapons
                        • (a) a light c rossbow and 20 bolts or (b) two handaxes
                        • (a) a dungeoneer’s pack or (b) an explorer’s pack

                    Fighting Style:
                        Archery:
                            +2 to attack rolls with range weapons
                        Defense:
                            +1 to AC when wearing armor
                        Dueling:
                            +2 to damage rolls if only holding a one handed weapon
                        Great Weapon Fighting:
                            if using a 2 hander, you can reroll 1s or 2s on damage dice - must use
                        Protection:
                            When a creature you can see attacks a target other than you that is within 5 feet o f you, you can use your 
                            reaction to impose disadvantage on the attack roll. You must be wielding a shield.
                        Two-Weapon Fighting:
                            can add ability mod to the damage of the second attack

                    Second Wind: 
                        Can use bonus action to regain hit points equal to 1d10 + fighter level     - once per short or long rest
                */
                break;
            case "Monk":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per monk level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per monk level after 1st

                    Proficiencies:
                        Armor: None
                        Weapons: Simple w eapons, shortswords
                        Tools: Choose one type of artisan’s tools or one musical instrument
                        PA: Dex and Wisdom
                        Saving Throws: Strength, Dexterity
                        Skills: Choose two from Acrobatics, Athletics, History, Insight, Religion, and Stealth

                    Equipment:
                        • (a) a shortsword or (b) any simple weapon
                        • (a) a dungeoneer’s pack or (b) an explorer’s pack
                        • 10 darts

                    Unarmored Defense:
                        when not wearing armor or a shield, AC = 10 + dex mod + wisdom mod

                    Martial Arts:
                        When unarmed / using monk weapons with no shield or armor:
                            • You can use Dexterity instead of Strength for the
                                attack and damage rolls of your unarmed strikes and
                                monk weapons.
                            • You can roll a d4 in place of the normal damage
                                of your unarmed strike or monk weapon. This die
                                changes as you gain monk levels, as shown in the
                                Martial Arts column of the Monk table.
                            • When you use the Attack action with an unarmed
                                strike or a monk weapon on your turn, you can make
                                one unarmed strike as a bonus action. For example, if
                                you take the Attack action and attack with a quarterstaff,
                                you can also make an unarmed strike as a bonus
                                action, assuming you haven't already taken a bonus
                                action this turn.
                */
                break;
            case "Paladin":
                /*
                    Hit Points:
                        Hit Dice: 1d10 per paladin level
                        Hit Points at 1st Level: 10 + your Constitution modifier
                        Hit Points at Higher Levels: 1d 10 (or 6) + your Constitution modifier per paladin level after 1st

                    Proficiencies:
                        Armor: All armor, shields
                        Weapons: Simple weapons, martial weapons
                        Tools: None
                        PA: Strength and Charisma 
                        Saving Throws: Wisdom, Charisma
                        Skills: Choose two from Athletics, Insight, Intimidation, Medicine, Persuasion, and Religion

                    Equipment:
                        • (a) a martial weapon and a shield or (b) two martial weapons
                        • (a) five javelins or (b) any simple melee weapon
                        • (a) a priest’s pack or (b) an explorer’s pack
                        • Chain mail and a holy symbol

                    Divine Sense:
                        Can sense the location of any celestial, fiend, or undead within 60 feet of you that is not behind total cover. Can also detect the presence
                        of any place or object that has been consecrated or desecrated, as with the hallow spell.

                    Lay on Hands: 
                        You have a pool of healing power that replenishes when you take a long rest. With that pool, you can restore a total number of
                        hit points equal to your paladin level x 5. As an action, you can touch a creature and draw power from the pool to restore a number of hit points
                        to that creature, up to the maximum amount remaining in your pool. Alternatively, you can expend 5 hit points from your
                        pool of healing to cure the target of one disease or neutralize one poison affecting it
                */
                break;
            case "Ranger":
                /*
                    Hit Points:
                        Hit Dice: 1d 10 per ranger level
                        Hit Points at 1st Level: 10 + your Constitution modifier
                        Hit Points at Higher Levels: 1d 10 (or 6) + your Constitution modifier per ranger level after 1st

                    Proficiencies:
                        Armor: Light armor, medium armor, shields
                        Weapons: Simple weapons, martial weapons
                        Tools: None
                        PA: Dex and Wisdom
                        Saving Throws: Strength, Dexterity
                        Skills: Choose three from Animal Handling, Athletics, Insight, Investigation, Nature, Perception, Stealth, and Survival

                    Equipment:
                        • (a) scale mail or (b) leather armor
                        • (a) two shortswords or (b) two simple melee w eapons
                        • (a) a dungeoneer’s pack or (b) an explorer’s pack
                        • A longbow and a quiver o f 20 arrows

                    Favored Enemy:
                        you have significant experience studying, tracking, hunting, and even talking to a certain type of enemy.
                        Choose a type of favored enemy: 
                            aberrations, beasts, celestials, constructs, dragons, elementals, fey, fiends, giants, monstrosities, 
                            oozes, plants, or undead. Alternatively, you can select two races of humanoid (such as gnolls and orcs) as favored enemies.
                        You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence checks to recall information about them.
                        You also learn one language of your choice that is spoken by your favored enemies, if they speak one at all.
                    
                    Natural Explorer:
                        You are particularly familiar with one type o f natural environment and are adept at traveling and surviving in such regions. 
                        Choose one type of favored terrain: 
                            arctic, coast, desert, forest, grassland, mountain, swamp, or the Underdark. 
                        When you make an Intelligence or Wisdom check related to your favored terrain, your proficiency bonus is doubled if you are using a skill that
                        you’re proficient in.
                        While traveling for an hour or more in your favored terrain, you gain the following benefits:
                            • Difficult terrain doesn’t slow your group’s travel.
                            • Your group can’t become lost except by magical means.
                            • Even when you are engaged in another activity while traveling (such as foraging, navigating, or tracking), you remain alert to danger.
                            • If you are traveling alone, you can move stealthily at a normal pace.
                            • When you forage, you find twice as much food as you normally would.
                            • While tracking other creatures, you also learn their exact number, their sizes, and how long ago they passed through the area.
                */
                break;
            case "Rogue":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per rogue level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per rogue level after 1st

                    Proficiencies:
                        Armor: Light armor
                        Weapons: Simple weapons, hand crossbows, longswords, rapiers, shortswords
                        Tools: Thieves’ tools
                        PA: Dexterity
                        Saving Throws: Dexterity. Intelligence
                        Skills: Choose four from Acrobatics, Athletics, Deception. Insight, Intimidation, Investigation, Perception, Performance, 
                                                 Persuasion, Sleight of Hand, and Stealth

                    Equipment:
                        • (a) a rapier or (b) a shortsword
                        • (a) a shortbow and quiver of 20 arrows or (b) a shortsword
                        • (a) a burglar’s pack, (b) a dungeoneer’s pack, or (c) an explorer’s pack
                        • Leather armor, two daggers, and thieves’ tools

                    Expertise:
                        Choose two of your skill proficiencies, or one of your skill proficiencies and your proficiency with thieves’ tools. 
                        Your proficiency bonus is doubled for any ability check you make that uses either of the chosen proficiencies.

                    Sneak Attack:
                        You know how to strike subtly and exploit a foe’s distraction. Once per turn, you can deal an extra 1d6 damage to one creature you hit with
                        an attack if you have advantage on the attack roll. The attack must use a finesse or a ranged weapon. You don’t need advantage on the attack roll 
                        if another enemy of the target is within 5 feet of it, that enemy isn’t incapacitated, and you don’t have disadvantage on the attack roll.

                    Thieves Cant:
                        You learned thieves’ cant, a secret mix of dialect, jargon, and code that allows you to hide messages in seemingly normal conversation. Only
                        another creature that knows thieves’ cant understands such messages. It takes four times longer to convey such a message than it does to 
                        speak the same idea plainly. In addition, you understand a set of secret signs and symbols used to convey short, simple messages.
                */
                break;
            case "Sorcerer":
                /*
                    Hit Points:
                        Hit Dice: 1d6 per sorcerer level
                        Hit Points at 1st Level: 6 + your Constitution modifier
                        Hit Points at Higher Levels: 1d6 (or 4) + your Constitution modifier per sorcerer level after 1st

                    Proficiencies:
                        Armor: None
                        Weapons: Daggers, darts, slings, quarterstaffs, light crossbows
                        Tools: None
                        PA: Charisma
                        Saving Throws: Constitution, Charisma
                        Skills: Choose two from Arcana, Deception, Insight, Intimidation, Persuasion, and Religion

                    Equipment:
                        • (a) a light c rossbow and 20 bolts or (b) any simple weapon
                        • (a) a component pouch or (b) an arcane focus
                        • (a) a dungeoneer’s pack or (b) an explorer’s pack
                        • Two daggers

                    Sorcerous Origin:
                        Choose a sorcerous origin, which describes thesource of your innate magical power: Draconic Bloodline or Wild Magic.

                        Draconic Bloodline:
                            Your innate magic comes from draconic magic that was mingled with your blood or that of your ancestors.
                            Choose one type of dragon as your ancestor: 
                                DRAGON TYPE / DAMAGE TYPE
                                -------------------------
                                Black / Acid
                                Blue /  Lightning
                                Brass / Fire
                                Bronze / Lightning
                                Copper / Acid
                                Gold / Fire
                                Green / Poison
                                Red / Fire
                                Silver / Cold
                                White / Cold
                            You can speak, read, and write Draconic. Additionally, whenever you make a Charisma check when interacting with dragons, 
                            your proficiency bonus is doubled if it applies to the check.

                            Draconic Resilience:
                                your hit point maximum increases by 1 and increases by 1 again whenever you gain a level in this class. 
                                Additionally, parts o f your skin are covered by a thin sheen of dragon-like scales. When you aren’t wearing armor, 
                                your AC equals 13 + your Dexterity modifier.

                        Wild Magic:
                            your spellcasting can unleash surges of untamed magic. Immediately after you cast a sorcerer spell of 1st level or higher, 
                            the DM can have you roll a d20. If you roll a 1, roll on the Wild Magic Surge table to create a random magical effect.

                            Tides of Chaos: 
                                You can manipulate the forces of chance and chaos to gain advantage on one attack roll, ability check, or saving throw. 
                                Once you do so, you must finish a long rest before you can use this feature again.
                   */             
                break;
            case "Warlock":
                /*
                    Hit Points:
                        Hit Dice: 1d8 per warlock level
                        Hit Points at 1st Level: 8 + your Constitution modifier
                        Hit Points at Higher Levels: 1d8 (or 5) + your Constitution modifier per warlock level after 1st

                    Proficiencies:
                        Armor: Light armor
                        Weapons: Simple weapons
                        Tools: None
                        PA: Charisma
                        Saving Throws: Wisdom, Charisma
                        Skills: Choose two skills from Arcana, Deception, History, Intimidation, Investigation, Nature, and Religion

                    Equipment:
                        • (a) a light c rossbow and 20 bolts or (b) any simple weapon
                        • (a) a component pouch or (b) an arcane focus
                        • (a) a scholar’s pack or (b) a dungeoneer’s pack
                        • Leather armor, any simple w eapon, and two daggers
                    
                    OtherWorldly Patron:
                        you have struck a bargain with an otherworldly being of your choice: the Archfey, the Fiend, or the Great Old One.

                        The ArchFey
                            Spells: 
                                Faerie Fire, Sleep
                            Fey Presence: 
                                As an action, you can cause each creature in a 10-foot cube originating from you to make a Wisdom saving throw against 
                                your warlock spell save DC. The creatures that fail their saving throws are all charmed or frightened by you (your choice) 
                                until the end of your next turn.
                        The Fiend:
                            Spells:
                                burning hands, command
                            Dark One's Blessing:
                                when you reduce a hostile creature to 0 hit points, you gain temporary hit points equal to your 
                                Charisma modifier + your warlock level (minimum of 1).
                        The Great Old One: 
                            Spells:
                                dissonant whispers, Tasha's hideous laughter
                            Awakened Mind:
                                your alien knowledge gives you the ability to touch the minds of other creatures. You can communicate telepathically with any creature you
                                can see within 30 feet of you. You don’t need to share a language with the creature for it to understand your telepathic utterances, 
                                but the creature must be able to understand at least one language.

                    Pact Magic:
                        Your arcane research and the magic bestowed on you by your patron have given you facility with spells.

                        Cantrips: 2 from warlock spell list
                        Spells Known: 2 
                        Spell slots: 2
                        SA = Charisma
                        Spell Save DC = 8 + prof bonus + charisma mod
                        Spell attack mod = prof bonus + charisma mod
                        Spellcasting Focus
                */
                break;
            case "Wizard":
                /*
                    Hit Points:
                        Hit Dice: 1d6 per wizard level
                        Hit Points at 1st Level: 6 + your Constitution modifier
                        Hit Points at Higher Levels: 1d6 (or 4) + your Constitution modifier per wizard level after 1st

                    Proficiencies:
                        Armor: None
                        Weapons: Daggers, darts, slings, quarterstaffs, light crossbows
                        Tools: None
                        PA: Intelligence
                        Saving Throws: Intelligence, Wisdom
                        Skills: Choose two from Arcana, History, Insight, Investigation, Medicine, and Religion

                    Equipment:
                        • (a) a quarterstaff or (b) a dagger
                        • (a) a component pouch or (b) an arcane focus
                        • (a) a scholar’s pack or (b) an explorer’s pack
                        • A spellbook
                    
                    Spellcasting:
                        Cantrips: 3 from warlock spell list
                        Spellbook: Have a spellbook containing six 1st level wizard spells of your choice
                        Spell slots: 2
                        SA = Intelligence
                        Spell Save DC = 8 + prof bonus + Intelligence mod
                        Spell attack mod = prof bonus + Intelligence mod
                        Ritual Casting
                        Spellcasting Focus
                        Learning Spells of 1st level and higher:
                            Each time you gain a w izard level, you can add two wizard spells o f your choice to your spellbook. Each of these spells must 
                            be of a level for which you have spell slots, as shown on the Wizard table. 
                            On your adventures, you might find other spells that you can add to your spellbook.
                    
                    Arcane Recovery:
                        You have learned to regain some of your magical energy by studying your spellbook. Once per day when you finish a short rest, 
                        you can choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your wizard level 
                        (rounded up), and none of the slots can be 6th level or higher.
                */
                break;
            default:
                //should never hit default
                break;
        }
    }



    public void Race()
    {
        this.race = "";
        //store race and subrace stuff?
    }

    public void setRace(String inRace)
    {
        this.race = inRace;
    }

    public String getRace()
    {
        return this.race;
    }

    public void selectRace()
    {
        //choose random race from x races (dwarf, elf, halfling, human, dragonborn, gnome, half-elf, half-orc, tiefling)

        String[] raceArray = {"Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half-Elf", "Half-Orc", "Tiefling"};
        List<String> races = new ArrayList<String>();

        for (String i : raceArray)      //Creates an arrayList of the differenct race types - 9 types
        {
            races.add(i);
        }

        int raceNum = numGen.ints(0, 9).findFirst().getAsInt();
        int SubRaceNum;
        this.race = races.get(raceNum);     //Records race selected

        switch(this.race)
        {
            case "Dwarf":
                SubRaceNum = numGen.ints(1, 3).findFirst().getAsInt();
                if (SubRaceNum == 1)
                {
                    subRace = "Hill Dwarf";
                    this.setRace(subRace);
                    //Wisdom +1
                    //"Dwarven Toughness" = +1 hit point max, +1 every level
                }
                else
                {
                    subRace = "Mountian Dwarf";
                    //Strength +2
                    //Dwarven Armor Training = procifient with light and med armor
                }
                break;
            case "Elf":
                SubRaceNum = numGen.ints(1, 4).findFirst().getAsInt();
                if (SubRaceNum == 1)
                {
                    subRace = "High Elf";
                    //int +1
                    //"Elf Weapon Training" = prof with longsword, shortsword, shortbow, and longbow
                }
                else if (SubRaceNum == 2)
                {
                    subRace = "Wood Elf";
                    //Wis +1
                    //"Elf Weapon Training" = prof with longsword, shortsword, shortbow, and longbow
                    //"Fleet Foot" = base walking speed is 35 ft
                    //"Mask of the Wild" = You can attempt to hide even when you are only lightly obscured by foliage, heavy rain, falling snow, mist, and other natural phenomena"
                }
                else
                {
                    subRace = "Dark Elf (Drow)";
                    //Ability Score Increase. Your Charisma score increases by 1.
                    //Superior Darkvision. Your darkvision has a radius of 120 feet.
                    //Sunlight Sensitivity. You have disadvantage on attack rolls and on Wisdom (Perception) checks that rely on sight when you, 
                    //                      the target of your attack, or whatever you are trying to perceive is in direct sunlight.
                    //Drow Magic. You know the dancing lights cantrip. When you reach 3rd level, you can cast the faerie fire spell once per day. 
                    //            When you reach 5th level, you can also cast the darkness spell once per day. Charisma is your spellcasting ability for these spells.
                    //Drow Weapon Training. You have proficiency with rapiers, shortswords, and hand crossbows.

                }
                break;
            case "Halfling":
                SubRaceNum = numGen.ints(1, 3).findFirst().getAsInt();
                if (SubRaceNum == 1)
                {
                    subRace = "LightFoot";
                    //Char +1
                    //"Naturally Stealthy" = You can attempt to hide even when you are obscured only by a creature that is at least one size larger than you.
                }
                else
                {
                    subRace = "Stout";
                    //Cons +1
                    //Stout Resilience =  You have advantage on saving throws against poison, and you have resistance against poison damage.
                }
            case "Human":                                                   //Humans do not have subRaces, have ethnic groups instead - no extra traits   
                String[] ethnic = {"Calishite", "Chondathan", "Damaran", "Illuskan", "Mulan", "Rashemi", "Shou", "Tethyrian", "Turami"};
                List<String> ethnicGroup = new ArrayList<String>();

                for (String i : ethnic)      //Creates an arrayList of the differenct race types - 9 types
                {
                    ethnicGroup.add(i);
                }
                SubRaceNum = numGen.ints(0, 9).findFirst().getAsInt();
                subRace = ethnicGroup.get(SubRaceNum);                           
                break;
            case "Dragonborn":                                              //Do not have subRaces, instead have Draconic Ancestry, probably need new method
                SubRaceNum = numGen.ints(1, 11).findFirst().getAsInt(); 
                
                switch(SubRaceNum)
                {
                    case 1:
                        subRace = "Black Acid 5 by 30 ft. line (Dex. save)";
                        break;
                    case 2: 
                        subRace = "Blue Lightning 5 by 30 ft. line (Dex. save)";
                        break;
                    case 3: 
                        subRace = "Brass Fire 5 by 30 ft. line (Dex. save)";
                        break;
                    case 4:
                        subRace = "Bronze Lightning 5 by 30 ft. line (Dex. save)";
                        break;
                    case 5:
                        subRace = "Copper Acid 5 by 30 ft. line (Dex. save)";
                        break;
                    case 6: 
                        subRace = "Gold Fire 15 ft. cone (Dex. save)";
                        break;
                    case 7: 
                        subRace = "Green Poison 15 ft. cone (Con. save)";
                        break;
                    case 8:
                        subRace = "Red Fire 15 ft. cone (Dex. save)";
                        break;
                    case 9: 
                        subRace = "Silver Cold 15 ft. cone (Con. save)";
                        break;
                    case 10: 
                        subRace = "White Cold 15 ft. cone (Con. save)";
                        break;
                }
                break;
            case "Gnome":
                SubRaceNum = numGen.ints(1, 3).findFirst().getAsInt();
                if (SubRaceNum == 1)
                {
                    subRace = "Forest Gnome";
                    //Dex +1
                    //Natural Illusionist - You know the minor illusion cantrip. Intelligence is your spellcasting ability for it.
                    //Speak with Small Beasts - Through sounds and gestures, you can communicate simple ideas with Small or smaller beasts. 
                    //                          Forest gnomes love animals and often keep squirrels, badgers, rabbits, moles, woodpeckers, and other creatures as beloved pets.
                }
                else
                {
                    subRace = "Rock Gnome";
                    //Con +1
                    //Artificer’s Lore - Whenever you make an Intelligence (History) check related to magic items, alchemical objects, or technological devices, 
                    //                   you can add twice your proficiency bonus, instead o f any proficiency bonus you normally apply. 
                    /*                  
                    //Tinker -         You have proficiency with artisan’s tools (tinker’s tools). Using those tools, you can spend 1 hour and 10 gp worth of materials to 
                                       construct a Tiny clockwork device (AC 5, 1 hp). The device ceases to function after 24 hours (unless you spend 1 hour
                                       repairing it to keep the device functioning), or when you use your action to dismantle it; at that time, you can 
                                       reclaim the materials used to create it. You can have up to three such devices active at a time.
                                       When you create a device, choose one of the following options:
                                      
                                        Clockwork Toy - This toy is a clockwork animal, monster, or person, such as a frog, mouse, bird, dragon, or
                                                        soldier. When placed on the ground, the toy moves 5 feet across the ground on each of your turns in a
                                                        random direction. It makes noises as appropriate to the creature it represents.
                                        Fire Starter -  The device produces a miniature flame, which you can use to light a candle, torch, or campfire. Using the device
                                                        requires your action.
                                        Music Box -     When opened, this music box plays a single song at a moderate volume. The box stops playing when it
                                                        reaches the song’s end or when it is closed.
                    */
                }
                break;
            case "Half-Elf":    //Half-Elves do not have subRaces
                subRace = "None";
                break;
            case "Half-Orc":    //Half-Orcs do not have subRaces
                subRace = "None";
                break;
            case "Tiefling":    //While Tieflings can have different infernal bloodlines, they do not have subraces
                subRace = "None";
                break;
            default:
                //should never hit default
                break;
        }
    }

    public void printCharacter()
    {
        System.out.println( "Name: " /* + method for getting name*/);
        System.out.println( "Class: " + this.getCharClass() + "\n" + 
                            "Race: " + this.getRace() + "\n" + 
                            "Ability Scores: ");
        this.printAbilityScores();

        System.out.println( "Hit Points: " + /*method for getting hp*/ "\n" + 
                            "Hit Dice: " + /*method for getting hit die*/ "\n" + 
                            "Armor Class: " + /*method for getting AC*/ "\n" + 
                            "Speed: " + /*method for getting speed*/ "\n" + 
                            "Saving Throws: " + /*method for getting saving throws*/ "\n" +          //maybe make a printStats method
                            "Skills: " + /*method for getting skills*/ "\n" + 
                            "Proficiencies: " + /*method for getting prof*/ "\n" + 
                            "Languages: " + /*method for getting languages*/ "\n" + "\n" + 
                            "Equipment: ");

        //for readability
        System.out.println( "Weapons: " + /*method for getting weapon chosen*/ "\n" + 
                            "Armor: " + /*method for getting armor*/ "\n" + 
                            "Inventory: " + /*method for getting Inventory*/ "\n" + "\n" +          //maybe make a printEquip method
                            "Personality: ");

        //for readability
        System.out.println( "Age: " + /*method for getting age*/ "\n" + 
                            "Height: " + /*method for getting height*/ "\n" +                           
                            "Weight: " + /*method for getting weight*/ "\n" + 
                            "Eye Color: " + /*method for getting eye color*/ "\n" +                     //maybe just make a printPersonality method
                            "Skin Tone: " + /*method for getting skin tone*/ "\n" +  
                            "Alignment: " + /*method for getting alignment*/ "\n" + 
                            "Personality Trait: " + /*method for getting personality trait*/ "\n" + 
                            "Ideals: " + /*method for getting ideals*/ "\n" +
                            "Bonds: " + /*method for getting bonds*/ "\n" +
                            "Flaws:" + /*method for getting flaws*/ "\n" +  
                            "Background: " /* + method for getting background*/);

        //for readability
        System.out.println("Features: " /* + method for getting features and descriptions*/);

        //then spells if i feel like it
    }
}










class diceRoller extends characterCreater
{
    //make real and fake die rolls work                     - DONE  
    //allow for quick inputs (20 = 1d20, 4 = 1d4, ect.)     - DONE
    //allow to continuously run, need to purposely exit     - DONE
    //make instant stat roller (allow for ignoring 1s)      - DONE
    

    //create an character autobuilder 


    Random numGen = new Random();
    String dieRoll;
    boolean quit = false;
    Scanner in = new Scanner(System.in);

    //constructor
    public diceRoller()
    {
        this.dieRoll = null;
    }

    //sets the dice to be rolled
    public void setRoll(String inRoll)
    {
        this.dieRoll = inRoll;
    } 

    //returns the dice to be rolled
    public String getRoll()
    {
        return dieRoll;
    }


    //checks to see if input is in correct form
    public boolean checkInput()
    {
        boolean hasX = false;
        boolean hasD = false; 
        boolean hasY = false; 

        while (hasX == false || hasD == false || hasY == false)
        {
            //reset bools incase has to ask for input again
            hasX = hasY = hasD = false;

            System.out.println("Please enter your dice roll in the form of x'd'y (EX: 3d10) OR just the dice type (4, 6, 10, 12, 20)" + 
                               "\n" + "Type 'quit' to stop rolling") ;

            String newRoll = in.next();
            System.out.println("");         //for readability
            this.setRoll(newRoll);

            //if a quick input
            if (newRoll.length() <= 2 || newRoll.charAt(0) == 'd')                  //need to make sure theres a num after 'd' if there is one
            {
                if (newRoll.charAt(0) == 'd')
                {
                    String[] shortRoll = newRoll.split("d");
                    newRoll = "1d";
                    String die = shortRoll[1];
                    newRoll = newRoll.concat(die);
                    this.setRoll(newRoll);
                }
                else
                {
                    String die = newRoll;
                    newRoll = "1d".concat(die);
                    this.setRoll(newRoll);
                }
            }

            //beginning checks
            for (int i = 0; i < newRoll.length(); i++)
            {
                char c = newRoll.charAt(i);
                
                //if quitting
                if (newRoll.equals("quit"))
                {
                    hasD = true; hasX = true; hasY = true; quit = true;
                    i = newRoll.length();
                }
                
                //check to see if it contains a 'd' to enable it to be split apart
                if(c == 'd')
                {
                    hasD = true;
                }

                //only allowed if 'd' is already found
                if (hasD == true && !newRoll.equals("quit"))
                {
                    String[] diceNums = newRoll.split("d");

                    if (diceNums[0].matches("[0-9]+?"))
                    {
                        hasX = true;
                    }

                    if (diceNums[1].matches("[0-9]+?"))
                    {
                        hasY = true;
                    }

                    if (hasD == true && hasX == true && hasY == true)
                    {
                        i = newRoll.length();
                    }
                }
            }
        }
        
        return true;
    }







    //rolls dice
    public void roll()
    {
        if (quit != true)
        {
            String roll = this.getRoll();
            String[] stringInput = roll.split("d");
    
            int numOfRolls = Integer.valueOf(stringInput[0]);
            int diceType = Integer.valueOf(stringInput[1]);
            int total = 0;
    
            for (int i = 0; i < numOfRolls; i++)
            {
                int rollNum = numGen.ints(1, (diceType + 1)).findFirst().getAsInt();
                System.out.println("roll " + (i + 1) + ":  " + rollNum);
                total = total + rollNum;
            }
            System.out.println("");         //for readability
            System.out.println(roll + " Total = " + total);
            System.out.println("\n");         //for readability            
        }
    }








    //rolls ability scores
    public void rollAbilities()
    {
        //abilities = roll 4d6, take the highest 3

        String inString = "";
        char allowed = 'A';
        while (allowed != 'n' && allowed != 'y')        //can only move on if given yes or no
        {
            System.out.println("Rolling Ability Scores: Reroll 1s? y/n");
            inString = in.next();
            inString = inString.toLowerCase();
            if (inString.length() != 0) //no entry not accepted
            {
                allowed = inString.charAt(0);
            }
        }
        
        int roll = 0;
        if (allowed == 'n')
        {
            //allow for rolling 1s
            for (int i = 0; i < 6; i++) //until all ability scores are set (6 ability scores)
            {
                List<Integer> total = new ArrayList<>();
                int abilityScore = 0;
                for (int j = 0; j < 4; j++) //rolling 4d6
                {
                    roll = numGen.ints(1, 7).findFirst().getAsInt();
                    total.add(roll);
                }

                Collections.sort(total);
                total.remove(0);
                
                for (int j = 0; j < total.size(); j++)  //to get the actual score
                {
                    abilityScore = abilityScore + total.get(j);
                }
                System.out.println(total + " : " + abilityScore);
                super.setAbilityScores(abilityScore);
            }

        }
        else if (allowed == 'y')
        {
            for (int i = 0; i < 6; i++) //until all ability scores are set
            {
                List<Integer> total = new ArrayList<>();
                int abilityScore = 0;
                for (int j = 0; j < 4; j++) //rolling 4d6
                {
                    roll = numGen.ints(1, 7).findFirst().getAsInt();

                    while(roll == 1)    //do not allow for 1s
                    {
                        roll = numGen.ints(1, 7).findFirst().getAsInt();
                    }

                    total.add(roll);
                }

                Collections.sort(total);
                total.remove(0);
                
                for (int j = 0; j < total.size(); j++)  //to get the actual score
                {
                    abilityScore = abilityScore + total.get(j);
                }
                System.out.println(total + " : " + abilityScore);
                super.setAbilityScores(abilityScore);
            }        
        }
    }

}



class menuSelect extends diceRoller
{
    //choose function to use
    public void menu()
    {
        System.out.println("\n" + "Select Function:" + "\n" + "1: Dice Roller" + "\n" + "2: Roll ability scores" + "\n" + "3: Create randomized character" + "\n" + "4: Exit");

        Scanner in = new Scanner(System.in);
        String select = in.next();
        boolean correctSelect = false;

        while(!correctSelect)
        {
            switch(select)
            {
                case("1"):
                {
                    //go to dice roller
                    correctSelect = true;
                    System.out.println("\n");
                    while (quit != true)
                    {
                        this.checkInput();
                        this.roll();
                    }
                    break;
                }
                case("2"):
                {
                    //go to ability score roller
                    correctSelect = true;
                    System.out.println("");
                    rollAbilities();
                    printAbilityScores();
                    break;
                }
                case("3"):
                {
                    //go to character creater
                    correctSelect = true;
                    System.out.println("");
                    break;
                }
                case("4"):
                {
                    //exit
                    correctSelect = true;
                    System.out.println("");
                    break;
                }
                default:
                {
                    System.out.println("" + "Please select one of the funcitons (1, 2, 3, or 4)");
                    select = in.next();
                    break;
                }
            }
        }
        in.close();
    }
}





class demo extends menuSelect
{
    public static void main(String[] args)
    {
        //diceRoller newDice = new diceRoller();
        //characterCreater newCharacter = new characterCreater();

        //newCharacter.selectRace();

        menuSelect newMenu = new menuSelect();
        newMenu.menu();
    }
}