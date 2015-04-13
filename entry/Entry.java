package entry;
/**
 *Design pattern "Composition" - represents an entry.
 *
 * @author SuperJulietta
 * @param <K> the key.
 * @param <V> the value.
 */
public interface Entry <K, V>{
	K getKey();
	V getValue();
	
}
