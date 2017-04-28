package simulator;


import general.BeemanParticle;
import general.GearParticle;
import general.VerletParticle;
import io.FileProcessor;

/**
 * Created by julian on 26/04/17.
 */
public class Simulator {

    private double tf;
    private VerletParticle pv;
    private BeemanParticle pb;
    private GearParticle pg;
    private double currentTime = 0;
    private double deltaT = 0.01;

    public Simulator (double mass, double k, double g, double tf){
        this.tf = tf;
        pv = new VerletParticle(0, mass, 1, -g/2, deltaT, k, g);
        pb = new BeemanParticle(0, mass, 1, -g/2, deltaT, k, g);
        pg = new GearParticle(0, mass, 1, -g/2, deltaT, k, g);

    }

    public void simulate() {
        while(currentTime < tf){
            pv.move();
            pb.move();
            pg.move();
            currentTime += deltaT;
            FileProcessor.writeOutputParticlesFile(pv, "output1.txt");
            FileProcessor.writeOutputParticlesFile(pb, "output2.txt");
            FileProcessor.writeOutputParticlesFile(pg, "output3.txt");
        }
    }
}
