import java.text.DecimalFormat;
import java.util.*;


//i dunna we gotta plan shit better

class characterCreater extends diceRoller
{
    //create an character autobuilder                       hold ability scores / prof in own class or method?
    //      oh god probably need a different class
    //      select Class
    //          roll ability scores             -DONE
    //              record                      -DONE
    //          record hit die, primary ability, saving throws, all proficiencies, skills, and equipment        -DONE
    //          if magic user, record spell save DC and spell attack modifier                                   -DONE
    //          select path (if is granted on lv 1) (add ability to choose level?)                              -DONEish (no custom level)
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
    //      assign weapons, spells (oh god)                 --DONE

    //simple stats
    String charClass;
    String race;
    String subRace;
    String name;
    String gender;
    String personality;
    String background;
    String hitDie;
    String spellCastingAbility;
    String pA;              // Primary ability
    String pA2;             //if they have two pAs
    String savingThrow1;
    String savingThrow2;
    int hp;
    int aC;
    int speed;
    int spellSlots;
    int spellSaveDC;
    int spellAttackMod;
    boolean ritualCasting;
    boolean spellCastingFocus;

    //Traits
    racialTraits traits = new racialTraits();
    
    //ability score lists
    abilityScores abilityScores = new abilityScores();
    abilityMods abilityMods = new abilityMods();
    ArrayList<Integer> intAbilityScores = new ArrayList<Integer>();
    
    //proficiencies
    ArrayList<String> weaponProf = new ArrayList<String>();
    ArrayList<String> armorProf = new ArrayList<String>();
    ArrayList<String> toolProf = new ArrayList<String>();
    ArrayList<String> skillProf = new ArrayList<String>();
    ArrayList<String> instrumentProf = new ArrayList<String>();

    //complex stats
    ArrayList<String> charLanguages = new ArrayList<String>();
    ArrayList<String> equipment = new ArrayList<String>();
    ArrayList<weapon> weapons = new ArrayList<weapon>();
    ArrayList<features> features = new ArrayList<features>();
    ArrayList<List<String>> charSpells = new ArrayList<List<String>>();
    
    Random numGen = new Random();
    diceRoller newDice = new diceRoller();


    public void createCharacter(characterCreater inChar)
    {
        rollAbilities(inChar);      //starts the process of creating the intAbilityScores along with mods. assigning of scores is done when selecting class
        inChar.selectCharClass();
        inChar.selectRace();
    }

    public void proficiencies(String inProf)
    {
        //i dunna store them in here somehow       
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

    public void printAbilityScores()
    {
        System.out.println("\n" + "Ability Scores: ");     //for readability
        for (int i = 0; i < intAbilityScores.size(); i++)
        {
            System.out.print(intAbilityScores.get(i) + " ");
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

        switch(this.charClass)
        {
            case "Barbarian":
                System.out.println("Class: " + charClass + "\n");
                //adds simple stats
                hitDie = "1d12";
                pA = "Strength";
                savingThrow1 = "Strength";
                savingThrow2 = "Constitution";
                //hp is a simple stat but it needs ability scores first
                                                

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Strength", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 2

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 1

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0)); //only one left
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0)); 
                intAbilityScores.remove(0); rA--;

                //adding hp
                hp = 12 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //adding proficiencies:
                    //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Heavy Armor");
                armorProf.addAll(armorProfList);


                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons", "martial weapons");
                weaponProf.addAll(weaponProfList);


                    //adds skill proficiencies gained from Barbarian Class
                skillList = Arrays.asList("Animal Handling", "Athletics", "Intimidation", "Nature", "Perception", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of optional skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
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
                    weapons.add(barbarianNewMartialMelee);
                }
                else
                {
                    weapon barbarianGreataxe = new weapon().getSpecificWeapon("Martial Melee", "Greataxe", 1);
                    weapons.add(barbarianGreataxe);
                }

                    //adds either a simple weapon or two handaxes
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (weaponNum == 0)
                {
                    weapon barbarianNewSimpleMelee = new weapon().getMelee();
                    weapons.add(barbarianNewSimpleMelee);
                }
                else
                {
                    weapon handaxe = new weapon().getSpecificWeapon("Simple Melee", "Handaxe", 2);
                    weapons.add(handaxe);
                }

                    //adds 4 javelins
                weapon javelin = new weapon().getSpecificWeapon("Simple Melee", "Javelin", 4);        
                weapons.add(javelin);



                //adding equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding explorers pack
                equipment.add("Explorer's Pack");


                //adding class features
                    //rage
                features rage = new features();
                rage.setName("Rage");
                rage.setDesc("While Raging, you gain +2 Damage." + "\n" + 
                            "You can enter a rage as a bonus action. While raging, you gain the following benefits if you aren’t wearing heavy armor:" + "\n" +
                            "• You have advantage on Strength checks and Strength saving throws." + "\n" +
                            "• When you make a melee weapon attack using Strength, you gain a bonus to the damage roll that increases as you gain levels as a barbarian," + "\n" + 
                            "    as shown in the Rage Damage column of the Barbarian table." + "\n" + 
                            "• You have resistance to bludgeoning, piercing, and slashing damage.");
                rage.setUses(2);
                features.add(rage);

                    //unarmored defence
                features barbarianUnarmoredDef = new features();
                barbarianUnarmoredDef.setName("Unarmored Defence");
                barbarianUnarmoredDef.setDesc("While you are not wearing any armor, your Armor Class equals 10 + your Dexterity modifier + your Constitution modifier." + "\n" +  
                                    "You can use a shield and still gain this benefit.");
                features.add(barbarianUnarmoredDef);
                this.aC = 10 + abilityMods.getAbilityMod("Dexterity") + abilityMods.getAbilityMod("Constitution");          //unarmored Defence aC
                break;

