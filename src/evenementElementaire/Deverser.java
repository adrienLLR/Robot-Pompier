package evenementElementaire;

import robot.Robot;

public class Deverser extends Evenement{

    private Robot r;
    private long initdate;
    private boolean premierDeplacement = true;
    private long timeToDo = 0;

    /**Evenement de deversement élementaire, debute a date et se fini une fois le reservoir rempli
     * @param r robot a deplacer
     * @param date date a laquel effectuer l'evenement
     */
    public Deverser(Robot r, long date){
        super ( date ) ;
        initdate = date;
        this.r = r;

        //On regarge si incendie a etteindre
        if(r.getPosition().getIncendie() != null && !r.getPosition().getIncendie().estEteind()){
            timeToDo = r.getTimeExtinguish(r.getPosition().getIncendie());
        }
    }
    
    public void execute() {
        if(timeToDo == 0){
            return ;
        }
        if(premierDeplacement){
            this.date += timeToDo;
            premierDeplacement = false;
        }
        else{
            if (r.getPosition().getIncendie() != null){
                r.deverserEeau(r.getReservoir());
            }
        }
    }

    public void resetEvent(){
        date = initdate;
        premierDeplacement = true;
    }

    @Override
    public String toString() {
        return "Event : Deverser eau sur feu: " + r + " at " + this.date;
    }

    public long getTimeToDo(){
        return timeToDo;
    }
    
}
