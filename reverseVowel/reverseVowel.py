class Solution(object):
    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        sl = list(s)
        i = 0
        j = len(s) - 1

        while( i < j ):

            if sl[i] in 'aeiouAEIOU' and sl[j] in 'aeiouAEIOU':

                sl[i],sl[j] = sl[j],sl[i]

                i = i +1
                j = j -1
            elif sl[i] in 'aeiouAEIOU':
                j = j-1
            else:
                i = i+1

        s = "".join(sl)
        return s
