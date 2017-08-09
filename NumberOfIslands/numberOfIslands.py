
def numIslands(grid):
    """
    :type grid: List[List[str]]
    :rtype: int
    """
    row = len(grid)
    if row == 0: return 0
    col = len(grid[0])

    lands = []
    for r in range(0, row):
        for c in range(0, col):
            if grid[r][c]=="1":
                lands.append([r,c])
    if not lands: return 0

    counter = 0
    block = [ lands.pop() ]

    while block:
        p = block.pop()
        i = p[0]
        j = p[1]

        if i+1 <= row - 1 and [i+1, j] in lands:
            lands.remove([i+1,j])
            block.append([i+1,j])

        if i-1 >= 0 and [i-1, j] in lands:
            lands.remove([i-1,j])
            block.append([i-1,j])

        if j+1 <= col - 1 and [i, j+1] in lands:
            lands.remove([i,j+1])
            block.append([i,j+1])

        if j-1 >= 0 and [i, j-1] in lands:
            lands.remove([i,j-1])
            block.append([i,j-1])

        if len(block) == 0:
            counter += 1
        if len(block) == 0 and len(lands) != 0:
            block.append(lands.pop())

    return counter
