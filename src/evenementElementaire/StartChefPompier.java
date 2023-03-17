package evenementElementaire;

import robot.Chefpompier;
import simulation.Simulateur;

public class StartChefPompier extends Evenement{

    Chefpompier chefpomp;
    Simulateur sim;
    /**Evenement qui lance le chef pompier
     * @param sim Simulation ou le chef pompier va inervenir
     * @param date date d'intervention du chef pompier
     */
    public StartChefPompier(Simulateur sim, long date){
        super(date);
        this.chefpomp = new Chefpompier(sim.getCarte(), sim.getRobots(), sim.getIncendie(), sim);
        this.sim = sim;
        sim.setChef(chefpomp);
    }

    @Override
    public void execute() {
        chefpomp.AdvancedFireExtinguisher();
    }

    @Override
    public long getTimeToDo() {
        return 0;
    }

    @Override
    public void resetEvent() {
        this.chefpomp = new Chefpompier(sim.getCarte(), sim.getRobots(), sim.getIncendie(), sim);
        sim.setChef(chefpomp);
    }
    
}
