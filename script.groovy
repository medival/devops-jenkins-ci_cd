def sendTeleMessage(message){
  sh "curl -s -X POST https://api.telegram.org/bot${TELE_SECRET}/sendMessage -d chat_id=${TELE_USER_ID} -d text='${message}'"
}

def registerDomain(){
  sh "curl --location --request POST 'https://api.cloudflare.com/client/v4/zones/${CF_ZONE}/dns_records' \
    --header 'X-Auth-Email: ${CF_EMAIL}' \
    --header 'X-Auth-Key: ${CF_AUTH_KEY}' \
    --header 'Content-Type: application/json' \
    --data-raw '{ \"type\": \"A\", \"name\": \"${APP_NAME}.${CF_MAIN_DOMAIN}\", \"content\": \"${SERVER_IP}\", \"ttl\": 1, \"priority\": 10, \"proxied\": true }'"
}

return this