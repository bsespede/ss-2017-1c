package io;

import particle.Particle;

import java.util.Set;

/**
 * Created by julian on 17/03/17.
 */
public class InputPackage {
    private Set<Particle> p;
    private int L;

    public InputPackage(Set<Particle> p, int l) {
        this.p = p;
        L = l;
    }

    public Set<Particle> getP() {
        return p;
    }

    public void setP(Set<Particle> p) {
        this.p = p;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }
}
