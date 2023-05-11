#!/usr/bin/env bash

if [ -z "$1" ] || [ -z "$2" ]; then
  echo "Usage: load.sh FOLDER USER:PASSWORD"
  exit 1
fi

readonly FOLDER="$1"
readonly CREDENTIALS="$2"
readonly SERVER="localhost:8080"
readonly TMP_CURL_FILE=".tmp_curl_out"

for file in $FOLDER/*; do
  entity=$(basename -- $file)
  url="$SERVER/api/data/load/${entity%.*}"

  curl -X POST $url \
    --user $CREDENTIALS \
    --header "Content-Type: text/csv" \
    --data "$(cat $file)" \
    -o /dev/null -s -w "%{http_code}\n" > $TMP_CURL_FILE

  echo "POST $url - $(cat $TMP_CURL_FILE)"
  [ $(cat $TMP_CURL_FILE) == "200" ] || echo 1
done

curl -X GET $SERVER/api/data/save \
  --user $CREDENTIALS \
  -o /dev/null -s -w "%{http_code}\n" > $TMP_CURL_FILE

echo "GET  $SERVER/api/data/save - $(cat $TMP_CURL_FILE)"
[ $(cat $TMP_CURL_FILE) == 200 ] || echo 1

rm $TMP_CURL_FILE
