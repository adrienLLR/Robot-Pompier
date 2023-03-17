package evenementElementaire;

import environment.Carte;
import robot.Robot;

public class RemplirRobot extends Evenement{
    private Robot r;
    private Carte c;
    private boolean premierDeplacement = true;
    private long initdate;
    /**Evenement qui rempli le robot si il est vide et sur la bonne case
     * @param r robot a remplir
     * @param date date de remplissage
     * @pararm c carte ou ce passe la simulation.
     */
    public RemplirRobot(Robot r, long date, Carte c){
        super ( date ) ;
        this.initdate = date;
        this.r = r;
        this.c = c;
    }
    
    public void execute() {
        if(premierDeplacement){
            if(r.remplissageEstPossible(c,r.getPosition())){
                this.date += getTimeToDo();
                premierDeplacement = false;
            }
        }
        else{
            r.remplirReservoir(c);
        }
    }

    @Override
    public String toString() {
        return "Event : Deverser eau sur feu: " + r + " at " + this.date;
    }

    public long getTimeToDo(){
        if(!r.remplissageEstPossible(c,r.getPosition())){
            return 0;
        }
        return (long) r.getTempsRemplissage();
    }
    public void resetEvent(){
        date = initdate;
    }

}
