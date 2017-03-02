package main

import (
	"fmt"
	"strings"
)
func printString(s  string) {
	fmt.Printf("Printing %v( %v): ", s, len(s))
	for i:=0 ;i < len(s) ; i++{
		fmt.Printf("%v,", string(s[i]))
	}
	fmt.Println()
}

func findLongestWord(s string, d []string) string {
	maxLength := -1
	maxIndex := -1
	
	for i :=0; i< len(d) ;i++{
		
		if isMatch(s, d[i]) {
			l := len(d[i])
			if l > maxLength {
				maxLength = l
				maxIndex = i	
			} else if l == maxLength && strings.Compare(d[i], d[maxIndex]) < 0 {
				maxIndex = i	
			}
			
		}
	}
	if maxIndex <0 {
		 return ""
	} else {
		return d[maxIndex]
	}
}

func isMatch( s string, dictionaryWord string) bool{
	
	j:=0
	for i:=0; i<len(s) ;i++ {
		
		if s[i] == dictionaryWord[j]{
			j++
			if j == len(dictionaryWord){
				return true
			} 
		}
		
	}
	
	return false;
}
