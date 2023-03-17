package evenementElementaire;

/* Classe Evenement */
public abstract class Evenement implements Comparable{
    
    protected long date;
    protected Evenement eventAExecuter;
    /** Creer un nouvel evenement qui doit se produire a date
     * @param date     Date a laquelle se produit l'evenement
     */
    public Evenement(long date){
        this.date = date;
    }

    /** Getter de Date */
    public long getDate() {
        return date;
    }
    
    /** Executer l'evenement*/
    public abstract void execute();

    /**temps d'excution des evenements elementaire, -1 pour les evenements avancer  */
    public abstract long getTimeToDo();

    /**Reset l'evenement a ses valeurs par defauts */
    public abstract void resetEvent();

    public int compareTo(Object o) {
        if (o instanceof Evenement){
            Evenement e = (Evenement) o;
            if (e.date == this.date){
                return 0;
            }
            else if (e.date > this.date){
                return -1;
            }
            else{
                return 1;
            }
        }
        return -1;
    }

    protected void LancerEvenementEnAttente(){
        if (eventAExecuter != null){
            eventAExecuter.execute();
        }
    }

    protected boolean LancerEvenementPuisLeMettreEnAttente(Evenement e){
        e.execute();
        if (e.getTimeToDo() != 0){
            this.eventAExecuter = e;
            this.date += eventAExecuter.getTimeToDo();
            return true;
        }
        return false;
    }

}
