package aviation;

import aviation.planes.ExperimentalPlane;
import aviation.models.PrivacyLevel;
import aviation.models.ExperimentalPlaneType;
import aviation.models.MilitaryPlaneType;
import com.sun.xml.internal.ws.binding.FeatureListUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import aviation.planes.MilitaryPlane;
import aviation.planes.PassengerPlane;
import aviation.planes.Plane;
import org.testng.collections.CollectionUtils;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AirportTest {

    private Airport airport;

    public List<Plane> bomberMilitaryPlanes = Arrays.asList(
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryPlaneType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryPlaneType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryPlaneType.BOMBER)
    );
    public List<Plane> transportMilitaryPlanes = Arrays.asList(
            new MilitaryPlane("B-1C Mengeer", 1050, 20000, 79000, MilitaryPlaneType.TRANSPORT),
            new MilitaryPlane("B-73 Avenger", 1000, 21000, 75000, MilitaryPlaneType.TRANSPORT)
    );
    public List<Plane> fighterMilitaryPlanes = Arrays.asList(
            new MilitaryPlane("ABC-13", 1000, 20000, 65000, MilitaryPlaneType.FIGHTER),
            new MilitaryPlane("G-41 Launcher", 1080, 19000, 70000, MilitaryPlaneType.FIGHTER)
    );
    public List<Plane> experimentalPlanes = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalPlaneType.HIGH_ALTITUDE, PrivacyLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalPlaneType.VTOL, PrivacyLevel.TOP_SECRET)
    );
    public List<Plane> passengerPlanes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196)
    );


    @BeforeTest
    public void fillAirport() {
        List<Plane> planesList = new ArrayList<>(passengerPlanes);
        planesList.addAll(bomberMilitaryPlanes);
        planesList.addAll(transportMilitaryPlanes);
        planesList.addAll(fighterMilitaryPlanes);
        planesList.addAll(experimentalPlanes);
        airport = new Airport(planesList);
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        List<Plane>expectedTransportMilitaryPlanes = transportMilitaryPlanes;
        List<MilitaryPlane>actualTransportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        Assert.assertTrue(expectedTransportMilitaryPlanes.size() == actualTransportMilitaryPlanes.size() && expectedTransportMilitaryPlanes.containsAll(actualTransportMilitaryPlanes) && actualTransportMilitaryPlanes.containsAll(expectedTransportMilitaryPlanes));
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
        List<Plane>expectedBomberMilitaryPlanes = bomberMilitaryPlanes;
        List<MilitaryPlane>actualBomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        Assert.assertTrue(expectedBomberMilitaryPlanes.size() == actualBomberMilitaryPlanes.size() && expectedBomberMilitaryPlanes.containsAll(actualBomberMilitaryPlanes) && actualBomberMilitaryPlanes.containsAll(expectedBomberMilitaryPlanes));
    }

}
