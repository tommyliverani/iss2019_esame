%====================================================================================
% fridge description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxfridge, "localhost",  "MQTT", "0" ).
 qactor( serverproxy, ctxfridge, "it.unibo.serverproxy.Serverproxy").
  qactor( fridge, ctxfridge, "it.unibo.fridge.Fridge").
