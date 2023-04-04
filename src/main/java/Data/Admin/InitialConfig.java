package Data.Admin;

import Data.Character.Enemy;
import Data.Item.Potion;
import Data.Room.GameObject;
import Data.Room.Room;
import Data.Spell;

import java.util.ArrayList;

public class InitialConfig {
    public void Creator(Game admin){
        GameObjectCreator(admin);
        RoomCreator(admin);
        SpellCreator(admin);
        potionCreator(admin);
        EnemyCreator(admin);
    }
    public void addObject(int id,String marker,boolean obstacle,Game admin){
        admin.objetList.add(new GameObject(id,marker,obstacle));
    }
    public void addScriptObject(int id,String marker,boolean obstacle,int effect, Game admin){
        admin.objetList.add(new GameObject(id,marker,obstacle,effect));
    }
    public void GameObjectCreator(Game admin){
        addObject(0,"X",true,admin);//Void
        addObject(1,"H",false,admin);//Hero
        admin.objetList.get(1).setDirection('D');
        addObject(2,"W",true,admin);//Wall
        addObject(3,"W",true,admin);//Water
        addObject(4,"T",true,admin);//Tree
        addObject(5,"B",true,admin);//Bush
        addObject(6,"T",true,admin);//Table
        addScriptObject(7,"P",true,1,admin);//Patronus
        addScriptObject(8,"S",true, 2,admin);//Snake
        addScriptObject(9,"L",true, 3,admin);//Lion
        addScriptObject(10,"E",true, 4,admin);//Eagle
        addScriptObject(11,"B",true, 5,admin);//Badger
        addScriptObject(12,"D",true,6,admin);//Closet Door to ground
        addScriptObject(13,"D",true,7,admin);//ground door to garden
        addScriptObject(14,"S",true,8,admin);//ground door to firststage
        addScriptObject(15,"D",true,9,admin);//ground door to closet
        addScriptObject(16,"P",true,10,admin);//Petunia
        addScriptObject(17,"V",true,11,admin);//Vernon
        addScriptObject(18,"M",true,12,admin);//Mailbox
        addScriptObject(19,"D",true,13,admin);//Close door
        addScriptObject(20,"D",true,14,admin);//Garden door to ground
        addScriptObject(21,"S",true,15,admin);//first door to ground
        addScriptObject(22,"D",true,16,admin);//first door to bedroom
        addScriptObject(23,"D",true,17,admin);//dudley room
        addScriptObject(24,"B",false,18,admin);//Bed
        addScriptObject(25,"D",true,19,admin);//bedroom door to first
        addScriptObject(26,"H",true,20,admin);//Hagrid
        addScriptObject(27,"D",true,21,admin);//Cloths
        addScriptObject(28,"D",true,22,admin);//Olivender
        addScriptObject(29,"D",true,23,admin);//bank
        addScriptObject(30,"D",true,24,admin);//Book
        addScriptObject(31,"D",true,25,admin);//Pets
        addObject(32,"1",false,admin);
        addObject(33,"2",false,admin);
        addObject(34,"3",false,admin);
        addObject(35,"4",false,admin);
        addObject(36,"5",false,admin);
        addObject(37,"6",false,admin);
        addObject(38,"7",false,admin);
        addObject(39,"8",false,admin);
        addObject(40,"9",false,admin);
        addScriptObject(41,"W",true,26,admin);//9 3/4
        addObject(42,"A",false,admin);
        addScriptObject(43,"S",false,27,admin);//Sorting Hat
        addScriptObject(44,"N",true,28,admin);//New student
        addScriptObject(45,"S",true,29,admin);//GH stair to WT
        addScriptObject(46,"S",true,30,admin);//GH stair to ET
        addScriptObject(47,"D",true,31,admin);//GH door to Little room
        addScriptObject(48,"S",true,32,admin);//GH stair to Underground
        addScriptObject(49,"D",true,33,admin);//GH door to Hogsmeade/forbidenForest
        addScriptObject(50,"C",true,34,admin);//Spell Class
        addScriptObject(51,"D",true,35,admin);//Gryffondor door
        addScriptObject(52,"C",true,36,admin);//Astronomy class
        addScriptObject(53,"S",true,37,admin);//WT door to GH
        addScriptObject(54,"S",true,38,admin);//Mme.Sinistra Astronomy
        addScriptObject(55,"S",true,39,admin);//Astronomy class to WT
        addScriptObject(56,"F",true,40,admin);//M.Flitwick Spell
        addScriptObject(57,"D",true,41,admin);//Spell class to WT
        addScriptObject(58,"D",true,42,admin);//Bedroom door
        addScriptObject(59,"C",true,43,admin);//DFM class
        addScriptObject(60,"D",true,44,admin);//Ravenclaw door
        addScriptObject(62,"S",true,46,admin);//ET door to GH
        addScriptObject(63,"Q",true,47,admin);//DFM teatcher
        addScriptObject(64,"D",true,48,admin);//DFM class to ET
        addScriptObject(65,"S",true,46,admin);//underground stair to GH
        addScriptObject(66,"F",true,49,admin);//underground to Secret chamber
        addScriptObject(67,"D",true,50,admin);//Slitherin door
        addScriptObject(68,"C",true,51,admin);//Potion class
        addScriptObject(69,"D",true,52,admin);//hufflepuff door
        addScriptObject(70,"R",true,53,admin);//Potion teachers
        addScriptObject(71,"D",true,54,admin);//Potion class to underground
        addScriptObject(72,"T",true,55,admin);//Troll
        addScriptObject(73,"Q",true,56,admin);//Quirell
        addScriptObject(74,"T",true,57,admin);//quirellroom to GH
        addScriptObject(75,"B",true,58,admin);//Basilic
        addScriptObject(76,"L",true,59,admin);//Secret chamber to underground
        addScriptObject(77,"D",true,60,admin);//Detraqueur
        addScriptObject(78,"A",true,61,admin);//arena
        addScriptObject(79,"D",true,62,admin);//forbidenforest to GH
        addScriptObject(80,"C",true,63,admin);//cave
        addScriptObject(81,"C",true,64,admin);//Cup
        addScriptObject(82,"O",true,65,admin);//Opponent
        addScriptObject(83,"D",true,66,admin);//Arena door to ET
        addScriptObject(84,"V",true,67,admin);//FirstVoldemort
    }

