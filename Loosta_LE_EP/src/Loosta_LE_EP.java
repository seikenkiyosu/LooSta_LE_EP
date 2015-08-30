import java.util.Random;
import Agent.Agent_EP;
import Interaction.Interaction_EP;

class Loosta_LE_EP_WF{
	public static final int Roundnum = 10000000;
	
	public static final int s = 192;			//96�ȏ��3n�ȏ�
	public static final int n = 30;	

	public static void main(String args[]){
		Random random = new Random();
    	Agent_EP agent[] = new Agent_EP[n];
		int CT = 0, HT = 0;
		boolean HT_count_flag = false, CT_count_flag = true;
		
		//Agentの初期化
		for(int i=0; i<n; i++)
		agent[i] = new Agent_EP(random.nextBoolean(), s);
		
		for(int i=0; i<Roundnum; i++){
			int leadercount=0;
//			int leaderid=0;
			//リーダの数をかぞえる
			for(int j=0; j<n; j++) if(agent[j].IsLeader()){ leadercount++; }
			
			//Holding Timeが終了したらぬける
			if(leadercount!=1 && HT_count_flag==true){ break; }
			
			if(leadercount==1){ 
						HT_count_flag = true;
						CT_count_flag = false;
					}
			
			if(HT_count_flag==true) HT++;

//			System.out.println("the number of leaders = " + leadercount);
			int p=0, q=0;
			while(p==q){					//�𗬂�����̂�I��
				p = random.nextInt(n);		//�����_����agent���Ƃ��Ă���
				q = random.nextInt(n);		//p�Ƌ���1�ȓ��ɂ���m�[�h�̒���(���id�̒Ⴂ)�m�[�h��q�ɑ��
			}
			
			Interaction_EP.interaction(agent[p], agent[q], s);	//�𗬂�����
			for(int j=0; j<n; j++) agent[j].Countdown();	//timer���J�E���g����

			if(CT_count_flag==true) CT++;
		}
		System.out.println("CT = " + CT);
		System.out.println("HT = " + HT);
	}
}
