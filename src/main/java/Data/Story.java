package Data;

import Data.Admin.Game;
import Data.Character.Wizard;

import java.io.IOException;


public class Story {
    public void allstory(Game admin)throws IOException{
        A0(admin);
        A1(admin);
        A2(admin);
        A3(admin);
        A4(admin);
        A5(admin);
        action(6,admin);
    }
    //General ############################################################################################################
    private void action(int type,Game admin) throws IOException {
        admin.var.action = type;
        admin.var.act = true;
        agenda(type,admin);
        switch (type){
            case 1:
                if (admin.var.spelltobad){
                    admin.var.act = false;
                }else {
                    System.out.println("Regardons mon emplois du tu temps, j'ai...cour de sortilège dans la tour Ouest...");
                }
                break;
            case 2:
                if (admin.var.spelltobad){
                    admin.var.act = false;
                }else {
                    System.out.println("Regardons mon emplois du tu temps, j'ai...cour de potion au cachot...");
                }
                break;
            case 3:
                if (admin.var.spelltobad){
                    admin.var.act = false;
                }else {
                    System.out.println("Regardons mon emplois du tu temps, j'ai...cour de DFM dans la tour Est...");
                }
                break;
            case 4:
                if (admin.var.spelltobad){
                    admin.var.act = false;
                }else {
                    System.out.println("Regardons mon emplois du tu temps, j'ai...cour d'astronomie au sommet de la tour Ouest...");
                }
                break;
            case 5:
                if (admin.var.spelltobad){
                    admin.var.act = false;
                }else {
                    System.out.println("Regardons mon emplois du tu temps, j'ai...fini ma journée !");
                }
                break;
        }
        admin.enter();
        while (admin.var.act){
            admin.action();
        }
    }
    private void action(int type,int sort,Game admin) throws IOException {
        admin.var.action = type;
        admin.var.act = true;
        System.out.println("Regardons mon emplois du tu temps, j'ai...cour de sortilège dans la tour Ouest...");
        agenda(1,admin);
        while (admin.var.act){
            admin.action();
        }
        admin.var.wizard.Knowspell.add(admin.findSpellById(sort));
        System.out.println("Je sais maintenant utilisé le sort "+admin.findSpellById(sort).name+" !");
    }
    private void agenda(int type, Game admin){
        switch (type) {
            case 1:
                admin.var.agenda = "Regardons mon emplois du tu temps, j'ai...cour de sortilège dans la tour Ouest...";
                break;
            case 2:
                admin.var.agenda = "Regardons mon emplois du tu temps, j'ai...cour de potion au cachot...";
                break;
            case 3:
                admin.var.agenda = "Regardons mon emplois du tu temps, j'ai...cour de DFM dans la tour Est...";
                break;
            case 4:
                admin.var.agenda = "Regardons mon emplois du tu temps, j'ai...cour d'astronomie au sommet de la tour Ouest...";
                break;
            case 5:
                if (admin.var.A1d3 && !admin.var.A1d4 && !admin.var.quirelldead){
                    admin.var.agenda = "Je devrais aller void dans la salle en bas de l'escalier est";
                }else {
                    admin.var.agenda = "Regardons mon emplois du tu temps, j'ai...fini ma journée !";
                }
                break;
        }
    }
    //A0 #################################################################################################################
    private void A0(Game admin) throws IOException {
        Wizard wizard = admin.var.wizard;
        if (wizard.years==0) {
            A0dream(admin);
            while(wizard.years==0){
                admin.action();
            }
            admin.save.saveGame(admin.var);
        }

    }//1,2
    private void A0dream(Game admin) throws IOException {
        if (!admin.var.enddream) {
            admin.creation.RoomCreator(admin);
            A0namefirstname(admin);
            A0patronum(admin);
            A0House(admin);
            admin.var.agenda = "Je n'ai rien à faire";
            System.out.println("Ce n'était qu'un reve...");
            admin.var.enddream = true;
            admin.save.firstsave(admin);
            admin.enter();
        }
    }//0
    private void A0namefirstname(Game admin){
        System.out.println("Où suis-je ?...");
        admin.delay(1000);
        System.out.println("Qui suis-je ?...");
        admin.delay(1000);
        System.out.print("Mon prenom...quel est mon prénom...");
        admin.delay(750);
        System.out.print("Jean-Claude ? ");
        admin.delay(750);
        System.out.println("non c'est pas ça...");
        admin.delay(750);
        System.out.print("A oui je sais, mon prénom est ");
        String inputString = new String(admin.scanner.nextLine());
        admin.var.wizard.setFirstname(inputString);
        System.out.print("et mon nom ");
        inputString = admin.scanner.nextLine();
        admin.var.wizard.setName(inputString);
        admin.delay(500);
        System.out.print(".");
        admin.delay(750);
        System.out.print(".");
        admin.delay(750);
        System.out.println(".");
        admin.delay(1000);
    }
    private void A0patronum(Game admin) throws IOException {
        System.out.print("Cette boule lumineuse...");
        admin.delay(750);
        System.out.println("on dirait un animal...");
        admin.delay(750);
        admin.ScreenMap();
        System.out.println("je devrais aller voir de plus près, il pourra peut-être m'aider...");
        admin.delay(750);
        while (!admin.var.patronumSet) {
            admin.action();
        }
        System.out.print("Je sais ! c'est un ");
        String inputString = admin.scanner.nextLine();
        admin.var.wizard.setPatronus(inputString);
        admin.door.change(2, 2, 2, admin);
    }
    private void A0House(Game admin) throws IOException {
        System.out.println("Quatre animaux...l'un d'eux dois bien pouvoir m'aider...");
        admin.delay(750);
        while (admin.var.wizard.getHouse().name().equals("NONE")) {
            admin.action();
        }
        admin.door.change(1, 1, 3, admin);

    }
    //A1 #################################################################################################################
    private void A1(Game admin) throws IOException {
        if (admin.var.wizard.years==1) {
            A1fn(admin);
            A1D1(admin);
            A1D2(admin);
            A1D3(admin);
            A1D4(admin);
            if (!admin.var.quirelldead) {
                System.out.println("Voldemort : je suis revenu grace à la pierre philosophal, meure !)");
                System.exit(0);
            }
            admin.blackscreen(1000);
            admin.door.change(9, 23, 10, admin);
            admin.grade();
            admin.save.saveGame(admin.var);
        }
    }//3
    private void A1fn(Game admin) throws IOException {
        if (!admin.var.A1fn){
            admin.var.agenda = "Je devrais aller me coucher, les cours commence demain";
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.var.A1fn=true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A1D1(Game admin) throws IOException{
        if (!admin.var.A1d1) {
            action(1, 2, admin);
            action(2, admin);
            action(5, admin);
            admin.var.A1d1 = true;
            admin.save.saveGame(admin.var);
        }
    }//4
    private void A1D2(Game admin) throws IOException{
        if (!admin.var.A1d2) {
            action(3, admin);
            action(4, admin);
            A1troll(admin);
            action(5, admin);
            admin.var.A1d2=true;
            admin.save.saveGame(admin.var);
        }
    }//5
    private void A1D3(Game admin) throws IOException{
        if (!admin.var.A1d3) {
            action(1, admin);
            admin.var.Alohomora = true;
            System.out.println(admin.var.wizard.firstname+" : Alohomora... un sort qui sert a avoir les portes fermé... intéréssant...");
            admin.enter();
            action(2, admin);
            action(5, admin);
            admin.var.A1d3=true;
            admin.save.saveGame(admin.var);
        }
    }//6
    private void A1D4(Game admin) throws IOException{
        if (!admin.var.A1d4) {
            if (!admin.var.quirelldead) {
                action(3, admin);
            }
            action(4, admin);
            admin.var.action = 5;
            admin.var.act = true;
            System.out.println("Dernier jours de l'année, demain je serais chez moi, je devrais allez faire mes valises...");
            if (!admin.var.quirelldead) {
                System.out.println("Mais avant ça je devrais allez voir ce qui ce passe dans la salle inutiliser");
            }
            while (admin.var.act) {
                admin.action();
            }
            admin.var.A1d4=true;
            admin.save.saveGame(admin.var);
        }
    }//7
    private void A1troll(Game admin) throws IOException {
        System.out.println("Dumbeldore : Alerte à tous les éleves, un troll est actuellement dans les toilettes du cachots");
        admin.enter();
        System.out.println("Soyez sage et rester dans vos salle en attendant que ce problème sois regler");
        admin.enter();
        System.out.println("Surtout ne faite pas de bêtise comme vous dire que vous aurez une chance contre lui...");
        admin.enter();
        System.out.println("Et surtout ne pensez pas que Wigardium Leviosa peut lui faire quelquechose...");
        admin.addObjetOnMap(72,5,4,17);
        admin.var.agenda = "Il y a un troll dans les cachot, c'est l'heure de s'amuser !";
        action(6,admin);
        admin.removeObjectFromMap(72,17);
        admin.enter();
        System.out.println("Dumbeldore : Apparemment une tête brulée n'a pas écouté les consignes et a foncé dans le tas");
        admin.enter();
        System.out.println("Par miracle cette énergumène est encore en vie contrairement au troll, donc vous pourvez sortir");
        admin.enter();
    }//event
    //A2 #################################################################################################################
    private void A2(Game admin) throws IOException {
        if (admin.var.wizard.years == 2) {
            A2fn(admin);
            admin.enter();
            A2D1(admin);
            A2D2(admin);
            A2D3(admin);
            A2D4(admin);
            admin.grade();
            admin.blackscreen(1000);
            admin.door.change(9, 23, 10, admin);
            admin.save.saveGame(admin.var);
        }

    }//8
    private void A2fn (Game admin) throws IOException {
        if (!admin.var.A2fn) {
            admin.var.sleapstart = true;
            admin.addObjetOnMap(63, 5, 2, 16);
            admin.renameObject(63, "L");
            System.out.println("Dumbeldore : Salut les petits garnement");
            admin.enter();
            System.out.println("le voyage a dû vous fatiguer, donc aller vous coucher avant qu'un méchant serpent vous croque");
            admin.enter();
            System.out.println("mais je parle pas du serpent cacher dans les tuyaux car il existe pas");
            admin.enter();
            System.out.println("Bonne nuit");
            admin.enter();
            admin.var.agenda = "Je devrais aller me coucher, les cours commence demain";
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.var.A2fn=true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A2D1(Game admin)throws IOException {
        if (!admin.var.A2d1) {
            action(1, 3, admin);
            action(2, admin);
            System.out.println("Dumbeldore : Alerte à tous les éleves, nous avons trouvé le chat du consièrge mort");
            admin.enter();
            System.out.println("Avec son sang es écris 'La chambre de secrète est ouverte vous allez mourir'");
            admin.enter();
            System.out.println("Je tiens à précisé que cela n'est pas grave et ne vous empechera pas de poursuivre vos études");
            admin.enter();
            admin.var.secretchamber = true;
            action(5, admin);
            admin.var.A2d1 = true;
            admin.save.saveGame(admin.var);
        }
    }//9
    private void A2D2(Game admin)throws IOException {
        if (!admin.var.A2d2) {
            action(3,admin);
            action(4,admin);
            action(5,admin);
            admin.var.A2d2 = true;
            admin.save.saveGame(admin.var);
        }
    }//10
    private void A2D3(Game admin)throws IOException {
        if (!admin.var.A2d3) {
            action(1,4,admin);
            action(2,admin);
            action(5,admin);
            admin.var.A2d3 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A2D4(Game admin)throws IOException {
        if (!admin.var.A2d4) {
            action(3,admin);
            action(4,admin);
            A2Basilic(admin);
            admin.var.action = 5;
            admin.var.act=true;
            System.out.println("Dernier jours de l'année, demain je serais chez moi, je devrais allez faire mes valises...");
            while (admin.var.act){
                admin.action();
            }
            admin.var.A2d4 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A2Basilic(Game admin) throws IOException {
        if(!admin.var.basilicdead){
            System.out.println("Dumbeldore : Alerte à tous les éleves, apparament un serpent meutrier multi-recidiviste");
            System.out.println("se balade actuellement sous les toilettes du cachot");
            admin.var.agenda = "Il y a un serpent sous les toilette du cachot, c'est l'heure de s'amuser !";
            admin.enter();
            System.out.println("Surtout ne faite pas de bêtise comme vous dire que vous aurez une chance contre lui...");
            admin.enter();
            if (admin.var.wizard.getHouse().name().equals("GRYFFONDOR")){
                System.out.println("Et surtout ne pensez pas trouvez une épée capable de le tuer en un coup là bas...");
            } else {
                System.out.println("Et surtout ne pensez pas que accio peut le tuer en lui arrachant une dent...");
            }
            action(6,admin);
        }
    }
    //A3 #################################################################################################################
    private void A3(Game admin) throws IOException {
        if (admin.var.wizard.years==3) {
            A3fn(admin);
            A3D1(admin);
            A3D2(admin);
            A3D3(admin);
            A3D4(admin);
            admin.grade();
            admin.blackscreen(1000);
            admin.door.change(9, 23, 10, admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A3fn(Game admin) throws IOException {
        if (!admin.var.A3fn) {
            System.out.println("Dumbeldore : Bonjour, bonsoir, bon après midi ou ce que vous voulez");
            admin.enter();
            System.out.println("En tout cas allez vous coucher et ne faite pas de cauchemar");
            admin.enter();
            System.out.println("ce n'est pas comme si un détraqueur aller débarquer dans la forêt interdite...");
            admin.enter();
            admin.var.agenda = "Je devrais aller me coucher, les cours commence demain";
            admin.var.sleapstart = true;
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.var.A3fn = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A3D1(Game admin)throws IOException {
        if (!admin.var.A3d1) {
            action(1,5,admin);
            action(2,admin);
            action(5,admin);
            admin.var.A3d1 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A3D2(Game admin)throws IOException {
        if (!admin.var.A3d2) {
            action(3,admin);
            action(4,admin);
            action(5,admin);
            admin.var.A3d2 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A3D3(Game admin)throws IOException {
        if (!admin.var.A3d3) {
            action(1,17,admin);
            action(2,admin);
            admin.addObjetOnMap(77,38,51,21);
            System.out.println("Dumbeldore : Alerte à tous les éleves, apparament un détraqueur se balade dans la forêt interdite...");
            admin.enter();
            System.out.println("Surtout ne faite pas de bêtise comme vous dire que vous aurez une chance contre lui...");
            admin.enter();
            System.out.println("Pour rappel, pour entrer dans la la forêt interdite il faut prendre la porte tout en bas du grand halle...");
            admin.enter();
            System.out.println("SURTOUT NE PASSEZ PAS CETTE PORTE !");
            admin.var.agenda = "Il y a un détraqueur dans la foret interdite, c'est l'heure de s'amuser !";
            admin.enter();
            action(6,admin);
            admin.removeObjectFromMap(77,21);
            action(5,admin);
            admin.var.A3d3 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A3D4(Game admin)throws IOException {
        if (!admin.var.A3d4) {
            action(3,admin);
            action(4,admin);
            System.out.println("Dernier jours de l'année, demain je serais chez moi, je devrais faire mes valises...");
            action(5,admin);
            admin.var.A3d4 = true;
            admin.save.saveGame(admin.var);
        }
    }
    //A4 ###############################################################################################################
    private void A4(Game admin)throws IOException {
        if (admin.var.wizard.years == 4){
            A4fn(admin);
            A4D1(admin);
            A4D2(admin);
            A4D3(admin);
            A4D4(admin);
            admin.grade();
            admin.door.change(9, 23, 10, admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A4fn(Game admin)throws IOException {
        if (!admin.var.A4fn){
            admin.renameObject(63, "F");
            System.out.println("Dumbeldore : SALUT A TOUS LES AMIS");
            admin.enter();
            System.out.println("C'EST DUMBELDORE DIRECTEUR DE L'ECOLE");
            admin.enter();
            System.out.println("aujourd'hui on se retrouve avec Olympe Maxime et Igor Karkaroff");
            admin.enter();
            System.out.println("Olympe Maxime : Coucou");
            System.out.println("Igor Karkaroff : Coucou");
            admin.enter();
            System.out.println("Dumbeldore : pour vous annoncer le retour de la coupe de feu");
            admin.enter();
            System.out.println("nous sommes tous unanime,");
            admin.enter();
            System.out.println("oui faire s'affronter des enfants dans un tournois potentielement mortel");
            admin.enter();
            System.out.println("c'est bon pour l'éducation en plus d'être extrèmement divertissant");
            admin.enter();
            System.out.println("Rassurer vous, il y plein de nouvelle regle pour évité les morts dans le public");
            admin.enter();
            System.out.println("Meme notre amis Voldy ne pourrai pas gacher cette belle fêtes !");
            admin.enter();
            System.out.println("les candidats serons annoncé demain, maintenant aller vous coucher");
            admin.enter();
            admin.var.agenda = "Je devrais aller me coucher, les cours commence demain";
            admin.var.sleapstart = true;
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.var.A4fn = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A4D1(Game admin)throws IOException{
        if (!admin.var.A4d1){
            action(1,6,admin);
            action(2,admin);
            System.out.println("Dumbeldore : test 1 2 1 2");
            admin.enter();
            System.out.println("ça marche pas ce truc...");
            admin.enter();
            System.out.println("pourquoi ça ne marche jamais du premier coup ce machin...");
            admin.enter();
            System.out.println("Quoi ? ca marche depuis tout à l'heure ? mais il fallait me le dire avant !");
            admin.enter();
            System.out.println("Alerte à tous les éleves, les candidats des qualifier pour le tournois sont tombés");
            admin.enter();
            System.out.println("Pour Durmstrang, Victor Krum");
            admin.enter();
            System.out.println("Pour Beauxbatons, Fleur Delacour");
            admin.enter();
            System.out.println("Et pour Poudlard, Cédric Digory et "+admin.var.wizard.firstname+" "+admin.var.wizard.name);
            admin.enter();
            System.out.println("Oui je triche, et alors ?");
            admin.enter();
            System.out.println("On a deux candidat et ils n'en ont qu'un");
            admin.enter();
            System.out.println("mais mon école, mes regles");
            admin.enter();
            action(5,admin);
            admin.var.A4d1 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A4D2(Game admin)throws IOException{
        if (!admin.var.A4d2){
            action(3,admin);
            action(4,admin);
            admin.renameObject(82,"V");
            admin.var.tournament = true;
            System.out.println("Dumbeldore : Cher candidat, la première épreuve est lancer");
            admin.enter();
            System.out.println("Il s'agit d'un tournois de duel de magie qui à lieu dans l'arène, au sommet de la tour Est");
            admin.enter();
            System.out.println("pour les adetpes de la magie noir, je rappelle que les sorts impardonable vous envois à Azkaban");
            admin.enter();
            System.out.println("sans passer par la case départ bien évidament");
            admin.enter();
            admin.var.agenda = "Je dois aller faire le tournois au sommet de la tour Est";
            action(6,admin);
            admin.var.tournament = false;
            admin.renameObject(82,"O");
            action(5,admin);
            admin.var.A4d2 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A4D3(Game admin)throws IOException{
        if (!admin.var.A4d3){
            action(1,admin);
            action(2,admin);
            action(5,admin);
            admin.var.A4d3 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A4D4(Game admin)throws IOException{
        if (!admin.var.A4d4){
            action(3,admin);
            action(4,admin);
            admin.maze = true;
            System.out.println("Dumbeldore : Cher candidat, la dernière épreuve est lancer");
            admin.enter();
            System.out.println("Il s'agit d'un Labyrinthe qui aurais du contenir des épreuves à l'intérieur");
            admin.enter();
            System.out.println("malheureusement nous n'avons pas eu le temps donc c'est un labyrinthe tous simple");
            admin.enter();
            System.out.println("Pour le rejoindre, nous avons enchanter la porte d'entré de l'école pour qu'elle vous y emmene");
            admin.enter();
            action(6,admin);
            admin.maze=false;
            action(5,admin);
            System.out.println("Dernier jours de l'année, demain je serais chez moi, je devrais faire mes valises...");
            admin.var.A4d4 = true;
            admin.save.saveGame(admin.var);
        }
    }
    //A5 ###############################################################################################################
    private void A5(Game admin)throws  IOException {
        if (admin.var.wizard.years == 5){
            A5fn(admin);
            A5D1(admin);
            A5D2(admin);
            A5D3(admin);
            A5D4(admin);
            admin.grade();
            admin.door.change(9, 23, 10, admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A5fn(Game admin)throws IOException {
        if (!admin.var.A5fn){
            admin.renameObject(63, "O");
            System.out.println("Dumbeldore : Coucou les amis");
            admin.enter();
            System.out.println("J'ai une super nouvelle pour vous cette année nous avons une super prof qui nous rejoint");
            admin.enter();
            System.out.println("Je suis sur que vous allez l'adorer, elle s'appel Dolores Ombrage");
            admin.enter();
            System.out.println("Pour vous donnez son niveau de gentilesse elle ADORE les chats");
            admin.enter();
            System.out.println("Si ça s'est pas la marque d'une personne adorable...");
            admin.enter();
            System.out.println("Et comme il est l'heure d'aller vous couchez je suis sur qu'elle serait ravie de vous border");
            admin.enter();
            admin.var.agenda = "Je devrais aller me coucher, les cours commence demain";
            admin.var.sleapstart = true;
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.var.A5fn = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A5D1(Game admin)throws IOException {
        if (!admin.var.A5d1){
            admin.var.A5d1 = true;
            action(1,9,admin);
            action(2,admin);
            System.out.println("Dumbeldore : Alerte à tous les éleves, j'ai été viré comme un malpropre et remplacé par Ombrage");
            admin.enter();
            System.out.println("Je vais donc, de ce pas, transplaner à pôle emplois");
            admin.enter();
            action(5,admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A5D2(Game admin)throws IOException {
        if (!admin.var.A5d2){
            admin.var.A5d2 = true;
            action(3,admin);
            action(4,admin);
            System.out.println("Ombrage : Nouveaureglement");
            admin.enter();
            System.out.println("Ombrage : reglenuméro1");
            admin.enter();
            System.out.println("Apartirdemaintenantleclubdeduelestfermée");
            admin.enter();
            admin.removeObjectFromMap(82,23);
            action(5,admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A5D3(Game admin)throws IOException {
        if (!admin.var.A5d3){
            admin.var.A5d3 = true;
            action(1,admin);
            action(2,admin);
            admin.var.ruletwo = true;
            System.out.println("Ombrage : Nouveaureglement");
            admin.enter();
            System.out.println("Ombrage : reglenuméro2");
            admin.enter();
            System.out.println("Apartirdemaintenantilestobligédeparlerauxprofesseurparladroitesinonvousserezrefuséencour");
            admin.enter();
            action(5,admin);
            admin.save.saveGame(admin.var);
        }
    }
    private void A5D4(Game admin)throws IOException {
        if (!admin.var.A5d4){
            admin.var.A5d4 = true;
            action(3,admin);
            action(4,admin);
            System.out.println("Ombrage : "+admin.var.wizard.firstname+admin.var.wizard.name);
            admin.enter();
            System.out.println("Ombrage : rejoignermoidanslegrandesallepourpasservotreBUSE");
            admin.enter();
            admin.var.agenda = "Je dois aller dans la grande salle passer mon BUSE";
            admin.addObjetOnMap(85,9,5,10);
            action(6,admin);
            action(5,admin);
            admin.var.ruletwo = false;
            admin.addObjetOnMap(82,6,6,23);
            admin.save.saveGame(admin.var);
        }
    }
}
