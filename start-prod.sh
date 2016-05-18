
STAGE="prod"

echo "-------------------"
echo "STAGE=$STAGE"
echo "-------------------"

sbt stage

./target/universal/stage/bin/play-scala -Dconfig.resource=application-prod.conf -J-server -J-Xms250m -J-Xmx250m -J-XX:+UseConcMarkSweepGC -J-XX:+CMSParallelRemarkEnabled -J-XX:+ScavengeBeforeFullGC -J-XX:+CMSScavengeBeforeRemark -Dsun.net.inetaddr.ttl=60