            case "Bard":
                System.out.println("Class: " + charClass + "\n");
                //adds simple stats
                hitDie = "1d8";
                pA = "Charisma";
                savingThrow1 = "Dexterity";
                savingThrow2 = "Charisma";
                //hp is a simple stat but it needs ability scores first
                

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 2

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 1

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));   //only one left
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0)); 
                intAbilityScores.remove(0); rA--;


                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod

                //adding proficiencies:
                    //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor");
                armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons", "Hand Crossbows", "Longswords", "Rapiers", "Shortswords");
                weaponProf.addAll(weaponProfList);

                    //adds instrument proficiencies
                ArrayList<String> potentialInstrumentProf = new ArrayList<String>();
                potentialInstrumentProf.addAll(allInstrumentsList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three instruments from the list of optional instruments to be proficient in
                {
                    int instrumentNum = numGen.ints(0, potentialInstrumentProf.size()).findFirst().getAsInt();
                    String newSkill = potentialInstrumentProf.get(instrumentNum);                    //NEED TO MAKE IT SO IT CANT CHOOSE SAME instrument AGAIN
                    instrumentProf.add(newSkill);
                    potentialInstrumentProf.remove(instrumentNum);
                }

                    //adds skill proficiencies
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(allSkillsList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //adding weapons
                    //adds either a rapier, a longsword, or a simple melee weapon
                weaponNum = numGen.ints(0, 3).findFirst().getAsInt();
                if (weaponNum == 0)
                {
                    weapon bardRapier = new weapon().getSpecificWeapon("Martial Melee", "Rapier", 1);
                    weapons.add(bardRapier);
                }
                else if (weaponNum == 1)
                {
                    weapon bardLongsword = new weapon().getSpecificWeapon("Martial Melee", "Longsword", 1);
                    weapons.add(bardLongsword);
                }
                else
                {
                    weapon bardNewSimpleMelee = new weapon().getMelee();
                    weapons.add(bardNewSimpleMelee);
                }

                    //adding dagger
                weapon dagger = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 1);
                weapons.add(dagger);


                //Adding equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding leather armor
                equipment.add("Leather Armor");
                this.aC = 11 + abilityMods.getAbilityMod("Dexterity");              // 11 = leather armor aC

                    //adding pack
                int equipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (equipmentNum == 0)
                {
                    equipment.add("Diplomat's Pack");
                }
                else
                {
                    equipment.add("Entertainer's Pack");
                }
                
                    //adding instrument - make it so it chooses from the proficient instrument list???
                equipmentNum = numGen.ints(0, 2).findFirst().getAsInt();                //chooses either a lute or another instrument
                int instrumentNum = numGen.ints(0, 10).findFirst().getAsInt();          //chooses possible other instrument
                if (equipmentNum == 0)
                {
                    equipment.add("Lute");
                }
                else
                {
                    equipment.add(allInstrumentsList.get(instrumentNum));
                }
                

                //adding Spells
                    //adding simple spell stats
                spellSlots = 2;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                    //creating entire bard spell list
                spells bardSpells = new spells();
                ArrayList<List<String>> bardSpellList = bardSpells.buildBardSpellLists();

                List<String> charCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charCantrips);
                List<String> bardLevelSpells = new ArrayList<String>();       //creates an empty bard cantrip spell list to choose from
                bardLevelSpells.addAll(bardSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses two spell cantrips from the list
                {
                    int spellNum = numGen.ints(0, bardLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = bardLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    bardLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                bardLevelSpells.clear();            //clear to refill with 1st level spells
                bardLevelSpells.addAll(bardSpellList.get(1));

                List<String> char_Bard1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_Bard1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, bardLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = bardLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    bardLevelSpells.remove(newSpell);
                }

                //adding Features
                features bardicInspiration = new features();
                features.add(bardicInspiration);
                bardicInspiration.setName("Bardic Inspiration");
                bardicInspiration.setDesc("You use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you." + "\n" + 
                                    "That creature gains one Bardic Inspiration die, a d6. Once within the next 10 minutes, the creature can roll the die and add " + "\n" + 
                                    "the number rolled to one ability check, attack roll, or saving throw it makes.");
                break;

            case "Cleric":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d8";
                pA = "Wisdom";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Charisma";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 2

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));   //only one left
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0)); 
                intAbilityScores.remove(0); rA--;


                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //adding proficiencies:
                //adds armor proficiencys
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple weapons");
                weaponProf.addAll(weaponProfList);

                    //adds skill proficiencies
                skillList = Arrays.asList("History", "Insight", "Medicine", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //adding weapons
                    //adds either a mace or warhammer
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon in the list
                if (weaponNum == 0)
                {
                    weapon mace = new weapon().getSpecificWeapon("Simple Melee", "Mace", 1);
                    weapons.add(mace);
                }
                else
                {
                    weapon warhammer = new weapon().getSpecificWeapon("Martial Melee", "Warhammer", 1);
                    weapons.add(warhammer);
                }

                    //adding light crossbow w/ 20 bolts or any simple weapon
                weaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon in the list
                if (weaponNum == 0)
                {
                    weapon crossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon bolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    weapons.add(crossbow);
                    weapons.add(bolts);
                }
                else
                {
                    weapon newSimpleWeapon = new weapon().getMelee();
                    weapons.add(newSimpleWeapon);                       
                }

                
                

                //Adding equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding armor
                int clericEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (clericEquipmentNum == 0)
                {
                    equipment.add("Scale Mail");
                    this.aC = 14 + abilityMods.getAbilityMod("Dexterity");              // 14 = Scale Mail aC
                }
                else
                {
                    equipment.add("Leather Armor");
                    this.aC = 11 + abilityMods.getAbilityMod("Dexterity");              // 11 = leather armor aC
                }

                    //adding pack
                clericEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (clericEquipmentNum == 0)
                {
                    equipment.add("Priest's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }

                    //adding shield
                equipment.add("Shield");
                this.aC = aC + 2;           //shields add +2 to aC

                    //adding Holy Symbol
                List<String> clericHolySymbol = Arrays.asList("Amulet", "Emblem", "Reliquary");
                clericEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                equipment.add(clericHolySymbol.get(clericEquipmentNum));


                //adding Spells
                spellSlots = 2;
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                spells clericSpells = new spells();
                ArrayList<List<String>> clericSpellList = clericSpells.buildClericSpellLists();

                List<String> charClericCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charClericCantrips);
                List<String> clericLevelSpells = new ArrayList<String>();       //creates an empty cleric cantrip spell list to choose from
                clericLevelSpells.addAll(clericSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 3; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, clericLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = clericLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    clericLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                clericLevelSpells.clear();            //clear to refill with 1st level spells
                clericLevelSpells.addAll(clericSpellList.get(1));

                List<String> char_Cleric1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_Cleric1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, clericLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = clericLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    clericLevelSpells.remove(newSpell);
                }


                //Adding Features
                features divineDomain = new features();
                features.add(divineDomain);
                int domainNum = numGen.ints(0, 7).findFirst().getAsInt();
                List<String> domainList = Arrays.asList("Knowledge", "Life", "Light", "Nature", "Tempest", "Trickery", "War");
                String domainName = domainList.get(domainNum);
                switch (domainName)
                {
                    case "Knowledge":
                        divineDomain.setName("Knowledge");
                        divineDomain.setDesc("Gain spells: Command, Identify." + "\n" + 
                                            "Blessings of Knowledge: Learn two additional languages and skill proficiencies");
                        //Adding additional Spells
                        if (charSpells.get(1).indexOf("Command") == -1)         //check to make sure it wasnt already chosen
                        {
                            charSpells.get(1).add("Command"); 
                        }
                        charSpells.get(1).add("Identify");          //we already know which spells to add so dont need the list

                        for (int i = 0; i < 2; i++)     //adds two languages
                        {
                            int exoticChance = numGen.ints(0, 10).findFirst().getAsInt();       // 1 / 10 chance to get exotic language
                            if (exoticChance == 0)
                            {
                                String newExoticLanguage = new langauges().getSingleExoticLanguage();
                                charLanguages.add(newExoticLanguage);
                                
                            }
                            else
                            {
                                String newStandardLangauge = new langauges().getSingleStandardLanguage();
                                charLanguages.add(newStandardLangauge);
                            }
                        }
                        break;
                    case "Life":
                        divineDomain.setName("Life");
                        divineDomain.setDesc("Gain spells: Bless, Cure Wounds." + "\n" + 
                                             "Gain Proficiency: Heavy Armor." + "\n" + 
                                            "Disciple of Life: Your healing spells are more effective. Whenever you use a spell of 1st level or higher" + "\n" + 
                                            " to restore hit points to a creature, the creature regains additional hit points equal to 2 + the spell's level.");
                        //Adding additional Spells 
                        if (charSpells.get(1).indexOf("Bless") == -1)   //checks to see if bless was already chosen
                        {
                            charSpells.get(1).add("Bless");  
                        }

                        if (charSpells.get(1).indexOf("Cure Wounds") == -1)   //checks to see if cure wounds was already chosen
                        {
                            charSpells.get(1).add("Cure Wounds");  
                        }

                        //adding additional Proficiencies
                        armorProf.add("Heavy Armor");
                        break;
                    case "Light":
                        divineDomain.setName("Light");
                        divineDomain.setDesc("Gain Cantrip: Light." + "\n" +
                                            "Gain Spells: Burning Hands, Faerie Fire." + "\n" + 
                                            "Warding Flame: Interpose divine light between yourself and an attacking enemy. When you are attacked " +  "\n" +
                                            "by a creature within 30 feet of you that you can see, you can use your reaction to impose disadvantage " + "\n" +
                                            "on the attack roll.");
                        //Adding additional Spells 
                        if (charSpells.get(0).indexOf("Light") == -1)   //checks to see if the light cantrip was already chosen
                        {
                            charSpells.get(0).add("Light"); 
                        }
                        charSpells.get(1).add("Burning Hands");  
                        charSpells.get(1).add("Faerie Fire");
                        break;
                    case "Nature":
                        divineDomain.setName("Nature");
                        divineDomain.setDesc("Gain Proficiency: Heavy Armor." + "\n" +
                                            "Gain Spells: Animal Friendship, Speak with Animals." + "\n" + 
                                            "Acolyte of Nature: Gain one Druid Cantrip.");
                        //Adding additional Spells
                        charSpells.get(1).add("Animal Friendship");
                        charSpells.get(1).add("Speak with Animals");

                            //Adding druid Cantrip
                        spells druidSpells = new spells();
                        ArrayList<List<String>> druidSpellList = druidSpells.buildDruidSpellLists();
                        List<String> druidLevelSpells = new ArrayList<String>();       //creates an empty cantrip spell list to choose from
                        druidLevelSpells.addAll(druidSpellList.get(0));

                        int cantripNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                        String druidSpell = druidLevelSpells.get(cantripNum);
                        charSpells.get(0).add(druidSpell);

                        //Adding Proficiency 
                        armorProf.add("Heavy Armor");
                        break;
                    case "Tempest":
                        divineDomain.setName("Tempest");
                        divineDomain.setDesc("Gain Spells: Fog Cloud, Thunderwave." + "\n" + 
                                            "Gain Proficiencies: Martial Weapons and Heavy Armor." + "\n" + 
                                            "Wrath of the Storm: When a creature within 5 feet of you that you can see hits you with an attack, " + "\n" + 
                                            "you can use your reaction to cause the creature to make a Dexterity saving throw." + "\n" +
                                            "The creature takes 2d8 lightning or thunder damage (your choice) on a failed saving throw, " + "\n" +
                                            "and half as much damage on a successful one. Regain use upon long rest.");
                        divineDomain.setUses(abilityMods.getAbilityMod("Wisdom"));
                        charSpells.get(1).add("Fog Cloud");
                        charSpells.get(1).add("Thunderwave");

                        //Adding Proficiencies
                        weaponProf.add("Martial Weapons");
                        armorProf.add("Heavy Armor");
                        break;
                    case "Trickery":
                        divineDomain.setName("Trickery");
                        divineDomain.setDesc("Gain Spells: Charm Person, Disguise Self." + "\n" + 
                                            "Blessing of the Trickster: Starting when you choose this domain at 1st level, you can use your action " + "\n" + 
                                            "to touch a willing creature other than yourself to give it advantage on Dexterity (Stealth) checks." + "\n" + 
                                            "This blessing lasts for 1 hour or until you use this feature again.");
                        charSpells.get(1).add("Charm Person");
                        charSpells.get(1).add("Disguise Self");
                        break;
                    case "War":
                        divineDomain.setName("War");
                        divineDomain.setDesc("Gain Spells: Divine Favor, Shield of Faith." + "\n" + 
                                            "Gain Proficiency: Martial Weapons and Heavy Armor." + "\n" + 
                                            "War Priest: Your god delivers bolts of inspiration to you while you are engaged in battle." + "\n" +  
                                            "When you use the Attack action, you can make one weapon attack as a bonus action. Regain use upon long rest.");
                        divineDomain.setUses(abilityMods.getAbilityMod("Wisdom"));

                        charSpells.get(1).add("Divine Favor");
                        if (charSpells.get(1).indexOf("Shield of Faith") == -1)     //if it wasnt previously chosen
                        {
                            charSpells.get(1).add("Shield of Faith");
                        }
                        break;
                    default:
                        //should never hit
                        break;
                }
                break;

            case "Druid":
                System.out.println("Class: " + charClass);
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d8";
                pA = "Wisdom";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Intelligence";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 2

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));   //only one left
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0)); 
                intAbilityScores.remove(0); rA--;


                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod

                //Adding Proficiencies
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Clubs", "Daggers", "Darts", "Javelins", "Maces", "Quarterstaff", "Scimitars", "Sickles", "Slings", "Spears");
                weaponProf.addAll(weaponProfList);

                    //Addings tool Proficiencies
                toolProf.add("Herbalism Kit");

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Animal Handling", "Insight", "Medicine", "Nature", "Perception", "Religion", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("2d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding shield or simple weapon
                int druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (druidEquipmentNum == 0)
                {
                    equipment.add("Wooden Shield");
                    this.aC = aC + 2;               //shields add +2 to aC
                }
                else    //if simple weapon, either melee or ranged
                {
                    druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                    if (druidEquipmentNum == 0)
                    {
                        weapon newDruidSimpleWeapon = new weapon().getMelee();
                        weapons.add(newDruidSimpleWeapon);
                    }
                    else
                    {
                        weapon newDruidSimpleWeapon = new weapon().getRanged();
                        weapons.add(newDruidSimpleWeapon);
                    }
                }

                    //adding scimitar or a simple melee waepon
                druidEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (druidEquipmentNum == 0)
                {
                    weapon druidScimitar = new weapon().getSpecificWeapon("Martial Melee", "Scimitar", 1);
                    weapons.add(druidScimitar);
                }
                else
                {
                    weapon newDruidSimpleWeapon = new weapon().getMelee();
                    weapons.add(newDruidSimpleWeapon);
                }

                    //leather armor and pack
                equipment.add("Leather Armor");
                this.aC = 11 + abilityMods.getAbilityMod("Dexterity");              // 11 = leather armor aC

                equipment.add("Explorer's Pack");

                druidEquipmentNum = numGen.ints(0, 4).findFirst().getAsInt();
                List<String> druidicFocus = Arrays.asList("Sprig of Mistletoe", "Totem", "Wooden Staff", "Yew Wand");
                equipment.add(druidicFocus.get(druidEquipmentNum));
            
                
                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                spells druidSpells = new spells();
                ArrayList<List<String>> druidSpellList = druidSpells.buildDruidSpellLists();

                List<String> charDruidCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charDruidCantrips);
                List<String> druidLevelSpells = new ArrayList<String>();       //creates an empty druid cantrip spell list to choose from
                druidLevelSpells.addAll(druidSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = druidLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    druidLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                druidLevelSpells.clear();            //clear to refill with 1st level spells
                druidLevelSpells.addAll(druidSpellList.get(1));

                List<String> char_druid1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_druid1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, druidLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = druidLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    druidLevelSpells.remove(newSpell);
                }


                //Adding Features
                features druidic = new features();
                features.add(druidic);
                druidic.setName("Druidic");
                druidic.setDesc("You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden " + "\n" +
                                    "messages. You and others who know this language automatically spot such a message.");
                break;

            case "Fighter":
                System.out.println("Class: " + charClass);
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d10";
                pA = "Strength";
                savingThrow1 = "Strength";
                savingThrow2 = "Constitution";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Strength", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(0)); 
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 3 */

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 2

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--; //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));   //only one left
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0)); 
                intAbilityScores.remove(0); rA--;


                //adding hp
                hp = 10 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //Adds armor proficiencies
                armorProfList = Arrays.asList("All Armor", "Shields");
                armorProf.addAll(armorProfList);

                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding chain mail or leather
                int fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    equipment.add("Chain Mail");
                    if (abilityScores.getAbilityScore("Strength") >= 13)
                    {
                        this.aC = 16;            // 16 = chain mail aC
                    }
                }
                else    //if simple weapon, either melee or ranged
                {
                    equipment.add("Leather Armor");
                    this.aC = 11 + abilityMods.getAbilityMod("Dexterity");              // 11 = leather armor aC
                }

                    //adding longbow with 20 arrows
                weapon fighterLongbow = new weapon().getSpecificWeapon("Martial Ranged", "Longbow", 1);
                weapons.add(fighterLongbow);
                weapon fighterArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                weapons.add(fighterArrows);

                    //adding martial weapon w/ shield or two martial weapons.
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    weapon newFighterWeapon = new weapon().getMartialMelee();
                    weapons.add(newFighterWeapon);
                    equipment.add("Shield");
                    this.aC = aC + 2;           //shields add +2 aC
                }
                else
                {
                    weapon newFighterWeapon = new weapon().getMartialMelee();
                    weapon newFighterWeapon1 = new weapon().getMartialMelee();
                    weapons.add(newFighterWeapon);
                    weapons.add(newFighterWeapon1);
                }

                    //adding a light crossbow w/ 20 bolts or two handaxes
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    weapon fighterCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon fighterBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    weapons.add(fighterCrossbow);
                    weapons.add(fighterBolts);
                }
                else
                {
                    weapon fighterHandaxes = new weapon().getSpecificWeapon("Simple Melee", "Handaxe", 2);
                    weapons.add(fighterHandaxes);
                }

                    //adding either a dungeoneers pack or an explorers pack
                fighterEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which item in the list
                if (fighterEquipmentNum == 0)
                {
                    equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }


                //Adding features
                    //adding fighting style
                features fightingStyle = new features();
                features.add(fightingStyle);
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
                                                "damage rolls with that weapon.");
                        break;
                    case "Great Weapon Fighting":
                        fightingStyle.setName("Great Weapon Fighting");
                        fightingStyle.setDesc("When you roll a 1 or 2 on a damage die for an attack you  make with a melee weapon that you " + "\n" +  
                                            "are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2." + "\n" +  
                                            "The weapon must have the two-handed or versatile property for you to gain this benefit.");
                        break;
                    case "Protection":
                        fightingStyle.setName("Protection");
                        fightingStyle.setDesc("When a creature you can see attacks a target other than you that is within 5 feet of you, " + "\n" + 
                                            "you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield.");
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
                features.add(secondWind);
                secondWind.setName("Second Wind");
                secondWind.setDesc("You have a limited well of stamina that you can draw on to protect yourself from harm." + "\n" +  
                                    "On your turn, you can use a bonus action to regain hit points equal to 1d 10 + your fighter level.");
                break;

            case "Monk":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d8";
                pA = "Dexterity";
                pA2 = "Wisdom";
                savingThrow1 = "Strength";
                savingThrow2 = "Dexterity";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(0));             //saving throw1
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(0));
                intAbilityScores.remove(0);

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(0));         //not randomly assigned as monk is a fighting class that needs con
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 2; ///rA = "Remaining Abilitys" that have not been asigned a num yet (2 have not been asigned)

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 1

                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod

                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Shortsword");
                weaponProf.addAll(weaponProfList);

                    //adding Tool proficiencies - either one set of artisans tools or one instrument
                int monkProfNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (monkProfNum == 0)
                {
                    monkProfNum = numGen.ints(0, 17).findFirst().getAsInt();    //17 = size of artisans tool list
                    toolProf.add(allArtisansToolsList.get(monkProfNum));
                }
                else
                {
                    monkProfNum = numGen.ints(0, 10).findFirst().getAsInt();    //10 = size of instrument list
                    instrumentProf.add(allInstrumentsList.get(monkProfNum));
                }

                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Athletics", "History", "Insight", "Religion", "Stealth");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("5d4");      //Monk does not get their rolled multiplied by 10
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding shortsword or any simple weapon
                int monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (monkEquipmentNum == 0)
                {
                    weapon monkShortsword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    weapons.add(monkShortsword);
                }
                else
                {
                    monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses either melee or ranged simple weapon
                    if (monkEquipmentNum == 0) //melee
                    {
                        weapon monkSimpleMelee = new weapon().getMelee();
                        weapons.add(monkSimpleMelee);
                    }
                    else       //ranged
                    {
                        weapon monkSimpleRanged = new weapon().getRanged();
                        weapons.add(monkSimpleRanged);
                    }
                }

                    //adding dungeoneers pack or explorers pack
                monkEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (monkEquipmentNum == 0)
                {
                    equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }
                
                    //adding 10 darts
                weapon darts = new weapon().getSpecificWeapon("Simple Ranged", "Dart", 10);
                weapons.add(darts);


                //adding features
                features monkUnarmoredDef = new features();
                monkUnarmoredDef.setName("Unarmored Defense");
                monkUnarmoredDef.setDesc("while you are w earing no armor and not w ielding a shield, your AC equals 10 + " + "\n" + 
                                            "your Dexterity modifier + your Wisdom modifier.");
                this.aC = 10 + abilityMods.getAbilityMod("Dexterity") + abilityMods.getAbilityMod("Wisdom");
                features.add(monkUnarmoredDef);

                features martialArts = new features();
                martialArts.setName("Martial Arts");
                martialArts.setDesc("You gain the following benefits while you are unarmed or wielding only monk weapons and you aren’t wearing " + "\n" + 
                                    "armor or wielding a shield:" + "\n" + 
                                    "• You can use Dexterity instead of Strength for the attack and damage rolls of your" + "\n" + 
                                    "   unarmed strikes and monk weapons." + "\n" + 
                                    "• You can roll a d4 in place of the normal damage of your unarmed strike or monk weapon." + "\n" + 
                                    "   This die changes as you gain monk levels, as shown in the Martial Arts column of the Monk table." + "\n" + 
                                    "• When you use the Attack action with an unarmed strike or a monk weapon on your turn, you can make" + "\n" + 
                                    "   one unarmed strike as a bonus action. For example, if you take the Attack action and attack with" + "\n" +
                                    "   a quarterstaff, you can also make an unarmed strike as a bonus action, assuming you haven't already" + "\n" + 
                                    "   taken a bonus action this turn.");
                features.add(martialArts);
                break;

            case "Paladin": 
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d10";
                pA = "Strength";
                pA2 = "Charisma";
                savingThrow1 = "Wisdom";
                savingThrow2 = "Charisma";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Strength", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0));
                intAbilityScores.remove(0);

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));             //saving throw1
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(0));         //not randomly assigned as paladin is a fighting class that needs con
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 2; ///rA = "Remaining Abilitys" that have not been asigned a num yet (two have not been asigned)

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 1

                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                armorProfList = Arrays.asList("All Armor", "Shields");
                armorProf.addAll(weaponProfList);;

                    //Adding skill proficiencies
                skillList = Arrays.asList("Athletics", "Insight", "Intimidation", "Medicine", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding martial weapon w/ shield or two martial weapons
                int paladinWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (paladinWeaponNum == 0)
                {
                    weapon paladinNewWeapon = new weapon().getMartialMelee();
                    weapons.add(paladinNewWeapon);
                    equipment.add("Shield");
                    this.aC = aC + 2;               //shields add +2 to aC
                }
                else
                {
                    weapon paladinNewWeapon = new weapon().getMartialMelee();
                    weapon paladinNewWeapon1 = new weapon().getMartialMelee();
                    weapons.add(paladinNewWeapon); 
                    weapons.add(paladinNewWeapon1);
                }

                    //adding five javelins or any simple melee weapon
                paladinWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (paladinWeaponNum == 0)
                {
                    weapon javelins = new weapon().getSpecificWeapon("Simple Melee", "Javelin", 5);
                    weapons.add(javelins);
                }
                else
                {
                    weapon paladinNewWeapon = new weapon().getMelee();
                    weapons.add(paladinNewWeapon);
                }

                    //adding priests pack or explorers pack
                int paladinEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which pack
                if (paladinEquipmentNum == 0)
                {
                    equipment.add("Priest's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }

                equipment.add("Chain mail");
                this.aC = 16;                       //chain mail aC = 16

                    //adding holy symbol
                List<String> paladinHolySymbol = Arrays.asList("Amulet", "Emblem", "Reliquary");
                paladinEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                equipment.add(paladinHolySymbol.get(paladinEquipmentNum));


                //adding features
                features divineSense = new features();
                divineSense.setName("Divine Sense");
                divineSense.setDesc("The presence of strong evil registers on your senses like a noxious odor, and powerful good rings " + "\n" + 
                        "like heavenly music in your ears. As an action, you can open your awareness to detect such forces." + "\n" + 
                        "Until the end of your next turn, you know the location of any celestial, fiend, or undead within " + "\n" + 
                        "60 feet of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being " + "\n" + 
                        "whose presence you sense, but not its identity (the vampire Count Strahd von Zarovich, for instance)." + "\n" + 
                        "Within the same radius, you also detect the presence of any place or object that has been consecrated or " + "\n" + 
                        "desecrated, as with the hallow spell. This feature is regained upon a long rest.");
                divineSense.setUses(1 + abilityMods.getAbilityMod("Charisma"));
                features.add(divineSense);

                features layOnHands = new features();
                layOnHands.setName("Lay On Hands");
                layOnHands.setDesc("You have a pool of healing power that replenishes when you take a long rest. With that pool, " + "\n" + 
                        "you can restore a total number of hit points equal to your paladin level x 5." + "\n" +
                        "As an action, you can touch a creature and draw power from the pool to restore a number of hit points " + "\n" + 
                        "to that creature, up to the maximum amount remaining in your pool. Alternatively, you can expend " + "\n" + 
                        "5 hit points from your pool of healing to cure the target of one disease or neutralize one poison affecting it." + "\n" +  
                        "You can cure multiple diseases and neutralize multiple poisons with a single use of Lay on Hands, " + "\n" + 
                        "expending hit points separately for each one.");
                features.add(layOnHands);


                //Adding spells
                spellSlots = 0;                     //while the paladin can cast spells, they cannot at level 1
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;
                break;

            case "Ranger":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d10";
                pA = "Dexterity";
                pA2 = "Wisdom";
                savingThrow1 = "Strength";
                savingThrow2 = "Dexterity";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(0));             //saving throw1
                    abilityMods.setAbilityMod("Strength", intAbilityScores.get(0));
                intAbilityScores.remove(0);
            
                    //assigning random Ability Scores / Mods
                rA = 3; ///rA = "Remaining Abilitys" that have not been asigned a num yet (three have not been asigned)

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;    //rA = 1

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                hp = 10 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Martial Weapons");
                weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                armorProfList = Arrays.asList("Light Armor", "Medium Armor", "Shields");
                armorProf.addAll(weaponProfList);;

                    //Adding skill proficiencies
                skillList = Arrays.asList("Animal Handling", "Athletics", "Insight", "Investigation", "Nature", "Perception", "Stealth", "Survival");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 3; i++)         //Chooses three skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("5d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding scale mail or leather armor
                int rangerWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rangerWeaponNum == 0)
                {
                    equipment.add("Scale Mail");
                    if (abilityMods.getAbilityMod("Dexterity") >= 2)            //scale mail aC = 14 + Dexterity mod (max of 2)
                    {
                        this.aC = 14 + 2;
                    }
                    else
                    {
                        this.aC = 14 + abilityMods.getAbilityMod("Dexterity"); 
                    }
                }
                else
                {
                    equipment.add("Leather Armor");
                    this.aC = 11 + abilityMods.getAbilityMod("Dexterity");
                }


                    //adding two shortswords or two simple melee weapons
                rangerWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rangerWeaponNum == 0)
                {
                    weapon rangerShortsword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 2);
                    weapons.add(rangerShortsword);
                }
                else
                {
                    weapon rangerSimpleMelee = new weapon().getMelee();
                    weapon rangerSimpleMelee1 = new weapon().getMelee();
                    weapons.add(rangerSimpleMelee);
                    weapons.add(rangerSimpleMelee1);
                }

                    //adding either dungeoneers pack or explorers pack
                int rangerEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which pack
                if (rangerEquipmentNum == 0)
                {
                    equipment.add("Dundeoneer's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }

                    //adding longbow and arrows
                weapon rangerLongbow = new weapon().getSpecificWeapon("Martial Ranged", "Longbow", 1);
                weapon rangerArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                weapons.add(rangerLongbow);
                weapons.add(rangerArrows);


                //Adding features
                features favoredEnemy = new features();
                favoredEnemy.setName("Favored Enemy");

                List<String> favoredEnemyList = Arrays.asList("aberrations", "beasts", "celestials", "constructs", "dragons", "elementals", "fey", "fiends", 
                                                                "giants", "monstrosities", "oozes", "plants", "undead");
                rangerEquipmentNum = numGen.ints(0, 13).findFirst().getAsInt();   //chooses which enemy to favor
                String rangerFavoredEnemy = favoredEnemyList.get(rangerEquipmentNum);

                favoredEnemy.setDesc("You have significant experience studying, tracking, hunting, and even talking to " + rangerFavoredEnemy + "." + "\n" +
                                     "You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence " + "\n" + 
                                     "checks to recall information about them. When you gain this feature, you also learn one language of your " + "\n" + 
                                     "choice that is spoken by your favored enemies, if they speak one at all." + "\n" + 
                                     "!!!WARNING!!! No access to monster manual so this extra language is not set !!!WARNING!!!");
                features.add(favoredEnemy);

                features naturalExplorer = new features();
                naturalExplorer.setName("Natural Explorer");

                List<String> favoredEnvironmnetList = Arrays.asList("arctic", "coast", "desert", "forest", "grassland", "mountain", "swamp", "Underdark");
                rangerEquipmentNum = numGen.ints(0, 8).findFirst().getAsInt();   //chooses which environment to favor
                String favoredEnvironmnet = favoredEnvironmnetList.get(rangerEquipmentNum);

                naturalExplorer.setDesc("You are particularly familiar with the " + favoredEnvironmnet + " environment and are adept at traveling and surviving in " + "\n" +
                                        "this region. When you make an Intelligence or Wisdom check related to your favored terrain, your " + "\n" + 
                                        "proficiency bonus is doubled if you are using a skill that you’re proficient in. While traveling " + "\n" + 
                                        "for an hour or more in your favored terrain, you gain the following benefits:" + "\n" + 
                                        "• Difficult terrain doesn’t slow your group’s travel." + "\n" + 
                                        "• Your group can’t become lost except by magical means." + "\n" + 
                                        "• Even when you are engaged in another activity while traveling" + "\n" + 
                                        "   (such as foraging, navigating, or tracking), you remain alert to danger." + "\n" + 
                                        "• If you are traveling alone, you can move stealthily at a normal pace." +"\n" + 
                                        "• When you forage, you find twice as much food as you normally would." +"\n" + 
                                        "• While tracking other creatures, you also learn their exact number, their sizes, " + "\n" + 
                                        "   and how long ago they passed through the area.");


                //Adding Spells
                spellSlots = 0;                     //while the ranger can cast spells, they cannot at level 1
                spellCastingAbility = "Wisdom";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Wisdom");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Wisdom");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = false;
                break;

            case "Rogue":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d8";
                pA = "Dexterity";
                savingThrow1 = "Dexterity";
                savingThrow2 = "Intelligence";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(0));
                intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));
                intAbilityScores.remove(abilityNum); rA--;

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;    //rA = 1

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons", "Hand Crossbows", "Longswords", "Rapiers", "Shortswords");
                weaponProf.addAll(weaponProfList);

                    //adding armor proficiencies
                armorProf.add("Light Armor");

                    //adding tool proficiencies
                toolProf.add("Thieves Tools");

                    //Adding skill proficiencies
                skillList = Arrays.asList("Acrobatics", "Athletics", "Deception", "Insight", "Intimidation", "Investigation", "Perception", 
                                            "Performance", "Persuasion", "Sleight of Hand", "Stealth");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 4; i++)         //Chooses four skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);

                    //adding rapier or shortsword
                int rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon rogueRapier = new weapon().getSpecificWeapon("Martial Melee", "Rapier", 1);
                    weapons.add(rogueRapier);
                }
                else
                {
                    weapon rogueShortSword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    weapons.add(rogueShortSword);
                }

                    //adding a shortbow w/ 20 arrows or a shortsword
                rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon rogueShortbow = new weapon().getSpecificWeapon("Simple Ranged", "Shortbow", 1);
                    weapon rogueArrows = new weapon().getSpecificWeapon("Ammunition", "Arrows", 20);
                    weapons.add(rogueShortbow);
                    weapons.add(rogueArrows);
                }
                else
                {
                    weapon rogueShortSword = new weapon().getSpecificWeapon("Martial Melee", "Shortsword", 1);
                    weapons.add(rogueShortSword);
                }

                    //adding burglars pack, dungeoneers pack, or explorers pack
                int rogueEquipmentNum = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which pack
                List<String> roguePacks = Arrays.asList("Burglar's Pack", "Dungeoneer's Pack", "Explorer's Pack");
                equipment.add(roguePacks.get(rogueEquipmentNum));

                    //adding leather armor, two daggers, and thieves tools
                equipment.add("Leather Armor");
                this.aC = 11 + abilityMods.getAbilityMod("Dexterity");      //leather armor aC = 11 + dex mod

                weapon rogueDaggers = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 2);
                weapons.add(rogueDaggers);

                equipment.add("Thieve's Tools");


                //Adding features
                features sneakAttack = new features();
                sneakAttack.setName("Sneak Attack");
                sneakAttack.setDesc("You know how to strike subtly and exploit a foe’s distraction. Once per turn, you can deal an extra " + "\n" + 
                            "1d6 damage to one creature you hit with an attack if you have advantage on the attack roll. The attack " +"\n" + 
                            "must use a finesse or a ranged weapon. You don’t need advantage on the attack roll if another enemy of " + "\n" + 
                            "the target is within 5 feet of it, that enemy isn’t incapacitated, and you don’t have disadvantage on " + "\n" + 
                            "the attack roll. The amount o f the extra damage increases as you gain levels in this class, as shown " + "\n" + 
                            "in the Sneak Attack column of the Rogue table. (Currently 1d6)"); 
                features.add(sneakAttack);

                features thievesCant = new features();
                thievesCant.setName("Thieves' Cant");
                thievesCant.setDesc("During your rogue training you learned thieves’ cant, a secret mix of dialect, jargon, and code that " + "\n" + 
                            "allows you to hide messages in seemingly normal conversation. Only another creature that knows thieves’ cant " + "\n" + 
                            "understands such messages. It takes four times longer to convey such a message than it does to speak the same " + "\n" + 
                            "idea plainly. In addition, you understand a set of secret signs and symbols used to convey short, simple messages, " + "\n" + 
                            "such as whether an area is dangerous or the territory of a thieves’ guild, whether loot is nearby, or whether the " + "\n" +
                            "people in an area are easy marks or will provide a safe house for thieves on the run.");   
                features.add(thievesCant);
                break;

            case "Sorcerer":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d6";
                pA = "Charisma";
                savingThrow1 = "Charisma";
                savingThrow2 = "Constitution";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(0));
                intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));
                intAbilityScores.remove(abilityNum); rA--;

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;    //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                hp = 6 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod

                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Daggers", "Darts", "Slings", "QuarterStaff", "Light Crossbows");
                weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Deception", "Insight", "Intimidation", "Persuasion", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }

                //Adding Equipment
                    //adding gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("3d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);


                
                    //adding a crossbow w/ 20 bolts or any simple weapon
                int sorcererWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                rogueWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (rogueWeaponNum == 0)
                {
                    weapon sorcererCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon sorcererBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    weapons.add(sorcererCrossbow);
                    weapons.add(sorcererBolts);
                }
                else
                {
                    sorcererWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses if melee or ranged
                    if (sorcererWeaponNum == 0)
                    {
                        weapon sorcererSimpleMelee = new weapon().getMelee();
                        weapons.add(sorcererSimpleMelee);
                    }
                    else
                    {
                        weapon sorcererSimpleRanged = new weapon().getRanged();
                        weapons.add(sorcererSimpleRanged);
                    }
                }

                    //adds component pouch or arcane focus
                int sorcererEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (sorcererWeaponNum == 0)
                {
                    equipment.add("Component Pouch");
                }
                else
                {
                    sorcererEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> sorcererArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    equipment.add(sorcererArcaneFocus.get(sorcererEquipmentNum));
                }

                    //adding pack
                sorcererEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (sorcererEquipmentNum == 0)
                {
                    equipment.add("Dungeoneer's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }

                    //adding two daggers
                weapon daggers = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 2);
                weapons.add(daggers);

                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;

                spells sorcererSpells = new spells();
                ArrayList<List<String>> sorcererSpellList = sorcererSpells.buildSorcererSpellLists();

                List<String> charSorcererCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charSorcererCantrips);
                List<String> sorcererLevelSpells = new ArrayList<String>();       //creates an empty sorcerer cantrip spell list to choose from
                sorcererLevelSpells.addAll(sorcererSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 4; i++)         //Chooses four cantrips from the list
                {
                    int spellNum = numGen.ints(0, sorcererLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = sorcererLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    sorcererLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                sorcererLevelSpells.clear();            //clear to refill with 1st level spells
                sorcererLevelSpells.addAll(sorcererSpellList.get(1));

                List<String> char_sorcerer1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_sorcerer1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, sorcererLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = sorcererLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    sorcererLevelSpells.remove(newSpell);
                }


                //Adding features
                features origin = new features();
                features.add(origin);
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
                        case "Blue":
                            bloodlineDamage = "Lightning";
                        case "Brass":
                            bloodlineDamage = "Fire";
                        case "Bronze":
                            bloodlineDamage = "Lightning";
                        case "Copper": 
                            bloodlineDamage = "Acid";
                        case "Gold":
                            bloodlineDamage = "Fire";
                        case "Green":
                            bloodlineDamage = "Poison";
                        case "Red":
                            bloodlineDamage = "Fire";
                        case "Silver":
                            bloodlineDamage = "Cold";
                        case "White": 
                            bloodlineDamage = "Cold";                      
                    }
                    origin.setDesc("Your innate magic comes from draconic magic that was mingled with your blood or that of your ancestors." + "\n" + 
                                    "You can also speak, read, and write Draconic. Whenever you make a Charisma check when interacting with dragons, " + "\n" +
                                    "your proficiency bonus is doubled." + "\n" +
                                    "Draconic Origin: " + bloodline + "\n" + 
                                    "Damage Type: " + bloodlineDamage);
                    charLanguages.add("Draconic");

                        //adding draconic resilience
                    features draconicResilience = new features();
                    features.add(draconicResilience);
                    draconicResilience.setName("Draconic Resilience");
                    draconicResilience.setDesc("At 1st level, your hit point maximum increases by 1 and increases by 1 again whenever you gain " + "\n" + 
                                    "a level in this class. Additionally, parts of your skin are covered by a thin sheen of dragon-like scales. " + "\n" + 
                                    "When you aren’t wearing armor, your AC equals 13 + your Dexterity modifier.");
                    //no armor currently in inventory or equipped
                    this.aC = 13 + abilityMods.getAbilityMod("Dexterity");
                }
                else    //best feature in the gaaaame
                {
                    origin.setName("Wild Magic");
                    origin.setDesc("Your innate magic comes from the wild forces of chaos that underlie the order of creation.");

                        //Adding wild magic surge
                    features wildMagicSurge = new features();
                    features.add(wildMagicSurge);
                    wildMagicSurge.setName("Wild Magic Surge");
                    wildMagicSurge.setDesc("Your spellcasting can unleash surges of untamed magic. Immediately after you cast a sorcerer spell of 1st level " + "\n" + 
                                    "or higher, the DM can have you roll a d20. If you roll a 1, roll on the Wild Magic Surge table to create a random " + "\n" + 
                                    "magical effect.");

                        //Adding tides of Chaos
                    features tidesOfChaos = new features();
                    features.add(tidesOfChaos);
                    tidesOfChaos.setName("Tides Of Chaos");
                    tidesOfChaos.setDesc("You can manipulate the forces of chance and chaos to gain advantage on one attack roll, ability check, " + "\n" + 
                                    "or saving throw. Once you do so, you must finish a long rest before you can use this feature again. OR any " + "\n" + 
                                    "time before you regain the use of this feature, the DM can have you roll on the Wild Magic Surge table " + "\n" + 
                                    "immediately after you cast a sorcerer spell of 1st level or higher. You then regain the use of this feature.");
                    tidesOfChaos.setUses(1);
                }
                break;

            case "Warlock":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d8";
                pA = "Charisma";
                savingThrow1 = "Charisma";
                savingThrow2 = "Wisdom";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Charisma", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; //rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));
                intAbilityScores.remove(abilityNum); rA--;

                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;    //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                hp = 8 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Simple Weapons");
                weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "Deception", "History", "Intimidation", "Investigation", "Nature", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //Adding Gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);
                
                    //adding a crossbow w/ 20 bolts or any simple weapon
                int warlockWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (warlockWeaponNum == 0)
                {
                    weapon warlockCrossbow = new weapon().getSpecificWeapon("Simple Ranged", "Crossbow - Light", 1);
                    weapon warlockBolts = new weapon().getSpecificWeapon("Ammunition", "Crossbow Bolts", 20);
                    weapons.add(warlockCrossbow);
                    weapons.add(warlockBolts);
                }
                else
                {
                    warlockWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses if melee or ranged
                    if (warlockWeaponNum == 0)
                    {
                        weapon warlockSimpleMelee = new weapon().getMelee();
                        weapons.add(warlockSimpleMelee);
                    }
                    else
                    {
                        weapon warlockSimpleRanged = new weapon().getRanged();
                        weapons.add(warlockSimpleRanged);
                    }
                }

                    //adding component pouch or arcane focus
                int warlockEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (warlockWeaponNum == 0)
                {
                    equipment.add("Component Pouch");
                }
                else
                {
                    warlockEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> warlockArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    equipment.add(warlockArcaneFocus.get(warlockEquipmentNum));
                }

                    //adding pack
                warlockEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (warlockEquipmentNum == 0)
                {
                    equipment.add("Scholar's Pack");
                }
                else
                {
                    equipment.add("Dungeoneer’s's Pack");
                }


                //SpellCasting
                spellSlots = 1;
                int invocations = 0;
                spellCastingAbility = "Charisma";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Charisma");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Charisma");         // +2 same thing
                ritualCasting = false;
                spellCastingFocus = true;

                spells warlockSpells = new spells();
                ArrayList<List<String>> warlockSpellList = warlockSpells.buildWarlockSpellLists();

                List<String> charWarlockCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charWarlockCantrips);
                List<String> warlockLevelSpells = new ArrayList<String>();       //creates an empty warlock cantrip spell list to choose from
                warlockLevelSpells.addAll(warlockSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 2; i++)         //Chooses two cantrips from the list
                {
                    int spellNum = numGen.ints(0, warlockLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = warlockLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    warlockLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                warlockLevelSpells.clear();            //clear to refill with 1st level spells
                warlockLevelSpells.addAll(warlockSpellList.get(1));

                List<String> char_warlock1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_warlock1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 2; j++)
                {
                    int spellNum = numGen.ints(0, warlockLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = warlockLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    warlockLevelSpells.remove(newSpell);
                }


                //Adding features
                features otherworldlyPatron = new features();
                features.add(otherworldlyPatron);
                int patronName = numGen.ints(0, 3).findFirst().getAsInt();   //chooses which draconic origin
                List<String> patronList = Arrays.asList("The Archfey", "The Fiend", "Great Old One");
                String patron = patronList.get(patronName);
                switch (patron)
                {
                    case "The Archfey":
                        otherworldlyPatron.setName("The Archfey");
                        otherworldlyPatron.setDesc("Your patron is a lord or lady of the fey, a creature of legend who holds secrets that " + "\n" + 
                                            "were forgotten before the mortal races w ere born. This being’s motivations are often inscrutable, " + "\n" + 
                                            "and sometimes whimsical, and might involve a striving for greater magical power or the settling " + "\n" + 
                                            "of age-old grudges.");

                            //adding fey level 1 spells
                        charSpells.get(1).add("Faerie Fire");
                        charSpells.get(1).add("Sleep");

                            //adding Fey Presence
                        features feyPresence = new features();
                        features.add(feyPresence);
                        feyPresence.setName("Fey Presence");
                        feyPresence.setDesc("Your patron bestows upon you the ability to project the beguiling and fearsome presence of the fey." + "\n" + 
                                            "As an action, you can cause each creature in a 10-foot cube originating from you to make a Wisdom " + "\n" + 
                                            "saving throw against your warlock spell save DC. The creatures that fail their saving throws are all " + "\n" + 
                                            "charmed or frightened by you (your choice) until the end of your next turn." + "\n" + 
                                            "You regain this feature upon a long or short rest.");
                        feyPresence.setUses(1);
                        break;
                    case "The Fiend":
                        otherworldlyPatron.setName("The Fiend");
                        otherworldlyPatron.setDesc("You have made a pact with a fiend from the lower planes of existence, a being whose aims are evil, " + "\n" +  
                                            "even if you strive against those aims. Such beings desire the corruption or destruction of all things, " + "\n" + 
                                            "ultimately including you.");
                        
                            //adding fiend spells
                        charSpells.get(1).add("Burning Hands");
                        charSpells.get(1).add("Command");
                    
                            //adding dark ones blessing
                        features darkOnesBlessing = new features();
                        features.add(darkOnesBlessing);
                        darkOnesBlessing.setName("Dark One's Blessing");
                        darkOnesBlessing.setDesc("When you reduce a hostile creature to 0 hit points, you gain temporary hit points equal to your " + "\n" + 
                                            "Charisma modifier + your warlock level (minimum of 1).");
                        break;
                    case "Great Old One":
                        otherworldlyPatron.setName("Great Old One");
                        otherworldlyPatron.setDesc("Your patron is a mysterious entity whose nature is utterly foreign to the fabric of reality." + "\n" + 
                                            "It might come from the Far Realm, the space beyond reality, or it could be one of the elder gods known " + "\n" + 
                                            "only in legends. Its motives are incomprehensible to mortals, and its knowledge so immense and ancient " + "\n" + 
                                            "that even the greatest libraries pale in comparison to the vast secrets it holds. The Great Old One "  + "\n" + 
                                            "might be unaware of your existence or entirely indifferent to you, but the secrets you have learned " + "\n" + 
                                            "allow you to draw your magic from it.");

                            //adding great old one's spells
                        charSpells.get(1).add("Dissonant Whispers");
                        charSpells.get(1).add("Tasha's hideous laughter");

                            //adding awakened mind
                        features awakenedMind = new features();
                        features.add(awakenedMind);
                        awakenedMind.setName("Awakened Mind");
                        awakenedMind.setDesc("Your alien knowledge gives you the ability to touch the minds of other creatures. You can communicate " + "\n" + 
                                            "telepathically with any creature you can see within 30 feet of you. You don’t need to share a language " + "\n" + 
                                            "with the creature for it to understand your telepathic utterances, but the creature must be able to " + "\n" + 
                                            "understand at least one language.");
                        break;
                }
                break;

            case "Wizard":
                //adds simple stats
                System.out.println("Class: " + charClass + "\n");
                hitDie = "1d6";
                pA = "Intelligence";
                savingThrow1 = "Intelligence";
                savingThrow2 = "Wisdom";

                //assigning primary Ability Scores / Mods
                abilityScores.setAbilityScore("Intelligence", intAbilityScores.get(0));            //pA
                    abilityMods.setAbilityMod("Intelligence", intAbilityScores.get(0));
                intAbilityScores.remove(0);     //takes away the num as it has been assigned

                abilityScores.setAbilityScore("Wisdom", intAbilityScores.get(0));               //pA2
                    abilityMods.setAbilityMod("Wisdom", intAbilityScores.get(0));
                intAbilityScores.remove(0);

            
                    //assigning random Ability Scores / Mods
                rA = 4; ///rA = "Remaining Abilitys" that have not been asigned a num yet (four have not been asigned)

                abilityScores.setAbilityScore("Constitution", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); /* choose a random element to 
                                                                                                                                             pick from in intAbilityScores */
                    abilityMods.setAbilityMod("Constitution", intAbilityScores.get(abilityNum));                                                                                                                             

                intAbilityScores.remove(abilityNum); rA--;            /* remove the intAbility score and lower amount of ability scores 
                                                                         that have not been asigned, rA = 2 */

                abilityScores.setAbilityScore("Strength", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));             //saving throw1
                abilityMods.setAbilityMod("Strength", intAbilityScores.get(abilityNum));
                intAbilityScores.remove(abilityNum); rA--;

                abilityScores.setAbilityScore("Charisma", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt()));
                abilityMods.setAbilityMod("Charisma", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;    //rA = 1

                abilityScores.setAbilityScore("Dexterity", intAbilityScores.get(abilityNum = numGen.ints(0, rA).findFirst().getAsInt())); 
                    abilityMods.setAbilityMod("Dexterity", intAbilityScores.get(abilityNum)); 
                intAbilityScores.remove(abilityNum); rA--;  //rA = 0

                //adding hp
                hp = 6 + abilityMods.getAbilityMod("Constitution");     //12 + Constitution mod


                //Adding Proficiencies
                    //adds weapon proficiencies
                weaponProfList = Arrays.asList("Daggers", "Darts", "Slings", "QuarterStaffs", "Light Crossbows");
                weaponProf.addAll(weaponProfList);

                    //Adding skill proficiencies
                skillList = Arrays.asList("Arcana", "History", "Insight", "Investigation", "Medicine", "Religion");
                potentialSkillsProfs = new ArrayList<String>();
                potentialSkillsProfs.addAll(skillList);      //puts into arrayList to grab from
                for (int i = 0; i < 2; i++)         //Chooses two skills from the list of skills to be proficient in
                {
                    int skillNum = numGen.ints(0, potentialSkillsProfs.size()).findFirst().getAsInt();
                    String newSkill = potentialSkillsProfs.get(skillNum);
                    skillProf.add(newSkill);
                    potentialSkillsProfs.remove(skillNum);
                }


                //Adding Equipment
                    //Adding Gold
                newDice = new diceRoller();
                goldNum = newDice.internalRoll("4d4");
                goldNum = (goldNum * 10);
                gold = String.valueOf(goldNum);
                equipment.add("Gold amount: " + gold);
                
                    //adding a quarterstaff or a dagger
                int wizardWeaponNum = numGen.ints(0, 2).findFirst().getAsInt();   //chooses which weapon
                if (wizardWeaponNum == 0)
                {
                    weapon wizardQuarterstaff = new weapon().getSpecificWeapon("Simple Melee", "Quarterstaff", 1);
                    weapons.add(wizardQuarterstaff);
                }
                else
                {
                    weapon wizardDagger = new weapon().getSpecificWeapon("Simple Melee", "Dagger", 1);
                    weapons.add(wizardDagger);
                }

                    //adding component pouch or arcane focus
                int wizardEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (wizardEquipmentNum == 0)
                {
                    equipment.add("Component Pouch");
                }
                else
                {
                    wizardEquipmentNum = numGen.ints(0, 5).findFirst().getAsInt();   //chooses which arcane focus
                    List<String> wizardArcaneFocus = Arrays.asList("Crystal", "Orb", "Rod", "Staff", "Wand");
                    equipment.add(wizardArcaneFocus.get(wizardEquipmentNum));
                }

                    //adding pack
                wizardEquipmentNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (wizardEquipmentNum == 0)
                {
                    equipment.add("Scholar's Pack");
                }
                else
                {
                    equipment.add("Explorer's Pack");
                }

                
                //SpellCasting
                spellSlots = 2;
                spellCastingAbility = "Intelligence";
                spellSaveDC = 8 + 2 + abilityMods.getAbilityMod("Intelligence");        // the +2 is the prof bonus at level 1
                spellAttackMod = 2 + abilityMods.getAbilityMod("Intelligence");         // +2 same thing
                ritualCasting = true;
                spellCastingFocus = true;

                    //adding spellbook
                equipment.add("Spellbook");

                spells wizardSpells = new spells();
                ArrayList<List<String>> wizardSpellList = wizardSpells.buildWizardSpellLists();

                List<String> charWizardCantrips = new ArrayList<String>();        //creates the empty arraylist that will hold the char's cantrips
                charSpells.add(0, charWizardCantrips);
                List<String> wizardLevelSpells = new ArrayList<String>();       //creates an empty wizard cantrip spell list to choose from
                wizardLevelSpells.addAll(wizardSpellList.get(0));

                    //adding Cantrips
                for (int i = 0; i < 3; i++)         //Chooses three cantrips from the list
                {
                    int spellNum = numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt();
                    String newCantrip = wizardLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);
                    wizardLevelSpells.remove(newCantrip);    //so the same one isnt picked again
                } 

                wizardLevelSpells.clear();            //clear to refill with 1st level spells
                wizardLevelSpells.addAll(wizardSpellList.get(1));

                List<String> char_wizard1stLevelSpells = new ArrayList<String>();      //new arraylist for 1st level spells
                charSpells.add(1, char_wizard1stLevelSpells);

                    //adding 1st Level Spells
                for (int j = 0; j < 6; j++)
                {
                    int spellNum = numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt();
                    String newSpell = wizardLevelSpells.get(spellNum);
                    charSpells.get(1).add(newSpell);
                    wizardLevelSpells.remove(newSpell);
                }


                //Adding features
                    //learning spells
                features learnSpells = new features();
                features.add(learnSpells);
                learnSpells.setName("Learn spells");
                learnSpells.setDesc("Each time you gain a wizard level, you can add two wizard spells of your choice to your spellbook. Each of these " + "\n" + 
                                "spells must be of a level for which you have spell slots, as shown on the Wizard table. On your adventures, you might " + "\n" + 
                                "find other spells that you can add to your spellbook (see the “Your Spellbook” sidebar).");

                    //adding Arcane Recovery
                features arcaneRecovery = new features();
                features.add(arcaneRecovery);
                arcaneRecovery.setName("Arcane Recovery");
                arcaneRecovery.setDesc("You have learned to regain some of your magical energy by studying your spellbook. Once per day when you " + "\n" + 
                                "finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level " + "\n" + 
                                "that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.");

                //Arcane Traditions (school of magic) is not obtained until level 2
                break;

            default:
                //should never hit default
                break;
        }
    }   //ya so this is like 2000 lines long, maybe split into different classes?







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
        racialTraits traits = new racialTraits();
        
        int SubRaceNum;

        //choose random race from x races (dwarf, elf, halfling, human, dragonborn, gnome, half-elf, half-orc, tiefling)
        List<String> raceArray = Arrays.asList("Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half-Elf", "Half-Orc", "Tiefling");
        ArrayList<String> races = new ArrayList<String>();
        races.addAll(raceArray);

        int raceNum = numGen.ints(0, 9).findFirst().getAsInt();
        this.race = races.get(raceNum);     //Records race selected

        this.race = "Dwarf";               //for testing

        //Set gender
        int genderNum = numGen.ints(0, 2).findFirst().getAsInt();
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
                this.name = dwarfNameList.get(numGen.ints(0, dwarfNameList.size()).findFirst().getAsInt());

                //Ability Score increase = con +2
                abilityScores.setAbilityScore("Constitution", (abilityScores.getAbilityScore("Constitution") + 2));
                abilityMods.setAbilityMod("Constitution", abilityScores.getAbilityScore("Constitution"));

                //set age   - dwarfs live up to 350 years, considered adult at age 50
                int age = numGen.ints(50, 300).findFirst().getAsInt();
                this.traits.setAge(age);

                //set Alignment     --really this is up to the player, but dwarfs are usually lawful
                List<String> lawfulAlignments = Arrays.asList("Lawful Good", "Lawful Neutral", "Lawful Evil");
                int alignmentNum = numGen.ints(0, 3).findFirst().getAsInt();
                this.traits.setAlignment(lawfulAlignments.get(alignmentNum));

                //set Size      --dwarfs are between 4 and 5 feet tall, weight around 150lbs, size is medium
                double heightDouble = numGen.doubles(4.0, 5.0).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = numGen.ints(125, 176).findFirst().getAsInt();
                this.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed     --does not decrease if wearing heavy armor
                this.traits.setSpeed(25);

                //Setting racial features
                    //darkvision
                features darkVision = new features();
                features.add(darkVision);
                darkVision.setName("Dark Vision");
                darkVision.setDesc("You have superior vision in dark and dim conditions. You can see in dim light within 60 feet of you " + "\n" + 
                                "as if it were bright light, and in darkness as if it were dim light.");

                    //Dwarven Resilience
                features dwarvenResilience = new features();
                features.add(dwarvenResilience);
                dwarvenResilience.setName("Dwarven Resilience");
                dwarvenResilience.setDesc("You have advantage on saving throws against poison, and you have resistance against poison damage");

                    //Dwarven Combat Training
                features dwarvenCombatTraining = new features();
                features.add(dwarvenCombatTraining);
                dwarvenCombatTraining.setName("Dwarven Combat Training");
                dwarvenCombatTraining.setDesc("You have proficiency with the battleaxe, handaxe, throwing hammer, and warhammer.");
                List<String> newDwarvenProf = Arrays.asList("BattleAxe", "Handaxe", "Throwing Hammer", "Warhammer");
                weaponProf.addAll(newDwarvenProf);

                    //StoneCunning  -- no not stone"cutting"
                features stonecunning = new features();
                features.add(stonecunning);
                stonecunning.setName("Stonecunning");
                stonecunning.setDesc("Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered " + "\n" + 
                                "proficient in the History skill and add double your proficiency bonus to the check.");

                //setting extra proficiencies 
                    //tool proficiency
                List<String> newDwarfToolProf = Arrays.asList("Smith’s tools", "Brewer’s supplies", "Mason’s tools");
                int toolNum = numGen.ints(0, 3).findFirst().getAsInt();
                toolProf.add(newDwarfToolProf.get(toolNum));

                    //languages
                charLanguages.add("Common");
                charLanguages.add("Dwarvish");


                SubRaceNum = numGen.ints(0, 2).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "Hill Dwarf";
                    this.setRace(subRace);

                    //Ability Score increase = wis +2
                    abilityScores.setAbilityScore("Wisdom", (abilityScores.getAbilityScore("Wisdom") + 2));
                    abilityMods.setAbilityMod("Wisdom", abilityScores.getAbilityScore("Wisdom"));

                    //Adding features
                        //Dwarven Toughness
                    features dwarvenToughness = new features();
                    features.add(dwarvenToughness);
                    dwarvenToughness.setName("Dwarven Toughness");
                    dwarvenToughness.setDesc("Your hit point maximum increaes by 1, and increases by 1 again every time you level");
                    this.hp = hp + 1;
                }
                else
                {
                    subRace = "Mountian Dwarf";
                    this.setRace(subRace);

                    //Ability Score increase = str +2
                    abilityScores.setAbilityScore("Strength", (abilityScores.getAbilityScore("Strength") + 2));
                    abilityMods.setAbilityMod("Strength", abilityScores.getAbilityScore("Strength"));

                    //Adding features
                        //Dwarven Armor Training = procifient with light and med armor
                    features dwarvenArmorTraining = new features();
                    features.add(dwarvenArmorTraining);
                    dwarvenArmorTraining.setName("Dwarven Armor Training");
                    dwarvenArmorTraining.setDesc("You gain proficiency in light and medium armor");
                    
                    if (armorProf.indexOf("Light Armor") == -1) //if they are not already proficient in light armor
                    {
                        armorProf.add("Light Armor");
                    }
                    if (armorProf.indexOf("Medium Armor") == -1)
                    {
                        armorProf.add("Medium Armor");
                    }
                }
                break;
            }
            case "Elf":
            {
                //Set Name
                ArrayList<String> elfNameList = new ArrayList<String>();
                elfNameList = traits.buildElfNameList(this.gender);
                this.name = elfNameList.get(numGen.ints(0, elfNameList.size()).findFirst().getAsInt());

                //Ability Score increase = dex +2
                abilityScores.setAbilityScore("Dexterity", (abilityScores.getAbilityScore("Dexterity") + 2));
                abilityMods.setAbilityMod("Dexterity", abilityScores.getAbilityScore("Dexterity"));

                //set age   - elves typically clain adulthood and name around the age of 100 and live to be 750 years old
                int age = numGen.ints(80, 775).findFirst().getAsInt();
                this.traits.setAge(age);

                //set Alignment     --really this is up to the player, but elves typically lean toward the gentler side of chaos, more often good than not, excluding drow
                List<String> gentleChaoticAlignments = Arrays.asList("Neutral Good", "Chaotic Good", "Neutral", "Chaotic Neutral");
                int alignmentNum = numGen.ints(0, 4).findFirst().getAsInt();
                this.traits.setAlignment(gentleChaoticAlignments.get(alignmentNum));

                //set Size      --elves range from 5 to 6 feet tall, with slender builds, size is medium
                double heightDouble = numGen.doubles(5.0, 6.0).findFirst().getAsDouble();
                DecimalFormat heightFormat = new DecimalFormat("#.0");
                String height = heightFormat.format(heightDouble);
                int weightNum = numGen.ints(90, 140).findFirst().getAsInt();    //--"Slender" guess
                this.traits.setSize(height + " Feet Tall, " + weightNum + "lbs,  Size = Medium");

                //set Speed
                this.traits.setSpeed(30);

                //Setting racial features
                    //darkvision
                features darkVision = new features();
                features.add(darkVision);
                darkVision.setName("Dark Vision");
                darkVision.setDesc("You have superior vision in dark and dim conditions. You can see in dim light within 60 feet of you " + "\n" + 
                                "as if it were bright light, and in darkness as if it were dim light.");
                
                    //Keen Senses
                features keenSenses = new features();
                features.add(keenSenses);
                keenSenses.setName("Keen Senses");
                keenSenses.setDesc("You have proficiency in the Perception Skill");
                skillProf.add("Perception");

                    //Fey Ancestry
                features feyAncestry = new features();
                features.add(feyAncestry);
                feyAncestry.setName("Fey Ancestry");
                feyAncestry.setDesc("You have advantage on saving throws against being charmed, and magic cannot put you to sleep");

                    //Trance
                features trance = new features();
                features.add(trance);
                trance.setName("Trance");
                trance.setDesc("Elves do not need to sleep. Instead they mediitate deeply, remaining semiconscious for four hours a day. After resting this way, " + "\n" + 
                                "you gain the same benefits that a human does from eight hours of sleep.");
                
                //Adding Languages
                charLanguages.add("Common");
                charLanguages.add("Elvish");
    
                SubRaceNum = numGen.ints(0, 3).findFirst().getAsInt();
                if (SubRaceNum == 0)
                {
                    subRace = "High Elf";

                    //Ability Score increase = int +2
                    abilityScores.setAbilityScore("Intelligence", (abilityScores.getAbilityScore("Intelligence") + 2));
                    abilityMods.setAbilityMod("Intelligence", abilityScores.getAbilityScore("Intelligence"));

                    //"Elf Weapon Training" = prof with longsword, shortsword, shortbow, and longbow
                    features elfWeaponTraining = new features();
                    features.add(elfWeaponTraining);
                    elfWeaponTraining.setName("Elf Weapon Training");
                    elfWeaponTraining.setDesc("You gain proficiency with the longsword, shortsword, shortbow, and longbow");
                    List<String> newElfWeaponProf = Arrays.asList("Longsword", "Shortsword", "Shortbow", "Longbow");
                    weaponProf.addAll(newElfWeaponProf);

                    //extra cantrip from wizard spell list
                    spells wizardSpells = new spells();
                    ArrayList<List<String>> wizardSpellList = wizardSpells.buildWizardSpellLists();     //gets wizard spell lists
                    List<String> wizardLevelSpells = new ArrayList<String>();       //creates an empty wizard cantrip spell list to choose from
                    wizardLevelSpells.addAll(wizardSpellList.get(0));

                    int spellNum = numGen.ints(0, wizardLevelSpells.size()).findFirst().getAsInt(); //pick random spell 
                    String newCantrip = wizardLevelSpells.get(spellNum);
                    charSpells.get(0).add(newCantrip);

                    //extra Lanuage
                    //i dunna not feeling it toady

                }
                else if (SubRaceNum == 1)
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
            }  
            case "Halfling":
            {
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
                break;
            }
            case "Human":                                                   //Humans do not have subRaces, have ethnic groups instead - no extra traits
            {
                List<String> ethnicArray = Arrays.asList("Calishite", "Chondathan", "Damaran", "Illuskan", "Mulan", "Rashemi", "Shou", "Tethyrian", "Turami");
                List<String> ethnicGroup = new ArrayList<String>();
                ethnicGroup.addAll(ethnicArray);

                SubRaceNum = numGen.ints(0, 9).findFirst().getAsInt();
                subRace = ethnicGroup.get(SubRaceNum);                           
                break;
            }   
            case "Dragonborn":                                              //Do not have subRaces, instead have Draconic Ancestry, probably need new method
            {
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
            }
            case "Gnome":
            {
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
            }
                
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
    
    public abilityScores()
    {
        this.strength = 0;
        this.dexterity = 0;
        this.constitution = 0;
        this.intelligence = 0;
        this.wisdom = 0;
        this.charisma = 0;
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
}







