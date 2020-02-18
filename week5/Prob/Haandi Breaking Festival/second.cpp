#include<iostream>
#include<vector>
#include<map>
using namespace std;
int func(vector<int> digits,map<vector<int>,int> &m){
   if (digits.size()==1){
       return digits[0];
   }
   if (m[digits]!=0) return m[digits];// getting max of previous vector from dp table
   else {
       int max=-1;
       int sum;
       for(int i=0;i<digits.size();i++){
           int prod=1;
           if (i==0)                prod=digits[i]*digits[i+1];
           else if (i==digits.size()-1)  prod=digits[i-1]*digits[i];
           else                     prod=digits[i]*digits[i+1]*digits[i-1];
           vector<int> temp=digits;
           temp.erase(temp.begin()+i);
           sum=prod+func(temp,m);
            if(sum>max) max=sum;
       }

       m.insert({digits,max});//storing in 
       m[digits]=max;// dp table
       return max;
   }
}
int main(){
    int n;
    cin>>n;
    vector<int> digits(n);
    map<vector<int>,int> m;  // map to store the previous vectors visited 
    for(int i=0;i<n;i++){
        cin>>digits[i];
    }
    int sum=0;
    cout<<func(digits,m);
    cout<<endl;
    
}