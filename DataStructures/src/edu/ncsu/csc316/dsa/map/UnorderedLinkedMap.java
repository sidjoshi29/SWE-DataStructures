package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

    private PositionalList<Entry<K, V>> list;
    
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key){
    	Iterable<Position<Entry<K, V>>> it = list.positions();
    	for(Position<Entry<K, V>> p: it) {
    		if(p.getElement().getKey().equals(key)) {
    			return p;
    		}
    	}
    	return null;
    }



    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null && p.getElement().getKey().equals(key)) {
        	moveToFront(p);
        	return p.getElement().getValue();
        	
        }
        return null;
    }
    
    private void moveToFront(Position<Entry<K, V>> position) {
    	Entry<K, V> front = list.remove(position);
    	list.addFirst(front);
    }

    @Override
    public V put(K key, V value) {
    	 Position<Entry<K, V>> p = lookUp(key);

         if(p != null) {
        	 V val = list.set(p, new MapEntry<K, V>(key, value)).getValue();
        	 moveToFront(p);
        	 return val;
         } else {
        	 list.addFirst(new MapEntry<K, V>(key, value));
        	 return null;
         }
    }
    
    @Override
    public V remove(K key) {
    	 Position<Entry<K, V>> p = lookUp(key);
    	 //&& p.getElement().getKey().equals(key)
         if(p != null) {
         		V ret = list.remove(p).getValue();
         		return ret;
         	
         		
         }
         return null;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}



