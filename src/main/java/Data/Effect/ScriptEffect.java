package Data.Effect;

import Data.Admin.Game;
import Data.Character.Boss;
import Data.Character.Wizard;
import Data.Enum.House;
import Data.Item.Wand;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScriptEffect {
    public void script (int id,Game admin) {
        Wizard wizard =admin.var.wizard;
        String myName = admin.var.wizard.getFirstname();
        switch (id){
            case 1://
                admin.var.patronumSet = true;
                break;
            case 2://Serpent
                System.out.println("Ce serpent a une tête fourbe...");
                admin.enter();
                System.out.println("Aïe ! il m'a mordu");
                admin.enter();
                System.out.println("j'ai la tête qui tourne, je crois que je vais m'évanouir...");
                admin.enter();
                admin.var.wizard.setHouse(House.SLYTHERIN);
                break;
            case 3://Lion
                System.out.println("Ce Lion m'a l'air bien téméraire parmis ces flames...");
                admin.enter();
                System.out.println("Non je dois me trompé, mais j'ai quand même bien l'impression qu'il veut que je lui monte dessus");
                admin.enter();
                System.out.println("Perdu pour perdu...aller je tentes");
                admin.enter();
                admin.var.wizard.setHouse(House.GRYFFONDOR);
                break;
            case 4://Aigle
                System.out.println("Cet aigles a l'air très intéligent, commment il s'est retrouvé là ?");
                admin.enter();
                System.out.println("Aïe ! il m'a attrappé le bras");
                admin.enter();
                System.out.println("Incroyable je vol !");
                admin.enter();
                admin.var.wizard.setHouse(House.RAVENCLAW);
                break;
            case 5://Blairaux
                System.out.println("Ce blaireaux a l'air de ne vouloir laisser personne ici");
                admin.enter();
                System.out.println("Peut être qu'il connait un moyen de sortir d'ici...");
                admin.enter();
                System.out.println("Perdu pour perdu...aller je tentes");
                admin.enter();
                admin.var.wizard.setHouse(House.HUFFLEPUFF);
                break;
            case 6:
                admin.door.change(2,3,4,admin);
                if (!admin.var.StartMail){
                    System.out.println("Vernon : "+myName+"! Va chercher le courier !");
                    admin.enter();
                    System.out.println(myName+" : Pff... devoir sortir dès le matin...");
                    admin.enter();
                    admin.var.StartMail = true;
                }
                break;
            case 7:
                admin.door.change(7,2,5,admin);
                break;
            case 8:
                admin.door.change(1,1,6,admin);
                break;
            case 9:
                admin.door.change(1,1,3,admin);
                break;
            case 10:
                System.out.println("Pétunia : Je t'ai déjà dis que tu es un dechet ?");
                admin.var.dechet++;
                admin.enter();
                System.out.println(myName+" : oui, "+ admin.var.dechet +" fois");
                admin.enter();
                System.out.println("Pétunia : Une fois de plus n'est jamais trop");
                admin.enter();
                break;
            case 11://Vernon
                Vernon(admin);
                break;
            case 12:
                if (!admin.var.mailtoVernon && !admin.var.bank) {
                    admin.var.mailtoVernon = true;
                    System.out.println("Il y a pas mal de courrier, je devrais me dépécher de le ramener à Vernon");
                    admin.enter();
                }
                break;
            case 13:
                System.out.println("Cette porte est fermée...");
                admin.enter();
                break;
            case 14:

                admin.door.change(7,1,4,admin);
                break;
            case 15:
                admin.door.change(5,1,4,admin);
                break;
            case 16:
                admin.door.change(1,3,7,admin);
                break;
            case 17:
                System.out.println("Dudley : C'est ma chambre, ni pense même pas");
                admin.enter();
                break;
            case 18://Bed
                Bed(admin);
                break;
            case 19:
                if (admin.var.sleep){
                    System.out.println("Vernon : Privé de sortie de ta chambre pour la journée !");
                    admin.enter();
                }
                else {
                    admin.door.change(3,1,6,admin);
                }
                break;
            case 20:
                Hagrid(admin);
                break;
            case 21:
                guipure(admin,myName);
                break;
            case 22:
                olivander(admin);
                break;
            case 23:
                System.out.println(myName+": J'aimerai retirer mon argent");
                admin.enter();
                if(!admin.var.bank) {
                    System.out.println("Goblin : Vous avez la clef ?");
                    admin.enter();
                    System.out.println(myName+": Oui tenez");
                    admin.var.bank = true;
                    System.out.println("Goblin : Je vais vous chercher votre or...");
                    admin.blackscreen(2000);
                    System.out.println("Goblin : Voilà");
                }
                else {
                    System.out.println("Goblin : Suite au dernière consigne de sécurité, un accès par coffre par jour");
                }
                break;
            case 24:
                buybook(admin);
                break;
            case 25://to do
                break;
            case 26:
                System.out.println("Ahah, j'ai trouvé la voix 9 3/4");
                admin.enter();
                admin.blackscreen(10);
                System.out.println("Je suis dans le Poudlard express, Poudlard me voilà !");
                admin.blackscreen(2000);
                admin.door.change(9,23,10,admin);
                System.out.println("Dumbuldor : Nouveau éleve, vennez mettre le choipeaux pour connaitre votre maison");
                admin.enter();
                break;
            case 27:
                SortingHat(admin);
                break;
            case 28:
                System.out.println("Nouvel élève : J'ai hâte de connaitre ma maison !");
                break;
            case 29:
                admin.door.change(4,4,11,admin);
                break;
            case 30:
                admin.door.change(4,4,15,admin);
                break;
            case 31:
                if (admin.var.Alohomora) {
                    admin.door.change(5, 10, 19,admin);
                } else {
                    System.out.println("La porte est fermée...il doit bien il y avoir un moyen de l'ouvrir !");
                }
                break;
            case 32:
                admin.door.change(10,1,17,admin);
                break;
            case 33:
                admin.door.change(30,77,21,admin);
                break;
            case 34:
                admin.door.change(5,9,13,admin);
                break;
            case 35:
                if (admin.var.wizard.getHouse().name().equals("GRYFFONDOR")) {
                    admin.door.change(2,3,14,admin);
                } else {
                    System.out.println("Seul les griffondor ont le droit d'entré");
                }
                break;
            case 36:
                admin.door.change(7,6,12,admin);
                break;
            case 37:
                admin.door.change(4,26,10,admin);
                break;
            case 38:
                lesson("Mme.Sinistra",4,admin);
                break;
            case 39:
                admin.door.change(2,4,11,admin);
                break;
            case 40://spell Teatcher
                lesson("M.Flitwick",1,admin);
                break;
            case 41:
                admin.door.change(1,3,11,admin);
                break;
            case 42:
                House house = admin.var.wizard.getHouse();
                if (house.name().equals("RAVENCLAW")) {
                    admin.door.change(5,3,15,admin);
                } else if (house.name().equals("SLYTHERIN")) {
                    admin.door.change(6,5,17,admin);
                } else if (house.name().equals("GRYFFONDOR")) {
                    admin.door.change(5,3,11,admin);
                } else {
                    admin.door.change(14,5,17,admin);
                }
                break;
            case 43:
                admin.door.change(5,9,16,admin);
                break;
            case 44:
                if (admin.var.wizard.getHouse().name().equals("RAVENCLAW")) {
                    admin.door.change(2,3,14,admin);
                } else {
                    System.out.println("Seul les serdaigles ont le droit d'entrer");
                }
                break;
            case 46:
                admin.door.change(14,26,10,admin);
                break;
            case 47:
                dfmlesson(admin);
                break;
            case 48:
                admin.door.change(1,3,15,admin);
                break;
            case 49:
                if (admin.var.secretchamber){
                    admin.door.change(6,16,20,admin);
                }
                break;
            case 50:
                if (admin.var.wizard.getHouse().name().equals("SLYTHERIN")) {
                    admin.door.change(2,3,14,admin);
                } else {
                    System.out.println("Seul les serpentards ont le droit d'entrer");
                }
                break;
            case 51:
                admin.door.change(5,9,18,admin);
                break;
            case 52:
                if (admin.var.wizard.getHouse().name().equals("HUFFLEPUFF")) {
                    admin.door.change(2,3,14,admin);
                } else {
                    System.out.println("Seul les poufsouffles ont le droit d'entrer");
                }
                break;
            case 53:
                //Potion teachers
                if(admin.var.wizard.years<6){
                    lesson("M.Rogue",2,admin);
                }else{
                    lesson("M.Slughorn",2,admin);
                }
                break;
            case 54:
                admin.door.change(10,5,17,admin);
                break;
            case 55:
                Boss troll = new Boss("Troll avec une masse lourde qu'il tiens mal et qui pourrait l'assomer en lui tombant sur la tête",25,50,50,14,2,100);
                troll.fight(admin);
                admin.var.act = false;
                break;
            case 56:
                System.out.println("M.Quirell : Ahah quand j'aurais mis la main sur la pierre philosophal, Voldemord renaitra !");
                Boss quirell = new Boss("Quirell qui se prend pour Janus mais avec un seul nez",100,75,5,1,1,25);
                quirell.fight(admin);
                admin.var.quirelldead = true;
                admin.findRoomById(19).objects.remove(0);
                admin.findRoomById(16).objects.remove(0);
                break;
            case 57:
                admin.door.change(3,26,10,admin);
                break;
            case 58:
                basilic(admin);
                break;
            case 59:
                admin.door.change(3,4,17,admin);
                break;
            case 60:
                System.out.println("M.Quirell : Ahah quand j'aurais mis la main sur la pierre philosophal, Voldemord renaitra !");
                Boss detraqueur = new Boss("Détraqueur détraquer",120,60,50,18,17,75);
                detraqueur.fight(admin);
                admin.var.act = false;
                admin.findRoomById(21).objects.remove(0);
                break;
            case 61:
                System.out.println("Dumbeldor : C'est un chantier, ne 'approche pas");
                break;
            case 62:
                admin.door.change(9,27,10,admin);
                break;
            case 63:
                forbiddenspell(admin);
                break;
        }
    }
    private void Hagrid(Game admin){
        if(admin.var.idRoom == 7){
            HagridMeet(admin);
            admin.door.change(2,2,8,admin);
            System.out.println("Hagrid : Bienvenue au chemin de travers, la banque est au bout, recupert ton argents et va dans les magasins acheter tes affaires, je t'attend là");

        } else if (admin.var.idRoom == 8) {
            if (admin.var.book && admin.var.cloth && admin.var.wizard.wand !=null){
                System.out.println("Hagrid : Tu as fini tes course, aller on rentre");
                admin.delay(1000);
                admin.door.change(7,1,5,admin);
                admin.delay(2000);
                System.out.println("Bon, bas plus qu'à attendre la rentré...");
                admin.var.sleepB = true;
            }
            else {
                System.out.println("Hagrid : Tu n'as pas fini tes courses");
                admin.delay(1000);
            }
        }
    }
    private void HagridMeet(Game admin){
        String myName = admin.var.wizard.getFirstname();
        System.out.println(myName+" : Qui êtes vous et que faite vous dans ma chambre ?!");
        admin.enter();
        System.out.println("??? : Moi ? Juste un demi-géant");
        admin.enter();
        System.out.println("Je suis la pour t'emmener dans le chateau d'un vieux monsieur qui adore les bonbons va te faire découvrir plein de choses avec sa baguette magique");
        admin.enter();
        System.out.println("A part ça je m'appel Hagrid");
        admin.enter();
        System.out.println(myName+" : Qu'es ce que c'est que cette histoire...");
        admin.enter();
        if (admin.random.nextInt(10)==5) {
            System.out.println("Hagrid : Tu es un sourcier " + myName);
            admin.enter();
            System.out.println(myName+" : Mais je suis alergique à l'eau");
            admin.enter();
            System.out.println("Hagrid : Ah! tant pis tu aurais pu avoir une belle vie " + myName);
            System.exit(0);
        }
        System.out.println("Hagrid : Tu es un sorcier " + myName);
        admin.enter();
        System.out.println("Hagrid : D'ailleur tiens ,ta lettre d'admission à Poudlard ");
        admin.lettre();
        admin.var.mailtoPoudlard = true;
        admin.enter();
        System.out.println(myName+" : Où peut on acheter les affaires ?");
        admin.enter();
        System.out.println("Hagrid : Au chemin de traverse d'ailleur on y va tout de suite");
        admin.enter();
        System.out.println(myName+" : Et les Dursley ?");
        admin.enter();
        System.out.println("Hagrid : Dudley a une queue de cochon");
        admin.enter();
        System.out.println(myName+" : ok...");
        admin.enter();
        admin.var.roomList.get(6).objects.remove(admin.var.roomList.get(6).objects.size()-1);
    }
    private void Vernon(Game admin){
        String myName = admin.var.wizard.getFirstname();
        if (admin.var.StartMail && !admin.var.mailtoPoudlard){
            VernonMail(admin);
        } else if (admin.var.departure) {
            admin.var.departure= false;
            System.out.println(myName+" : Tu peux m'ammener à la gare ?");
            admin.enter();
            System.out.println("Vernon : Tu as de la chance, je dois allez à Londre aujourd'hui");
            admin.enter();
            if (admin.var.wizard.years == 0){
                admin.door.change(0,13,9,admin);
                System.out.println("Bon, maintenant il faut trouver la voix 9 3/4...");
                admin.enter();
            } else {
                admin.blackscreen(3000);
                admin.door.change(9,23,10,admin);
            }
        } else {
            System.out.println("Vernon : Voiture : 1, tes parents : 0");
            admin.enter();
        }
    }
    private void VernonMail(Game admin){
        String myName = admin.var.wizard.getFirstname();
        if (!admin.var.mailtoVernon){
            System.out.println("Vernon : Je n'aime pas me répéter, va chercher le courrier");
        } else {
            admin.var.mailtoVernon = false;
            System.out.print("Vernon : Les impots,...");
            admin.enter();
            System.out.print("le journal...");
            admin.enter();
            System.out.print("la rançon pour le petit Louis...");
            admin.enter();
            System.out.print("et un courrier pour...");
            admin.delay(1000);
            System.out.println("TOI!?!");
            admin.enter();
            System.out.print("On va commencer par lire ton courrier...");
            admin.enter();
            System.out.println(myName + " : C'est mon courrier donne le moi !");
            admin.enter();
            System.out.println("Vernon : non");
            admin.enter();
            System.out.println("non...impossible...");
            admin.enter();
            System.out.println("Petunia regarde ça !");
            admin.enter();
            System.out.println("Petunia : je ni crois pas...");
            admin.enter();
            System.out.println(myName + " : DONNEZ MOI MON COURRIER");
            admin.enter();
            System.out.println("Vernon : FILE DANS LA CHAMBRE DU HAUT A PARTIR DE MAINTENANT C'EST LA TIENNE");
            admin.enter();
            System.out.println(myName + " : et mon courrier ?");
            admin.enter();
            System.out.println("Vernon : Oublie le");
            admin.enter();
            admin.door.change(1, 3, 7,admin);
            admin.var.sleep = true;
        }
    }
    private void Bed(Game admin) {
        if (admin.var.sleep){
            admin.var.sleep = false;
            admin.blackscreen(2000);
            admin.addObjetOnMap(26,1,3,7);
        } else if (admin.var.sleepB) {
            admin.var.sleepB = false;
            admin.blackscreen(2000);
            System.out.println("Ca y est, c'est le jour du départ !");
            admin.delay(1000);
            System.out.println("Je devrais demander a Vernon de m'amener à la gare");
            admin.delay(1000);
            admin.var.departure = true;
        } else if (admin.var.sleapstart) {
            admin.var.sleapstart=false;
            admin.blackscreen(2000);
        }
        else if (admin.var.action==5) {
            System.out.println("Quelle matière je devrais travailler avant de me coucher ?");
            switch (admin.playerchoice("(Sortilège:0 ,Potion:1 ,DFM:2 ,Astronomie:3)", 4)) {
                case 0:
                    admin.var.wizard.setMagic(admin.var.wizard.getMagic() + 3);
                    break;
                case 1:
                    admin.potionGift(1);
                    admin.var.wizard.potionrevision++;
                    break;
                case 2:
                    admin.var.wizard.setResist(admin.var.wizard.getResist()+5);
                    break;
                case 3:
                    admin.var.wizard.setFullLife(admin.var.wizard.getFullLife()+5);
                    break;
            }
            System.out.println("Bon maintenant dodo...");
            admin.enter();
            admin.blackscreen(2500);
            admin.var.act = false;
        }
    }
    private void SortingHat(Game admin){
        System.out.print("Facile : ");
        if (admin.var.wizard.getHouse().name().equals("GRYFFONDOR")){
            System.out.println("Griffondor !");
            admin.enter();
            System.out.println("Dumbuldor : Tu habitera donc dans la tour ouest");
        } else if (admin.var.wizard.getHouse().name().equals("SLYTHERIN")) {
            System.out.println("Serpentard !");
            admin.enter();
            System.out.println("Dumbuldor : Tu habitera donc dans les cachots");
        } else if (admin.var.wizard.getHouse().name().equals("RAVENCLAW")) {
            System.out.println("Serdaigle !");
            admin.enter();
            System.out.println("Dumbuldor : Tu habitera donc dans la tour est");
        } else {
            System.out.println("Poufsoufle !");
            admin.enter();
            System.out.println("Dumbuldor : Tu habitera donc dans les cachots");
        }
        admin.enter();
        System.out.println("Voilà qui met fin à la répartition mais je dois vous préciser une règle pour cette année");
        admin.enter();
        System.out.println("Il est totalement interdit d'aller dans la salle oublier de l'escalier Ouest...");
        admin.enter();
        System.out.println("Vraiment, si quelqu'un y va je me facherais tout rouge...");
        admin.enter();
        System.out.println("Je rigole pas...");
        admin.enter();
        System.out.println("Normalement elle est verouiller mais si quelqu'un tente Alohomora...");
        admin.enter();
        System.out.println("Bon, maintenant allez vous coucher");
        admin.enter();
        admin.var.wizard.years = 1;
        admin.var.sleapstart = true;
        admin.removeObjectFromMap(43,10);
        admin.removeObjectFromMap(44,10);
    }
    private void lesson(String teachers,int type,Game admin) {
        Wizard wizard = admin.var.wizard;
        String myName = admin.var.wizard.getFirstname();
        if (admin.var.action==type){
            System.out.println(teachers+" : Bonjours monsieur "+myName+", dépéchez vous de rejoindre votre place, le cours va commencer");
            admin.blackscreen(2500);
            admin.var.act = false;
            System.out.println(teachers+" : Voilà le cours est terminé vous pouvez ranger vos affaires et vous en aller");
            admin.enter();
            admin.XP(8);
        }
        else {
            System.out.println(teachers+" : Nous n'avons pas cours ensemble monsieur "+myName);
        }
        switch (type){
            case 1:
                wizard.setMagic(wizard.getMagic()+5);
                break;
            case 2:
                admin.potionGift(3);
                admin.enter();
                break;
            case 3:
                wizard.setResist(wizard.getResist()+5);
                break;
            case 4:
                wizard.setFullLife(wizard.getFullLife()+5);
                break;

        }
    }
    private void guipure(Game admin, String myName){
        System.out.println(myName+": Les meilleurs vetement s'il vous plait");
        admin.enter();
        System.out.println("Mme Guipure : Vous avez l'argent ?");
        if(admin.var.bank && !admin.var.cloth){
            System.out.println(myName+": Oui");
            admin.enter();
            System.out.println("Mme Guipure : Alors on va vous trouvez ça...");
            admin.blackscreen(2000);
            admin.var.cloth = true;
            System.out.println("Mme Guipure : Voilà cela vous va à ravir");
        } else if (admin.var.cloth) {
            System.out.println("Mme Guipure : Attendez, vous êtes déjà venu !");
        } else {
            System.out.println(myName+": Non");
            admin.enter();
            System.out.println("Mme Guipure : Pas d'argent pas de vetement");
        }
    }
    private void basilic(Game admin){
        if (admin.var.wizard.getHouse().name().equals("GRYFFONDOR")){
            System.out.println("Tiens une épée par terre");
            admin.var.wizard.Knowspell.add(0,admin.findSpellById(16));
            admin.delay(1000);
        }
        Boss basilic = new Boss("Basilic, le gros serpent mertrier multirecidiviste",50,150,30,15,3,50);
        basilic.fight(admin);
        if (admin.var.wizard.getHouse().name().equals("GRYFFONDOR")){
            admin.var.wizard.Knowspell.remove(0);
        }
        if (admin.var.action == 6) {
            admin.var.act = false;
        }
        admin.findRoomById(20).objects.remove(0);
    }
    private void forbiddenspell(Game admin){
        if (!admin.var.forbiddenspell) {
            System.out.println("Il y a un parchemin au font de cette petite grotte...");
            admin.enter();
            System.out.println("Les sortilèges impardonables...");
            admin.enter();
            System.out.println("Attend...ils sont si simple à lancer !");
            admin.blackscreen(2000);
            admin.var.wizard.Knowspell.add(admin.findSpellById(11));
            admin.var.wizard.Knowspell.add(admin.findSpellById(12));
            admin.var.wizard.Knowspell.add(admin.findSpellById(13));
            admin.var.forbiddenspell = true;
        }
    }
    private void dfmlesson(Game admin){
        switch (admin.var.wizard.years){
            case 1:
                lesson("M.Quirell",3,admin);
                break;
            case 2:
                lesson("M.Lockart",3,admin);
                break;
            case 3:
                lesson("M.Lupin",3,admin);
                break;
            case 4:
                lesson("M.Maugrey",3,admin);
                break;
            case 5:
                lesson("Mme.Ombrage",3,admin);
                break;
            case 6:
                lesson("M.Rogue",3,admin);
                break;
        }
    }
    private void buybook(Game admin){
        String myName = admin.var.wizard.getFirstname();
        System.out.println(myName+": J'aimerai acheter mes livres s'il vous plait");
        admin.enter();
        System.out.println("Bibliotecaire : Vous avez l'argent ?");
        if(admin.var.bank && !admin.var.book){
            System.out.println(myName+": Oui");
            admin.enter();
            System.out.println("Bibliotecaire : Alors on va vous trouvez ça...");
            admin.blackscreen(2000);
            admin.var.book = true;
            System.out.println("Bibliotecaire : Voilà les livres de la première année");
        } else if (admin.var.cloth) {
            System.out.println("Bibliotecaire : Attendez, vous êtes déjà venu !");
        } else {
            System.out.println(myName+": Non");
            admin.enter();
            System.out.println("Bibliotecaire : Pas d'argent pas de livre");
        }
    }
    private void olivander(Game admin){
        String myName = admin.var.wizard.getFirstname();
        System.out.println(myName+": La meilleurs baguette s'il vous plait");
        admin.enter();
        System.out.println("Olivender : Vous avez l'argent ?");
        if(admin.var.bank && admin.var.wizard.wand == null){
            System.out.println(myName+": Oui");
            admin.enter();
            System.out.println("Olivender : Alors on va vous trouvez ça...");
            admin.blackscreen(2000);
            admin.var.wizard.wand=new Wand(admin);
            System.out.println("Olivender : Voilà une baguette en "+admin.var.wizard.wand.getName());
        } else if (admin.var.wizard.wand != null) {
            System.out.println("Olivender : Attendez, vous êtes déjà venu !");
        }
        else {
            System.out.println(myName+": Non");
            admin.enter();
            System.out.println("Olivender : Pas d'argent pas de baguette");
        }
    }
}
