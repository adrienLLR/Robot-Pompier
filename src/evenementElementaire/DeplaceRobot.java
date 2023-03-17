package evenementElementaire;

import environment.Carte;
import environment.Direction;
import robot.Robot;

public class DeplaceRobot extends Evenement{
    
    private Direction direction;
    private Robot r;
    private Carte c;
    private boolean premierDeplacement = true;
    private long initdate;

    /**Evenment de deplacement élementaire, debute a date et se fini apres la traverser de la case actuel
     * @param r robot a deplacer
     * @param d direction du déplacement
     * @param date date a laquel effectuer l'evenement
     * @param c carte ou se passe le deplacement
     */
    public DeplaceRobot(Robot r, Direction d, long date, Carte c){
        super ( date ) ;
        this.initdate = date;
        this.direction = d;
        this.r = r;
        this.c = c;
    }
    
    public void execute() {
        //Si on execute pour la premiere fois et que l'on peut se déplacer sur la case suivante
        if(this.c.getVoisin(r.getPosition(), this.direction) == null){
            return ;
        }
        if(premierDeplacement && r.getVitesse(this.c.getVoisin(r.getPosition(), this.direction).getNature()) != 0F){
            this.date += getTimeToDo();
            premierDeplacement = false;
        }
        else{
            r.setPosition(this.c.getVoisin(r.getPosition(), this.direction));
        }
    }

    public void resetEvent(){
        date = initdate;
        premierDeplacement = true;
    }


    @Override
    public String toString() {
        return "Event : Deplace Robot : " + direction + " at " + this.date;
    }

    public long getTimeToDo(){
        if(this.c.getVoisin(r.getPosition(), this.direction) == null){
            return 0;
        }
        if (r.getVitesse(this.c.getVoisin(r.getPosition(), this.direction).getNature()) == 0F){
            return 0;
        }
        return c.getTailleCases() / (long) (r.getVitesse(r.getPosition().getNature())/3.6);
    }
}
