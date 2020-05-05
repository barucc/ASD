
import java.util.*;

public class InvertiLista {

	public static void invertiLista(LinkedList<Integer> list) {
		//TODO: Da completare
		if(list.toArray().length == 1) return;
		
		int tmp = list.getFirst();	
			
		list.remove();
			
		invertiLista(list);

		list.addLast(tmp);
			
	}
	
}
