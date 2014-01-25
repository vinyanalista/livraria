#!/bin/bash
echo "*******************************************************************************"
echo "Livraria"
echo -e "*******************************************************************************\n"
mvn clean compile package
mvn glassfish:redeploy
mvn glassfish:deploy
echo -e "\nA aplicacao ja pode ser acessada pelo endereco http://localhost:8080/livraria/"
