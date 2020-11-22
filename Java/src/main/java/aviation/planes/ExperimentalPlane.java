package aviation.planes;

import aviation.planes.properties.PrivacyLevel;
import aviation.planes.properties.ExperimentalPlaneType;

public class ExperimentalPlane extends Plane{

    private final ExperimentalPlaneType type;
    private PrivacyLevel privacyLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalPlaneType type, PrivacyLevel privacyLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.privacyLevel = privacyLevel;
    }

    public PrivacyLevel getPrivacyLevel(){
        return privacyLevel;
    }

    public void setPrivacyLevel(PrivacyLevel privacyLevel){
        this.privacyLevel = privacyLevel;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + type + ", privacyLevel="+privacyLevel+
                        '}');
    }
}
