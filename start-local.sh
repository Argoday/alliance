
STAGE="local"

echo "-------------------"
echo "STAGE=$STAGE"
echo "-------------------"

sbt run -Dconfig.resource=application-local.conf
