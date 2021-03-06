@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  productx startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and PRODUCTX_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\gs-accessing-data-mongodb-0.1.0.jar;%APP_HOME%\lib\spring-boot-starter-web-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-rest-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-mongodb-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-integration-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-integration-java-dsl-1.0.2.RELEASE.jar;%APP_HOME%\lib\commons-collections4-4.0.jar;%APP_HOME%\lib\commons-csv-1.1.jar;%APP_HOME%\lib\commons-beanutils-1.9.2.jar;%APP_HOME%\lib\commons-lang3-3.0.jar;%APP_HOME%\lib\google-api-services-customsearch-v1-rev46-1.20.0.jar;%APP_HOME%\lib\JRI-0.9-6.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.2.5.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.4.6.jar;%APP_HOME%\lib\hibernate-validator-5.1.3.Final.jar;%APP_HOME%\lib\spring-web-4.1.7.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-4.1.7.RELEASE.jar;%APP_HOME%\lib\jackson-annotations-2.4.6.jar;%APP_HOME%\lib\spring-tx-4.1.7.RELEASE.jar;%APP_HOME%\lib\spring-data-rest-webmvc-2.2.3.RELEASE.jar;%APP_HOME%\lib\mongo-java-driver-2.12.5.jar;%APP_HOME%\lib\spring-data-mongodb-1.6.3.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-messaging-4.1.7.RELEASE.jar;%APP_HOME%\lib\spring-integration-core-4.1.5.RELEASE.jar;%APP_HOME%\lib\spring-integration-file-4.1.5.RELEASE.jar;%APP_HOME%\lib\spring-integration-http-4.1.5.RELEASE.jar;%APP_HOME%\lib\spring-integration-ip-4.1.5.RELEASE.jar;%APP_HOME%\lib\spring-integration-stream-4.1.5.RELEASE.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar;%APP_HOME%\lib\google-api-client-1.20.0.jar;%APP_HOME%\lib\snakeyaml-1.14.jar;%APP_HOME%\lib\tomcat-embed-core-8.0.23.jar;%APP_HOME%\lib\tomcat-embed-el-8.0.23.jar;%APP_HOME%\lib\tomcat-embed-logging-juli-8.0.23.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.0.23.jar;%APP_HOME%\lib\jackson-core-2.4.6.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.1.3.GA.jar;%APP_HOME%\lib\classmate-1.0.0.jar;%APP_HOME%\lib\spring-data-rest-core-2.2.3.RELEASE.jar;%APP_HOME%\lib\json-patch-1.7.jar;%APP_HOME%\lib\slf4j-api-1.7.12.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.12.jar;%APP_HOME%\lib\spring-data-commons-1.9.3.RELEASE.jar;%APP_HOME%\lib\aspectjrt-1.8.6.jar;%APP_HOME%\lib\aspectjweaver-1.8.6.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\google-oauth-client-1.20.0.jar;%APP_HOME%\lib\google-http-client-jackson2-1.20.0.jar;%APP_HOME%\lib\guava-jdk5-13.0.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.12.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.12.jar;%APP_HOME%\lib\logback-classic-1.1.3.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\spring-hateoas-0.16.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-core-1.1.0.RELEASE.jar;%APP_HOME%\lib\evo-inflector-1.2.jar;%APP_HOME%\lib\jackson-coreutils-1.6.jar;%APP_HOME%\lib\jsr305-2.0.1.jar;%APP_HOME%\lib\google-http-client-1.20.0.jar;%APP_HOME%\lib\logback-core-1.1.3.jar;%APP_HOME%\lib\objenesis-2.1.jar;%APP_HOME%\lib\msg-simple-1.1.jar;%APP_HOME%\lib\guava-16.0.1.jar;%APP_HOME%\lib\httpclient-4.0.1.jar;%APP_HOME%\lib\btf-1.2.jar;%APP_HOME%\lib\httpcore-4.0.1.jar;%APP_HOME%\lib\commons-codec-1.3.jar;%APP_HOME%\lib\spring-boot-starter-actuator-1.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-1.2.7.RELEASE.jar;%APP_HOME%\lib\jsoup-1.8.3.jar;%APP_HOME%\lib\reactor-core-1.1.4.RELEASE.jar;%APP_HOME%\lib\spring-retry-1.1.1.RELEASE.jar;%APP_HOME%\lib\gs-collections-5.1.0.jar;%APP_HOME%\lib\disruptor-3.2.1.jar;%APP_HOME%\lib\jsr166e-1.0.jar;%APP_HOME%\lib\gs-collections-api-5.1.0.jar;%APP_HOME%\lib\spring-context-4.1.8.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.1.8.RELEASE.jar;%APP_HOME%\lib\spring-core-4.1.8.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.1.8.RELEASE.jar;%APP_HOME%\lib\spring-aop-4.1.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-1.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-1.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.2.7.RELEASE.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.2.7.RELEASE.jar

@rem Execute productx
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %PRODUCTX_OPTS%  -classpath "%CLASSPATH%" com.analytique.config.ApplicationConfig %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable PRODUCTX_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%PRODUCTX_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
