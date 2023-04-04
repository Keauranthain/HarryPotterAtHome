package Data.Admin;

import Data.Character.Wizard;
import Data.Room.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Variable implements Serializable {
    public List<Room> roomList = new ArrayList<>();
    public int idRoom = 1;
    public Wizard wizard;
    public int dechet;

    //Story boolean #######################################################################################################################
    public boolean patronumSet;
    public boolean StartMail;
    public boolean mailtoVernon;
    public boolean end;
    public boolean sleep;
    public boolean bank;
    public boolean cloth;
    public boolean book;
    public boolean sleepB;
    public boolean departure;
    public boolean mailtoPoudlard;
    public boolean sleapstart;
    public int action;
    public boolean act;
    public boolean Alohomora;
    public boolean quirelldead;
    public boolean secretchamber;
    public boolean basilicdead;
    public boolean forbiddenspell;
    public String gamename;
    public int stage;
    public boolean enddream;

    public Variable(){
        this.wizard=new Wizard();
    }
    //Day avancement ######################################################################################################
    //A1
    public boolean A1fn;
    public boolean A1d1;
    public boolean A1d2;
    public boolean A1d3;
    public boolean A1d4;
    //A2
    public boolean A2fn;
    public boolean A2d1;
    public boolean A2d2;
    public boolean A2d3;
    public boolean A2d4;
    //A3
    public boolean A3fn;
    public boolean A3d1;
    public boolean A3d2;
    public boolean A3d3;
    public boolean A3d4;

}
