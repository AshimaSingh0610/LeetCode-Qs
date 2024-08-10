class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {
    set<pair<long long, long long>> corners;
    int minx = INT_MAX;
    int miny = INT_MAX;
    int maxx = INT_MIN;
    int maxy = INT_MIN;
    long long area = 0;
    
    for (const auto& rect : rectangles) {
        minx = min(minx, rect[0]);
        miny = min(miny, rect[1]);
        maxx = max(maxx, rect[2]);
        maxy = max(maxy, rect[3]);
        area +=  static_cast<long long>(rect[2] - rect[0]) * (rect[3] - rect[1]);
        pair<long long, long long> p1 = {rect[0], rect[1]};
        pair<long long, long long> p2 = {rect[0], rect[3]};
        pair<long long, long long> p3 = {rect[2], rect[3]};
        pair<long long, long long> p4 = {rect[2], rect[1]};
        
        if (!corners.insert(p1).second) corners.erase(p1);
        if (!corners.insert(p2).second) corners.erase(p2);
        if (!corners.insert(p3).second) corners.erase(p3);
        if (!corners.insert(p4).second) corners.erase(p4);
    }
    
    if (corners.size() != 4 ||
        corners.count({minx, miny}) == 0 ||
        corners.count({minx, maxy}) == 0 ||
        corners.count({maxx, miny}) == 0 ||
        corners.count({maxx, maxy}) == 0) {
        return false;
    }
    
    long long expectedArea =  static_cast<long long>(maxx - minx) * (maxy - miny);
    return area == expectedArea;
}
};