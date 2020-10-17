<html>
<body>
	<h1>Applications and Services managing system</h1>
	<h1>Main page</h1>
	<hr>

	<h2>Add new application</h2>
	<form action="addApp">
		<label for="name">Name:</label> 
		<input type="text" id="name" name="name"><br> <br> 
		
		<label for="group">Group:</label> 
		<input type="text" id="group" name="group"><br> <br>
		
		<label for="type">Type:</label> <select name="type" id="type">
			<option value="JAVA">java</option>
			<option value="PHP">php</option>
			<option value="BOX">box</option>
			<option value="OS_COMPONENT">os component</option>
			<option value="DATABASE_ENGINE">database engine</option>
		</select><br> <br>
		
		<label for="description">Description:</label> 
		<input type="text" id="description" name="description"><br> <br> 
		
		<label for="cost">Cost:</label> 
		<input type="text" id="cost" name="cost" step=any><br> <br> 
		
		<button type="submit" value="Submit">Submit</button>
		<button type="reset" value="Reset">Reset</button> <br>
	</form>
	<hr>
	
	<h2>Add new service</h2>
	<form action="addService">
		<label for="appCode">App code:</label> 
		<input type="text" id="appCode" name="appCode"><br> <br> 
	
		<label for="name">Name:</label> 
		<input type="text" id="name" name="name"><br> <br> 
		
		<label for="type">Type:</label> 
		<select name="type" id="type">
			<option value="HTTP">HTTP</option>
			<option value="SAML">SAML</option>
			<option value="SSH">SSH</option>
			<option value="JDBC">JDBC</option>
			<option value="ODBC">ODBC</option>
		</select><br> <br>
		
		<label for="subType">Sub type:</label> 
		<select name="subType" id="subType">
			<option value="REST">REST</option>
			<option value="SOAP">SOAP</option>
		</select><br> <br>
		
		<label for="description">Description:</label> 
		<input type="text" id="description" name="description"><br> <br> 
		
		<button type="submit" value="Submit">Submit</button>
		<button type="reset" value="Reset">Reset</button> <br>
	</form>
	<hr>
	
	<h2>Find services for application</h2>
	<form action="findApp">
		<label for="name">Application Name:</label> 
		<input type="text" id="name" name="name"><br> <br> 
		
		<button type="submit" value="Submit">Submit</button>
		<button type="reset" value="Reset">Reset</button> <br>
	</form>
	<hr>
	
	<h2>Find application for service</h2>
	<form action="findService">
		<label for="name">Service name:</label> 
		<input type="text" id="name" name="name"><br> <br> 
		
		<button type="submit" value="Submit">Submit</button>
		<button type="reset" value="Reset">Reset</button> <br>
	</form>
	<hr>

</body>
</html>
