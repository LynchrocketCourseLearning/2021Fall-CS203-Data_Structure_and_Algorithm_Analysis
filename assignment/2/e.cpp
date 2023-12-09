#include<iostream>
using namespace std;
const int N = 1e5+10;

long al[N], bl[N];
int tc[N][2];


int midNum(long a[], long b[], int tc1, int tc2){

    int lo1=tc1,lo2=tc1,hi1=tc2,hi2=tc2,mid1,mid2;
    
    while(lo1!=hi1 || lo2!=hi2){
        
        mid1=lo1+(hi1-lo1)/2;
        mid2=lo2+(hi2-lo2)/2;
        
        if(a[mid1]==b[mid2])
            return a[mid1];
        else if(a[mid1]<b[mid2]){  
            hi2=mid2;
            if((hi1-lo1)%2) mid1++;         
            lo1=mid1;
        }else{               
            hi1=mid1;
            if((hi2-lo2)%2) mid2++;
            lo2=mid2;
        }
    }
    
    return min(a[lo1],b[lo2]);
}



int main()
{
    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);

    int n, T;
    cin >> n >> T;

    for(int i = 1; i <= n; i++) cin >> al[i];
    for(int i = 1; i <= n; i++) cin >> bl[i];
    for(int i = 1; i <= T; i++) cin >> tc[i][0] >> tc[i][1];
    
    for(int i = 1; i <= T; i++){ 
        cout << midNum(al,bl,tc[i][0],tc[i][1]) <<endl;
    }


    return 0;
}