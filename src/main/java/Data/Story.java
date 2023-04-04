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
    }
    //General ############################################################################################################
    private void action(int type,Game admin) throws IOException {
        admin.var.action = type;
        admin.var.act = true;
        if (type != 6) {
            System.out.print("Regardons mon emplois du tu temps, j'ai...");
        }
        switch (type){
            case 1:
                System.out.println("cour de sortilège dans la tour Ouest...");
                break;
            case 2:
                System.out.println("cour de potion au cachot...");
                break;
            case 3:
                System.out.println("cour de DFM dans la tour Est...");
                break;
            case 4:
                System.out.println("cour d'astronomie au sommet de la tour Ouest...");
                break;
            case 5:
                System.out.println("fini ma journée !");
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
        while (admin.var.act){
            admin.action();
        }
        admin.var.wizard.Knowspell.add(admin.findSpellById(sort));
        System.out.println("Je sais maintenant utilisé le sort "+admin.findSpellById(sort).name+" !");
    }
    private void firstnight(Game admin) throws IOException {
        if (admin.var.stage == admin.count){
            while (admin.var.sleapstart) {
                admin.action();
            }
            admin.count++;
        }
    }
    //A0 #################################################################################################################
    private void A0(Game admin) throws IOException {
        Wizard wizard = admin.var.wizard;
        if (wizard.years==0) {
            A0dream(admin);
            admin.save.firstsave(admin);
            while(wizard.years==0){
                admin.action();
            }
            admin.save.saveGame(admin.var);
        }

    }//1,2
    private void A0dream(Game admin) throws IOException {
        if (!admin.var.enddream) {
            A0namefirstname(admin);
            A0patronum(admin);
            A0House(admin);
            System.out.println("Ce n'était qu'un reve...");
            admin.var.enddream = true;
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
            admin.save.saveGame(admin.var);
        }
    }//3
    private void A1fn(Game admin) throws IOException {
        if (!admin.var.A1fn){
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
            admin.var.A1d1 = true;
            action(5, admin);
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
            System.out.println("Alohomora... un sort qui sert a avoir les portes fermé... intéréssant...");
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
        admin.enter();
        admin.addObjetOnMap(72,5,4,18);
        action(6,admin);
        admin.findRoomById(17).objects.remove(0);
        admin.enter();
        System.out.println("Dumbeldore : Apparemment une tête brulée n'a pas écouté les consignes et a foncé dans le admins");
        admin.enter();
        System.out.println("Par miracle cette énergumène est encore en vie, donc vous pourvez sortir");
        admin.enter();
    }//event
    //A2 #################################################################################################################
    private void A2(Game admin) throws IOException {
        if (admin.var.wizard.years == 2) {
            admin.var.sleapstart = true;
            admin.addObjetOnMap(64, 5, 2, 16);
            admin.renameObject(64, "L");
            System.out.println("Dumbeldore : Salut les petits garnement");
            admin.enter();
            System.out.println("le voyage a dû vous fatiguer, donc aller vous coucher avant qu'un méchant serpent vous croque");
            admin.enter();
            System.out.println("mais je parle pas du serpent cacher dans les tuyaux car il existe pas");
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

            while (admin.var.sleapstart) {
                admin.action();
            }
        }
        admin.var.A2fn=true;
        admin.save.saveGame(admin.var);
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
            System.out.println("Dumbeldore : Bonjour, bonsoir, bon après midi ou ce que vous voulez");
            admin.enter();
            System.out.println("En tout cas allez vous coucher et ne faite pas de cauchemar");
            admin.enter();
            System.out.println("ce n'est pas comme si un détraqueur aller débarquer dans la forêt interdite...");
            admin.enter();
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
        while (admin.var.sleapstart && !admin.var.A3fn){
            admin.action();
        }
        admin.var.A3fn = true;
        admin.save.saveGame(admin.var);
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
            admin.addObjetOnMap(77,38,51,22);
            System.out.println("Dumbeldore : Alerte à tous les éleves, apparament un détraqueur se balade dans la forêt interdite...");
            admin.enter();
            System.out.println("Surtout ne faite pas de bêtise comme vous dire que vous aurez une chance contre lui...");
            admin.enter();
            System.out.println("Pour rappel, pour entrer dans la la forêt interdite il faut prendre la porte tout en bas du grand halle...");
            admin.enter();
            System.out.println("SURTOUT NE PASSEZ PAS CETTE PORTE !");
            admin.enter();
            action(6,admin);
            action(5,admin);
            admin.var.A3d3 = true;
            admin.save.saveGame(admin.var);
        }
    }
    private void A3D4(Game admin)throws IOException {
        if (!admin.var.A3d4) {
            System.out.println("Dernier jours de l'année, demain je serais chez moi, je devrais faire mes valises...");
            action(3,admin);
            action(4,admin);
            action(5,admin);
            admin.var.A3d4 = true;
            admin.save.saveGame(admin.var);
        }
    }
    //A4 #################################################################################################################
}
