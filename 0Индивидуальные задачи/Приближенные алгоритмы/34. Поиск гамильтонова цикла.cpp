#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
#pragma GCC optimize("Ofast") 
#pragma GCC target("avx,avx2,fma")
#pragma warning(disable : 4996)
using namespace std;
int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
 
    cin.tie(0);
    ios_base::sync_with_stdio(0);

    int  n, x, y;
    cin >> n;
    vector<pair<int, int>> vert(n+1);
    for (int i = 1; i <= n; i++) {
        cin >> x >> y;
        vert[i] = make_pair(x, y);
    }
    vector<vector<int>> costMatrix(n + 1, vector<int>(n + 1));
    for (int i = 1; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
            costMatrix[i][j] = costMatrix[j][i] = abs(vert[i].first - vert[j].first)
                + abs(vert[i].second - vert[j].second);
        }
    }
    bool* visited = new bool[n + 1]{ 0 };
    int* arr = new int[n + 1]{ 0 };
   
    int k = n / 2;
    int l = 0;
    int cost = 0;
    for (int i = 1; i < n; i++) {
        int min = 1e9;
        int l = k;
        
        for (int j = 1; j <= n; j++) {
            if (costMatrix[l][j]!=0 && costMatrix[l][j] < min && !visited[j]) {
                min = costMatrix[l][j];
                k = j;
            }
        }
        visited[l] = true;
        cost += min;
        arr[i] = k;
    }
    cost += abs(vert[n / 2].first - vert[k].first) + abs(vert[n / 2].second - vert[k].second);
    cout << cost << "\n";
    cout << n / 2 <<" ";
    for (int i = 1; i < n; ++i) {
        cout << arr[i] << " ";
    }
    cout << n / 2 << " ";
}