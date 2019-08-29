package ua.lviv.iot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.manager.PoolManager;
import ua.lviv.iot.models.Pool;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class PoolManagerTest {

    private List<Pool> listOfPools = new ArrayList();

    private PoolManager poolManager = new PoolManager();

    private ArrayList listOfPoolsCapacity = new ArrayList();

    @BeforeEach
    public void setUp() {

        listOfPools.add(new Pool("Bandery 21", 400, 50));
        listOfPools.add(new Pool("Horodotska 11", 2000, 400));
        listOfPools.add(new Pool("Varshavska 321", 1400, 340));
        listOfPools.add(new Pool("Mazepy 1", 50, 10));
        listOfPools.add(new Pool("Polya 22", 100, 20));
    }

    @Test
    void testSelectionSortPoolsByDecreaseOfCapacity() {

        List<Pool> sortedListOfPools = poolManager.selectionSortPoolsByDecreaseOfCapacity(listOfPools);
        Assertions.assertEquals(2000.0, sortedListOfPools.get(0).getCapacity());
        Assertions.assertEquals(1400.0, sortedListOfPools.get(1).getCapacity());
        Assertions.assertEquals(400.0, sortedListOfPools.get(2).getCapacity());
        Assertions.assertEquals(100.0, sortedListOfPools.get(3).getCapacity());
        Assertions.assertEquals(50.0, sortedListOfPools.get(4).getCapacity());
    }
}
