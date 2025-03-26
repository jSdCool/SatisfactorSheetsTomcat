# Satisfactor Sheets Tomcat
___
A front end implementation of [Satisfactor sheets](https://github.com/jSdCool/SatisFactorSheets) for an Apache tomcat webserver

## setup
1. Clone this repo into your tomcat webapps folder
2. Download a compatible version of Satisfactor Sheets from [here](https://github.com/jSdCool/SatisFactorSheets/releases)
3. Place the jar file you downloaded in `WEB-INF/lib` for this web app
4. Compile the class files at `WEB-INF/classes`. Make sure to include the servlet-api jar and the version of satisfactor Sheets you downloaded on the class path  

example compile command:
```shell
#lunix/mac
javac -cp "/path/to/servlet-api.jar:/path/to/SatrifactorSheets.jar" GenerateSpreadsheetServlet.java
```
```cmd
rem windows
javac -cp "/path/to/servlet-api.jar;/path/to/SatrifactorSheets.jar" GenerateSpreadsheetServlet.java
```
Note: you will have to compile each class file individually  
Note: SatrifactorSheets.jar is compiled with java version 17 by default, tomcat will need to be running in java 17 or later to make use of this project

### Compatible Satisfactor Sheets versions: [1.1.0 - ]