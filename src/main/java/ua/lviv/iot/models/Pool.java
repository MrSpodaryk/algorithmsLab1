package ua.lviv.iot.models;

public class Pool {

    private String address;
    private double capacity;
    private int maxNumberOfVisitors;

    public Pool(String address, double capacity, int maxNumberOfVisitors) {
        this.address = address;
        this.capacity = capacity;
        this.maxNumberOfVisitors = maxNumberOfVisitors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getMaxNumberOfVisitors() {
        return maxNumberOfVisitors;
    }

    public void setMaxNumberOfVisitors(int maxNumberOfVisitors) {
        this.maxNumberOfVisitors = maxNumberOfVisitors;
    }

    @Override
    public String toString() {
        return "Pool{" +
                "address='" + address + '\'' +
                ", capacity=" + capacity +
                ", maxNumberOfVisitors=" + maxNumberOfVisitors +
                '}'+ "\n";
    }
}
