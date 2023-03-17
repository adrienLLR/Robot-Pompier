package robot;

import environment.Carte;
import environment.Case;
import environment.Direction;
import environment.NatureTerrain;

/* Classe Robot à chenilles hérité de robot
 * Dans les fichier : CHENILLES
*/

public class RobotAChenilles extends Robot{
    /**
    * Creer un Robot à chenilles
    * @param c Case sur ou positionner le Robot (inutil de preciser une Nature)
    **/
    public RobotAChenilles(Case c){
        super(c, "image/chenilles.png");
        this.v = 60;
        this.reservoir = getMaxCapaciteReservoir();
    }
    /**
     * Creer un Robot à chenilles
     * @param v vitesse du robot
     * @param c Case sur ou positionner le Robot (inutil de preciser une Nature)
     */
    public RobotAChenilles(double v, Case c){
        super(c, "image/chenilles.png");
        this.v = 60;
        if (v <= 80 && v >= 0){
            this.v = v;
        }
        this.reservoir = getMaxCapaciteReservoir();
    }

    /** Renvoie la vitesse a laquelle le robot évolue sur un terrain n
     * Si return 0; alors le terrain n'est pas praticable pour le robot
     * @param n Nature du terrain sur lequel on souhaite connaitre la vitesse
    */
    public double getVitesse(NatureTerrain n){
        switch(n){
            case FORET:
                return this.v/2;
            case EAU:
                return 0;
            case ROCHE:
                return 0;
            default:
                return this.v;
        }
    }

    /** Donne le temps de remplissage complet du reservoir */
    public double getTempsRemplissage(){
        return 5F;
    }

    /* Donne le debit du robot en L/s */
    public double getExtinctionRatio(){
        return 100/8;
    }

    /* Indique si un remplissage est possible pour la case actuel 
     * @param c Carte sur lequel evolu le robot
    */
    public boolean remplissageEstPossible(Carte carte, Case casee){
        if (casee.getNature() == NatureTerrain.EAU){
            return false;
        }
        Direction d[] = {Direction.NORD, Direction.EST, Direction.SUD, Direction.OUEST};
        for (Direction dir : d){
            if(carte.voisinExiste(casee, dir)){
                if (carte.getVoisin(casee, dir).getNature() == NatureTerrain.EAU){
                    return true;
                }
            }
        }
        return false;
    }

    /** Donne la capacité max du reservoir en L */
    public double getMaxCapaciteReservoir(){
        return 2000F;
    }
    
    /** Donne le type de robot sous forme de chaine de caractere*/
    public String getTypeRobot(){
        return "Robot à chenille ";
    }
}
