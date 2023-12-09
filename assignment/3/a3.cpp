#include <iostream>
#define ll long long
using namespace std;

ll a[100010], b[100010], tmp[200010]；// tc[100][2];


ll merge(ll a[], int alen, ll b[], int blen){

    ll count = 0;
    int len = alen + blen;
    int i = 0, j = 0, k = 0;//i -> a; j -> b; k -> tmp

    while(i < alen && j < blen){
        if(a[i] > b[j]){
            count +=  (alen - i);
            tmp[k++] = b[j++];
        }else{
            tmp[k++] = a[i++];
        }
    }
    //cout << i << " " << j << endl;
    while(i < alen) {
        tmp[k++] = a[i++];
        //cout<<tmp[k-1];
    }
    while(j < blen) {
        tmp[k++] = b[j++];
        //cout<<tmp[k-1];
    }
    //cout << k << endl;
    return count;
}

int main() {
    ios::sync_with_stdio(0),cin.tie(0),cout.tie(0);
    int T;
    cin >> T;
   
    int n, m;
    for (int i = 0; i < T; i++) {
        //cin >> tc[i][0] >> tc[i][1];
        //n = tc[i][0], m = tc[i][1];
        cin >> n >> m;
        for (int j = 0; j < n; j++) cin >> a[j];
        for (int j = 0; j < m; j++) cin >> b[j];
       
        
        cout << merge(a, n, b, m) << endl;
        for (int j = 0; j < n + m; j++) cout << tmp[j] << " ";
        
        cout << endl;
    }

    

    return 0;

}
