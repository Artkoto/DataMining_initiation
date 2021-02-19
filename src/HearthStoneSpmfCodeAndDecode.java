import java.io.*;
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

    /**
     * convertir les donnés au format spmf
     * @param fileReader
     * @throws IOException
     */
    static void conversionSpmf (FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String contenuLigne ;
        partie partieCourante = new partie();
        while ((contenuLigne = bufferedReader.readLine()) != null){
            String action = mot(2, contenuLigne);
            //debut d'une partie
            if (action.contains("Begin")){
                if (!partieCourante.cartesJoueur1.isEmpty())
                dataSpmf.add(partieCourante.getCarteJoueur1());
                if (!partieCourante.cartesJoueur2.isEmpty())
                dataSpmf.add(partieCourante.getCarteJoueur2());
                partieCourante.reset();

            }
            //verifier si le caracter est différent de theCoin
            else if (!action.contains("TheCoin")){
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
            }
        }
        dataSpmf.add(partieCourante.getCarteJoueur1());
        dataSpmf.add(partieCourante.getCarteJoueur2());

    }

    /**
     * ecrire dans un fichier de sortie
     * @param nomFile
     */
    static void ecrireDansFichierSpmf(String nomFile) throws FileNotFoundException {
        String path = "sortie/"+nomFile ;
        PrintWriter writer = new PrintWriter(path);
        //entête
        writer.println("@CONVERTED_FROM_TEXT");
        for ( Map.Entry<String, Integer> m : dataCartes.entrySet()) {
            writer.println("@ITEM=" + m.getValue() + "=" + m.getKey());
        }
        for (List<Integer> m : dataSpmf){
            Collections.sort(m);
            String ligne = m.toString().substring(1,m.toString().length()-1);
            writer.println(ligne.replaceAll(",", ""));
        }
        System.out.println("\nConversion terminée.");
        writer.close();
    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in) ;

        System.out.print("Entrez le format souhaité (SPMF; .. ;..) :  ");
        String format = sc.nextLine();
        System.out.print("Entrez le fichier de données :  ");
        String entree = sc.nextLine();
        System.out.print("Entrez le fichier de destination:  ");
        String sortie = sc.nextLine();
        FileReader fichier = lireFichier(entree);
        switch (format.toLowerCase()) {
            case "spmf":
            conversionSpmf(fichier);
            ecrireDansFichierSpmf(sortie);
            default:
                System.out.println("Le format choisi est incorrecte");
        }

    }


}
