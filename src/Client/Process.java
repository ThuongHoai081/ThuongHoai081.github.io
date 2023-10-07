package Client;

import java.awt.Color;

import javax.swing.JOptionPane;


public class Process {
	private Client main;
	MainGame mainGame;
	Box[] box=new Box[12];
	ScoreBox[] scBox=new ScoreBox[2];
	int [] sr = new int[2];
	
	public Process(MainGame m, Client main){
		mainGame=m;
		this.main = main;
	}
	public void init() {
		for(int i=0;i<12;i++) box[i]=new Box(mainGame,this,i);
		scBox[0]=new ScoreBox(mainGame,0);
		scBox[1]=new ScoreBox(mainGame,1);
	}
	public void reDraw() {  
		//reset game.
		mainGame.currentTeam=1;
		for(int i=0;i<12;i++) box[i].change(mainGame.getNumberInBox());
		box[5].change(mainGame.getNumberInScoreBox());
		box[11].change(mainGame.getNumberInScoreBox());
		scBox[0].change(0);
		scBox[1].change(0);
		scBox[1].setTurn(true);
	}
	public void resize() {
		for(int i=0;i<12;i++) box[i].resize();
		scBox[0].resize();
		scBox[1].resize();
	}
	public void removeAllArrow() {
		System.out.println("remove all");
		for(int i=0;i<12;i++) box[i].removeArrow();
	}
	public void move(int pos,int direction, boolean isEaten){
		if(pos==5||pos==11) {
			  mainGame.nextTurn();
		}
		else if (box[pos].getNum()>0){
			if(!isEaten){
				int num=box[pos].getNum();
				box[pos].setColor(Color.BLUE);
				for(int i=1;i<=num;i++) {
					int temp=calNewPos(pos,i*direction);
					box[pos].change(box[pos].getNum()-1);
					box[temp].change(box[temp].getNum()+1);
				}
				int vtSau=calNewPos(pos,(num+1)*direction);
				move(vtSau,direction,false);
			}else {
				mainGame.nextTurn();
			}
		}else{
			if(box[calNewPos(pos,direction)].getNum()>0){
				kill(mainGame.getCurTeam(),calNewPos(pos,direction));
				move(calNewPos(pos,direction*2),direction,true);
			}else {
				mainGame.nextTurn();
			}
		}
		
	}
	public int check(int team) {
		if(box[5].getNum()==0&&box[11].getNum()==0) return -1;
		if(scBox[team].getNum()<5&&total(team)==0) 	return 0;
		if(scBox[team].getNum()>5&&total(team)==0) 	return 1;
		return 2;
	}
	int total(int team) {
		if(team==0)	
			return box[6].getNum()+box[7].getNum()+box[8].getNum()+box[9].getNum()+box[10].getNum();
		else 
			return box[0].getNum()+box[1].getNum()+box[2].getNum()+box[3].getNum()+box[4].getNum();
	}
	int calNewPos(int src,int step){
		return (src+1200+step)%12;
	}
	void kill(int team,int pos){
		int boxTemp=box[pos].getNum();
		for(int i=0;i<boxTemp;i++) {
			box[pos].change(box[pos].getNum()-1);
			scBox[team].change(1+scBox[team].getNum());
		}
		
	}
	void nextTurn() {
		int result=check(mainGame.getCurTeam());
		if(result==-1) {
			int winTeam;
			if(scBox[0].getNum()>scBox[1].getNum())
			{
				   main.getThread().doSendData("End",ViewPlay.namePlayer2.getText(),String.valueOf(scBox[0].getNum()));
				   winTeam=0;
				   
			}
			else if(scBox[0].getNum()<scBox[1].getNum()) {
				 main.getThread().doSendData("End",ViewPlay.namePlayer1.getText(),String.valueOf(scBox[1].getNum()));
				winTeam=1;
			}
			else {
				
				winTeam=-1;
			}
			victory(winTeam);
		}
		if(result==0) victory(1-mainGame.getCurTeam());
		if(result==1) spread(mainGame.getCurTeam());
		scBox[mainGame.getCurTeam()].setTurn(true);
		scBox[1-mainGame.getCurTeam()].setTurn(false);
		
		main.getThread().doSendData("reset",ViewPlay.namePlayer1.getText(),String.valueOf( box[0].getNum()),String.valueOf( box[1].getNum()),String.valueOf( box[2].getNum()),String.valueOf( box[3].getNum()),
				String.valueOf( box[4].getNum()),String.valueOf( box[5].getNum()),String.valueOf( box[6].getNum()),String.valueOf( box[7].getNum()),String.valueOf( box[8].getNum()),
				String.valueOf( box[9].getNum()),String.valueOf( box[10].getNum()),String.valueOf( box[11].getNum()),String.valueOf( scBox[1].getNum()),String.valueOf( scBox[0].getNum()));
		
	
	}
	
	void victory(int team) {
	
		if(team>-1) 
			if(team == 0)
			 JOptionPane.showConfirmDialog(mainGame,"Thua rồi!!","",JOptionPane.DEFAULT_OPTION);
			else if(team == 1)
			 JOptionPane.showConfirmDialog(mainGame,"Bạn đã thắng!!","Chuc mung",JOptionPane.DEFAULT_OPTION);
		else 
			JOptionPane.showConfirmDialog(mainGame,"Hoà!!","Game over",1);
		
		 int result = JOptionPane.showConfirmDialog(mainGame,
                 "Chơi lại!",
                 "Xác nhận",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE);
         if(result == JOptionPane.YES_OPTION){
        	 reDraw();
         }else if (result == JOptionPane.NO_OPTION){
        	 main.getThread().doSendData("Back");
         }
	}
	void spread(int team) {
		if(team==0)
			for(int i=6;i<11;i++) boxSpread(team,i);
		else 
			for(int i=0;i<5;i++) boxSpread(team,i);
	}
	void boxSpread(int team, int pos) {
		
		scBox[team].change(scBox[team].getNum()-1);
		box[pos].change(1);
		
	}
}

