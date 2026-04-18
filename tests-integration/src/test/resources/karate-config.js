function fn() {
  var config = {
    clienteServiceUrl: karate.properties['clienteServiceUrl'] || 'http://localhost:8080',
    transaccionServiceUrl: karate.properties['transaccionServiceUrl'] || 'http://localhost:8081'
  };
  return config;
}