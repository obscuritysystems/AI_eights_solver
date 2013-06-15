package eights;
import java.util.ArrayList;
import java.util.Random;


public class Board {

	  public Board pred;
	  public double f;
	  public double g;
	  public double h;
	  public int[]  array;
	  public int   blank;
	  public int   depth;
	  public double score;
	  public int MAX_BOARD;
	  private Random generator = new Random();
	  
	  private ArrayList<Integer> values = new ArrayList<Integer>();

	
	public Board()
	{
		MAX_BOARD = 9;		
		array = new int[MAX_BOARD];
	}
	
	
	
	public Board(Board b)
	{		
		this.blank = b.blank;
		this.MAX_BOARD = b.MAX_BOARD;
		this.depth = b.depth;
		this.array = copyArray(b.array);

	}
	
	public boolean isBlank()
	{
		if(this.array == null)
		return true;		
		else
		return false;		
	}
	public boolean equals(Board b)
	{
		if(this.values.size() != b.values.size()){
			return false;		
		}else{
			
			for(int i = 0; i < MAX_BOARD; i++){
				
			   if(this.array[i] != b.array[i])
			   {
				   return false;
			   }
			   
			}
			return true;
		}
				
	}
	
	public  ArrayList<Integer> cloneListInt(ArrayList<Integer> list) {
	    ArrayList<Integer> clone = new ArrayList<Integer>(list.size());
	    for(Integer item: list) clone.add(item.intValue());
	    return clone;
	}
	
	public int[] copyArray(int[] a)
	{
		int[] copied = new int[MAX_BOARD];
		for(int i = 0; i < MAX_BOARD; i++){
		
			copied[i] = a[i];
		}
		return copied;
	}
	
	public void initPuzzle()
	{	
		array = new int[MAX_BOARD];	
		// generate board
		do{
			int j = 0;			
			//create items to be pulled out randomly
			for(int i=0; i < MAX_BOARD; i++){
				values.add(i);			
			}	
			
			
			while(values.size() > 0){

				int rand = generator.nextInt(values.size());								
				array[j] = values.remove(rand).intValue();					
				j++;										
			}
			
		}while((countInversions() & 1) == 1);
		
			
		for (int i = 0 ; i <  MAX_BOARD; i++) {
		    if (array[i] == 0) {
		      this.blank = i;
		      break;
		    }
		  }

		
	}
	
	
	public void setBlank()
	{
		for (int i = 0 ; i <  MAX_BOARD; i++) {
		    if (array[i] == 0) {
		      this.blank = i;
		      break;
		    }
		  }
	}
	private int countInversions()
	{
		  int i, j, inversions = 0;
		  for (j = 0 ; j < MAX_BOARD-1 ; j++) {
			  
		    for (i = j+1; i < MAX_BOARD ; i++) {
		    
		    	if (array[j] > array[i]){ 
		    		inversions++;
		    	}
		    }
		  
		  }		  
		  return inversions;
		}

	
	public void print()
	{
		System.out.println("Board");
		for(int i =0; i < MAX_BOARD; i++)
		{
			System.out.print(this.array[i]);
			if(i == 2 || i == 5){
				System.out.println("");				
			}
		}
		System.out.println("");
				
	}
	
	public void printAll()
	{
		System.out.println("Board");
		for(int i =0; i < MAX_BOARD; i++)
		{
			System.out.print(this.array[i]);
			if(i == 2 || i == 5){
				System.out.println("");				
			}
		}
		System.out.println("");
		
		System.out.println("blank = " + blank);
		System.out.println("f = " + f);
		System.out.println("h = " + h);
		System.out.println("depth = " + depth);
		System.out.println("score = " + score);
	}
}
	
	
