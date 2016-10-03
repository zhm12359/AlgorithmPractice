class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """

        stack = []

        if(len(s)%2 != 0):
            return False

        for c in s:
            if c in ['(', '[','{']:
                stack.append(c)
            elif c == ')':
                if not stack: return False
                p = stack.pop()
                if p != '(':
                    return False
            elif c == ']':
                if not stack: return False
                p = stack.pop()
                if p != '[':
                    return False
            elif c == '}':
                if not stack: return False
                p = stack.pop()
                if p != '{':
                    return False
        if stack:
            return False
        else:
            return True
