# Toll Parking Library

RESTFull api to provide a static park service where car can check in and check out. 
A toll parking contains multiple parking slots of different types :
- the standard parking slots for sedan cars (gasoline-powered)
- parking slots with 20kw power supply for electric cars
- parking slots with 50kw power supply for electric cars

20kw electric cars cannot use 50kw power supplies and vice-versa.


## Build

As Maven Project, build It and export as runnable jar (call It for example toolparking.jar) 

To run the api just use:

```
java -jar toolparking.jar port
```

It will start on the machine and on the port you provide as parameter

## Usage

The api provide three built-in parking:
- "Parking 1" with code "PRK1" has 10 dieselSlot, 20 e20kwSlot, 30 e50kwSlot (Pay for hour)
- "Parking 2" with code "PRK2" has 15 dieselSlot, 10 e20kwSlot, 15 e50kwSlot (Pay for hour + fixed amount)
- "Parking 3" with code "PRK3" has 25 dieselSlot, 15 e20kwSlot, 10 e50kwSlot (Pay for hour)

###Check-in

PUT request
http://ipaddress:port/park/checkIn/parkCode with Car as JSON body of the request

For example:

```
http://127.0.0.1:8081/park/checkIn/PRK1

where car is
{   
    "type" : "DIESEL", // DIESEL,E20KW, E50KW
    "plate" : "AX909AC" // unique identifier of the car
}
```

The response will be a Slot if one is available, NOT_FOUND web response otherwise

```
Example of Slot:
{
    "number": 1,
    "type": "DIESEL",
    "car": {
        "type": "DIESEL",
        "plate": "aDKAKKi"
    },
    "dateIn": "2019-10-01T21:30:20+02:00"
}
```


###Check-out

PUT request
http://ipaddress:port/park/checkOut/parkCode with Car as JSON body of the request

For example:

```
http://127.0.0.1:8081/park/checkOut/PRK1

where car is
{   
    "type" : "DIESEL", // DIESEL,E20KW, E50KW
    "plate" : "AX909AC" // unique identifier of the car
}
```

The response will be a Bill with the totalAmount to pay

```
Example of Bill:
{
    "totalAmount": 2
}
```


### Built With

* [Jersey](https://eclipse-ee4j.github.io/jersey/) - REST framework that provides a JAX-RS (JSR-370)    implementation and more
* [Maven](https://maven.apache.org/) - Dependency Management
