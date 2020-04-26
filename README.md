# innovsolvtech-offline-assessment
Assignment for Lisa Muir Innovsolvtech for Discovery

Application: ShortestDistancePath
Purpose : to find out the distance between any two planets (source and distance)
========================================================================================

Package Structure
---------------------
package com.doncorp.innovsolvtechofflineassessment.algorithm--> Dijkstra algorithm to find out shortest path
package com.doncorp.innovsolvtechofflineassessment.controller --> Rest Controllers for CRUD operations and 
					to load the UI and deligate the request to the service and repository.
package com.doncorp.innovsolvtechofflineassessment.service --> Service classes.
package com.doncorp.innovsolvtechofflineassessment.repository --> repository classes 
						and CRUD operational methods for graph domain object.
package com.doncorp.innovsolvtechofflineassessment.model--> entities goes here.

data file
------------------

	/resources folder 
src/main/resources/Worksheet-in-HR-OffsiteAssignmentV30.xlsx


Frontend pages
-----------------
html files place under the resources/templates


to start the application 
--------------------------
DemoApplication .java ---> starting point of the service 
run this class to read the excel file and persist with H2 Database on application start up.
so that data is ready after start up.

URLs to the load the UI page 
-----------------------------
http://localhost:8080/ --> it loads the frontend page where you can select source and destination planet

on submission, the URI /findshortestpath will be called and displays the result.

and there is Back button to resubmit the page with difference planets.

Adding Data into in H2 in memory DB
-- the data is loaded using Commandline runner for the three worksheets using the apache poi library
-- run the application either from within the IDE or using maven from the commandline using mvn clean install and check the
data on the following url: http://localhost:8080/h2
supply the following credentials: username: sa
                                  password:password
Run the following commands
select * from planet; to check planet list
select * from route; to check route list
select * from planet;