class weapon
{
    String name;
    String damage;
    int amount;
    Random numGen = new Random();

    public weapon()
    {
        /* 
        this.name = "";
        this.damage = "";   
        */  
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
                    newWeapon.amount = inAmount;
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
                    newWeapon.amount = inAmount;
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
                    newWeapon.amount = inAmount;
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
                    newWeapon.amount = inAmount;
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
                weapon weaponItr = ammunitionList.get(i);
                if (weaponItr.getWeaponName().equals(inName))
                {
                    newWeapon = weaponItr;
                    this.amount = inAmount;
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
        return newWeapon;
    }

    public weapon getRanged()
    {
        ArrayList<weapon> rangedList = buildSimpleRangedList();

        int simpRangedWeaponNum = numGen.ints(0, 4).findFirst().getAsInt();
        weapon newWeapon = rangedList.get(simpRangedWeaponNum);                     //choose random, then return
        return newWeapon;
    }

    public weapon getMartialMelee()
    {
        ArrayList<weapon> martialMeleeList = buildMartialMeleeList();

        int weaponNum = numGen.ints(0, 18).findFirst().getAsInt();
        weapon newWeapon = martialMeleeList.get(weaponNum);                     //choose random, then return
        return newWeapon;
    }

    public weapon getMartialRanged()
    {
        ArrayList<weapon> martialRangedList = buildMartialMeleeList();

        int weaponNum = numGen.ints(0, 5).findFirst().getAsInt();
        weapon newWeapon = martialRangedList.get(weaponNum);                     //choose random, then return
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









class langauges
{
    String lanName;
    Random numGen = new Random();

    public langauges()
    {
        this.lanName = "";
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
}





class racialTraits
{
    int age;
    int speed;
    String alignment;
    String size;

    Random numGen = new Random();

    public racialTraits()
    {
        this.age = 0;
        this.speed = 0;
        this.alignment = "";
        this.size = "";
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

 /*    public String getRandomName(String inRace, String inGender)
    {
        Switch (inRace)
        {

        }
        return "";
    } */

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

    public ArrayList<String> dragonBornNameList(String inGender)
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

    public ArrayList<String> gnomeNameList(String inGender)
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
                inChar.sortAbilityScores(abilityScore);
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
                inChar.sortAbilityScores(abilityScore);
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
                    printAbilityScores();
                    break;
                }
                case("3"):
                {
                    //go to character creater
                    correctSelect = true;
                    System.out.println("");
                    characterCreater newChar = new characterCreater();
                    newChar.createCharacter(newChar);
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
