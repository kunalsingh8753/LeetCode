class Solution {
    public int countMagicalPairs(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int totalMagical=0;
        for(int j=0;j<n-1;j++){
            int[] leftDistCount = new int[27];
            int maskLeft=0;
            int distinctLeft=0;
            for(int i=j;i>=0;i--){
                int bit=1 << (arr[i]-'a');
                if((maskLeft & bit)==0){
                    maskLeft |= bit;
                    distinctLeft++;
                }
                leftDistCount[distinctLeft]++;
            }
            int maskRight=0;
            int distinctRight=0;
            for(int k=j+1;k<n;k++){
                int bit =1 << (arr[k]-'a');
                if((maskRight & bit)==0){
                    maskRight |= bit;
                    distinctRight++;
                }
                totalMagical += leftDistCount[distinctRight];
            }
        }
        return totalMagical;
    }
}