#!/bin/bash
#script pour executer l'algorithme

#Etape-1 : traduction du fichier au format souhaité
java -cp "out/production/tp_datamining/" HearthStoneSpmfCodeAndDecode $1 $3

#application de l'algorithme
java -jar spmf.jar run $2 "convertis/format_SPMF_$3" "resultats/$4" "$5%"

#lecture du résultat
echo ""
echo "Résultats : "
echo ""
cat "resultats/$4"

echo ""
