
package Obec;

import java.io.IOException;

public interface IObyvatel {
    
    int importData(String soubor) throws IOException;  
    
    void vlozObec(Obec obec, enumPozice pozice, enumKraje kraj);  
    
    Obec zpristupniObec(enumPozice pozice, enumKraje kraj);  
    
    Obec odeberObec(enumPozice pozice, enumKraje kraj);  
    
    float zjistiPrumer(enumKraje kraj); 
    
    void zobrazObce(enumKraje kraj, StringBuilder output);  
    
    void zobrazObceNadPrumer(enumKraje kraj);
    
    void zrus(enumKraje kraj); 
}


