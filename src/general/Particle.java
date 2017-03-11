package general;


public class Particle {

    private float x;
    private float y;
    private float radius;
    private float integrationRadius;

    public Particle(float x, float y, float radius, float integrationRadius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.integrationRadius = integrationRadius;
    }

    public Particle() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getIntegrationRadius() {
        return integrationRadius;
    }

    public void setIntegrationRadius(float integrationRadius) {
        this.integrationRadius = integrationRadius;
    }

    @Override
    public int hashCode() {
        //not efficient but works
        return Float.hashCode(x + y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Particle.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Particle other = (Particle) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
