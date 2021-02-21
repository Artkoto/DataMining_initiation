#!/bin/bash
#script pour executer l'algorithme
format="spmf"
if [[ -z $3 ]]
then
    echo "Syntaxe incorrecte."
    echo "respecter la sytaxe suivante : nomAlgorithme fichierDeDonnes fichierDeSortie pourcentage "
    echo "ou nomAlgorithme fichierDeDonnes pourcentage (dans ce cas la sortie par defaut est
    nomAlgorithme_fichierDeDonnes.txt)"
    echo "exmple : LCM all_absolute+.txt result1 10 ou  LCM all_absolute+.txt 10 "

#si le dernier param n'est pas compris en 0 et 100 on ne peut pas executer
elif  [[  (-z $4 ) &&  ( ( ! $3 -ge 0)  ||   ( ! $3 -le 100) )  ]]
then
  echo " entrez une valeur numérique en 0 et 100 pour le procentage. "

elif [[ (-z $4)  &&  ( $3 -ge 0 ) && ($3 -le 100) ]]

 then
    #Etape-1 : traduction du fichier au format souhaité
    java -cp "out/production/tp_datamining/" HearthStoneSpmfCodeAndDecode $format $2

    #application de l'algorithme
    java -jar spmf.jar run $1 "convertis/format_SPMF_$2" "resultats/$1_$2" "$3%"
    reslt="resultats/$1_$2"

elif [[ ( ( ! $4 -ge 0)  ||   ( ! $4 -le 100) ) ]]
then
      echo " entrez une valeur numérique en 0 et 100 pour le procentage. "

else
  #Etape-1 : traduction du fichier au format souhaité
  java -cp "out/production/tp_datamining/" HearthStoneSpmfCodeAndDecode $format $2

  #application de l'algorithme
  java -jar spmf.jar run $1 "convertis/format_SPMF_$2" "resultats/$3" "$4%"
  reslt="resultats/$3"


fi

#lecture du résultat
if [ ! -z $reslt  ]
 then
    echo ""
  echo "Résultats : "
  echo ""
  cat "$reslt"

echo ""
fi
