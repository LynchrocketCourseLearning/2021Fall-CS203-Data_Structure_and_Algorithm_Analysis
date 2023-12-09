/*
此题为lab2 f题解

*/

#include<iostream>
#define ll long long
using namespace std;

ll tc[20][2];

ll matrix(ll i, ll j){
    return i*i + j*j + 12345 * (i - j) + i * j;
}

bool BinarySearch(ll mid, ll M, ll N){
    ll cnt = 0;
    for(int j = 1; j <= N; j++){
        int lo = 1, hi = N, res = 0;
        while(lo <= hi){
            int i = lo + (hi - lo) / 2;
            if(matrix(i, j) <= mid){
                res = i;
                lo = i + 1;
            }
            else hi = i - 1;
        }
        cnt += res;
    }
    return cnt >= M;
}
int main(){
    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);
    
    int T;
    cin >> T;
    for(int i = 1; i <= T; i++) cin >> tc[i][0] >> tc[i][1];
    
    long N, M;
    for(int i = 1; i <= T; i++){ 
        N = tc[i][0];
        M = tc[i][1];

        ll lo = -12345*N;
        ll hi = 3*N*N+12345*N;
        ll res = 0;
        while(lo <= hi){
            ll mid = lo + (hi - lo)/2;
            if(BinarySearch(mid, M, N)){ 
                res = mid;
                hi = mid - 1;
            }else lo = mid + 1;
        }
        cout << res << endl;
        
    }
    return 0;
}
