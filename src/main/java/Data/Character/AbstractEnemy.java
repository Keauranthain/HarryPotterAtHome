package Data.Character;

import Data.Admin.Game;
import Data.Enum.House;
import Data.Spell;
import Data.Item.Potion;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class AbstractEnemy extends Character {
    private String name;
    private int fullLife;
    public int magic;
    public int resist;
    private Spell spell;

    public Spell witness;
    private int xp;
    private int magicBonus;
    private int resistBonus;
    private int accurencyBonus;
    private boolean ok = false;
    public abstract int critical(int damage,int idspell);

    public abstract void reward(Game admin);
    public abstract void lose(Game admin);

    public void startfight(String name,int fullLife,int magic,int resist,int spell,Game admin){
        int life = admin.var.wizard.getFullLife();
        int oppLife = fullLife;
        admin.var.forbiddenspelluse = false;
        int turn = 0;
        setHouseBoost(admin.var.wizard.getHouse());
        while (life>0 && oppLife>0 && turnlimitation(admin,turn)){
            turn ++;
            printfight(life,oppLife,name,fullLife,admin);
            ok = false;
            while (!ok) {
                if (admin.playerchoiceboolean("Que dois-je faire ? (0=sort,1=potion) : ")) {
                    oppLife = playerAttack(oppLife, resist, magic,admin);
                } else {
                    life = playerPotion(life,admin.var.wizard.getFullLife(),admin);
                }
            }
            admin.enter();
            if (oppLife >0){
                admin.hudgewhite(5);
                printfight(life,oppLife,name,fullLife,admin);
                life = oppAttack(life,admin.findSpellById(spell),resist,magic,admin);
            }
            admin.enter();
            admin.hudgewhite(5);
        }
        if(oppLife<=0){
            printfight(life,0,name,fullLife,admin);
            reward(admin);
        }
        else if (life <=0){
            lose(admin);
        }
    }

    private boolean turnlimitation(Game admin,int turn){
        boolean continu = true;
        if (turn>0 && turn == admin.var.fightturnlimite) {
            continu = false;
            admin.var.fightturnlimite=0;
        }
        return continu;
    }
    private int playerfightchoice(int nchoice, String sentence,Game admin){
        String choice;
        char backtest ='n';
        int value = -1;
        while ((value<0 || value>=nchoice)&&backtest!='e'){
            System.out.print(sentence);
            choice = admin.scanner.nextLine();
            try {
                backtest = choice.charAt(0);
            }
            catch (IndexOutOfBoundsException e) {
            }
            try{
                value = Integer.parseInt(choice);
            }
            catch (NumberFormatException e) {

            }
        }
        if (backtest == 'e'){
            value=-1;
        }
        return value;
    }
    public int playerAttack(int oppLife,int resist,int magic,Game admin){
        ok=true;
        int newlife = oppLife;
        Wizard wizard = admin.var.wizard;
        if (admin.var.wizard.getKnowspell().size()==0){
            admin.var.wizard.Knowspell.add(admin.findSpellById(1));
        }
        for (int i=0 ; i<wizard.getKnowspell().size();i++){
            System.out.print(i+": "+wizard.getKnowspell().get(i).getName()+"   (Puissance : ");
            System.out.print(wizard.getKnowspell().get(i).getBase_damage()+", Précision : ");
            System.out.println(wizard.getKnowspell().get(i).getAccuracy()+"%)");
        }
        System.out.println("e: retour");
        int value = playerfightchoice(wizard.getKnowspell().size(),"Quel sort est le plus approprié... ",admin);
        if (value == -1){
            ok = false;
        }
        else {
            Spell spell = wizard.getKnowspell().get(value);
            newlife = newlife - spellDamage(true, spell, resist, magic,admin);
            if (spell.getIdSpell()==11 ||spell.getIdSpell()==12||spell.getIdSpell()==13){
                admin.var.forbiddenspelluse = true;
            }
        }
        return newlife;
    }
    public int playerPotion(int life,int fullLife,Game admin){
        ok=true;
        int newlife = life;
        Wizard wizard = admin.var.wizard;
        if (admin.var.wizard.getPotionList().size()==0){
            System.out.println("Je n'ai point de potion");
            ok=false;
        }
        else {
            admin.potionSort();
            for (int i = 0; i < wizard.getPotionList().size(); i++) {
                List<Integer> potion =wizard.getPotionList().get(i);
                System.out.println(i + ": " + admin.findPotionById(potion.get(0)).getName()+" ("+potion.get(1)+")");
            }
            System.out.println("e: retour");
            int value = playerfightchoice(wizard.getPotionList().size(),"Quel potion est la plus approprié... ",admin);
            if (value == -1){
                ok = false;
            }
            else {
                Potion potion = admin.findPotionById(wizard.getPotionList().get(value).get(0));
                newlife = potionEffect(potion,life,fullLife,admin);
                wizard.potionList.get(value).set(1,wizard.potionList.get(value).get(1)-1);
                if (wizard.potionList.get(value).get(1)==0){
                    wizard.potionList.remove(value);
                }
            }

        }
        return newlife;
    }

    public int potionEffect(Potion potion,int life,int fullLife,Game admin){
        int newlife = 0;
        switch (potion.getType()) {
            case "heal":
                int gain = potion.heal(admin);
                newlife = life + gain;
                if (newlife>fullLife){
                    newlife=fullLife;
                    gain = newlife-life;
                }
                System.out.println("Ahhh, ça fait du bien, j'ai au moins dû récupérer "+gain+" point de vie...");
                break;
            case "accurency":
                accurencyBonus += potion.accurency(admin);
                newlife = life;
                System.out.println("A partir de maintenant, je ne louperai plus ma cible");
                break;
            case "magic":
                magicBonus += potion.magic(admin);
                newlife = life;
                System.out.println("Tremble face à la nouvelle puissance de ma magie");
                break;
            case "resist":
                resistBonus +=potion.resist(admin);
                newlife = life;
                System.out.println("Tu ne m'atteindra plus");
                break;
        }
        return newlife;
    }

    public int oppAttack(int life,Spell spell,int resist,int magic,Game admin){
        int newlife = spellDamage(false,spell,resist,magic,admin);
        if (newlife<0){
            newlife = 0;
        }
        newlife = life-newlife;
        return newlife;
    }

    public int spellDamage(boolean mcattack,Spell spell,int resist, int magic,Game admin){
        int damage = 0;
        int accurency = admin.random.nextInt(100) + 1+accurencyBonus;
        if (mcattack && accurency<=spell.getAccuracy()){
            damage = (admin.var.wizard.getMagic()+magicBonus)*spell.getBase_damage()/resist;
            damage = critical(damage,spell.getIdSpell());
            System.out.println("Cette attaque à bien dû lui faire perdre "+(damage)+" point de vie");
        }
        else if(!mcattack && accurency<=spell.getAccuracy()){
            damage = magic*spell.getBase_damage()/(admin.var.wizard.getResist()+resistBonus);
            System.out.println("Ouch, son "+spell.getName()+"à bien du me faire perdre "+(damage)+" point de vie");
        }
        else  if (mcattack){
            System.out.println("Raté, il faut que je me concentre");
        }
        else {
            System.out.print("Ouf...");
            admin.delay(500);
            System.out.println(" son "+spell.getName()+" n'est pas passé loin...");
        }
        return damage;
    }
    public void heal(){

    }

    public void printfight(int life, int opplife, String name,int fullLife,Game admin){
        System.out.print(admin.var.wizard.getName()+" :  ");
        System.out.print(life+" / "+admin.var.wizard.getFullLife()+"          ||          ");
        System.out.print(name+" :  ");
        System.out.println(opplife+" / "+fullLife);
        System.out.println("");
        System.out.println("");
    }

    public void setHouseBoost(House house){
        if (house.name().equals("RAVENCLAW")) {
            magicBonus=5;
        } else if (house.name().equals("SLYTHERIN")) {
            magicBonus=10;
        } else if (house.name().equals("GRYFFONDOR")) {
            resistBonus = 10;
        }
    }

}
