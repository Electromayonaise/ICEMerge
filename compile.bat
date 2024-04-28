@echo off
.\gradlew build
.\gradlew :primaryServer:build  
.\gradlew :client:build
.\gradlew :helperServer:build