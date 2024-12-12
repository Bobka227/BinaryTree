
package Lists;

import java.util.Iterator;

public interface IAbstrDoubleList <T> {
    
    void zrus();
    
    boolean jePrazdny();
    
    
    void vlozPrvni(T data);
    void vlozPosledni(T data);
    void vlozNaslednika(T data);
    void vlozPredchudce(T data);
    
    
    T zpristuniAktualni();
    T zpristupniPrvni();
    T zpristuniPosledni();
    T zpristupniNaslednika();
    T zpristuniPredchudce();
    
    
    T odeberAktualni();
    T odeberPrvni();
    T odeberPosledni();
    T odeberNaslednika();
    T odeberPredchudce();
    
    Iterator<T> iterator();
    
    
    
}
