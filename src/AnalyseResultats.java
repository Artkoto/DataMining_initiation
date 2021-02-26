import java.io.*;
import java.util.*;

/**
 * Annalyse des resultats apres application de l'algorithme spmf
 * @author Yao Arnaud Akoto
 * @author kassim kaba
 *
 */
public class AnalyseResultats {
    /**
     * association pouvoir-hero
     */
//    static Map<String,String> pouvoirHero = new HashMap<>(){{
//        put("Armor Up!","warrior");
//        put("Dagger Mastery", "Rogue");
//        put("Fireblast", "Mage");
//        put("Lesser Heal", "Priest");
//        put("Life Tap", "Warlock");
//        put("Reinforce", "Paladin");
//        put("Shapeshift", "Druid");
//        put("Steady Shot", "Hunter");
//        put("Totemic Call", "Shaman");
//    }};
    //les differents itemsets par dimension
    static  Set<String> itemSetD1 = new HashSet<>();
    static  Set<String> itemSetD2 = new HashSet<>();
    static  Set<String> itemSetD3 = new HashSet<>();
    static  Set<String> itemSetD4 = new HashSet<>();
    static  Set<String> itemSetD5 = new HashSet<>();
    static  Set<String> itemSetDPlus = new HashSet<>();
    //itemset par hero
    static  Set<String> itemSetWarrio = new HashSet<>();
    static  Set<String> itemSetRogue = new HashSet<>();
    static  Set<String> itemSetMage = new HashSet<>();
    static  Set<String> itemSetPriest = new HashSet<>();
    static  Set<String> itemSetWarlock = new HashSet<>();
    static  Set<String> itemSetPaladin = new HashSet<>();
    static  Set<String> itemSetDruid = new HashSet<>();
    static  Set<String> itemSetHunter = new HashSet<>();
    static  Set<String> itemSetShaman = new HashSet<>();
    //cartes par héos
    static  Set<String> cartesWarrio = new HashSet<>();
    static  Set<String> cartesRogue = new HashSet<>();
    static  Set<String> cartesMage = new HashSet<>();
    static  Set<String> cartesPriest = new HashSet<>();
    static  Set<String> cartesWarlock = new HashSet<>();
    static  Set<String> cartesPaladin = new HashSet<>();
    static  Set<String> cartesDruid = new HashSet<>();
    static  Set<String> cartesHunter = new HashSet<>();
    static  Set<String> cartesShaman = new HashSet<>();
    //cartes spécifiques héros
    static  Set<String> specificCartesWarrio = new HashSet<>();
    static  Set<String> specificCartesRogue = new HashSet<>();
    static  Set<String> specificCartesMage = new HashSet<>();
    static  Set<String> specificCartesPriest = new HashSet<>();
    static  Set<String> specificCartesWarlock = new HashSet<>();
    static  Set<String> specificCartesPaladin = new HashSet<>();
    static  Set<String> specificCartesDruid = new HashSet<>();
    static  Set<String> specificCartesHunter = new HashSet<>();
    static  Set<String> specificCartesShaman = new HashSet<>();
    //cartes communes à tous
    static  Set<String> cartesCommunes = new HashSet<>();
    //nombre de participarions par classe
    static  int nbTotal=0;
    static  int nbMage=0;
    static  int nbWarrio=0;
    static  int nbRogue=0;
    static  int nbPriest=0;
    static  int nbWarlock=0;
    static  int nbPaladin=0;
    static  int nbDruid =0;
    static  int nbHunter=0;
    static  int nbShaman=0;


    static void decoup(String nomFile){
        try {
            String path = "resultats/"+nomFile ;
            FileReader file = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(file);
            String contenuLigne ;
            while ((contenuLigne = bufferedReader.readLine()) != null){
                //mettre dans itemSet en fonction de taille
                int taille = contenuLigne.split(" ").length - 2;
                switch (taille){
                    case 1:
                        itemSetD1.add(contenuLigne);
                        break;
                    case 2:
                        itemSetD2.add(contenuLigne);
                        break;
                    case 3:
                        itemSetD3.add(contenuLigne);
                        break;
                    case 4:
                        itemSetD4.add(contenuLigne);
                        break;
                    case 5:
                        itemSetD5.add(contenuLigne);
                        break;
                    default:
                        itemSetDPlus.add(contenuLigne);
                }
                //en fonction des héros
                if (taille >=2) {
                    if (contenuLigne.toLowerCase().contains("ArmorUp!".toLowerCase())) {
                        itemSetWarrio.add(contenuLigne);
                        cartesWarrio.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0,contenuLigne.split(" ").length - 2));
                        cartesWarrio.remove("ArmorUp!");
                    }

                    if (contenuLigne.toLowerCase().contains("DaggerMastery".toLowerCase())) {
                        itemSetRogue.add(contenuLigne);
                        cartesRogue.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0,contenuLigne.split(" ").length - 2));
                        cartesRogue.remove("DaggerMastery");
                    }

