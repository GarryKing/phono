@echo off
cd ..
call mvn clean eclipse:clean eclipse:eclipse -DdownloadSources=true -U

@pause