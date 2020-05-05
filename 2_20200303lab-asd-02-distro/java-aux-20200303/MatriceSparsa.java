
public class MatriceSparsa {

	private int m,n;
	Elem head;

	private class Elem{
		int i, j, x;
		Elem next;
		
		private void setElem(Elem e){
			this.i=e.i;
			this.j=e.j;
			this.x=e.x;
		}
	}

	
	
	public MatriceSparsa(int m, int n) {
		this.m = m;
		this.n = n;
		
	}

	public int getNumRow() {
		// TODO: Implement here
		return this.m;
	}

	public int getNumCol() {
		// TODO: Implement here
		return this.n;
	}

	
	public void rem(int i, int j){
		if(head==null)	return;
		if(head.next==null){
			Elem e = head;
			if(e.i==i && e.j==j){
				head = new Elem();
				head = e.next;
				return;
			}
		}
		else{
			Elem e = head;
			Elem e1 = new Elem();
			Elem ptr = e1;
			do{
				
				if(e.i==i && e.j==j){
					if(e.next==null){
						
						break;
					}
					else e = e.next;
				}
				ptr.setElem(e);
				
				if(e.next!=null){
					e = e.next;
					if(e.i==i && e.j==j && e.next==null){break;}
					ptr.next = new Elem();
					ptr = ptr.next;
				}
				else{break;}
				
				
			}while(true);

			head = new Elem();
			head = e1;			
		}
		
		return;
	}

	public void set(int i, int j, int x) {
		// TODO: Implement here
		
		if(i>=this.m || j>=this.n){
			System.out.println("Exceeds Bounds");
			return;
		}
		if(x==0) {
			rem(i,j);
			return;
		}
		if(head == null){
			head = new Elem();
			head.i = i;
			head.j = j;
			head.x = x;
		}
		else if(head.next==null){
			Elem e = head;
			if(e.i==i && e.j==j){
				e.x = x;
				return;
			}
			e.next = new Elem();
			e=e.next;
			e.i=i; e.j=j; e.x=x;
			
		}
		else{
			Elem e = head;
			do{
				if(e.i==i && e.j==j){
					e.x = x;
					return;
				}
				e=e.next;
			}while(e.next!=null);
			
			e.next=new Elem();
			e=e.next;
			e.i=i; e.j=j; e.x=x;
		}
						
	}
		
	

	public int get(int i, int j) {
		// TODO: Implement here
		//if(i>this.m || j>this.n)
		//	return 0;
		Elem e = head;
		while(e.next!=null){
			if(e.i==i && e.j==j)
				return e.x;
			e = e.next;
		}
		if(e.i==i && e.j==j)
			return e.x;
		else{
			return 0;
		}
	}

	public String toString() {
		// TODO: Implement here
		Elem e = head;
		String s = "";
		for(int r=0; r<this.m; r++){
			for(int c=0; c<this.n; c++){
				s+=get(r,c)+ " ";
			}
			s+="\n";
		}
		s+="\n";
		while(e.next!=null){
			s+=e.x+" ";
			e = e.next;
		}
		s+=e.x;
		return s;
	}

	public static MatriceSparsa add(MatriceSparsa mat1, MatriceSparsa mat2) {
		// TODO: Implement here
		int r = mat1.getNumRow(); int c = mat1.getNumCol(); int v=0;
		MatriceSparsa out = new MatriceSparsa(r,c);
		if(mat1.getNumRow()!= mat2.getNumRow() || mat1.getNumCol() != mat2.getNumCol()){
			System.out.println("Errore dimensioni matrici");
			return null;
		}
		for(int row=0; row<r; row++){
			for(int col=0; col<c; col++){
				v = mat1.get(row, col) + mat2.get(row,col);
				out.set(row,col,v);
			}
		}
		return out;
	}

	public static MatriceSparsa tra(MatriceSparsa mat1, MatriceSparsa mat2) {
		// TODO: Implement here
		return null;
	}

	public MatriceSparsa mul(MatriceSparsa mat1, MatriceSparsa mat2) {
		// TODO: Implement here
		return null;
	}
}
