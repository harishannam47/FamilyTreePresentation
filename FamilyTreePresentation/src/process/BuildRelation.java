package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.MemberNode;

public class BuildRelation {

	
	public List<MemberNode> buildMembersFromInput(String input)
	{
		if(input!=null &&! input.isEmpty() && !input.contains("|"))
		{
			System.out.println(" Please check the input "+input);
			return null;
		}else{
			List<MemberNode> members = new ArrayList();
			for(String memberData :input.split("\\|"))
			{
				if(memberData.contains(","))
				{
					System.out.println(" Here s "+memberData);
					String[] memberinfo = memberData.split(",");
					MemberNode member = new MemberNode();
					member.setParentId(Integer.parseInt(memberinfo[0]));
					member.setNodeId(Integer.parseInt(memberinfo[1]));
					member.setRelation(memberinfo[2]);
					
					members.add(member);
				}
			}
			
			return members;
		}
	}
	
	
	public Map<Integer, List<MemberNode>> buildRelation(List<MemberNode> membersList){
		
		Map<Integer, List<MemberNode>> relationMap=new HashMap<>();
		for(MemberNode node : membersList)
		{
			if(node.getParentId()!=0 && relationMap.containsKey(node.getParentId())){
				System.out.println(" here s "+node.getParentId());
					relationMap.get(node.getNodeId()).add(node);
			}else{
				List<MemberNode> buildList = new ArrayList();
				buildList.add(node);
				relationMap.put(node.getNodeId(),buildList );
			}
				
		}
		return relationMap;
		
	}
	
	public void print(Map<Integer, List<MemberNode>> relationMap){
		
		for(int nodeId: relationMap.keySet())
		{
			int i=0;
			for(MemberNode node: relationMap.get(nodeId))
			{
				if(i==0)
				{
					System.out.println(node.getRelation()+"  has child(s) ");
				}else{
					System.out.println(" --> "+node.getRelation());
				}
				i++;
			}
		}
	}
	
	public static void main(String args[])
	{
		String input="0,0,grandpa|0,1,Son|0,2,Daughter|1,3,Grandkid|1,4,Grandkid|2,5,Grandkid|5,6,GreatGrandKid";
		BuildRelation br = new BuildRelation();
		br.print(br.buildRelation(br.buildMembersFromInput(input)));
	}
	

}
