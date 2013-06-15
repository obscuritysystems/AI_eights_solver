package eights;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Eights implements  ActionListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	EightsPuzzleAI AI;
	Board myBoard;
	JBoard boardGUI;
	JButton solve, randomize;
	static JTextArea resultArea;

	
	public JPanel createContentPane (){
		
		 JPanel totalGUI = new JPanel();
	     totalGUI.setLayout(null);
	        
			       


	
		
    
        
		// Buttons 
		solve = new JButton(); 
		randomize = new JButton(); 
        
		
		// We create a bottom JPanel to place everything on.
       
        solve.setText("Solve");
        solve.setLayout(null);
        solve.setLocation(10, 80);
        solve.setSize(100, 40);
        solve.setVisible(true);
        solve.addActionListener(this);
        
        randomize.setText("Randomize");
        randomize.setLayout(null);
        randomize.setLocation(120, 80);
        randomize.setSize(100, 40);
        randomize.setVisible(true);
        randomize.addActionListener(this);
        
        
       // totalGUI.add(resultArea);
        totalGUI.add(randomize);
        totalGUI.add(solve);
        totalGUI.add(boardGUI);
        totalGUI.setOpaque(true);
        return totalGUI;
    }
	
	 public void actionPerformed(ActionEvent e) {	   
		 	if(e.getSource() == solve)
	        {
		 		Board userBoard = boardGUI.getBoard();
		 		userBoard.setBlank();
	        	AI.astar(userBoard);
	        }
		 	if(e.getSource() == randomize)
		 	{
		 		myBoard.initPuzzle();
		 		boardGUI.setBoard(myBoard);
		 	}
	       
	    }
	
	private static void createAndShowGUI() {
		
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Lance's Eights Puzzle Solver ");

        //Create and set up the content pane.
        Eights demo = new Eights();
        frame.setContentPane(demo.createContentPane());
        
       

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        
        
    }
	
	public static void main (String args[])
	{
		/*
			//new Eights();		
		Board b = new Board();
		Board b1 = new Board();
		b.initPuzzle();			
		b1.initPuzzle();
		b1 = new Board(b);
		b.print();		
		
				
		EightsPuzzleAI AI = new EightsPuzzleAI();		
		AI.astar(b);	
		*/
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	public Eights()
	{
		myBoard = new Board();
		myBoard.initPuzzle();
		boardGUI =new  JBoard();
		boardGUI.setBoard(myBoard);
		  
		AI = new EightsPuzzleAI();
	}
}