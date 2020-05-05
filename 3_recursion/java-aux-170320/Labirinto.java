public class Labirinto {

	private static enum Cella { VUOTA, PIENA };

	private int n;
    private Cella m[][];
    private boolean marcata[][];

  public Labirinto(int n) {
		//TODO: Da Completare
		this.n = n;
		this.m = new Cella[n][n];
		for(int i=0; i<this.n; i++)
			for(int j=0; j<this.n; j++)
				this.m[i][j] = Cella.VUOTA;
				
		this.marcata = new boolean[n][n];
		for(int i=0; i<this.n; i++)
			for(int j=0; j<this.n; j++)
				this.marcata[i][j] = false;
  }

	public void setPiena(int r, int c){
		//TODO: Da Completare
		this.m[r][c] = Cella.PIENA;
  }

	private boolean uscita(int r, int c){
  		//TODO: Da Completare
		return this.n-1==r && this.n-1==c;
  }

	public boolean percorribile(int r, int c){
  		//TODO: Da Completare
		if(r>=this.n || c>=this.n || r<0 || c<0)	return false;
		boolean perc = this.m[r][c] == Cella.VUOTA && this.marcata[r][c] == false; 
		return perc;
  }

	private boolean uscitaRaggiungibileDa(int r, int c){
		//TODO: Da Completare
		int n = this.n;
		int x = 0, y = 0;
		boolean a = false;
		
		this.marcata[r][c] = true; //giusto
		if(this.uscita(r, c))	return true;
		
		
		if(this.percorribile(r, c+1)){ //if =>else if
			
			if(a) return true;
			a |= this.uscitaRaggiungibileDa(r, c+1);
		}	
		if(this.percorribile(r+1, c)){
			
			if(a) return true;
			a |= this.uscitaRaggiungibileDa(r+1, c);
		}
		if(this.percorribile(r, c-1)){
			
			if(a) return true;
			a |= this.uscitaRaggiungibileDa(r, c-1);
		}
		if(this.percorribile(r-1, c)){
			
			if(a) return true;
			a |= this.uscitaRaggiungibileDa(r-1, c);
		}
		if(!a) this.marcata[r][c] = false; //if(a) segna solo i blocchi
		
		return a;
	}

	public boolean risolvibile(){
		//TODO: Da Completare
		return this.uscitaRaggiungibileDa(0,0);
	}

	public String toString() {
		//TODO: Da Completare
		String s = "";
		for(int i=0; i<this.n; i++){
			for(int j=0; j<this.n; j++){
				if(this.m[i][j] == Cella.PIENA)
					s+="#";
				else if(this.m[i][j] == Cella.VUOTA && this.marcata[i][j] == false)
					s+=".";
				else if(this.m[i][j] == Cella.VUOTA && this.marcata[i][j] == true)
					s+="+";
				
			}
			s+="\n";
		}
		
		return s;
	}
}
