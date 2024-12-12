
package AbstrTable;

import Lists.AbstrDoubleList;
import Lists.IAbstrDoubleList;
import java.util.Iterator;

/**
 *
 * @author 38067
 */
public class AbstrFIFO<T> {
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
        return ADS.odeberPrvni();
    }
    
    public Iterator<T> VytvorIterator(){
        return ADS.iterator();
    }
    
}
