Database for UCM Time Tables Project
========================================

* There are 5 tables in this database and their relations are as follows:
* fav[F(user):fav_userid, F(services):fav_serviceid]
* feedback[P:feed_id, F(user):feed_userid, feed_text]
* services[P:service_id, U:service_name]
* times[F(services):time_serviceid, time_opens, time_close, time_day, time_week]
* user[P:user_id, U:user_name, user_pass]

 Some explanation of notation used:
---------------------------------------------------
* P: = "primary key":"attribute"
* F(): = "foreign key" ("of specified table")'s primary key: "attribute" 
* U: = "unique":"attribute"

 Explanation of tables:
--------------------------------------------------------------------
* fav contains the favorites pair matchings between user and service id
* Ex: fav_userid   fav_serviceid  
*		1			2
*		1			3
*		2			1
*		4			2
* This would come to represent user 1 who has services 2 and 3 as favorites,
* user 2 having favorite service 1 and user 4 having service 2 as a favorite.
* feedback contains information about the feedback given by the users and includes
* user id, user comment text, and feedback id i.e. feedback number for reference
* services contains a simplistic lookup table containing service ids and the corresponding service name
* times contains service id, opens, close, when in the day, and what days of the week
* user contains the id associated with each user, their user name, and password

 Explanation of the data in the times table:
---------------------------------------------------------

* currently the only 2 tables with data are services and times
* services is pretty straight forward with id and service name
* times is a little more complicated
* time_serviceid just contains the service id
* time_opens and time_close are integers in military time format
- I found this to be probably the most disambiguous way to represent these times
- There isn't a specific datatype for storing time itself in sqlite
- The best that comes up is datetime but requires a date along with the time and am or pm
- I figured lookups for times and comparing them would be easier using a numeric value
- To further disambiguate the times from am or pm, military time is used
* I found that only the DC has the specific times between evenings, mornings and night
* so for the time_day column here are the breakdowns
- REG represents a standard time format
- SPEC represents a special time format and that only occurs for the taps which is closed during the lunch period
- LUNCH represents lunch time for DC
- BREAKFAST represents breakfast time for DC
- DINNER	represents dinner time for DC
- BRUNCH   reperesents brunch time for DC which is only on saturday and sundays
* time_week attribute can be convoluted as well because there a couple of differences between the services
- M-TH Monday through Thursday
- M-F Monday through Friday
- SAT-SUN Saturday through Sunday
- SAT Saturday
- SUN Sunday
- F Friday
+ as a furthernote TAPS  is the only service in the example services included that has a closing time between the operating hours
+ this closing time is recorded in as time_serviceid time_opens time_close time_day time_week 
+											3 				1300 		1200 	LUNCH 		M-F
