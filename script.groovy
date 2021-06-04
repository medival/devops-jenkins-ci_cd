def sendTeleMessage(message){
  sh "curl -s -X POST https://api.telegram.org/bot1751001936:AAFc7JQBsl_Qj7G7Ej_0JYJXmBJymOiLG2U/sendMessage -d chat_id=583270547 -d text='${message}'"
}

return this