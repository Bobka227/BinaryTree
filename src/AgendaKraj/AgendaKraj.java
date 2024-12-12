
package AgendaKraj;

import AbstrTable.AbstrTable;
import AbstrTable.eTypProhl;
import Obec.Obec;
import Obec.enumKraje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AgendaKraj implements IAgendaKraj {

    private AbstrTable<String, Obec> table = new AbstrTable<>();

    private Random ran = new Random();

    @Override
    public Obec najdi(String key) {
        return table.najdi(key);
    }

    @Override
    public void vloz(Obec obec) {
        table.vloz(obec.getNazev(), obec);
    }

    @Override
    public Obec odeber(String key) {
        return table.odeber(key);
    }

    @Override
    public void Vybuduj() {
        List<Obec> obceList = new ArrayList<>();
        Iterator<Obec> iterator = table.vytvorIterator(eTypProhl.HLOUBKA);
        while (iterator.hasNext()) {
            obceList.add(iterator.next());
        }

        table.zrus();

        postroeniVyvazenehoBVS(obceList, 0, obceList.size() - 1);
    }

    private void postroeniVyvazenehoBVS(List<Obec> obceList, int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        Obec obec = obceList.get(mid);
        table.vloz(obec.getNazev(), obec);

        postroeniVyvazenehoBVS(obceList, start, mid - 1);
        postroeniVyvazenehoBVS(obceList, mid + 1, end);
    }

    @Override
    public Iterator<Obec> VytvorIterator(eTypProhl typ) {
        return table.vytvorIterator(typ);
    }

    @Override
    public Obec Generuj() {
        String name = generateRandomString(5); 
        int psc = ran.nextInt(99999);
        int pocetMuzu = ran.nextInt(1000000);
        int pocetZen = ran.nextInt(1000000);
        enumKraje kraj = enumKraje.values()[ran.nextInt(enumKraje.values().length)];
        return new Obec(name, kraj, psc, pocetMuzu, pocetZen);
    }

    private String generateRandomString(int length) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(ran.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public int importData(String soubor) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(soubor))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 7) {
                    System.out.println("neni spravny line: " + line);
                    continue;
                }

                try {
                    int krajIndex = Integer.parseInt(parts[0]) - 1;
                    enumKraje kraj = enumKraje.values()[krajIndex];

                    String obecName = parts[3].trim();
                    int PSC = Integer.parseInt(parts[2].trim());
                    int pocetMuzu = Integer.parseInt(parts[4].trim());
                    int pocetZen = Integer.parseInt(parts[5].trim());

                    Obec novaObec = new Obec(obecName, kraj, PSC, pocetMuzu, pocetZen);

                    table.vloz(novaObec.getNazev(), novaObec);
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Chyba: " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Нneni spravny index: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void zrus() {
        table.zrus();
    }

}
