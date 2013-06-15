package eights;

import java.util.Vector;


public class EightsPuzzleAI {
	
	
	int MAX_BOARD = 9;
	double ALPHA =	1.0;	/* Depth Bias */
	double BETA	=  2.0;	/* Misplaced Tile Bias */
	int MAX_DEPTH = 26;
	
	public Vector<Board> openList;
	public Vector<Board> closedList;
	public Vector<Board> solution;
	public Vector<Vector<Integer>> moves;

	
	
	public EightsPuzzleAI()
	{		
		openList = new Vector<Board>();
	    closedList = new Vector<Board>();
		solution = new Vector<Board>();		
				initMoves();
	}
	
	
	public void printValidMoves()
	{
		System.out.println("Printing Moves");
		int k = 0;
		for(Vector<Integer> move : moves )
		{
		
			System.out.println("Move" + k);
			k++;
			for(Integer i : move)
			{
				System.out.print(i);
			}
			System.out.println("");
		}
	}
	
	private void initMoves()
	{
		moves = new Vector<Vector<Integer>>();
		//1
		//move 1 {1, 3}
		Vector<Integer> move = new Vector<Integer>();
		move.add(1);
		move.add(3);
		moves.add(move);
		
		//move 2 {0, 2, 4} 
		move = new Vector<Integer>();
		move.add(0);
		move.add(2);
		move.add(4);
		moves.add(move);
		
		//move 3 {1, 5}     
		move = new Vector<Integer>();
		move.add(1);
		move.add(5);		
		moves.add(move);
		
		//move 4 {0, 4, 6}
		move = new Vector<Integer>();
		move.add(0);
		move.add(4);		
		move.add(6);
		moves.add(move);
		
		//move 5 {1, 3, 5, 7}
		move = new Vector<Integer>();
		move.add(1);
		move.add(3);		
		move.add(5);
		move.add(7);
		moves.add(move);
		
		//move 6 {2, 4, 8}
		move = new Vector<Integer>();
		move.add(2);
		move.add(4);		
		move.add(8);
		moves.add(move);
		
		//move 7  {3, 7}
		move = new Vector<Integer>();
		move.add(1);			
		move.add(7);
		moves.add(move);
		
		//move 8  {4, 6, 8}
		move = new Vector<Integer>();
		move.add(4);
		move.add(6);		
		move.add(8);
		
		moves.add(move);
		
		//move 9  {5, 7}
		move = new Vector<Integer>();	
		move.add(5);
		move.add(7);
		moves.add(move);
		
		
	}
	
	
	public double evaluateBoard(Board myBoard)
	{
	 int i;
	 int[] test = new int[MAX_BOARD];
	   
	 for(int t = 1; t < MAX_BOARD; t++){
		 test[t-1] = t;
	 }
	 
	  int  score=0;

	  for (i = 0 ; i < MAX_BOARD-1 ; i++) {
	    if(myBoard.array[i] != test[i])
	    	score++;
	  }
	  
	 
	  return (double)score;
	}

	private Board getListBest()
	{
		int k =0;		
		for(int i =0; i < openList.size(); i++)
		{			
			
				double i_f = openList.get(i).f;
				double k_f = openList.get(k).f;
				
				if(i_f < k_f){
					k = i;
				}			
		}		
		
		return openList.remove(k); 
	}
	
	
	 private void putClosedList(Board  b)
	 {
		 closedList.add(b);		 
	 }
	 
	 private void putOpenList(Board  b)
	 {
		 openList.add(b);		 
	 }
	 	
	 
	private void solutionSoluttions(Board b)
	{
						
		solution.add(b);
		while(b.pred != null)
		{
			//b.print();
			b = b.pred;
			solution.add(b);			
		}
		
		for(int s = solution.size() -1; s > -1; s--)
		{
			solution.get(s).print();			
		}
	}
	
	private Board getChildBoard(Board b, int move)
	{
		Board child_p = new Board();
		int blankSpot = b.blank;
		
		if (move < moves.get(blankSpot).size()) {
		    
			int moveFrom;
		    child_p = new Board(b);
		   

		    moveFrom = moves.get(blankSpot).get(move);

		    child_p.array[child_p.blank ] = child_p.array[ moveFrom ];

		    child_p.array[ moveFrom ] = 0;
		    child_p.blank = moveFrom;

		  }

		  return child_p;

	}
	
	private boolean onClosedList(Board b)
	{
		
		for(Board closed : closedList)
		{
			if(closed.equals(b))
			{
				return true;
			}
		}
		return false; 	
	}
	
	private boolean onOpenList(Board b)
	{
		for(Board open : openList)
		{
			if(open.equals(b))
			{
				return true;
			}
		}
		return false;  	
	}
	
	
	private Board getBoardOpenList(Board b)
	{		
		for(Board open : openList)
		{
			if(open.equals(b))
			{
				return open;
			}
		}
		return null;
	}
	
	
	public void printChildern(Board b)
	{
		System.out.println("Printing Childern");
		Board child_p = new Board();
		for(int i = 0; i < 4; i++)
		 {
			 child_p = getChildBoard( b, i);
			 
			 if(!child_p.isBlank()){
				 	    	
				 if(onClosedList(child_p)){
					 continue;
				 }
				 
				 child_p.depth = b.depth +1;	    				 
				 child_p.h = evaluateBoard(child_p);
				 child_p.g = (double) child_p.depth;
				 child_p.f = (child_p.g * ALPHA) + (child_p.h *BETA);
				 				
				 child_p.print();
			 }
			 
		 }	    	 
	}
	
	public void astar(Board b)
	{
	  double score = evaluateBoard(b);
	  putOpenList(b); 
	  b.f = score;
	  b.h = score;
	  b.depth = 0;
	  b.score = score;
	  Board cur_board_p, child_p, temp;
	  
	  while ( openList.size() != 0 ) { 
	   
		 cur_board_p = getListBest();
		 
	     putClosedList(cur_board_p);
	     if(cur_board_p.h == 0.0){
	    	 System.out.println("Found Solution");
	    	 solutionSoluttions(cur_board_p);
	    	 return;
	     }else{
	    	 
	    	 if(cur_board_p.depth > MAX_DEPTH) continue;
	    	 {
	    		 for(int i = 0; i < 4; i++)
	    		 {
	    			 child_p = getChildBoard( cur_board_p, i);
	    			 
	    			 if(!child_p.isBlank()){
	    				 	    	
	    				 if(onClosedList(child_p)){
	    					 continue;
	    				 }
	    				 
	    				 child_p.depth = cur_board_p.depth +1;	    				 
	    				 child_p.h = evaluateBoard(child_p);
	    				 child_p.g = (double) child_p.depth;
	    				 child_p.f = (child_p.g * ALPHA) + (child_p.h *BETA);
	    				 
	    				 if(onOpenList(child_p)){	    					 
	    					temp = getBoardOpenList(child_p);
	    					 
	    					if(temp.g < child_p.g )
	    					{
	    						putOpenList(temp);
	    						continue;
	    					}
	    				 }else {
	    					 putOpenList(child_p);
	    					 child_p.pred = cur_board_p;
	    				 }
	    			 }	    			 
	    		 }	    	 
	    	 }
	     }	     
	  }
	  
	}
	
	
	
}
