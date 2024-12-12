
package AbstrTable;

import Lists.AbstrDoubleList;
import Lists.IAbstrDoubleList;
import java.util.Iterator;

/**
 *
 * @author 38067
 */
public class AbstrLIFO<T> {
    
    IAbstrDoubleList<T> ADS = new AbstrDoubleList<>();
    
    public void zrus(){
        ADS.zrus();
    }
    
    public boolean jePrazdny(){
        return ADS.jePrazdny();
    }
    
    public void vloz(T data){
        ADS.vlozPosledni(data);
    }
    
    public T odeber(){
        return ADS.odeberPosledni();
    }
    
    public Iterator<T> vytvorIterator(){
        return ADS.iterator();
    }
}
