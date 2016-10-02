class Solution:
    # @param board, a 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def solve(self, board):
        row = len(board)
        if row==0:
            return
        col = len(board[0])
        bb = [[False for j in range(0,col)] for i in range(0,row)]
        que = []
        for c in range(0, col):
            if board[0][c] == "O":
                bb[0][c] = True
                que.append([0,c])
            if board[row-1][c] == "O":
                bb[row-1][c] = True
                que.append([row-1,c])
        for i in range(0,row):
            if board[i][0]=='O':
                bb[i][0]=True
                que.append([i,0])
            if board[i][col-1]=='O':
                bb[i][col-1]=True
                que.append([i,col-1])

        while que:
            i = que[0][0]
            j = que[0][1]
            que.pop(0)
            if (i-1>0 and board[i-1][j]=='O' and bb[i-1][j]==False):
                bb[i-1][j]=True
                que.append([i-1,j])
            if (i+1<row-1 and board[i+1][j]=='O' and bb[i+1][j]==False):
                bb[i+1][j]=True
                que.append([i+1,j])
            if (j-1>0 and board[i][j-1]=='O' and bb[i][j-1]==False):
                bb[i][j-1]=True
                que.append([i,j-1])
            if (j+1<col-1 and board[i][j+1]=='O' and bb[i][j+1]==False):
                bb[i][j+1]=True
                que.append([i,j+1])

        for i in range(0,row):
            for j in range(0,col):
                if board[i][j]=='O' and bb[i][j]==False:
                    board[i][j] = 'X'
         
