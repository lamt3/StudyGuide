package datastructures.uw;

public class runner {

	public void firstToLast(LinkedIntList l) {
		
		if(l!=null && l.next != null ) {
			LinkedIntList temp = l;
			int first = l.value;
			while(temp.next != null) {
				temp = temp.next; 
			}
			temp.next = new LinkedIntList(first);
			l = l.next; 
		}
		
		
	}
	
	public static void extend(LinkedIntList a, LinkedIntList b) {
		LinkedIntList temp = a;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = b;
	}
	
	public static LinkedIntList concat(LinkedIntList a, LinkedIntList b) {
		LinkedIntList returnList = new LinkedIntList(0);
		LinkedIntList aTemp = a;
		LinkedIntList bTemp = b;
		LinkedIntList temp = returnList;
		while(aTemp != null) {
			temp.next = new LinkedIntList(aTemp.value);
			aTemp = aTemp.next;
			temp = temp.next;
		}
		
		while(bTemp!= null) {
			temp.next = new LinkedIntList(bTemp.value);
			bTemp = bTemp.next;
			temp = temp.next;
		}
		
		return returnList.next;
	}
	
	public static void main(String[] args) {
		LinkedIntList a = new LinkedIntList(1);
		a.next = new LinkedIntList(2);
		a.next.next = new LinkedIntList(3);
		
		LinkedIntList b = new LinkedIntList(4);
		b.next = new LinkedIntList(5);
		b.next.next = new LinkedIntList(6);
		
		LinkedIntList c = concat(a,b);
		
		System.out.println(a);
		
		
		
	}
	
}
