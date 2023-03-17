package robot;
import environment.Carte;
import environment.Case;
import environment.Direction;
import environment.NatureTerrain;

/* Classe Robot à chenilles hérité de robot
 * Dans les fichier : PATTES
*/

public class RobotAPattes extends Robot{
    /**
    * Creer un Robot à pattes
    * @param c Case sur ou positionner le Robot (inutil de preciser une Nature)
    */
    public RobotAPattes(Case c){
        super(c, "image/pattes.png");
        this.v = 30;
        this.reservoir = getMaxCapaciteReservoir();
    }
    
    /** Renvoie la vitesse a laquelle le robot évolue sur un terrain n
     * Si return 0; alors le terrain n'est pas praticable pour le robot
     * @param n Nature du terrain sur lequel on souhaite connaitre la vitesse
    */
    public double getVitesse(NatureTerrain n){
        switch(n){
            case EAU:
                return 0;
            case ROCHE:
                return 10;
            default:
                return this.v;
        }
    }

    /** Donne le temps de remplissage complet du reservoir */   
    public double getTempsRemplissage(){
        return 0F;
    }

    /** Donne le debit du robot en L/s */
    public double getExtinctionRatio(){
        return 10/1;
    }

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param c  : Carte sur lequel evolu le robot
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
        return Double.POSITIVE_INFINITY;
    }

    /** Donne le type de robot sous forme de chaine de caractere*/
    public String getTypeRobot(){
        return "Robot à pattes";
    }
}
