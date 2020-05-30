
import java.util.*; 


public class MyMap<V> {
	
	private ArrayList<Entry<V>> a; 
	
	public MyMap() {
		a = new ArrayList<Entry<V>>(); 
	}
	
	public void add(Integer k, V v){
		Entry<V> e = new Entry(k, v);
		a.add(e);
	}
	
	public V removeMin(){
		Entry<V> e = null, ret =a.get(0);
		int n = a.get(0).k;
		for(int i=0; i<a.size(); i++){
			e = a.get(i);
			if(e.k < n){
				ret = e;
				n = e.k;
			}
		}
		a.remove(ret);
		return ret.v;
	}
	
	public V removeMax(){
		Entry<V> e = null, ret =a.get(0);
		int n = a.get(0).k;
		for(int i=0; i<a.size(); i++){
			e = a.get(i);
			if(e.k > n){
				ret = e;
				n = e.k;
			}
		}
		a.remove(ret);
		return ret.v;
	}
	
	public boolean isEmpty(){
		return a.size()==0;
	}
	
	public Entry<V> get(V v){
		Entry<V> e = null;
		Entry<V> ret = null;
		for(int i=0; i<a.size(); i++){
			e = a.get(i);
			if(e.v == v){
				ret = e;
				return ret;
			}
		}
		return ret;
	}
	
	public void replaceKey(Entry<V> e, Integer k){
		e.k = k;
	}
	
}
	

