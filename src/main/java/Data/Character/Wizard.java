package Data.Character;

import Data.Enum.House;
import Data.Item.Potion;
import Data.Item.Wand;
import Data.Spell;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter

public class Wizard extends Character implements Serializable {
    //Personal data
    public String firstname;
    public String name;
    public int years;
    public String pet;
    private String patronus;
    private House house;
    public Wand wand;
    //Caracteristic data
    public int potionrevision;
    public int level;
    private int xp;
    //fight data
    private int fullLife;
    private int magic;
    private int resist;

    public List<Spell> Knowspell= new ArrayList<>();
    public List<List<Integer>> potionList= new ArrayList<>();

   public Wizard (){
       this.level =1;
       this.fullLife =50;
       this.magic = 10;
       this.resist = 10;
       this.house = House.NONE;
   }
}

