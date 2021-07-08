import java.text.DecimalFormat;
import java.util.*;

class characterCreater extends diceRoller
{
    //create an character autobuilder                       hold ability scores / prof in own class or method?
    //
    //      select Class
    //          roll ability scores             -DONE
    //              record                      -DONE
    //          record hit die, primary ability, saving throws, all proficiencies, skills, and equipment        -DONE
    //          if magic user, record spell save DC and spell attack modifier                                   -DONE
    //          select path (if is granted on lv 1) (add ability to choose level?)                              -DONEish (no custom level)
    //      select race                         -DONE
    //          record race traits              -DONE
    //          select subrace                  -DONE
    //              record race & subrace traits (abilitie score +s, extra proficicies, abilities, ect)         -DONE
    //          select sex and name             -DONE
    //      select Personality (Traits??)       -DONE
    //          select age and alignment        -DONE
    //          select height and weight (use table given - pg 121)                                             -DONE
    //          ?? select additional physical characteristics (hair / eye color, skin tone, scars, ect) ??      -DONE
    //          select additional languages (possibly assign sooner) (make it less likely to assign exotic languages??)     -DONE 
    //      select Background                                                                       -DONE
    //          record extra skill proficiencies, languages, and equipment                          -DONE
    //          record features                                                                     -DONE
    //          record extra feature (Favorite schemes, criminal specialty, guild business, ect)    -DONE
    //          record possible variants    !!! Not included !!!
    //      assign weapons, spells (oh god)     -DONE
    //      split classes up better (getClass(), getRace(), getProf())                              -DONE


    // change the print method for features to not need the features.get(0)
    // continue adding shit to print method

    //Simple Stats
    int hp;
    int aC;

    //Class
    charClass charClass = new charClass();

    //Race
    charRace charRace = new charRace();

    //Traits
    racialTraits traits = new racialTraits();

    //Background
    backgrounds charBackground = new backgrounds();
    
    //ability score lists
    abilityScores abilityScores = new abilityScores();
    abilityMods abilityMods = new abilityMods();
    
    //proficiencies
    proficicies charProf = new proficicies();

    //complex stats
    ArrayList<String> charLanguages = new ArrayList<String>();
    ArrayList<String> equipment = new ArrayList<String>();
    ArrayList<weapon> weapons = new ArrayList<weapon>();
    ArrayList<features> features = new ArrayList<features>();
    ArrayList<List<String>> charSpells = new ArrayList<List<String>>();
    
    Random numGen = new Random();
    diceRoller newDice = new diceRoller();



    //getters / setters for simple stats
    public int getHP()
    {
        return this.hp;
    }

    public void setHP(int inHP)
    {
        this.hp = inHP;
    }

    public int getAC()
    {
        return this.aC;
    }

    public void setAC(int inAC)
    {
        this.aC = inAC;
    }


    public void createCharacter(characterCreater inChar)
    {
        inChar.hp = 0;
        inChar.aC = 0;
        rollAbilities(inChar);      //starts the process of creating the intAbilityScores along with mods. assigning of scores is done when selecting class
        charClass.selectCharClass(inChar);
        charRace.selectRace(inChar);
        charBackground.getBackground(inChar);

        //Begin printing to screen
        System.out.println("");     //for readability
        System.out.println("----------------------------------");
        System.out.println("Race: " + inChar.charRace.getRace());
        System.out.println("Class: " + inChar.charClass.getCharClass());
        System.out.println("");             
        abilityScores.printAbilityScores(inChar);
        System.out.println("");
        charProf.printProficies();
        System.out.println("");      
        System.out.print("Languages: ");
        for (int i = 0; i < charLanguages.size(); i++)
        {
            if (i < (charLanguages.size() - 1))
            {
                System.out.print(charLanguages.get(i) + ", ");
            }
            else 
            {
                System.out.println(charLanguages.get(i) + ".");
            }
        }
        System.out.println("" + "\n");          //extra break lines cause features can be huge
        features.get(0).printFeatures(inChar);
        System.out.println(""+ "\n");  
        charBackground.printBackground();  
        System.out.println("----------------------------------");
        System.out.println(""); 

    }
    

    public void printCharacter(characterCreater inChar)
    {
        System.out.println( "Name: " );//each class get their own print method?
        System.out.println( "Class: " + inChar.charClass.getCharClass() + "\n" + 
                            "Race: " + inChar.charRace.getRace() + "\n" + 
                            "Ability Scores: ");
        inChar.abilityScores.printAbilityScores(inChar);

        System.out.println( "Hit Points: " + /*method for getting hp*/ "\n" + 
                            "Hit Dice: " + /*method for getting hit die*/ "\n" + 
                            "Armor Class: " + /*method for getting AC*/ "\n" + 
                            "Speed: " + /*method for getting speed*/ "\n" + 
                            "Saving Throws: " + /*method for getting saving throws*/ "\n" +          //maybe make a printStats method
                            "Skills: " + /*method for getting skills*/ "\n" + 
                            "Proficiencies: " + /*method for getting prof*/ "\n" + 
                            "Languages: " + /*method for getting languages*/ "\n" + "\n" + 
                            "Features: ");

        //for readability
        System.out.println( "Weapons: " + /*method for getting weapon chosen*/ "\n" + 
                            "Armor: " + /*method for getting armor*/ "\n" + 
                            "Equipment: " /*method for getting equipment*/);

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
    }
}











class charRace
{
    
    private String race;
    private String subRace;
    private String name;        //doesnt do anything else, can stay unused
    private String gender;

    public charRace()
    {
        this.race = "";
        this.subRace = "";
        this.name = "";
        this.gender = "";
    }


    public void setRace(String inRace)
    {
        this.race = inRace;
    }

    public String getRace()
    {
        return this.race;
    }

    public void selectRace(characterCreater inChar)
    {
        racialTraits traits = new racialTraits();
        
        int SubRaceNum;
        int startingWisMod = inChar.abilityMods.getAbilityMod("Wisdom");      //for if monk's unarmored defence needs changed 
        //choose random race from x races (dwarf, elf, halfling, human, dragonborn, gnome, half-elf, half-orc, tiefling)
        List<String> raceArray = Arrays.asList("Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half-Elf", "Half-Orc", "Tiefling");
        ArrayList<String> races = new ArrayList<String>();
        races.addAll(raceArray);

        int raceNum = inChar.numGen.ints(0, 9).findFirst().getAsInt();
        this.race = races.get(raceNum);     //Records race selected

        //this.race = "Elf";               //for testing

        //Set gender
        int genderNum = inChar.numGen.ints(0, 2).findFirst().getAsInt();
        if (genderNum == 0)
        {
            this.gender = "Male";
        }

        else
        {
            this.gender = "Female";
        }

        switch(this.race)
        {
            case "Dwarf": 
            {
                //Set Name
                ArrayList<String> dwarfNameList = new ArrayList<String>();
                dwarfNameList = traits.buildDwarfNameList(this.gender);
                this.name = dwarfNameList.get(inChar.numGen.ints(0, dwarfNameList.size()).findFirst().getAsInt());

                //Ability Score increase = con +2
                inChar.abilityScores.setAbilityScore("Constitution", (inChar.abilityScores.getAbilityScore("Constitution") + 2));
                inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.getAbilityScore("Constitution"));
                inChar.setHP(inChar.getHP() + 1);   //con mod increases by 1, so does hp

                //set age   - dwarfs live up to 350 years, considered adult at age 50
                int age = inChar.numGen.ints(50, 300).findFirst().getAsInt();
                inChar.traits.setAge(age);

                //set Alignment     --really this is up to the player, but dwarfs are usually lawful
                List<String> lawfulAlignments = Arrays.asList("Lawful Good", "Lawful Neutral", "Lawful Evil");
                int alignmentNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                inChar.traits.setAlignment(lawfulAlignments.get(alignmentNum));

                //set Size      --dwarfs are between 4 and 5 feet tall, weight around 150lbs, size is medium
                double heightDouble = inChar.numGen.doubles(4.0, 5.0).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(125, 176).findFirst().getAsInt();
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed     --does not decrease if wearing heavy armor
                inChar.traits.setSpeed(25);

                //Setting racial features
                    //darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("Darkvision");
                darkVision.setDesc("You have superior vision in dark and dim conditions. You can see in dim light within 60 feet of you " + "\n" + 
                                "   as if it were bright light, and in darkness as if it were dim light.");

                    //Dwarven Resilience
                features dwarvenResilience = new features();
                inChar.features.add(dwarvenResilience);
                dwarvenResilience.setName("Dwarven Resilience");
                dwarvenResilience.setDesc("You have advantage on saving throws against poison, and you have resistance against poison damage");

                    //Dwarven Combat Training
                features dwarvenCombatTraining = new features();
                inChar.features.add(dwarvenCombatTraining);
                dwarvenCombatTraining.setName("Dwarven Combat Training");
                dwarvenCombatTraining.setDesc("You have proficiency with the battleaxe, handaxe, throwing hammer, and warhammer.");
                List<String> newDwarvenProf = Arrays.asList("BattleAxe", "Handaxe", "Throwing Hammer", "Warhammer");
                inChar.charProf.weaponProf.addAll(newDwarvenProf);

                    //StoneCunning  -- no not stone"cutting"
                features stonecunning = new features();
                inChar.features.add(stonecunning);
                stonecunning.setName("Stonecunning");
                stonecunning.setDesc("Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered " + "\n" + 
                                "   proficient in the History skill and add double your proficiency bonus to the check.");

                //setting extra proficiencies 
                    //tool proficiency
                List<String> newDwarfToolProf = Arrays.asList("Smith’s tools", "Brewer’s supplies", "Mason’s tools");
                int toolNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                inChar.charProf.toolProf.add(newDwarfToolProf.get(toolNum));

                    //languages
                List<String> newLanguages = Arrays.asList("Common", "Dwarvish");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }

                /*
                 * Dwarven skin ranges from deep brown to a paler hue tinged with red, but the
                 * most common shades are light brown or deep tan, like certain tones of earth.
                 * Their hair, worn long but in simple styles, is usually black, gray, or brown,
                 * though paler dwarves often have red hair. Male dwarves value their beards
                 * highly and groom them carefully.
                 */

                //Set Skin Tone
                List<String> dwarfTones = Arrays.asList("Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan", "Pale Tan");
                String dwarfSkinTone = (dwarfTones.get(inChar.numGen.ints(0, dwarfTones.size()).findFirst().getAsInt()));
                inChar.traits.setSkinTone(dwarfSkinTone);

                //Set Hair Color
                if (dwarfSkinTone.equals("Pale Tan"))  //paler dwarves often have red hair
                {
                    inChar.traits.setHairColor("Red");
                }
                else
                {
                    List<String> dwarfHair = Arrays.asList("Black", "Grey", "Brown", "Bald");
                    inChar.traits.setHairColor(dwarfHair.get(inChar.numGen.ints(0, dwarfHair.size()).findFirst().getAsInt()));
                }

                //Set Eye Color
                inChar.traits.setEyeColor();

                SubRaceNum = inChar.numGen.ints(0, 2).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "Hill Dwarf";
                    this.setRace(subRace);

                    //Ability Score increase = wis +2
                    inChar.abilityScores.setAbilityScore("Wisdom", (inChar.abilityScores.getAbilityScore("Wisdom") + 2));
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.getAbilityScore("Wisdom"));

