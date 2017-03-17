package main

import (
	"fmt"
	"strconv"
)
func isUnique(abbreviatedDict [] string) (map[string] [] int, bool) {
	keyMap:= make( map[string] [] int)
	isUniqueResult := true
	for i:=0 ; i< len(abbreviatedDict);i++{
		keyMap[abbreviatedDict[i]] = append(keyMap[abbreviatedDict[i]], i)
		if isUniqueResult && len(keyMap[abbreviatedDict[i]]) > 1{
			isUniqueResult = false
		}
	}
	
	return keyMap, isUniqueResult
	
}
func wordsAbbreviation(dict []string) []string {
    output :=make( [] string, len(dict))
    abbreviationList := make( [] int, len(dict))
    for i:=0; i<len(dict); i++{
    	if len(dict[i]) > 3 {
    		abbreviationList[i] = len(dict[i])-2
	    	output[i] = string(dict[i][0])+strconv.Itoa(abbreviationList[i])+string(dict[i][len(dict[i])-1])	
    	} else {
    		abbreviationList[i] = 0
    		output[i] = dict[i]
    	}
    }
    
    keyMap, isUniqueResult:= isUnique(output)
    
    fmt.Printf("newOut-> %v, %v , %v\n",  output, keyMap, isUniqueResult)
    for !isUniqueResult {
    	for key, indices := range keyMap{
    		fmt.Printf("%v = %v(%v)\n",key, indices, isUniqueResult)
    		 if len(indices) > 1 {
    		 	
    		 	for _,i := range indices {
    		 		fmt.Printf("%d, %v => %d\n",i, output[i],abbreviationList[i]) 
    		 		abbreviationList[i] --;
    		 		if abbreviationList[i] > 1 {
    		 			output[i] = string(dict[i][0:len(dict[i]) -abbreviationList[i]-1 ])+strconv.Itoa(abbreviationList[i])+string(dict[i][len(dict[i])-1])	
    		 		} else {
    		 			output[i] = dict[i]
    		 		}
    		 	}
    		 }
    	}
    	keyMap, isUniqueResult= isUnique(output)
    	fmt.Printf("newOut-> %v, %v , %v\n",  output, keyMap, isUniqueResult)
    }
    
    return output
}

//func main() {
//	dict := [] string {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"}
//	
//	ans := wordsAbbreviation(dict)
//	
//	fmt.Printf("%v ( %d)=>\n%v (%d)", dict, len(dict), ans, len(ans))
//	
//}