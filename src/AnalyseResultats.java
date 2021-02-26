import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    static Map<String,String> pouvoirHero = new HashMap<>(){{
        put("Armor Up!","warrior");
        put("Dagger Mastery", "Rogue");
        put("Fireblast", "Mage");
        put("Lesser Heal", "Priest");
        put("Life Tap", "Warlock");
        put("Reinforce", "Paladin");
        put("Shapeshift", "Druid");
        put("Steady Shot", "Hunter");
        put("Totemic Call", "Shaman");
    }};
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
    int nbTotal=0;
    int nbMage=0;
    int nbWarrio=0;
    int nbRogue=0;
    int nbPriest=0;
    int nbWarlock=0;
    int nbPaladin=0;
    int nbDruid =0;
    int nbHunter=0;
    int nbShaman=0;


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
                if (contenuLigne.toLowerCase().contains("ArmorUp!".toLowerCase())){
                    itemSetWarrio.add(contenuLigne);
                    cartesWarrio.addAll(Arrays.asList(contenuLigne.replaceAll("ArmorUp!" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("DaggerMastery".toLowerCase())){
                    itemSetRogue.add(contenuLigne);
                    cartesRogue.addAll(Arrays.asList(contenuLigne.replaceAll("DaggerMastery" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("Fireblast".toLowerCase())){
                    itemSetMage.add(contenuLigne);
                    cartesMage.addAll(Arrays.asList(contenuLigne.replaceAll("Fireblast" ,"").split(
                            " ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("LesserHeal".toLowerCase())){
                    itemSetPriest.add(contenuLigne);
                    cartesPriest.addAll(Arrays.asList(contenuLigne.replaceAll("LesserHeal" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("LifeTap".toLowerCase())){
                    itemSetWarlock.add(contenuLigne);
                    cartesWarlock.addAll(Arrays.asList(contenuLigne.replaceAll("LifeTap" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("Reinforce".toLowerCase())){
                    itemSetPaladin.add(contenuLigne);
                    cartesPaladin.addAll(Arrays.asList(contenuLigne.replaceAll("Reinforce" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("Shapeshift".toLowerCase())){
                    itemSetDruid.add(contenuLigne);
                    cartesDruid.addAll(Arrays.asList(contenuLigne.replaceAll("Shapeshift" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("SteadyShot".toLowerCase())){
                    itemSetHunter.add(contenuLigne);
                    cartesHunter.addAll(Arrays.asList(contenuLigne.replaceAll("SteadyShot" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

                if (contenuLigne.toLowerCase().contains("TotemicCall".toLowerCase())){
                    itemSetShaman.add(contenuLigne);
                    cartesShaman.addAll(Arrays.asList(contenuLigne.replaceAll("TotemicCall" ,"").split(" ")).subList(0,
                            contenuLigne.split(" ").length - 2));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("   Armor Up!   ".toLowerCase().replaceAll(" ",""));
        System.out.println("Armor Up!".split(" ").length);
        decoup("result1");
        for (String s : cartesPriest){
            System.out.println(s);
        }
        for (String s : cartesShaman){
            System.out.println(s);
        }
        for (String s : cartesMage){
            System.out.println(s);
        }
        for (String s : cartesHunter){
            System.out.println(s);
        }
        for (String s : cartesWarrio){
            System.out.println(s);
        }
        for (String s : cartesPaladin){
            System.out.println(s);
        }
        for (String s : cartesWarlock){
            System.out.println(s);
        }
        for (String s : cartesRogue){
            System.out.println(s);
        }
        for (String s : cartesDruid){
            System.out.println(s);
        }


    }


}