                    //Adding features
                        //Dwarven Toughness
                    features dwarvenToughness = new features();
                    inChar.features.add(dwarvenToughness);
                    dwarvenToughness.setName("Dwarven Toughness");
                    dwarvenToughness.setDesc("Your hit point maximum increaes by 1, and increases by 1 again every time you level");
                    inChar.setHP(inChar.getHP() + 1);
                }
                else
                {
                    subRace = "Mountian Dwarf";
                    this.setRace(subRace);

                    //Ability Score increase = str +2
                    inChar.abilityScores.setAbilityScore("Strength", (inChar.abilityScores.getAbilityScore("Strength") + 2));
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.getAbilityScore("Strength"));

                    //Adding features
                        //Dwarven Armor Training = procifient with light and med armor
                    features dwarvenArmorTraining = new features();
                    inChar.features.add(dwarvenArmorTraining);
                    dwarvenArmorTraining.setName("Dwarven Armor Training");
                    dwarvenArmorTraining.setDesc("You gain proficiency in light and medium armor");
                    
                    if (inChar.charProf.armorProf.indexOf("Light Armor") == -1) //if they are not already proficient in light armor
                    {
                        inChar.charProf.armorProf.add("Light Armor");
                    }
                    if (inChar.charProf.armorProf.indexOf("Medium Armor") == -1)
                    {
                        inChar.charProf.armorProf.add("Medium Armor");
                    }
                }
                break;
            }
            case "Elf":
            {                //Set Name
                ArrayList<String> elfNameList = new ArrayList<String>();
                elfNameList = traits.buildElfNameList(this.gender);
                this.name = elfNameList.get(inChar.numGen.ints(0, elfNameList.size()).findFirst().getAsInt());

                //Ability Score increase = dex +2
                inChar.abilityScores.setAbilityScore("Dexterity", (inChar.abilityScores.getAbilityScore("Dexterity") + 2));
                inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.getAbilityScore("Dexterity"));
                inChar.setAC(inChar.getAC() + 1);   //cause +2 dex, the mod goes up by 1, thus aC will also increase by 1

                //set age   - elves typically clain adulthood and name around the age of 100 and live to be 750 years old
                int age = inChar.numGen.ints(80, 775).findFirst().getAsInt();
                inChar.traits.setAge(age);

                //set Alignment     --really this is up to the player, but elves typically lean toward the gentler side of chaos, more often good than not, excluding drow
                List<String> gentleChaoticAlignments = Arrays.asList("Neutral Good", "Chaotic Good", "Neutral", "Chaotic Neutral");
                int alignmentNum = inChar.numGen.ints(0, 4).findFirst().getAsInt();
                inChar.traits.setAlignment(gentleChaoticAlignments.get(alignmentNum));

                //set Size      --elves range from 5 to 6 feet tall, with slender builds, size is medium
                double heightDouble = inChar.numGen.doubles(5.0, 6.0).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(90, 140).findFirst().getAsInt();    //--"Slender" guess
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed
                inChar.traits.setSpeed(30);

                //Setting racial features
                    //darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("Darkvision");
                darkVision.setDesc("You have superior vision in dark and dim conditions. You can see in dim light within 60 feet of you " + "\n" + 
                                "   as if it were bright light, and in darkness as if it were dim light.");
                
                    //Keen Senses
                features keenSenses = new features();
                inChar.features.add(keenSenses);
                keenSenses.setName("Keen Senses");
                keenSenses.setDesc("You have proficiency in the Perception Skill");
                if (inChar.charProf.skillProf.indexOf("Perception") == -1)      //if they dont already have it
                {
                    inChar.charProf.skillProf.add("Perception");
                }
                
                    //Fey Ancestry
                features feyAncestry = new features();
                inChar.features.add(feyAncestry);
                feyAncestry.setName("Fey Ancestry");
                feyAncestry.setDesc("You have advantage on saving throws against being charmed, and magic cannot put you to sleep");

                    //Trance
                features trance = new features();
                inChar.features.add(trance);
                trance.setName("Trance");
                trance.setDesc("Elves do not need to sleep. Instead they mediitate deeply, remaining semiconscious for four hours a day. After resting this way, " + "\n" + 
                                "   you gain the same benefits that a human does from eight hours of sleep.");
                
                //Adding Languages
                List<String> newLanguages = Arrays.asList("Common", "Elvish");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }
    
                /*
                * Elves’ coloration encompasses the normal human range and also includes skin
                * in shades of copper, bronze, and almost bluish-white, hair of green or blue,
                * and eyes like pools of liquid gold or silver. Elves have no facial and
                * little body hair. They favor elegant clothing in bright colors, and they
                * enjoy simple yet lovely jewelry.
                */

                //Set Skin Tone
                List<String> elfTones = Arrays.asList("Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan", "White", "Pale White", "Blueish White",
                                                        "Copper", "Bronze");
                String elfSkinTone = (elfTones.get(inChar.numGen.ints(0, elfTones.size()).findFirst().getAsInt()));
                inChar.traits.setSkinTone(elfSkinTone);

                //Set Hair Color
                List<String> elfHair = Arrays.asList("Black", "Grey", "Brown", "Blond", "Red", "Green", "Blue");    
                inChar.traits.setHairColor(elfHair.get(inChar.numGen.ints(0, elfHair.size()).findFirst().getAsInt()));

                //Set Eye Color
                ArrayList<String> elfEyeColors = traits.getEyeColors("Both");
                elfEyeColors.add("Gold");
                elfEyeColors.add("Silver");
                inChar.traits.setSpecificColor(elfEyeColors.get(inChar.numGen.ints(0, elfEyeColors.size()).findFirst().getAsInt()));

                SubRaceNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "High Elf";
                    this.setRace(subRace);

                    //Ability Score increase = int +2
                    inChar.abilityScores.setAbilityScore("Intelligence", (inChar.abilityScores.getAbilityScore("Intelligence") + 2));
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.getAbilityScore("Intelligence"));

                    //"Elf Weapon Training" = prof with longsword, shortsword, shortbow, and longbow
                    features elfWeaponTraining = new features();
                    inChar.features.add(elfWeaponTraining);
                    elfWeaponTraining.setName("Elf Weapon Training");
                    elfWeaponTraining.setDesc("You gain proficiency with the longsword, shortsword, shortbow, and longbow");
                    List<String> newElfWeaponProf = Arrays.asList("Longsword", "Shortsword", "Shortbow", "Longbow");
                    for (int i = 0; i < newElfWeaponProf.size(); i++)   //if the prof hasnt already been added
                    {
                        if (inChar.charProf.weaponProf.indexOf(newElfWeaponProf.get(i)) == -1)
                        {
                            inChar.charProf.weaponProf.add(newElfWeaponProf.get(i));
                        }
                    }

                    //extra cantrip from wizard spell list
                    spells wizardSpells = new spells();
                    ArrayList<List<String>> wizardSpellList = wizardSpells.buildWizardSpellLists();     //gets wizard spell lists
                    List<String> wizardLevelSpells = new ArrayList<String>();       //creates an empty wizard cantrip spell list to choose from
                    wizardLevelSpells.addAll(wizardSpellList.get(0));

                    int spellNum = inChar.numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt(); //pick random spell 
                    String newCantrip = wizardLevelSpells.get(spellNum);
                    try
                    {
                        inChar.charSpells.get(0).add(newCantrip);
                        if (inChar.charClass.getSpellCastingAbility() == null)
                        {
                            inChar.charClass.setSpellCastingAbility("Intelligence");
                        }
                    } catch (Exception noCantripList)
                    {
                        ArrayList<String> charCantrips = new ArrayList<String>();
                        inChar.charSpells.add(charCantrips);
                        inChar.charSpells.get(0).add(newCantrip);
                        if (inChar.charClass.getSpellCastingAbility() == null)
                        {
                            inChar.charClass.setSpellCastingAbility("Intelligence");
                        }
                    }

                    //extra langauge
                    languages newElfLanguage = new languages();
                    String newLangauge = newElfLanguage.getRandomLanguage();
                    boolean added = false;
                    while (!added)      //to make sure the new langauge isnt already in the list
                    {
                        if (inChar.charLanguages.indexOf(newLangauge) == -1)
                        {
                            inChar.charLanguages.add(newLangauge);
                            added = true;
                        }
                        else
                        {
                            newLangauge = newElfLanguage.getRandomLanguage();
                        }
                    }
                    
                }
                else if (SubRaceNum == 1)
                {
                    subRace = "Wood Elf";
                    this.setRace(subRace);

                    //Ability Score increase = Wisdom +2
                    inChar.abilityScores.setAbilityScore("Wisdom", (inChar.abilityScores.getAbilityScore("Wisdom") + 2));
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.getAbilityScore("Wisdom"));

                    //Features
                        //"Elf Weapon Training" = prof with longsword, shortsword, shortbow, and longbow
                    features elfWeaponTraining = new features();
                    inChar.features.add(elfWeaponTraining);
                    elfWeaponTraining.setName("Elf Weapon Training");
                    elfWeaponTraining.setDesc("You gain proficiency with the longsword, shortsword, shortbow, and longbow");
                    List<String> newElfWeaponProf = Arrays.asList("Longsword", "Shortsword", "Shortbow", "Longbow");
                    for (int i = 0; i < newElfWeaponProf.size(); i++)   //if the prof hasnt already been added
                    {
                        if (inChar.charProf.weaponProf.indexOf(newElfWeaponProf.get(i)) == -1)
                        {
                            inChar.charProf.weaponProf.add(newElfWeaponProf.get(i));
                        }
                    }
                    
                        //Fleat of Foot
                    features fleatOfFoot = new features();
                    inChar.features.add(fleatOfFoot);
                    fleatOfFoot.setName("Fleet of Foot");
                    fleatOfFoot.setDesc("Your base walking speed is increased to 35 feet");
                    inChar.traits.setSpeed(35);

                        //Mask of the Wild
                    features wildMask = new features();
                    inChar.features.add(wildMask);
                    wildMask.setName("Mask of the Wild");
                    wildMask.setDesc("You can attempt to hide even when you are only lightly obscured by foliage, heavy rain, falling snow, " + "\n" + 
                                        "   mist, and other natural phenomena.");
                }
                else
                {
                    subRace = "Dark Elf (Drow)";
                    this.setRace(subRace);

                    //reset alignment
                    List<String> evilChaoticAlignments = Arrays.asList("Neutral Evil", "Chaotic Evil", "Neutral", "Chaotic Neutral");
                    inChar.traits.setAlignment(evilChaoticAlignments.get(inChar.numGen.ints(0, 4).findFirst().getAsInt()));

                    //Ability Score increase = Charisma +2
                    inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 2));
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));

                    //replace darkvision with superior darkvision
                    darkVision.setName("Superior Darkvision");
                    darkVision.setDesc("You have superior vision in dark and dim conditions. You can see in dim light within 120 feet of you " + "\n" + 
                                    "   as if it were bright light, and in darkness as if it were dim light.");

                    //Drow Magic
                    features drowMagic = new features();
                    inChar.features.add(drowMagic);
                    drowMagic.setName("Drow Magic");
                    drowMagic.setDesc("You know the dancing lights cantrip. When you reach 3rd level, you can cast the faerie fire spell once per day." + "\n" +  
                                    "   When you reach 5th level, you can also cast the darkness spell once per day. Charisma is your spellcasting ability for these spells.");  
                    try
                    {
                        inChar.charSpells.get(0).add("Dancing Lights");            //might need to make sure the character does or does not already have the cantrip arraylist
                        if (inChar.charClass.getSpellCastingAbility() == null)
                        {
                            inChar.charClass.setSpellCastingAbility("Charisma");
                        }
                    }
                    catch (Exception noCantripList)
                    {
                        System.out.println("Drow did not already have spells, adding cantrip List");
                        ArrayList<String> drowCantrips = new ArrayList<String>();
                        inChar.charSpells.add(drowCantrips);
                        inChar.charSpells.get(0).add("Dancing Lights");
                        if (inChar.charClass.getSpellCastingAbility() == null)
                        {
                            inChar.charClass.setSpellCastingAbility("Charisma");
                        }
                    }
                    
                    //features
                        //sunlight Sensitivity
                    features sunlightSensitivity = new features();
                    inChar.features.add(sunlightSensitivity);
                    sunlightSensitivity.setName("Sunlight Sensitivity");
                    sunlightSensitivity.setDesc("You have disadvantage on attack rolls and on Wisdom (Perception) checks that rely on sight " + "\n" + 
                                            "   when you, the target of your attack, or whatever you are trying to perceive is in direct sunlight.");

                        //Drow weapons training - proficiency with rapiers, shortswords, and hand crossbows.
                    features drowWeaponTraining = new features();
                    inChar.features.add(drowWeaponTraining);
                    drowWeaponTraining.setName("Drow Weapon Training");
                    drowWeaponTraining.setDesc("You gain proficiency with rapiers, shortswords, and hand crossbows.");
                    List<String> newElfWeaponProf = Arrays.asList("Rapier", "Shortsword", "Hand Crossbow");
                    for (int i = 0; i < newElfWeaponProf.size(); i++)   //if the prof hasnt already been added
                    {
                        if (inChar.charProf.weaponProf.indexOf(newElfWeaponProf.get(i)) == -1)
                        {
                            inChar.charProf.weaponProf.add(newElfWeaponProf.get(i));
                        }
                    }

                    /*
                     * Also called dark elves, the drow have black skin that resembles polished
                     * obsidian and stark white or pale yellow hair. They commonly have very pale
                     * eyes (so pale as to be mistaken for white) in shades o f lilac, silver, pink,
                     * red, and blue.
                     */

                    //reset racial trait colors
                        //Skin Tone
                    inChar.traits.setSkinTone("Obsidian Black");

                        //Hair color
                    if (inChar.numGen.ints(0, 2).findFirst().getAsInt() == 0)
                    {
                        inChar.traits.setHairColor("Stark White");
                    }
                    else
                    {
                        inChar.traits.setHairColor("Pale Yellow");
                    }

                        //Eye Color
                    inChar.traits.setSpecificColor("Pale White");
                }
                break;
            }  
            case "Halfling":
            {
                //Set Name
                ArrayList<String> halflingNameList = new ArrayList<String>();
                halflingNameList = traits.buildHalflingNameList(this.gender);
                this.name = halflingNameList.get(inChar.numGen.ints(0, halflingNameList.size()).findFirst().getAsInt());

                //Ability Score increase = dex +2
                inChar.abilityScores.setAbilityScore("Dexterity", (inChar.abilityScores.getAbilityScore("Dexterity") + 2));
                inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.getAbilityScore("Dexterity"));
                inChar.setAC(inChar.getAC() + 1);   //cause +2 dex, the mod goes up by 1, thus aC will also increase by 1

                //set age - Halflings typially reach adulthood at age 20, and live until their second century
                traits.setAge(inChar.numGen.ints(20, 150).findFirst().getAsInt());

                //set Alignment - really up to player, but most halflings are lawful good
                List<String> halflingAlignments = Arrays.asList("Neutral Good", "Lawful Good", "Lawful Good");  //two elements of "Lawful good" to make it more likely chosen
                int alignmentNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                inChar.traits.setAlignment(halflingAlignments.get(alignmentNum));

                //set Size  -- halflings are about 3 feet tall and weigh about 40lbs
                double heightDouble = inChar.numGen.doubles(2.7, 3.3).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(35, 45).findFirst().getAsInt();
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed
                inChar.traits.setSpeed(25);

                //racial features
                    //Lucky
                features lucky = new features();
                inChar.features.add(lucky);
                lucky.setName("Lucky");
                lucky.setDesc("When you roll a 1 on an attack roll, ability check, or saving throw, you can reroll the die, but you must use the new roll.");

                    //Brave
                features brave = new features();
                inChar.features.add(brave);
                brave.setName("Brave");
                brave.setDesc("You have advantage on saving throws against being frightened");

                    //Halfling NimbleNess
                features halfNimble = new features();
                inChar.features.add(halfNimble);
                halfNimble.setName("Halfling NimbleNess");
                halfNimble.setDesc("You can move through the space of any creature that is of a size larger than yours.");

                //Languages
                List<String> newLanguages = Arrays.asList("Common", "Halfling");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }


                /*
                * Halflings’ skin ranges from tan to pale with a ruddy cast, and their hair is
                * usually brown or sandy brown and wavy. They have brown or hazel eyes.
                * Halfling men often sport long sideburns, but beards are rare among them and
                * mustaches even more so. They like to w ear simple, comfortable, and practical
                * clothes, favoring bright colors.
                */

                //Set Skin Tone
                List<String> halflingTones = Arrays.asList("Deep Tan", "Tan", "Light Tan", "White", "Pale White", "Ruddy White");
                String halflingSkinTone = (halflingTones.get(inChar.numGen.ints(0, halflingTones.size()).findFirst().getAsInt()));
                inChar.traits.setSkinTone(halflingSkinTone);

                //Set Hair Color
                List<String> halflingHair = Arrays.asList("Black", "Brown", "Sandy Brown", "Bald");    
                inChar.traits.setHairColor(halflingHair.get(inChar.numGen.ints(0, halflingHair.size()).findFirst().getAsInt()));

                //Set Eye Color
                List<String> halflingEyeColors = Arrays.asList("Brown", "Hazel");
                inChar.traits.setSpecificColor(halflingEyeColors.get(inChar.numGen.ints(0, halflingEyeColors.size()).findFirst().getAsInt()));

                SubRaceNum = inChar.numGen.ints(0, 2).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "LightFoot Halfling";
                    this.setRace(subRace);

                    //Ability Score increase = Charisma +2
                    inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 2));
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));

                    //Naturally Stealthy
                    features naturallyStelth = new features();
                    inChar.features.add(naturallyStelth);
                    naturallyStelth.setName("Naturally Stealthy");
                    naturallyStelth.setDesc("You can attempt to hide even when you are obscured only by a creature that is at least one size larger than you.");
                }
                else
                {
                    subRace = "Stout Halfling";
                    this.setRace(subRace);

                    //Ability Score increase = Con +2
                    inChar.abilityScores.setAbilityScore("Constitution", (inChar.abilityScores.getAbilityScore("Constitution") + 2));
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.getAbilityScore("Constitution"));
                    inChar.setHP(inChar.getHP() + 1);   //con mod increases by 1, so does hp

                    //Stout Resilience
                    features stoutResilience = new features();
                    stoutResilience.setName("Stout Resilience");
                    stoutResilience.setDesc("You have advantage on saving throws against poison, and you have resistance against poison damage.");
                }
                break;
            }
            case "Human":               //Humans do not have subRaces, have ethnic groups instead - no extra traits
            {
                int currentMod = inChar.abilityMods.getAbilityMod("Constitution");     //for checking if hp need increased
                int newMod;

                //Ability Score increase = Every ability +1
                inChar.abilityScores.setAbilityScore("Strength", (inChar.abilityScores.getAbilityScore("Strength") + 1));
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.getAbilityScore("Strength"));
                inChar.abilityScores.setAbilityScore("Constitution", (inChar.abilityScores.getAbilityScore("Constitution") + 1));
                inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.getAbilityScore("Constitution"));
                newMod = inChar.abilityMods.getAbilityMod("Constitution");
                if (currentMod < newMod)
                {
                    inChar.setHP(inChar.getHP() + 1);       //if con mod increases by 1, so does hp
                }

                currentMod = inChar.abilityMods.getAbilityMod("Dexterity"); //same thing but for AC
                inChar.abilityScores.setAbilityScore("Dexterity", (inChar.abilityScores.getAbilityScore("Dexterity") + 1));
                inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.getAbilityScore("Dexterity"));
                newMod = inChar.abilityMods.getAbilityMod("Dexterity");
                if (currentMod < newMod)
                {
                    inChar.setAC(inChar.getAC() + 1);       //if dex mod increases by 1, so does ac
                }

                inChar.abilityScores.setAbilityScore("Intelligence", (inChar.abilityScores.getAbilityScore("Intelligence") + 1));
                inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.getAbilityScore("Intelligence"));
                inChar.abilityScores.setAbilityScore("Wisdom", (inChar.abilityScores.getAbilityScore("Wisdom") + 1));
                inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.getAbilityScore("Wisdom"));
                inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 1));
                inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));

                //set age - Humans reach adulthood in their late teens and live less than a century
                inChar.traits.setAge(inChar.numGen.ints(18, 80).findFirst().getAsInt());

                //set Alignment - really up to player, but humans typically have no alignment
                List<String> allAlignments = Arrays.asList("Lawful Good", "Neutral Good", "Chaotic Good", "Neutral Good", "Neutral", "Neutral Evil", 
                                                                "Lawful Evil", "Neurtal Evil", "Chaotic Evil");
                int alignmentNum = inChar.numGen.ints(0, 9).findFirst().getAsInt();
                inChar.traits.setAlignment(allAlignments.get(alignmentNum));

                //set Size  -- Humans range from barely 5 feet to over 6 feet tall, Size is medium
                double heightDouble = inChar.numGen.doubles(4.7, 6.3).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum;                              //Since its a large range, should make dependent on hight / age ?
                if (Double.parseDouble(height) <= 5)
                {
                    weightNum = inChar.numGen.ints(90, 120).findFirst().getAsInt();            //I dunna if these weight numbers actually make sense, i guessed
                }
                else if (Double.parseDouble(height) > 5 && Double.parseDouble(height) <= 5.5)
                {
                    weightNum = inChar.numGen.ints(120, 150).findFirst().getAsInt();
                }
                else 
                {
                    weightNum = inChar.numGen.ints(150, 226).findFirst().getAsInt();
                }
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed
                inChar.traits.setSpeed(30);

                //sets languages
                    //common
                if (inChar.charLanguages.indexOf("Common") == -1)
                {
                    inChar.charLanguages.add("Common");
                }
                    //new random
                languages newHumanLanguage = new languages();
                String newLangauge = newHumanLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newHumanLanguage.getRandomLanguage();
                    }
                }


                /*
                 * There is no typical human. An individual can stand from 5 feet to a little
                 * over 6 feet tall and weigh from 125 to 250 pounds. Human skin shades range
                 * from nearly black to very pale, and hair colors from black to blond (curly,
                 * kinky, or straight); males might sport facial hair that is sparse or thick. A
                 * lot of humans have a dash o f nonhuman blood, revealing hints of elf, orc ,
                 * or other lineages.
                 * 
                 * NOTE: Maybe should make colors based on ethnicity but we're going with the idea that anyone can live anywhere
                 */


                //Set Skin Tone
                List<String> humanTones = Arrays.asList("Black", "Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan", "White", "Pale White");
                String humanSkinTone = (humanTones.get(inChar.numGen.ints(0, humanTones.size()).findFirst().getAsInt()));
                inChar.traits.setSkinTone(humanSkinTone);

                //Set Hair Color
                List<String> humanHair = Arrays.asList("Black", "Grey", "Brown", "Blond", "Red", "Bald");    
                inChar.traits.setHairColor(humanHair.get(inChar.numGen.ints(0, humanHair.size()).findFirst().getAsInt()));;

                //Set Eye Color
                inChar.traits.setEyeColor();
                
                //Disclaimer
                System.out.println("NOTE: Human Skin/Hair/Eye color not chosen based on ethnicity, chosen from a set containing all posibilities.");

                //Find Ethnicity
                List<String> ethnicArray = Arrays.asList("Calishite", "Chondathan", "Damaran", "Illuskan", "Mulan", "Rashemi", "Shou", "Tethyrian", "Turami");
                List<String> ethnicGroup = new ArrayList<String>();
                ethnicGroup.addAll(ethnicArray);
                //SubRaceNum = inChar.numGen.ints(0, ethnicGroup.size()).findFirst().getAsInt();
                subRace = ethnicGroup.get(inChar.numGen.ints(0, ethnicGroup.size()).findFirst().getAsInt());  
                
                //sets name based on ethnicity
                ArrayList<String> humanNameList = new ArrayList<String>();
                switch (subRace)
                {
                    case "Calishite":                  
                        humanNameList = traits.buildHumanNameList("Calishite", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Chondathan":
                        humanNameList = traits.buildHumanNameList("Chondathan", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Damaran":
                        humanNameList = traits.buildHumanNameList("Damaran", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Illuskan":
                        humanNameList = traits.buildHumanNameList("Illuskan", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Mulan":
                        humanNameList = traits.buildHumanNameList("Mulan", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Rashemi":
                        humanNameList = traits.buildHumanNameList("Rashemi", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Shou":
                        humanNameList = traits.buildHumanNameList("Shou", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Tethyrian":
                        humanNameList = traits.buildHumanNameList("Tethyrian", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    case "Turami":
                        humanNameList = traits.buildHumanNameList("Turami", this.gender);
                        this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                        break;
                    default: 
                        this.name = "Doug Dimmadome";   //owner of the Dimmsdale Dimmadome
                        break;
                }                         
                break;
            }   
            case "Dragonborn":          //Do not have subRaces, instead have Draconic Ancestry
            {
                //Set Name
                ArrayList<String> dragonbornNameList = new ArrayList<String>();
                dragonbornNameList = traits.buildDragonbornNameList(this.gender);
                this.name = dragonbornNameList.get(inChar.numGen.ints(0, dragonbornNameList.size()).findFirst().getAsInt());

                //Ability Score increase = str +2 & char +1
                inChar.abilityScores.setAbilityScore("Strength", (inChar.abilityScores.getAbilityScore("Strength") + 2));
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.getAbilityScore("Strength"));
                inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 1));
                inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));

                //Set age  - dragonborn reach adulthood at age 15 and live to be around 80
                inChar.traits.setAge(inChar.numGen.ints(15, 85).findFirst().getAsInt());

                //Set Alignment - really up to player, but most dragonborn tend to extremes, most are good
                List<String> dragonbornAlignments = Arrays.asList("Lawfull Good", "Lawful Good", "Lawful Evil");  //two elements of "Lawful good" to make it more likely chosen
                int alignmentNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                inChar.traits.setAlignment(dragonbornAlignments.get(alignmentNum));

                //Set Size  - Taller and heavier than humans, standing over 6ft tall and weigh ~250lbs, size is medium
                double heightDouble = inChar.numGen.doubles(6.0, 7.5).findFirst().getAsDouble();   //doesnt give the max hight, dunna if 7.5 is too much
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(230, 276).findFirst().getAsInt();
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //Set Speed
                inChar.traits.setSpeed(30);

                //racial features
                    //Draconic Ancestry
                features draconicAncestry = new features();
                inChar.features.add(draconicAncestry);
                draconicAncestry.setName("Draconic Ancestry");
                draconicAncestry.setDesc("You have draconic ancestry. Your breath weapon and damage resistance are determined by the dragon type.");

                    //Damage Resistence
                features damResistence = new features();
                inChar.features.add(damResistence);
                damResistence.setName("Damage Resistence");
                
                    //Breath Weapon
                features breathWeapon = new features();
                inChar.features.add(breathWeapon);

                //Set Languages 
                List<String> newLanguages = Arrays.asList("Common", "Draconic");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }


                /*
                 * Dragonborn look very much like dragons standing erect in humanoid form,
                 * though they lack wings or a tail. The first dragonborn had scales of vibrant
                 * hues matching the colors of their dragon kin, but generations of
                 * interbreeding have created a more uniform appearance. Their small, fine
                 * scales are usually brass or bronze in color, sometimes ranging to scarlet,
                 * rust, gold, or copper-green. They are tall and strongly built, often standing
                 * close to 6 1/2 feet tall and weighing 300 pounds or more. Their hands and
                 * feet are strong, talonlike claws with three fingers and a thumb on each hand.
                 * The blood of a particular type of dragon runs very strong through some
                 * dragonborn clans. These dragonborn often boast scales that more closely match
                 * those of their dragon ancestor—bright red, green, blue, or white, lustrous
                 * black, or gleaming metallic gold, silver, brass, copper, or bronze.
                 * 
                 * 
                 * NOTE: "skin" (really scale) tone is gunna match type of draconic ancestry (is this dragonist?) 
                 *        hair matches scaleTone, eyes set as normal
                 */

                //Set Eye Color
                inChar.traits.setEyeColor();

                SubRaceNum = inChar.numGen.ints(0, 10).findFirst().getAsInt(); 
                switch(SubRaceNum)
                {
                    case 0:
                        subRace = "Draconic Ancestry: Black Dragon";            //"Black Acid 5 by 30 ft. line (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Acid).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Acid");
                        breathWeapon.setDesc("Breathe a 5 by 30 foot line of acid. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + 
                                            "   . A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Black Scales");
                        inChar.traits.setHairColor("Black Scales");
                        break;
                    case 1: 
                        subRace = "Draconic Ancestry: Blue Dragon";             //"Blue Lightning 5 by 30 ft. line (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Lightning).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Lightning");
                        breathWeapon.setDesc("Breathe a 5 by 30 foot line of lightning. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Blue Scales");
                        inChar.traits.setHairColor("Blue Scales");
                        break;
                    case 2: 
                        subRace = "Draconic Ancestry: Brass Dragon";            //Brass Fire 5 by 30 ft. line (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Fire).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Fire");
                        breathWeapon.setDesc("Breathe a 5 by 30 foot line of fire. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Brass Scales");
                        inChar.traits.setHairColor("Brass Scales");
                        break;
                    case 3:
                        subRace = "Draconic Ancestry: Bronze Dragon";            //"Bronze Lightning 5 by 30 ft. line (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Lightning).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Lightning");
                        breathWeapon.setDesc("Breathe a 5 by 30 foot line of lightning. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Bronze Scales");
                        inChar.traits.setHairColor("Bronze Scales");                   
                        break;
                    case 4:
                        subRace = "Draconic Ancestry: Copper Dragon";           //"Copper Acid 5 by 30 ft. line (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Acid).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Acid");
                        breathWeapon.setDesc("Breathe a 5 by 30 foot line of acid. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Copper Scales");
                        inChar.traits.setHairColor("Copper Scales");
                        break;
                    case 5: 
                        subRace = "Draconic Ancestry: Gold Dragon";           //"Gold Fire 15 ft. cone (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Fire).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Fire");
                        breathWeapon.setDesc("Breathe a 15 foot cone of fire. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Gold Scales");
                        inChar.traits.setHairColor("Gold Scales");
                        break;
                    case 6: 
                        subRace = "Draconic Ancestry: Green Dragon";         //"Green Poison 15 ft. cone (Con. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Poison).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Poison");
                        breathWeapon.setDesc("Breathe a 15 foot cone of poison. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   constitution saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");
                            
                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Green Scales");
                        inChar.traits.setHairColor("Green Scales");
                        break;
                    case 7:
                        subRace = "Draconic Ancestry: Red Dragon";            //"Red Fire 15 ft. cone (Dex. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Fire).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Fire");
                        breathWeapon.setDesc("Breathe a 15 foot cone of fire. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   dexterity saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Red Scales");
                        inChar.traits.setHairColor("Red Scales");
                        break;
                    case 8: 
                        subRace = "Draconic Ancestry: Silver Dragon";          //"Silver Cold 15 ft. cone (Con. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Cold).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Cold");
                        breathWeapon.setDesc("Breathe a 15 foot cone of coldness. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   constitution saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("Silver Scales");
                        inChar.traits.setHairColor("Silver Scales");
                        break;
                    case 9: 
                        subRace = "Draconic Ancestry: White Dragon";           //"White Cold 15 ft. cone (Con. save)";

                        //sets the draconic ancestry damnage type
                        damResistence.setDesc("You have resistance to the damage type associated with your draconic ancestry (Cold).");

                        //sets the draconic ancestry breath weapon
                        breathWeapon.setName("Breath Weapon: Cold");
                        breathWeapon.setDesc("Breathe a 15 foot cone of coldness. When used each creature in the area of the exhalation must make a " + "\n" + 
                                            "   constitution saving throw. The DC for this saving throw is: " + 
                                            (8 + inChar.abilityMods.getAbilityMod("Constitution") + 2) + "." + "\n" + 
                                            "   A creature takes 2d6 damage on a failed save, and half as much on a successul one.");

                        //Draconic Ancestry tones
                        inChar.traits.setSkinTone("White Scales");
                        inChar.traits.setHairColor("White Scales");
                        break;
                }
                break;
            }
            case "Gnome":
            {
                //Set Name
                ArrayList<String> gnomeNameList = new ArrayList<String>();
                gnomeNameList = traits.buildGnomeNameList(this.gender);
                this.name = gnomeNameList.get(inChar.numGen.ints(0, gnomeNameList.size()).findFirst().getAsInt());

                //Ability Score increase = int +2
                inChar.abilityScores.setAbilityScore("Intelligence", (inChar.abilityScores.getAbilityScore("Intelligence") + 2));
                inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.getAbilityScore("Intelligence"));

                //Set age   - gnomes are considered adults at age 40 and live from 350 to 500 years 
                inChar.traits.setAge(inChar.numGen.ints(40, 476).findFirst().getAsInt());

                //Set Alignment     -really up to player, but most gnomes are often good. Range from lawful to chaotic good
                List<String> gnomeAlignments = Arrays.asList("Lawfull Good", "Neutral Good", "Chaotic Good");  //two elements of "Lawful good" to make it more likely chosen
                int alignmentNum = inChar.numGen.ints(0, 3).findFirst().getAsInt();
                inChar.traits.setAlignment(gnomeAlignments.get(alignmentNum));

                //Set Size  - gnome are between 3 and 4 feet tall and average ~40lbs. Size is small
                double heightDouble = inChar.numGen.doubles(3.0, 4.0).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(35, 45).findFirst().getAsInt();
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //Set speed
                inChar.traits.setSpeed(25);

                //Features
                    //Darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("Darkvision");
                darkVision.setDesc("Accustomed to life underground, you have superior vision in dark and dim conditions. You can see in dim light " + "\n" + 
                                "   within 60 feet of you as if it were bright light, and in darkness as if it were dim light.");

                    //Gnome Cunning
                features gnomeCunning = new features();
                inChar.features.add(gnomeCunning);
                gnomeCunning.setName("Gnome Cunning");
                gnomeCunning.setDesc("You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic.");

                //Set Languages
                List<String> newLanguages = Arrays.asList("Common", "Gnomish");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }

                


                /*
                 * A gnome’s energy and enthusiasm for living shines through every inch o f his
                 * or her tiny body. Gnomes average slightly over 3 feet tall and w eigh 40 to
                 * 45 pounds. Their tan or brown faces are usually adorned with broad smiles
                 * (beneath their prodigious noses), and their bright eyes shine with
                 * excitement. Their fair hair has a tendency to stick out in every direction,
                 * as if expressing the gnome’s insatiable interest in everything around. A
                 * gnome’s personality is writ large in his or her appearance. A male gnome’s
                 * beard, in contrast to his wild hair, is kept carefully trimmed but often
                 * styled into curious forks or neat points. A gnome’s clothing, though usually
                 * m ade in modest earth tones, is elaborately decorated with embroidery,
                 * embossing, or gleaming jewels.
                 */


                //Set Skin Tone
                List<String> gnomeTones = Arrays.asList("Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan");
                inChar.traits.setSkinTone(gnomeTones.get(inChar.numGen.ints(0, gnomeTones.size()).findFirst().getAsInt()));

                //Set Hair Color
                List<String> humanHair = Arrays.asList("Black", "Grey", "Brown", "Blond", "Red", "Green", "Bald");   
                inChar.traits.setHairColor(humanHair.get(inChar.numGen.ints(0, humanHair.size()).findFirst().getAsInt()));;

                //Set Eye Color
                inChar.traits.setEyeColor();


                SubRaceNum = inChar.numGen.ints(0, 2).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "Forest Gnome";
                    this.setRace(subRace);

                    //Ability Score increase = dex +1
                    int currentMod = inChar.abilityMods.getAbilityMod("Dexterity");
                    inChar.abilityScores.setAbilityScore("Dexterity", (inChar.abilityScores.getAbilityScore("Dexterity") + 1));
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.getAbilityScore("Dexterity"));
                    int newMod = inChar.abilityMods.getAbilityMod("Dexterity");
                    if (currentMod < newMod)
                    {
                        inChar.setAC(inChar.getAC() + 1);   //if dex mod goes up by 1, so does aC
                    }

                    //Natural Illusionist
                    features natIllusionist = new features();
                    inChar.features.add(natIllusionist);
                    natIllusionist.setName("Natural Illusionist");
                    natIllusionist.setDesc("You know the Minor Illusion cantrip. Intelligence is your spellcasting ability for it.");
                    try
                    {
                        if (inChar.charSpells.get(0).indexOf("Minor Illusiuon") == -1)     //if they dont already have it
                        {
                            inChar.charSpells.get(0).add("Minor Illusion");            //might need to make sure the character does or does not already have the cantrip arraylist
                            if (inChar.charClass.getSpellCastingAbility() == null)
                            {
                                inChar.charClass.setSpellCastingAbility("Intelligence");
                            }
                        }
                    }
                    catch (Exception noCantripList)
                    {
                        System.out.println("Forest Gnome did not already have spells, adding cantrip List" + "\n");
                        ArrayList<String> gnomeCantrips = new ArrayList<String>();
                        inChar.charSpells.add(gnomeCantrips);
                        inChar.charSpells.get(0).add("Minor Illusion");
                        if (inChar.charClass.getSpellCastingAbility() == null)
                        {
                            inChar.charClass.setSpellCastingAbility("Intelligence");
                        }
                    }

                    //Speak with small Beasts
                    features speakWithSmallBeasts = new features();
                    inChar.features.add(speakWithSmallBeasts);
                    speakWithSmallBeasts.setName("Speak With Small Beasts");
                    speakWithSmallBeasts.setDesc("Through sounds and gestures, you can communicate simple ideas with Small or smaller beasts.");
                }
                else
                {
                    subRace = "Rock Gnome";
                    this.setRace(subRace);

                    //Ability Score increase = con +1
                    int currentMod = inChar.abilityMods.getAbilityMod("Constitution");
                    inChar.abilityScores.setAbilityScore("Constitution", (inChar.abilityScores.getAbilityScore("Constitution") + 1));
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.getAbilityScore("Constitution"));
                    int newMod = inChar.abilityMods.getAbilityMod("Constitution");
                    if (currentMod < newMod)
                    {
                        inChar.setHP(inChar.getHP() + 1);   //if con mod increases by 1, so does hp
                    }

                    //Artificer's Lore
                    features artificerLore = new features();
                    inChar.features.add(artificerLore);
                    artificerLore.setName("Artificer's Lore");
                    artificerLore.setDesc("Whenever you make an Intelligence (History) check related to magic items, alchemical objects, " + "\n" + 
                                        "   or technological devices, you can add twice your proficiency bonus, instead of any " + "\n" + 
                                        "   proficiency bonus you normally apply.");

                    //Tinker
                    features tinker = new features();
                    inChar.features.add(tinker);
                    tinker.setName("Tinker");
                    tinker.setDesc("You have proficiency with artisan’s tools (tinker’s tools). Using those tools, you can spend 1 hour and 10 gp worth of " + "\n" + 
                                    "   materials to construct a Tiny clockwork device (AC 5, 1 hp). The device ceases to function after 24 hours " + "\n" + 
                                    "   (unless you spend 1 hour repairing it to keep the device functioning), or when you use your action to dismantle it; " + "\n" + 
                                    "   at that time, you can reclaim the materials used to create it. You can have up to three such devices active at a time." + "\n" + 
                                    "   When you create a device, choose one of the following options:" + "\n" + "\n" + 

                                    "   • Clockwork Toy - This toy is a clockwork animal, monster, or person, such as a frog, mouse, bird, dragon, or " + "\n" + 
                                    "           soldier. When placed on the ground, the toy moves 5 feet across the ground on each of your turns in a " + "\n" + 
                                    "           random direction. It makes noises as appropriate to the creature it represents." + "\n" + "\n" + 

                                    "   • Fire Starter -  The device produces a miniature flame, which you can use to light a candle, torch, or campfire." + "\n" +  
                                    "           Using the device requires your action." + "\n" + "\n" + 

                                    "   • Music Box - When opened, this music box plays a single song at a moderate volume. The box stops playing when it " + "\n" + 
                                    "           reaches the song’s end or when it is closed.");  
                    inChar.charProf.toolProf.add("Tinker's Tools");
                }
                break;
            }   
            case "Half-Elf":    //Half-Elves do not have subRaces
            {
                subRace = "None";

                //Set Name  - Half-Elves either take elven names if living with humans, or human names if living with elves
                if (inChar.numGen.ints(0, 2).findFirst().getAsInt() == 0)  //take human name
                {
                    ArrayList<String> humanNameList = new ArrayList<String>();
                    humanNameList = traits.buildHumanNameList("Random", this.gender);
                    this.name = humanNameList.get(inChar.numGen.ints(0, humanNameList.size()).findFirst().getAsInt());
                }
                else    //take elf name
                {
                    ArrayList<String> elfNameList = new ArrayList<String>();
                    elfNameList = traits.buildElfNameList(this.gender);
                    this.name = elfNameList.get(inChar.numGen.ints(0, elfNameList.size()).findFirst().getAsInt());
                }

                //Ability Score increase = Charisma +2 && two other ability scores +1
                inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 2));
                inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));
                List<String> halfElfAbilitsList = Arrays.asList("Strength", "Constitution", "Dexterity", "Intelligence", "Wisdom");
                ArrayList<String> potentialIncrease = new ArrayList<String>();
                potentialIncrease.addAll(halfElfAbilitsList);

                for (int i = 0; i < 2; i++)     //two other ability scores
                {
                    String abilityIncrease = potentialIncrease.get((inChar.numGen.ints(0, potentialIncrease.size()).findFirst().getAsInt()));   //choose random ability out of the 5
                    if (abilityIncrease.equals("Dextertiy"))    //if dex check to see if need to increase aC
                    {
                        int currentMod = inChar.abilityMods.getAbilityMod("Dexterity");
                        inChar.abilityScores.setAbilityScore(abilityIncrease, (inChar.abilityScores.getAbilityScore(abilityIncrease) + 1));
                        inChar.abilityMods.setAbilityMod(abilityIncrease, inChar.abilityScores.getAbilityScore(abilityIncrease));

                        int newMod = inChar.abilityMods.getAbilityMod("Dexterity");
                        if (currentMod < newMod)
                        {
                            inChar.setAC(inChar.getAC() + 1);   //if dex mod increases by 1, so does aC
                        }

                        potentialIncrease.remove(abilityIncrease); 
                    }
                    else if (abilityIncrease.equals("Constitution"))    //if con check to see if need to increase hp
                    {
                        int currentMod = inChar.abilityMods.getAbilityMod("Constitution");
                        inChar.abilityScores.setAbilityScore(abilityIncrease, (inChar.abilityScores.getAbilityScore(abilityIncrease) + 1));
                        inChar.abilityMods.setAbilityMod(abilityIncrease, inChar.abilityScores.getAbilityScore(abilityIncrease));

                        int newMod = inChar.abilityMods.getAbilityMod("Constitution");
                        if (currentMod < newMod)
                        {
                            inChar.setHP(inChar.getHP() + 1);   //if con mod increases by 1, so does aC
                        }

                        potentialIncrease.remove(abilityIncrease);
                    }
                    else
                    {
                        inChar.abilityScores.setAbilityScore(abilityIncrease, (inChar.abilityScores.getAbilityScore(abilityIncrease) + 1));                   //increase as normal
                        inChar.abilityMods.setAbilityMod(abilityIncrease, inChar.abilityScores.getAbilityScore(abilityIncrease));                             //change mod if needed
                        potentialIncrease.remove(abilityIncrease);                                                                        //remove from list so its not chosen twice
                    }
                }
                


                //Set age   - Half-Elves reach adulthood around the age of 20, and can live potentially more than 180 years
                inChar.traits.setAge(inChar.numGen.ints(20, 191).findFirst().getAsInt());
 
                //Set Alignment     -really up to player, but most half-elves share the chaotic bent of their elven heritage
                List<String> halfElfAlignments = Arrays.asList("Chaotic Good", "Chaotic Neutral", "Neutral");
                inChar.traits.setAlignment(halfElfAlignments.get(inChar.numGen.ints(0, 3).findFirst().getAsInt()));

                //Set Size  - about the same size as humans, ranging from 5 to 6 feet tall
                double heightDouble = inChar.numGen.doubles(4.7, 6.3).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum;                              //same problem as humans, should make dependent on hight / age ?
                if (Double.parseDouble(height) <= 5)
                {
                    weightNum = inChar.numGen.ints(90, 120).findFirst().getAsInt();            //again, guessed and less sure 
                }
                else if (Double.parseDouble(height) > 5 && Double.parseDouble(height) <= 5.5)
                {
                    weightNum = inChar.numGen.ints(120, 150).findFirst().getAsInt();
                }
                else 
                {
                    weightNum = inChar.numGen.ints(150, 201).findFirst().getAsInt();
                }
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //Set Speed
                inChar.traits.setSpeed(30);

                //Features
                    //Darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("DarkVision");
                darkVision.setDesc("Thanks to your elf blood, you have superior vision in dark and dim conditions. You can see in dim light within " + "\n" + 
                                    "   60 feet of you as if it were bright light, and in darkness as if it were dim light.");

                    //Fey Ancestry
                features feyAncestry = new features();
                inChar.features.add(feyAncestry);
                feyAncestry.setName("Fey Ancestry");
                feyAncestry.setDesc("You have advantage on saving throws against being charmed, and magic can’t put you to sleep.");

                //Skill Versatility - Gain proficiency in two skills of your choice
                ArrayList<String> potentialSkillsProfs = new ArrayList<String>();
                List<String> allSkillsList = Arrays.asList("Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight", 
                                                            "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", 
                                                            "Sleight of Hand", "Stealth", "Survival");      //same list as in the charClass class()
                potentialSkillsProfs.addAll(allSkillsList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = inChar.numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    if (inChar.charProf.skillProf.indexOf(newSkill) == -1)      //if the new skill is not already in the list
                    {
                        inChar.charProf.skillProf.add(newSkill);
                        potentialSkillsProfs.remove(skillNum);
                    }
                    else    //try again
                    {
                        i--;
                    }
                }

                //Set Languages     + one of your choice
                List<String> newLanguages = Arrays.asList("Common", "Elvish");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }
                    //random language
                languages newHalfelfLanguage = new languages();
                String newLangauge = newHalfelfLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newHalfelfLanguage.getRandomLanguage();
                    }
                }


                /*
                 * To humans, half-elves look like elves, and to elves, they look human. In
                 * height, they’re on par with both parents, though they’re neither as slender
                 * as elves nor as broad as humans. They range from under 5 feet to about 6 feet
                 * tall, and from 100 to 180 pounds, with men only slightly taller and heavier
                 * than women. Half-elf men do have facial hair, and sometimes grow beards to
                 * mask their elven ancestry. Half-elven coloration and features lie somewhere
                 * between their human and elf parents, and thus show a variety even more
                 * pronounced than that found among either race. They tend to have the eyes o f
                 * their elven parents.
                 * 
                 * NOTE: Just combined human and elf colors
                 */

                //Set Skin Tone
                List<String> halfelfTones = Arrays.asList("Black", "Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan", "White", "Pale White", "Blueish White",
                                                        "Copper", "Bronze");
                inChar.traits.setSkinTone(halfelfTones.get(inChar.numGen.ints(0, halfelfTones.size()).findFirst().getAsInt()));

                //Set Hair Color
                List<String> halfelfHair = Arrays.asList("Black", "Grey", "Brown", "Blond", "Red", "Green", "Blue");    
                inChar.traits.setHairColor(halfelfHair.get(inChar.numGen.ints(0, halfelfHair.size()).findFirst().getAsInt()));

                //Set Eye Color
                ArrayList<String> halfelfEyeColors = traits.getEyeColors("Both");
                halfelfEyeColors.add("Gold");
                halfelfEyeColors.add("Silver");
                inChar.traits.setSpecificColor(halfelfEyeColors.get(inChar.numGen.ints(0, halfelfEyeColors.size()).findFirst().getAsInt()));

                break;
            }
            case "Half-Orc":    //Half-Orcs do not have subRaces
            {
                subRace = "None";

                //Set Name
                ArrayList<String> orcNameList = new ArrayList<String>();
                orcNameList = traits.buildHalforcNameList(this.gender);
                this.name = orcNameList.get(inChar.numGen.ints(0, orcNameList.size()).findFirst().getAsInt());

                //Ability Score increase = str +2 & Con +1
                inChar.abilityScores.setAbilityScore("Strength", (inChar.abilityScores.getAbilityScore("Strength") + 2));
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.getAbilityScore("Strength"));
                int currentMod = inChar.abilityMods.getAbilityMod("Constitution");
                inChar.abilityScores.setAbilityScore("Constitution", (inChar.abilityScores.getAbilityScore("Constitution") + 1));
                inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.getAbilityScore("Constitution"));
                int newMod = inChar.abilityMods.getAbilityMod("Constitution");
                if (currentMod < newMod)
                {
                    inChar.setHP(inChar.getHP() + 1);   //if con mod increases by 1, so does hp
                }

                //Set age   - half orcs mature faster than humans, reaching adulthood around age 14, and rarely live longer than 75 years
                inChar.traits.setAge(inChar.numGen.ints(14, 71).findFirst().getAsInt());

                //Set Alignment     -really up to player, but half orcs are not strongly inclined toward good. Half orcs living with orcs are usually evil
                List<String> halfElfAlignments = Arrays.asList("Chaotic Neutral", "Neutral", "Neutral Evil", "Chaotic Evil");
                inChar.traits.setAlignment(halfElfAlignments.get(inChar.numGen.ints(0, 4).findFirst().getAsInt()));

                //Set Size  - half orcs are bulkier and larger than humans, range from 5 to well over 6 feet tall, size is medium
                double heightDouble = inChar.numGen.doubles(5.2, 7.1).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = inChar.numGen.ints(160, 250).findFirst().getAsInt();            //more guessing
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //Set Speed
                inChar.traits.setSpeed(30);

                //Features
                    //Darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("DarkVision");
                darkVision.setDesc("Thanks to your orc blood, you have superior vision in dark and dim conditions. You can see in dim light within " + "\n" + 
                                    "   60 feet of you as if it were bright light, and in darkness as if it were dim light.");
    
                    //Menacing
                features menacing = new features();
                inChar.features.add(menacing);
                menacing.setName("Menacing");
                menacing.setDesc("You gain proficiency in the Intimidation skill.");
                if (inChar.charProf.skillProf.indexOf("Intimidation") == -1)    //if its not already added
                {
                    inChar.charProf.skillProf.add("Intimidation");
                }

                    //Relentless Endurance
                features relentlessEndurance = new features();
                inChar.features.add(relentlessEndurance);
                relentlessEndurance.setName("Relentless Endurance");
                relentlessEndurance.setDesc("When you are reduced to 0 hit points but not killed outright, you can drop to 1 hit point instead." + "\n" + 
                                            "   You can’t use this feature again until you finish a long rest.");
                relentlessEndurance.setUses(1);

                    //Savage attacks
                features savageAttack = new features();
                inChar.features.add(savageAttack);
                savageAttack.setName("Savage Attacks");
                savageAttack.setDesc("When you score a critical hit with a melee weapon attack, you can roll one of the weapon’s damage dice " + "\n" + 
                                    "   one additional time and add it to the extra damage of the critical hit.");

                //Languages
                List<String> newLanguages = Arrays.asList("Common", "Orc");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }


                /*
                 * Half-orcs’ grayish pigmentation, sloping foreheads, jutting jaws, prominent
                 * teeth, and towering builds make their orcish heritage plain for all to see.
                 * Half-orcs stand between 6 and 7 feet tall and usually weigh between 180 and
                 * 250 pounds. Orcs regard battle scars as tokens of pride and ornamental
                 * scars as things of beauty. Other scars, though, mark an orc or half-orc as a
                 * former slave or a disgraced exile. Any half-orc who has lived among or near
                 * orcs has scars, whether they are marks of humiliation or of pride,
                 * recounting their past exploits and injuries. Such a half-orc living among
                 * humans might display these scars proudly or hide them in shame.
                 */


                //Set Skin Tone
                List<String> halforcTones = Arrays.asList("Black - Scarred", "Grey - Scarred", "Deep Brown - Scarred");
                inChar.traits.setSkinTone(halforcTones.get(inChar.numGen.ints(0, halforcTones.size()).findFirst().getAsInt()));

                //Set Hair Color
                List<String> halforcHair = Arrays.asList("Black", "Grey", "Brown", "Dark Blue", "Dark Green", "Bald");    //feels like dark blue or green is optional
                inChar.traits.setHairColor(halforcHair.get(inChar.numGen.ints(0, halforcHair.size()).findFirst().getAsInt()));

                //Set Eye Color
                inChar.traits.setEyeColor();
                break;
            }
            case "Tiefling":    //While Tieflings can have different infernal bloodlines, they do not have subraces
            {
                subRace = "None";

                //Set Name
                ArrayList<String> tieflingNameList = new ArrayList<String>();
                tieflingNameList = traits.buildTieflingNameList(this.gender);
                this.name = tieflingNameList.get(inChar.numGen.ints(0, tieflingNameList.size()).findFirst().getAsInt());
                
                //Ability Score increase = int +1 & char +2
                inChar.abilityScores.setAbilityScore("Intelligence", (inChar.abilityScores.getAbilityScore("Intelligence") + 1));
                inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.getAbilityScore("Intelligence"));
                inChar.abilityScores.setAbilityScore("Charisma", (inChar.abilityScores.getAbilityScore("Charisma") + 2));
                inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.getAbilityScore("Charisma"));

                //Set age   - Tieflings mature at the same rate as humans but live a few years longer
                inChar.traits.setAge(inChar.numGen.ints(18, 91).findFirst().getAsInt());

                //Set Alignment  -tieflings do not have an innate tendency toward evil, but many end up there. their independent nature inclines many toward chaos
                List<String> halfElfAlignments = Arrays.asList("Chaotic Neutral", "Chaotic Good", "Chaotic Evil");
                inChar.traits.setAlignment(halfElfAlignments.get(inChar.numGen.ints(0, 3).findFirst().getAsInt()));

                //Set Size  - roughly the same size and build as humans. Size is medium
                double heightDouble = inChar.numGen.doubles(4.7, 6.3).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum;                              //same problem with humans
                if (Double.parseDouble(height) <= 5)
                {
                    weightNum = inChar.numGen.ints(90, 120).findFirst().getAsInt();            //more guessing
                }
                else if (Double.parseDouble(height) > 5 && Double.parseDouble(height) <= 5.5)
                {
                    weightNum = inChar.numGen.ints(120, 150).findFirst().getAsInt();
                }
                else 
                {
                    weightNum = inChar.numGen.ints(150, 226).findFirst().getAsInt();
                }
                inChar.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //Set Speed
                inChar.traits.setSpeed(30);

                //Features
                    //Darkvision
                features darkVision = new features();
                inChar.features.add(darkVision);
                darkVision.setName("DarkVision");
                darkVision.setDesc("Thanks to your infernal heritage, you have superior vision in dark and dim conditions. You can see in dim light within " + "\n" + 
                                    "   60 feet of you as if it were bright light, and in darkness as if it were dim light.");

                    //Hellish Resistance
                features hellResistance = new features();
                inChar.features.add(hellResistance);
                hellResistance.setName("Hellish Resistance");
                hellResistance.setDesc("You have resistance against fire damage");

                    //Infernal Legacy
                features infernalLegacy = new features();
                inChar.features.add(infernalLegacy);
                infernalLegacy.setName("Infernal Legacy");
                infernalLegacy.setDesc("You know the thaumaturgy cantrip. Charisma is your spell casting ability for this spell");
                try
                {
                    inChar.charSpells.get(0).add("Thaumaturgy");            //might need to make sure the character does or does not already have the cantrip arraylist
                    if (inChar.charClass.getSpellCastingAbility() == null)
                    {
                        inChar.charClass.setSpellCastingAbility("Charisma");
                    }
                }
                catch (Exception noCantripList)
                {
                    System.out.println("Tiefling did not already have spells, adding cantrip List" + "\n");
                    ArrayList<String> tieflingCantrips = new ArrayList<String>();
                    inChar.charSpells.add(tieflingCantrips);
                    inChar.charSpells.get(0).add("Thaumaturgy");
                    if (inChar.charClass.getSpellCastingAbility() == null)
                    {
                        inChar.charClass.setSpellCastingAbility("Charisma");
                    }
                }

                //Languages
                List<String> newLanguages = Arrays.asList("Common", "Infernal");
                for (int i = 0; i < newLanguages.size(); i++)
                {
                    if (inChar.charLanguages.indexOf(newLanguages.get(i)) == -1)
                    {
                        inChar.charLanguages.add(newLanguages.get(i));
                    }
                }
                


                /*
                * Tieflings are derived from human bloodlines, and in the broadest possible
                * sense, they still look human. However, their infernal heritage has left a
                * clear imprint on their appearance. Tieflings have large horns that take any of
                * a variety of shapes: some have curling horns like a ram, others have
                * straight and tall horns like a gazelle’s, and some spiral upward like an
                * antelopes’ horns. They have thick tails, four to five feet long, which lash
                * or coil around their legs when they get upset or nervous. Their canine teeth
                * are sharply pointed, and their eyes are solid colors—black, red, white,
                * silver, or gold—with no visible sclera or pupil. Their skin tones cover the
                * full range of human coloration, but also include various shades of red.
                * Their hair, cascading down from behind their horns, is usually dark, from
                * black or brown to dark red, blue, or purple.
                */

                //Set Skin Tone
                List<String> tieflingTones = Arrays.asList("Black", "Deep Brown", "Brown", "Light Brown", "Deep Tan", "Tan", "Light Tan", "White", "Pale White", 
                                                            "Dark Red", "Blood Red", "Violet", "Dark Blue", "Rose");
                inChar.traits.setSkinTone(tieflingTones.get(inChar.numGen.ints(0, tieflingTones.size()).findFirst().getAsInt()));

                //Set Hair Color
                List<String> tieflingHair = Arrays.asList("Black", "Brown", "Dark Red", "Red", "Dark Blue", "Blue", "Deep Purple", "Purple");    
                inChar.traits.setHairColor(tieflingHair.get(inChar.numGen.ints(0, tieflingHair.size()).findFirst().getAsInt()));;

                //Set Eye Color
                List<String> teiflingEyeColors = Arrays.asList("Completely Black", "Completely Red", "Completely White", "Completely Silver", "Completely Gold");
                inChar.traits.setSpecificColor(teiflingEyeColors.get(inChar.numGen.ints(0, teiflingEyeColors.size()).findFirst().getAsInt()));
                break;
            }
        }
        //check for increase in monk's unarmored defence (only one that has two mods affecting aC)
        if (inChar.charClass.getCharClass().equals("Monk"))
        {
            int newMod = inChar.abilityMods.getAbilityMod("Wisdom");
            if (startingWisMod < newMod)
            {
                inChar.setAC(inChar.getAC() + 1);
            }
        }
    }
}









