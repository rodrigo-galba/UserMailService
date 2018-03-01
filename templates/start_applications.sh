#!/bin/bash
mailcatcher --ip 0.0.0.0 &
sudo service postgresql start
java -jar {{ project_path }}/services/config-server/target/configuration-service-0.0.1-SNAPSHOT.jar &
java -jar {{ project_path }}/services/mail-service/target/mailapp-0.0.1-SNAPSHOT.jar &
java -jar {{ project_path }}/target/userapp-0.0.1-SNAPSHOT.jar &