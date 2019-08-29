package ua.lviv.iot.manager;

import ua.lviv.iot.models.Pool;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoolManager {

    private int numberOfComparisonsInSelectionSort = 0;
    public int numberOfComparisonsInMergeSort = 0;
    private int numberOfExchangesInSelectionSort = 0;
    public long startTimeOfMerge;
    public boolean check = false;
    public int key1 = 0;
    public int key2 = 0;

    public List selectionSortPoolsByDecreaseOfCapacity(List<Pool> listOfPools) {

        final long startTime = System.nanoTime();

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

        if (check != true) {
            key1 = listOfPools.size();
            startTimeOfMerge = System.nanoTime();
            check = true;
        }

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

        key2++;

        if (key2 == key1 - 1) {
            final long duration = System.nanoTime() - startTimeOfMerge;
            System.out.println(duration + " наносекунд");
        }

        return resultListOfPools;
    }

    private List merge(List<Pool> leftPart, List<Pool> rightPart) {

        List<Pool> resultListOfPools = new ArrayList<>();

        int leftPointer, rightPointer;
        leftPointer = rightPointer = 0;

        while (leftPointer < leftPart.size() || rightPointer < rightPart.size()) {

            if (leftPointer < leftPart.size() && rightPointer < rightPart.size()) {

                numberOfComparisonsInMergeSort++;

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
        List<Pool> listOfPools = new ArrayList();

        List list = new ArrayList();
        String s = new String();
        try (FileReader reader = new FileReader("text.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                s += (char) c;
            }

            for (String retval : s.split(", ")) {
                list.add(retval);
            }
            for (int i = 0; i < list.size(); i += 3) {
                listOfPools.add(new Pool(list.get(i).toString(), Integer.parseInt(list.get(i + 1).toString()), Integer.parseInt(list.get(i + 2).toString())));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Selection Sort By Decrease Of Capacity");
        List sortedListOfPools1 = poolManager.selectionSortPoolsByDecreaseOfCapacity(listOfPools);
        System.out.println(sortedListOfPools1);

        System.out.println();

        System.out.println("Merge Sort By Decrease Of Visitors");
        List sortedListOfPools2 = poolManager.mergeSortByDecreaseOfVisitors(listOfPools);
        System.out.println("Number Of Comparisons In MergeSort = " + poolManager.numberOfComparisonsInMergeSort);
        System.out.println(sortedListOfPools2);
    }
}
