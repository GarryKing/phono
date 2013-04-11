@echo off
::-----------------------------
::repackage phono-server
::-----------------------------
cd d:
cd \
cd "D:\hezeqing\myproject\studyproj\github\phono\phono-server\"
rd /s /q target
md target
cd ..
call mvn clean package -DskipTests=true

::-----------------------------
::clean phono-server in tomcat
::-----------------------------
cd \
cd "D:\java_tools\tomcat7\webapps"
rd /s /q  ROOT
del /s /q  ROOT.war

::-----------------------------
::copy war to tomcat
::-----------------------------
copy "D:\hezeqing\myproject\studyproj\github\phono\phono-server\target\phono-server.war" "D:\java_tools\tomcat7\webapps"

::-----------------------------
::rename war
::-----------------------------
cd \
cd D:\java_tools\tomcat7\webapps
ren phono-server.war ROOT.war

::-----------------------------
::start tomcat
::-----------------------------
cd \
cd "D:\java_tools\tomcat7\bin"
catalina.bat jpda start