                    if (contenuLigne.toLowerCase().contains("Fireblast".toLowerCase())) {
                        itemSetMage.add(contenuLigne);
                        cartesMage.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0, contenuLigne.split(" ").length - 2));
                        cartesMage.remove("Fireblast");
                    }

                    if (contenuLigne.toLowerCase().contains("LesserHeal".toLowerCase())) {
                        itemSetPriest.add(contenuLigne);
                        cartesPriest.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0,contenuLigne.split(" ").length - 2));
                        cartesPriest.remove("LesserHeal");
                    }

                    if (contenuLigne.toLowerCase().contains("LifeTap".toLowerCase())) {
                        itemSetWarlock.add(contenuLigne);
                        cartesWarlock.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0, contenuLigne.split(" ").length - 2));
                        cartesWarlock.remove("LifeTap");
                    }

                    if (contenuLigne.toLowerCase().contains("Reinforce".toLowerCase())) {
                        itemSetPaladin.add(contenuLigne);
                        cartesPaladin.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0, contenuLigne.split(" ").length - 2));
                        cartesPaladin.remove("Reinforce");
                    }

                    if (contenuLigne.toLowerCase().contains("Shapeshift".toLowerCase())) {
                        itemSetDruid.add(contenuLigne);
                        cartesDruid.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0, contenuLigne.split(" ").length - 2));
                        cartesDruid.remove("Shapeshift");
                    }

                    if (contenuLigne.toLowerCase().contains("SteadyShot".toLowerCase())) {
                        itemSetHunter.add(contenuLigne);
                        cartesHunter.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0,contenuLigne.split(" ").length - 2));
                        cartesHunter.remove("SteadyShot");
                    }

                    if (contenuLigne.toLowerCase().contains("TotemicCall".toLowerCase())) {
                        itemSetShaman.add(contenuLigne);
                        cartesShaman.addAll(Arrays.asList(contenuLigne.split(" ")).subList(0, contenuLigne.split(" ").length - 2));
                        cartesShaman.remove("TotemicCall");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //MAJ du nombre de partie
        for (String s : itemSetD1){
            String[] sDecoup = s.split(" ");
            int support = Integer. parseInt(sDecoup[2]) ;
            switch (sDecoup[0].toLowerCase()){
                case "armorup!":
                    nbWarrio = support;
                    nbTotal+=support;
                    break;
                case "daggermastery":
                    nbRogue =support;
                    nbTotal+=support;
                    break;
                case "fireblast":
                    nbMage =support;
                    nbTotal+=support;
                    break;
                case "lesserheal":
                    nbPriest =support;
                    nbTotal+=support;
                    break;
                case "lifetap":
                    nbWarlock =support;
                    nbTotal+=support;
                    break;
                case "reinforce":
                    nbPaladin =support;
                    nbTotal+=support;
                    break;
                case "shapeshift":
                    nbDruid =support;
                    nbTotal+=support;
                    break;
                case "Steadyshot":
                    nbHunter=support;
                    nbTotal+=support;
                    break;
                case "totemiccall":
                    nbShaman +=support;
                    nbTotal+=support;
                    break;
                default:
                    break;
            }
        }
        //MAJ des cartes spécifiques aux decks

        for (String s : cartesWarrio){
            if (cartesRogue.contains(s)||
                cartesMage.contains(s)||
                cartesPriest.contains(s)||
                cartesWarlock.contains(s)||
                cartesPaladin.contains(s)||
                cartesDruid.contains(s)||
                cartesHunter.contains(s)||
                cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesWarrio.add(s);
        }
        for (String s : cartesRogue ){
            if (cartesWarrio.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesRogue.add(s);
        }
        for (String s : cartesMage ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesMage.add(s);
        }
        for (String s : cartesPriest ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesPriest.add(s);
        }
        for (String s : cartesWarlock ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesWarlock.add(s);
        }
        for (String s : cartesPaladin ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesPaladin.add(s);
        }
        for (String s : cartesDruid ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesHunter.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesDruid.add(s);
        }
        for (String s : cartesHunter ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesShaman.contains(s))
                cartesCommunes.add(s);
            else specificCartesHunter.add(s);
        }
        for (String s : cartesShaman ){
            if (cartesWarrio.contains(s)||
                    cartesRogue.contains(s)||
                    cartesMage.contains(s)||
                    cartesPriest.contains(s)||
                    cartesWarlock.contains(s)||
                    cartesPaladin.contains(s)||
                    cartesDruid.contains(s)||
                    cartesHunter.contains(s))
                cartesCommunes.add(s);
            else specificCartesShaman.add(s);
        }
    }

    static void ecrireAnalyse(String nomFile) throws FileNotFoundException {
        String path = "analyses/"+nomFile ;
        PrintWriter writer = new PrintWriter(path);
        if (!itemSetDPlus.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("################ ITEMSET DE TAILLE SUPERIEUR A 5 ####################");
            for (String s : itemSetDPlus){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetD5.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET DE TAILLE  5 ########################");
            for (String s : itemSetD5){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetD4.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET DE TAILLE  4 ########################");
            for (String s : itemSetD4){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetD3.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET DE TAILLE  3 ########################");
            for (String s : itemSetD3){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetD2.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET DE TAILLE  2 ########################");
            for (String s : itemSetD2){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }


        writer.println("\n\n***************************************************************************\n\n");
        writer.println("####################### ITEMSET PAR HERO ########################\n");
        if (!itemSetWarrio.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET WARRIOS ########################");
            for (String s : itemSetWarrio){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetRogue.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET ROGUES ########################");
            for (String s : itemSetRogue){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetMage.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET MAGES ########################");
            for (String s : itemSetMage){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetPriest.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET PRIESTS ########################");
            for (String s : itemSetPriest){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetWarlock.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET WARLOCK ########################");
            for (String s : itemSetWarlock){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetPaladin.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET PALADINS ########################");
            for (String s : itemSetPaladin){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetDruid.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET DRUIDS ########################");
            for (String s : itemSetDruid){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetHunter.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET HUNTERS ########################");
            for (String s : itemSetHunter){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!itemSetShaman.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### ITEMSET SHAMANS ########################");
            for (String s : itemSetShaman){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }

        writer.println("\n\n***************************************************************************\n\n");
        writer.println("####################### DECKS PAR HERO ########################\n");
        if (!specificCartesWarrio.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK WARRIOS ########################");
            for (String s : specificCartesWarrio){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesRogue.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK ROGUES ########################");
            for (String s : specificCartesRogue){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesMage.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK MAGES ########################");
            for (String s : specificCartesMage){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesPriest.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK PRIESTS ########################");
            for (String s : specificCartesPriest){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesWarlock.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK WARLOCKS ########################");
            for (String s : specificCartesWarlock){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesPaladin.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK PALADINS ########################");
            for (String s : specificCartesPaladin){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesDruid.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK DRUIDS ########################");
            for (String s : specificCartesDruid){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesHunter.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK HUNTERS ########################");
            for (String s : specificCartesHunter){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!specificCartesShaman.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("####################### DECK SHAMANS ########################");
            for (String s : specificCartesShaman){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }
        if (!cartesCommunes.isEmpty()){
            writer.println("_____________________________________________________________________");
            writer.println("##################### CARTES COMMUNES AUX HEROS ######################");
            for (String s : cartesCommunes){
                writer.println(s);
            }
            writer.println("_____________________________________________________________________");
        }


        writer.println("\n\n***************************************************************************\n\n");
        writer.println("############################## HEROS FREQUENTS #################################\n");
        writer.println("Marge : "+nbMage*100/nbTotal + "%");
        writer.println("Warrior : "+nbWarrio*100/nbTotal + "%");
        writer.println("Rogue : "+nbRogue*100/nbTotal + "%");
        writer.println("Priest : "+nbPriest*100/nbTotal + "%");
        writer.println("Warlock : "+nbWarlock*100/nbTotal + "%");
        writer.println("Paladin : "+nbPaladin*100/nbTotal + "%");
        writer.println("Druid : "+nbDruid*100/nbTotal + "%");
        writer.println("Hunter : "+nbHunter*100/nbTotal + "%");
        writer.println("Shaman : "+nbShaman*100/nbTotal + "%");

        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        decoup("result1");
        ecrireAnalyse("analyse1");


    }


}
