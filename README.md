# SpringReactiveWebFlux




Build the Employee Project, get the Jar from target folder



Start the Server:
----------------------
start the server in local terminal by executing the jar

java -jar employee.jar

-> in runs on the port 8088


http://localhost:8088/employees


-> To know which port is using the application
lsof -i :8088 | grep LISTEN
java      930 srinivaspanaganti   12u  IPv6 0xfb61f9ce35114649      0t0  TCP *:radan-http (LISTEN)


-> To stop the process
kill -9 930





End Points - for Employee Server
-----------------------------------

1) http://localhost:8088/employees

2) http://localhost:8088/employees/find/2

3) http://localhost:8088/employees/add
{"firstName":"Sam","designation":"Senior Developer","salary":"95000"}

4)  http://localhost:8088/employees

5) http://localhost:8088/employees/update
{"firstName":"Vennela","designation":"Senior Developer","salary":"95000"}

6) http://localhost:8088/employees/delete/5


7)http://localhost:8088/employees/update/1
{"firstName":"Sree","designation":"Senior Developer","salary":"95000"}


http://localhost:8088/employees/update/1

{"firstName":"Vennela","designation":"Senior Developer","salary":"95000"}





Employee EndPoint Constants
----------------------------

/employees

/employees/find/{employeeId}

/employees/add

/employees/update/{employeeId}

/employees/delete/{employeeId}