class charClass
{

    //vars
    String charClass;
    String hitDie;
    String spellCastingAbility;
    String pA;              // Primary ability
    String pA2;             //if they have two pAs
    String savingThrow1;
    String savingThrow2;
    int spellSlots;
    int spellSaveDC;
    int spellAttackMod;
    boolean ritualCasting;
    boolean spellCastingFocus;

    Random numGen = new Random();

    public charClass()
    {
        this.charClass = "";
        this.hitDie = "";
        this.spellCastingAbility = "";
        this.pA = "";
        this.pA2 = "";
        this.savingThrow1 = "";
        this.savingThrow2 = "";
        this.spellSlots = 0;
        this.spellSaveDC = 0;
        this.spellAttackMod = 0;
        this.ritualCasting = false;
        this.spellCastingFocus = false;
    }

    public void setSpellCastingAbility(String inSpellCastingAbility)    //for use in charRace class
    {
        this.spellCastingAbility = inSpellCastingAbility;
    }

    public String getSpellCastingAbility()
    {
        return this.spellCastingAbility;
    }

    public void setCharClass(String inClass)
    {
        this.charClass = inClass;
    }

    public String getCharClass()
    {
        return this.charClass;
    }

    public void selectCharClass(characterCreater inChar)
    {
        List<String> armorProfList;
        List<String> weaponProfList;
        List<String> skillList;
        List<String> allInstrumentsList = Arrays.asList("Bag Pipes", "Drum", "Dulcimer", "Flute", "Lute", "Lyre", "Horn", "Pan Flute", "Shawm", "Viol");
        List<String> allArtisansToolsList = Arrays.asList("Alchemist’s supplies", "Brewer’s supplies", "Calligrapher's supplies", "Carpenter’s tools", 
                                                        "Cartographer’s tools", "Cobbler’s tools", "Cook’s utensils", "Glassblower’s tools", "Jeweler’s tools", 
                                                        "Leatherworker’s tools", "Mason’s tools", "Painter’s supplies", "Potter’s tools", "Smith’s tools", 
                                                        "Tinker’s tools", "Weaver’s tools", "Woodcarver's tools");
        List<String> allSkillsList = Arrays.asList("Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight", "Intimidation", "Investigation", 
                                                    "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand", "Stealth", "Survival");                                          
        ArrayList<String> potentialSkillsProfs;
        String gold;
        int weaponNum;
        int abilityNum;
        int rA;
        int goldNum;

        //choose random class out of available classes ()
        List<String> classArray = Arrays.asList("Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard");
        ArrayList<String> classes = new ArrayList<String>();
        classes.addAll(classArray);

        int classNum = numGen.ints(0, 12).findFirst().getAsInt();
        this.charClass = classes.get(classNum);     //Records race selected

        //this.charClass = "Monk";    //for testing

        switch(this.charClass)
        {
            case "Barbarian":
                //adds simple stats
                hitDie = "1d12";
                pA = "Strength";
                savingThrow1 = "Strength";
                savingThrow2 = "Constitution";
                //hp is a simple stat but it needs ability scores first
                                                

                //assigning primary Ability Scores / Mods
                inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

                inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 2

                inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 1

                inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0)); //only one left
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0)); 
                inChar.abilityScores.intAbilityScores.remove(0); rA--;

                //adding hp
                inChar.setHP(12 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //adding proficiencies:
                    //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Heavy Armor");
                inChar.charProf.armorProf.addAll(armorProfList);


                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons", "Martial weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);


                    //adds skill proficiencies gained from Barbarian Class
                skillList = Arrays.asList("Animal Handling", "Athletics", "Intimidation", "Nature", "Perception", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of optional skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                /*
                    previously using linked list with a potentialWeapons array, 
                    switched to if / else so every weapon isnt created every time - 06/18/2021
                */


                //adding weapons
                    //adds either a greataxe or a martial melee weapon
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();   
                if (weaponNum == 0)
                {
                    weapon barbarianNewMartialMelee = new weapon().getMartialMelee();
                    inChar.weapons.add(barbarianNewMartialMelee);
                }
                else
                {
                    weapon barbarianGreataxe = new weapon().getSpecificWeapon("Martial Melee", "Greataxe", 1);
                    inChar.weapons.add(barbarianGreataxe);
                }

                    //adds either a simple weapon or two handaxes
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (weaponNum == 0)
                {
                    weapon barbarianNewSimpleMelee = new weapon().getMelee();
                    inChar.weapons.add(barbarianNewSimpleMelee);
                }
                else
                {
                    weapon handaxe = new weapon().getSpecificWeapon("Simple Melee", "Handaxe", 2);
                    inChar.weapons.add(handaxe);
                }

                    //adds 4 javelins
                weapon javelin = new weapon().getSpecificWeapon("Simple Melee", "Javelin", 4);        
                inChar.weapons.add(javelin);



                //adding equipment
                    //adding gold
                goldNum = inChar.newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding explorers pack
                inChar.equipment.add("Explorer's Pack");


                //adding class features
                    //rage
                features rage = new features();
                rage.setName("Rage");
                rage.setDesc("While Raging, you gain +2 Damage." + "\n" + 
                            "   You can enter a rage as a bonus action. While raging, you gain the following benefits if you aren’t wearing heavy armor:" + "\n" +
                            "• You have advantage on Strength checks and Strength saving throws." + "\n" +
                            "• When you make a melee weapon attack using Strength, you gain a bonus to the damage roll that increases as you gain levels as a barbarian," + "\n" + 
                            "    as shown in the Rage Damage column of the Barbarian table." + "\n" + 
                            "• You have resistance to bludgeoning, piercing, and slashing damage.");
                rage.setUses(2);
                inChar.features.add(rage);

                    //unarmored defence
                features barbarianUnarmoredDef = new features();
                barbarianUnarmoredDef.setName("Unarmored Defence");
                barbarianUnarmoredDef.setDesc("While you are not wearing any armor, your Armor Class equals 10 + your Dexterity modifier + your Constitution modifier." + "\n" +  
                                    "   You can use a shield and still gain this benefit.");
                inChar.features.add(barbarianUnarmoredDef);
                inChar.setAC(10 + inChar.abilityMods.getAbilityMod("Dexterity") + inChar.abilityMods.getAbilityMod("Constitution"));
                break;

            case "Bard":
                //adds simple stats
                hitDie = "1d8";
                pA = "Charisma";
                savingThrow1 = "Dexterity";
                savingThrow2 = "Charisma";
                //hp is a simple stat but it needs ability scores first
                

                //assigning primary Ability Scores / Mods
                inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 2

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 1

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));   //only one left
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0)); 
                inChar.abilityScores.intAbilityScores.remove(0); rA--;


                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));    //12 + Constitution mod

                //adding proficiencies:
                    //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor");
                inChar.charProf.armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons", "Hand Crossbow", "Longsword", "Rapier", "Shortsword");
                for (int i = 0; i < weaponProfList.size(); i++)     //shouldnt be duplicate but check anyway
                {
                    if (inChar.charProf.weaponProf.indexOf(weaponProfList.get(i)) == -1)
                    {
                        inChar.charProf.weaponProf.add(weaponProfList.get(i));
                    }
                }

                    //adds instrument proficiencies
                ArrayList<String> potentialInstrumentProf = new ArrayList<String>();
                potentialInstrumentProf.addAll(allInstrumentsList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three instruments from the list of optional instruments to be proficient in
                {
                    int instrumentNum = numGen.ints(0, potentialInstrumentProf.size()).findFirst().getAsInt();
                    String newSkill = potentialInstrumentProf.get(instrumentNum);
                    inChar.charProf.instrumentProf.add(newSkill);
                    potentialInstrumentProf.remove(instrumentNum);
                }

                    //adds skill proficiencies
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(allSkillsList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //adding weapons
                    //adds either a rapier, a longsword, or a simple melee weapon
                weaponNum = numGen.ints(0, 3).findFirst().getAsInt();
                if (weaponNum == 0)
                {
                    weapon bardRapier = new weapon().getSpecificWeapon("Martial Melee", "Rapier", 1);
                    inChar.weapons.add(bardRapier);
                }
                else if (weaponNum == 1)
                {
                    weapon bardLongsword = new weapon().getSpecificWeapon("Martial Melee", "Longsword", 1);
                    inChar.weapons.add(bardLongsword);
                }
                else
                {
                    weapon bardNewSimpleMelee = new weapon().getMelee();
                    inChar.weapons.add(bardNewSimpleMelee);
                }

                    //adding dagger
                weapon dagger = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 1);
                inChar.weapons.add(dagger);


                //Adding equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding leather armor
                inChar.equipment.add("Leather Armor");
                inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));           // 11 = leather armor aC

                    //adding pack
                int equipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (equipmentNum == 0)
                {
                    inChar.equipment.add("Diplomat's Pack");
                }
                else
                {
                    inChar.equipment.add("Entertainer's Pack");
                }
                
                    //adding instrument - make it so it chooses from the proficient instrument list???
                equipmentNum = numGen.ints(0, 2).findFirst().getAsInt();                //chooses either a lute or another instrument
                int instrumentNum = numGen.ints(0, 10).findFirst().getAsInt();          //chooses possible other instrument
                if (equipmentNum == 0)
                {
                    inChar.equipment.add("Lute");
                }
                else
                {
                    inChar.equipment.add(allInstrumentsList.get(instrumentNum));
                }
                

                //adding Spells
                    //adding simple spell stats
                spellSlots = 2;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                    //creating entire bard spell list
                spells bardSpells = new spells();
                ArrayList<List<String>> bardSpellList = bardSpells.buildBardSpellLists();

                List<String> charCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charCantrips);
                List<String> bardLevelSpells = new ArrayList<String>();       //creates an empty bard cantrip spell list to choose from
                bardLevelSpells.addAll(bardSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses two spell cantrips from the list
                {
                    int spellNum = numGen.ints(0, bardLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = bardLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    bardLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                bardLevelSpells.clear();            //clear to refill with 1st level spells
                bardLevelSpells.addAll(bardSpellList.get(1));

                List<String> char_Bard1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_Bard1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, bardLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = bardLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    bardLevelSpells.remove(newSpell);
                }

                //adding Features
                features bardicInspiration = new features();
                inChar.features.add(bardicInspiration);
                bardicInspiration.setName("Bardic Inspiration");
                bardicInspiration.setDesc("You use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you." + "\n" + 
                                    "   That creature gains one Bardic Inspiration die, a d6. Once within the next 10 minutes, the creature can roll the die and add " + "\n" + 
                                    "   the number rolled to one ability check, attack roll, or saving throw it makes.");
                break;

            case "Cleric":
                //adds simple stats
                hitDie = "1d8";
                pA = "Wisdom";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Charisma";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 2

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));   //only one left
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0)); 
                inChar.abilityScores.intAbilityScores.remove(0); rA--;


                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //adding proficiencies:
                //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                inChar.charProf.armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //adds skill proficiencies
                skillList = Arrays.asList("History", "Insight", "Medicine", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //adding weapons
                    //adds either a mace or warhammer
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon in the list
                if (weaponNum == 0)
                {
                    weapon mace = new weapon().getSpecificWeapon("Simple Melee", "Mace", 1);
                    inChar.weapons.add(mace);
                }
                else
                {
                    weapon warhammer = new weapon().getSpecificWeapon("Martial Melee", "Warhammer", 1);
                    inChar.weapons.add(warhammer);
                }

                    //adding light crossbow w/ 20 bolts or any simple weapon
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon in the list
                if (weaponNum == 0)
                {
                    weapon crossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon bolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    inChar.weapons.add(crossbow);
                    inChar.weapons.add(bolts);
                }
                else
                {
                    weapon newSimpleWeapon = new weapon().getMelee();
                    inChar.weapons.add(newSimpleWeapon);                       
                }

                
                

                //Adding equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding armor
                int clericEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (clericEquipmentNum == 0)
                {
                    inChar.equipment.add("Scale Mail");
                    inChar.setAC(14 + inChar.abilityMods.getAbilityMod("Dexterity"));             // 14 = Scale Mail aC
                }
                else
                {
                    inChar.equipment.add("Leather Armor");
                    inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));            // 11 = leather armor aC
                }

                    //adding pack
                clericEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (clericEquipmentNum == 0)
                {
                    inChar.equipment.add("Priest's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }

                    //adding shield
                inChar.equipment.add("Shield");
                inChar.setAC(inChar.getAC() + 2);        //shields add +2 to aC

                    //adding Holy Symbol
                List<String> clericHolySymbol = Arrays.asList("Amulet", "Emblem", "Reliquary");
                clericEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                inChar.equipment.add(clericHolySymbol.get(clericEquipmentNum));


                //adding Spells
                spellSlots = 2;
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                spells clericSpells = new spells();
                ArrayList<List<String>> clericSpellList = clericSpells.buildClericSpellLists();

                List<String> charClericCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charClericCantrips);
                List<String> clericLevelSpells = new ArrayList<String>();       //creates an empty cleric cantrip spell list to choose from
                clericLevelSpells.addAll(clericSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 3; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, clericLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = clericLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    clericLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                clericLevelSpells.clear();            //clear to refill with 1st level spells
                clericLevelSpells.addAll(clericSpellList.get(1));

                List<String> char_Cleric1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_Cleric1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, clericLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = clericLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    clericLevelSpells.remove(newSpell);
                }


                //Adding Features
                features divineDomain = new features();
                inChar.features.add(divineDomain);
                int domainNum = numGen.ints(0, 7).findFirst().getAsInt();
                List<String> domainList = Arrays.asList("Knowledge", "Life", "Light", "Nature", "Tempest", "Trickery", "War");
                String domainName = domainList.get(domainNum);
                switch (domainName)
                {
                    case "Knowledge":
                        divineDomain.setName("Domain Name: Knowledge");
                        divineDomain.setDesc("Gain spells: Command, Identify." + "\n" + 
                                            "   Blessings of Knowledge: Learn two additional languages and skill proficiencies");
                        //Adding additional Spells
                        if (inChar.charSpells.get(1).indexOf("Command") == -1)         //check to make sure it wasnt already chosen
                        {
                            inChar.charSpells.get(1).add("Command"); 
                        }
                        inChar.charSpells.get(1).add("Identify");          //we already know which spells to add so dont need the list
                        
                        //Adding languages
                        languages newclericLanguage = new languages();
                        for (int i = 0; i < 2; i++)     //adding two
                        {
                            String newLangauge = newclericLanguage.getRandomLanguage();
                            boolean added = false;
                            while (!added)      //to make sure the new langauge isnt already in the list
                            {
                                if (inChar.charLanguages.indexOf(newLangauge) == -1)
                                {
                                    inChar.charLanguages.add(newLangauge);
                                    added = true;
                                }
                                else
                                {
                                    newLangauge = newclericLanguage.getRandomLanguage();
                                }
                            }
                        }
                        break;
                    case "Life":
                        divineDomain.setName("Domain Name: Life");
                        divineDomain.setDesc("Gain spells: Bless, Cure Wounds." + "\n" + 
                                             "  Gain Proficiency: Heavy Armor." + "\n" + 
                                            "   Disciple of Life: Your healing spells are more effective. Whenever you use a spell of 1st level or higher" + "\n" + 
                                            "   to restore hit points to a creature, the creature regains additional hit points equal to 2 + the spell's level.");
                        //Adding additional Spells 
                        if (inChar.charSpells.get(1).indexOf("Bless") == -1)   //checks to see if bless was already chosen
                        {
                            inChar.charSpells.get(1).add("Bless");  
                        }

                        if (inChar.charSpells.get(1).indexOf("Cure Wounds") == -1)   //checks to see if cure wounds was already chosen
                        {
                            inChar.charSpells.get(1).add("Cure Wounds");  
                        }

                        //adding additional Proficiencies
                        inChar.charProf.armorProf.add("Heavy Armor");
                        
                        break;
                    case "Light":
                        divineDomain.setName("Domain Name: Light");
                        divineDomain.setDesc("Gain Cantrip: Light." + "\n" +
                                            "   Gain Spells: Burning Hands, Faerie Fire." + "\n" + 
                                            "   Warding Flame: Interpose divine light between yourself and an attacking enemy. When you are attacked " +  "\n" +
                                            "       by a creature within 30 feet of you that you can see, you can use your reaction to impose disadvantage " + "\n" +
                                            "       on the attack roll.");
                        //Adding additional Spells 
                        if (inChar.charSpells.get(0).indexOf("Light") == -1)   //checks to see if the light cantrip was already chosen
                        {
                            inChar.charSpells.get(0).add("Light"); 
                        }
                        inChar.charSpells.get(1).add("Burning Hands");  
                        inChar.charSpells.get(1).add("Faerie Fire");
                        break;
                    case "Nature":
                        divineDomain.setName("Domain Name: Nature");
                        divineDomain.setDesc("Gain Proficiency: Heavy Armor." + "\n" +
                                            "   Gain Spells: Animal Friendship, Speak with Animals." + "\n" + 
                                            "   Acolyte of Nature: Gain one Druid Cantrip.");
                        //Adding additional Spells
                        inChar.charSpells.get(1).add("Animal Friendship");
                        inChar.charSpells.get(1).add("Speak with Animals");

                            //Adding druid Cantrip
                        spells druidSpells = new spells();
                        ArrayList<List<String>> druidSpellList = druidSpells.buildDruidSpellLists();
                        List<String> druidLevelSpells = new ArrayList<String>();       //creates an empty cantrip spell list to choose from
                        druidLevelSpells.addAll(druidSpellList.get(0));

                        int cantripNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                        String druidSpell = druidLevelSpells.get(cantripNum);
                        inChar.charSpells.get(0).add(druidSpell);

                        //Adding Proficiency 
                        inChar.charProf.armorProf.add("Heavy Armor");
                        break;
                    case "Tempest":
                        divineDomain.setName("Domain Name: Tempest");
                        divineDomain.setDesc("Gain Spells: Fog Cloud, Thunderwave." + "\n" + 
                                            "   Gain Proficiencies: Martial Weapons and Heavy Armor." + "\n" + 
                                            "   Wrath of the Storm: When a creature within 5 feet of you that you can see hits you with an attack, " + "\n" + 
                                            "       you can use your reaction to cause the creature to make a Dexterity saving throw." + "\n" +
                                            "       The creature takes 2d8 lightning or thunder damage (your choice) on a failed saving throw, " + "\n" +
                                            "       and half as much damage on a successful one. Regain use upon long rest.");
                        divineDomain.setUses(inChar.abilityMods.getAbilityMod("Wisdom"));
                        inChar.charSpells.get(1).add("Fog Cloud");
                        inChar.charSpells.get(1).add("Thunderwave");

                        //Adding Proficiencies
                        inChar.charProf.weaponProf.add("Martial Weapons");
                        inChar.charProf.armorProf.add("Heavy Armor");
                        break;
                    case "Trickery":
                        divineDomain.setName("Domain Name: Trickery");
                        divineDomain.setDesc("Gain Spells: Charm Person, Disguise Self." + "\n" + 
                                            "   Blessing of the Trickster: Starting when you choose this domain at 1st level, you can use your action " + "\n" + 
                                            "       to touch a willing creature other than yourself to give it advantage on Dexterity (Stealth) checks." + "\n" + 
                                            "       This blessing lasts for 1 hour or until you use this feature again.");
                        inChar.charSpells.get(1).add("Charm Person");
                        inChar.charSpells.get(1).add("Disguise Self");
                        break;
                    case "War":
                        divineDomain.setName("Domain Name: War");
                        divineDomain.setDesc("Gain Spells: Divine Favor, Shield of Faith." + "\n" + 
                                            "   Gain Proficiency: Martial Weapons and Heavy Armor." + "\n" + 
                                            "   War Priest: Your god delivers bolts of inspiration to you while you are engaged in battle." + "\n" +  
                                            "       When you use the Attack action, you can make one weapon attack as a bonus action. Regain use upon long rest.");
                        divineDomain.setUses(inChar.abilityMods.getAbilityMod("Wisdom"));

                        inChar.charSpells.get(1).add("Divine Favor");
                        if (inChar.charSpells.get(1).indexOf("Shield of Faith") == -1)     //if it wasnt previously chosen
                        {
                            inChar.charSpells.get(1).add("Shield of Faith");
                        }
                        break;
                    default:
                        //should never hit
                        break;
                }
                break;
            case "Druid":
                //adds simple stats
                hitDie = "1d8";
                pA = "Wisdom";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Intelligence";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 2

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));   //only one left
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0)); 
                inChar.abilityScores.intAbilityScores.remove(0); rA--;


                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod

                //Adding Proficiencies
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                inChar.charProf.armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Clubs", "Daggers", "Darts", "Javelins", "Maces", "Quarterstaff", "Scimitars", "Sickles", "Slings", "Spears");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //Addings tool Proficiencies
                inChar.charProf.toolProf.add("Herbalism Kit");

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Animal Handling", "Insight", "Medicine", "Nature", "Perception", "Religion", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding shield or simple weapon
                int druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (druidEquipmentNum == 0)
                {
                    inChar.equipment.add("Wooden Shield");
                    inChar.setAC(inChar.getAC() + 2);               //shields add +2 to aC
                }
                else    //if simple weapon, either melee or ranged
                {
                    druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                    if (druidEquipmentNum == 0)
                    {
                        weapon newDruidSimpleWeapon = new weapon().getMelee();
                        inChar.weapons.add(newDruidSimpleWeapon);
                    }
                    else
                    {
                        weapon newDruidSimpleWeapon = new weapon().getRanged();
                        inChar.weapons.add(newDruidSimpleWeapon);
                    }
                }

                    //adding scimitar or a simple melee waepon
                druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (druidEquipmentNum == 0)
                {
                    weapon druidScimitar = new weapon().getSpecificWeapon("Martial Melee", "Scimitar", 1);
                    inChar.weapons.add(druidScimitar);
                }
                else
                {
                    weapon newDruidSimpleWeapon = new weapon().getMelee();
                    inChar.weapons.add(newDruidSimpleWeapon);
                }

                    //leather armor and pack
                inChar.equipment.add("Leather Armor");
                inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));            // 11 = leather armor aC

                inChar.equipment.add("Explorer's Pack");

                druidEquipmentNum = numGen.ints(0, 4).findFirst().getAsInt();
                List<String> druidicFocus = Arrays.asList("Sprig of Mistletoe", "Totem", "Wooden Staff", "Yew Wand");
                inChar.equipment.add(druidicFocus.get(druidEquipmentNum));
            
                
                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                spells druidSpells = new spells();
                ArrayList<List<String>> druidSpellList = druidSpells.buildDruidSpellLists();

                List<String> charDruidCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charDruidCantrips);
                List<String> druidLevelSpells = new ArrayList<String>();       //creates an empty druid cantrip spell list to choose from
                druidLevelSpells.addAll(druidSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = druidLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    druidLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                druidLevelSpells.clear();            //clear to refill with 1st level spells
                druidLevelSpells.addAll(druidSpellList.get(1));

                List<String> char_druid1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_druid1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = druidLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    druidLevelSpells.remove(newSpell);
                }


                //Adding Features
                features druidic = new features();
                inChar.features.add(druidic);
                druidic.setName("Druidic");
                druidic.setDesc("You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden " + "\n" +
                                    "   messages. You and others who know this language automatically spot such a message.");
                break;
            case "Fighter":
                //adds simple stats
                hitDie = "1d10";
                pA = "Strength";
                savingThrow1 = "Strength";
                savingThrow2 = "Constitution";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(0)); 
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 2

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--; //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));   //only one left
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0)); 
                inChar.abilityScores.intAbilityScores.remove(0); rA--;


                //adding hp
                inChar.setHP(10 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //Adds armor proficiencies
                armorProfList = Arrays.asList("All Armor", "Shields");
                inChar.charProf.armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);


                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding chain mail or leather
                int fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    inChar.equipment.add("Chain Mail");
                    if (inChar.abilityScores.getAbilityScore("Strength") >= 13)
                    {
                        inChar.setAC(16);            // 16 = chain mail aC
                    }
                    else
                    {
                        inChar.setAC(10 + inChar.abilityMods.getAbilityMod("Dexterity"));
                    }
                }
                else    //if simple weapon, either melee or ranged
                {
                    inChar.equipment.add("Leather Armor");
                    inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));             // 11 = leather armor aC
                }

                    //adding longbow with 20 arrows
                weapon fighterLongbow = new weapon().getSpecificWeapon("Martial Ranged", "Longbow", 1);
                inChar.weapons.add(fighterLongbow);
                weapon fighterArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                inChar.weapons.add(fighterArrows);

                    //adding martial weapon w/ shield or two martial weapons.
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    weapon newFighterWeapon = new weapon().getMartialMelee();
                    inChar.weapons.add(newFighterWeapon);
                    inChar.equipment.add("Shield");
                    inChar.setAC(inChar.getAC() + 2);          //shields add +2 aC
                }
                else
                {
                    weapon newFighterWeapon = new weapon().getMartialMelee();
                    weapon newFighterWeapon1 = new weapon().getMartialMelee();
                    inChar.weapons.add(newFighterWeapon);
                    inChar.weapons.add(newFighterWeapon1);
                }

                    //adding a light crossbow w/ 20 bolts or two handaxes
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    weapon fighterCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon fighterBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    inChar.weapons.add(fighterCrossbow);
                    inChar.weapons.add(fighterBolts);
                }
                else
                {
                    weapon fighterHandaxes = new weapon().getSpecificWeapon("Simple Melee", "Handaxe", 2);
                    inChar.weapons.add(fighterHandaxes);
                }

                    //adding either a dungeoneers pack or an explorers pack
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    inChar.equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }


                //Adding features
                    //adding fighting style
                features fightingStyle = new features();
                inChar.features.add(fightingStyle);
                int fightingNum = numGen.ints(0, 6).findFirst().getAsInt();
                List<String> fightingList = Arrays.asList("Archery", "Defense", "Dueling", "Great Weapon Fighting", "Protection", "Two-Weapon Fighting");
                String styleName = fightingList.get(fightingNum);
                switch (styleName)
                {
                    case "Archery":
                        fightingStyle.setName("Archery");
                        fightingStyle.setDesc("You gain a +2 bonus to attack rolls you make with ranged weapons.");
                        break;
                    case "Defense":
                        fightingStyle.setName("Defense");
                        fightingStyle.setDesc("While you are wearing armor, you gain a +1 bonus to AC.");
                        break;
                    case "Dueling":
                        fightingStyle.setName("Dueling");
                        fightingStyle.setDesc("When you are wielding a melee weapon in one hand and no other weapons, you gain a +2 bonus to " + "\n" + 
                                                "   damage rolls with that weapon.");
                        break;
                    case "Great Weapon Fighting":
                        fightingStyle.setName("Great Weapon Fighting");
                        fightingStyle.setDesc("When you roll a 1 or 2 on a damage die for an attack you  make with a melee weapon that you " + "\n" +  
                                            "   are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2." + "\n" +  
                                            "   The weapon must have the two-handed or versatile property for you to gain this benefit.");
                        break;
                    case "Protection":
                        fightingStyle.setName("Protection");
                        fightingStyle.setDesc("When a creature you can see attacks a target other than you that is within 5 feet of you, " + "\n" + 
                                            "   you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield.");
                        break;
                    case "Two-Weapon Fighting":
                        fightingStyle.setName("Two-Weapon Fighting");
                        fightingStyle.setDesc("When you engage in two-weapon fighting, you can add your ability modifier to the damage o f the second attack.");
                        break;
                    default:
                        //should never hit
                        break;
                }

                    //adding second wind
                features secondWind = new features();
                inChar.features.add(secondWind);
                secondWind.setName("Second Wind");
                secondWind.setDesc("You have a limited well of stamina that you can draw on to protect yourself from harm." + "\n" +  
                                    "   On your turn, you can use a bonus action to regain hit points equal to 1d 10 + your fighter level.");
                break;

            case "Monk":
                //adds simple stats
                hitDie = "1d8";
                pA = "Dexterity";
                pA2 = "Wisdom";
                savingThrow1 = "Strength";
                savingThrow2 = "Dexterity";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(0));             //saving throw1
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(0));         //not randomly assigned as monk is a fighting class that needs con
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 2; ///rA = "Remaining Abilitys" that have not been asigned a num yet (2 have not been asigned)

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 1

                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //8 + Constitution mod

                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Shortsword");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //adding Tool proficiencies - either one set of artisans tools or one instrument
                int monkProfNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (monkProfNum == 0)
                {
                    monkProfNum = numGen.ints(0, 17).findFirst().getAsInt();    //17 = size of artisans tool list
                    inChar.charProf.toolProf.add(allArtisansToolsList.get(monkProfNum));
                }
                else
                {
                    monkProfNum = numGen.ints(0, 10).findFirst().getAsInt();    //10 = size of instrument list
                    inChar.charProf.instrumentProf.add(allInstrumentsList.get(monkProfNum));
                }

                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Athletics", "History", "Insight", "Religion", "Stealth");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("5d4");      //Monk does not get their rolled multiplied by 10
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding shortsword or any simple weapon
                int monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (monkEquipmentNum == 0)
                {
                    weapon monkShortsword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    inChar.weapons.add(monkShortsword);
                }
                else
                {
                    monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses either melee or ranged simple weapon
                    if (monkEquipmentNum == 0) //melee
                    {
                        weapon monkSimpleMelee = new weapon().getMelee();
                        inChar.weapons.add(monkSimpleMelee);
                    }
                    else       //ranged
                    {
                        weapon monkSimpleRanged = new weapon().getRanged();
                        inChar.weapons.add(monkSimpleRanged);
                    }
                }

                    //adding dungeoneers pack or explorers pack
                monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (monkEquipmentNum == 0)
                {
                    inChar.equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }
                
                    //adding 10 darts
                weapon darts = new weapon().getSpecificWeapon("Simple Ranged", "Dart", 10);
                inChar.weapons.add(darts);


                //adding features
                features monkUnarmoredDef = new features();
                monkUnarmoredDef.setName("Unarmored Defense");
                monkUnarmoredDef.setDesc("while you are wearing no armor and not wielding a shield, your AC equals 10 + " + "\n" + 
                                            "   your Dexterity modifier + your Wisdom modifier.");
                inChar.setAC(10 + inChar.abilityMods.getAbilityMod("Dexterity") + inChar.abilityMods.getAbilityMod("Wisdom"));
                inChar.features.add(monkUnarmoredDef);

                features martialArts = new features();
                martialArts.setName("Martial Arts");
                martialArts.setDesc("You gain the following benefits while you are unarmed or wielding only monk weapons and you aren’t wearing " + "\n" + 
                                    "armor or wielding a shield:" + "\n" + 
                                    "   • You can use Dexterity instead of Strength for the attack and damage rolls of your" + "\n" + 
                                    "       unarmed strikes and monk weapons." + "\n" + 
                                    "   • You can roll a d4 in place of the normal damage of your unarmed strike or monk weapon." + "\n" + 
                                    "       This die changes as you gain monk levels, as shown in the Martial Arts column of the Monk table." + "\n" + 
                                    "   • When you use the Attack action with an unarmed strike or a monk weapon on your turn, you can make" + "\n" + 
                                    "       one unarmed strike as a bonus action. For example, if you take the Attack action and attack with" + "\n" +
                                    "       a quarterstaff, you can also make an unarmed strike as a bonus action, assuming you haven't already" + "\n" + 
                                    "       taken a bonus action this turn.");
                inChar.features.add(martialArts);
                break;

            case "Paladin": 
                //adds simple stats
                hitDie = "1d10";
                pA = "Strength";
                pA2 = "Charisma";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Charisma";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));             //saving throw1
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(0));         //not randomly assigned as paladin is a fighting class that needs con
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 2; ///rA = "Remaining Abilitys" that have not been asigned a num yet (two have not been asigned)

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 1

                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                armorProfList = Arrays.asList("All Armor", "Shields");
                inChar.charProf.armorProf.addAll(armorProfList);;

                    //Adding skill proficiencies
                skillList = Arrays.asList("Athletics", "Insight", "Intimidation", "Medicine", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    if (inChar.charProf.skillProf.indexOf(newSkill) == -1)  //if not already in the skillProf list
                    {
                        inChar.charProf.skillProf.add(newSkill);
                        potentialSkillsProfs.remove(skillNum);
                    }
                    else
                    {
                        i--;    //try again
                    }
                }


                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding martial weapon w/ shield or two martial weapons
                int paladinWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (paladinWeaponNum == 0)
                {
                    weapon paladinNewWeapon = new weapon().getMartialMelee();
                    inChar.weapons.add(paladinNewWeapon);
                    inChar.equipment.add("Shield");
                    inChar.setAC(inChar.getAC() + 2);               //shields add +2 to aC
                }
                else
                {
                    weapon paladinNewWeapon = new weapon().getMartialMelee();
                    weapon paladinNewWeapon1 = new weapon().getMartialMelee();
                    inChar.weapons.add(paladinNewWeapon); 
                    inChar.weapons.add(paladinNewWeapon1);
                }

                    //adding five javelins or any simple melee weapon
                paladinWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (paladinWeaponNum == 0)
                {
                    weapon javelins = new weapon().getSpecificWeapon("Simple Melee", "Javelin", 5);
                    inChar.weapons.add(javelins);
                }
                else
                {
                    weapon paladinNewWeapon = new weapon().getMelee();
                    inChar.weapons.add(paladinNewWeapon);
                }

                    //adding priests pack or explorers pack
                int paladinEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which pack
                if (paladinEquipmentNum == 0)
                {
                    inChar.equipment.add("Priest's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }

                inChar.equipment.add("Chain mail");
                inChar.setAC(16);                     //chain mail aC = 16

                    //adding holy symbol
                List<String> paladinHolySymbol = Arrays.asList("Amulet", "Emblem", "Reliquary");
                paladinEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                inChar.equipment.add(paladinHolySymbol.get(paladinEquipmentNum));


                //adding features
                features divineSense = new features();
                divineSense.setName("Divine Sense");
                divineSense.setDesc("The presence of strong evil registers on your senses like a noxious odor, and powerful good rings " + "\n" + 
                        "   like heavenly music in your ears. As an action, you can open your awareness to detect such forces." + "\n" + 
                        "   Until the end of your next turn, you know the location of any celestial, fiend, or undead within " + "\n" + 
                        "   60 feet of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being " + "\n" + 
                        "   whose presence you sense, but not its identity (the vampire Count Strahd von Zarovich, for instance)." + "\n" + 
                        "   Within the same radius, you also detect the presence of any place or object that has been consecrated or " + "\n" + 
                        "   desecrated, as with the hallow spell. This feature is regained upon a long rest.");
                divineSense.setUses(1 + inChar.abilityMods.getAbilityMod("Charisma"));
                inChar.features.add(divineSense);

                features layOnHands = new features();
                layOnHands.setName("Lay On Hands");
                layOnHands.setDesc("You have a pool of healing power that replenishes when you take a long rest. With that pool, " + "\n" + 
                        "   you can restore a total number of hit points equal to your paladin level x 5." + "\n" +
                        "   As an action, you can touch a creature and draw power from the pool to restore a number of hit points " + "\n" + 
                        "   to that creature, up to the maximum amount remaining in your pool. Alternatively, you can expend " + "\n" + 
                        "   5 hit points from your pool of healing to cure the target of one disease or neutralize one poison affecting it." + "\n" +  
                        "   You can cure multiple diseases and neutralize multiple poisons with a single use of Lay on Hands, " + "\n" + 
                        "   expending hit points separately for each one.");
                inChar.features.add(layOnHands);


                //Adding spells
                spellSlots = 0;                     //while the paladin can cast spells, they cannot at level 1
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;
                break;

            case "Ranger":
                //adds simple stats
                hitDie = "1d10";
                pA = "Dexterity";
                pA2 = "Wisdom";
                savingThrow1 = "Strength";
                savingThrow2 = "Dexterity";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(0));             //saving throw1
                    inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 3; ///rA = "Remaining Abilitys" that have not been asigned a num yet (three have not been asigned)

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;    //rA = 1

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                inChar.setHP(10 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                inChar.charProf.armorProf.addAll(armorProfList);;

                    //Adding skill proficiencies
                skillList = Arrays.asList("Animal Handling", "Athletics", "Insight", "Investigation", "Nature", "Perception", "Stealth", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding scale mail or leather armor
                int rangerWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rangerWeaponNum == 0)
                {
                    inChar.equipment.add("Scale Mail");
                    if (inChar.abilityMods.getAbilityMod("Dexterity") >= 2)            //scale mail aC = 14 + Dexterity mod (max of 2)
                    {
                        inChar.setAC(14 + 2);
                    }
                    else    //if its less than 2 then its not a problem
                    {
                        inChar.setAC(14 + inChar.abilityMods.getAbilityMod("Dexterity"));
                    }
                }
                else
                {
                    inChar.equipment.add("Leather Armor");
                    inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));
                }


                    //adding two shortswords or two simple melee weapons
                rangerWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rangerWeaponNum == 0)
                {
                    weapon rangerShortsword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 2);
                    inChar.weapons.add(rangerShortsword);
                }
                else
                {
                    weapon rangerSimpleMelee = new weapon().getMelee();
                    weapon rangerSimpleMelee1 = new weapon().getMelee();
                    inChar.weapons.add(rangerSimpleMelee);
                    inChar.weapons.add(rangerSimpleMelee1);
                }

                    //adding either dungeoneers pack or explorers pack
                int rangerEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which pack
                if (rangerEquipmentNum == 0)
                {
                    inChar.equipment.add("Dundeoneer's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }

                    //adding longbow and arrows
                weapon rangerLongbow = new weapon().getSpecificWeapon("Martial Ranged", "Longbow", 1);
                weapon rangerArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                inChar.weapons.add(rangerLongbow);
                inChar.weapons.add(rangerArrows);


                //Adding features
                features favoredEnemy = new features();
                favoredEnemy.setName("Favored Enemy");

                List<String> favoredEnemyList = Arrays.asList("aberrations", "beasts", "celestials", "constructs", "dragons", "elementals", "fey", "fiends", 
                                                                "giants", "monstrosities", "oozes", "plants", "undead");
                rangerEquipmentNum = numGen.ints(0, 13).findFirst().getAsInt();   //chooses which enemy to favor
                String rangerFavoredEnemy = favoredEnemyList.get(rangerEquipmentNum);

                favoredEnemy.setDesc("You have significant experience studying, tracking, hunting, and even talking to " + rangerFavoredEnemy + "." + "\n" +
                                     "  You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence " + "\n" + 
                                     "  checks to recall information about them. When you gain this feature, you also learn one language of your " + "\n" + 
                                     "  choice that is spoken by your favored enemies, if they speak one at all." + "\n" + 
                                     "  !!!WARNING!!! No access to monster manual so this extra language is not set !!!WARNING!!!");
                inChar.features.add(favoredEnemy);

                features naturalExplorer = new features();
                naturalExplorer.setName("Natural Explorer");

                List<String> favoredEnvironmnetList = Arrays.asList("arctic", "coast", "desert", "forest", "grassland", "mountain", "swamp", "Underdark");
                rangerEquipmentNum = numGen.ints(0, 8).findFirst().getAsInt();   //chooses which environment to favor
                String favoredEnvironmnet = favoredEnvironmnetList.get(rangerEquipmentNum);

                naturalExplorer.setDesc("You are particularly familiar with the " + favoredEnvironmnet + " environment and are adept at traveling and surviving in " + "\n" +
                                        "   this region. When you make an Intelligence or Wisdom check related to your favored terrain, your " + "\n" + 
                                        "   proficiency bonus is doubled if you are using a skill that you’re proficient in. While traveling " + "\n" + 
                                        "   for an hour or more in your favored terrain, you gain the following benefits:" + "\n" + 
                                        "   • Difficult terrain doesn’t slow your group’s travel." + "\n" + 
                                        "   • Your group can’t become lost except by magical means." + "\n" + 
                                        "   • Even when you are engaged in another activity while traveling" + "\n" + 
                                        "       (such as foraging, navigating, or tracking), you remain alert to danger." + "\n" + 
                                        "   • If you are traveling alone, you can move stealthily at a normal pace." +"\n" + 
                                        "   • When you forage, you find twice as much food as you normally would." +"\n" + 
                                        "   • While tracking other creatures, you also learn their exact number, their sizes, " + "\n" + 
                                        "       and how long ago they passed through the area.");
                inChar.features.add(naturalExplorer);

                //Adding Spells
                spellSlots = 0;                     //while the ranger can cast spells, they cannot at level 1
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = false;
                break;

            case "Rogue":
                //adds simple stats
                hitDie = "1d8";
                pA = "Dexterity";
                savingThrow1 = "Dexterity";
                savingThrow2 = "Intelligence";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;    //rA = 1

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Hand Crossbow", "Longsword", "Rapier", "Shortsword");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                inChar.charProf.armorProf.add("Light Armor");

                    //adding tool proficiencies
                inChar.charProf.toolProf.add("Thieves Tools");

                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Athletics", "Deception", "Insight", "Intimidation", "Investigation", "Perception", 
                                            "Performance", "Persuasion", "Sleight of Hand", "Stealth");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 4; i++)         //Chooses four skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);

                    //adding rapier or shortsword
                int rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon rogueRapier = new weapon().getSpecificWeapon("Martial Melee", "Rapier", 1);
                    inChar.weapons.add(rogueRapier);
                }
                else
                {
                    weapon rogueShortSword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    inChar.weapons.add(rogueShortSword);
                }

                    //adding a shortbow w/ 20 arrows or a shortsword
                rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon rogueShortbow = new weapon().getSpecificWeapon("Simple Ranged", "Shortbow", 1);
                    weapon rogueArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                    inChar.weapons.add(rogueShortbow);
                    inChar.weapons.add(rogueArrows);
                }
                else
                {
                    weapon rogueShortSword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    inChar.weapons.add(rogueShortSword);
                }

                    //adding burglars pack, dungeoneers pack, or explorers pack
                int rogueEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                List<String> roguePacks = Arrays.asList("Burglar's Pack", "Dungeoneer's Pack", "Explorer's Pack");
                inChar.equipment.add(roguePacks.get(rogueEquipmentNum));

                    //adding leather armor, two daggers, and thieves tools
                inChar.equipment.add("Leather Armor");
                inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));     //leather armor aC = 11 + dex mod

                weapon rogueDaggers = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 2);
                inChar.weapons.add(rogueDaggers);

                inChar.equipment.add("Thieve's Tools");


                //Adding features
                features sneakAttack = new features();
                sneakAttack.setName("Sneak Attack");
                sneakAttack.setDesc("You know how to strike subtly and exploit a foe’s distraction. Once per turn, you can deal an extra " + "\n" + 
                            "   1d6 damage to one creature you hit with an attack if you have advantage on the attack roll. The attack " +"\n" + 
                            "   must use a finesse or a ranged weapon. You don’t need advantage on the attack roll if another enemy of " + "\n" + 
                            "   the target is within 5 feet of it, that enemy isn’t incapacitated, and you don’t have disadvantage on " + "\n" + 
                            "   the attack roll. The amount o f the extra damage increases as you gain levels in this class, as shown " + "\n" + 
                            "   in the Sneak Attack column of the Rogue table. (Currently 1d6)"); 
                inChar.features.add(sneakAttack);

                features thievesCant = new features();
                thievesCant.setName("Thieves' Cant");
                thievesCant.setDesc("During your rogue training you learned thieves’ cant, a secret mix of dialect, jargon, and code that " + "\n" + 
                            "   allows you to hide messages in seemingly normal conversation. Only another creature that knows thieves’ cant " + "\n" + 
                            "   understands such messages. It takes four times longer to convey such a message than it does to speak the same " + "\n" + 
                            "   idea plainly. In addition, you understand a set of secret signs and symbols used to convey short, simple messages, " + "\n" + 
                            "   such as whether an area is dangerous or the territory of a thieves’ guild, whether loot is nearby, or whether the " + "\n" +
                            "   people in an area are easy marks or will provide a safe house for thieves on the run.");   
                inChar.features.add(thievesCant);
                break;

            case "Sorcerer":
                //adds simple stats
                hitDie = "1d6";
                pA = "Charisma";
                savingThrow1 = "Charisma";
                savingThrow2 = "Constitution";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;    //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                inChar.setHP(6 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod

                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Daggers", "Darts", "Slings", "QuarterStaff", "Light Crossbows");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Deception", "Insight", "Intimidation", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("3d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);


                
                    //adding a crossbow w/ 20 bolts or any simple weapon
                int sorcererWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon sorcererCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon sorcererBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    inChar.weapons.add(sorcererCrossbow);
                    inChar.weapons.add(sorcererBolts);
                }
                else
                {
                    sorcererWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses if melee or ranged
                    if (sorcererWeaponNum == 0)
                    {
                        weapon sorcererSimpleMelee = new weapon().getMelee();
                        inChar.weapons.add(sorcererSimpleMelee);
                    }
                    else
                    {
                        weapon sorcererSimpleRanged = new weapon().getRanged();
                        inChar.weapons.add(sorcererSimpleRanged);
                    }
                }

                    //adds component pouch or arcane focus
                int sorcererEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (sorcererWeaponNum == 0)
                {
                    inChar.equipment.add("Component Pouch");
                }
                else
                {
                    sorcererEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> sorcererArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    inChar.equipment.add(sorcererArcaneFocus.get(sorcererEquipmentNum));
                }

                    //adding pack
                sorcererEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (sorcererEquipmentNum == 0)
                {
                    inChar.equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }

                    //adding two daggers
                weapon daggers = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 2);
                inChar.weapons.add(daggers);

                //No armor given - standard AC
                inChar.setAC(10 + inChar.abilityMods.getAbilityMod("Dexterity"));

                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;

                spells sorcererSpells = new spells();
                ArrayList<List<String>> sorcererSpellList = sorcererSpells.buildSorcererSpellLists();

                List<String> charSorcererCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charSorcererCantrips);
                List<String> sorcererLevelSpells = new ArrayList<String>();       //creates an empty sorcerer cantrip spell list to choose from
                sorcererLevelSpells.addAll(sorcererSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 4; i++)         //Chooses four cantrips from the list
                {
                    int spellNum = numGen.ints(0, sorcererLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = sorcererLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    sorcererLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                sorcererLevelSpells.clear();            //clear to refill with 1st level spells
                sorcererLevelSpells.addAll(sorcererSpellList.get(1));

                List<String> char_sorcerer1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_sorcerer1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, sorcererLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = sorcererLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    sorcererLevelSpells.remove(newSpell);
                }


                //Adding features
                features origin = new features();
                inChar.features.add(origin);
                int originNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (originNum == 0)
                {
                    origin.setName("Draconic BloodLine");
                    int draconicType = numGen.ints(0, 10).findFirst().getAsInt();   //chooses which draconic origin
                    List<String> bloodlineList = Arrays.asList("Black", "Blue", "Brass", "Bronze", "Copper", "Gold", "Green", "Red", "Silver", "White");
                    String bloodline = bloodlineList.get(draconicType);
                    String bloodlineDamage = "";
                    switch (bloodline)
                    {
                        case "Black":
                            bloodlineDamage = "Acid";
                            break;
                        case "Blue":
                            bloodlineDamage = "Lightning";
                            break;
                        case "Brass":
                            bloodlineDamage = "Fire";
                            break;
                        case "Bronze":
                            bloodlineDamage = "Lightning";
                            break;
                        case "Copper": 
                            bloodlineDamage = "Acid";
                            break;
                        case "Gold":
                            bloodlineDamage = "Fire";
                            break;
                        case "Green":
                            bloodlineDamage = "Poison";
                            break;
                        case "Red":
                            bloodlineDamage = "Fire";
                            break;
                        case "Silver":
                            bloodlineDamage = "Cold";
                            break;
                        case "White": 
                            bloodlineDamage = "Cold";      
                            break;                
                    }
                    origin.setDesc("Your innate magic comes from draconic magic that was mingled with your blood or that of your ancestors." + "\n" + 
                                    "   You can also speak, read, and write Draconic. Whenever you make a Charisma check when interacting with dragons, " + "\n" +
                                    "   your proficiency bonus is doubled." + "\n" +
                                    "Draconic Origin: " + bloodline + "\n" + 
                                    "Damage Type: " + bloodlineDamage);
                    if (inChar.charLanguages.indexOf("Draconic") == -1)     //probably not already in it but check anyway
                    {
                        inChar.charLanguages.add("Draconic");
                    }
                    
                        //adding draconic resilience
                    features draconicResilience = new features();
                    inChar.features.add(draconicResilience);
                    draconicResilience.setName("Draconic Resilience");
                    draconicResilience.setDesc("At 1st level, your hit point maximum increases by 1 and increases by 1 again whenever you gain " + "\n" + 
                                    "   a level in this class. Additionally, parts of your skin are covered by a thin sheen of dragon-like scales. " + "\n" + 
                                    "   When you aren’t wearing armor, your AC equals 13 + your Dexterity modifier.");
                    //no armor currently in inventory or equipped
                    inChar.setAC(13 + inChar.abilityMods.getAbilityMod("Dexterity"));       //13 cause resilience
                }
                else    //best feature in the game
                {
                    origin.setName("Wild Magic");
                    origin.setDesc("Your innate magic comes from the wild forces of chaos that underlie the order of creation.");

                        //Adding wild magic surge
                    features wildMagicSurge = new features();
                    inChar.features.add(wildMagicSurge);
                    wildMagicSurge.setName("Wild Magic Surge");
                    wildMagicSurge.setDesc("Your spellcasting can unleash surges of untamed magic. Immediately after you cast a sorcerer spell of 1st level " + "\n" + 
                                    "   or higher, the DM can have you roll a d20. If you roll a 1, roll on the Wild Magic Surge table to create a random " + "\n" + 
                                    "   magical effect.");

                        //Adding tides of Chaos
                    features tidesOfChaos = new features();
                    inChar.features.add(tidesOfChaos);
                    tidesOfChaos.setName("Tides Of Chaos");
                    tidesOfChaos.setDesc("You can manipulate the forces of chance and chaos to gain advantage on one attack roll, ability check, " + "\n" + 
                                    "   or saving throw. Once you do so, you must finish a long rest before you can use this feature again. OR any " + "\n" + 
                                    "   time before you regain the use of this feature, the DM can have you roll on the Wild Magic Surge table " + "\n" + 
                                    "   immediately after you cast a sorcerer spell of 1st level or higher. You then regain the use of this feature.");
                    tidesOfChaos.setUses(1);
                }
                break;

            case "Warlock":
                //adds simple stats
                hitDie = "1d8";
                pA = "Charisma";
                savingThrow1 = "Charisma";
                savingThrow2 = "Wisdom";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; //rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;

               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;    //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                inChar.setHP(8 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Deception", "History", "Intimidation", "Investigation", "Nature", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    if (inChar.charProf.skillProf.indexOf(newSkill) == -1)
                    {
                        inChar.charProf.skillProf.add(newSkill);
                        potentialSkillsProfs.remove(skillNum);
                    }
                    else
                    {
                        i--;    //try again
                    }
                }


                //Adding Equipment
                    //Adding Gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);
                
                    //adding a crossbow w/ 20 bolts or any simple weapon
                int warlockWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (warlockWeaponNum == 0)
                {
                    weapon warlockCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon warlockBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    inChar.weapons.add(warlockCrossbow);
                    inChar.weapons.add(warlockBolts);
                }
                else
                {
                    warlockWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses if melee or ranged
                    if (warlockWeaponNum == 0)
                    {
                        weapon warlockSimpleMelee = new weapon().getMelee();
                        inChar.weapons.add(warlockSimpleMelee);
                    }
                    else
                    {
                        weapon warlockSimpleRanged = new weapon().getRanged();
                        inChar.weapons.add(warlockSimpleRanged);
                    }
                }

                    //adding component pouch or arcane focus
                int warlockEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (warlockWeaponNum == 0)
                {
                    inChar.equipment.add("Component Pouch");
                }
                else
                {
                    warlockEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> warlockArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    inChar.equipment.add(warlockArcaneFocus.get(warlockEquipmentNum));
                }

                    //adding pack
                warlockEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (warlockEquipmentNum == 0)
                {
                    inChar.equipment.add("Scholar's Pack");
                }
                else
                {
                    inChar.equipment.add("Dungeoneer’s's Pack");
                }

                    //Adding leather armor, any simple weapon, and two daggers
                inChar.equipment.add("Leather Armor");
                inChar.setAC(11 + inChar.abilityMods.getAbilityMod("Dexterity"));

                weapon warlockSimpleWeapon = new weapon().getMelee();
                inChar.weapons.add(warlockSimpleWeapon);

                weapon warlockDaggers = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 2);
                inChar.weapons.add(warlockDaggers);


                //SpellCasting
                spellSlots = 1;
                int invocations = 0;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;

                spells warlockSpells = new spells();
                ArrayList<List<String>> warlockSpellList = warlockSpells.buildWarlockSpellLists();

                List<String> charWarlockCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charWarlockCantrips);
                List<String> warlockLevelSpells = new ArrayList<String>();       //creates an empty warlock cantrip spell list to choose from
                warlockLevelSpells.addAll(warlockSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses two cantrips from the list
                {
                    int spellNum = numGen.ints(0, warlockLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = warlockLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    warlockLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                warlockLevelSpells.clear();            //clear to refill with 1st level spells
                warlockLevelSpells.addAll(warlockSpellList.get(1));

                List<String> char_warlock1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_warlock1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, warlockLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = warlockLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    warlockLevelSpells.remove(newSpell);
                }


                //Adding features
                features otherworldlyPatron = new features();
                inChar.features.add(otherworldlyPatron);
                int patronName = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which draconic origin
                List<String> patronList = Arrays.asList("The Archfey", "The Fiend", "Great Old One");
                String patron = patronList.get(patronName);
                switch (patron)
                {
                    case "The Archfey":
                        otherworldlyPatron.setName("The Archfey");
                        otherworldlyPatron.setDesc("Your patron is a lord or lady of the fey, a creature of legend who holds secrets that " + "\n" + 
                                            "   were forgotten before the mortal races w ere born. This being’s motivations are often inscrutable, " + "\n" + 
                                            "   and sometimes whimsical, and might involve a striving for greater magical power or the settling " + "\n" + 
                                            "   of age-old grudges.");

                            //adding fey level 1 spells
                        inChar.charSpells.get(1).add("Faerie Fire");
                        inChar.charSpells.get(1).add("Sleep");

                            //adding Fey Presence
                        features feyPresence = new features();
                        inChar.features.add(feyPresence);
                        feyPresence.setName("Fey Presence");
                        feyPresence.setDesc("Your patron bestows upon you the ability to project the beguiling and fearsome presence of the fey." + "\n" + 
                                            "   As an action, you can cause each creature in a 10-foot cube originating from you to make a Wisdom " + "\n" + 
                                            "   saving throw against your warlock spell save DC. The creatures that fail their saving throws are all " + "\n" + 
                                            "   charmed or frightened by you (your choice) until the end of your next turn." + "\n" + 
                                            "   You regain this feature upon a long or short rest.");
                        feyPresence.setUses(1);
                        break;
                    case "The Fiend":
                        otherworldlyPatron.setName("The Fiend");
                        otherworldlyPatron.setDesc("You have made a pact with a fiend from the lower planes of existence, a being whose aims are evil, " + "\n" +  
                                            "   even if you strive against those aims. Such beings desire the corruption or destruction of all things, " + "\n" + 
                                            "   ultimately including you.");
                        
                            //adding fiend spells
                        inChar.charSpells.get(1).add("Burning Hands");
                        inChar.charSpells.get(1).add("Command");
                    
                            //adding dark ones blessing
                        features darkOnesBlessing = new features();
                        inChar.features.add(darkOnesBlessing);
                        darkOnesBlessing.setName("Dark One's Blessing");
                        darkOnesBlessing.setDesc("When you reduce a hostile creature to 0 hit points, you gain temporary hit points equal to your " + "\n" + 
                                            "   Charisma modifier + your warlock level (minimum of 1).");
                        break;
                    case "Great Old One":
                        otherworldlyPatron.setName("Great Old One");
                        otherworldlyPatron.setDesc("Your patron is a mysterious entity whose nature is utterly foreign to the fabric of reality." + "\n" + 
                                            "   It might come from the Far Realm, the space beyond reality, or it could be one of the elder gods known " + "\n" + 
                                            "   only in legends. Its motives are incomprehensible to mortals, and its knowledge so immense and ancient " + "\n" + 
                                            "   that even the greatest libraries pale in comparison to the vast secrets it holds. The Great Old One "  + "\n" + 
                                            "   might be unaware of your existence or entirely indifferent to you, but the secrets you have learned " + "\n" + 
                                            "   allow you to draw your magic from it.");

                            //adding great old one's spells
                        inChar.charSpells.get(1).add("Dissonant Whispers");
                        inChar.charSpells.get(1).add("Tasha's hideous laughter");

                            //adding awakened mind
                        features awakenedMind = new features();
                        inChar.features.add(awakenedMind);
                        awakenedMind.setName("Awakened Mind");
                        awakenedMind.setDesc("Your alien knowledge gives you the ability to touch the minds of other creatures. You can communicate " + "\n" + 
                                            "   telepathically with any creature you can see within 30 feet of you. You don’t need to share a language " + "\n" + 
                                            "   with the creature for it to understand your telepathic utterances, but the creature must be able to " + "\n" + 
                                            "   understand at least one language.");
                        break;
                }
                break;
            case "Wizard":
                //adds simple stats
                hitDie = "1d6";
                pA = "Intelligence";
                savingThrow1 = "Intelligence";
                savingThrow2 = "Wisdom";

                //assigning primary Ability Scores / Mods
               inChar.abilityScores.setAbilityScore("Intelligence", inChar.abilityScores.intAbilityScores.get(0));            //pA
                    inChar.abilityMods.setAbilityMod("Intelligence", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);     //takes away the num as it has been assigned

               inChar.abilityScores.setAbilityScore("Wisdom", inChar.abilityScores.intAbilityScores.get(0));               //pA2
                    inChar.abilityMods.setAbilityMod("Wisdom", inChar.abilityScores.intAbilityScores.get(0));
                inChar.abilityScores.intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

               inChar.abilityScores.setAbilityScore("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    inChar.abilityMods.setAbilityMod("Constitution", inChar.abilityScores.intAbilityScores.get(abilityNum));                                                                                                                             

                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

               inChar.abilityScores.setAbilityScore("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                inChar.abilityMods.setAbilityMod("Strength", inChar.abilityScores.intAbilityScores.get(abilityNum));
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;

               inChar.abilityScores.setAbilityScore("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                inChar.abilityMods.setAbilityMod("Charisma", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;    //rA = 1

               inChar.abilityScores.setAbilityScore("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    inChar.abilityMods.setAbilityMod("Dexterity", inChar.abilityScores.intAbilityScores.get(abilityNum)); 
                inChar.abilityScores.intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                inChar.setHP(6 + inChar.abilityMods.getAbilityMod("Constitution"));     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Daggers", "Darts", "Slings", "QuarterStaffs", "Light Crossbows");
                inChar.charProf.weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "History", "Insight", "Investigation", "Medicine", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    inChar.charProf.skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //Adding Gold
                inChar.newDice = new diceRoller();
                goldNum = inChar.newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                inChar.equipment.add("Gold amount: " + gold);
                
                    //adding a quarterstaff or a dagger
                int wizardWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (wizardWeaponNum == 0)
                {
                    weapon wizardQuarterstaff = new weapon().getSpecificWeapon("Simple Melee", "Quarterstaff", 1);
                    inChar.weapons.add(wizardQuarterstaff);
                }
                else
                {
                    weapon wizardDagger = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 1);
                    inChar.weapons.add(wizardDagger);
                }

                    //adding component pouch or arcane focus
                int wizardEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (wizardEquipmentNum == 0)
                {
                    inChar.equipment.add("Component Pouch");
                }
                else
                {
                    wizardEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> wizardArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    inChar.equipment.add(wizardArcaneFocus.get(wizardEquipmentNum));
                }

                    //adding pack
                wizardEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (wizardEquipmentNum == 0)
                {
                    inChar.equipment.add("Scholar's Pack");
                }
                else
                {
                    inChar.equipment.add("Explorer's Pack");
                }

                //Standard AC
                inChar.setAC(10 + inChar.abilityMods.getAbilityMod("Dexterity"));

                
                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Intelligence";
                spellSaveDC = 8 + 2 + inChar.abilityMods.getAbilityMod("Intelligence");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + inChar.abilityMods.getAbilityMod("Intelligence");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                    //adding spellbook
                inChar.equipment.add("Spellbook");

                spells wizardSpells = new spells();
                ArrayList<List<String>> wizardSpellList = wizardSpells.buildWizardSpellLists();

                List<String> charWizardCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                inChar.charSpells.add(0, charWizardCantrips);
                List<String> wizardLevelSpells = new ArrayList<String>();       //creates an empty wizard cantrip spell list to choose from
                wizardLevelSpells.addAll(wizardSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 3; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = wizardLevelSpells.get(spellNum);
                    inChar.charSpells.get(0).add(newCantrip);
                    wizardLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                wizardLevelSpells.clear();            //clear to refill with 1st level spells
                wizardLevelSpells.addAll(wizardSpellList.get(1));

                List<String> char_wizard1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                inChar.charSpells.add(1, char_wizard1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 6; j++)
                {
                    int spellNum = numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = wizardLevelSpells.get(spellNum);
                    inChar.charSpells.get(1).add(newSpell);
                    wizardLevelSpells.remove(newSpell);
                }


                //Adding features
                    //learning spells
                features learnSpells = new features();
                inChar.features.add(learnSpells);
                learnSpells.setName("Learn spells");
                learnSpells.setDesc("Each time you gain a wizard level, you can add two wizard spells of your choice to your spellbook. Each of these " + "\n" + 
                                "   spells must be of a level for which you have spell slots, as shown on the Wizard table. On your adventures, you might " + "\n" + 
                                "   find other spells that you can add to your spellbook (see the “Your Spellbook” sidebar).");

                    //adding Arcane Recovery
                features arcaneRecovery = new features();
                inChar.features.add(arcaneRecovery);
                arcaneRecovery.setName("Arcane Recovery");
                arcaneRecovery.setDesc("You have learned to regain some of your magical energy by studying your spellbook. Once per day when you " + "\n" + 
                                "   finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level " + "\n" + 
                                "   that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.");

                //Arcane Traditions (school of magic) is not obtained until level 2
                break;

            default:
                //should never hit default
                break;
        }
    }
}











class proficicies
{

    ArrayList<String> weaponProf;
    ArrayList<String> armorProf;
    ArrayList<String> toolProf;
    ArrayList<String> skillProf;
    ArrayList<String> instrumentProf;


    public proficicies()
    {
        this.weaponProf = new ArrayList<String>();
        this.armorProf = new ArrayList<String>();
        this.toolProf = new ArrayList<String>();
        this.skillProf = new ArrayList<String>();
        this.instrumentProf = new ArrayList<String>();
    }

    public void addProf(String inProf, String inItem)
    {
        switch (inProf)
        {
            case "weapon":
            {
                weaponProf.add(inItem);
                break;
            }
            case "armor":
            {
                armorProf.add(inItem);
                break;
            }
            case "tool":
            {
                toolProf.add(inItem);
                break;
            }
            case "skill":
            {
                skillProf.add(inItem);
                break;
            }
            case "instrument":
            {
                instrumentProf.add(inItem);
                break;
            }
            default:
            {
                System.out.println("Warning: Proficiency not added");
            }
        }
    }

    public ArrayList<String> getProf(String inProf)
    {
        switch (inProf)
        {
            case "weapon":
            {
                return weaponProf;
            }
            case "armor":
            {
                return armorProf;
            }
            case "tool":
            {
                return toolProf;
            }
            case "skill":
            {
                return skillProf;
            }
            case "instrument":
            {
                return instrumentProf;
            }
            default:
            {
                System.out.println("Warning: Proficiency not returned");
                ArrayList<String> problem = new ArrayList<String>();
                return problem;
            }
        }
    }

    public void printProficies()
    {
        System.out.println("Proficient in: ");
        if (skillProf.size() > 0)
        {
            System.out.print("Skills: ");
            for (int i = 0; i < skillProf.size(); i++)
            {
                if (i < (skillProf.size() - 1))
                {
                    System.out.print(skillProf.get(i) + ", ");
                }
                else 
                {
                    System.out.println(skillProf.get(i) + ".");
                }
            } 
        }
        if (weaponProf.size() > 0)
        {
            System.out.print("Weapons: ");
            for (int i = 0; i < weaponProf.size(); i++)
            {
                if (i < (weaponProf.size() - 1))
                {
                    System.out.print(weaponProf.get(i) + ", ");
                }
                else 
                {
                    System.out.println(weaponProf.get(i) + ".");
                }
            }
        }
        if (armorProf.size() > 0)
        {
            System.out.print("Armor: ");
            for (int i = 0; i < armorProf.size(); i++)
            {
                if (i < (armorProf.size() - 1))
                {
                    System.out.print(armorProf.get(i) + ", ");
                }
                else 
                {
                    System.out.println(armorProf.get(i) + ".");
                }
            }
        }
        if (toolProf.size() > 0)
        {
            System.out.print("Tools: ");
            for (int i = 0; i < toolProf.size(); i++)
            {
                if (i < (toolProf.size() - 1))
                {
                    System.out.print(toolProf.get(i) + ", ");
                }
                else 
                {
                    System.out.println(toolProf.get(i) + ".");
                }
            }
        }
        if (instrumentProf.size() > 0)
        {
            System.out.print("Instruments: ");
            for (int i = 0; i < instrumentProf.size(); i++)
            {
                if (i < (instrumentProf.size() - 1))
                {
                    System.out.print(instrumentProf.get(i) + ", ");
                }
                else 
                {
                    System.out.println(instrumentProf.get(i) + ".");
                }
            }
        }
    }
}










class backgrounds
{

    //backgrounds include skill prof updates, languages, equipment, and features
    //  features add personality traits, ideals, bonds, and flaws

    String backDesc;
    String backName;
    String personalTrait;
    String ideal;
    String bond;
    String flaw;
    Random numGen = new Random();

    public backgrounds()
    {
        this.backDesc = "";
        this.backName = "";
        this.personalTrait = "";
        this.ideal = "";
        this.bond = "";
        this.flaw = "";
    }

    public void printBackground()
    {
        System.out.println("Background: ");
        System.out.println("Background Name: " + backName);
        System.out.println("Background Description: " + backDesc);
        System.out.println("");     //for readability
        System.out.println("Personal Trait: " + personalTrait);
        System.out.println("Ideal: " + ideal);
        System.out.println("Bond: " + bond);
        System.out.println("Flaw: " + flaw);
    }

    public void getBackground(characterCreater inChar)
    {
        List <String> backgroundsList = Arrays.asList("Acolyte", "Charlatan", "Criminal", "Entertainer", "Folk Hero", "Guild Artisan", "Hermit", "Noble", "Outlander", "Sage",
                                        "Sailor", "Soldier", "Urchin");

        String newBackground = backgroundsList.get(numGen.ints(0, 13).findFirst().getAsInt());  //gets list and chooses background
        //String newBackground = "Entertainer";   //for testing / ^ uncomment when done

        backgrounds newCharBackground = new backgrounds();
        inChar.charBackground.backName = newBackground;                        //sets the background's name
        inChar.charBackground.pickBackground(inChar, newCharBackground);          //begin setting character's background stats
    }

    private void pickBackground(characterCreater inChar, backgrounds inBackground)
    {
        int d8;
        int d6;
        switch (inChar.charBackground.backName)
        {
            case "Acolyte":
            {
                inChar.charBackground.backName = "Acoltyte";
                inChar.charBackground.backDesc = "You have spent your life in the service of a temple to a specific god or pantheon of gods. You act as an " + "\n" + 
                                    "   intermediary between the realm of the holy and the mortal world, performing sacred rites and offering sacrifices " + "\n" + 
                                    "   in order to conduct worshipers into the presence of the divine. You are not necessarily a cleric - performing " + "\n" + 
                                    "   sacred rites is not the same thing as channeling divine power. Choose a god, a pantheon of gods, or some other " + "\n" + 
                                    "   quasi-divine being from among those listed in appendix B or those specified by your DM, and work with your DM " + "\n" + 
                                    "   to detail the nature of your religious service.";

                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I idolize a particular hero of my faith, and constantly " + "\n" +
                                                "   refer to that person’s deeds and example.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I can find common ground between the fiercest enemies, " + "\n" + 
                                                "   empathizing with them and always working toward peace.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I see omens in every event and action. The gods try to " + "\n" + 
                                                "   speak to us, we just need to listen";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "Nothing can shake my optimistic attitude.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I quote (or misquote) sacred texts and proverbs in " + "\n" + 
                                                "   almost every situation.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I am tolerant (or intolerant) of other faiths and respect " + "\n" + 
                                                "   (or condemn) the worship of other gods.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I've enjoyed fine food, drink, and high society among " + "\n" + 
                                                "   my temple’s elite. Rough living grates on me.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I’ve spent so long in the temple that I have little " + "\n" + 
                                                "   practical experience dealing with people in the outside world.";
                        break;
                }

                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "The ancient traditions of worship and " + "\n" + 
                                            "   sacrifice must be preserved and upheld. (Lawful)";
                        inChar.charBackground.bond = "I would die to recover an ancient relic of my faith that was lost long ago.";
                        inChar.charBackground.flaw = "I judge others harshly, and myself even more severely.";
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Charity. I always try to help those in need, no matter " + "\n" + 
                                            "   what the personal cost. (Good)";
                        inChar.charBackground.bond = "I will someday get revenge on the corrupt temple hierarchy who branded me a heretic.";
                        inChar.charBackground.flaw = "I put too much trust in those who wield power within my temple’s hierarchy.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Change. We must help bring about the changes the " + "\n" + 
                                            "   gods are constantly working in the world. (Chaotic)";
                        inChar.charBackground.bond = "I owe my life to the priest who took me in when my parents died.";
                        inChar.charBackground.flaw = "My piety sometimes leads me to blindly trust those that profess faith in my god.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Power. I hope to one day rise to the top of my faith’s " +"\n" + 
                                            "   religious hierarchy. (Lawful)";
                        inChar.charBackground.bond = "Everything I do is for the common people.";
                        inChar.charBackground.flaw = "I am inflexible in my thinking.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Faith. I trust that my deity will guide my actions, I have " + "\n" + 
                                            "   faith that if I work hard, things will go well. (Lawful)";
                        inChar.charBackground.bond = "I will do anything to protect the temple where I served.";
                        inChar.charBackground.flaw = "I am suspicious of strangers and expect the worst of them.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Aspiration. I seek to prove myself worthy of my god’s " + "\n" + 
                                            "   favor by matching my actions against his or her teachings. (Any)";
                        inChar.charBackground.bond = "I seek to preserve a sacred text that my enemies consider heretical and seek to destroy.";
                        inChar.charBackground.flaw = "Once I pick a goal, I become obsessed with it to the detriment of everything else in my life.";
                        break;
                }

                features acolyteShelter = new features();
                inChar.features.add(acolyteShelter);
                acolyteShelter.setName("Shelter of the Faithful");
                acolyteShelter.setDesc("As an acolyte, you command the respect of those who share your faith, and you can perform the religious " + "\n" + 
                                    "   ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care " + "\n" + 
                                    "   at a temple, shrine, or other established presence of your faith, though you must provide any material " + "\n" + 
                                    "   components needed for spells. Those who share your religion will support you (but only you) at a modest " + "\n" + 
                                    "   lifestyle. While near your temple, you can call upon the priests for assistance, provided the assistance " + "\n" + 
                                    "   you ask for is not hazardous and you remain in good standing with your temple.");  
                
                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Insight") == -1)
                {
                    inChar.charProf.skillProf.add("Insight");
                }
                if (inChar.charProf.skillProf.indexOf("Religion") == -1)
                {
                    inChar.charProf.skillProf.add("Religion");
                }

                //Adding Languages
                languages newAcolyteLanguage = new languages();
                String newLangauge = newAcolyteLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newAcolyteLanguage.getRandomLanguage();
                    }
                }

                //Adding Equipment
                    //Holy Symbol
                List<String> newHolySymbol = Arrays.asList("Amulet", "Emblem", "Reliquary");
                inChar.equipment.add(newHolySymbol.get(numGen.ints(0, 3).findFirst().getAsInt()));

                    //Prayer items
                if (numGen.ints(0, 2).findFirst().getAsInt() == 0)
                {
                    inChar.equipment.add("Prayer Book");
                }
                else
                {
                    inChar.equipment.add("Prayer Wheel");
                }
                inChar.equipment.add("5 Incense");
                inChar.equipment.add("Vestments");
                inChar.equipment.add("Set of Common Clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");

                break;
            }
            case "Charlatan":
            {
                inChar.charBackground.backName = "Charlatan";
                inChar.charBackground.backDesc = "You have always had a way with people. You know what makes them tick, you can tease out their hearts' desires " + "\n" + 
                                    "   after a few minutes of conversation, and with a few leading questions you can read them like they were children's " + "\n" + 
                                    "   books. It’s a useful talent, and one that you’re perfectly willing to use for your advantage. You know what people " + "\n" + 
                                    "   want and you deliver, or rather, you promise to deliver. Common sense should steer people away from things that " + "\n" + 
                                    "   sound too good to be true, but common sense seems to be in short supply when you’re around. Should ideas sound " + "\n" + 
                                    "   implausible, you make them sound like the real deal.";

                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I fall in and out of love easily, and am always pursuing someone.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I have a joke for every occasion, especially occasions where humor is inappropriate.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "Flattery is my preferred trick for getting what I want.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I’m a born gambler who can't resist taking a risk for a potential payoff.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I lie about almost everything, even when there’s no good reason to.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "Sarcasm and insults are my weapons of choice.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I keep multiple holy symbols on me and invoke whatever deity might come in useful at any given moment.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I pocket anything I see that might have some value.";
                        break;
                }

                String schemeDesc = "";

                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Independence. I am a free spirit— no one tells me what to do. (Chaotic)";
                        inChar.charBackground.bond = "I fleeced the wrong person and must work to ensure that this individual " + "\n" + 
                                            "   never crosses paths with me or those I care about.";
                        inChar.charBackground.flaw = "I can’t resist a pretty face.";
                        schemeDesc = ("I cheat at games of chance.");  
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Fairness. I never target people who can’t afford to lose a few coins. (Lawful)";
                        inChar.charBackground.bond = "I owe everything to my mentor — a horrible person who’s probably rotting in jail somewhere.";
                        inChar.charBackground.flaw = "I'm always in debt. I spend my ill-gotten gains on decadent luxuries faster than I bring them in.";
                        schemeDesc = ("I shave coins or forge documents.");  
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Charity. I distribute the money I acquire to the people who really need it. (Good)";
                        inChar.charBackground.bond = "Somewhere out there, I have a child who doesn’t know me. I’m making the world better for him or her.";
                        inChar.charBackground.flaw = "I’m convinced that no one could ever fool me the way I fool others.";
                        schemeDesc = ("I insinuate myself into people’s lives to prey on their weakness and secure their fortunes.");
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Creativity. I never run the same con twice. (Chaotic)";
                        inChar.charBackground.bond = "I come from a noble family, and one day I’ll reclaim my lands and title from those who stole them from me.";
                        inChar.charBackground.flaw = "I’m too greedy for my own good. I can’t resist taking a risk if there’s money involved.";
                        schemeDesc = ("I put on new identities like clothes.");
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Friendship. Material goods come and go. Bonds of friendship last forever. (Good)";
                        inChar.charBackground.bond = "A powerful person killed someone I love. Some day soon, I’ll have my revenge.";
                        inChar.charBackground.flaw = "I can’t resist swindling people who are more powerful than me.";
                        schemeDesc = ("I run sleight-of-hand cons on street corners.");
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Aspiration. I’m determined to make something of myself. (Any)";
                        inChar.charBackground.bond = "I swindled and ruined a person who didn’t deserve it. I seek to atone for my misdeeds but might never be " + "\n" + 
                                            "   able to forgive myself.";
                        inChar.charBackground.flaw = "I hate to admit it and will hate myself for it, but I'll run and preserve my own hide if the going gets tough.";
                        schemeDesc = ("I convince people that worthless junk is worth their hard-earned money.");
                        break;
                }
                


                //Features
                    //Favorite Scheme
                features favoriteScheme = new features();
                inChar.features.add(favoriteScheme);
                favoriteScheme.setName("Favorite Scheme");
                favoriteScheme.setDesc(schemeDesc);

                //Adding (additional) feature
                features falseIdentity = new features();
                inChar.features.add(falseIdentity);
                falseIdentity.setName("False Identity");
                falseIdentity.setDesc("You have created a second identity that includes documentation, established acquaintances, and disguises that " + "\n" + 
                                    "   allow you to assume that persona. Additionally, you can forge documents including official papers and personal " + "\n" + 
                                    "   letters, as long as you have seen an example of the kind of document or the handwriting you are trying to copy.");  
                
                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Deception") == -1)
                {
                    inChar.charProf.skillProf.add("Deception");
                }
                if (inChar.charProf.skillProf.indexOf("Sleight of Hand") == -1)
                {
                    inChar.charProf.skillProf.add("Sleight of Hand");
                }

                //Adding tool prof
                if (inChar.charProf.toolProf.indexOf("Disguise Kit") == -1)
                {
                    inChar.charProf.toolProf.add("Disguise Kit");
                }
                if (inChar.charProf.toolProf.indexOf("Forgery Kit") == -1)
                {
                    inChar.charProf.toolProf.add("Forgery Kit");
                }

                //Adding Equipment
                    //Tools of the con of your choice
                List<String> toolsOfCon = Arrays.asList("Ten stoppered bottles filled with colored liquid", "A set of weighted dice", "A deck of marked cards", 
                                                        "A signet ring of an imaginary duke");
                inChar.equipment.add(toolsOfCon.get(numGen.ints(0, 4).findFirst().getAsInt()));

                inChar.equipment.add("Disguise kit");
                inChar.equipment.add("Set of Fine Clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");

                break;
            }
            case "Criminal":
            {
                inChar.charBackground.backName = "Criminal";
                inChar.charBackground.backDesc = "You are an experienced criminal with a history of breaking the law. You have spent a lot of time among other " + "\n" + 
                                        "   criminals and still have contacts within the criminal underworld. You’re far closer than most people to " + "\n" + 
                                        "   the world of murder, theft, and violence that pervades the underbelly of civilization, and you have survived " + "\n" + 
                                        "   up to this point by flouting the rules and regulations of society.";

                String crimSpecialty = "";

                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I always have a plan for what to do when things go wrong.";
                        crimSpecialty = ("Blackmailer.");
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I am always calm, no matter what the situtation. I never raise my voice or let my emotions control me.";
                        crimSpecialty = ("Burglar.");
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "The first thing I do in a new place is note the locations of everything valuable - or where such things " + "\n" + 
                                                                "   could be hidden.";
                        crimSpecialty = ("Enforcer.");
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I would rather make a new friend than a new enemy.";
                        crimSpecialty = ("Fence.");
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I am incredibly slow to trust. Those who seem the fairest often have the most to hide.";
                        crimSpecialty = ("Highway Robber.");
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I don't pay attention to the risks in a situation. Never tell me the odds.";
                        crimSpecialty = ("Hired Killer.");
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "The best way to get me to do something is to tell me I can't do it. ";
                        crimSpecialty = ("Pickpocket.");
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I blow up at the slightest insult.";
                        crimSpecialty = ("Smuggler.");
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Honor. I don’t steal from others in the trade. (Lawful)";
                        inChar.charBackground.bond = "I’m trying to pay off an old debt I owe to a generous benefactor.";
                        inChar.charBackground.flaw = "When I see something valuable, I can’t think about anything but how to steal it."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Freedom. Chains are meant to be broken, as are those who would forge them. (Chaotic)";
                        inChar.charBackground.bond = "My ill-gotten gains go to support my family.";
                        inChar.charBackground.flaw = "When faced with a choice between money and my friends, I usually choose the money.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Charity. I steal from the wealthy so that I can help people in need. (Good)";
                        inChar.charBackground.bond = "Something important was taken from me, and I aim to steal it back.";
                        inChar.charBackground.flaw = "If there’s a plan, I’ll forget it. If I don’t forget it, I’ll ignore it.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Greed. I will do whatever it takes to become wealthy. (Evil)";
                        inChar.charBackground.bond = "I will become the greatest thief that ever lived.";
                        inChar.charBackground.flaw = "I have a “tell” that reveals when I'm lying.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "People. I’m loyal to my friends, not to any ideals, and everyone else can take a trip down the " + "\n" + 
                                                    "   Styx for all I care. (Neutral)";
                        inChar.charBackground.bond = "I’m guilty of a terrible crime. I hope I can redeem myself for it.";
                        inChar.charBackground.flaw = "I turn tail and run when things look bad.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Redemption. There’s a spark of good in everyone. (Good)";
                        inChar.charBackground.bond = "Someone I loved died because of a mistake I made. That will never happen again.";
                        inChar.charBackground.flaw = "An innocent person is in prison for a crime that I committed. I’m okay with that.";
                        break;
                }

                //Features
                    //Criminal Specialty
                features criminalSpecialty = new features();
                inChar.features.add(criminalSpecialty);
                criminalSpecialty.setName("Criminal Specialty");
                criminalSpecialty.setDesc("There are many kinds of criminals, and within a thieves’ guild or similar criminal organization, individual members " + "\n" + 
                                        "   have particular specialties. Even criminals who operate outside of such organizations have strong preferences for " + "\n" + 
                                        "   certain kinds of crimes over others. Your specialty is: " + crimSpecialty); 

                    //Criminal Contact
                features criminalContact = new features();
                inChar.features.add(criminalContact);
                criminalContact.setName("Criminal Contact");
                criminalContact.setDesc("You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how " + "\n" + 
                                        "   to get messages to and from your contact, even over great distances; specifically, you know the local messengers, " + "\n" + 
                                        "   corrupt caravan masters, and seedy sailors who can deliver messages for you.");
                
                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Deception") == -1)
                {
                    inChar.charProf.skillProf.add("Deception");
                }
                if (inChar.charProf.skillProf.indexOf("Stealth") == -1)
                {
                    inChar.charProf.skillProf.add("Stealth");
                }

                //Adding tool prof
                    // one type of gaming set
                List<String> gamingSet = Arrays.asList("Dice Set", "Dragonchess Set", "Playing Card Set", "Three-Dragon Ante Set");
                inChar.charProf.toolProf.add(gamingSet.get(numGen.ints(0, 4).findFirst().getAsInt()));
                    //Thieves tools
                if (inChar.charProf.toolProf.indexOf("Thieves' Tools") == -1)
                {
                    inChar.charProf.toolProf.add("Thieves' Tools");
                }
                

                //Adding Equipment
                inChar.equipment.add("Crowbar");
                inChar.equipment.add("Set of dark common clothes including a hood");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");

                break;
            } 
            case "Entertainer":
            {
                inChar.charBackground.backName = "Entertainer";
                inChar.charBackground.backDesc = "You thrive in front of an audience. You know how to entrance them, entertain them, and even inspire them. Your " + "\n" + 
                                        "   poetics can stir the hearts of those who hear you, awakening grief or joy, laughter or anger. Your music raises " + "\n" + 
                                        "   their spirits or captures their sorrow. Your dance steps captivate, your humor cuts to the quick. Whatever " + "\n" + 
                                        "   techniques you use, your art is your life.";
                
                 //Add personality trait
                 d8 = numGen.ints(0, 8).findFirst().getAsInt();
                 switch (d8)
                 {
                     case 0:
                         inChar.charBackground.personalTrait = "I know a story relevant to almost every situation.";
                         break;
                     case 1: 
                         inChar.charBackground.personalTrait = "Whenever I come to a new place, I collect local rumors and spread gossip.";
                         break;
                     case 2: 
                         inChar.charBackground.personalTrait = "I’m a hopeless romantic, always searching for that “special someone.”";
                         break;
                     case 3: 
                         inChar.charBackground.personalTrait = "Nobody stays angry at me or around me for long, since I can defuse any amount of tension.";
                         break;
                     case 4: 
                         inChar.charBackground.personalTrait = "I love a good insult, even one directed at me.";
                         break;
                     case 5:
                         inChar.charBackground.personalTrait = "I get bitter if I’m not the center of attention.";
                         break;
                     case 6: 
                         inChar.charBackground.personalTrait = "I’ll settle for nothing less than perfection.";
                         break;
                     case 7: 
                         inChar.charBackground.personalTrait = "I change my mood or my mind as quickly as I change key in a song.";
                         break;
                 }
                 //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Beauty. When I perform, I make the world better than it was. (Good)";
                        inChar.charBackground.bond = "My instrument is my most treasured possession, and it reminds me of someone I love.";
                        inChar.charBackground.flaw = "I’ll do anything to win fame and renown."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Tradition. The stories, legends, and songs of the past must never be forgotten, for they teach us who we are. (Lawful)";
                        inChar.charBackground.bond = "Someone stole my precious instrument, and someday I’ll get it back.";
                        inChar.charBackground.flaw = "I’m a sucker for a pretty face.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Creativity. The world is in need of new ideas and bold action. (Chaotic)";
                        inChar.charBackground.bond = "I want to be famous, whatever it takes.";
                        inChar.charBackground.flaw = "A scandal prevents me from ever going home again. That kind of trouble seems to follow me around.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Greed. I’m only in it for the money and fame. (Evil)";
                        inChar.charBackground.bond = "I idolize a hero of the old tales and measure my deeds against that person’s.";
                        inChar.charBackground.flaw = "I once satirized a noble who still wants my head. It was a mistake that I will likely repeat.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "People. I like seeing the smiles on people’s faces when I perform. That’s all that matters. (Neutral)";
                        inChar.charBackground.bond = "I will do anything to prove myself superior to my hated rival.";
                        inChar.charBackground.flaw = "I have trouble keeping my true feelings hidden. My sharp tongue lands me in trouble.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Honesty. Art should reflect the soul; it should come from within and reveal who we really are. (Any)";
                        inChar.charBackground.bond = "I would do anything for the other members of my old troupe.";
                        inChar.charBackground.flaw = "Despite my best efforts, I am unreliable to my friends.";
                        break;
                }

                //Adding (additional) feature
                    //Popular Demand
                features popularDemand = new features();
                inChar.features.add(popularDemand);
                popularDemand.setName("By Popular Demand");
                popularDemand.setDesc("You can always find a place to perform, usually in an inn or tavern but possibly with a circus, at a theater, or " + "\n" + 
                                    "   even in a noble’s court. At such a place, you receive free lodging and food of a modest or comfortable standard " + "\n" + 
                                    "   (depending on the quality of the establishment), as long as you perform each night. In addition, your performance " + "\n" + 
                                    "   makes you something of a local figure. When strangers recognize you in a town where you have performed, they " + "\n" + 
                                    "   typically take a liking to you.");
                    
                    //Entertainer Routine
                features routine = new features();
                inChar.features.add(routine);
                routine.setName("Entertainer Routine");
                routine.setDesc("A good entertainer is versatile, spicing up every performance with a variety of different routines. Your routines are: ");

                //Choose different performances
                List<String> routineNameList = Arrays.asList("Actor", "Dancer", "Fire-eater", "Jester", "Juggler", "Instrumentalist", "Poet", "Singer", 
                                                        "StroyTeller", "Tumbler");
                List<String> routineNames = new ArrayList<String>();
                routineNames.addAll(routineNameList);

                for (int i = 0; i < 3; i++)         //Adding three routines 
                {
                    int rountineNum = numGen.ints(0, routineNames.size()).findFirst().getAsInt();

                    if (i == 2) //if its the last one
                    {
                        routine.setDesc(routine.getDesc().concat(routineNames.get(rountineNum) + "."));
                    }
                    else
                    {
                        routine.setDesc(routine.getDesc().concat(routineNames.get(rountineNum) + ", "));
                        routineNames.remove(rountineNum);   //so its not chosen twice
                    }
                }

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Acrobatics") == -1)
                {
                    inChar.charProf.skillProf.add("Acrobatics");
                }
                if (inChar.charProf.skillProf.indexOf("Performance") == -1)
                {
                    inChar.charProf.skillProf.add("Performance");
                }

                //Adding tool prof
                    // one type of musical instrument
                List<String> instrumentSet = Arrays.asList("Bag Pipes", "Drum", "Dulcimer", "Flute", "Lute", "Lyre", "Horn", "Pan Flute", "Shawm", "Viol");
                inChar.charProf.instrumentProf.add(instrumentSet.get(numGen.ints(0, 10).findFirst().getAsInt()));
                    //Disguise Kit
                if (inChar.charProf.toolProf.indexOf("Disguise Kit") == -1)
                {
                    inChar.charProf.toolProf.add("Disguise Kit");
                }
                

                //Adding Equipment
                    //One type of instrument - Should make it the one youre proficient in?
                inChar.equipment.add(instrumentSet.get(numGen.ints(0, 10).findFirst().getAsInt()));
                    //favor of an admirer
                trinkets newTrinket = new trinkets();
                List<String> admirerFavor = Arrays.asList("Love Letter", "Lock of Hair", newTrinket.getTrinket());
                inChar.equipment.add(admirerFavor.get(numGen.ints(0, 3).findFirst().getAsInt()));
                inChar.equipment.add("A Costume");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");
                
                break;
            }
            case "Folk Hero":
            {
                inChar.charBackground.backName = "Folk Hero";
                inChar.charBackground.backDesc = "You come from a humble social rank, but you are destined for so much more. Already the people of your home " + "\n" + 
                                        "   village regard you as their champion, and your destiny calls you to stand against the tyrants and monsters " + "\n" + 
                                        "   that threaten the common folk everywhere.";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I judge people by their actions, not their words.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "If someone is in trouble, I’m always ready to lend help.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "When I set my mind to something, I follow through no matter what gets in my way.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I have a strong sense of fair play and always try to find the most equitable solution to arguments.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I’m confident in my own abilities and do what I can to instill confidence in others.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "Thinking is for other people. I prefer action.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I misuse long words in an attempt to sound smarter.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I get bored easily. When am I going to get on with my destiny?";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Respect. People deserve to be treated with dignity and respect. (Good)";
                        inChar.charBackground.bond = "I have a family, but I have no idea where they are. One day, I hope to see them again.";
                        inChar.charBackground.flaw = "The tyrant who rules my land will stop at nothing to see me killed."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Fairness. No one should get preferential treatment before the law, and no one is above the law. (Lawful)";
                        inChar.charBackground.bond = "I worked the land, I love the land, and I will protect the land.";
                        inChar.charBackground.flaw = "I’m convinced of the significance of my destiny, and blind to my shortcomings and the risk of failure.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Freedom. Tyrants must not be allowed to oppress the people. (Chaotic)";
                        inChar.charBackground.bond = "A proud noble once gave me a horrible beating, and I will take my revenge on any bully I encounter.";
                        inChar.charBackground.flaw = "The people who knew me when I was young know my shameful secret, so I can never go home again.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Might. If I become strong, I can take what I want — what I deserve. (Evil)";
                        inChar.charBackground.bond = "My tools are symbols of my past life, and I carry them so that I will never forget my roots.";
                        inChar.charBackground.flaw = "I have a weakness for the vices of the city, especially hard drink.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Sincerity. There’s no good in pretending to be something I’m not. (Neutral)";
                        inChar.charBackground.bond = "I protect those who cannot protect themselves.";
                        inChar.charBackground.flaw = "Secretly, I believe that things would be better if I were a tyrant lording over the land.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Destiny. Nothing and no one can steer me away from my higher calling. (Any)";
                        inChar.charBackground.bond = "I wish my childhood sweetheart had come with me to pursue my destiny.";
                        inChar.charBackground.flaw = "I have trouble trusting in my allies.";
                        break;
                }

                //Adding (additional) feature
                    //Rustic Hospitality
                features rusticHospitality = new features();
                inChar.features.add(rusticHospitality);
                rusticHospitality.setName("Rustic Hospitality");
                rusticHospitality.setDesc("Since you come from the ranks of the common folk, you fit in among them with ease. You can find a place " + "\n" + 
                                        "   to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. " + "\n" + 
                                        "   They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.");
                    
                    //Defining Event
                features defEvent = new features();
                inChar.features.add(defEvent);
                defEvent.setName("Defining Event");

                //Choose defining event
                List<String> eventList = Arrays.asList("I stood up to a tyrant’s agents.",
                        "I saved people during a natural disaster.", "I stood alone against a terrible monster.",
                        "I stole from a corrupt merchant to help the poor.",
                        "I led a militia to fight off an invading army.",
                        "I broke into a tyrant’s castle and stole weapons to arm the people.",
                        "I trained the peasantry to use farm implements as weapons against a tyrant’s soldiers.",
                        "A lord rescinded an unpopular decree after I led a symbolic act of protect against it.",
                        "A celestial, fey, or similar creature gave me a blessing or revealed my secret origin.",
                        "Recruited into a lord’s army, I rose to leadership and was commended for my heroism.");
                String event = eventList.get(numGen.ints(0, 10).findFirst().getAsInt());


                defEvent.setDesc("You previously pursued a simple profession among the peasantry, perhaps as a farmer, miner, servant, shepherd, " + "\n" + 
                                "   woodcutter, or gravedigger. But something happened that set you on a different path and marked you for greater things." + "\n" + 
                                "   This event was: " + event + ".");

                

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Animal Handling") == -1)
                {
                    inChar.charProf.skillProf.add("Animal Handling");
                }
                if (inChar.charProf.skillProf.indexOf("Survival") == -1)
                {
                    inChar.charProf.skillProf.add("Survival");
                }

                //Adding tool prof
                    // one type of artisan tools
                List<String> artisanTools = Arrays.asList("Alchemist’s supplies", "Brewer’s supplies",
                        "Calligrapher's supplies", "Carpenter’s tools", "Cartographer’s tools", "Cobbler’s tools",
                        "Cook’s utensils", "Glassblower’s tools", "Jeweler’s tools", "Leatherworker’s tools",
                        "Mason’s tools", "Painter’s supplies", "Potter’s tools", "Smith’s tools", "Tinker’s tools",
                        "Weaver’s tools", "Woodcarver's tools");
                String newToolProff = artisanTools.get(numGen.ints(0, artisanTools.size()).findFirst().getAsInt());
                while (inChar.charProf.toolProf.indexOf(newToolProff) != -1)     //repeat if the chosen tool was already in the tool prof list
                {
                    newToolProff = artisanTools.get(numGen.ints(0, artisanTools.size()).findFirst().getAsInt());
                }
                inChar.charProf.toolProf.add(newToolProff);      //once one that is found that was not already in the tool prof list

                    //Vehicles
                if (inChar.charProf.toolProf.indexOf("Vehicles (Land)") == -1)   //pretty sure this has never been added but check anyway
                {
                    inChar.charProf.toolProf.add("Vehicles (Land)");
                }
                

                //Adding Equipment
                    //One type of artisans tools - Should make it the one youre proficient in?
                inChar.equipment.add(artisanTools.get(numGen.ints(0, artisanTools.size()).findFirst().getAsInt()));                    
                inChar.equipment.add("A shovel");
                inChar.equipment.add("An iron pot");
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");

                break;
            }
            case "Guild Artisan":
            {
                inChar.charBackground.backName = "Guild Artisan";
                inChar.charBackground.backDesc = "You are a member of an artisan’s guild, skilled in a particular field and closely associated with other artisans." + "\n" + 
                                        "   You are a well-established part of the mercantile world, freed by talent and wealth from the constraints of a " + "\n" + 
                                        "   feudal social order. You learned your skills as an apprentice to a master artisan, under the sponsorship of your " + "\n" + 
                                        "   guild, until you became a master in your own right.";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I believe that anything worth doing is worth doing right. I can’t help it— I’m a perfectionist.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I’m a snob who looks down on those who can’t appreciate fine art.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I always want to know how things work and what makes people tick.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I’m full of witty aphorisms and have a proverb for every occasion.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I’m rude to people who lack my commitment to hard work and fair play.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I don’t part with my money easily and will haggle tirelessly to get the best deal possible.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I’m well known for my work, and I want to make sure everyone appreciates it. " + "\n" + 
                                                    "   I'm always taken aback when people haven’t heard of me.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I like to talk at length about my profession.";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Community. It is the duty of all civilized people to strengthen the bonds of community and the " + "\n" + 
                                                    "   security of civilization. (Lawful)";
                        inChar.charBackground.bond = "The workshop where I learned my trade is the most important place in the world to me.";
                        inChar.charBackground.flaw = "I’ll do anything to get my hands on something rare or priceless."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Generosity. My talents were given to me so that I could use them to benefit the world. (Good)";
                        inChar.charBackground.bond = "I created a great work for someone, and then found them unworthy to receive it. I’m still looking for someone worthy.";
                        inChar.charBackground.flaw = "I’m quick to assume that someone is trying to cheat me.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Freedom. Everyone should be free to pursue his or her own livelihood. (Chaotic)";
                        inChar.charBackground.bond = "I owe my guild a great debt for forging me into the person I am today.";
                        inChar.charBackground.flaw = "No one must ever learn that I once stole money from guild coffers.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Greed. I’m only in it for the money. (Evil)";
                        inChar.charBackground.bond = "I pursue wealth to secure someone’s love.";
                        inChar.charBackground.flaw = "I’m never satisfied with what I have— I always want more.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "People. I’m committed to the people I care about, not to ideals. (Neutral)";
                        inChar.charBackground.bond = "One day I will return to my guild and prove that I am the greatest artisan of them all.";
                        inChar.charBackground.flaw = "I would kill to acquire a noble title.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Aspiration. I work hard to be the best there is at my craft.";
                        inChar.charBackground.bond = "I will get revenge on the evil forces that destroyed my place of business and ruined my livelihood.";
                        inChar.charBackground.flaw = "I’m horribly jealous of anyone who can outshine my handiwork. Everywhere I go, I’m surrounded by rivals.";
                        break;
                }

                //Adding (additional) features
                    //Guild Membership
                features guildMembership = new features();
                inChar.features.add(guildMembership);
                guildMembership.setName("Guild Membership");
                guildMembership.setDesc("As an established and respected member of a guild, you can rely on certain benefits that membership provides. Your " + "\n" + 
                                        "   fellow guild members will provide you with lodging and food if necessary, and pay for your funeral if needed." + "\n" + 
                                        "   In some cities and towns, a guildhall offers a central place to meet other members of your profession, which can " + "\n" + 
                                        "   be a good place to meet potential patrons, allies, or hirelings. " + "\n" + 
                                        "   Guilds often wield tremendous political power. If you are accused o f a crime, your guild will support you if a good " + "\n" + 
                                        "   case can be made for your innocence or the crime is justifiable. You can also gain access to powerful political figures " + "\n" + 
                                        "   through the guild, if you are a member in good standing. Such connections might require the donation of money or " + "\n" + 
                                        "   magic items to the guild’s coffers. You must pay dues of 5 gp per month to the guild. If you miss payments, " + "\n" + 
                                        "   you must make up back dues to remain in the guild’s good graces.");
                
                      //Guild Business  
                features business = new features();
                inChar.features.add(business);
                business.setName("Guild Business");

                //build business list and choose
                List<String> charBusiness = Arrays.asList("Alchemists and apothecaries",
                        "Armorers, locksmiths, and finesmiths", "Brewers, distillers, and vintners",
                        "Calligraphers, scribes, and scriveners", "Carpenters, roofers, and plasterers",
                        "Cartographers, surveyors, and chart-makers", "Cobblers and shoemakers", "Cooks and bakers",
                        "Glassblowers and glaziers", "Jewelers and gemcutters", "Leatherworkers, skinners, and tanners",
                        "Masons and stonecutters", "Painters, limners, and sign-makers", "Potters and tile-makers",
                        "Shipwrights and sailmakers", "Smiths and metal-forgers", "Tinkers, pewterers, and casters",
                        "Wagon-makers and wheelwrights", "Weavers and dyers", "Woodcarvers, coopers, and bowyers");
                String guildBusiness = charBusiness.get(numGen.ints(0, charBusiness.size()).findFirst().getAsInt());

                business.setDesc("Guilds are generally found in cities large enough to support several artisans practicing the same trade. However, your guild " + "\n" + 
                                "   might instead be a loose network of artisans who each work in a different village within a larger realm." + "\n" + 
                                "   You are in the guild business of: " + guildBusiness + ".");

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Insight") == -1)
                {
                    inChar.charProf.skillProf.add("Insight");
                }
                if (inChar.charProf.skillProf.indexOf("Persuasion") == -1)
                {
                    inChar.charProf.skillProf.add("Persuasion");
                }

                //Adding tool prof
                    // one type of artisan tools
                List<String> guildArtisanTools = Arrays.asList("Alchemist’s supplies", "Brewer’s supplies",
                        "Calligrapher's supplies", "Carpenter’s tools", "Cartographer’s tools", "Cobbler’s tools",
                        "Cook’s utensils", "Glassblower’s tools", "Jeweler’s tools", "Leatherworker’s tools",
                        "Mason’s tools", "Painter’s supplies", "Potter’s tools", "Smith’s tools", "Tinker’s tools",
                        "Weaver’s tools", "Woodcarver's tools");
                String newToolProff = guildArtisanTools.get(numGen.ints(0, guildArtisanTools.size()).findFirst().getAsInt());
                while (inChar.charProf.toolProf.indexOf(newToolProff) != -1)     //repeat if the chosen tool was in the tool prof list
                {
                    newToolProff = guildArtisanTools.get(numGen.ints(0, guildArtisanTools.size()).findFirst().getAsInt());
                }
                inChar.charProf.toolProf.add(newToolProff);      //once one that is found that was not already in the tool prof list
                

                //Adding Language
                languages newGuildArtisanLanguage = new languages();
                String newLangauge = newGuildArtisanLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newGuildArtisanLanguage.getRandomLanguage();
                    }
                }

                //Adding Equipment
                    //One type of artisans tools - Should make it the one youre proficient in?
                inChar.equipment.add(guildArtisanTools.get(numGen.ints(0, guildArtisanTools.size()).findFirst().getAsInt()));
                
                inChar.equipment.add("A Letter of introduction from your guild");
                inChar.equipment.add("A set of traveler's clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 15 gp");

                break;
            }
            case "Hermit":
            {
                inChar.charBackground.backName = "Hermit";
                inChar.charBackground.backDesc = "You lived in seclusion—either in a sheltered community such as a monastery, or entirely a lone—for a formative part " + "\n" + 
                                        "   of your life. In your time apart from the clamor of society, you found quiet, solitude, and perhaps some of the " + "\n" + 
                                        "   answers you were looking for.";
                

                String reason = "";
                
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I’ve been isolated for so long that I rarely speak, preferring gestures and the occasional grunt.";
                        reason = ("I was searching for spiritual enlightenment.");
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I am utterly serene, even in the face of disaster.";
                        reason = ("I was partaking of communal living in accordance with the dictates of a religious order.");
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "The leader of my community had something wise to say on every topic, and I am eager to share that wisdom.";
                        reason = ("I was exiled for a crime I didn’t commit.");
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I feel tremendous empathy for all who suffer.";
                        reason = ("I retreated from society after a life-altering event.");
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I’m oblivious to etiquette and social expectations.";
                        reason = ("I needed a quiet place to work on my art, literature, music, or manifesto.");
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I connect everything that happens to me to a grand, cosmic plan.";
                        reason = ("I needed to commune with nature, far from civilization.");
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I often get lost in my own thoughts and contemplation, becoming oblivious to my surroundings.";
                        reason = ("I was the caretaker of an ancient ruin or relic.");
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I am working on a grand philosophical theory and love sharing my ideas.";
                        reason = ("was a pilgrim in search of a person, place, or relic of spiritual significance.");
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Greater Good. My gifts are meant to be shared with all, not used for my own benefit. (Good)";
                        inChar.charBackground.bond = "Nothing is more important than the other members of my hermitage, order, or association.";
                        inChar.charBackground.flaw = "Now that I've returned to the world, I enjoy its delights a little too much."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Logic. Emotions must not cloud our sense of what is right and true, or our logical thinking. (Lawful)";
                        inChar.charBackground.bond = "I entered seclusion to hide from the ones who might still be hunting me. I must someday confront them.";
                        inChar.charBackground.flaw = "I harbor dark, bloodthirsty thoughts that my isolation and meditation failed to quell.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Free Thinking. Inquiry and curiosity are the pillars of progress. (Chaotic)";
                        inChar.charBackground.bond = "I’m still seeking the enlightenment I pursued in my seclusion, and it still eludes me.";
                        inChar.charBackground.flaw = "I am dogmatic in my thoughts and philosophy.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Power. Solitude and contemplation are paths toward mystical or magical power. (Evil)";
                        inChar.charBackground.bond = "I entered seclusion because I loved someone I could not have.";
                        inChar.charBackground.flaw = "I let my need to win arguments overshadow friendships and harmony.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Live and Let Live. Meddling in the affairs of others only causes trouble. (Neutral)";
                        inChar.charBackground.bond = "Should my discovery come to light, it could bring ruin to the world.";
                        inChar.charBackground.flaw = "I’d risk too much to uncover a lost bit of knowledge.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Self-Knowledge. If you know yourself, there’s nothing left to know. (Any)";
                        inChar.charBackground.bond = "My isolation gave me great insight into a great evil that only I can destroy.";
                        inChar.charBackground.flaw = "I like keeping secrets and won’t share them with anyone.";
                        break;
                }

                //Features
                    //Discovery
                features discovery = new features();
                inChar.features.add(discovery);
                discovery.setName("Discovery");
                discovery.setDesc("The quiet seclusion of your extended hermitage gave you access to a unique and powerful discovery. The exact nature of " + "\n" + 
                                "   this revelation depends on the nature of your seclusion. It might be a great truth about the cosmos, the deities, the " + "\n" + 
                                "   powerful beings of the outer planes, or the forces of nature. It could be a site that no one else has ever seen. " + "\n" + 
                                "   You might have uncovered a fact that has long been forgotten, or unearthed some relic of the past that could rewrite history." + "\n" + 
                                "   It might be information that would be damaging to the people who or consigned you to exile, and hence the reason for your " + "\n" + 
                                "   return to society. Work with your DM to determine the details of your discovery and its impact on the campaign.");

                    //Life of Seclusion
                features seclusionLife = new features();
                inChar.features.add(seclusionLife);
                seclusionLife.setName("Life of Seclusion");
                seclusionLife.setDesc("What was the reason for your isolation, and what changed to allow you to end your solitude?" + "\n" + 
                                    "   Your reason was: " + reason);

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Medicine") == -1)
                {
                    inChar.charProf.skillProf.add("Medicine");
                }
                if (inChar.charProf.skillProf.indexOf("Religion") == -1)
                {
                    inChar.charProf.skillProf.add("Religion");
                }

                //Adding tool prof
                if (inChar.charProf.toolProf.indexOf("Herbalism kit") == -1)
                {
                    inChar.charProf.toolProf.add("Herbalism kit");
                }               

                //Adding Language
                languages newHermitLanguage = new languages();
                String newLangauge = newHermitLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newHermitLanguage.getRandomLanguage();
                    }
                }

                //Adding Equipment
                    //One type of artisans tools - Should make it the one youre proficient in?
                inChar.equipment.add("Herbalism Kit");
                inChar.equipment.add("A scroll case stuffed full of notes from your studies or prayers");               
                inChar.equipment.add("a winter blanket");
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: 5 gp");
                break;
            }
            case "Noble":
            {
                inChar.charBackground.backName = "Noble";
                inChar.charBackground.backDesc = "You understand wealth, power, and privilege. You carry a noble title, and your family owns land, collects taxes, " + "\n" + 
                                        "   and wields significant political influence. You might be a pampered aristocrat unfamiliar with work or discomfort, " + "\n" + 
                                        "   a former merchant just elevated to the nobility, or a disinherited scoundrel with a disproportionate sense of entitlement." + "\n" + 
                                        "   Or you could be an honest, hard-working landowner who cares deeply about the people who live and work on your land, keenly " + "\n" + 
                                        "   aware of your responsibility to them. Work with your DM to come up with an appropriate title and determine how much authority " + "\n" + 
                                        "   that title carries.";
                

                //Feature - Life of Seclusion - finished in d8
                features seclusionLife = new features();
                inChar.features.add(seclusionLife);
                seclusionLife.setName("Life of Seclusion");
                seclusionLife.setDesc("What was the reason for your isolation, and what changed to allow you to end your solitude?" + "\n" + 
                                    "   Your reason was: ");
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "My eloquent flattery makes everyone I talk to feel like the most wonderful and important person in the world.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "The common folk love me for my kindness and generosity.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "No one could doubt by looking at my regal bearing that I am a cut above the unwashed masses.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I take great pains to always look my best and follow the latest fashions.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I don’t like to get my hands dirty, and I won’t be caught dead in unsuitable accommodations.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "Despite my noble birth, I do not place myself above other folk. We all have the same blood.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "My favor, once lost, is lost forever.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "If you do me an injury, I will crush you, ruin your name, and salt your fields.";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Respect. Respect is due to me because of my position, but all people regardless of station deserve to be " + "\n" + 
                                             "  treated with dignity. (Good)";
                        inChar.charBackground.bond = "I will face any challenge to win the approval of my family.";
                        inChar.charBackground.flaw = "I secretly believe that everyone is beneath me."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Responsibility. It is my duty to respect the authority of those above me, just as those below " + "\n" + 
                                                    "   me must respect mine. (Lawful)";
                        inChar.charBackground.bond = "My house’s alliance with another noble family must be sustained at all costs.";
                        inChar.charBackground.flaw = "I hide a truly scandalous secret that could ruin my family forever.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Independence. I must prove that I can handle myself without the coddling of my family. (Chaotic)";
                        inChar.charBackground.bond = "Nothing is more important than the other members of my family.";
                        inChar.charBackground.flaw = "I too often hear veiled insults and threats in every word addressed to me, and I’m quick to anger.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Power. If I can attain more power, no one will tell me what to do. (Evil)";
                        inChar.charBackground.bond = "I am in love with the heir of a family that my family despises.";
                        inChar.charBackground.flaw = "I have an insatiable desire for carnal pleasures.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Family. Blood runs thicker than water. (Any)";
                        inChar.charBackground.bond = "My loyalty to my sovereign is unwavering.";
                        inChar.charBackground.flaw = "In fact, the world does revolve around me.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Noble Obligation. It is my duty to protect and care for the people beneath me. (Good)";
                        inChar.charBackground.bond = "The common folk must see me as a hero of the people.";
                        inChar.charBackground.flaw = "By my words and actions, I often bring shame to my family.";
                        break;
                }

                //Features
                    //Position of Privilege
                features posPrivilege = new features();
                inChar.features.add(posPrivilege);
                posPrivilege.setName("Position of Privilege");
                posPrivilege.setDesc("Thanks to your noble birth, people are inclined to think the best of you. You are welcome in high society, and people " + "\n" + 
                                    "   assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your " + "\n" + 
                                    "   displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience " + "\n" + 
                                    "   with a local noble if you need to.");

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("History") == -1)
                {
                    inChar.charProf.skillProf.add("History");
                }
                if (inChar.charProf.skillProf.indexOf("Persuasion") == -1)
                {
                    inChar.charProf.skillProf.add("Persuasion");
                }

                //Adding tool prof
                    //One type of gaming set
                List<String> gamingSet = Arrays.asList("Dice Set", "Dragonchess Set", "Playing Card Set", "Three-Dragon Ante Set");
                inChar.charProf.toolProf.add(gamingSet.get(numGen.ints(0, 4).findFirst().getAsInt()));              

                //Adding Language
                languages newNobleLanguage = new languages();
                String newLangauge = newNobleLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newNobleLanguage.getRandomLanguage();
                    }
                }

                //Adding Equipment
                inChar.equipment.add("A signet ring");
                inChar.equipment.add("A scroll of pedigree");               
                inChar.equipment.add("A set of fine clothes");
                inChar.equipment.add("Add to Gold: A purse containing 25 gp");
                break;
            }
            case "Outlander":
            {
                inChar.charBackground.backName = "Outlander";
                inChar.charBackground.backDesc = "You grew up in the wilds, far from civilization and the comforts of town and technology. You’ve witnessed the migration " + "\n" + 
                                        "   of herds larger than forests, survived weather more extreme than any city-dweller could comprehend, and enjoyed the " + "\n" + 
                                        "   solitude of being the only thinking creature for miles in any direction. The wilds are in your blood, whether you were " + "\n" + 
                                        "   a nomad, an explorer, a recluse, a hunter-gatherer, or even a marauder. Even in places where you don’t know the specific " + "\n" + 
                                        "   features of the terrain, you know the ways of the wild.";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I’m driven by a wanderlust that led me away from home.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I watch over my friends as if they were a litter of newborn pups.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "once ran twenty-five miles without stopping to warn to my clan of an approaching orc horde." + "\n" +
                                                            "I’d do it again if I had to.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I have a lesson for every situation, drawn from observing nature.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I place no stock in wealthy or well-mannered folk. Money and manners won’t save you from a hungry owlbear.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I’m always picking things up, absently fiddling with them, and sometimes accidentally breaking them.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I feel far more comfortable around animals than people.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I was, in fact, raised by wolves.";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Change. Life is like the seasons, in constant change, and we must change with it. (Chaotic)";
                        inChar.charBackground.bond = "My family, clan, or tribe is the most important thing in my life, even when they are far from me.";
                        inChar.charBackground.flaw = "I am too enamored of ale, wine, and other intoxicants."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Greater Good. It is each person’s responsibility to make the most happiness for the whole tribe. (Good)";
                        inChar.charBackground.bond = "An injury to the unspoiled wilderness of my home is an injury to me.";
                        inChar.charBackground.flaw = "There’s no room for caution in a life lived to the fullest.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Honor. If I dishonor myself, I dishonor my whole clan. (Lawful)";
                        inChar.charBackground.bond = "I will bring terrible wrath down on the evildoers who destroyed my homeland.";
                        inChar.charBackground.flaw = "I remember every insult I’ve received and nurse a silent resentment toward anyone who’s ever wronged me.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Might. The strongest are meant to rule. (Evil)";
                        inChar.charBackground.bond = "I am the last of my tribe, and it is up to me to ensure their names enter legend.";
                        inChar.charBackground.flaw = "I am slow to trust members of other races, tribes, and societies.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Nature. The natural world is more important than all the constructs of civilization. (Neutral)";
                        inChar.charBackground.bond = "I suffer awful visions of a coming disaster and will do anything to prevent it.";
                        inChar.charBackground.flaw = "Violence is my answer to almost any challenge.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Glory. I must earn glory in battle, for myself and my clan. (Any)";
                        inChar.charBackground.bond = "It is my duty to provide children to sustain my tribe.";
                        inChar.charBackground.flaw = "Don’t expect me to save those who can’t save themselves. It is nature’s way that the strong thrive and the weak perish.";
                        break;
                }

                //Features
                    //Origin
                features origin = new features();
                inChar.features.add(origin);
                origin.setName("Origin");
                List<String> originList = Arrays.asList("Forester", "Bounty hunter", "Trapper", "Pilgrim",      //choosing which occupation
                        "Homesteader", "Tribal nomad", "Guide", "Hunter-gatherer", "Exile or outcast",
                        "Tribal marauder");
                String originOccupaiton = (originList.get(numGen.ints(0, 10).findFirst().getAsInt()));   //add
                origin.setDesc("You've been to strange places and seen things that others cannot begin to fathom. Consider some of the distant lands you have " + "\n" + 
                                "   visited, and how they impacted you. Your occupation during your time in the wilds is: " + originOccupaiton + ".");

                    //Wanderer
                features wanderer = new features();
                inChar.features.add(wanderer);
                wanderer.setName("Wanderer");
                wanderer.setDesc("You have an excellent memory for maps and geography, and you can always recall the general layout of terrain, settlements, " + "\n" + 
                                "   and other features around you. In addition, you can find food and fresh water for yourself and up to five other people each day, " + "\n" + 
                                "   provided that the land offers berries, small game, water, and so forth.");        

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Athletics") == -1)
                {
                    inChar.charProf.skillProf.add("Athletics");
                }
                if (inChar.charProf.skillProf.indexOf("Survival") == -1)
                {
                    inChar.charProf.skillProf.add("Survival");
                }

                //Adding tool prof
                    //One type musical instrument
                List<String> instrumentSet = Arrays.asList("Bag Pipes", "Drum", "Dulcimer", "Flute", "Lute", "Lyre", "Horn", "Pan Flute", "Shawm", "Viol");
                inChar.charProf.instrumentProf.add(instrumentSet.get(numGen.ints(0, 10).findFirst().getAsInt()));              

                //Adding Language
                languages newOutlanderLanguage = new languages();
                String newLangauge = newOutlanderLanguage.getRandomLanguage();
                boolean added = false;
                while (!added)      //to make sure the new langauge isnt already in the list
                {
                    if (inChar.charLanguages.indexOf(newLangauge) == -1)
                    {
                        inChar.charLanguages.add(newLangauge);
                        added = true;
                    }
                    else
                    {
                        newLangauge = newOutlanderLanguage.getRandomLanguage();
                    }
                }

                //Adding Equipment
                inChar.equipment.add("A staff");
                inChar.equipment.add("A hunting trap");               
                inChar.equipment.add("A trophy from an animal you killed");
                inChar.equipment.add("A set of traveler's clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 10 gp");
                break;
            }
            case "Sage":
            {
                inChar.charBackground.backName = "Sage";
                inChar.charBackground.backDesc = "You spent years learning the lore of the multiverse. You scoured manuscripts, studied scrolls, and listened to the " + "\n" + 
                                        "   greatest experts on the subjects that interest you. Your efforts have made you a master in your fields of study.";

                String specDesc = "";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I use polysyllabic words that convey the impression of great erudition.";
                        specDesc = ("Alchemist.");
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I've read every book in the world’s greatest libraries — or I like to boast that I have.";
                        specDesc = ("Astronomer.");
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I'm used to helping out those who aren’t as smart as I am, and I patiently explain anything " + "\n" + 
                                                            "   and everything to others.";
                        specDesc = ("Discredited academic.");
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "There’s nothing I like more than a good mystery.";
                        specDesc = ("Librarian.");
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I’m willing to listen to every side of an argument before I make my own judgment.";
                        specDesc = ("Professor.");
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I . . . speak . . . slowly . . . when talking . . . to idiots, . . . which . . . almost . . . everyone " + "\n" + 
                                                    "   . . . is . . . compared . . . to me.";
                        specDesc = ("Researcher.");
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I am horribly, horribly awkward in social situations.";
                        specDesc = ("Wizard’s apprentice.");
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I’m convinced that people are always trying to steal my secrets.";
                        specDesc = ("Scribe.");
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Knowledge. The path to power and self-improvement is through knowledge. (Neutral)";
                        inChar.charBackground.bond = "It is my duty to protect my students.";
                        inChar.charBackground.flaw = "I am easily distracted by the promise of information."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Beauty. What is beautiful points us beyond itself toward what is true. (Good)";
                        inChar.charBackground.bond = "I have an ancient text that holds terrible secrets that must not fall into the wrong hands.";
                        inChar.charBackground.flaw = "Most people scream and run when they see a demon. I stop and take notes on its anatomy.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Logic. Emotions must not cloud our logical thinking. (Lawful)";
                        inChar.charBackground.bond = "I work to preserve a library, university, scriptorium, or monastery.";
                        inChar.charBackground.flaw = "Unlocking an ancient mystery is worth the price of a civilization.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "No Limits. Nothing should fetter the infinite possibility inherent in all existence. (Chaotic)";
                        inChar.charBackground.bond = "My life’s work is a series of tomes related to a specific field of lore.";
                        inChar.charBackground.flaw = "I overlook obvious solutions in favor of complicated ones.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Power. Knowledge is the path to power and domination. (Evil)";
                        inChar.charBackground.bond = "I've been searching my whole life for the answer to a certain question.";
                        inChar.charBackground.flaw = "I speak without really thinking through my words, invariably insulting others.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Self-Improvement. The goal of a life of study is the betterment of oneself. (Any)";
                        inChar.charBackground.bond = "I sold my soul for knowledge. I hope to do great deeds and win it back.";
                        inChar.charBackground.flaw = "I can’t keep a secret to save my life, or anyone else’s.";
                        break;
                }

                //Features
                    //Researcher
                features researcher = new features();
                inChar.features.add(researcher);
                researcher.setName("Researcher");
                researcher.setDesc("When you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom " + "\n" + 
                                "   you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person " + "\n" + 
                                "   or creature. Your DM might rule that the knowledge you seek is secreted away in an almost inaccessible place, or that it simply " + "\n" + 
                                "   cannot be found. Unearthing the deepest secrets of the multiverse can require an adventure or even a whole campaign.");      
                                
                    //Specialty
                features specialty = new features();
                inChar.features.add(specialty);
                specialty.setName("Specialty");
                specialty.setDesc("Your scholarly training has made you a master in your field of study. " + "\n" + 
                                    "   The nature of your scholarly training is: " + specDesc);

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Arcana") == -1)
                {
                    inChar.charProf.skillProf.add("Arcana");
                }
                if (inChar.charProf.skillProf.indexOf("History") == -1)
                {
                    inChar.charProf.skillProf.add("History");
                }            

                //Adding two Languages 
                languages newSageLanguage = new languages();
                for (int i = 0; i < 2; i++)     //adds two
                {
                    String newLangauge = newSageLanguage.getRandomLanguage();
                    boolean added = false;
                    while (!added)      //to make sure the new langauge isnt already in the list
                    {
                        if (inChar.charLanguages.indexOf(newLangauge) == -1)
                        {
                            inChar.charLanguages.add(newLangauge);
                            added = true;
                        }
                        else
                        {
                            newLangauge = newSageLanguage.getRandomLanguage();
                        }
                    }
                }

                //Adding Equipment
                inChar.equipment.add("A bottle of black ink");
                inChar.equipment.add("A quill");               
                inChar.equipment.add("A small knife");
                inChar.equipment.add("A letter from a dead colleague posing a question you have not yet been able to answer");
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 10 gp");
                break;
            }
            case "Sailor":
            {
                inChar.charBackground.backName = "Sailor";
                inChar.charBackground.backDesc = "You sailed on a seagoing vessel for years. In that time, you faced down mighty storms, monsters of the deep, and " + "\n" + 
                                        "   those who wanted to sink your craft to the bottomless depths. Your first love is the distant line of the horizon, " + "\n" + 
                                        "   but the time has come to try your hand at something new.";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "My friends know they can rely on me, no matter what.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I work hard so that I can play hard when the work is done.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I enjoy sailing into new ports and making new friends over a flagon of ale.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I stretch the truth for the sake of a good story.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "To me, a tavern brawl is a nice way to get to know a new city.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I never pass up a friendly wager.";
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "My language is as foul as an otyugh nest.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I like a job well done, especially if I can convince someone else to do it.";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Respect. The thing that keeps a ship together is mutual respect between captain and crew. (Good)";
                        inChar.charBackground.bond = "I’m loyal to my captain first, everything else second.";
                        inChar.charBackground.flaw = "I follow orders, even if I think they’re wrong."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Fairness. We all do the work, so we all share in the rewards. (Lawful)";
                        inChar.charBackground.bond = "The ship is most important—crewmates and captains come and go.";
                        inChar.charBackground.flaw = "I’ll say anything to avoid having to do extra work.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Freedom. The sea is freedom—the freedom to go anywhere and do anything. (Chaotic)";
                        inChar.charBackground.bond = "I’ll always remember my first ship.";
                        inChar.charBackground.flaw = "Once someone questions my courage, I never back down no matter how dangerous the situation.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Mastery. I’m a predator, and the other ships on the sea are my prey. (Evil)";
                        inChar.charBackground.bond = "In a harbor town, I have a paramour whose eyes nearly stole me from the sea.";
                        inChar.charBackground.flaw = "Once I start drinking, it’s hard for me to stop.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "People. I’m committed to my crewmates, not to ideals. (Neutral)";
                        inChar.charBackground.bond = "I was cheated out of my fair share of the profits, and I want to get my due.";
                        inChar.charBackground.flaw = "I can’t help but pocket loose coins and other trinkets I come across.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Aspiration. Someday I’ll own my own ship and chart my own destiny. (Any)";
                        inChar.charBackground.bond = "Ruthless pirates murdered my captain and crewmates, plundered our ship, and left me to die. Vengeance will be mine.";
                        inChar.charBackground.flaw = "My pride will probably lead to my destruction.";
                        break;
                }

                //Features
                    //Ship’s Passage
                features shipPassage = new features();
                inChar.features.add(shipPassage);
                shipPassage.setName("Ship's Passage");
                shipPassage.setDesc("When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might " + "\n" + 
                                "   sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). " + "\n" + 
                                "   Because you’re calling in a favor, you can’t be certain of a schedule or route that will meet your every need. Your Dungeon " + "\n" + 
                                "   Master will determine how long it takes to get where you need to go. In return for your free passage, you and your companions " + "\n" + 
                                "   are expected to assist the crew during the voyage.");        

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Athletics") == -1)
                {
                    inChar.charProf.skillProf.add("Athletics");
                }
                if (inChar.charProf.skillProf.indexOf("Perception") == -1)
                {
                    inChar.charProf.skillProf.add("Perception");
                }            

                //Adding tool prof
                if (inChar.charProf.toolProf.indexOf("Navigator’s tools") == -1)     //pretty sure this is the only place where you can get these prof but check anyway
                {
                    inChar.charProf.toolProf.add("Navigator’s tools");
                }
                if (inChar.charProf.toolProf.indexOf("Vehicles (water)") == -1)
                {
                    inChar.charProf.toolProf.add("Vehicles (water)");
                }

                //Adding Equipment
                inChar.equipment.add("A belaying pin (club)");
                inChar.equipment.add("50 feet o f silk rope");               
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 10 gp");
                    //A lucky charm (Trinket)
                trinkets sailorTrinket = new trinkets();
                inChar.equipment.add(sailorTrinket.getTrinket());
                break;
            }    
            case "Soldier":
            {
                inChar.charBackground.backName = "Soldier";
                inChar.charBackground.backDesc = "War has been your life for as long as you care to remember. You trained as a youth, studied the use of weapons and " + "\n" + 
                                        "   armor, learned basic survival techniques, including how to stay alive on the battlefield. You might have been part " + "\n" + 
                                        "   of a standing national army or a mercenary company, or perhaps a member of a local militia who rose to prominence " + "\n" + 
                                        "   during a recent war. When you choose this background, work with your DM to determine which military organization " + "\n" + 
                                        "   you were a part of, how far through its ranks you progressed, and what kind of experiences you had during your military career.";
                
                String specName = "";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I'm always polite and respectful.";
                        specName = ("Officer.");
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I’m haunted by memories of war. I can’t get the images of violence out of my mind.";
                        specName = ("Scout.");
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I’ve lost too many friends, and I’m slow to make new ones.";
                        specName = ("Infantry.");
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I’m full of inspiring and cautionary tales from my military experience relevant to almost every combat situation.";
                        specName = ("Cavalry.");
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I can stare down a hell hound without flinching.";
                        specName = ("Healer.");
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I enjoy being strong and like breaking things.";
                        specName = ("Quartermaster.");
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I have a crude sense of humor.";
                        specName = ("Standard bearer.");
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I face problems head-on. A simple, direct solution is the best path to success.";
                        specName = ("Support staff (cook, blacksmith, or the like).");
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Greater Good. Our lot is to lay down our lives in defense of others. (Good)";
                        inChar.charBackground.bond = "I would still lay down my life for the people I served with.";
                        inChar.charBackground.flaw = "The monstrous enemy we faced in battle still leaves me quivering with fear."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Responsibility. I do what I must and obey just authority. (Lawful)";
                        inChar.charBackground.bond = "Someone saved my life on the battlefield. To this day, I will never leave a friend behind.";
                        inChar.charBackground.flaw = "I have little respect for anyone who is not a proven warrior.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Independence. When people follow orders blindly, they embrace a kind of tyranny. (Chaotic)";
                        inChar.charBackground.bond = "My honor is my life.";
                        inChar.charBackground.flaw = "I made a terrible mistake in battle cost many lives — and I would do anything to keep that mistake secret.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Might. In life as in war, the stronger force wins. (Evil)";
                        inChar.charBackground.bond = "I’ll never forget the crushing defeat my company suffered or the enemies who dealt it.";
                        inChar.charBackground.flaw = "My hatred of my enemies is blind and unreasoning.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "Live and Let Live. Ideals aren’t worth killing over or going to war for. (Neutral)";
                        inChar.charBackground.bond = "Those who fight beside me are those worth dying for.";
                        inChar.charBackground.flaw = "I obey the law, even if the law causes misery.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Nation. My city, nation, or people are all that matter. (Any)";
                        inChar.charBackground.bond = "I fight for those who cannot fight for themselves.";
                        inChar.charBackground.flaw = "I’d rather eat my armor than admit when I’m wrong.";
                        break;
                }

                //Features
                    //Military Rank
                features milRank = new features();
                inChar.features.add(milRank);
                milRank.setName("Military Rank");
                milRank.setDesc("You have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize " + "\n" + 
                                "   your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence " + "\n" + 
                                "   over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly " + "\n" + 
                                "   military encampments and fortresses where your rank is recognized.");        
                                
                    //Specialty
                features specialty = new features();
                inChar.features.add(specialty);
                specialty.setName("Specialty");
                specialty.setDesc("During your time as a soldier, you had a specific role to play in your unit or army." + "\n" + 
                                "   Your role was: " + specName);
                
                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Athletics") == -1)
                {
                    inChar.charProf.skillProf.add("Athletics");
                }
                if (inChar.charProf.skillProf.indexOf("Intimidation") == -1)
                {
                    inChar.charProf.skillProf.add("Intimidation");
                }            

                //Adding tool prof
                    //One type of gaming set
                List<String> gamingSet = Arrays.asList("Dice Set", "Dragonchess Set", "Playing Card Set", "Three-Dragon Ante Set");
                inChar.charProf.toolProf.add(gamingSet.get(numGen.ints(0, 4).findFirst().getAsInt()));   
                if (inChar.charProf.toolProf.indexOf("Vehicles (land)") == -1)
                {
                    inChar.charProf.toolProf.add("Vehicles (land)");
                }
                
                //Adding Equipment
                inChar.equipment.add("An insignia of rank");
                inChar.equipment.add("A trophy taken from a fallen enemy (a dagger, broken blade, or piece of a banner)");       
                inChar.equipment.add("A set of bone dice or deck of cards");
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 10 gp");

                break;
            }  
            case "Urchin":
            {
                inChar.charBackground.backName = "Urchin";
                inChar.charBackground.backDesc = "You grew up on the streets alone, orphaned, and poor. You had no one to watch over you or to provide for you, " + "\n" + 
                                        "   so you learned to provide for yourself. You fought fiercely over food and kept a constant watch out for other " + "\n" + 
                                        "   desperate souls who might steal from you. You slept on rooftops and in alleyways, exposed to the elements, and " + "\n" + 
                                        "   endured sickness without the advantage of medicine or a place to recuperate. You’ve survived despite all odds, "  + "\n" + 
                                        "   and did so through cunning, strength, speed, or some combination of each.";
                
                //Add personality trait
                d8 = numGen.ints(0, 8).findFirst().getAsInt();
                switch (d8)
                {
                    case 0:
                        inChar.charBackground.personalTrait = "I hide scraps of food and trinkets away in my pockets.";
                        break;
                    case 1: 
                        inChar.charBackground.personalTrait = "I ask a lot of questions.";
                        break;
                    case 2: 
                        inChar.charBackground.personalTrait = "I like to squeeze into small places where no one else can get to me.";
                        break;
                    case 3: 
                        inChar.charBackground.personalTrait = "I sleep with my back to a wall or tree, with everything I own wrapped in a bundle in my arms.";
                        break;
                    case 4: 
                        inChar.charBackground.personalTrait = "I eat like a pig and have bad manners.";
                        break;
                    case 5:
                        inChar.charBackground.personalTrait = "I think anyone who’s nice to me is hiding evil intent.";;
                        break;
                    case 6: 
                        inChar.charBackground.personalTrait = "I don’t like to bathe.";
                        break;
                    case 7: 
                        inChar.charBackground.personalTrait = "I bluntly say what other people are hinting at or hiding.";
                        break;
                }
                //Add ideals, bonds, and flaws
                d6 = numGen.ints(0, 6).findFirst().getAsInt();      //get char's alignment to choose ideals?
                switch (d6)
                {
                    case 0:
                        inChar.charBackground.ideal = "Respect. All people, rich or poor, deserve respect. (Good)";
                        inChar.charBackground.bond = "My town or city is my home, and I’ll fight to defend it.";
                        inChar.charBackground.flaw = "If I'm outnumbered, I will run away from a fight."; 
                        break;
                    case 1:
                        inChar.charBackground.ideal = "Community. We have to take care of each other, because no one else is going to do it. (Lawful)";
                        inChar.charBackground.bond = "I sponsor an orphanage to keep others from enduring what I was forced to endure.";
                        inChar.charBackground.flaw = "Gold seems like a lot of money to me, and I’ll do just about anything for more of it.";
                        break;
                    case 2:
                        inChar.charBackground.ideal = "Change. The low are lifted up, and the high and mighty are brought down. Change is the nature of things. (Chaotic)";
                        inChar.charBackground.bond = "I owe my survival to another urchin who taught me to live on the streets.";
                        inChar.charBackground.flaw = "I will never fully trust anyone other than myself.";
                        break;
                    case 3:
                        inChar.charBackground.ideal = "Retribution. The rich need to be shown what life and death are like in the gutters. (Evil)";
                        inChar.charBackground.bond = "I owe a debt I can never repay to the person who took pity on me..";
                        inChar.charBackground.flaw = "I’d rather kill someone in their sleep then fight fair.";
                        break;
                    case 4:
                        inChar.charBackground.ideal = "People. I help the people who help me — that’s what keeps us alive. (Neutral)";
                        inChar.charBackground.bond = "I escaped my life of poverty by robbing an important person, and I’m wanted for it.";
                        inChar.charBackground.flaw = "It’s not stealing if I need it more than someone else.";
                        break;
                    case 5:
                        inChar.charBackground.ideal = "Aspiration. I'm going to prove that I'm worthy of a better life.";
                        inChar.charBackground.bond = "No one else should have to endure the hardships I’ve been through.";
                        inChar.charBackground.flaw = "People who can't take care of themselves get what they deserve.";
                        break;
                }

                //Features
                    //City Secrets
                features citySecrets = new features();
                inChar.features.add(citySecrets);
                citySecrets.setName("City Secrets");
                citySecrets.setDesc("You know the secret patterns and flow to cities and can find passages through the urban sprawl that others would miss." + "\n" +  
                                    "   When you are not in combat, you (and companions you lead) can travel between any two locations in the city twice as " + "\n" + 
                                    "   fast as your speed would normally allow.");        

                //Adding skills
                if (inChar.charProf.skillProf.indexOf("Sleight of Hand") == -1)
                {
                    inChar.charProf.skillProf.add("Sleight of Hand");
                }
                if (inChar.charProf.skillProf.indexOf("Stealth") == -1)
                {
                    inChar.charProf.skillProf.add("Stealth");
                }            

                //Adding tool prof  
                if (inChar.charProf.toolProf.indexOf("Disguise kit") == -1)
                {
                    inChar.charProf.toolProf.add("Disguise kit");
                }
                if (inChar.charProf.toolProf.indexOf("Thieves’ tools") == -1)
                {
                    inChar.charProf.toolProf.add("Thieves’ tools");
                }

                //Adding Equipment
                inChar.equipment.add("A small knife");
                inChar.equipment.add("A map of the city you grew up in");       
                inChar.equipment.add("A pet mouse");
                inChar.equipment.add("A token to remember your parents by");
                inChar.equipment.add("A set of common clothes");
                inChar.equipment.add("Add to Gold: A belt pouch containing 10 gp");

                break;
            } 
        }
    }
}









