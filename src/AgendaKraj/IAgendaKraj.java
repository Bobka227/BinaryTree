
package AgendaKraj;

import AbstrTable.eTypProhl;
import Obec.Obec;
import java.util.Iterator;

public interface IAgendaKraj {
    Obec najdi(String key);
    void vloz(Obec obec);
    Obec odeber(String key);
    void Vybuduj();
    Iterator<Obec> VytvorIterator(eTypProhl typ);
    Obec Generuj();
    void zrus();
}
