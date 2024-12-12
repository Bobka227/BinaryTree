
package AbstrTable;

import java.util.Iterator;

public interface IAbstrTable<K ,V> {
    public void zrus();
    
     boolean jePrazdny();
    
     V najdi(K key);
    
     void vloz(K key, V value);
    
     V odeber(K key);
     
     Iterator vytvorIterator(eTypProhl typ);
    
    
}
