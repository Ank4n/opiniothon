# Instead of building something thats already there, we are focusing on solving simple problems which we have faced from our own experiences and trying to reduce cost and time for last mile delivery businesses by optimizing deliveries.

- ## Problem 1:
**Route Optimization for motor bikes:**
 Google maps do not currently show routes optimized for bikes in India. A local delivery guy may know a better route, and those with knowledge mostly choose to ignore map route. We will keep track of his route, and if his route took less time than the route displayed to the delivery guy, we save that information for showing to delivery guys next time. If we do not have last best route, only route by google api is shown. If a record already exists, we show both routes to the delivery guy.

- ## Problem 2:
**Accuracy of Receivers address:** 
We all have dealt with delivery guys calling and asking for directions to find your home. We are trying to solve this problem by getting feedback from the delivery guy. At the first successful delivery to the reciever, he simply clicks on a button to save the address coordinates and tag it with his given address. The consequent orders will ask the receiver to choose from last successful delivered addresses. If he wants to add a new address, we also give him an option to correctly mark the location so it helps us deliver more efficiently. 


- ## Problem 3:
**Unavailability of Receiver at delivery address:**
We all have faced this issue when delivery guy calls up to deliver your order, but you need to leave your place. Or you are not even at the location when the guy is out to deliver to your address. Its also not very cost efficient to arrive at receiver's door and find out that he is not available. We want to give an optional feature to the consumer to enable live tracking if he thinks he may not be able to receive the order. This enables the app to check if the user is at his given address, and try delivery at that time without annoying him. From the zonal hub to the address on average should not take more than 25-30 minutes and that we think is a very less risky range to attempt delivery. As soon as the delivery is made, tracking stops.