class abilityMods   //could have kept in ability Scores, but this makes it easier to see / access in the Create character class
{

    int strengthMod;
    int dexterityMod;
    int constitutionMod;
    int intelligenceMod;
    int wisdomMod;
    int charismaMod;

    public abilityMods()
    {
        this.strengthMod = 0;
        this.dexterityMod = 0;
        this.constitutionMod = 0;
        this.intelligenceMod = 0;
        this.wisdomMod = 0;
        this.charismaMod = 0;
    }

    public void setAbilityMod(String inAbility, int inScore)
    {
        double modScore = inScore;
        modScore = modScore - 10;
        modScore = (modScore / 2);
        modScore = Math.floor(modScore);
        int mod = (int) modScore;

        switch(inAbility)
        {
            case "Strength":
                this.strengthMod = mod;
                break;
            case "Dexterity":
                this.dexterityMod = mod;
                break;
            case "Constitution":
                this.constitutionMod = mod;
                break;
            case "Intelligence":
                this.intelligenceMod = mod;
                break;
            case "Wisdom":
                this.wisdomMod = mod;
                break;
            case "Charisma":
                this.charismaMod = mod;
                break;
            default:
                //should never be default
        }
    }

    public int getAbilityMod(String inAbility)
    {
        switch(inAbility)
        {
            case "Strength":
                return this.strengthMod;
            case "Dexterity":
                return this.dexterityMod;
            case "Constitution":
                return this.constitutionMod;
            case "Intelligence":
                return this.intelligenceMod;
            case "Wisdom":
                return this.wisdomMod;
            case "Charisma":
                return this.charismaMod;
            default:
                return 0; //should never hit default
        }
    }
}







