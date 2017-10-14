//687. Longest Univalue Path 
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
 function TreeNode(val) {
      this.val = val;
      this.left = this.right = null;
 }
var ans =0;
var longestUnivaluePath = function(root) {
	ans = 0;
	getArrowLength(root);
	return ans;
    
};
var getArrowLength = function(root){
	if(root == null) return 0;
	var left = getArrowLength(root.left);
	var right = getArrowLength(root.right);
	var arrowLeft =0;
	var arrowRight =0;
	if(root.left != null && root.left.val == root.val){
		arrowLeft = left+1;
	}
	if(root.right != null && root.right.val == root.val){
		arrowRight = right+1;
	}
	ans = Math.max(ans, arrowLeft+arrowRight);
	return Math.max(arrowLeft, arrowRight);
};