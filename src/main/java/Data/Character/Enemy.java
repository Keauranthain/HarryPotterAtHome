package Data.Character;

import Data.Admin.Game;
import Data.Spell;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
public class Enemy extends AbstractEnemy implements Serializable {
    int id;
    private String name;
    private int fullLife;
    public int magic;
    public int resist;
    private int spell;

    public int witness;
    private int xp;
    public Enemy(int enemyId, String Name, int fullLife,int magic,int resist,int spellId,int xp) {
        this.id = enemyId;
        this.name = new String(Name);
        this.fullLife = fullLife;
        this.magic = magic;
        this.resist = resist;
        this.spell = spellId;
        this.xp=xp;
    }
    public int critical(int damage,int idspell){
        return damage;
    }
    public void fight(Game admin) {
        //System.out.println(name);
        admin.lose = false;
        startfight(name,fullLife,magic,resist,spell,admin);
    }
    public void reward(Game admin){
        admin.XP(xp);
        if (admin.var.forbiddenspelluse){
            admin.var.azkaban =true;
        }
    }
    public void lose(Game admin){
        System.out.println("j'ai...'");
        admin.delay(1500);
        System.out.println("perdu...");
        admin.delay(1500);
        System.out.println("...");
        admin.enter();
        admin.lose = true;
        if (admin.var.forbiddenspelluse){
            admin.var.azkaban =true;
        }
    }
}