class abilityScores
{
    int strength;
    int dexterity;
    int constitution;
    int intelligence;
    int wisdom;
    int charisma;
    
    ArrayList<Integer> intAbilityScores;
    
    public abilityScores()
    {
        this.strength = 0;
        this.dexterity = 0;
        this.constitution = 0;
        this.intelligence = 0;
        this.wisdom = 0;
        this.charisma = 0;
        intAbilityScores = new ArrayList<Integer>();
    }

    public int getAbilityScore(String inAbility)
    {
        switch(inAbility)
        {
            case "Strength":
                return this.strength;
            case "Dexterity":
                return this.dexterity;
            case "Constitution":
                return this.constitution;
            case "Intelligence":
                return this.intelligence;
            case "Wisdom":
                return this.wisdom;
            case "Charisma":
                return this.charisma;
            default:
                return 0; //should never hit default
        }
    }

    public void setAbilityScore(String inAbility, int inScore)
    {
        switch(inAbility)
        {
            case "Strength":
                this.strength = inScore;
                break;
            case "Dexterity":
                this.dexterity = inScore;
                break;
            case "Constitution":
                this.constitution = inScore;
                break;
            case "Intelligence":
                this.intelligence = inScore;
                break;
            case "Wisdom":
                this.wisdom = inScore;
                break;
            case "Charisma":
                this.charisma = inScore;
                break;
            default:
                //should never be default
        }
    }

