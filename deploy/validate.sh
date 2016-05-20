
echo "Waiting for webserver to start up"
until $(curl --output /dev/null --silent --head --fail http://localhost:9000/ping); do
  printf '.'
  sleep 2
done
echo "Finished waiting for webserver to start up"
