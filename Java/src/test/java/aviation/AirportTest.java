package aviation;

import aviation.planes.ExperimentalPlane;
import aviation.Airport;
import aviation.models.PrivacyLevel;
import aviation.models.ExperimentalPlaneType;
import aviation.models.MilitaryPlaneType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import aviation.planes.MilitaryPlane;
import aviation.planes.PassengerPlane;
import aviation.planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static Airport airport;

    @BeforeTest
    public void fillAirport() {
        airport = new Airport(Arrays.asList(
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
                new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryPlaneType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryPlaneType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryPlaneType.BOMBER),
                new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryPlaneType.FIGHTER),
                new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryPlaneType.FIGHTER),
                new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryPlaneType.TRANSPORT),
                new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalPlaneType.HIGH_ALTITUDE, PrivacyLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalPlaneType.VTOL, PrivacyLevel.TOP_SECRET)
        ));
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        boolean areAllReceivedPlanesTransportMilitary = false;

        for (MilitaryPlane militaryPlane : airport.getTransportMilitaryPlanes()) {
            if ((militaryPlane.getType() == MilitaryPlaneType.TRANSPORT)) {
                areAllReceivedPlanesTransportMilitary = true;
                break;
            }
        }
        Assert.assertTrue(areAllReceivedPlanesTransportMilitary);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PassengerPlane expectedPlaneWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
        PassengerPlane actualPlaneWithMaxPassengersCapacity = airport.getMaxCapacityPassengerPlane();
        Assert.assertEquals(expectedPlaneWithMaxPassengerCapacity, actualPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        boolean isNextPlaneMaxLoadCapacityHigherThanCurrent = true;

        for (int i = 1; i < planesSortedByMaxLoadCapacity.size(); i++) {
            if (planesSortedByMaxLoadCapacity.get(i).getMaxLoadCapacity() < planesSortedByMaxLoadCapacity.get(i-1).getMaxLoadCapacity()) {
                isNextPlaneMaxLoadCapacityHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(isNextPlaneMaxLoadCapacityHigherThanCurrent);
    }

    @Test
    public void testGetBomberMilitaryPlanes() {
        boolean areAleReceivedPlanesBomberMilitary = true;
        for (MilitaryPlane militaryPlane : airport.getBomberMilitaryPlanes()) {
            if (militaryPlane.getType() == MilitaryPlaneType.BOMBER) {
                areAleReceivedPlanesBomberMilitary = false;
                break;
            }
        }
        Assert.assertTrue(areAleReceivedPlanesBomberMilitary);
    }


}