    public ArrayList<Integer> getAbilityInts()
    {
        return intAbilityScores;
    }

    public void sortAbilityScores(int inScore)
    {
        intAbilityScores.add(inScore);
        if (intAbilityScores.size() == 6)          //once all ability scores are rolled, sort
        {
            Collections.sort(intAbilityScores);
            Collections.reverse(intAbilityScores);      //at this point all int ability scores are sorted highest to lowest
            //findMods();
        }
    }

    public void printAbilityScores(characterCreater inChar)
    {
        System.out.println("Ability Scores: ");
        System.out.println("Strength: " + getAbilityScore("Strength") + "       Mod: " + inChar.abilityMods.getAbilityMod("Strength"));
        System.out.println("Constitution: " + getAbilityScore("Constitution") + "   Mod: " + inChar.abilityMods.getAbilityMod("Constitution"));
        System.out.println("Dexterity: " + getAbilityScore("Dexterity") + "      Mod: " + inChar.abilityMods.getAbilityMod("Dexterity"));
        System.out.println("Intelligence: " + getAbilityScore("Intelligence") + "   Mod: " + inChar.abilityMods.getAbilityMod("Intelligence"));
        System.out.println("Wisdom: " + getAbilityScore("Wisdom") + "         Mod: " + inChar.abilityMods.getAbilityMod("Wisdom"));
        System.out.println("Charisma: " + getAbilityScore("Charisma") + "       Mod: " + inChar.abilityMods.getAbilityMod("Charisma"));
    }
}







