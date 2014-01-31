#!/bin/bash
# Antes de executar esse script, iniciar o servidor via:
# $ sh ~/dev/tools/wildfly/bin/standalone.sh
echo "*******************************************************************************"
echo "Livraria"
echo -e "*******************************************************************************\n"
mvn clean compile package install -P wildfly
echo -e "\nA aplicacao ja pode ser acessada pelo endereco http://localhost:8080/livraria/"
