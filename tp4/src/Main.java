import simulator.Simulator;

/**
 * Created by julian on 26/04/17.
 */
public class Main {

    public static void main(String[] args) {
        Simulator s = new Simulator(70, 10000, 100, 5);
        s.simulate();
    }
}
