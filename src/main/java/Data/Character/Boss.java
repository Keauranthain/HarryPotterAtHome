package Data.Character;

import Data.Admin.Game;
import Data.Spell;
import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;

@Getter @Setter
public class Boss extends AbstractEnemy {
    private String name;
    private int fullLife;
    public int magic;
    public int resist;
    public int spell ;
    public int witness;
    private int xp;

    public Boss(String name, int fullLife, int magic, int resist,int spellId,int witnessId,int xp) {
        this.name = new String(name);
        this.fullLife = fullLife;
        this.magic = magic;
        this.resist = resist;
        this.spell = spellId;
        this.witness = witnessId;
        this.xp = xp;
    }

    public void fight (Game admin){
        startfight(name,fullLife,magic,resist,spell,admin);
    }

    public int critical(int damage,int idspell){
        int citicaldamage = damage;
        if (idspell ==witness){
            citicaldamage = 3*damage;
        }
        return citicaldamage;
    }
    public void reward(Game admin){
        admin.enter();
        System.out.println("Tu ne faisais pas le poid...");
        admin.enter();
        admin.XP(xp);
        System.out.println("Que ce passe t'il ?");
        admin.enter();
        System.out.println("Je sens la magie affluer en moi...");
        admin.enter();
        char character = 'n';
        boolean choice = false;
        while (character != '0' && character != '1') {
            System.out.print("Je le sens, (0=mon endurance,1=ma magie) : ");
            String input = admin.scanner.nextLine();
            System.out.println("est plus forte !");
            try {
                character = input.charAt(0);
            }
            catch (IndexOutOfBoundsException e) {
            }
        }
        if (character == '0'){
            admin.var.wizard.setFullLife(admin.var.wizard.getFullLife()+10);
        }
        else {
            admin.var.wizard.setMagic(admin.var.wizard.getMagic()+10);
        }
    }
}
