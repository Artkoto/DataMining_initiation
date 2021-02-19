import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HearthStoneSpmfCodeAndDecode {

    static Map<String , Integer> dataCartes = new HashMap<>();
    static ArrayList<List<Integer>> dataSpmf = new ArrayList<>();

     static class partie {

        ArrayList<Integer> cartesJoueur1;
        ArrayList<Integer> cartesJoueur2;

         public partie() {
             this.cartesJoueur1 = new ArrayList<Integer>();
             this.cartesJoueur2 = new ArrayList<Integer>();
         }

        public ArrayList<Integer> getCarteJoueur1() {
            return (ArrayList<Integer>) cartesJoueur1.clone();
        }

        public ArrayList<Integer> getCarteJoueur2() {
            return (ArrayList<Integer>) cartesJoueur2.clone();
        }

        public void reset(){
            cartesJoueur1.clear();
            cartesJoueur2.clear();
        }

        public void addCarteJ1(Integer val){
             if (!cartesJoueur1.contains(val))
             cartesJoueur1.add(val);
        }

         public void addCarteJ2(Integer val){
             if (!cartesJoueur2.contains(val))
             cartesJoueur2.add(val);
         }
    }

    static FileReader lireFichier (String nom) throws FileNotFoundException {
        String path = "data/"+nom ;
        FileReader file = new FileReader(path);
        return file;
    }

    /**
     * recupération d'un mot dans une chaine
     * @param rang
     * @return
     */
    static String mot(int rang, String str){
        String result ="" ;
        int comp = 0 ;
        int bornInf = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' '){
                comp++;
                if (comp == rang){
                    result = str.substring(bornInf,i);
                    return result;

                }
                else bornInf=i+1;
            }
            if (i == str.length()-1 && comp == rang-1){
                result = str.substring(bornInf);
            }
        }
        return result;
    }

    static void conversion (FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String contenuLigne ;
        boolean partieDebute = false ;
        partie partieCourante = new partie();
        while ((contenuLigne = bufferedReader.readLine()) != null){
           // System.out.println(contenuLigne);
            String action = mot(2, contenuLigne);
            //debut d'une partie
            if (action.contains("Begin") && partieDebute){
                dataSpmf.add(partieCourante.getCarteJoueur1());
                dataSpmf.add(partieCourante.getCarteJoueur2());
                partieCourante.reset();

            }
            //verifier si le caracter est différent de theCoin
            else if (!action.contains("TheCoin") && !action.contains("Begin")){
                //verifier si c'est le joueur 1 ou 2
                Integer carteValNum ;
                String cateName = action.substring(1);
                //ajouter les cartes non encore repertoriers
                if( (carteValNum = dataCartes.get(cateName)) == null ) {
                    carteValNum = dataCartes.size() + 1;
                    dataCartes.put(cateName, carteValNum);
                }
                if (action.charAt(0)=='M'){
                    partieCourante.addCarteJ1(carteValNum);
                }
                else if (action.charAt(0)=='O'){
                        partieCourante.addCarteJ2(carteValNum);
                }
                partieDebute=true;
            }
        }
        dataSpmf.add(partieCourante.getCarteJoueur1());
        dataSpmf.add(partieCourante.getCarteJoueur2());

    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in) ;

        String entree = sc.nextLine();
       // System.out.println(mot(2,entree));
        FileReader fichier = lireFichier(entree);
        conversion(fichier);
       // System.out.println(dataCartes.size());
       /* for ( Map.Entry<String, Integer> m : dataCartes.entrySet()){
            System.out.println(m.getValue() + "\t"+ m.getKey());
        }*/
        System.out.println("@CONVERTED_FROM_TEXT");
         for ( Map.Entry<String, Integer> m : dataCartes.entrySet()) {
             System.out.println("@ITEM=" + m.getValue() + "=" + m.getKey());
         }
        for (List<Integer> m : dataSpmf){
            for (Integer i : m) {
                System.out.print(i + "\t");
            }
            System.out.println("\n#####################################################");
        }
        System.out.println(dataSpmf.get(0).get(0));

    }


}
