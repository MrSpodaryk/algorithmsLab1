package ua.lviv.iot.manager;

import ua.lviv.iot.models.Pool;

import java.util.ArrayList;
import java.util.List;

public class PoolManager {

    private int numberOfComparisonsInSelectionSort = 0;
    private int numberOfExchangesInSelectionSort = 0;
    static long timeOfMergeSort;

    public List selectionSortPoolsByDecreaseOfCapacity(List<Pool> listOfPools) {

        final long startTime = System.nanoTime();

        System.out.println("Selection Sort By Decrease Of Capacity");


        for (int i = 0; i < listOfPools.size(); i++) {

            double max = listOfPools.get(i).getCapacity();
            int maxIndex = i;

            for (int j = i + 1; j < listOfPools.size(); j++) {
                this.numberOfComparisonsInSelectionSort++;
                if (max < listOfPools.get(j).getCapacity()) {
                    max = listOfPools.get(j).getCapacity();
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                this.numberOfExchangesInSelectionSort++;
                Pool tempo = listOfPools.get(i);
                listOfPools.set(i, listOfPools.get(maxIndex));
                listOfPools.set(maxIndex, tempo);
            }
        }

        System.out.println((System.nanoTime() - startTime) + " наносекунд");
        System.out.println("Number Of Comparisons = " + this.numberOfComparisonsInSelectionSort);
        System.out.println("Number Of Exchanges = " + this.numberOfExchangesInSelectionSort);

        return listOfPools;
    }

    public List mergeSortByDecreaseOfVisitors(List<Pool> listOfPools) {

        final long startTimeOfMerge = System.nanoTime();

        if (listOfPools.size() <= 1) {
            return listOfPools;
        }

        int midpoint;
        List<Pool> leftPartOfList = new ArrayList<>();
        List<Pool> rightPartOfList = new ArrayList<>();

        if (listOfPools.size() % 2 == 0) {
            midpoint = listOfPools.size() / 2;
            for (int j = 0; j < midpoint; j++) {
                rightPartOfList.add(listOfPools.get(midpoint + j));
            }
        } else {
            midpoint = (listOfPools.size() - 1) / 2;
            for (int j = 0; j <= midpoint; j++) {
                rightPartOfList.add(listOfPools.get(midpoint + j));
            }
        }

        for (int i = 0; i < midpoint; i++) {
            leftPartOfList.add(listOfPools.get(i));
        }

        List<Pool> resultListOfPools;

        leftPartOfList = mergeSortByDecreaseOfVisitors(leftPartOfList);
        rightPartOfList = mergeSortByDecreaseOfVisitors(rightPartOfList);

        resultListOfPools = merge(leftPartOfList, rightPartOfList);

        long duration = System.nanoTime() - startTimeOfMerge;

        return resultListOfPools;

    }

    private List merge(List<Pool> leftPart, List<Pool> rightPart) {

        List<Pool> resultListOfPools = new ArrayList<>();

        int leftPointer, rightPointer;
        leftPointer = rightPointer = 0;

        while (leftPointer < leftPart.size() || rightPointer < rightPart.size()) {

            if (leftPointer < leftPart.size() && rightPointer < rightPart.size()) {

                if (leftPart.get(leftPointer).getMaxNumberOfVisitors() > rightPart.get(rightPointer).getMaxNumberOfVisitors()) {

                    resultListOfPools.add(leftPart.get(leftPointer++));
                } else {

                    resultListOfPools.add(rightPart.get(rightPointer++));
                }

            } else if (leftPointer < leftPart.size()) {

                resultListOfPools.add(leftPart.get(leftPointer++));

            } else if (rightPointer < rightPart.size()) {

                resultListOfPools.add(rightPart.get(rightPointer++));
            }
        }
        return resultListOfPools;
    }

    public static void main(String[] args) {

        PoolManager poolManager = new PoolManager();
        ArrayList<Pool> listOfPools = new ArrayList();

        listOfPools.add(new Pool("Bandery 21", 400, 50));
        listOfPools.add(new Pool("Horodotska 11", 2000, 400));
        listOfPools.add(new Pool("Varshavska 321", 1400, 340));
        listOfPools.add(new Pool("Mazepy 1", 50, 30));
        listOfPools.add(new Pool("Polya 22", 100, 25));

        List sortedListOfPools1 = poolManager.selectionSortPoolsByDecreaseOfCapacity(listOfPools);
        System.out.println(sortedListOfPools1);

        System.out.println();

        System.out.println("Merge Sort By Decrease Of Visitors");
        List sortedListOfPools2 = poolManager.mergeSortByDecreaseOfVisitors(listOfPools);
        System.out.println(sortedListOfPools2);


    }
}
