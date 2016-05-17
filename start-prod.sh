
STAGE="prod"

echo "-------------------"
echo "STAGE=$STAGE"
echo "-------------------"

sbt stage

./target/universal/stage/bin/play-scala -J-server -J-Xms400m -J-Xmx400m -J-XX:+UseConcMarkSweepGC -J-XX:+CMSParallelRemarkEnabled -J-XX:+ScavengeBeforeFullGC -J-XX:+CMSScavengeBeforeRemark -Dsun.net.inetaddr.ttl=60
