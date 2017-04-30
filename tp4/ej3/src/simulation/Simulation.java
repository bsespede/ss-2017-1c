package simulation;

import java.io.IOException;

import io.OutputWriter;
import simulation.integrator.Integrator;
import simulation.particle.Particle;

public class Simulation {

	private static final double G = 6.693*Math.pow(10.0,-11.0);
	private final SimpleSolarSystem system;
	private final Integrator integrator;
	private final Double interval;
	private Double time = 0.0;
	private final OutputWriter writer;
	private long count = 0;
	private static final String ENERGY_OUTPUT_FILE="system_energy.csv";
	private DlmWriter energyDataWriter;

	public Simulation(SimpleSolarSystem system, Integrator integrator, Double interval, ParticleWriter writer) {
		super();
		this.system = system;
		this.integrator = integrator;
		this.interval = interval;
		this.writer = writer;
		try {
			this.energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public Simulation(Integrator integrator, Double interval, ParticleWriter writer) {
		super();

		String id = "sim";
		Double sunMass = 2 * Math.pow(10, 30);
		Double sunRadius = Math.pow(10, 6);
		Integer initialParticlesCant = 1000;
		Double minDistanceFromSun = Math.pow(10, 9);
		Double maxDistanceFromSun = Math.pow(10, 10);
		Double maxParticleMass = null;
		Double minParticleMass = null;

		this.system = SimpleSolarSystem.randomSolarSysyem(id, sunMass, sunRadius, initialParticlesCant,
				minDistanceFromSun, maxDistanceFromSun, minParticleMass, maxParticleMass);
		this.integrator = integrator;
		this.interval = interval;
		this.writer = writer;
		try {
			this.energyDataWriter=new DlmWriter(ENERGY_OUTPUT_FILE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void simulate(int currentStep) {

		Particle sun = system.getSun();
		system.setParticles(Collider.collisions(system.getParticles(),sun));
		for (Particle particle : system.getParticles()) {
			integrator.next(particle, system.getParticles(), sun, interval);
		}
		if(count % 100 == 0) {
			//count = 0;
			//System.out.println(system.getParticles().size());
			try {
				writer.write(time, sun, system.getParticles());
				double totalKinetic=getTotalKineticEnergy();
				double totalPotential=getTotalPotentialEnergy();
				double totalEnergy=totalKinetic+totalPotential;
				double[][] energyData={{currentStep*interval,totalKinetic,totalPotential,totalEnergy}};
				energyDataWriter.write(energyData, 1, 4);
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		count++;
		time = time + interval;
	}
	
	
	private double getTotalKineticEnergy(){
		double ans=0.0;
		
		for(Particle particle:system.getParticles()){
			ans+=particle.getKineticEnergy();
		}
		return ans;
		
	}
	
	private double getTotalPotentialEnergy(){
		double ans=0.0;
		
		for(Particle particle:system.getParticles()){
			double sunMass=system.getSun().getMass();
			
			ans+=particle.getPotentialEnergy(G, sunMass);
		}
		return ans;		
	}
}