class weapon
{
    String name;
    String damage;
    int amount;
    Random numGen = new Random();

    public weapon()
    {
        this.name = "";
        this.damage = "";
        this.amount = 0; 
    }

    public weapon(String inName, String inDamage)
    {
        this.name = inName;
        this.damage = inDamage;
    }

    public weapon(String inName, int inAmmo)
    {
        this.name = inName;
        this.amount = inAmmo;
    }

    public String getWeaponName()
    {
        return this.name;
    }

    public String getWeaponDamage()
    {
        return this.damage;
    }

    private void setAmount(int inAmount)
    {
        this.amount = inAmount;
    }

    public weapon getSpecificWeapon(String inType, String inName, int inAmount)
    {
        weapon newWeapon = new weapon();
        boolean matched = false;
        int i = 0;
        if (inType.equals("Simple Melee"))                                      //only makes the list type of the weapon asked for
        {
            ArrayList<weapon> simpleMeleeList = buildSimpleMeleeList();
            do
            {
                newWeapon = simpleMeleeList.get(i);
                if(newWeapon.getWeaponName().equals(inName))
                {
                    //newWeapon = weaponItr;
                    newWeapon.setAmount(inAmount);
                    matched = true;
                }
                i++;
            } while (!matched);
        }
        else if(inType.equals("Simple Ranged"))
        {
            ArrayList<weapon> simpleRangedList = buildSimpleRangedList();
            do
            {
                newWeapon = simpleRangedList.get(i);
                if (newWeapon.getWeaponName().equals(inName))
                {
                    //newWeapon = weaponItr;
                    newWeapon.setAmount(inAmount);
                    matched = true;
                }
                i++;
            } while (!matched);
        }
        else if (inType.equals("Martial Melee"))
        {
            ArrayList<weapon> martialMeleeList = buildMartialMeleeList();
            do
            {
                newWeapon = martialMeleeList.get(i);
                if(newWeapon.getWeaponName().equals(inName))
                {
                    //newWeapon = weaponItr;
                    newWeapon.setAmount(inAmount);
                    matched = true;
                }
                i++;
            } while (!matched);
        }
        else if (inType.equals("Martial Ranged"))
        {
            ArrayList<weapon> martialRangedList = buildMartialRangedList();
            do
            {
                newWeapon = martialRangedList.get(i);
                if (newWeapon.getWeaponName().equals(inName))
                {
                    //newWeapon = weaponItr;
                    newWeapon.setAmount(inAmount);
                    matched = true;
                }
                i++;
            } while (!matched);     
        }
        else if (inType.equals("Ammunition"))
        {
            ArrayList<weapon> ammunitionList = buildAmmunitionList();
            do
            {
                newWeapon = ammunitionList.get(i);
                if (newWeapon.getWeaponName().equals(inName))
                {
                    //newWeapon = weaponItr;
                    newWeapon.setAmount(inAmount);
                    matched = true;
                }
                i++;
            } while (!matched);
        }
        return newWeapon;
    }

    public weapon getMelee()
    {
        ArrayList<weapon> meleeList = buildSimpleMeleeList();

        int simpMeleeWeaponNum = numGen.ints(0, 11).findFirst().getAsInt();
        weapon newWeapon = meleeList.get(simpMeleeWeaponNum);                     //choose random, then return
        newWeapon.amount = 1;
        return newWeapon;
    }

    public weapon getRanged()
    {
        ArrayList<weapon> rangedList = buildSimpleRangedList();

        int simpRangedWeaponNum = numGen.ints(0, 4).findFirst().getAsInt();
        weapon newWeapon = rangedList.get(simpRangedWeaponNum);                     //choose random, then return
        newWeapon.amount = 1;
        return newWeapon;
    }

    public weapon getMartialMelee()
    {
        ArrayList<weapon> martialMeleeList = buildMartialMeleeList();

        int weaponNum = numGen.ints(0, 18).findFirst().getAsInt();
        weapon newWeapon = martialMeleeList.get(weaponNum);                     //choose random, then return
        newWeapon.amount = 1;
        return newWeapon;
    }

    public weapon getMartialRanged()
    {
        ArrayList<weapon> martialRangedList = buildMartialMeleeList();

        int weaponNum = numGen.ints(0, 5).findFirst().getAsInt();
        weapon newWeapon = martialRangedList.get(weaponNum);                     //choose random, then return
        newWeapon.amount = 1;
        return newWeapon;
    }

    public ArrayList<weapon> buildSimpleMeleeList()
    {
        ArrayList<weapon> simpleMeleeList = new ArrayList<weapon>();

        weapon club = new weapon("Club", "1d4 Bludgeoning");
        weapon dagger = new weapon("Dagger", "1d4 Piercing");
        weapon greatclub = new weapon("Greatclub", "1d8 Bludgeoning");
        weapon handaxe = new weapon("Handaxe", "1d6 Slashing");
        weapon javelin = new weapon("Javelin", "1d6 Piercing");
        weapon lightHammer = new weapon("Light Hammer", "1d4 Bludgeoning");
        weapon mace = new weapon("Mace", "1d6 Bludeoning");
        weapon quarterstaff = new weapon("Quarterstaff", "1d6 Bludgeoning");
        weapon sickle = new weapon("Sickle", "1d4 Slashing");
        weapon spear = new weapon("Spear", "1d6 Piercing");
        weapon unarmed = new weapon("Unarmed Strike", "1 bludgeoning");


        List<weapon> simpleMeleeListArray = Arrays.asList(club, dagger, greatclub, handaxe, javelin, lightHammer, mace, quarterstaff, sickle, spear, unarmed);
        simpleMeleeList.addAll(simpleMeleeListArray); 
        return simpleMeleeList;
    }

    public ArrayList<weapon> buildSimpleRangedList()
    {
        ArrayList<weapon> simpleRangedList = new ArrayList<weapon>(); 

        weapon crossbow_light = new weapon("Crossbow - Light", "1d8 Piercing");
        weapon dart = new weapon("Dart", "1d4 Piercing");
        weapon shortbow = new weapon("Shortbow", "1d6 Piercing");
        weapon sling = new weapon("Sling", "1d4 Bludgeoning");

        List<weapon> simpleRangedListArray = Arrays.asList(crossbow_light, dart, shortbow, sling);   //throw weapons into array, then into arraylist
        simpleRangedList.addAll(simpleRangedListArray); 
        return simpleRangedList;
    }

    public ArrayList<weapon> buildMartialMeleeList()
    {
        ArrayList<weapon> martialMeleeList = new ArrayList<weapon>();

        weapon battleaxe = new weapon("Battleaxe", "1d8 Slashing");
        weapon flail = new weapon("Flail", "1d8 Bludgeoning");
        weapon glaive = new weapon("Glaive", "1d10 Slashing");
        weapon greataxe = new weapon("Greataxe", "1d12 Slashing");
        weapon greatsword = new weapon("Greatsword", "2d6 Slashing");
        weapon halberd = new weapon("Halberd", "1d10 Slashing");
        weapon lance = new weapon("Lance", "1d12 Piercing");
        weapon longsword = new weapon("Longsword", "1d8 Slashing");
        weapon maul = new weapon("Maul", "2d6 Bludgeoning");
        weapon morningstar = new weapon("Morningstar", "1d8 Piercing");
        weapon pike = new weapon("Pike", "1d10 Piercing");
        weapon rapier = new weapon("Rapier", "1d8 Piercing");
        weapon scimitar = new weapon("Scimitar", "1d6 Slashing");
        weapon shortsword = new weapon("Shortsword", "1d6 Piercing");
        weapon trident = new weapon("Trident", "1d6 Piercing");
        weapon war_pick = new weapon("War Pick", "1d8 Piercing");
        weapon warhammer = new weapon("Warhammer", "1d8 Bludgeoning");
        weapon whip = new weapon("Whip", "1d4 Slashing");

        List<weapon> martialMeleeListArray = Arrays.asList(battleaxe, flail, glaive, greataxe, greatsword, halberd, lance, longsword, maul, morningstar, pike, rapier, 
                                                                 scimitar, shortsword, trident, war_pick, warhammer, whip);
        
        martialMeleeList.addAll(martialMeleeListArray); 
        return martialMeleeList;
    }

    public ArrayList<weapon> buildMartialRangedList()
    {
        ArrayList<weapon> martialRangedList = new ArrayList<weapon>();

        weapon blowgun = new weapon("Blowgun", "1 Piercing");
        weapon crossbow_hand = new weapon("Crossbow - Hand", "1d6 Piercing");
        weapon crossbow_heavy = new weapon("Crossbow - Heavy", "1d10 Piercing");
        weapon longbow = new weapon("Longbow", "1d8 Piercing");
        weapon net = new weapon("Net", "-");

        List<weapon> martialRangedListArray = Arrays.asList(blowgun, crossbow_hand, crossbow_heavy, longbow, net);
        
        martialRangedList.addAll(martialRangedListArray);
        return martialRangedList;
    }

    public ArrayList<weapon> buildAmmunitionList()
    {
        ArrayList<weapon> ammunitionList = new ArrayList<weapon>();

        weapon arrows = new weapon("Arrows", 20);
        weapon blowgun_needles = new weapon("Blowgun Needels", 50);
        weapon crossbow_bolts = new weapon("Crossbow Bolts", 20);
        weapon sling_bullets = new weapon("Sling Bullets", 20);

        List<weapon> ammunitionListArray = Arrays.asList(arrows, blowgun_needles, crossbow_bolts, sling_bullets);

        ammunitionList.addAll(ammunitionListArray);
        return ammunitionList;
    }
}









class languages
{
    String lanName;
    Random numGen = new Random();

    public languages()
    {
        this.lanName = "";
    }

    public String getRandomLanguage()
    {
        String standard = getSingleExoticLanguage();
        String exotic = getSingleExoticLanguage();

        if (numGen.ints(0, 10).findFirst().getAsInt() == 0)     // 1 / 10 chance for exotic 
        {
            return exotic;
        }
        else
        {
            return standard;
        }
    }

    public ArrayList<String> getStandardLanguageList()
    {
        this.lanName = "Standard List";
        List<String> strd = Arrays.asList("Common", "Dwarvish", "Elvish", "Giant", "Gnomish", "Goblin", "Halfling", "Orc");
        ArrayList<String> strdList = new ArrayList<String>();
        strdList.addAll(strd);
        return strdList;
    }

    public ArrayList<String> getExoticLanguageList()
    {
        this.lanName = "Exotic List";
        List<String> exotic = Arrays.asList("Abyssal", "Celestial", "Draconic", "Deep Speech", "Infernal", "Primordial", "Sylvan", "Undercommon");
        ArrayList<String> exoticList = new ArrayList<String>();
        exoticList.addAll(exotic);
        return exoticList;
    }

    public String getSingleStandardLanguage()
    {
        this.lanName = "Standard";
        ArrayList<String> strdList = getStandardLanguageList();
        int newLanguage = numGen.ints(0, strdList.size()).findFirst().getAsInt();  
        String outLanguage = strdList.get(newLanguage);
        return outLanguage;
    }

    public String getSingleExoticLanguage()
    {
        this.lanName = "Exotic";
        ArrayList<String> exoticList = getExoticLanguageList();
        int newLanguage = numGen.ints(0, exoticList.size()).findFirst().getAsInt();  
        String outLanguage = exoticList.get(newLanguage);
        return outLanguage;
    }
}













class features
{
    String featName;
    String desc;
    int uses;

    public features()
    {
        this.featName = "";
        this.desc = "";
        this.uses = 0;
    }

    public String getName()
    {
        return this.featName;
    }

    public void setName(String inName)
    {
        this.featName = inName;
    }

    public String getDesc()
    {
        return this.desc;
    }

    public void setDesc(String inDesc)
    {
        this.desc = inDesc;
    }

    public int getUses()
    {
        return this.uses;
    }

    public void setUses(int inUses)
    {
        this.uses = inUses;
    }

    public void printFeatures(characterCreater inChar)
    {
        System.out.println("-----Features Start----- ");
        for (int i = 0; i < inChar.features.size(); i++)
        {
            System.out.println("Name: " + inChar.features.get(i).getName());
            System.out.println("Description: " + inChar.features.get(i).getDesc());
            if (i == (inChar.features.size() - 1))
            {
                System.out.println("-----Features End-----");
            }
            else
            {
                System.out.println(""); //for readability
            }
            
        }
    }
}







class racialTraits
{
    int age;
    int speed;
    String alignment;
    String size;
    String skinTone;
    String hairColor;
    String eyeColor;

    

    public racialTraits()
    {
        this.age = 0;
        this.speed = 0;
        this.alignment = "";
        this.size = "";
        this.skinTone = "";
        this.hairColor = "";
        this.eyeColor = "";
    }

    public void setAge(int inAge)
    {
        this.age = inAge;
    }

    public void setSpeed(int inSpeed)
    {
        this.speed = inSpeed;
    }

    public void setAlignment(String inAlignment)
    {
        this.alignment = inAlignment;
    }

    public void setSize(String inSize)
    {
        this.size = inSize;
    }

    public void setSkinTone(String inTone)
    {
        this.skinTone = inTone;
    }

    public void setHairColor(String inColor)
    {
        this.hairColor = inColor;
    }

    public void setEyeColor()
    {
        List<String> standardEyeColors = Arrays.asList("Brown", "Blue", "Green", "Amber", "Gray", "Hazel");
        List<String> rareEyeColors = Arrays.asList("Red", "Violet", "Pale White", "Void Black", "Electric Blue", "Reflects Mood", "Heterochromia");
        Random numGen = new Random();
        if (numGen.ints(0, 9).findFirst().getAsInt() == 0) // 1/10 chance for rare eye color
        {
            this.eyeColor = rareEyeColors.get(numGen.ints(0, 7).findFirst().getAsInt());
        }
        else
        {
            this.eyeColor = standardEyeColors.get(numGen.ints(0, 6).findFirst().getAsInt());
        }
    }

    public ArrayList<String> getEyeColors(String rarity)
    {
        List<String> standardEyeColors = Arrays.asList("Brown", "Blue", "Green", "Amber", "Gray", "Hazel");
        List<String> rareEyeColors = Arrays.asList("Red", "Violet", "Pale White", "Void Black", "Electric Blue", "Reflects Mood", "Heterochromia");
        ArrayList<String> eyeColors = new ArrayList<String>();

        switch (rarity)
        {
            case "Standard":
            {
                eyeColors.addAll(standardEyeColors);
                break;
            }
            case "Rare":
            {
                eyeColors.addAll(rareEyeColors);
                break;
            }
            case "Both":
            {
                eyeColors.addAll(standardEyeColors);
                eyeColors.addAll(rareEyeColors);
                break;
            }
        }
        return eyeColors;
    }

    public void setSpecificColor(String inColor)
    {
        this.eyeColor = inColor;
    }

    //rest are lists of names for the races
    public ArrayList<String> buildDwarfNameList(String inGender)
    {
        ArrayList<String> dwarfNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male": 
            {
                List<String> maleNames = Arrays.asList("Adrik", "Alberich", "Baern", "Barendd", "Brottor", "Bruenor",
                        "Dain", "Darrak", "Delg", "Eberk", "Einkil", "Fargrim", "Flint", "Gardain", "Harbek", "Kildrak",
                        "Morgran", "Orsik", "Oskar", "Rangrim", "Rurik", "Taklinn", "Thoradin", "Thorin", "Tordek",
                        "Traubon", "Travok", "Ulfgar", "Veit", "Vondal");
                dwarfNames.addAll(maleNames);
                return dwarfNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Amber", "Artin", "Audhild", "Bardryn", "Dagnal", "Diesa",
                        "Eldeth", "Falkrunn", "Finellen", "Gunnloda", "Gurdis", "Helja", "Hlin", "Kathra", "Kristryd",
                        "Ilde", "Liftrasa", "Mardred", "Riswynn", "Sannl", "Torbera", "Torgga", "Vistra");
                dwarfNames.addAll(femaleNames);
                return dwarfNames;
            }
            case "Clan":
            {
                List<String> clanNames = Arrays.asList("Balderk", "Battlehammer", "Brawnanvil", "Dankil", "Fireforge",
                        "Frostbeard", "Gorunn", "Holderhek", "Ironfist", "Loderr", "Lutgehr", "Rumnaheim", "Strakeln",
                        "Torunn", "Ungart");
                dwarfNames.addAll(clanNames);
                return dwarfNames;
            }
        }
        return dwarfNames;
    }

    public ArrayList<String> buildElfNameList(String inGender)
    {
        ArrayList<String> elfNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Adran", "Aelar", "Aramil", "Arannis", "Aust", "Beiro",
                        "Berrian", "Carric", "Enialis", "Erdan", "Erevan", "Galinndan", "Hadarai", "Heian", "Himo",
                        "Immeral", "Ivellios", "Laucian", "Mindartis", "Paelias", "Peren", "Quarion", "Riardon",
                        "Rolen", "Soveliss", "Thamior", "Tharivol", "Theren", "Varis");
                elfNames.addAll(maleNames);
                return elfNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Adrie", "Althaea", "Anastrianna", "Andraste", "Antinua",
                        "Bethrynna", "Birel", "Caelynn", "Drusilia", "Enna", "Felosial", "Ielenia", "Jelenneth",
                        "Keyleth", "Leshanna", "Lia", "Meriele", "Mialee", "Naivara", "Quelenna", "Quillathe", "Sariel",
                        "Shanairra", "Shava", "Silaqui", "Theirastra", "Thia", "Vadania", "Valanthe", "Xanaphia");
                elfNames.addAll(femaleNames);
                return elfNames;
            }
            case "Clan":
            {
                List<String> clanNames = Arrays.asList("Amakiir (Gemflower)", "Amastacia (Starflower)",
                        "Galanode (Moonwhisper)", "Holimion (Diamonddew)", "Ilphelkiir (Gemblossom)",
                        "Liadon (Silverfrond)", "Meliamne (Oakenheel)", "Nai'lo (Nightbreeze)", "Siannodel (Moonbrook)",
                        "Xiloscient (Goldpetal)");
                elfNames.addAll(clanNames);
                return elfNames;
            }
        }
        return elfNames;
    }

    public ArrayList<String> buildHalflingNameList(String inGender)
    {
        ArrayList<String> halflingNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Alton", "Ander", "Cade", "Corrin", "Eldon", "Errich", "Finnan",
                        "Garret", "Lindal", "Lyle", "Merric", "Milo", "Osborn", "Perrin", "Reed", "Roscoe", "Wellby");
                halflingNames.addAll(maleNames);
                return halflingNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Andry", "Bree", "Callie", "Cora", "Euphemia", "Jillian",
                        "Kithri", "Lavinia", "Lidda", "Merla", "Nedda", "Paela", "Portia", "Seraphina", "Shaena",
                        "Trym", "Vani", "Verna");
                halflingNames.addAll(femaleNames);
                return halflingNames;
            }
            case "Clan":
            {
                List<String> clanNames = Arrays.asList("Brushgather", "Goodbarrel", "Greenbottle", "High-hill",
                        "Hilltopple", "Leagallow", "Tealeaf", "Thorngage", "Tosscobble", "Underbough");
                halflingNames.addAll(clanNames);
                return halflingNames;
            }
        }
        return halflingNames;
    }

    public ArrayList<String> buildHumanNameList(String inEthnic, String inGender)
    {
        ArrayList<String> humanNames = new ArrayList<String>();
        switch (inEthnic)
        {
            case "Calishite":
            {
                switch (inGender)
                {
                    case "Male": 
                    {
                        List<String> maleNames = Arrays.asList("Aseir", "Bardeid", "Haseid", "Khemed", "Mehmen",
                                "Sudeiman", "Zasheir");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Atala", "Ceidil", "Hama", "Jasmal", "Meilil", "Seipora", "Yasheira",
                        "Zasheida");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Basha", "Dumein", "Jassan", "Khalid", "Mostana",
                                "Pashar", "Rein");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Chondathan":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Darvin", "Dorn", "Evendur", "Gorstag", "Grim", "Helm",
                                "Malark", "Morn", "Randal", "Stedd");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("rveene", "Esvele", "Jhessail", "Kerri", "Lureene",
                                "Miri", "Rowan", "Shandri", "Tessele");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Amblecrown", "Buckman", "Dundragon", "Evenwood",
                        "Greycastle", "Tallstag");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Damaran":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Bor", "Fodel", "Glar", "Grigor", "Igan", "Ivor",
                                "Kosef", "Mival", "Orel", "Pavel", "Sergor");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Alethra", "Kara", "Katernin", "Mara", "Natali",
                                "Olma", "Tana", "Zora");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Bersk", "Chernin", "Dotsk", "Kulenov", "Marsk",
                                "Nemetsk", "Shemov", "Starag");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Illuskan":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Ander", "Blath", "Bran", "Frath", "Geth", "Lander",
                                "Luth", "Malcer", "Stor", "Taman", "Urth");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Amafrey", "Betha", "Cefrey", "Kethra", "Mara", "Olga",
                                "Silifrey", "Westra");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Brightwood", "Helder", "Hornraven", "Lackman",
                                "Stormwind", "Windrivver");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Mulan":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Aoth", "Bareris", "Ehput-Ki", "Kethoth", "Mumed",
                                "Ramas", "So-Kehur", "Thazar-De", "Urhur");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Arizima", "Chathi", "Nephis", "Nulara", "Murithi",
                                "Sefris", "Thola", "Umara", "Zolis");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Ankhalab", "Anskuld", "Fezim", "Hahpet", "Nathandem",
                                "Sepret", "Uuthrakt");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Rashemi":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Borivik", "Faurgar", "Jandar", "Kanithar", "Madislak",
                                "Ralmevik", "Shaumar", "Vladislak");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Fyevarra", "Hulmarra", "Immith", "Imzel", "Navarra",
                                "Shevarra", "Tammith", "Yuldra");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Chergoba", "Dyernina", "Iltazyara", "Murnyethara",
                                "Stayanoga", "Ulmokina");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Shou":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("An", "Chen", "Chi", "Fai", "Jiang", "Jun", "Lian",
                                "Long", "Meng", "On", "Shan", "Shui", "Wen");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Bai", "Chao", "Jia", "Lei", "Mei", "Qiao", "Shui", "Tai");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Chien", "Huang", "Kao", "Kung", "Lao", "Ling", "Mei",
                                "Pin", "Shin", "Sum", "Tan", "Wan");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Tethyrian":
            {
                //Largly use Chondathan names
                switch (inGender)
                {
                    case "Male":
                    {
                        return buildHumanNameList("Chondathan", "Male");
                    }
                    case "Female":
                    {
                        return buildHumanNameList("Chondathan", "Female");
                    }
                    case "Clan":
                    {
                        return buildHumanNameList("Chondathan", "Clan");
                    }
                }
            }
            case "Turami":
            {
                switch (inGender)
                {
                    case "Male":
                    {
                        List<String> maleNames = Arrays.asList("Anton", "Diero", "Marcon", "Pieron", "Rimardo",
                                "Romero", "Salazar", "Umbero");
                        humanNames.addAll(maleNames);
                        return humanNames;
                    }
                    case "Female":
                    {
                        List<String> femaleNames = Arrays.asList("Balama", "Dona", "Faila", "Jalana", "Luisa", "Marta",
                                "Quara", "Selise", "Vonda");
                        humanNames.addAll(femaleNames);
                        return humanNames;
                    }
                    case "Clan":
                    {
                        List<String> clanNames = Arrays.asList("Agosto", "Astorio", "Calabra", "Domine", "Falone",
                                "Marivaldi", "Pisacar", "Ramondo");
                        humanNames.addAll(clanNames);
                        return humanNames;
                    }
                }
            }
            case "Random":
            {
                Random numGen = new Random();
                int humanEthnicNum = numGen.ints(0, 9).findFirst().getAsInt();   //chooses which ethnicity
                List<String> humanEthnicsList = Arrays.asList("Calishite", "Chondathan", "Damaran", "Illuskan", "Mulan", "Rashemi", "Shou", "Tethyrian", "Turami");
                String ethnic =  humanEthnicsList.get(humanEthnicNum);
                switch (inGender)
                {
                    case "Male":
                    {
                        return buildHumanNameList(ethnic, "Male");
                    }
                    case "Female":
                    {
                        return buildHumanNameList(ethnic, "Female");
                    }
                    case "Clan":
                    {
                        return buildHumanNameList(ethnic, "Clan");
                    }
                }
            }
        }
        return humanNames;
    }

    public ArrayList<String> buildDragonbornNameList(String inGender)
    {
        ArrayList<String> dragonbornNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Arjhan", "Balasar", "Bharash", "Donaar", "Ghesh", "Heskan",
                        "Kriv", "Medrash", "Mehen", "Nadarr", "Pandjed", "Patrin", "Rhogar", "Shamash", "Shedinn",
                        "Tarhun", "Torinn");
                dragonbornNames.addAll(maleNames);
                return dragonbornNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Akra", "Biri", "Daar", "Farideh", "Harann", "Flavilar",
                        "Jheri", "Kava", "Korinn", "Mishann", "Nala", "Perra", "Raiann", "Sora", "Surina", "Thava",
                        "Uadjit");
                dragonbornNames.addAll(femaleNames);
                return dragonbornNames;
            }
            case "Clan":
            {
                List<String> clanNames = Arrays.asList("Clethtinthiallor", "Daardendrian", "Delmirev", "Drachedandion",
                        "Fenkenkabradon", "Kepeshkmolik", "Kerrhylon", "Kimbatuul", "Linxakasendalor", "Myastan",
                        "Nemmonis", "Norixius", "Ophinshtalajiir", "Prexijandilin", "Shestendeliath", "Turnuroth",
                        "Verthisathurgiesh", "Yarjerit");
                dragonbornNames.addAll(clanNames);
                return dragonbornNames;
            }
        }
        return dragonbornNames;
    }

    public ArrayList<String> buildGnomeNameList(String inGender)
    {
        ArrayList<String> gnomeNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Alston", "Alvyn", "Boddynock", "Brocc", "Burgell", "Dimble",
                        "Eldon", "Erky", "Fonkin", "Frug", "Gerbo", "Gimble", "Glim", "Jebeddo", "Kellen", "Namfoodle",
                        "Orryn", "Roondar", "Seebo", "Sindri", "Warryn", "Wrenn", "Zook");
                gnomeNames.addAll(maleNames);
                return gnomeNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Bimpnottin", "Breena", "Caramip", "Carlin", "Donella",
                        "Duvamil", "Ella", "Ellyjobell", "Ellywick", "Lilli", "Loopmottin", "Lorilla", "Mardnab",
                        "Nissa", "Nyx", "Oda", "Orla", "Roywyn", "Shamil", "Tana", "Waywocket", "Zanna");
                gnomeNames.addAll(femaleNames);
                return gnomeNames;
            }
            case "Clan":
            {
                List<String> clanNames = Arrays.asList("Beren", "Daergel", "Folkor", "Garrick", "Nackle", "Murnig",
                        "Ningel", "Raulnor", "Scheppen", "Timbers", "Turen");
                gnomeNames.addAll(clanNames);
                return gnomeNames;
            }
        }
        return gnomeNames;
    }

    public ArrayList<String> buildHalfelfNameList(String inGender)
    {
        Random numGen = new Random();
        ArrayList<String> halfelfNames = new ArrayList<String>();
        int humanOrElfName = numGen.ints(0, 2).findFirst().getAsInt();
        if (humanOrElfName == 0)    //human
        {
            switch (inGender)
            {
                case "Male":
                {
                    return buildHumanNameList("Random", "Male");
                }
                case "Female":
                {
                    return buildHumanNameList("Random", "Female");
                }
                case "Clan":
                {
                    return buildHumanNameList("Random", "Clan");
                }
            }
        }
        else    //elf 
        {
            switch (inGender)
            {
                case "Male":
                {
                    return buildElfNameList("Male");
                }
                case "Female":
                {
                    return buildElfNameList("Female");
                }
                case "Clan":
                {
                    return buildElfNameList("Clan");
                }
            }
        }
        return halfelfNames;
    }

    public ArrayList<String> buildHalforcNameList(String inGender)
    {   
        //Half-Orcs raised in human culture use human names, so use buildHumanNamesList(culture , gender) instead
        //  the following are orc names
        ArrayList<String> halforcNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Dench", "Feng", "Gell", "Henk", "Holg", "Imsh", "Keth", "Krusk",
                        "Mhurren", "Ront", "Shump", "Thokk");
                halforcNames.addAll(maleNames);
                return halforcNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Baggi", "Emen", "Engong", "Kansif", "Myev", "Neega", "Ovak",
                        "Ownka", "Shautha", "Sutha", "Vola", "Volen", "Yevelda");
                halforcNames.addAll(femaleNames);
                return halforcNames;
            }
        }
        return halforcNames;
    }

    public ArrayList<String> buildTieflingNameList(String inGender)
    {
        //Tiefling born into another culture typically use their culture's names, younger tieflings may take a name that signifies a virtue
        ArrayList<String> tieflingNames = new ArrayList<String>();
        switch (inGender)
        {
            case "Male":
            {
                List<String> maleNames = Arrays.asList("Akmenos", "Amnon", "Barakas", "Damakos", "Ekemon", "Iados",
                        "Kairon", "Leucis", "Melech", "Mordai", "Morthos", "Pelaios", "Skamos", "Therai");
                tieflingNames.addAll(maleNames);
                return tieflingNames;
            }
            case "Female":
            {
                List<String> femaleNames = Arrays.asList("Akta", "Anakis", "Bryseis", "Criella", "Damaia", "Ea",
                        "Kallista", "Lerissa", "Makaria", "Nemeia", "Orianna", "Phelaia", "Rieta");
                tieflingNames.addAll(femaleNames);
                return tieflingNames;
            }
            case "Virtue":
            {
                List<String> clanNames = Arrays.asList("Art", "Carrion", "Chant", "Creed", "Despair", "Excellence",
                        "Fear", "Glory", "Hope", "Ideal", "Music", "Nowhere", "Open", "Poetry", "Quest", "Random",
                        "Reverence", "Sorrow", "Temerity", "Torment", "Weary");
                tieflingNames.addAll(clanNames);
                return tieflingNames;
            }
        }
        return tieflingNames;
    }
}










class spells
{
    ArrayList<List<String>> classSpellLists;

    public spells()
    {
        classSpellLists = new ArrayList<List<String>>();
    }

