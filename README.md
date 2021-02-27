# tp_DataMining

## Consigne d'utilisation
Ce programme fonction sous le système linux.
Avant tout exécution, assurez-vous d'activer l'autorisation aux fichiers start.sh et compile.sh à s'exécuter comme programmes

pour installer le programme lancer la commande :
```bash
./compile.sh
```

Pour exécuter le programme vous avez deux options 

Option1:
```bash
./start.sh  nomAlgorithme fichierDeDonnes fichierDeSortie pourcentage
```
Exemple
```bash
./start.sh LCM all_absolute+.txt result1.txt 3
```

Option2:
```bash
./start.sh  nomAlgorithme fichierDeDonnes pourcentage
```
Exemple
```bash
./start.sh LCM all_absolute+.txt 3
```
Dans le cas de l'option 2, le fichier de sortie est sous la forme nomAlgorithme_fichierDeDonnes

## Où trouver les fichiers ?

```mermaid
graph TD;
   Jeux de données-->datas;
 
```

