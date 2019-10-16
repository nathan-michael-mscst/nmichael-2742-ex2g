package com.example.nmichael2742ex2g1;


import java.util.ArrayList;
import java.util.Objects;

public class Sensor {
    private int sensorId;
    private int roomId;
    private int sensorTypeId;
    private String description;
    private ArrayList<SensorReading> sensorReadings;

    public Sensor(int sensorId, int roomId, int sensorTypeId, String description) {
        this.sensorId = sensorId;
        this.roomId = roomId;
        this.sensorTypeId = sensorTypeId;
        this.description = description;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return sensorId == sensor.sensorId &&
                roomId == sensor.roomId &&
                sensorTypeId == sensor.sensorTypeId &&
                Objects.equals(description, sensor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, roomId, sensorTypeId, description);
    }

    @Override
    public String toString() {
        return Integer.toString(sensorId) +
                ", roomId=" + roomId +
                ", sensorTypeId=" + sensorTypeId +
                ", " + description;
    }

    public ArrayList<SensorReading> getSensorReadings() {
        return sensorReadings;
    }

    public void setSensorReadings(ArrayList<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }


    public int findMinReadingIndex() {
        int minIndex = findMinReadingIndex(0, this.sensorReadings.size()- 1);
        return minIndex;
    }


    public int findMinReadingIndex(int startIndex, int endIndex) {
        float minReading = this.sensorReadings.get(startIndex).getValue();  // Starts with value in startIndex being lowest
        int minIndex = startIndex;                                          // startIndex set to min
        if (startIndex < 0 || endIndex >= this.sensorReadings.size() || startIndex >= endIndex)
            throw new IndexOutOfBoundsException(
                    "startIndex: " + startIndex + " | endIndex: " + endIndex + " | Out Of Bounds (0 - " +
                            (this.sensorReadings.size()-1) + ")");
        for(int i = startIndex + 1; i <= endIndex; i++) {                   // Check each reading against minReading in range of startIndex to endIndex
            if (this.sensorReadings.get(i).getValue() < minReading) {       // If reading is lower than minReading
                minReading = this.sensorReadings.get(i).getValue();         // minReading is assigned new value to check others against
                minIndex = i;                                               // Corresponding index set to min
            }
        }
        return minIndex;
    }


    public int findMaxReadingIndex() {
        int maxIndex = findMaxReadingIndex(0, this.sensorReadings.size()-1);
        return maxIndex;
    }


    public int findNextCycleMinIndex(int startIndex) {
        SensorReading rMin = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for ( ; i < this.sensorReadings.size(); i++) {
            if (rMin.getValue() > this.sensorReadings.get(i).getValue())
                rMin = this.sensorReadings.get(i);
            else
                break;
        }
        return i - 1;
    }


    public int findMaxReadingIndex(int startIndex, int endIndex) {
        float maxReading = this.sensorReadings.get(startIndex).getValue();  // Starts with value in startIndex being highest
        int maxIndex = startIndex;                                          // startIndex set to max
        if (startIndex < 0 || endIndex >= this.sensorReadings.size() || startIndex >= endIndex)
            throw new IndexOutOfBoundsException(
                    "startIndex: " + startIndex + " | endIndex: " + endIndex + " | Out Of Bounds (0 - " +
                            (this.sensorReadings.size()-1) + ")");
        for(int i = startIndex + 1; i <= endIndex; i++) {                   // Check each reading against minReading in range of startIndex to endIndex
            if (this.sensorReadings.get(i).getValue() > maxReading) {       // If reading is higher than maxReading
                maxReading = this.sensorReadings.get(i).getValue();         // maxReading is assigned new value to check others against
                maxIndex = i;                                               //  assign corresponding index as max
            }
        }
        return maxIndex;
    }


    public int findNextCycleMaxIndex(int startIndex) {
        SensorReading rMax = this.sensorReadings.get(startIndex);
        int i = startIndex + 1;

        for ( ; i < this.sensorReadings.size(); i++) {
            if (rMax.getValue() < this.sensorReadings.get(i).getValue())
                rMax = this.sensorReadings.get(i);
            else
                break;
        }
        return i - 1;
    }


    public SensorReading getSensorReading(int index) {
        return this.sensorReadings.get(index);
    }
}