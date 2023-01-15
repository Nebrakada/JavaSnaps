package com.pawelpluta.day013

import spock.lang.Shared
import spock.lang.Specification

import static com.pawelpluta.day013.FuelType.PETROL

class CarTest extends Specification {

    Integer NO_PASSENGERS = 0
    Integer FOUR_SEATS = 4
    @Shared
    Integer TEN_LITERS = 10
    Integer FIFTY_LITERS = 50
    Integer EXAMPLE_MILEAGE = 50

    def "car should inform about its mileage in kilometers"() {
        given:
        Integer mileage = 10000
        Car car = new Car(NO_PASSENGERS, FOUR_SEATS, mileage, TEN_LITERS, FIFTY_LITERS, PETROL)

        expect:
        car.mileageInKm() == mileage
    }


    def "should be able to add passengers upto car's limit"() {
        given:
        Car car = new Car(NO_PASSENGERS, FOUR_SEATS, EXAMPLE_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)
        for (i in 0..<numberOfPassengersToAdd) {
            car = car.addPassenger()
        }

        expect:
        car.passengers() == expectedNumberOfPassengers

        where:
        numberOfPassengersToAdd | expectedNumberOfPassengers
        1                       | 1
        2                       | 2
        3                       | 3
        4                       | 4
        5                       | 4
        6                       | 4
    }

    def "should only fuel with propert fuel type and cannot overflow tank"() {
        given:
        Car car = new Car(NO_PASSENGERS, FOUR_SEATS, EXAMPLE_MILEAGE, TEN_LITERS, FIFTY_LITERS, PETROL)
        car = car.refuel(fuelType, fuelToTank)

        expect:
        car.fuelInTank() == expectedFuelInTank

        where:
        fuelType        | fuelToTank | expectedFuelInTank
        FuelType.CNG    | TEN_LITERS | TEN_LITERS
        FuelType.DIESEL | TEN_LITERS | TEN_LITERS
        FuelType.LPG    | TEN_LITERS | TEN_LITERS
        FuelType.PETROL | TEN_LITERS | 20
        FuelType.PETROL | 50         | TEN_LITERS

    }
}