    public ArrayList<List<String>> buildBardSpellLists()
    {
        List<String> bardCantrips = Arrays.asList("Blade Ward", "Dancing Lights", "Friends", "Light", "Mage Hand", "Mending", "Message", "Minor Illusion", 
                                 "Prestidigitation", "True Strike", "Vicious Mockery");

        List<String> bard1stLevel = Arrays.asList( "Animal Friendship", "Bane", "Charm Person", "Comprehend Languages", "Cure Wounds",
                                    "Detect Magic", "Disguise Self", "Dissonant Whispers", "Faerie Fire", "Feather Fall", "Healing Word",
                                    "Heroism", "Identify", "Illusory Script", "Longstrider", "Silent Image", "Sleep", "Speak with Animals",
                                    "Tasha’s Hideous Laughter", "Thunderwave", "Unseen Servant" );

        List<String> bard2ndLevel = Arrays.asList( "Animal Messenger", "Blindness/Deafness", "Calm Emotions", "Cloud of Daggers",
                                    "Crown of Madness", "Detect Thoughts", "Enhance Ability", "Enthrall", "Heat Metal", "Hold Person",
                                    "Invisibility", "Knock", "Lesser Restoration", "Locate Animals or Plants", "Locate Object",
                                    "Magic Mouth", "Phantasmal Force", "See Invisibility", "Shatter", "Silence", "Suggestion",
                                    "Zone of Truth" );
                                
        List<String> bard3rdLevel = Arrays.asList( "Bestow Curse", "Clairvoyance", "Dispel Magic", "Fear", "Feign Death",
                                    "Glyph of Warding", "Hypnotic Pattern", "Leomund’s Tiny Hut", "Major Image", "Nondetection",
                                    "Plant Growth", "Sending", "Speak with Dead", "Speak with Plants", "Stinking Cloud", "Tongues" );

        List<String> bard4thLevel = Arrays.asList( "Compulsion", "Confusion", "Dimension Door", "Freedom of Movement",
                                    "Greater Invisibility", "Hallucinatory Terrain", "Locate Creature", "Polymorph" );

        List<String> bard5thLevel = Arrays.asList( "Animate Objects", "Awaken", "Dominate Person", "Dream", "Geas",
                                    "Greater Restoration", "Hold Monster", "Legend Lore", "Mass Cure Wounds", "Mislead", "Modify Memory",
                                    "Planar Binding", "Raise Dead", "Scrying", "Seeming", "Teleportation Circle" );

        List<String> bard6thLevel = Arrays.asList( "Eyebite", "Find the Path", "Guards and Wards", "Mass Suggestion",
                                    "Otto’s Irresistible Dance", "Programmed Illusion", "True Seeing" );

        List<String> bard7thLevel = Arrays.asList( "Etherealness", "Forcecage", "Mirage Arcane", "Mordenkainen’s", "Magnificent Mansion",
                                    "Mordenkainen’s Sword", "Project Image", "Regenerate", "Resurrection", "Symbol", "Teleport" );

        List<String> bard8thLevel = Arrays.asList( "Dominate Monster", "Feeblemind", "Glibness", "Mind Blank", "Power Word Stun" );

        List<String> bard9thLevel = Arrays.asList( "Foresight", "Power Word Heal", "Power Word Kill", "True Polymorph" );

        ArrayList<List<String>> bardSpells = new ArrayList<List<String>>();
        bardSpells.add(bardCantrips); bardSpells.add(bard1stLevel); bardSpells.add(bard2ndLevel); bardSpells.add(bard3rdLevel); bardSpells.add(bard4thLevel);
        bardSpells.add(bard5thLevel); bardSpells.add(bard6thLevel); bardSpells.add(bard7thLevel); bardSpells.add(bard8thLevel); bardSpells.add(bard9thLevel); 
        return bardSpells;                                
    }

    public ArrayList<List<String>> buildClericSpellLists()
    {
        List<String> ClericCantrips = Arrays.asList("Guidance", "Light", "Mending", "Resistance", "Sacred Flame",
                                                    "Spare the Dying", "Thaumaturgy");

        List<String> Cleric1stLevel = Arrays.asList("Bane", "Bless", "Command", "Create or Destroy Water", "Cure Wounds",
                                                    "Detect Evil and Good", "Detect Magic", "Detect Poison and Disease", "Guiding Bolt", "Healing Word",
                                                    "Inflict Wounds", "Protection from Evil and Good", "Purify Food and Drink", "Sanctuary",
                                                    "Shield of Faith");

        List<String> Cleric2ndLevel = Arrays.asList("Aid", "Augury", "Blindness/Deafness", "Calm Emotions",
                                                    "Continual Flame", "Enhance Ability", "Find Traps", "Gentle Repose", "Hold Person",
                                                    "Lesser Restoration", "Locate Object", "Prayer of Healing", "Protection from Poison", "Silence",
                                                    "Spiritual Weapon", "Warding Bond", "Zone of Truth");
                                
        List<String> Cleric3rdLevel = Arrays.asList("Animate Dead", "Beacon of Hope", "Bestow Curse", "Clairvoyance",
                                                    "Create Food and Water", "Daylight", "Dispel Magic", "Feign Death", "Glyph of Warding", "Magic Circle",
                                                    "Mass Healing Word", "Meld into Stone", "Protection from Energy", "Remove Curse", "Revivify", "Sending",
                                                    "Speak with Dead", "Spirit Guardians", "Tongues", "Water Walk");

        List<String> Cleric4thLevel = Arrays.asList("Banishment", "Control Water", "Death Ward", "Divination",
                                                    "Freedom of Movement", "Guardian of Faith", "Locate Creature", "Stone Shape");

        List<String> Cleric5thLevel = Arrays.asList("Commune", "Contagion", "Dispel Evil and Good", "Flame Strike",
                                                    "Geas", "Greater Restoration", "Hallow", "Insect Plague", "Legend Lore", "Mass Cure Wounds",
                                                    "Planar Binding", "Raise Dead", "Scrying");

        List<String> Cleric6thLevel = Arrays.asList("Blade Barrier", "Create Undead", "Find the Path", "Forbiddance",
                                                    "Harm", "Heal", "Heroes’ Feast", "Planar Ally", "True Seeing", "Word of Recall");

        List<String> Cleric7thLevel = Arrays.asList("Conjure Celestial", "Divine Word", "Etherealness", "Fire Storm",
                                                    "Plane Shift", "Regenerate", "Resurrection", "Symbol");

        List<String> Cleric8thLevel = Arrays.asList("Antimagic Field", "Control Weather", "Earthquake", "Holy Aura");

        List<String> Cleric9thLevel = Arrays.asList("Astral Projection", "Gate", "Mass Heal", "True Resurrection");

        ArrayList<List<String>> ClericSpells = new ArrayList<List<String>>();
        ClericSpells.add(ClericCantrips); ClericSpells.add(Cleric1stLevel); ClericSpells.add(Cleric2ndLevel); ClericSpells.add(Cleric3rdLevel); ClericSpells.add(Cleric4thLevel); ClericSpells.add(Cleric5thLevel);
        ClericSpells.add(Cleric6thLevel); ClericSpells.add(Cleric7thLevel); ClericSpells.add(Cleric8thLevel); ClericSpells.add(Cleric9thLevel);
        return ClericSpells;                                
    }   
    
    public ArrayList<List<String>> buildDruidSpellLists()
    {
        List<String> DruidCantrips = Arrays.asList("Druidcraft", "Guidance", "Mending", "Poison Spray", "Produce Flame",
                                                        "Resistance", "Shillelagh", "Thorn Whip");

        List<String> Druid1stLevel = Arrays.asList("Animal Friendship", "Charm Person", "Create or Destroy Water",
                                                    "Cure Wounds", "Detect Magic", "Detect Poison and Disease", "Entangle", "Faerie Fire", "Fog Cloud",
                                                    "Goodberry", "Healing Word", "Jump", "Longstrider", "Purify Food and Drink", "Speak with Animals",
                                                    "Thunderwave");

        List<String> Druid2ndLevel = Arrays.asList("Animal Messenger", "Barkskin", "Beast Sense", "Darkvision",
                                                    "Enhance Ability", "Find Traps", "Flame Blade", "Flaming Sphere", "Gust of Wind", "Heat Metal",
                                                    "Hold Person", "Lesser Restoration", "Locate Animals or Plants", "Locate Object", "Moonbeam",
                                                    "Pass without Trace", "Protection from Poison", "Spike Growth");
                                
        List<String> Druid3rdLevel = Arrays.asList("Call Lightning", "Conjure Animals", "Daylight", "Dispel Magic",
                                                    "Feign Death", "Meld into Stone", "Plant Growth", "Protection from Energy", "Sleet Storm",
                                                    "Speak with Plants", "Water Breathing", "Water Walk", "Wind Wall");

        List<String> Druid4thLevel = Arrays.asList("Blight", "Confusion", "Conjure Minor Elementals",
                                                    "Conjure Woodland Beings", "Control Water", "Dominate Beast", "Freedom of Movement", "Giant Insect",
                                                    "Grasping Vine", "Hallucinatory Terrain", "Ice Storm", "Locate Creature", "Polymorph", "Stone Shape",
                                                    "Stoneskin", "Wall of Fire");

        List<String> Druid5thLevel = Arrays.asList("Antilife Shell", "Awaken", "Commune with Nature",
                                                    "Conjure Elemental", "Contagion", "Geas", "Greater Restoration", "Insect Plague", "Mass Cure Wounds",
                                                    "Planar Binding", "Reincarnate", "Scrying", "Tree Stride", "Wall of Stone");

        List<String> Druid6thLevel = Arrays.asList("Conjure Fey", "Find the Path", "Heal", "Heroes’ Feast",
                                                    "Move Earth", "Sunbeam", "Transport via Plants", "Wall of Thorns", "Wind Walk");

        List<String> Druid7thLevel = Arrays.asList("Fire Storm", "Mirage Arcane", "Plane Shift", "Regenerate", "Reverse Gravity");

        List<String> Druid8thLevel = Arrays.asList("Animal Shapes", "Antipathy/Sympathy", "Control Weather",
                                                    "Earthquake", "Feeblemind", "Sunburst", "Tsunami");

        List<String> Druid9thLevel = Arrays.asList("Foresight", "Shapechange", "Storm of Vengeance", "True Resurrection");

        ArrayList<List<String>> DruidSpells = new ArrayList<List<String>>();
        DruidSpells.add(DruidCantrips); DruidSpells.add(Druid1stLevel); DruidSpells.add(Druid2ndLevel); DruidSpells.add(Druid3rdLevel); DruidSpells.add(Druid4thLevel); 
        DruidSpells.add(Druid5thLevel); DruidSpells.add(Druid6thLevel); DruidSpells.add(Druid7thLevel); DruidSpells.add(Druid8thLevel); DruidSpells.add(Druid9thLevel);
        return DruidSpells;                                
    }

    public ArrayList<List<String>> buildPaladinSpellLists()
    {
        List<String> Paladin1stLevel = Arrays.asList("Bless", "Command", "Compelled Duel", "Cure Wounds",
                                                    "Detect Evil and Good", "Detect Magic", "Detect Poison and Disease", "Divine Favor", "Heroism",
                                                    "Protection from Evil and Good", "Purify Food and Drink", "Searing Smite", "Shield of Faith",
                                                    "Thunderous Smite", "Wrathful Smite");

        List<String> Paladin2ndLevel = Arrays.asList("Aid", "Branding Smite", "Find Steed", "Lesser Restoration",
                                                    "Locate Object", "Magic Weapon", "Protection from Poison", "Zone of Truth");
                                
        List<String> Paladin3rdLevel = Arrays.asList("Aura of Vitality", "Blinding Smite", "Create Food and Water",
                                                    "Crusader's Mantle", "Daylight", "Dispel Magic", "Elemental Weapon", "Magic Circle", "Remove Curse",
                                                    "Revivify");

        List<String> Paladin4thLevel = Arrays.asList("Aura of Life", "Aura of Purity", "Banishment", "Death Ward",
                                                    "Locate Creature", "Staggering Smite");

        List<String> Paladin5thLevel = Arrays.asList("Banishing Smite", "Circle of Power", "Destructive Smite",
                                                    "Dispel Evil and Good", "Geas", "Raise Dead");

        ArrayList<List<String>> PaladinSpells = new ArrayList<List<String>>();
        PaladinSpells.add(Paladin1stLevel); PaladinSpells.add(Paladin2ndLevel); PaladinSpells.add(Paladin3rdLevel); 
        PaladinSpells.add(Paladin4thLevel); PaladinSpells.add(Paladin5thLevel);
        return PaladinSpells;                                
    }

    public ArrayList<List<String>> buildRangerSpellLists()
    {
        List<String> Ranger1stLevel = Arrays.asList("Alarm", "Animal Friendship", "Cure Wounds", "Detect Magic",
                                                    "Detect Poison and Disease", "Ensnaring Strike", "Fog Cloud", "Goodberry", "Hail o f Thorns",
                                                    "Hunter’s Mark", "Jump", "Longstrider", "Speak with Animals");

        List<String> Ranger2ndLevel = Arrays.asList("Animal Messenger", "Barkskin", "Beast Sense", "Cordon of Arrows",
                                                    "Darkvision", "Find Traps", "Lesser Restoration", "Locate Animals or Plants", "Locate Object",
                                                    "Pass without Trace", "Protection from Poison", "Silence", "Spike Growth");
                                
        List<String> Ranger3rdLevel = Arrays.asList("Conjure Animals", "Conjure Barrage", "Daylight", "Lightning Arrow",
                                                    "Nondetection", "Plant Growth", "Protection from Energy", "Speak with Plants", "Water Breathing",
                                                    "Water Walk", "Wind Wall");

        List<String> Ranger4thLevel = Arrays.asList("Conjure Woodland Beings", "Freedom of Movement", "Grasping Vine",
                                                    "Locate Creature", "Stoneskin");

        List<String> Ranger5thLevel = Arrays.asList("Commune with Nature", "Conjure Volley", "Swift Quiver", "Tree Stride");

        ArrayList<List<String>> RangerSpells = new ArrayList<List<String>>();
        RangerSpells.add(Ranger1stLevel); RangerSpells.add(Ranger2ndLevel); RangerSpells.add(Ranger3rdLevel); 
        RangerSpells.add(Ranger4thLevel); RangerSpells.add(Ranger5thLevel);
        return RangerSpells;                                
    }

    public ArrayList<List<String>> buildSorcererSpellLists()
    {
        List<String> SorcererCantrips = Arrays.asList("Acid Splash", "Blade Ward", "Chill Touch", "Dancing Lights",
                                                        "Fire Bolt", "Friends", "Light", "Mage Hand", "Mending", "Message", "Minor Illusion", "Poison Spray",
                                                        "Prestidigitation", "Ray of Frost", "Shocking Grasp", "True Strike");

        List<String> Sorcerer1stLevel = Arrays.asList("Burning Hands", "Charm Person", "Chromatic Orb", "Color Spray",
                                                        "Comprehend Languages", "Detect Magic", "Disguise Self", "Expeditious Retreat", "False Life",
                                                        "Feather Fall", "Fog Cloud", "Jump", "Mage Armor", "Magic Missile", "Ray of Sickness", "Shield",
                                                        "Silent Image", "Sleep", "Thunderwave", "Witch Bolt");

        List<String> Sorcerer2ndLevel = Arrays.asList("Alter Self", "Blindness/Deafness", "Blur", "Cloud of Daggers",
                                                        "Crown of Madness", "Darkness", "Darkvision", "Detect Thoughts", "Enhance Ability", "Enlarge/Reduce",
                                                        "Gust of Wind", "Hold Person", "Invisibility", "Knock", "Levitate", "Mirror Image", "Misty Step",
                                                        "Phantasmal Force", "Scorching Ray", "See Invisibility", "Shatter", "Spider Climb", "Suggestion",
                                                        "Web");
                                
        List<String> Sorcerer3rdLevel = Arrays.asList("Blink", "Clairvoyance", "Counterspell", "Daylight",
                                                        "Dispel Magic", "Fear", "Fireball", "Fly", "Gaseous Form", "Haste", "Hypnotic Pattern",
                                                        "Lightning Bolt", "Major Image", "Protection from Energy", "Sleet Storm", "Slow", "Stinking Cloud",
                                                        "Tongues", "Water Breathing", "Water Walk");

        List<String> Sorcerer4thLevel = Arrays.asList("Banishment", "Blight", "Confusion", "Dimension Door", "Dominate Beast", "Greater Invisibility", 
                                                        "Ice Storm", "Polymorph", "Stoneskin", "Wall of Fire");

        List<String> Sorcerer5thLevel = Arrays.asList("Animate Objects", "Cloudkill", "Cone of Cold", "Creation", "Dominate Person", "Hold Monster", 
                                                        "Insect Plague", "Seeming", "Telekinesis", "Teleportation Circle", "Wall of Stone");

        List<String> Sorcerer6thLevel = Arrays.asList("Arcane Gate", "Chain Lightning", "Circle of Death", "Disintegrate", "Eyebite", 
                                                        "Globe of Invulnerability", "Mass Suggestion", "Move Earth", "Sunbeam", "True Seeing");

        List<String> Sorcerer7thLevel = Arrays.asList("Delayed Blast Fireball", "Etherealness", "Finger of Death", "Fire Storm", "Plane Shift", 
                                                        "Prismatic Spray", "Reverse Gravity", "Teleport");

        List<String> Sorcerer8thLevel = Arrays.asList("Dominate Monster", "Earthquake", "Incendiary Cloud", "Power Word Stun", "Sunburst");

        List<String> Sorcerer9thLevel = Arrays.asList("Gate", "Meteor Swarm", "Power Word Kill", "Time Stop", "Wish");

        ArrayList<List<String>> SorcererSpells = new ArrayList<List<String>>();
        SorcererSpells.add(SorcererCantrips); SorcererSpells.add(Sorcerer1stLevel); SorcererSpells.add(Sorcerer2ndLevel); SorcererSpells.add(Sorcerer3rdLevel); SorcererSpells.add(Sorcerer4thLevel); 
        SorcererSpells.add(Sorcerer5thLevel); SorcererSpells.add(Sorcerer6thLevel); SorcererSpells.add(Sorcerer7thLevel); SorcererSpells.add(Sorcerer8thLevel); SorcererSpells.add(Sorcerer9thLevel);
        return SorcererSpells;                                
    }

    public ArrayList<List<String>> buildWarlockSpellLists()
    {
        List<String> WarlockCantrips = Arrays.asList("Blade Ward", "Chill Touch", "Eldritch Blast", "Friends", "Mage Hand", "Minor Illusion", 
                                                        "Poison Spray", "Prestidigitation", "True Strike");

        List<String> Warlock1stLevel = Arrays.asList("Armor of Agathys", "Arms of Hadar", "Charm Person", "Comprehend Languages", "Expeditious Retreat", 
                                                        "Hellish Rebuke", "Hex", "Illusory Script", "Protection from Evil and Good", "Unseen Servant", "Witch Bolt");

        List<String> Warlock2ndLevel = Arrays.asList("Cloud of Daggers", "Crown of Madness", "Darkness", "Enthrall", "Hold Person", "Invisibility", 
                                                        "Mirror Image", "Misty Step", "Ray of Enfeeblement", "Shatter", "Spider Climb", "Suggestion");
                                
        List<String> Warlock3rdLevel = Arrays.asList("Counterspell", "Dispel Magic", "Fear", "Fly", "Gaseous Form", "Hunger of Hadar", "Hypnotic Pattern", 
                                                        "Magic Circle", "Major Image", "Remove Curse", "Tongues", "Vampiric Touch");

        List<String> Warlock4thLevel = Arrays.asList("Banishment", "Blight", "Dimension Door", "Hallucinatory Terrain");

        List<String> Warlock5thLevel = Arrays.asList("Contact Other Plane", "Dream", "Hold Monster", "Scrying");

        List<String> Warlock6thLevel = Arrays.asList("Arcane Gate", "Circle of Death", "Conjure Fey", "Create Undead", "Eyebite", "Flesh to Stone", 
                                                        "Mass Suggestion", "True Seeing");

        List<String> Warlock7thLevel = Arrays.asList("Etherealness", "Finger of Death", "Forcecage", "Plane Shift");

        List<String> Warlock8thLevel = Arrays.asList("Demiplane", "Dominate Monster", "Feeblemind", "Glibness", "Power Word Stun");

        List<String> Warlock9thLevel = Arrays.asList("Astral Projection", "Foresight", "Imprisonment", "Power Word Kill", "True Polymorph");

        ArrayList<List<String>> WarlockSpells = new ArrayList<List<String>>();
        WarlockSpells.add(WarlockCantrips); WarlockSpells.add(Warlock1stLevel); WarlockSpells.add(Warlock2ndLevel); WarlockSpells.add(Warlock3rdLevel); WarlockSpells.add(Warlock4thLevel); 
        WarlockSpells.add(Warlock5thLevel); WarlockSpells.add(Warlock6thLevel); WarlockSpells.add(Warlock7thLevel); WarlockSpells.add(Warlock8thLevel); WarlockSpells.add(Warlock9thLevel);
        return WarlockSpells;                                
    }

    public ArrayList<List<String>> buildWizardSpellLists()
    {
        List<String> WizardCantrips = Arrays.asList("Acid Splash", "Blade Ward", "Chill Touch", "Dancing Lights", "Fire Bolt", "Friends", 
                "Light", "Mage Hand", "Mending", "Message", "Minor Illusion", "Poison Spray", 
                "Prestidigitation", "Ray of Frost", "Shocking Grasp", "True Strike");

        List<String> Wizard1stLevel = Arrays.asList("Alarm", "Burning Hands", "Charm Person", "Chromatic Orb",
                "Color Spray", "Comprehend Languages", "Detect Magic", "Disguise Self", "Expeditious Retreat",
                "False Life", "Feather Fall", "Find Familiar", "Fog Cloud", "Grease", "Identify", "Illusory Script",
                "Jump", "Longstrider", "Mage Armor", "Magic Missile", "Protection from Evil and Good",
                "Ray of Sickness", "Shield", "Silent Image", "Sleep", "Tasha’s Hideous Laughter",
                "Tenser’s Floating Disk", "Thunderwave", "Unseen Servant", "Witch Bolt");

        List<String> Wizard2ndLevel = Arrays.asList("Alter Self", "Arcane Lock", "Blindness/Deafness", "Blur",
                "Cloud of Daggers", "Continual Flame", "Crown of Madness", "Darkness", "Darkvision", "Detect Thoughts",
                "Enlarge/Reduce", "Flaming Sphere", "Gentle Repose", "Gust of Wind", "Hold Person", "Invisibility",
                "Knock", "Levitate", "Locate Object", "Magic Mouth", "Magic Weapon", "Melf’s Acid Arrow",
                "Mirror Image", "Misty Step", "Nystul’s Magic Aura", "Phantasmal Force", "Ray of Enfeeblement",
                "Rope Trick", "Scorching Ray", "See Invisibility", "Shatter", "Spider Climb", "Suggestion", "Web");
       
        List<String> Wizard3rdLevel = Arrays.asList("Animate Dead", "Bestow Curse", "Blink", "Clairvoyance",
                "Counterspell", "Dispel Magic", "Fear", "Feign Death", "Fireball", "Fly", "Gaseous Form",
                "Glyph of Warding", "Haste", "Hypnotic Pattern", "Leomund’s Tiny Hut", "Lightning Bolt", "Magic Circle",
                "Major Image", "Nondetection", "Phantom Steed", "Protection from Energy", "Remove Curse", "Sending",
                "Sleet Storm", "Slow", "Stinking Cloud", "Tongues", "Vampiric Touch", "Water Breathing");

        List<String> Wizard4thLevel = Arrays.asList("Arcane Eye", "Banishment", "Blight", "Confusion",
                "Conjure Minor Elementals", "Control Water", "Dimension Door", "Evard's Black Tentacles", "Fabricate",
                "Fire Shield", "Greater Invisibility", "Hallucinatory Terrain", "Ice Storm", "Leomund’s Secret Chest",
                "Locate Creature", "Mordenkainen’s", "Faithful Hound", "Mordenkainen’s", "Private Sanctum",
                "Otiluke’s Resilient Sphere", "Phantasmal Killer", "Polymorph", "Stone Shape", "Stoneskin",
                "Wall of Fire");

        List<String> Wizard5thLevel = Arrays.asList("Animate Objects", "Bigby’s Hand", "CloudkilI", "Cone of Cold",
                "Conjure Elemental", "Contact Other Plane", "Creation", "Dominate Person", "Dream", "Geas",
                "Hold Monster", "Legend Lore", "Mislead", "Modify Memory", "Passwall", "Planar Binding",
                "Rary’s Telepathic Bond", "Scrying", "Seeming", "Telekinesis", "Teleportation Circle", "Wall of Force",
                "Wall of Stone");

        List<String> Wizard6thLevel = Arrays.asList("Arcane Gate", "Chain Lightning", "Circle of Death", "Contingency",
                "Create Undead", "Disintegrate", "Drawmij’s Instant", "Summons", "Eyebite", "Flesh to Stone",
                "Globe of Invulnerability", "Guards and Wards", "Magic Jar", "Mass Suggestion", "Move Earth",
                "Otiluke’s Freezing Sphere", "Otto’s Irresistible Dance", "Programmed Illusion", "Sunbeam",
                "True Seeing", "Wall of Ice");

        List<String> Wizard7thLevel = Arrays.asList("Delayed Blast Fireball", "Etherealness", "Finger of Death",
                "Forcecage", "Mirage Arcane", "Mordenkainen's", "Magnificent Mansion", "Mordenkainen’s Sword",
                "Plane Shift", "Prismatic Spray", "Project Image", "Reverse Gravity", "Sequester", "Simulacrum",
                "Symbol", "Teleport");

        List<String> Wizard8thLevel = Arrays.asList("Antimagic Field", "Antipathy/Sympathy", "Clone", "Control Weather",
                "Demiplane", "Dominate Monster", "Feeblemind", "Incendiary Cloud", "Maze", "Mind Blank",
                "Power Word Stun", "Sunburst", "Telepathy", "Trap the Soul");

        List<String> Wizard9thLevel = Arrays.asList("Astral Projection", "Foresight", "Gate", "Imprisonment",
                "Meteor Swarm", "Power Word Kill", "Prismatic Wall", "Shapechange", "Time Stop", "True Polymorph",
                "Weird", "Wish");

        ArrayList<List<String>> WizardSpells = new ArrayList<List<String>>();
        WizardSpells.add(WizardCantrips); WizardSpells.add(Wizard1stLevel); WizardSpells.add(Wizard2ndLevel); WizardSpells.add(Wizard3rdLevel); WizardSpells.add(Wizard4thLevel); 
        WizardSpells.add(Wizard5thLevel); WizardSpells.add(Wizard6thLevel); WizardSpells.add(Wizard7thLevel); WizardSpells.add(Wizard8thLevel); WizardSpells.add(Wizard9thLevel);
        return WizardSpells;                                
    }
}









class trinkets      //Own class cause list is huge, split into two so it doesnt have to build the whole thing every time
{
    List<String> trinketList;
    ArrayList<String> trinketArray;
    Random numGen = new Random();

    public trinkets()
    {
        this.trinketList = Arrays.asList();
        this.trinketArray = new ArrayList<String>();
    }
 
    public String getTrinket()
    {
        String trinket;
        ArrayList<String> newTrinketList = new ArrayList<String>();

        int trinketHalf = numGen.ints(0, 2).findFirst().getAsInt();
        if (trinketHalf == 0)
        {
            newTrinketList = build1stHalfTrinketList();
            trinket = newTrinketList.get(numGen.ints(0, newTrinketList.size()).findFirst().getAsInt());
            return trinket;
        }
        else
        {
            newTrinketList = build2ndHalfTrinketList();
            trinket = newTrinketList.get(numGen.ints(0, newTrinketList.size()).findFirst().getAsInt());
            return trinket;
        }
    }

    public ArrayList<String> build1stHalfTrinketList()
    {
        trinketList = Arrays.asList("A mummified goblin hand",
                "A shard of obsidian that always feels warm to the touch",
                "A piece of crystal that faintly glows in the moonlight",
                "A dragon's bony talon hanging from a plain leather necklace", "A gold coin minted in an unknown land",
                "A diary written in a language you don’t know", "A pair of old socks",
                "A brass ring that never tarnishes",
                "A blank book whose pages refuse to hold ink, chalk, graphite, or any other substance or marking",
                "An old chess piece made from glass",
                "A pair of knucklebone dice, each with a skull symbol on the side that would normally show six pips",
                "A silver badge in the shape of a five-pointed star", "A knife that belonged to a relative",
                "A small idol depicting a nightmarish creature that gives you unsettling dreams when you sleep near it",
                "A glass vial filled with nail clippings",
                "A rectangular metal device with two tiny metal cups on one end that throws sparks when wet",
                "A rope necklace from which dangles four mummified elf fingers",
                "A white, sequined glove sized for a human", "The deed for a parcel of land in a realm unknown to you",
                "A vest with one hundred tiny pockets", "A small, weightless stone block",
                "A 1-ounce block made from an unknown material", "A tiny sketch portrait of a goblin",
                "A small cloth doll skewered with needles", "An empty glass vial that smells of perfume when opened",
                "A tooth from an unknown beast", "An enormous scale, perhaps from a dragon",
                "A gemstone that looks like a lump of coal when examined by anyone but you", "A bright green feather",
                "An old divination card bearing your likeness", "A scrap of cloth from an old banner",
                "A glass orb filled with moving smoke", "A rank insignia from a lost legionnaire",
                "A 1-pound egg with a bright red shell", "A tiny silver bell without a clapper",
                "A pipe that blows bubbles", "A mechanical canary inside a gnomish lamp",
                "A glass jar containing a weird bit of flesh floating in pickling fluid",
                "A tiny chest carved to look like it has numerous feet on the bottom",
                "A tiny gnome-crafted music box that plays a song you dimly remember from your childhood",
                "A dead sprite inside a clear glass bottle",
                "A metal can that has no opening but sounds as if it is filled with liquid, sand, spiders, or broken glass (your choice)",
                "A small wooden statuette of a smug halfling", "A brass orb etched with strange runes",
                "A multicolored stone disk", "A glass orb filled with water, in which swims a clockwork goldfish",
                "A tiny silver icon of a raven", "A bag containing forty-seven humanoid teeth, one of which is rotten",
                "A silver spoon with an M engraved on the handle", "A whistle made from gold-colored wood");
        trinketArray.addAll(trinketList);
        return trinketArray;
    }

    public ArrayList<String> build2ndHalfTrinketList()
    {
        trinketList = Arrays.asList("A dead scarab beetle the size of your hand",
                "An invitation to a party where a murder happened", "Two toy soldiers, one with a missing head",
                "A bronze pentacle with an etching of a rat's head",
                "A small box filled with different-sized buttons in its center", "A candle that can’t be lit",
                "A purple handkerchief embroidered with the name of a powerful archmage", "A tiny cage with no door",
                "An old key", "Half of a floorplan for a temple, castle, or some other structure",
                "An indecipherable treasure map", "A hilt from a broken sword",
                "A bit of folded cloth that, when unfolded, turns into a stylish cap", "A rabbit’s foot", "A glass eye",
                "A receipt of deposit at a bank in a far-flung city",
                "A cameo carved in the likeness of a hideous person", "A diary with seven missing pages",
                "A silver skull the size of a coin",
                "An empty silver snuffbox bearing an inscription on the surface that says “dreams”",
                "An alabaster mask", "A pyramid of sticky black incense that smells very bad",
                "An iron holy symbol devoted to an unknown god",
                "A nightcap that, when worn, gives you pleasant dreams",
                "A book that tells the story of a legendary hero's rise and fall, with the last chapter missing",
                "A single caltrop made from bone", "A vial of dragon blood", "A gold monocle frame without the lens",
                "An ancient arrow of elven design", "A 1-inch cube, each side painted a different color",
                "A needle that never bends", "A crystal knob from a door", "An ornate brooch of dwarven design",
                "A small packet filled with pink dust",
                "An empty wine bottle bearing a pretty label that says, “The Wizard of Wines Winery, Red Dragon Crush, 331422-W”",
                "A fragment of a beautiful song, written as musical notes on two pieces of parchment",
                "A silver teardrop earring made from a real teardrop",
                "A mosaic tile with a multicolored, glazed surface",
                "The shell of an egg painted with scenes of human misery in disturbing detail", "A petrified mouse",
                "A black pirate flag adorned with a dragon's skull",
                "A fan that, when unfolded, shows a sleeping cat and crossbones", "A set of bone pipes",
                "A tiny mechanical crab or spider that moves about when it’s not being observed",
                "A four-leaf clover pressed inside a book discussing manners and etiquette",
                "A glass jar containing lard with a label that reads, “Griffon Grease”",
                "A sheet of parchment upon which is drawn a complex mechanical contraption",
                "A wooden box with a ceramic bottom that holds a living worm with a head on each end of its body",
                "An ornate scabbard that fits no blade you have found so far",
                "A metal urn containing the ashes of a hero");
        trinketArray.addAll(trinketList);
        return trinketArray;
    }
}











class diceRoller
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


    //checks to see if given input is in correct form to begin printing rolls to screen
    public boolean checkInput()
    {
        boolean hasX = false;
        boolean hasD = false; 
        boolean hasY = false; 

        while (hasX == false || hasD == false || hasY == false)             //will NOT let you leave until given proper input
        {
            //reset bools incase has to ask for input again
            hasX = hasY = hasD = false;

            System.out.println("Please enter your dice roll in the form of x'd'y (EX: 3d10) OR just the dice type (4, 6, 10, 12, 20)" + 
                               "\n" + "Type 'quit' to stop rolling") ;

            String newRoll = in.next();     //gets input
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


    //rolls dice to print on screen
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

    //if using, inRoll MUST be in correct format to work - does not print rolls to screen, only for use in methods.
    public int internalRoll(String inRoll)
    {
        this.setRoll(inRoll);
        String[] stringInput = inRoll.split("d");
    
        int numOfRolls = Integer.valueOf(stringInput[0]);
        int diceType = Integer.valueOf(stringInput[1]);
        int total = 0;

        for (int i = 0; i < numOfRolls; i++)
        {
            int rollNum = numGen.ints(1, (diceType + 1)).findFirst().getAsInt();
            total = total + rollNum;
        }

        return total;
    }


    //rolls ability scores
    public void rollAbilities(characterCreater inChar)
    {
        //abilities = roll 4d6, take the highest 3

        String inString = "";
        char allowed = 'A';

        /*
        while (allowed != 'n' && allowed != 'y')        //can only move on if given yes or no (Y, y, N, n)
        {
            System.out.println("Rolling Ability Scores: Reroll 1s? y/n");
            inString = in.next();
            inString = inString.toLowerCase();
            if (inString.length() != 1) //must be only one character
            {
                allowed = inString.charAt(0);
            }
        }
        */

        allowed = 'n';  //FOR TESTING   ^ uncomment when done testing

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
                inChar.abilityScores.sortAbilityScores(abilityScore);
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
                inChar.abilityScores.sortAbilityScores(abilityScore);
            }        
        }
    }
}








class menuSelect extends characterCreater
{
    //choose function to use
    public void menu()
    {
        System.out.println("\n" + "Select Function:" + "\n" + "1: Dice Roller" + "\n" + "2: Roll ability scores" + "\n" + "3: Create randomized character" + "\n" + "4: Exit");

        Scanner in = new Scanner(System.in);
        String select = in.next();
        boolean correctSelect = false;
        boolean quit = false;

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
                        checkInput();
                        roll();
                    }
                    break;
                }
                case("2"):
                {
                    //go to ability score roller
                    correctSelect = true;
                    System.out.println("");
                    characterCreater newChar = new characterCreater();
                    rollAbilities(newChar);
                    abilityScores.printAbilityScores(newChar);
                    break;
                }
                case("3"):
                {
                    //go to character creater
                    correctSelect = true;
                    for (int i = 0; i < 1; i++)        //LOOP FOR TESTING
                    {
                        System.out.println("");
                        characterCreater newChar = new characterCreater();
                        newChar.createCharacter(newChar);
                    }
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