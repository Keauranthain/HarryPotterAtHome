package Data.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Save {
    List<String> Savegame;

    public void saveGame(Variable var) throws IOException {
        var.stage++;
        FileOutputStream fos = new FileOutputStream(var.gamename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(var);
        oos.close();
        fos.close();
    }

    public Variable loadGame(String filename) {
        //System.out.println("Nom de la sauvegarde :"+filename);
        Variable var = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            var = (Variable) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pas trouvé");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return var;
    }

    public void saveList(List<String> savename) throws IOException {
        SaveList save = new SaveList();
        save.Savegame.addAll(savename);
        String savelist = "saveliste";
        FileOutputStream fos = new FileOutputStream(savelist);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(save);
        oos.close();
        fos.close();
    }

    public List<String> loadList() throws IOException {
        List<String> list = new ArrayList<>();
        SaveList loadList;
        String savelist = "saveliste";
        try {
            FileInputStream fis = new FileInputStream(savelist);
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadList = (SaveList) ois.readObject();
            ois.close();
            fis.close();
            list.addAll(loadList.Savegame);
        } catch (FileNotFoundException e) {
            saveList(new ArrayList<>());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void start(Game admin) throws IOException {
        while (admin.var.gamename == null) {
            List<String> oldsave = loadList();
            if (oldsave.size() > 0) {
                new_old(admin, oldsave);
            } else {
                System.out.println("Entrez le nom de la nouvelle partie : ");
                admin.var.gamename = admin.scanner.nextLine();
            }
        }
    }
    public void firstsave(Game admin) throws IOException {
        List<String> oldsave= loadList();
        oldsave.add(admin.var.gamename);
        saveGame(admin.var);
        saveList(oldsave);
    }
    public void new_old(Game admin,List<String> oldsave) throws IOException {
        if(admin.playerchoiceboolean("Nouvelle partie/Continuer (0:Continuer, 1:Nouvelle partie) : ")) {
            String name = admin.scanner.nextLine();
            for (int i = 0; i < oldsave.size(); i++) {
                System.out.println(i + ": '" + oldsave.get(i)+"'");
            }
            admin.var = loadGame(oldsave.get(admin.playerchoice("Quelle partie charger :",oldsave.size())));
        }else{
            String name = "";
            boolean ok = true;
            while (name == "" || !ok ){
                ok = true;
                System.out.print("Entrez le nom de la nouvelle partie : ");
                name = admin.scanner.nextLine();

                for (int i = 0 ; oldsave.size()>i ; i++){
                    if (oldsave.get(i).equals(name)){
                        if (admin.playerchoiceboolean("Nom déjà utilisé, voulez vous écraser la sauvegarde (0:non, 1:oui) : ")){
                            ok = false;
                        }else if (admin.playerchoiceboolean("Cette décision est définitive, validez vous votre choix (0:non, 1:oui) : ")){
                            ok = false;
                        }
                        else {
                            oldsave.remove(i);
                            saveList(oldsave);
                        }
                    }
                }

            }
            admin.var.gamename = name;
        }
    }

}
