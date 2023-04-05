package Data.Admin;

import Data.Character.Boss;
import Data.Character.Enemy;
import Data.Effect.Door;
import Data.Effect.ScriptEffect;
import Data.Item.Potion;
import Data.Room.GameObject;
import Data.Room.Room;
import Data.Spell;

import java.io.*;
import java.util.*;

public class Game implements Serializable{
    public Random random;
    public Scanner scanner;
    public Door door;
    public InitialConfig creation;
    //List of element
    public List<GameObject> objetList = new ArrayList<>();
    public List<Enemy> enemyList = new ArrayList<>();
    public List<Boss> bossList = new ArrayList<>();
    public List<Spell> spellList = new ArrayList<>();
    public List<Potion> potionList = new ArrayList<>();
    public Variable var = new Variable();
    public Save save = new Save();
    public int count;
    public Game(){
        this.objetList = new ArrayList<>();
        this.enemyList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.door = new Door(0,0,0);
        this.count = 0;
        this.creation= new InitialConfig();
    }
    //id ################################################################################################################
    public Room findRoomById(int roomId) {
        for (Room room : var.roomList) {
            if (room.getId() == roomId) {
                return room;
            }
        }
        System.out.println("BUG: l'ID "+roomId+" de la salle n'exite pas");
        return null;
    }
    public GameObject findGameObjectById(int id) {
        for (GameObject obj : objetList) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        System.out.println("BUG: l'object rechercher n'existe pas");
        return null;
    }
    public Spell findSpellById(int id) {
        for (Spell spell : spellList) {
            if (spell.getIdSpell() == id) {
                return spell;
            }
        }
        System.out.println("BUG: le sort recherché n'existe pas");
        return null;
    }
    public Potion findPotionById(int id) {
        for (Potion potion : potionList) {
            if (potion.getId() == id) {
                return potion;
            }
        }
        System.out.println("BUG: la potion recherché n'éxiste pas");
        return null;
    }
    public Enemy findEnemyById(int id) {
        for (Enemy enemy : enemyList) {
            if (enemy.getId() == id) {
                return enemy;
            }
        }
        System.out.println("BUG: l'enemie recherché n'existe pas");
        return null;
    }
    //Sort ################################################################################################################
    public void potionSort(){
        var.wizard.potionList=split(var.wizard.potionList);
    }
    public List<List<Integer>> split (List<List<Integer>> list){
        List<List<Integer>> finalList;
        if(list.size()>1){
            int cut = list.size()/2;
            List<List<Integer>>left = new ArrayList<>(list.subList(0, cut));
            List<List<Integer>>right = new ArrayList<>(list.subList(cut, list.size()));
            finalList = merge(split(left),split(right));
        }
        else {
            finalList = new ArrayList<>(list);
        }
        return finalList;
    }
    public List<List<Integer>> merge (List<List<Integer>> left,List<List<Integer>> right){
        List<List<Integer>> finalList = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()){
            if(left.get(0).get(0)>right.get(0).get(0)){
                finalList.add(right.get(0));
                right.remove(0);
            }
            else {
                finalList.add(left.get(0));
                left.remove(0);
            }
        }
        while (!right.isEmpty()){
            finalList.add(right.get(0));
            right.remove(0);
        }
        while (!left.isEmpty()){
            finalList.add(left.get(0));
            left.remove(0);
        }
        return finalList;
    }
    //Utility ###############################################################################################################
    public void hudgewhite(int n){
        for(int i =0;i<n;i++){
            System.out.println("");
        }
    }
    public int playerchoice(String question,int option){
        int choice = -1;
        while (choice<0 ||choice>=option ) {
            System.out.print(question);
            String input = scanner.nextLine();
            try {
                choice = Character.getNumericValue(input.charAt(0));
            }
            catch (IndexOutOfBoundsException e) {
            }
        }
        return choice;
    }
    public boolean playerchoiceboolean(String question){
        char character = 'n';
        boolean choice = false;
        while (character != '0' && character != '1' ) {
            System.out.print(question);
            String input = scanner.nextLine();
            try {
                character = input.charAt(0);
            }
            catch (IndexOutOfBoundsException e) {
            }
        }
        switch(character){
            case '0':
                choice = true;
                break;
            case '1':
            default:
                break;
        }
        return choice;
    }
    public void blackscreen(int time){
        String[][] room = new String[9][15];
        for (int i =0; i<9 ; i++){
            for (int k = 0 ; k<15 ; k++){
                room[i][k]="X";
            }
        }
        for (int i = 0; i < room.length; i++) {
            System.out.println(Arrays.toString(room[i]));
        }
        System.out.println("");
        delay(time);
    }
    public void enter(){
        System.out.print("(entrer)");
        scanner.nextLine();
    }
    public void XP (int xp){
        var.wizard.setXp(var.wizard.getXp()+xp);
        while (var.wizard.getXp()>=5+(var.wizard.getLevel()-1)*2){
            var.wizard.setXp(var.wizard.getXp()-(5+(var.wizard.getLevel()-1)*2));
            var.wizard.setLevel(var.wizard.getLevel()+1);
            var.wizard.setResist(var.wizard.getResist()+5);
            var.wizard.setMagic(var.wizard.getMagic()+5);
            var.wizard.setFullLife(var.wizard.getFullLife()+5);
            System.out.println("Je le sens j'ai progressé !");
            delay(1000);
        }
    }
    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addObjetOnMap(int id,int X,int Y,int idroom){
        int[]object = new int[3];
        object[0]=id;
        object[1]=X;
        object[2]=Y;
        findRoomById(idroom).objects.add(object);
    }
    public void removeObjectFromMap(int id, int idroom){
        for(int i = 0 ; findRoomById(idroom).objects.size()>i;i++){
            while (findRoomById(idroom).objects.get(i)[0]==id){
                findRoomById(idroom).objects.remove(i);
            }
        }
    }
    public void renameObject(int id, String name){
        findGameObjectById(id).setMarker(name);
    }
    //School ##############################################################################################################
    public void grade(){
        System.out.println("############################################################");
        System.out.println("  REMISE DE NOTE, COLLEGE POUDLARD, ECOLE DE SORCELLERIE");
        System.out.println("");
        System.out.println("");
        System.out.print("    Sortilège : ");
        grader(80*var.wizard.years,var.wizard.getMagic());
        System.out.print("    Potion : ");
        grader(var.wizard.years,var.wizard.potionrevision);
        System.out.print("    Défense contre les forces du mal : ");
        grader(80*var.wizard.years,var.wizard.getResist());
        System.out.print("    Astronomie : ");
        grader(100*var.wizard.years,var.wizard.getFullLife());
        System.out.println("");
        System.out.println("");
        System.out.println("############################################################");
        var.wizard.years++;
    }
    public void grader(int target,int level){
        if(level>target*1.5){
            System.out.println("O");
        }
        else if(level>target*1.2){
            System.out.println("E.E");
        }
        else if(level>target){
            System.out.println("A");
        }
        else if(level>target*0.8){
            System.out.println("P");
        }
        else if(level>target*0.5){
            System.out.println("D");
        }
        else {
            System.out.println("T");
        }
    }
    public void lettre(){
        System.out.println("############################################################");
        System.out.println("  COLLEGE POUDLARD, ECOLE DE SORCELLERIE");
        System.out.println("  Directeur : Albus Dumbeldore");
        System.out.println("Commandeur du grand ordre de Merlin");
        System.out.println("Docteur ès Sorcellerie, Enchanteur en chef, Manitou suprême");
        System.out.println("de la Confédération International des mages et des sorciers");
        System.out.println("");
        System.out.println("  Mr."+var.wizard.getName()+",");
        System.out.println("  Nous avons le plaisir de vous informer que vous bénéficiez");
        System.out.println("d'ores et déjà d'une inscription au collège Poudlard. Vous");
        System.out.println("trouverez ci-joint la liste des ouvrages et équipement");
        System.out.println("nécessaire au bon déroulement de votre scolarité.");
        System.out.println("");
        System.out.println("  La rentré étant fixé au 1 septembre, nous attendons votre");
        System.out.println("hibou le 31 aout au plus tard.");
        System.out.println("");
        System.out.println("  Veuillez croire, cher Mr."+var.wizard.getName()+",en l'expression");
        System.out.println("de nos sentiment distingués.");
        System.out.println("");
        System.out.println("        Minerva McGonagall");
        System.out.println("        Directrice-adjointe");
        System.out.println("############################################################");
    }
    public void potionGift(int number){
        int k;
        for (int i = 0 ; i < number ; i++){
            potionSort();
            k = 0;
            int type = random.nextInt(4)*7+var.wizard.years;
            if (var.wizard.potionList.size()>0) {

                while (var.wizard.potionList.get(k).get(0) < type && var.wizard.potionList.size()>k+1) {
                    k++;
                }
                if (var.wizard.potionList.get(k).get(0) == type) {
                    var.wizard.potionList.get(k).set(1, var.wizard.potionList.get(k).get(1) + 1);
                } else {
                    List<Integer> potion = new ArrayList<>();
                    potion.add(type);
                    potion.add(1);
                    var.wizard.potionList.add(potion);
                }
            }else {
                List<Integer> potion = new ArrayList<>();
                potion.add(type);
                potion.add(1);
                var.wizard.potionList.add(potion);
            }
            System.out.println("J'ai obtenu une "+findPotionById(type).name);
            potionSort();
        }
    }

    //Action on the map ####################################################################################################
    public void action() throws IOException {
        char character='n';
        ScreenMap();
        see();
        System.out.print("Que dois je faire ? (z:↑, q:←, d:→, s:↓, a:interagir, e:menu) : ");
        String input = scanner.nextLine();
        try {
            character = input.charAt(0);
        }
        catch (IndexOutOfBoundsException e) {
        }
        switch (character){
            case 's':
            case 'S':
                moveonY(1);
                break;
            case 'z':
            case 'Z':
                moveonY(-1);
                break;
            case 'q':
            case 'Q':
                moveonX(-1);
                break;
            case 'd':
            case 'D':
                moveonX(1);
                break;
            case 'e':
            case 'E':
                menu();
                break;
            case 'a':
            case 'A':
                interaction();
                break;
            default:
                break;
        }

    }
    public void moveonX(int direction){
        look('X',direction);
        int[] mcXY = heroXY();
        boolean ok = true;
        List<int[]> obj = findRoomById(var.idRoom).objects;
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i)[1] == (mcXY[0] + direction) && obj.get(i)[2] == mcXY[1] && isitObstacle(obj.get(i)[0])) {
                ok = false;
                break;
            }
        }
        if (mcXY[0]+direction<0 ||mcXY[0]+direction> findRoomById(var.idRoom).getX()-1){
            ok = false;
        }
        if(ok){
            int position = 0;
            for (int i = 0; i<obj.size();i++) {
                if (obj.get(i)[0] == 1) {
                    position = i;
                    break;
                }
            }
            obj.get(position)[1]=obj.get(position)[1]+direction;
        }
    }
    public void moveonY(int direction){
        look('Y',direction);
        int[] mcXY = heroXY();
        boolean ok = true;
        List<int[]> obj = findRoomById(var.idRoom).objects;
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i)[2] == (mcXY[1] + direction) && obj.get(i)[1] == mcXY[0] && isitObstacle(obj.get(i)[0])) {
                ok = false;
                //System.out.println("Obstacle");
                break;
            }
        }
        if (mcXY[1]+direction<0 ||mcXY[1]+direction> findRoomById(var.idRoom).getY()-1){
            ok = false;
            //System.out.println("limite");
        }
        if(ok){
            int position = 0;
            for (int i = 0; i<obj.size();i++) {
                if (obj.get(i)[0] == 1) {
                    position = i;
                    break;
                }
            }
            obj.get(position)[2]=obj.get(position)[2]+direction;
        }
    }
    public void interaction() throws IOException {
        ScriptEffect scr = new ScriptEffect();
        int effect = 0;
        if (checkunder()!= 0){
            effect = checkunder();
        }
        else if (checkdirection() != 0){
            effect = checkdirection();
        }
        if (effect != 0){
            scr.script(effect,this);
        }
    }
    public void look(char axe,int direction){
        if (axe=='X' && direction==1){
            findGameObjectById(1).setDirection('r');
        }
        else if (axe=='X' && direction==-1){
            findGameObjectById(1).setDirection('l');
        }
        else if (axe=='Y' && direction==1){
            findGameObjectById(1).setDirection('d');
        }
        else if (axe=='Y' && direction==-1){
            findGameObjectById(1).setDirection('u');
        }
    }
    private void see(){
        char direction = findGameObjectById(1).getDirection();
        switch (direction){
            case 'u':
                System.out.println("Regard : ↑");
                break;
            case 'l':
                System.out.println("Regard : ←");
                break;
            case 'r':
                System.out.println("Regard : →");
                break;
            case 'd':
                System.out.println("Regard : ↓");
                break;
        }

    }
    public int[] heroXY(){
        List<int[]> objects = findRoomById(var.idRoom).getObjects();
        int[] XY = new int[2];
        for (int i = 0; i<objects.size();i++ ){
            if (objects.get(i)[0]==1){
                XY[0]=objects.get(i)[1];
                XY[1]=objects.get(i)[2];
                //System.out.print("[X,Y] = ");
                //System.out.println(Arrays.toString(XY));
                return XY;
            }
        }
        System.out.println("PAS de MC");
        return null;
    }
    public int checkdirection(){
        int check = 0;
        char look = findGameObjectById(1).getDirection();
        List<int[]> obj = findRoomById(var.idRoom).objects;
        int[] mcXY = heroXY();
        int i = 0 ;
        int[] objTest;
        while(i<obj.size()) {
            objTest= obj.get(i);
            int X = objTest[1];
            int Y = objTest[2];
            int script = objEffect(objTest[0]);
            switch (look) {
                case 'r':
                    if(mcXY[0]==X-1 && mcXY[1]==Y && script != 0){
                        check = script;
                    }
                    break;
                case 'l':
                    if(mcXY[0]==X+1 && mcXY[1]==Y && script != 0){
                        check = script;
                    }
                    break;
                case 'u':
                    if(mcXY[1]==Y+1 && mcXY[0]==X && script != 0){
                        check = script;
                    }
                    break;
                case 'd':
                    if(mcXY[1]==Y-1 && mcXY[0]==X && script != 0){
                        check = script;
                    }
                    break;
            }
            i++;
        }
        return check;
    }
    public int checkunder(){
        int check = 0;
        List<int[]> obj = findRoomById(var.idRoom).objects;
        int[] mcXY = heroXY();
        int i = 0 ;
        int[] objTest;
        while(i<obj.size()) {
            objTest = obj.get(i);
            boolean obstacle = isitObstacle(objTest[0]);
            int X = objTest[1];
            int Y = objTest[2];
            if(mcXY[0]==X && mcXY[1]==Y && !obstacle && objEffect(objTest[0]) != 0){
                check = objEffect(objTest[0]);
            }
            i++;
        }
        return check;
    }
    public void menu() {
        System.out.println("1 : Caracteristique");
        System.out.println("2 : Sort");
        System.out.println("3 : Inventaire");
        System.out.println("4 : Agenda");
        System.out.print("Action à faire : ");
        String inputString;
        inputString = new String(scanner.nextLine());
        char character = 0;
        try {
            character = inputString.charAt(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Aucune Data.Effect.action entrée");
        }
        switch (character) {
            case '1':
                stat();
                break;
            case '2':
                spellmenu();
                break;
            case '3':
                potionmenu();
                break;
            case '4':
                planning();
                break;
        }
        enter();

    }
    private void stat() {
        System.out.println("");
        System.out.println("Prénom : " + var.wizard.getFirstname());
        System.out.println("Nom : " + var.wizard.getName());
        if (var.wizard.getLevel() != 0) {
            System.out.println("Niveau : " + var.wizard.getLevel());
        }
        if (var.wizard.getMagic() != 0) {
            System.out.println("Vie : " + var.wizard.getFullLife() + "/" + var.wizard.getFullLife());
        }
        if (var.wizard.getMagic() != 0) {
            System.out.println("Magie : " + var.wizard.getMagic());
        }
        if (var.wizard.getResist() != 0) {
            System.out.println("Résistance : " + var.wizard.getResist());
        }
        if (var.wizard.getYears() != 0) {
            System.out.println("Maison : " + var.wizard.getHouse());
        }
        if (var.wizard.getYears() != 0) {
            System.out.println("Année : " + var.wizard.getYears());
        }
        if (var.wizard.getPet() != null) {
            System.out.println("Animal : " + var.wizard.getPet());
        }
        if (var.wizard.getWand() != null) {
            System.out.println("Baguette : " + var.wizard.getWand().getName());
        }
    }
    private void spellmenu(){
        if (var.wizard.getKnowspell().size()>0) {
            for (int i = 0; i < var.wizard.getKnowspell().size(); i++) {
                System.out.println(i + ": " + var.wizard.getKnowspell().get(i).getName());
            }
        }
        else {System.out.println("Je ne connais point de sort");}
    }
    private void potionmenu(){
        if (var.wizard.getPotionList().size()==0){
            System.out.println("Je n'ai point de potion");
        }
        else {
            potionSort();
            for (int i = 0; i < var.wizard.getPotionList().size(); i++) {
                List<Integer> potion = var.wizard.getPotionList().get(i);
                System.out.println(i + ": " + findPotionById(potion.get(0)).getName() + " (" + potion.get(1) + ")");
            }
        }
    }
    private void planning(){
        switch (var.action){
            case 1:
                System.out.println("cour de sortilège dans la tour Ouest...");
                break;
            case 2:
                System.out.println("Regardons mon emplois du tu temps, j'ai...cour de potion au cachot...");
                break;
            case 3:
                System.out.println("Regardons mon emplois du tu temps, j'ai...cour de DFM dans la tour Est...");
                break;
            case 4:
                System.out.println("Regardons mon emplois du tu temps, j'ai...cour d'astronomie au sommet de la tour Ouest...");
                break;
            case 5:
                System.out.println("Regardons mon emplois du tu temps, j'ai...fini ma journée !");
                break;
            case 6:
                System.out.println("Quelque chose se passe dans l'école, je devrais aller voir...");
                break;
        }
    }
    public int objEffect(int id) {
        GameObject obj = findGameObjectById(id);
        return obj.getEffect();
    }
    public boolean isitObstacle(int id) {
        GameObject obj = findGameObjectById(id);
        return obj.isObstacle();
    }
    //Screen ################################################################################################################
    public void ScreenMap(){
        int roomID = var.idRoom;
        System.out.println("#################### "+findRoomById(var.idRoom).getName()+" ####################");
        String[][] room = new String[9][15];
        String[][] GlobalRoom = roomBuilder(roomID);
        int[] mc = heroXY();
        for (int i = (mc[0]);i<(mc[0]+15);i++) {
            for (int k = (mc[1]);k<(mc[1]+9);k++) {
                room[k-mc[1]][i-mc[0]] = GlobalRoom[k][i];
            }
        }

        for (int i = 0; i < room.length; i++) {
            System.out.println(Arrays.toString(room[i]));
        }
        System.out.println("");
    }
    public String[][] roomBuilder(int id){
        Room room = findRoomById(id);
        List<int[]> obj = room.objects;
        int x = room.getX();
        int y = room.getY();
        String[][] ArrayRoom = new String[y+8][x+14];
        //Border define
        for (int i = 0; i <(y+8);i++) {
            for (int k = 0; k < (x+14); k++) {
                if(i>3 && i<(y+4) && k>6 && k<(x+7)){
                    ArrayRoom[i][k]=" ";
                }
                else {
                    ArrayRoom[i][k]="X";
                }
            }
        }
        //add object
        for (int i = 0;i<obj.size() ;i++){
            ArrayRoom[obj.get(i)[2]+4][obj.get(i)[1]+7] = findGameObjectById(obj.get(i)[0]).getMarker();
        }
        return ArrayRoom;
    }
    public void printRoom(int id) {
        String[][] room = roomBuilder(id);
        for (int i = 0; i < room.length; i++) {
            System.out.println(Arrays.toString(room[i]));
        }
    }
}
