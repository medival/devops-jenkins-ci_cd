def sendTeleMessage(message){
  sh "curl -s -X POST https://api.telegram.org/bot${TELE_SECRET}/sendMessage -d chat_id=${TELE_USER_ID} -d text='${message}'"
}

return this