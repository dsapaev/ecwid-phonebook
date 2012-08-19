@echo off
set LIBDIR=%~dp0/lib
set DRIVER=org.firebirdsql.jdbc.FBDriver
set URL="jdbc:firebirdsql:localhost/3050:c:/work/db/base.FDB"
set USER=SYSDBA
set PSWD=masterkey
@rem src/main/resources здесь для того, чтобы liquibase мог найти changelog-файлы
set CLASSPATH=src/main/resources;%LIBDIR%/jaybird-full-2.0.1.jar
set CHGLOGFILE=com/ecwidtest/phonebook/server/db/changelog/db.changelog-master.xml
@echo on

java -jar %LIBDIR%/liquibase.jar --driver=%DRIVER% --classpath=%CLASSPATH% --changeLogFile=%CHGLOGFILE% --url=%URL% --username=%USER% --password=%PSWD% update