    public void RoomCreator(Game admin) {
        RoomMaker RM = new RoomMaker();
        //dream
        RM.dream1(admin);//1
        RM.dream2(admin);//2
        RM.closet(admin);//3
        //House
        RM.groundHouse(admin);//4
        RM.gardenHouse(admin);//5
        RM.firstHouse(admin);//6
        RM.bedroom(admin);//7
        //London
        RM.diagonallay(admin);//8
        RM.kingcross(admin);//9
        //Hogward
        RM.greatHall(admin);//10
        RM.WestTower(admin);//11
        RM.astronomyclass(admin);//12
        RM.SpellClass(admin);//13
        RM.bedroomHW(admin);//14
        RM.EstTower(admin);//15
        RM.DFMClass(admin);//16
        RM.underground(admin);//17
        RM.PotionClass(admin);//18
        RM.quirellroom(admin);//19
        RM.secretchamber(admin);//20
        RM.forbiddenforest(admin);//21
        RM.maze(admin);//22
        RM.arena(admin);//23
        RM.graveyard(admin);//24
        RM.azkaban(admin);//25
    }
    public void EnemyCreator(Game admin){
        admin.enemyList.add(new Enemy(1,"Dudley",20,5,5,1,10));
    }

    public  void SpellCreator(Game admin) {
        admin.spellList.add(new Spell(1,"Coup de poing",5,90));//0
        admin.spellList.add(new Spell(2,"Wigardium Leviosa",25,80));//1
        admin.spellList.add(new Spell(3,"Accio",30,100));//2
        admin.spellList.add(new Spell(4,"Expeliarmus",40,75));//2
        admin.spellList.add(new Spell(5,"Petrificus Totalus",80,50));//3
        admin.spellList.add(new Spell(6,"Bombarda",200,25));//4
        admin.spellList.add(new Spell(7,"Deletricus",75,80));//5
        admin.spellList.add(new Spell(8,"Septumsempra",100,60));//5
        admin.spellList.add(new Spell(9,"Reducto",100,70));//6
        admin.spellList.add(new Spell(10,"Bombarda Maxima",320,25));//7
        admin.spellList.add(new Spell(11,"Imperio",1000,20));//FS
        admin.spellList.add(new Spell(12,"Endoloris",100,100));//FS
        admin.spellList.add(new Spell(13,"Avada Kedavra",10000,40));//FS
        admin.spellList.add(new Spell(14,"Coup de massue",45,15));//Boss
        admin.spellList.add(new Spell(15,"Cros du Basilique",45,15));//Boss
        admin.spellList.add(new Spell(16,"Epée de Griffondor",1000,100));//Objet
        admin.spellList.add(new Spell(17,"Expecto Patronum",50,80));//3
        admin.spellList.add(new Spell(18,"Bhou hou",40,100));//Boss
    }

    public  void potionCreator(Game admin) {
        potionAutoCreator(1,"Potion de soin","heal",admin);
        potionAutoCreator(8,"Potion de précision","accurency",admin);
        potionAutoCreator(15,"Potion de magie","magic",admin);
        potionAutoCreator(22,"Potion de résistance","resist",admin);
    }
    public void potionAutoCreator(int id,String name,String type,Game admin){
        String realname=new String();
        for(int i = 0;i<6;i++){
            switch (i){
                case 0:
                    realname = name+" médiocre";
                    break;
                case 1:
                    realname = name+" mauvaise";
                    break;
                case 2:
                    realname = name+" moyenne";
                    break;
                case 3:
                    realname = name;
                    break;
                case 4:
                    realname = name+" bonne";
                    break;
                case 5:
                    realname = name+" excelente";
                    break;
                case 6:
                    realname = name+" légeandaire";
                    break;
            }
            admin.potionList.add(new Potion(id+i,realname,type,i));
        }
    }
}
