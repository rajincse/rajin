//125. Valid Palindrome 
var msg ="A man, a plan, a canal: Panama";
var isAlphaNumeric = function(c){
	if(c >= '0' && c <='9')
	{
		return true;
	}
	else if(c >='A' && c<='Z')
	{
		return true;
	}
	else if(c >='a' && c<='z')
	{
		return true;
	}
	else{
		return false;
	}
};
var isPalindrome = function(s){
	s = s.toLowerCase();
	
	
	var left =0;
	var right = s.length-1;
	while(left <= right)
	{
		if(!isAlphaNumeric(s.charAt(left)) )
		{
			left+=1;
		}
		else if(!isAlphaNumeric(s.charAt(right)))
		{
			right-=1;
		}
		else if(s.charAt(left) === s.charAt(right))
		{
			left+=1;
			right-=1;
		}
		else
		{
			return false;
		}
	}

	return true;
};
console.log(isPalindrome(msg));