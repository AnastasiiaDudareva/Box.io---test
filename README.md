# Box.io---test
Developer Challenge
General notes:

Please write clean code that you will feel comfortable reviewing with other developers

You may use external libraries as needed but not ones that provide the core functionality

The challenge should take around 4-6 hours to complete

Box.io
We're making a new company that gives away boxes.  The user can sign up for a subscription to receive their box monthly free of charge.  They can choose between a small cube (15 cm x 15 cm x 15 cm), a medium rectangular box (100 cm x 25 cm x 25 cm), or a large cube (75 cm x 75 cm x 75 cm).
The small box comes in the following colors: red, blue, and yellow.
The medium box comes in the following colors: red, yellow, purple, and green.
The large box comes in the following colors: green, orange, and blue.
Lastly, the user can choose whether or not to have their name printed on top of the box.
At a minimum, we'll need to store the user's name, email address, and any box configurations parameters on our server.  Don't worry - the server isn't real in this demo so all network communications can be mocked and defined by you.
Tasks
- Create a native Android app using Java that allows a user to enter the required user information and select a box and color combination.  Include a sign up button (or similar).  Only a single view is needed, but more can be used if you prefer.
- Define the JSON to hold the user's profile information.
- Create a mock of the network function that validates the new user and returns a valid user JSON or an error signifying a missing field, invalid field, or invalid box/color combination.
- Create a translator to go from a user/box/color JSON to a user/box/color object(s).
- Show an error or success message depending on the user's submitted profile.
- Include basic field validation
- Use Dagger 2
Bonus
- Use dependency injection.
- Create unit tests to test your functionality.
- Make user/box/color object(s) thread safe.
- Use background threads to run the network and logic commands.
- Allow the box/color combinations to be customizable from a file.
- Create a network function mock that allows the app to query the server for an existing user based on the user's email.
- Use a local database mock to create and store users and their box selections
- Add a timestamp field to the user's profile to record when the user's profile last changed
- Add a userInfo field to store arbitrary string data on the user's profile
Thank you for taking this coding challenge and good luck!
