int scoreOfParentheses(char* s) {
     int n=strlen(s);
            int stack[n];
            int top=-1;
            int cur=0;
            for(int i=0; s[i]!='\0';i++){
                if(s[i]=='('){
                    top++;
                    stack[top]=cur;
                    cur=0;
                }
                else{
                    int lastscore=stack[top--];
                    if(cur==0){
                        cur=lastscore+1;
                    }
                    else{
                        cur=lastscore + cur*2;
                    }
                }
            }        
    return cur;
}