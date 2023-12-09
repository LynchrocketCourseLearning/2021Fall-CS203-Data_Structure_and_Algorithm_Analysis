/*
//此题为lab2 f题的一个特殊版，即对两个升序数组，求第m小的数






*/


#include<iostream>
#include<cmath>
using namespace std;

long tc[15][2];

long matrix(long i, long j){
    return i*i + j*j + 12345 * (i - j) + i * j;
}

int MthSmallest(long j1, long j2, int max, long M){//the M-th smallest

    long lo1 = 1, lo2 = 1, hi1 = max, hi2 = max, mid1, mid2;//i is mid, j is setteled by the argument
    long count = 0; //to check whether it is the M-th smallest 

    while(lo1!=hi1 || lo2!=hi2){
        
        mid1 = lo1 + (hi1 - lo1) / 2;
        mid2 = lo2 + (hi2 - lo2) / 2;
        
        if(matrix(mid1, j1) == matrix(mid2, j2))
            return matrix(mid1, j1);
        else if(matrix(mid1, j1) < matrix(mid2, j2)){  
            hi2 = mid2;
            if((hi1 - lo1) % 2) mid1++;         
            lo1 = mid1;
        }else{                                                 //
            hi1 = mid1;
            if((hi2 - lo2) % 2) mid2++;
            lo2 = mid2;
        }

        if(M == count) return matrix(mid1, j1);
    }


    return 0;
}



int main()
{
    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);

    int T;
    cin >> T;

    for(int i = 1; i <= T; i++) cin >> tc[i][0] >> tc[i][1];
    
    long N, M;
    for(int i = 1; i <= T; i++){ 
        N = tc[i][0];
        M = tc[i][1];
        cout << MthSmallest(N, N-1, N, M) <<endl;
    }


    return 0;
}