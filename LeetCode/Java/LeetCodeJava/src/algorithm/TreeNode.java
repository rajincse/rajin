package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    @Override
	public String toString() {
		// TODO Auto-generated method stub
	
   		 String msg ="{val:"+val;
   		 
   		 String left = "";
   		 if(this.left != null)
   		 {
   			left =this.left.toString();
   		 }
   		 else {
   			left = "null"; 
   		 }
   		 msg+=", left:"+left;
   		 
   		String right = "";
  		 if(this.right != null)
  		 {
  			right =this.right.toString();
  		 }
  		 else {
  			right = "null"; 
  		 }
   		 msg+=", right:"+right+"}";
   		 
   		 return msg;
   		 
   	 
	}

	public static TreeNode getTreeNode(String arrayString)
	{
		if(arrayString ==null || arrayString.equals("") || arrayString.equals("[]"))
		{
			return null;
		}
		arrayString = arrayString.substring(1,arrayString.length()-1);
		String[] values  = arrayString.split(",");
		if(values.length==0)
		{
			return null;
		}
		else
		{
			Integer[] intValues = new Integer[values.length];

			for(int i=0;i<values.length;i++)
			{
				if(values[i].trim().equals("null"))
				{
					intValues[i] = null;
				}
				else
				{
					intValues[i] = Integer.parseInt(values[i].trim());
				}
			}
			TreeNode root = new TreeNode(intValues[0]);
			ArrayList<TreeNode> treeQueue = new ArrayList<TreeNode>();
			ArrayList<Integer> indexQueue = new ArrayList<Integer>();

			treeQueue.add(root);
			indexQueue.add(0);

			int nullCount =0;
			while (!treeQueue.isEmpty())
			{
				TreeNode node = treeQueue.remove(0);
				Integer index = indexQueue.remove(0);

				//left
				if(2*index+1 < intValues.length && intValues[2*index+1] != null)
				{
					TreeNode left = new TreeNode(intValues[2*index+1]);
					node.left = left;
					treeQueue.add(left);
					indexQueue.add(2*index+1-nullCount);
				}
				else
				{
					nullCount++;
				}
				//right
				if(2*index+2 < intValues.length && intValues[2*index+2] != null)
				{
					TreeNode right = new TreeNode(intValues[2*index+2]);
					node.right = right;
					treeQueue.add(right);
					indexQueue.add(2*index+2-nullCount);
				}
				else{
					nullCount++;
				}
			}

			return root;
		}



	}
	public String getPrintString()
	{
		StringBuilder printString = new StringBuilder();
		char[][] charArray = getCharArrayPrint(this);

		for(int i=0;i<charArray.length;i++)
		{
			//System.out.println(Arrays.toString(charArray[i]));
			for(int j=0;j<charArray[i].length;j++)
			{
				printString.append(charArray[i][j]);
			}
			printString.append('\n');
		}

		return printString.toString();
	}
	private char[][] getCharArrayPrint(TreeNode node)
	{
		if(node ==null)
		{
			return null;
		}

		char[][] left = getCharArrayPrint(node.left);
		int m1=0;
		int n1 =0;
		if(left != null && left.length != 0)
		{
			m1 = left.length;
			n1 = left[0].length;
		}
		char[][] right = getCharArrayPrint(node.right);
		int m2 =0;
		int n2 =0;
		if(right != null && right.length != 0)
		{
			m2 = right.length;
			n2 = right[0].length;
		}

		String nodeValueString =node.val+"";
		if(nodeValueString.length()%2==0) // if even length, make it odd
		{
			nodeValueString+="-";
		}
		char[] valueArray = nodeValueString.toCharArray();
		int r = valueArray.length;
		int d = (n1 | n2) > 0? 1:0; // if any leaf exist then atleast a single dash to separate them
		int v = (n1 | n2) > 0? 1:0; // if any leaf exist then a vertical dash

		int m = Math.max(m1, m2)+1+v;
		int n = r;
		if(v > 0)
		{
			int leftSize = n1>(r-1)/2? n1: (r-1)/2;
			int rightSize = n2>(r-1)/2? n2: (r-1)/2;

			n= leftSize+1+rightSize;

		}


		char[][] result = new char[m][n];
		for(int i=0;i<result.length;i++) // all characters are space
		{
			for(int j=0;j<result[i].length;j++)
			{
				result[i][j] =' ';
			}
		}
		int rootOffset = (r-1)/2> n1?0: Math.abs((r-1)/2-n1);
		int childrenOffset = (r-1)/2> n1?Math.abs((r-1)/2-n1):0;


		int leftRootIndex =rootOffset+r/2-d;
		//left
		for(int i=0;i<m1;i++)
		{
			for(int j=0;j<n1;j++)
			{
				result[1+v+i][childrenOffset+j] = left[i][j];
				if(i==0 && left[i][j] != ' ' && v >0) // get left subtree root
				{
					leftRootIndex = childrenOffset+j;
				}
			}
		}


		int rightRootIndex =rootOffset+(r-d)/2+1;
		//right
		for(int i=0;i<m2;i++)
		{
			for(int j=0;j<n2;j++)
			{
				result[1+v+i][rootOffset+(r-1)/2+d+j] = right[i][j];
				if(i==0 && rightRootIndex<= rootOffset+r/2-d && right[i][j] != ' ' && v >0) // get right sub tree root
				{
					rightRootIndex = rootOffset+(r-1)/2+d+j;
				}

			}
		}

		if(v <m-1 ) //set horizontal dashes from left to right child
		{
			if(m2 > 0)
			{
				for(int j=leftRootIndex+1;j<result[1+v].length;j++)
				{
					if(result[1+v][j] >= '0')
					{
						break;
					}
					result[1+v][j]='-';
				}
			}
			else // if right child do not exist
			{
				result[1+v][leftRootIndex+1]='-';
			}

		}


		//root
		for(int i=0;i<valueArray.length;i++)
		{
			result[0][rootOffset+i] = valueArray[i];
		}
		if(v >0)
		{
			result[1][rootOffset+r/2] ='|';
		}

		return result;

	}


	public static  void main(String[] args)
	{
		String arrayString = "[1111,null,2]";//"[5,4,8,11,null,13,4,7,2,null,null,null,1]";//"[5,4,1,null,null,1,2]";//"[11,2]";//"[5,4,8,11,13,4,7,2,null,1]"
		TreeNode node = TreeNode.getTreeNode(arrayString);

		System.out.println(node ==null?"null":node.getPrintString());
	}

    
}