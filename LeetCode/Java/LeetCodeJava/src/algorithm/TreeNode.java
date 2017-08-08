package algorithm;

import java.util.ArrayList;

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
		if(node.left != null && node.right != null)
		{
			char[][] left = getCharArrayPrint(node.left);
			int m1=0;
			int n1 =0;
			if(left.length != 0)
			{
				m1 = left.length;
				n1 = left[0].length;
			}
			char[][] right = getCharArrayPrint(node.right);
			int m2 =0;
			int n2 =0;
			if(right.length != 0)
			{
				m2 = right.length;
				n2 = right[0].length;
			}

			char[] valueArray = (node.val+"").toCharArray();
			int m = Math.max(m1, m2)+2;
			int n = Math.max(n1+n2+1,valueArray.length);
			char[][] result = new char[m][n];


			int leftRootIndex =-1;
			//left
			for(int i=0;i<m1;i++)
			{
				for(int j=0;j<n1;j++)
				{
					result[2+i][j] = left[i][j];
					if(i==0 && left[i][j] != '\0')
					{
						leftRootIndex = j;
					}
				}
			}


			int rightRootIndex =-1;
			//right
			for(int i=0;i<m2;i++)
			{
				for(int j=0;j<n2;j++)
				{
					result[2+i][n1+1+j] = right[i][j];
					if(i==0 && right[i][j] != '\0')
					{
						rightRootIndex = n1+1+j;
					}

				}
			}
			for(int j=leftRootIndex+1;j<rightRootIndex;j++)
			{
				result[2][j]='-';
			}

			int offset = valueArray.length> (n1+n2+1)?0: (n1+n2+1-valueArray.length)/2;
			//root
			for(int i=0;i<valueArray.length;i++)
			{
				result[0][offset+i] = valueArray[i];
			}
			result[1][n/2] ='|';

			return result;
		}
		else if(node.left != null && node.right ==null)
		{
			char[][] left = getCharArrayPrint(node.left);
			int m1=0;
			int n1 =0;
			if(left.length != 0)
			{
				m1 = left.length;
				n1 = left[0].length;
			}


			char[] valueArray = (node.val+"").toCharArray();
			int m = m1+2;
			int n = Math.max(n1,valueArray.length);
			char[][] result = new char[m][n];


			int leftRootIndex =-1;
			//left
			for(int i=0;i<m1;i++)
			{
				for(int j=0;j<n1;j++)
				{
					result[2+i][j] = left[i][j];
					if(i==0 && left[i][j] != '\0')
					{
						leftRootIndex = j;
					}
				}
			}



			for(int j=leftRootIndex+1;j<n1;j++)
			{
				result[2][j]='-';
			}

			int offset = valueArray.length> (n1)?0: (n1-valueArray.length)/2;
			//root
			for(int i=0;i<valueArray.length;i++)
			{
				result[0][offset+i] = valueArray[i];
			}
			result[1][n/2] ='|';

			return result;
		}
		else if(node.left == null && node.right !=null)
		{
			char[][] right = getCharArrayPrint(node.right);
			int m2 =0;
			int n2 =0;
			if(right.length != 0)
			{
				m2 = right.length;
				n2 = right[0].length;
			}

			char[] valueArray = (node.val+"").toCharArray();
			int m = m2+2;
			int n = Math.max(n2,valueArray.length);
			char[][] result = new char[m][n];





			int rightRootIndex =-1;
			//right
			for(int i=0;i<m2;i++)
			{
				for(int j=0;j<n2;j++)
				{
					result[2+i][j] = right[i][j];
					if(i==0 && right[i][j] != '\0')
					{
						rightRootIndex = j;
					}

				}
			}
			for(int j=0;j<rightRootIndex;j++)
			{
				result[2][j]='-';
			}

			int offset = valueArray.length> (n2)?0: (n2-valueArray.length)/2;
			//root
			for(int i=0;i<valueArray.length;i++)
			{
				result[0][offset+i] = valueArray[i];
			}
			result[1][n/2] ='|';

			return result;
		}
		else
		{
			char[][] result = new char[][]{(node.val+"").toCharArray()};
			return result;
		}

	}


	public static  void main(String[] args)
	{
		String arrayString = "[11111111,2,3,null,8,4,6,null,5,null,null,7]";
		TreeNode node = TreeNode.getTreeNode(arrayString);
		System.out.println(node.getPrintString());
	}

    
}