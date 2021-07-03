#include <iostream>
#include <fstream>
#include <vector>
#include <ctime>
#include <algorithm>
#pragma GCC optimize("O3")
#pragma GCC target("avx,avx2,fma")
#pragma warning(disable : 4996)

using namespace std;
short isElephant[10][10];
bool visited[10][10];
int m, n, remain;
vector<vector< pair<short,short> >> result;
vector<pair<short, short>> pos;
vector<pair<short,short>> arr;
void add(short x, short y)
{
    isElephant[x][y] = 1;
    remain--;
    short nx = x, ny = y;
    while (++nx < m && ++ny < n)
    {
        if (visited[nx][ny])
            break;
        if (!isElephant[nx][ny])
            remain--;
        ++isElephant[nx][ny];
    }
    while (++x < m && --y >= 0)
    {
        if (visited[x][y])
            break;
        if (!isElephant[x][y])
            remain--;
        ++isElephant[x][y];
    }
}
void del(short x, short y)
{
    isElephant[x][y] = 0;
    remain++;
    short nx = x, ny = y;
    while (++nx < m && ++ny < n)
    {
        if (visited[nx][ny])
            break;
        --isElephant[nx][ny];
        if (!isElephant[nx][ny])
            remain++;
    }
    while (++x < m && --y >= 0)
    {
        if (visited[x][y])
            break;
        --isElephant[x][y];
        if (!isElephant[x][y])
            remain++;
    }
}
void DFS(short index)
{
    int delta = 0;
    for (int k = index; k < pos.size(); ++k)
    {
        if (arr.size() + remain < result.back().size())
            break;
        short i = pos[k].first;
        short j = pos[k].second;
        if (!isElephant[i][j])
        {
            arr.push_back(pos[k]);
            add(i, j);
            DFS(k + 1);
            if (arr.size() > result.back().size())
            {
                result.clear();
                result.push_back(arr);
            }
            else if (arr.size() == result.back().size())
            {
                result.push_back(arr);
            }
            del(i, j);
            arr.pop_back();
            delta++;
            remain--;
        }
    }
    remain += delta;
}

bool used[10][10];
void FillComponent(short x, short y)
{
    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || used[x][y])
        return;
    used[x][y] = true;
    pos.emplace_back(x, y);
    FillComponent(x - 1, y - 1);
    FillComponent(x - 1, y + 1);
    FillComponent(x + 1, y - 1);
    FillComponent(x + 1, y + 1);
}

vector<vector<vector< pair<short,short> >>> parts;
vector<vector< pair<short,short> >> answer;
void GenAnswer(int index)
{
    if (index == parts.size())
    {
        answer.push_back(arr);
        sort(answer.back().begin(), answer.back().end());
        return;
    }
    for (int i = 0; i < parts[index].size(); i++)
    {
        for (int j = 0; j < parts[index][i].size(); j++)
            arr.push_back(parts[index][i][j]);
        GenAnswer(index + 1);
        for (int j = 0; j < parts[index][i].size(); j++)
            arr.pop_back();
    }
}

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int k;
    cin >> m >> n;
    for (int i = 0; i < m; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            cin >> visited[i][j];
        }
    }
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (!visited[i][j] && !used[i][j])
            {
                pos.clear();
                FillComponent(i, j);
                remain = pos.size();
                sort(pos.begin(), pos.end());
                result.push_back(vector<pair<short,short>>());
                DFS(0);
                parts.push_back(move(result));

            }
    GenAnswer(0);
    sort(answer.begin(), answer.end());
    cout << answer.size() << "\n" << answer[0].size() << "\n";
    for (int i = 0; i < answer.size(); ++i)
    {
        for (int j = 0; j < answer[i].size(); ++j)
        {
            cout << answer[i][j].first << " " << answer[i][j].second;
            if (j + 1 != answer[i].size())
                cout << " ";
        }
        if (i + 1 != result.size())
        {
            cout << "\n";
        }
    }
    return 0;
}