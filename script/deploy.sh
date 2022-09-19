

ENVIRON="prod"

line=$(grep -n "ENVIRON" .env | head -n 1 | cut -d: -f1)
sed -i "$line c\ENVIRON=$ENVIRON" .env


docker-compose -f docker-compose.yml up --build --remove-orphan