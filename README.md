# Backend Setup
- Clone this project
- Delete the .git folder and use "git init"
- Create your OWN repository for this project on github
- Go to travis and add your new repository by "flipping the switch" (Synchronize if now shown)
- Add credentials: "REMOTE_USER" + "script_user" and "REMOTE_PW" + password (values can be found in your "my-bootstrap.sh")
- Commit and Push your code to this repository
- Create databases by either only creating these 2 exacly as they are:
CA3
CA3_test
- or choose your own name for db but then you will have to change the following files:
- Travis.yml: Line 40
- Other Sources: src/main/resources: <default package>: config.properties: Change db name at line 17 and 21.
- run "mvn clean test" in terminal: fix problems if any appears.
 
# Backend Deploy
- Change the remote.server url to your own domain/droplet
Project Files: Pom.xml: <remote.server>https://leafmight.dk/manager/text</remote.server> 

- Run "ssh DropletUserName@domain" followed by "sudo nano /opt/tomcat/bin/setenv.sh"

- change USER, PW and CONNECTION(startcode) to your own values:

export DEPLOYED="DEV_ON_DIGITAL_OCEAN"

export USER="YOUR_DB_USER"

export PW="YOUR_DB_PASSWORD"

export CONNECTION_STR="jdbc:mysql://localhost:3306/startcode"

- Save file: "cntrl + x" and "Enter"
- Restart tomcat "sudo systemctl restart tomcat"

- you can run the following with your own user / pw info: 
mvn clean test -Dremote.user=script_user -Dremote.password=PW_FOR_script_user tomcat7:deploy

- If everything was fine the project should be deployed to your droplet, ready to use with the remote database.
- Otherwise you should be able to just commit and push changes to github and travis should deploy it for you.


