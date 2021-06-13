This Spring boot project has been implemented using gradle
==========================================================

Guide Lines
==========
1. gradle clean build -x test   --> [requires  Gradle 6.8.x or 7.x]
     if gradle is not configured run
        * ******************************
        *  gradlew clean build -x test *
        ********************************
2.cd home-test-allion\build\libs

3 java -jar  issuetracker-0.0.1-SNAPSHOT.jar

4 API Information
   Request url :http://localhost:9092/allion/issue/summary/weeks
   Request Body : {
                    "project_id":,
                    "from_week": "2017W01",
                    "states": [
                      "open"
                    ],
                    "to_week": "2017W15",
                    "types": [
                      "bug"
                    ]
                  }
   Note : Any rest client can be used or else can be consumed via swagger url as well --> http://localhost:9092/swagger-ui.html
          Mongo database is used in this project. "allion_home_test", localhost are the values for  database and  url respectively.if the database and url need to be changed by configuring the application.yml accordingly
          third party issue tracking url should be configured in yml and current value is http://localhost:8080/v1/getIssues
