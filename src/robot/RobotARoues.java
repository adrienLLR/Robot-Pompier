package robot;
import environment.Carte;
import environment.Case;
import environment.Direction;
import environment.NatureTerrain;

public class RobotARoues extends Robot{
    /**
    * Creer un Robot à roues
    * @param c Case sur ou positionner le Robot (inutil de preciser une Nature)
    */
    public RobotARoues(Case c){
        super(c, "image/roues.png");
        this.v = 80;
        this.reservoir = getMaxCapaciteReservoir();
    }
    /** Renvoie la vitesse a laquelle le robot évolue sur un terrain n
     * Si return 0; alors le terrain n'est pas praticable pour le robot
     * @param n Nature du terrain sur lequel on souhaite connaitre la vitesse
    */
    public double getVitesse(NatureTerrain n){
        switch(n){
            case TERRAIN_LIBRE:
                return this.v;
            case HABITAT:
                return this.v;
            default:
                return 0;
        }
    }

    /** Donne le temps de remplissage complet du reservoir */
    public double getTempsRemplissage(){
        return 10F;
    }

    /** Donne le debit du robot en L/s */
    public double getExtinctionRatio(){
        return 100/5;
    }

    /** Indique si un remplissage est possible pour la case actuel 
     * @param c Carte sur lequel evolu le robot
     * @return Renvoi true si le remplissage est possible sinon false 
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
        return 5000F;
    }
    
    /** Donne le type de robot sous forme de chaine de caractere*/
    public String getTypeRobot(){
        return "Robot à roues";
    }
}
