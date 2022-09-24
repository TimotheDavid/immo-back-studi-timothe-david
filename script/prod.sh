
ENVIRON=prod
mvn package  -Dspring.profiles.active=$ENVIRON -Dmaven.test.skip -Dserver.port=8080

line=$(grep -n "ENVIRON" .env | head -n 1 | cut -d: -f1)
sed -i "$line c\ENVIRON=$ENVIRON" .env

docker-compose -f docker-compose-prod.yml up --build  --remove-orphan