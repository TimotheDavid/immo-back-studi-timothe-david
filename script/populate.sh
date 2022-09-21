ENVIRON="populate"

mvn test  -Dspring.profiles.active=$ENVIRON -Dtest="PopulateTest"

