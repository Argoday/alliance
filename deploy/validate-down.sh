
echo "Waiting for webserver to tear down"
while $(curl --output /dev/null --silent --head --fail http://localhost:9000/ping); do
  printf '.'
  sleep 2
done
echo "."
echo "Finished waiting for webserver to tear down"
