class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> op=new Stack<>();
        Stack<Integer> sta=new Stack<>();


        for(int i=0; i<s.length(); i++){
            char ch=s.charAt(i);
            if(ch=='(')
            op.push(i);
            else if(ch=='*')
            sta.push(i);
            else{
                if(!op.isEmpty()){
                    op.pop();
                }
                else if(!sta.isEmpty())
                sta.pop();
                else
                return false;
            }
        }
        while(!op.isEmpty() && !sta.isEmpty()){
            if(op.peek()>sta.peek()){
                return false;
            }
            op.pop(); 
            sta.pop();
        }
        return op.isEmpty();
     }
}