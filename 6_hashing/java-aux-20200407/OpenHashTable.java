import java.util.LinkedList;


public class OpenHashTable extends AbstractHashTable {
	// Un array di Entry per la tabella
	private Entry[] table;
	// marcatore di cella vuota ma da esaminare durante probing
	private final Entry DEFUNCT = new Entry(null, 0); 
	
	// Costruttori
	public OpenHashTable(int cap, int p, double lambda) {
		super(cap, p, lambda);
	}
	public OpenHashTable(int cap, int p) {
		super(cap, p); // massimo fattore di carico predefinito
	}
	public OpenHashTable(int cap) {
		super(cap); // primo predefinito
	}
	public OpenHashTable() {
		super(); // capacità predefinita
	}
		
	// Metodi non implementati in AbstractHashTable

	// Inizializza una tabella hash vuota secondo i parametri passati al costruttore
	protected void createTable() {
		int c = this.getCapacity();
		this.table = new Entry[c];
		return;
	}


	private Entry psearch(String k){
		int hk = hashFunction(k);
		
		int n = 0;
		Entry e = table[hk];
		while(e != null && !e.equals(DEFUNCT)){
			if(e.getKey().equals(k)){
				return e;
			}
			n++;
			if(n*n+hk>=this.getCapacity())	return null;
			e = table[n*n+hk];
		}	
		return null;
	}
	// Restituisce il valore (intero) associato alla chiave k
	// Restituisce -1 se la chiave è assente
	public int get(String k) {
		if(this.isEmpty())	return -1;
		Entry e = psearch(k);
		
		if(e!=null) return e.getValue();
		return -1;
	}
	
	// Aggiorna il valore associato alla chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int put(String k, int value) {
		Entry ef = new Entry(k, value);
		
		
		if(this.isEmpty()){
			if(this.getMaxLambda()<(double)(this.size()+1)/this.getCapacity()){	
				System.out.println("   REALLOCATED!  1  ");
				int c = this.getCapacity(); 
				this.resize(c);
				this.realloc(c);
			}
			int hk = hashFunction(k);
			this.incSize();
			this.table[hk] = ef;
			return -1;
		}
		
		Entry e = psearch(k);
		if(e!=null){				
			int v = e.getValue();
			e.setValue(value);
			return v;
		}
		
		if((double)(this.size()+1)/this.getCapacity() > this.getMaxLambda()){
				
			int c = this.getCapacity(); 
			this.resize(c);
			this.realloc(c);
			System.out.println("   REALLOCATED!   2   ");
		}
		
		int hk = hashFunction(k);
		this.incSize();
		int n = 0;
		e = this.table[hk];
		while(e != null && !e.equals(DEFUNCT)){
			n++;
			
			if(n*n+hk >= this.getCapacity()){
				
				int c = this.getCapacity(); 
				this.resize(c);
				this.realloc(c);
				System.out.println("   REALLOCATED!   2   ");
			}
			
			e = this.table[n*n+hk];
		}
		this.table[n*n+hk] = ef;
		return -1;
		
	}
	
	
	// Rimuove la coppia con chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int remove(String k) {
		int hk = hashFunction(k);
		for(int n=0; hk<this.getCapacity(); hk+=n*n){
			if(this.table[hk] != null && !this.table[hk].equals(DEFUNCT)){
				if(this.table[hk].getKey().equals(k)){
					this.decSize();
					int v = table[hk].getValue();
					this.table[hk] = DEFUNCT;
					return v;
				}
			}
			n++;
		}
		return -1;
	}
	
	// Restituisce un oggetto Iterable contenente tutte le coppie presenti
	// nella tabella hash
	public Iterable<Entry> entrySet() {
		LinkedList<Entry> l = new LinkedList<Entry>();
		int x = 0;
		Entry e = null;
		while(x<this.getCapacity()){
			e = this.table[x];
			if(e!=null && e!=DEFUNCT)
				l.add(e);
			x++;
		}
		return l;
	}
	
	private void realloc(int oldcap){
		Entry[] nl = new Entry[oldcap];
		
		for(int i = 0; i<oldcap; i++){
			if(table[i] != null && !table[i].equals(DEFUNCT)){
				nl[i] = this.table[i];
			}
		}
		this.createTable();
		while(this.size()!=0)	
			this.decSize();
		
		for(int j=0; j<oldcap; j++){
			if(nl[j]!=null){
				this.put(nl[j].getKey(), nl[j].getValue());
			}
		}
	}
	
}


