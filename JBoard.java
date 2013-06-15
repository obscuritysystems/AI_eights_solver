package eights;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class JBoard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<JTextField> box;
	public int MAX_BOARD;
	
	public JBoard(){
	 this.MAX_BOARD = 9;
     this.setLayout(null);     
     this.setLocation(10, 0);
     this.setSize(200, 200);
     
     generateBox();
   
	}
	
	public Board getBoard()
	{
		Board aboard = new Board();
		
		for(int i = 0;i < MAX_BOARD-1; i++){
			aboard.array[i] = Integer.parseInt(box.get(i).getText().trim());
		}
		
		return aboard;
	}
	public void setBoard(Board b)
	{
		int i =0;
		for(JTextField position : box){
			position.setText(Integer.toString(b.array[i]));
			i++;
		}
	}
	private void generateBox()
	{
		box = new Vector<JTextField>();
		JTextField position;
		int k =0;
		int j =0;
		for(int i = 0; i < MAX_BOARD; i++){
			
			    // Creation of a Panel to contain the title labels
			    position = new JTextField();
			    position.setLocation((j*15)+10, (k*20)+10);			    
			    position.setSize(15, 20);
			    position.setText("0");
			    position.setHorizontalAlignment(0);
			    //position.setDocument(new JTextFieldLimit(1));
			    j++;
			    box.add(position);
			    				    
			    if(i == 2 || i == 5){
			    	j = 0;
			    	k++;
			    }
		}
		
		for(JTextField pos : box){			
			this.add(pos);
		}			
	}

}
