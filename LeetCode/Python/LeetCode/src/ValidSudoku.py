class Solution(object):
    def printBoard(self, board):
        str='';
        for i in range(len(board)):
            for j in range(len(board[i])):
                item = board[i][j];
                str += item+' ';
            str+='\n';
        print '%s' % str;
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
#         self.printBoard(board);
        rowMap ={};
        columnMap ={};
        boxMap ={};
        
        
        for i in range(len(board)):
            for j in range(len(board[i])):
                item = board[i][j];
                if item != '.':
                    if item not in rowMap:                        
                        rowMap[item] = [];
                        rowMap[item].append(i);
                    elif i not in rowMap[item]:
                        rowMap[item].append(i);
                    else:
#                         print ' invalid for %s in row %d' % ( item, i);
                        return False;
                    if item not in columnMap:                        
                        columnMap[item] = [];
                        columnMap[item].append(j);
                    elif j not in columnMap[item]:
                        columnMap[item].append(j);
                    else:
#                         print ' invalid for %s in column %d' % ( item, j);
                        return False;
                    
                    boxIndex = int(i/ 3)*3 + int(j/3);
                    if item not in boxMap:                        
                        boxMap[item] = [];
                        boxMap[item].append(boxIndex);
                    elif boxIndex not in boxMap[item]:
                        boxMap[item].append(boxIndex);
                    else:
#                         print ' invalid for %s in box %d ( %d, %d, %s)' % ( item, boxIndex, i, j, boxMap[item]);
                        return False;
                
        
        return True;
board =[".87654321","2........","3........","4........","5........","6........","7........","8........","9........"];
result = Solution().isValidSudoku(board);

print ' %s= %s' % (board, result);