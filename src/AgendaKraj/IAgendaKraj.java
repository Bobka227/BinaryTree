/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AgendaKraj;

import AbstrTable.eTypProhl;
import Obec.Obec;
import java.util.Iterator;

/**
 *
 * @author 38067
 */
public interface IAgendaKraj {
    Obec najdi(String key);
    void vloz(Obec obec);
    Obec odeber(String key);
    void Vybuduj();
    Iterator<Obec> VytvorIterator(eTypProhl typ);
    Obec Generuj();
    void zrus();
}
