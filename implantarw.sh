#!/bin/bash
# Antes de executar esse script, iniciar o servidor e fazer deploy do driver JDBC do PostgreSQL
echo "*******************************************************************************"
echo "Livraria"
echo -e "*******************************************************************************\n"
mvn clean compile package install -P wildfly
echo -e "\nA aplicacao ja pode ser acessada pelo endereco http://localhost:8080/livraria/"
